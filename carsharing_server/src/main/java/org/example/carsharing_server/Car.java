import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class CarDAO {
    private Connection connection;

    public CarDAO() {
        try {
            connection = DriverManager.getConnection("", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/*
    public void addCar(Car car) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cars (model, release_year, location, availability_status, license_plate, maintenance_record) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setInt(2, car.getReleaseYear());
            preparedStatement.setString(3, car.getLocation());
            preparedStatement.setString(4, car.getAvailabilityStatus());
            preparedStatement.setString(5, car.getLicensePlate());
            preparedStatement.setString(6, car.getMaintenanceRecord());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Car getCarById(int carId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cars WHERE car_id=?");
            preparedStatement.setInt(1, carId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String model = resultSet.getString("model");
                int releaseYear = resultSet.getInt("release_year");
                String location = resultSet.getString("location");
                String availabilityStatus = resultSet.getString("availability_status");
                String licensePlate = resultSet.getString("license_plate");
                String maintenanceRecord = resultSet.getString("maintenance_record");
                return new Car(carId, model, releaseYear, location, availabilityStatus, licensePlate, maintenanceRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cars");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int carId = resultSet.getInt("car_id");
                String model = resultSet.getString("model");
                int releaseYear = resultSet.getInt("release_year");
                Location location = resultSet.getString("location");
                boolean availabilityStatus = resultSet.getString("availability_status");
                String licensePlate = resultSet.getString("license_plate");
                CarMaintenance maintenanceRecord = resultSet.getString("maintenance_record");
                cars.add(new Car(carId, model, releaseYear, location, availabilityStatus, licensePlate, maintenanceRecord));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }
 */

}
public class Car {
    private int carId;
    private String model;
    private int releaseYear;
    private Location location;
    private boolean availabilityStatus;
    private String licensePlate;
    private CarMaintenance maintenanceRecord;

    public Car(String model, int releaseYear,  String licensePlate) {
        this.model = model;
        this.releaseYear = releaseYear;
        this.licensePlate = licensePlate;
    }

    public int getCarId(){
        return carId;
    }

    public String getModel(){
        return model;
    }
    public int getReleaseYear()
    {
        return releaseYear;
    }

    public Location getLocation(){
        return location;
    }

    public boolean getAvailabilityStatus(){
        return availabilityStatus;
    }

    public String getLicensePlate(){
        return licensePlate;
    }

    public CarMaintenance getMaintenanceRecord(){
        return maintenanceRecord;
    }
}
