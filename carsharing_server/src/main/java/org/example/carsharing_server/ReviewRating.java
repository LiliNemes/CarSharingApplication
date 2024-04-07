package org.example.carsharing_server;
import java.sql.*;

class ReviewRatingDAO {
    private Connection connection;

    public ReviewRatingDAO() {
        try {
            connection = DriverManager.getConnection("", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

public class ReviewRating {
    private int reviewId;
    private Booking bookingId;
    private User userId;
    private int rating;
    private String comment;
    private Date date;

    public ReviewRating (int rating, String comment, Date date) {
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }


    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public Booking getBookingId() {
        return bookingId;
    }

    public void setBookingId(Booking bookingId) {
        this.bookingId = bookingId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
