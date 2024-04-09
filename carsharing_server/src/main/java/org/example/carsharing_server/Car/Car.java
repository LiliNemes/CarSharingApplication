package org.example.carsharing_server.Car;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.example.carsharing_server.Booking.Booking;
import org.example.carsharing_server.CarMaintenance.CarMaintenance;
import org.example.carsharing_server.Location.Location;
import org.example.carsharing_server.User.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Car {

    @Id
    @Column(unique = true)
    @Pattern(regexp = "[a-zA-Z].. [0-9]..")
    private String licensePlate;
    private String model;
    private int releaseYear;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "locationID", referencedColumnName = "locationID")
    private Location location;
    private boolean availabilityStatus;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<CarMaintenance> maintenanceRecord;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User owner;


    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Booking> bookings;


    public Car() {

    }

    public Car(String model, int releaseYear, String licensePlate, Location location, User owner) {
        this.model = model;
        this.releaseYear = releaseYear;
        this.licensePlate = licensePlate;
        this.maintenanceRecord = new ArrayList<CarMaintenance>();
        this.location = location;
        this.availabilityStatus = true;
        this.owner = owner;
        this.bookings = new ArrayList<>();

    }


    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public List<CarMaintenance> getMaintenanceRecord() {
        return maintenanceRecord;
    }

    public void addMaintenance(CarMaintenance maintenance) {
        maintenanceRecord.add(maintenance);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public User getOwnerId() {
        return owner;
    }

    public void setOwnerId(User owner) {
        this.owner = owner;
    }


    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Hibernate6Module()
                .enable(Hibernate6Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS)
                .enable(Hibernate6Module.Feature.WRITE_MISSING_ENTITIES_AS_NULL));
        try {
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            throw new RuntimeException("Object not mappable");
        }
    }

}
/*
class CarDAO {
    private Connection connection;

    public CarDAO() {
        try {
            connection = DriverManager.getConnection("", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

  }
*/


