package org.example.carsharing_server.ReviewRating;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.example.carsharing_server.Booking.Booking;
import org.example.carsharing_server.User.User;

import java.util.Date;


@Entity
@Table
public class ReviewRating {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int reviewID;

    @OneToOne(mappedBy = "review")
    @JoinColumn(name = "bookingID", referencedColumnName = "bookingID")
    @JsonBackReference
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User author;

    @Min(1)
    @Max(5)
    private int rating;

    @NotBlank
    private String comment;

    private Date date;

    public ReviewRating (int rating, String comment, User author, Booking booking) {
        this.rating = rating;
        this.comment = comment;
        this.date = new Date();
        this.author = author;
        this.booking = booking;
    }

    public ReviewRating() {

    }


    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewId) {
        this.reviewID = reviewId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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
