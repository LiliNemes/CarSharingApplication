package org.example.carsharing_server.Booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/bookings")
    public ResponseEntity<String> getBookings(){
        List<Booking> bookings = bookingService.getBookings();
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(bookings);
        } catch (JsonProcessingException e) {
            res = bookings.toString();
        }
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public void addNewBooking(Booking booking) {
        try {
            bookingService.addNewBooking(booking);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @DeleteMapping
    public void deleteBooking(Booking booking){
        try {
            bookingService.deleteBooking(booking);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @GetMapping("/bookings/{userId}/past")
    public ResponseEntity<String> getPastBookingOfUser(@PathVariable String userId){
        List<Booking> bookings = bookingService.getPastBookingsOfUser(userId);
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(bookings);
        } catch (JsonProcessingException e) {
            res = bookings.toString();
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/bookings/{userId}/present")
    public ResponseEntity<String> getPresentBookings(@PathVariable String userId){
        List<Booking> bookings = bookingService.getPresentBookingsOfUser(userId);
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(bookings);
        } catch (JsonProcessingException e) {
            res = bookings.toString();
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/bookings/{userID}")
    public ResponseEntity<String> getUsersBookings(@PathVariable String userID){
        List<Booking> bookings = bookingService.getUsersBookings(userID);
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(bookings);
        } catch (JsonProcessingException e) {
            res = bookings.toString();
        }
        return ResponseEntity.ok(res);
    }
    @GetMapping("/bookings/{licensePlate}")
    public ResponseEntity<String> getCarsBookings(@PathVariable String licensePlate){
        List<Booking> bookings = bookingService.getCarsBookings(licensePlate);
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(bookings);
        } catch (JsonProcessingException e) {
            res = bookings.toString();
        }
        return ResponseEntity.ok(res);
    }
    @GetMapping("/bookings/{userId}")
    public ResponseEntity<String> carsOwnersBookings(@PathVariable String userId){
        List<Booking> bookings = bookingService.carsOwnersBookings(userId);
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(bookings);
        } catch (JsonProcessingException e) {
            res = bookings.toString();
        }
        return ResponseEntity.ok(res);
    }
}
