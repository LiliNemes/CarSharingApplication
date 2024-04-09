package org.example.carsharing_server.Location;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import jakarta.persistence.*;
import org.example.carsharing_server.Car.Car;

import java.util.List;

/*class LocationDAO {
    private Connection connection;

    public LocationDAO() {
        try {
            connection = DriverManager.getConnection("", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
*/

@Entity
@Table
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int locationID;

    private int GPS_width;

    private int GPS_height;
    private String address;

    @OneToMany(mappedBy="location", cascade = CascadeType.ALL)
    private List<Car> cars_here;

    public Location(){

    }

    public Location(String address, int GPS_height, int GPS_width)
    {
        this.address = address;
        this.GPS_height = GPS_height;
        this.GPS_width = GPS_width;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID){
        this.locationID = locationID;
    }

    public int getGPS_width() {
        return GPS_width;
    }

    public void setGPS_width(int GPS_width) {
        this.GPS_width = GPS_width;
    }

    public int getGPS_height() {
        return GPS_height;
    }

    public void setGPS_height(int GPS_height) {
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
