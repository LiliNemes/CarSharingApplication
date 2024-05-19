package org.example.carsharing_server.CarMaintenance;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.example.carsharing_server.Car.Car;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table
public class CarMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int maintenanceID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "licensePlate", referencedColumnName = "licensePlate")
    private Car car;

    private LocalDateTime maintenanceDate;

    @NotBlank
    private String typeOfMaintenance;

    @NotBlank
    private String details;

    @Min(1)
    private int cost;

    public CarMaintenance(){

    }
    public CarMaintenance(LocalDateTime maintenanceDate, String typeOfMaintenance, String details, Car car, int cost) {
        this.car = car;
        this.maintenanceDate = maintenanceDate;
        this.typeOfMaintenance = typeOfMaintenance;
        this.details = details;
        this.cost = cost;
    }

    public int getMaintenanceID() {
        return maintenanceID;
    }

    public void setMaintenanceID(int maintenanceID){
        this.maintenanceID = maintenanceID;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car){
        this.car=car;
    }

    public LocalDateTime getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(LocalDateTime maintenanceDate){
        this.maintenanceDate=maintenanceDate;
    }

    public String getTypeOfMaintenance() {
        return  typeOfMaintenance;
    }

    public void setTypeOfMaintenance(String typeOfMaintenance){
        this.typeOfMaintenance = typeOfMaintenance;
    }

    public String getDetails(){
        return details;
    }

    public void setDetails(String details){
        this.details = details;
    }

    public int getCost(){
        return cost;
    }

    public void setCost(int cost){
        this.cost = cost;
    }

    @Override
    public String toString(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
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

