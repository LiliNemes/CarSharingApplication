package org.example.carsharing_server.Car;

import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
interface CarService {

    List<Car> getCars();

    void addNewCar(Car car, UserDetails userDetails);

    @Transactional
    void updateCar(Car car, UserDetails userDetails);

    void deleteCar(String LicensePlate, UserDetails userDetails);

    Car getCar(String licensePlate);

    List<Car> getAvailableCars();

    List<Car> getOwnedCars(int userId, UserDetails userDetails);

    void updateAvailability(String licensePlate, boolean availability);
}
