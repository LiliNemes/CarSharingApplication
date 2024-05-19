package org.example.carsharing_server.Car;

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
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {this.carService = carService;}

    @GetMapping
    public ResponseEntity<String> getCars() {
        List<Car> cars = carService.getCars();
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(cars);
        } catch (JsonProcessingException e) {
            res = cars.toString();
        }
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public void addNewCar(@Valid @RequestBody Car car, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            carService.addNewCar(car, userDetails);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @PutMapping
    public void updateCar(@Valid @RequestBody Car car, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            carService.updateCar(car, userDetails);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @DeleteMapping("/car/{licensePlate}")
    public void deleteCars(@Valid @PathVariable("licensePlate") @NotBlank @Size(min = 6, max = 6) String licensePlate, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            carService.deleteCar(licensePlate, userDetails);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @GetMapping("/available")
    public ResponseEntity<String> getAvailableCars() {
        List<Car> cars = carService.getAvailableCars();
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(cars);
        } catch (JsonProcessingException e) {
            res = cars.toString();
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<String> getOwnedCars(@Valid @PathVariable @Min(1) Integer userId, @AuthenticationPrincipal UserDetails userDetails) {
        List<Car> cars = carService.getOwnedCars(userId, userDetails);
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(cars);
        } catch (JsonProcessingException e) {
            res = cars.toString();
        }
        return ResponseEntity.ok(res);
    }


}








