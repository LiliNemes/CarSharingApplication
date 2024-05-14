package org.example.carsharing_server.ReviewRating;

import jakarta.persistence.criteria.Join;
import org.example.carsharing_server.User.User;
import org.example.carsharing_server.Booking.Booking;
import org.springframework.data.jpa.domain.Specification;

import java.awt.print.Book;

public class ReviewRatingSpecification {
    static Specification<ReviewRating> bookingReviewRating(int bookingID) {
        return (root, query, builder) -> {
            Join<Booking, ReviewRating> bookingReviewRating = root.join("bookings");
            return builder.equal(bookingReviewRating.get("bookingID"), bookingID);
        };
    }

    static Specification<ReviewRating> usersReviewRating(int userID) {
        return (root, query, builder) -> {
            Join<User, ReviewRating> usersReviewRating = root.join("users");
            return builder.equal(usersReviewRating.get("usersID"), userID);
        };
    }
}
