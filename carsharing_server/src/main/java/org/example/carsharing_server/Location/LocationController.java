package org.example.carsharing_server.Location;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {this.locationService = locationService;}

    @GetMapping("/locations")
    public ResponseEntity<String> getLocations() {
        List<Location> locations = locationService.getLocations();
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(locations);
        } catch (JsonProcessingException e) {
            res = locations.toString();
        }
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public void addNewLocation(@RequestBody Location location) {
        try {
            locationService.addNewLocation(location);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @PutMapping
    public void updateLocation(@RequestBody Location location) {
        try {
            locationService.updateLocation(location);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @DeleteMapping("/locations/{locationId}")
    public void deleteLocation(@PathVariable int locationId) {
        try {
            locationService.deleteLocation(locationId);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @GetMapping("/locations/{licensePlate}")
    public ResponseEntity<String> getCarsLocation(@PathVariable String licensePlate) {
        List<Location> locations = locationService.getCarsLocation(licensePlate);
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(locations);
        } catch (JsonProcessingException e) {
            res = locations.toString();
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/locations/{bookingId}/start")
    public ResponseEntity<String> getBookingStartingLocation(@PathVariable String bookingId) {
        List<Location> locations = locationService.getBookingStartingLocation(bookingId);
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(locations);
        } catch (JsonProcessingException e) {
            res = locations.toString();
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/locations/{bookingId}/end")
    public ResponseEntity<String> getBookingEndingLocation(@PathVariable String bookingId) {
        List<Location> locations = locationService.getBookingEndingLocation(bookingId);
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(locations);
        } catch (JsonProcessingException e) {
            res = locations.toString();
        }
        return ResponseEntity.ok(res);
    }

}




