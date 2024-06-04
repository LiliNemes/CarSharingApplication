package org.example.carsharing_server.Booking;

import jakarta.transaction.Transactional;
import org.example.carsharing_server.ReviewRating.ReviewRating;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {

    List<Booking> getBookings();

    void addNewBooking(Booking booking, UserDetails userDetails);

    @Transactional
    void stopBooking(Booking booking, UserDetails userDetails);

    @Transactional
    void addBookingReview(Booking booking, ReviewRating reviewRating, UserDetails userDetails);

    List<Booking> getPastBookingsOfUser(int userId, UserDetails userDetails);

    List<Booking> getPresentBookingsOfUser(int userId, UserDetails userDetails);

    List<Booking> getUsersBookings(int userID, UserDetails userDetails);

    List<Booking> getCarsBookings(String licensePlate, UserDetails userDetails);

    Booking getBooking(int bookingId);

    List<Booking> getBookingsByCar(String licensePlate);
}
