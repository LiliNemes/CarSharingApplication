package org.example.carsharing_server.Booking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import jakarta.persistence.*;
import org.example.carsharing_server.Car.Car;
import org.example.carsharing_server.Location.Location;
import org.example.carsharing_server.Payment.Payment;
import org.example.carsharing_server.ReviewRating.ReviewRating;
import org.example.carsharing_server.User.User;

import java.sql.Date;

/*
class BookingDAO {
    private Connection connection;

    public BookingDAO() {
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
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int bookingId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "licensePlate", referencedColumnName = "licensePlate")
    private Car car;
    private Date start_time;
    private Date end_time;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pickUpLocationID", referencedColumnName = "locationID")
    private Location pickup_location;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dropOffLocationID", referencedColumnName = "locationID")
    private Location dropoff_location;
    @OneToOne
    private ReviewRating review;
    @OneToOne
    private Payment payment;
    private int total_cost;

    public Booking(Date start_time, int total_cost, Location dropoff_location, Location pickup_location, ReviewRating review, Payment payment, Car car, User user) {
        this.user = user;
        this.car = car;
        this.start_time = start_time;
        this.pickup_location = pickup_location;
        this.dropoff_location = dropoff_location;
        this.review = review;
        this.payment = payment;
        this.total_cost = total_cost;
    }

    public Booking() {

    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = userId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car carId) {
        this.car = carId;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Location getPickup_location() {
        return pickup_location;
    }

    public void setPickup_location(Location pickup_location) {
        this.pickup_location = pickup_location;
    }

    public Location getDropoff_location() {
        return dropoff_location;
    }

    public void setDropoff_location(Location dropoff_location) {
        this.dropoff_location = dropoff_location;
    }

    public int getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(int total_cost) {
        this.total_cost = total_cost;
    }

    public ReviewRating getReview() {
        return review;
    }

    public void setReview(ReviewRating review) {
        this.review = review;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
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

