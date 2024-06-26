package org.example.carsharing_server.Location;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {this.locationService = locationService;}

    @GetMapping
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
    public void addNewLocation(@Valid @RequestBody Location location) {
        try {
            locationService.addNewLocation(location);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @PutMapping
    public void updateLocation(@Valid @RequestBody Location location) {
        try {
            locationService.updateLocation(location);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @DeleteMapping("/{locationId}")
    public void deleteLocation(@Valid @PathVariable @Min(0) Integer locationId) {
        try {
            locationService.deleteLocation(locationId);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @GetMapping("/{licensePlate}")
    public ResponseEntity<String> getCarsLocation(@Valid @PathVariable @NotBlank @Size(min = 6, max = 6) String licensePlate) {
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

    @GetMapping("/{bookingId}/start")
    public ResponseEntity<String> getBookingStartingLocation(@Valid @PathVariable @Min(0) Integer bookingId) {
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

    @GetMapping("/{bookingId}/end")
    public ResponseEntity<String> getBookingEndingLocation(@Valid @PathVariable @Min(0) Integer bookingId) {
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




