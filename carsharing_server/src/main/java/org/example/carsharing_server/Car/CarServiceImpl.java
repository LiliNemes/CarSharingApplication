package org.example.carsharing_server.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.carsharing_server.Car.CarSpecification.*;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {this.carRepository = carRepository;}

    @Override
    public List<Car> getCars() {return carRepository.findAll();}

    @Override
    public void addNewCar(Car car) {carRepository.save(car); }

    @Override
    public void updateCar(Car car) {
        Car existingCar = carRepository.findById(car.getLicensePlate()).orElseThrow(() ->
                new IllegalArgumentException("Car license plate " + car.getLicensePlate() + " does not exists"));
        existingCar.setLocation(car.getLocation());
        existingCar.setModel(car.getModel());
        existingCar.setAvailabilityStatus(car.getAvailabilityStatus());
        existingCar.setOwnerId(car.getOwnerId());
        existingCar.setReleaseYear(car.getReleaseYear());
        existingCar.setBookings(car.getBookings());
        existingCar.setLicensePlate(car.getLicensePlate());
        existingCar.addMaintenance(car.getMaintenanceRecord().getLast());
        carRepository.save(existingCar);
    }

    @Override
    public void deleteCar(String licensePlate) {
        Car car = carRepository.findById(licensePlate).orElseThrow(() ->
                new IllegalArgumentException("Car license plate " + licensePlate + " does not exists"));
        carRepository.delete(car);
    }

    @Override
    public List<Car> getAvailableCars(String locationId) {
        int locationIdNum = Integer.parseInt(locationId);
        return carRepository.findAll(availableCars(locationIdNum));
    }

    @Override
    public List<Car> getOwnedCars(String userId) {
        int userIdNum = Integer.parseInt(userId);
        return carRepository.findAll(ownedCars(userIdNum));
    }
}
