package org.example.carsharing_server.CarMaintenance;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.carsharing_server.CarMaintenance.CarMaintenanceSpecification.*;

@Service
public class CarMaintenanceServiceImpl implements CarMaintenanceService{
    private final CarMaintenanceRepository carMaintenanceRepository;

    @Autowired
    public CarMaintenanceServiceImpl(CarMaintenanceRepository carMaintenanceRepository) {this.carMaintenanceRepository = carMaintenanceRepository;}

    @Override
    public List<CarMaintenance> getCarMaintenances() {return carMaintenanceRepository.findAll();}

    @Override
    public void addNewCarMaintenance(CarMaintenance carMaintenance) {carMaintenanceRepository.save(carMaintenance);}

    @Override
    @Transactional
    public void updateCarMaintenance(CarMaintenance carMaintenance) {
        CarMaintenance currentCM = carMaintenanceRepository.findById(carMaintenance.getMaintenanceID()).orElseThrow(() ->
                new IllegalArgumentException("CarMaintenance Id " + carMaintenance.getMaintenanceID() + " does not exists"));
        if (carMaintenance.getCar() != null)
            currentCM.setCar(carMaintenance.getCar());

        if (carMaintenance.getCost() != 0)
            currentCM.setCost(carMaintenance.getCost());

        if (carMaintenance.getTypeOfMaintenance() != null && !carMaintenance.getTypeOfMaintenance().isBlank())
            currentCM.setTypeOfMaintenance(carMaintenance.getTypeOfMaintenance());

        if (carMaintenance.getDetails() != null && !carMaintenance.getDetails().isBlank())
        currentCM.setDetails(carMaintenance.getDetails());
        currentCM.setMaintenanceDate(carMaintenance.getMaintenanceDate());
        carMaintenanceRepository.save(currentCM);
    }

    @Override
    public void deleteCarMaintenance(int carMaintenanceId) {
        CarMaintenance carMaintenance = carMaintenanceRepository.findById(carMaintenanceId).orElseThrow(() ->
                new IllegalArgumentException("CarMaintenance Id " + carMaintenanceId + "does not exist"));
        carMaintenanceRepository.delete(carMaintenance);
    }

    @Override
    public List<CarMaintenance> getCarsCarMaintenance(String licensPlate) {
        return carMaintenanceRepository.findAll(carsCarMaintenance(licensPlate));
    }

}
