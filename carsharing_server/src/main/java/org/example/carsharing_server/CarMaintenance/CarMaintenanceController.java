package org.example.carsharing_server.CarMaintenance;

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
@RequestMapping("/carMaintenance")
public class CarMaintenanceController {

    private final CarMaintenanceService carMaintenanceService;

    @Autowired
    public CarMaintenanceController(CarMaintenanceService carMaintenanceService) {this.carMaintenanceService = carMaintenanceService;}

    @GetMapping
    public ResponseEntity<String> getCarMaintenance() {
        List<CarMaintenance> carMaintenances = carMaintenanceService.getCarMaintenances();
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(carMaintenances);
        } catch (JsonProcessingException e) {
            res = carMaintenances.toString();
        }
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<String> addNewCarMaintenance(@Valid @RequestBody CarMaintenance carMaintenance) {
        try {
            carMaintenanceService.addNewCarMaintenance(carMaintenance);
            return ResponseEntity.ok("Car Maintenance added successfully");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> updateCarMaintenance(@Valid @RequestBody CarMaintenance carMaintenance) {
        try{
            carMaintenanceService.updateCarMaintenance(carMaintenance);
            return ResponseEntity.ok("Car Maintenance updated successfully");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCarMaintenance(@Valid @PathVariable("id") @Min(0) Integer carMaintenanceId) {
        try{
            carMaintenanceService.deleteCarMaintenance(carMaintenanceId);
            return ResponseEntity.ok("Car Maintenance deleted successfully");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{licensePlate}")
    public ResponseEntity<String> getCarsCarMaintenance(@Valid @PathVariable("licensePlate") @NotBlank @Size(min = 6, max = 6) String licensePlate) {
        List<CarMaintenance> carMaintenances = carMaintenanceService.getCarsCarMaintenance(licensePlate);
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(carMaintenances);
        } catch (JsonProcessingException e) {
            res = carMaintenances.toString();
        }
        return ResponseEntity.ok(res);
    }
}





