package org.example.carsharing_server.CarMaintenance;

import jakarta.transaction.Transactional;
import org.example.carsharing_server.Car.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
interface CarMaintenanceService {

    List<CarMaintenance> getCarMaintenances();

    void addNewCarMaintenance(CarMaintenance carMaintenance);

    @Transactional
    void updateCarMaintenance(CarMaintenance carMaintenance);

    void deleteCarMaintenance(int carMaintenanceId);

    List<CarMaintenance> getCarsCarMaintenance(String licensePlate);
}
