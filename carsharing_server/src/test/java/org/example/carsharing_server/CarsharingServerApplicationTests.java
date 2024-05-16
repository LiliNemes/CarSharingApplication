package org.example.carsharing_server;

import org.example.carsharing_server.Car.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest
class CarsharingServerApplicationTest {

	//private List<Car> carList;
	Car car;
	CarController carController;
	CarRepository carRepository;

	@BeforeEach
	public void init(){
		carRepository = Mockito.mock(CarRepository.class);
		CarService carService = new CarServiceImpl(carRepository);
		carController = new CarController(carService);
		car = new Car();
	}

	@Test
	public void testGetCars(){
		carController.getCars();
		Mockito.verify(carRepository, times(1)).findAll();

		//List<Car> expectedCars = new ArrayList<>();
		//expectedCars.add(new Car("Audi", 2024, "ABC123", null, null));
		//expectedCars.add(new Car("BMW", 2024, "DEF123", null, null));

		//when(carController.getCars()).thenReturn((ArrayList<Car>) expectedCars);

		//List<Car> actualCars = (List<Car>) carController.getCars();

		//assertEquals(expectedCars, actualCars);
	}

//	@Test
//	public void testAddNewCar(Car car){
//		List<Car> expectedCars = new ArrayList<>();
//		expectedCars.add(new Car());
//
//		int actualCars = carService.getCars().size();
//
//		assertEquals(expectedCars.size(), actualCars);
//	}
//
//	@Test
//	public void testDeleteCar(String LicensePlate){
//		carService.addNewCar(new Car("Audi", 2024, "ABC123", null, null));
//		carService.addNewCar(new Car());
//		carService.addNewCar(new Car());
//		carService.deleteCar("ABC123");
//
//		assertEquals(carList.size(), 2);
//	}
//
//	@Test
//	public void testGetAvailableCars(String locationId){
//		Location location = new Location();
//		location.setLocationID(Integer.parseInt(locationId));
//		carService.addNewCar(new Car("Audi", 2024, "ABC123", location, null));
//		carService.addNewCar(new Car("BMW", 2024, "def123", location, null));
//		carService.getAvailableCars(locationId);
//
//		assertEquals(carService.getAvailableCars(locationId).size(), 2);
//	}
//
//	@Test
//	public void testGetOwnedCars(String userId){
//		User user = new User();
//		user.setUser_id(Integer.parseInt(userId));
//		carService.addNewCar(new Car("Audi", 2024, "ABC123", null, user));
//		carService.addNewCar(new Car("BMW", 2024, "def123", null, user));
//		carService.getOwnedCars(userId);
//
//		assertEquals(carService.getOwnedCars(userId).size(), 2);
//	}
}
