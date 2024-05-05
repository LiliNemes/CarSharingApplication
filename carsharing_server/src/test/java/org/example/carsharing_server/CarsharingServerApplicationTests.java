package org.example.carsharing_server;

import org.aspectj.lang.annotation.Before;
import org.example.carsharing_server.Car.Car;
import org.example.carsharing_server.Car.CarRepository;
import org.example.carsharing_server.Car.CarService;
import org.example.carsharing_server.Car.CarServiceImpl;
import org.example.carsharing_server.Location.Location;
import org.example.carsharing_server.User.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class CarsharingServerApplicationTests {

	private List<Car> carList;
	private Car car;
	private CarService carService;
	private CarRepository carRepository;
	@BeforeTestClass
	public void setUp(){
		carRepository = mock(CarRepository.class);
		carService = new CarServiceImpl(carRepository);
	}

	@Test
	public void testGetCars(){

		List<Car> expectedCars = new ArrayList<>();
		expectedCars.add(new Car("Audi", 2024, "ABC123", null, null));
		expectedCars.add(new Car("BMW", 2024, "DEF123", null, null));

		when(carService.getCars()).thenReturn(expectedCars);

		List<Car> actualCars = carService.getCars();

		assertEquals(expectedCars, actualCars);
	}

	@Test
	public void testAddNewCar(Car car){
		List<Car> expectedCars = new ArrayList<>();
		expectedCars.add(new Car());

		int actualCars = carService.getCars().size();

		assertEquals(expectedCars.size(), actualCars);
	}

	@Test
	public void testDeleteCar(String LicensePlate){
		carService.addNewCar(new Car("Audi", 2024, "ABC123", null, null));
		carService.addNewCar(new Car());
		carService.addNewCar(new Car());
		carService.deleteCar("ABC123");

		assertEquals(carList.size(), 2);
	}

	@Test
	public void testGetAvailableCars(String locationId){
		Location location = new Location();
		location.setLocationID(Integer.parseInt(locationId));
		carService.addNewCar(new Car("Audi", 2024, "ABC123", location, null));
		carService.addNewCar(new Car("BMW", 2024, "def123", location, null));
		carService.getAvailableCars(locationId);

		assertEquals(carService.getAvailableCars(locationId).size(), 2);
	}

	@Test
	public void testGetOwnedCars(String userId){
		User user = new User();
		user.setUser_id(Integer.parseInt(userId));
		carService.addNewCar(new Car("Audi", 2024, "ABC123", null, user));
		carService.addNewCar(new Car("BMW", 2024, "def123", null, user));
		carService.getOwnedCars(userId);

		assertEquals(carService.getOwnedCars(userId).size(), 2);
	}
}
