package org.example.carsharing_server.Car;

import jakarta.transaction.Transactional;
import org.example.carsharing_server.Location.LocationService;
import org.example.carsharing_server.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.carsharing_server.Car.CarSpecification.ownedCars;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final LocationService locationService;
    private final UserService userService;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, LocationService locationService, UserService userService) {
        this.carRepository = carRepository;
        this.locationService = locationService;
        this.userService = userService;
    }

    @Override
    public List<Car> getCars() {return carRepository.findAll();}

    @Override
    public void addNewCar(Car car, UserDetails userDetails) {
        if (!userService.getUser(car.getOwner().getUser_id()).getEmail().equals(userDetails.getUsername())) {
            throw new IllegalArgumentException("User is trying to access a car in the name of someone else");
        }

        if (car.getLocation() != null) {
            locationService.addNewLocation(car.getLocation());
        }
        else {
            throw new IllegalArgumentException("Car location is required");
        }

        if (car.getOwner() != null) {
            try {
                car.setOwner(userService.getUser(car.getOwner().getUser_id()));
            } catch (Exception e) {
                throw new IllegalArgumentException("Car owner does not exist");
            }
        }
        else {
            throw new IllegalArgumentException("Car owner is required");
        }

        carRepository.save(car);
    }

    @Transactional
    @Override
    public void updateCar(Car car, UserDetails userDetails) {
        if (!userService.getUser(car.getOwner().getUser_id()).getEmail().equals(userDetails.getUsername())) {
            throw new IllegalArgumentException("User is trying to access a car in the name of someone else");
        }

        Car existingCar = carRepository.findById(car.getLicensePlate()).orElseThrow(() ->
                new IllegalArgumentException("Car license plate " + car.getLicensePlate() + " does not exists"));

        if (car.getLocation() != null)
            existingCar.setLocation(car.getLocation());

        if (car.getModel() != null && !car.getModel().isBlank())
            existingCar.setModel(car.getModel());

        existingCar.setAvailabilityStatus(car.getAvailabilityStatus());

        if (car.getOwner() != null)
            existingCar.setOwner(car.getOwner());

        existingCar.setReleaseYear(car.getReleaseYear());

        if (car.getLicensePlate() != null && !car.getLicensePlate().isBlank())
            existingCar.setLicensePlate(car.getLicensePlate());
    }

    @Override
    public void deleteCar(String licensePlate, UserDetails userDetails) {
        Car car = carRepository.findById(licensePlate).orElseThrow(() ->
                new IllegalArgumentException("Car license plate " + licensePlate + " does not exists"));

        if (!userService.getUser(car.getOwner().getUser_id()).getEmail().equals(userDetails.getUsername())) {
            throw new IllegalArgumentException("User is trying to access a car in the name of someone else");
        }

        carRepository.delete(car);
    }

    @Override
    public List<Car> getAvailableCars() {
        return carRepository.getCarsByAvailabilityStatus(true);
    }

    @Override
    public List<Car> getOwnedCars(int userId, UserDetails userDetails) {
        if (!userService.getUser(userId).getEmail().equals(userDetails.getUsername())) {
            throw new IllegalArgumentException("User is trying to access someone other's car!");
        }

        return carRepository.findAll(ownedCars(userId));
    }

    @Override
    public Car getCar(String licensePlate)
    {
        return carRepository.findById(licensePlate).orElseThrow(() ->
                new IllegalArgumentException("Car license plate " + licensePlate + " does not exists"));
    }

    @Override
    @Transactional
    public void updateAvailability(String licensePlate, boolean availability){
        Car car = carRepository.findById(licensePlate).orElseThrow(() -> new IllegalArgumentException("Car not found!"));
        car.setAvailabilityStatus(availability);
    }
}
