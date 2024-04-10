package org.example.carsharing_server.Booking;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
interface BookingService {
    List<Booking> getBookings();

    void addNewBooking(Booking booking);

    void deleteBooking(Booking booking);
    List<Booking> getPastBookingsOfUser(String userId);
    List<Booking> getPresentBookingsOfUser(String userId);
    List<Booking> getUsersBookings(String userID);
    List<Booking> getCarsBookings(String licensePlate);
    List<Booking> carsOwnersBookings(String userId);



}
