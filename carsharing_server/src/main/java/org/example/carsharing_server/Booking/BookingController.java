package org.example.carsharing_server.Booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
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
    public void addNewBooking(@RequestBody Booking booking) {
        try {
            bookingService.addNewBooking(booking);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @PutMapping
    public void deleteBooking(@RequestBody Booking booking){
        try{
            bookingService.updateBooking(booking);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @GetMapping("/{userId}/past")
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

    @GetMapping("/{userId}/present")
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

    @GetMapping("/{userID}")
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
    @GetMapping("/{licensePlate}")
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

}
