package org.example.carsharing_server.Car;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
interface CarService {
    List<Car> getCars();

    void addNewCar(Car car);

    void updateCar(Car car);

    void deleteCar(String LicensePlate);

    List<Car> getAvailableCars(String locationId);
    List<Car> getOwnedCars(String userId);
}
