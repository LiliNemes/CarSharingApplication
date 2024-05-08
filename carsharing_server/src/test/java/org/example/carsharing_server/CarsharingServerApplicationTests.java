package org.example.carsharing_server;

import org.example.carsharing_server.Car.*;
import org.example.carsharing_server.Location.Location;
import org.example.carsharing_server.User.User;
import org.example.carsharing_server.User.UserType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarsharingServerApplicationTests {
		private List<Car> carList;
		private Car car;
		private CarService carService;
		private CarRepository carRepository;
		private CarController carController;

	@Before
	public void setUp() throws NoSuchFieldException, IllegalAccessException {
		carRepository = Mockito.mock(CarRepository.class);
		carService = new CarServiceImpl(carRepository);
		carController = new CarController(carService);

		Location location = new Location("Mockito street 12", 12.5, 50.5);
		User user = new User("MarNemSzamit", "marnemszamit@gmail.com", 3690123, "marnemszamit123", 100, UserType.OWNER);
		car = new Car("Audi", 2024, "ABC123", location, user);

		Field f = car.getClass().getDeclaredField(car.getLicensePlate());
		f.setAccessible(true);
		f.set(car, "licensePlate");
		}


	@Test
	public void testGetCars(){
		carController.getCars();
		Mockito.verify(carRepository, Mockito.times(1)).findAll();
	}

	@Test
	public void testAddNewCar(){
		Mockito.when(carRepository.findById(car.getLicensePlate())).thenReturn(Optional.empty());
		carController.addNewCar(car);
		Mockito.verify(carRepository, Mockito.times(1)).save(car);
	}

	@Test
	public void testAddExistingCar(){
		Mockito.when(carRepository.findById(car.getLicensePlate())).thenReturn(Optional.of(car));
		carController.addNewCar(car);
	}

	@Test
	public void testUpdateCar() throws NoSuchFieldException, IllegalAccessException {
		Mockito.when(carRepository.findById(car.getLicensePlate())).thenReturn(Optional.of(car));
		Mockito.when(carRepository.findById(car.getLicensePlate())).thenReturn(Optional.empty());

		Car car1 = new Car("DEF123");

		Field f1 = car1.getClass().getDeclaredField("licensePlate");
		f1.setAccessible(true);
		f1.set(car1, car.getLicensePlate());

		carController.updateCar(car1);
		Assert.assertEquals("DEF123", car.getLicensePlate());

	}

	@Test
	public void testUpdateNonExistingCar() throws NoSuchFieldException, IllegalAccessException {
		Mockito.when(carRepository.findById(car.getLicensePlate())).thenReturn(Optional.empty());

		Car car1 = new Car("DEF123");

		Field f1 = car1.getClass().getDeclaredField("licensePlate");
		f1.setAccessible(true);
		f1.set(car1, car.getLicensePlate());

		carController.updateCar(car1);
	}

	@Test
	public void testUpdateCarWithSameName() throws NoSuchFieldException, IllegalAccessException {
		Car car2 = new Car("DEF123");
		Mockito.when(carRepository.findById(car.getLicensePlate())).thenReturn(Optional.of(car));
		Mockito.when(carRepository.findById(car.getLicensePlate())).thenReturn(Optional.of(car2));

		Car car1 = new Car("DEF123");

		Field f1 = car1.getClass().getDeclaredField("licensePlate");
		f1.setAccessible(true);
		f1.set(car1, car.getLicensePlate());

		carController.updateCar(car1);
	}

	@Test
	public void testUpdateCarWithNullName() throws NoSuchFieldException, IllegalAccessException {
		Mockito.when(carRepository.findById(car.getLicensePlate())).thenReturn(Optional.of(car));

		Car car1 = new Car("null");

		Field f1 = car1.getClass().getDeclaredField("licensePlate");
		f1.setAccessible(true);
		f1.set(car1, car.getLicensePlate());

		carController.updateCar(car1);
		Assert.assertEquals("ABC123", car.getLicensePlate());
	}

	@Test
	public void testDeleteCar(){
		Mockito.when(carRepository.existsById(car.getLicensePlate())).thenReturn(true);
		carController.deleteCar(car.getLicensePlate());
		Mockito.verify(carRepository, Mockito.times(1)).deleteById(car.getLicensePlate());
	}

	@Test
	public void testDeleteNonExisitngCar(){
		Mockito.when(carRepository.existsById(car.getLicensePlate())).thenReturn(false);
		carController.deleteCar(car.getLicensePlate());
	}

	@Test
	public void testGetAvailableCars(){
		carController.getAvailableCars(String.valueOf(car.getLocation().getLocationID()));
		Mockito.verify(carRepository, times(1)).findAll();
	}

	@Test
	public void testGetOwnedCars(){
		carController.getOwnedCars(String.valueOf(car.getOwnerId().getUser_id()));
		Mockito.verify(carRepository, times(1)).findAll();
	}
}
