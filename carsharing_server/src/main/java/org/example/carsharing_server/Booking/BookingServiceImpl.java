package org.example.carsharing_server.Booking;

import jakarta.transaction.Transactional;
import org.example.carsharing_server.Car.CarService;
import org.example.carsharing_server.Payment.PaymentService;
import org.example.carsharing_server.ReviewRating.ReviewRating;
import org.example.carsharing_server.User.User;
import org.example.carsharing_server.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.example.carsharing_server.Booking.BookingSpecification.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;
    private final CarService carService;
    private final UserService userService;
    private final PaymentService paymentService;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, CarService carService, UserService userService, PaymentService paymentService) {
        this.bookingRepository = bookingRepository;
        this.carService = carService;
        this.userService = userService;
        this.paymentService = paymentService;
    }

    @Override
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public void addNewBooking(Booking booking, UserDetails userDetails) {
        if (!userService.getUser(booking.getUser().getUser_id()).getEmail().equals(userDetails.getUsername())) {
            throw new IllegalArgumentException("User is trying to access other's bookings!");
        }

        if (booking.getCar() != null) {
            try {
                booking.setCar(carService.getCar(booking.getCar().getLicensePlate()));
            } catch (Exception e) {
                throw new IllegalArgumentException("Car does not exist");
            }
            if (!booking.getCar().getAvailabilityStatus())
                    throw new IllegalArgumentException("car is already reserved!");
        }
        else {
            throw new IllegalArgumentException("Car is required");
        }

        if (booking.getUser() != null) {
            try {
                booking.setUser(userService.getUser(booking.getUser().getUser_id()));
            } catch (Exception e) {
                throw new IllegalArgumentException("User does not exist");
            }
        }
        else {
            throw new IllegalArgumentException("User is required");
        }

        booking.configure();

        paymentService.addNewPayment(booking.getPayment(), userDetails);
        bookingRepository.save(booking);
        carService.updateAvailability(booking.getCar().getLicensePlate(), false);
        booking.getPayment().setBooking(booking);
        paymentService.updatePayment(booking.getPayment());
    }

    @Override
    @Transactional
    public void stopBooking(Booking booking, UserDetails userDetails) {
        User bookingUser = userService.getUser(booking.getUser().getUser_id());
        if (!Objects.equals(bookingUser.getEmail(), userDetails.getUsername()))
            throw new IllegalArgumentException("User is trying to access someone other's booking!");

        Booking initialBooking = bookingRepository.findById(booking.getBookingId()).orElseThrow(() ->
                new IllegalArgumentException("booking with id " + booking.getBookingId() + " does not exists"));

        if (initialBooking.getUser().getUser_id() != booking.getUser().getUser_id())
            throw new IllegalArgumentException("You are not the owner of this booking");

        if (initialBooking.getEnd_time() == null) {
            initialBooking.setEnd_time(LocalDateTime.now());
            initialBooking.setTotal_cost(initialBooking.getPayment().getAmount());
            carService.updateAvailability(initialBooking.getCar().getLicensePlate(), true);
        }
    }

    @Override
    @Transactional
    public void addBookingReview(Booking booking, ReviewRating reviewRating, UserDetails userDetails) {
        User bookingUser = userService.getUser(booking.getUser().getUser_id());
        if (!Objects.equals(bookingUser.getEmail(), userDetails.getUsername()))
            throw new IllegalArgumentException("User is trying to access someone other's booking!");

        Booking initialBooking = bookingRepository.findById(booking.getBookingId()).orElseThrow(() ->
                new IllegalArgumentException("booking with id " + booking.getBookingId() + " does not exists"));

        if (initialBooking.getUser().getUser_id() != booking.getUser().getUser_id())
            throw new IllegalArgumentException("You are not the owner of this booking");


        if (initialBooking.getReview() == null) {
            initialBooking.setReview(reviewRating);
        }
    }

    @Override
    public List<Booking> getPastBookingsOfUser(int userId, UserDetails userDetails) {
        if (!userService.getUser(userId).getEmail().equals(userDetails.getUsername())) {
            throw new IllegalArgumentException("User is trying to access other's bookings!");
        }
        return bookingRepository.findAll(where(pastBookings().and(usersBookings(userId))));
    }

    @Override
    public List<Booking> getPresentBookingsOfUser(int userId, UserDetails userDetails) {
        if (!userService.getUser(userId).getEmail().equals(userDetails.getUsername())) {
            throw new IllegalArgumentException("User is trying to access other's bookings!");
        }
        return bookingRepository.findAll(where(presentBookings().and(usersBookings(userId))));
    }

    @Override
    public List<Booking> getUsersBookings(int userId, UserDetails userDetails) {
        if (!userService.getUser(userId).getEmail().equals(userDetails.getUsername())) {
            throw new IllegalArgumentException("User is trying to access other's bookings!");
        }
        return bookingRepository.findAll(usersBookings(userId));
    }

    @Override
    public List<Booking> getCarsBookings(String licensePlate, UserDetails userDetails) {
        if (!carService.getCar(licensePlate).getOwner().getEmail().equals(userDetails.getUsername())) {
            throw new IllegalArgumentException("User is trying to access other's bookings!");
        }
        return bookingRepository.findAll(carsBookings(licensePlate));
    }
    @Override
    public Booking getBooking(int bookingId)
    {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() ->
                new IllegalArgumentException("BookingId " + bookingId + " does not exists"));
        return booking;
    }

    @Override
    public List<Booking> getBookingsByCar(String licensePlate) {
        return bookingRepository.findAll(carsBookings(licensePlate));
    }
}
