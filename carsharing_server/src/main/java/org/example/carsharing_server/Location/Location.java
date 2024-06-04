package org.example.carsharing_server.Location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.example.carsharing_server.Booking.Booking;
import org.example.carsharing_server.Car.Car;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int locationID;

    private double GPS_width;

    private double GPS_height;

    @NotBlank
    private String address;

    @OneToMany(mappedBy="location", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Car> cars_here;

    @OneToMany(mappedBy="pickup_location", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Booking> started_here;

    @OneToMany(mappedBy="dropoff_location", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Booking> ended_here;

    public Location(){

    }

    public Location(String address, double GPS_height, double GPS_width)
    {
        this.address = address;
        this.GPS_height = GPS_height;
        this.GPS_width = GPS_width;
        ended_here = new ArrayList<>();
        started_here = new ArrayList<>();
        cars_here = new ArrayList<>();
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID){
        this.locationID = locationID;
    }

    public double getGPS_width() {
        return GPS_width;
    }

    public void setGPS_width(double GPS_width) {
        this.GPS_width = GPS_width;
    }

    public double getGPS_height() {
        return GPS_height;
    }

    public void setGPS_height(double GPS_height) {
        this.GPS_height = GPS_height;
    }

    public List<Car> getCars_here() {
        return cars_here;
    }

    public void setCars_here(List<Car> cars_here) {
        this.cars_here = cars_here;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public List<Booking> getStarted_here() {
        return started_here;
    }

    public void setStarted_here(List<Booking> started_here) {
        this.started_here = started_here;
    }

    public List<Booking> getEnded_here() {
        return ended_here;
    }

    public void setEnded_here(List<Booking> ended_here) {
        this.ended_here = ended_here;
    }

    @Override
    public String toString(){
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
