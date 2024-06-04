package org.example.carsharing_server.Car;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    @Pattern(regexp = "[a-zA-Z]..[0-9]..")
    @NotBlank
    private String licensePlate;

    @NotBlank
    private String model;

    @Min(1672)
    private int releaseYear;

    @Min(1)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locationID", referencedColumnName = "locationID")
    private Location location;

    private boolean availabilityStatus=true;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CarMaintenance> maintenanceRecord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User owner;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Booking> bookings;


    public Car() {

    }

    public Car(String plate){
        this.licensePlate = plate;
    }

    public Car(String model, int releaseYear, String licensePlate, Location location, User owner, double price) {
        this.model = model;
        this.releaseYear = releaseYear;
        this.licensePlate = licensePlate;
        this.price = price;
        this.owner = owner;
        this.location = location;
        this.maintenanceRecord = new ArrayList<CarMaintenance>();
        this.availabilityStatus = true;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
