package org.example.carsharing_server.CarMaintenance;

import org.example.carsharing_server.Car.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
interface CarMaintenanceService {

    List<CarMaintenance> getCarMaintenances();
    void addNewCarMaintenance(CarMaintenance carMaintenance);
    void updateCarMaintenance(CarMaintenance carMaintenance);
    void deleteCarMaintenance(int carMaintenanceId);

    List<CarMaintenance> getCarsCarMaintenance(String licensePlate);
}
