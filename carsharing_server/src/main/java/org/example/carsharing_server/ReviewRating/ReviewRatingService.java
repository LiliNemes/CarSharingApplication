package org.example.carsharing_server.ReviewRating;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ReviewRatingService {
    List<ReviewRating> getReviewRatings();

    void addNewReviewRating(ReviewRating reviewRating, UserDetails userDetails);

    List<ReviewRating> getUsersReviewRating(int userId);

    Optional<ReviewRating> getBookingsReviewRating(int bookingId);

    Optional<ReviewRating> getReviewRating(int reviewRatingId);

    List<ReviewRating> getCarsReviewRating(String licensePlate);
}
