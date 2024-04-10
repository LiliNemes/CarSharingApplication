package org.example.carsharing_server.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.carsharing_server.Booking.BookingSpecification.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class BookingServiceImpl implements BookingService{
    private final BookingRepository bookingRepository;
    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public void addNewBooking(Booking booking) {
       bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Booking booking) {
        bookingRepository.deleteById(booking.getBookingId());
    }

    @Override
    public List<Booking> getPastBookingsOfUser(String userId) {
        int userIdNum = Integer.parseInt(userId);
        return bookingRepository.findAll(where(pastBookings().and(usersBookings(userIdNum))));
    }

    @Override
    public List<Booking> getPresentBookingsOfUser(String userId) {
        int userIdNum = Integer.parseInt(userId);
        return bookingRepository.findAll(where(presentBookings().and(usersBookings(userIdNum))));
    }

    @Override
    public List<Booking> getUsersBookings(String userId) {
        int userIdNum = Integer.parseInt(userId);
        return bookingRepository.findAll(usersBookings(userIdNum));
    }

    @Override
    public List<Booking> getCarsBookings(String licensePlate) {
        return bookingRepository.findAll(carsBookings(licensePlate));
    }

    @Override
    public List<Booking> carsOwnersBookings(String userId) {
        int userIdNum = Integer.parseInt(userId);
        return bookingRepository.findAll(CarsOwnersBookings(userIdNum));
    }


}
