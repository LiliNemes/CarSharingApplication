package org.example.carsharing_server.Booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
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
    public ResponseEntity<String> addNewBooking(@Valid @RequestBody Booking booking, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            bookingService.addNewBooking(booking, userDetails);
            return ResponseEntity.ok("Booking added successfully");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/stop")
    public ResponseEntity<String> stopBooking(@Valid @RequestBody Booking booking, @AuthenticationPrincipal UserDetails userDetails){
        try{
            bookingService.stopBooking(booking, userDetails);
            return ResponseEntity.ok("Booking updated successfully");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/past/{userId}")
    public ResponseEntity<String> getPastBookingOfUser(@Valid @PathVariable @Min(1) Integer userId, @AuthenticationPrincipal UserDetails userDetails){
        List<Booking> bookings = bookingService.getPastBookingsOfUser(userId, userDetails);
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(bookings);
        } catch (JsonProcessingException e) {
            res = bookings.toString();
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/user/current/{userId}")
    public ResponseEntity<String> getPresentBookings(@Valid @PathVariable @Min(1) Integer userId, @AuthenticationPrincipal UserDetails userDetails){
        List<Booking> bookings = bookingService.getPresentBookingsOfUser(userId, userDetails);
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(bookings);
        } catch (JsonProcessingException e) {
            res = bookings.toString();
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/users/{userID}")
    public ResponseEntity<String> getUsersBookings(@Valid @PathVariable @Min(0) Integer userID, @AuthenticationPrincipal UserDetails userDetails){
        try {
            List<Booking> bookings = bookingService.getUsersBookings(userID, userDetails);
            ObjectMapper objectMapper = new ObjectMapper();
            String res;
            try {
                res = objectMapper.writeValueAsString(bookings);
            } catch (JsonProcessingException e) {
                res = bookings.toString();
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("cars/{licensePlate}")
    public ResponseEntity<String> getCarsBookings(@Valid @PathVariable @NotBlank @Size(min = 6, max = 6) String licensePlate, @AuthenticationPrincipal UserDetails userDetails){
        List<Booking> bookings = bookingService.getCarsBookings(licensePlate, userDetails);
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
