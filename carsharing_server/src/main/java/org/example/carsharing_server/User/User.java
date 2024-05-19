package org.example.carsharing_server.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.example.carsharing_server.Booking.Booking;
import org.example.carsharing_server.Car.Car;
import org.example.carsharing_server.Payment.Payment;
import org.example.carsharing_server.ReviewRating.ReviewRating;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Min(1)
    private int userID;

    @Email
    private String email;

    @JsonIgnore
    @NotBlank
    @Size(min = 8)
    private String password;

    @Pattern(regexp = "^(OWNER|RENTER)$")
    private String userRole;

    private int balance =0;

    @OneToMany(mappedBy="payer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Payment> payments;

    @OneToMany(mappedBy="author", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ReviewRating> reviews;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Booking> bookings;

    @OneToMany(mappedBy="owner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Car> cars;

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.userRole = role;
        this.payments = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.cars = new ArrayList<>();
    }

    public User() {}

    public User(int userID){
        this.userID = userID;
    }

    public int getUser_id() {
        return userID;
    }

    public void setUser_id(int user_id) {
        this.userID = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return userRole;
    }

    public void setRole(String role) {
        this.userRole = role;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<ReviewRating> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewRating> reviews) {
        this.reviews = reviews;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
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
