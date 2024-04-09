package org.example.carsharing_server;
import java.sql.*;

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
public class Booking {
     private int bookingId;
     private User userId;
     private Car carId;
     private Date start_time;
     private Date end_time;
     private Location pickup_location;
     private Location dropoff_location;
     private int total_cost;

     public Booking(Date start_time, Date end_time, int total_cost) {
         this.start_time = start_time;
         this.end_time = end_time;
         this.total_cost = total_cost;
     }
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Car getCarId() {
        return carId;
    }

    public void setCarId(Car carId) {
        this.carId = carId;
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
}

