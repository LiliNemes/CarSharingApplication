package org.example.carsharing_server.CarMaintenance;

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
    public void updateCarMaintenance(CarMaintenance carMaintenance) {
        CarMaintenance currentCM = carMaintenanceRepository.findById(carMaintenance.getMaintenanceID()).orElseThrow(() ->
                new IllegalArgumentException("CarMaintenance Id " + carMaintenance.getMaintenanceID() + " does not exists"));
        currentCM.setCar(carMaintenance.getCar());
        currentCM.setMaintenanceID(carMaintenance.getMaintenanceID());
        currentCM.setCost(carMaintenance.getCost());
        currentCM.setTypeOfMaintenance(carMaintenance.getTypeOfMaintenance());
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
