package org.example.carsharing_server.CarMaintenance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carMaintenance")
public class CarMaintenanceController {

    private final CarMaintenanceService carMaintenanceService;

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
    public void addNewCarMaintenance(@RequestBody CarMaintenance carMaintenance) {
        try {
            carMaintenanceService.addNewCarMaintenance(carMaintenance);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @PutMapping
    public void updateCarMaintenance(@RequestBody CarMaintenance carMaintenance) {
        try{
            carMaintenanceService.updateCarMaintenance(carMaintenance);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @DeleteMapping("/{carMaintenanceId}")
    public void deleteCarMaintenance(@PathVariable int carMaintenanceId) {
        try{
            carMaintenanceService.deleteCarMaintenance(carMaintenanceId);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @GetMapping("/{licensePlate}")
    public ResponseEntity<String> getCarsCarMaintenance(@PathVariable String licensePlate) {
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





