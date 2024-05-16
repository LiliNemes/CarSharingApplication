package org.example.carsharing_server.Car;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

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
    public void addNewCar(@RequestBody Car car) {
        try {
            carService.addNewCar(car);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @PutMapping
    public void updateCar(@RequestBody Car car) {
        try {
            carService.updateCar(car);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @DeleteMapping("/{licensePlate}")
    public void deleteCars(@PathVariable String licensePlate) {
        try {
            carService.deleteCar(licensePlate);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<String> getAvailableCars(@PathVariable String locationId) {
        List<Car> cars = carService.getAvailableCars(Integer.parseInt(locationId));
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(cars);
        } catch (JsonProcessingException e) {
            res = cars.toString();
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<String> getOwnedCars(@PathVariable String userId) {
        List<Car> cars = carService.getOwnedCars(userId);
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








