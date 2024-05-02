package org.example.carsharing_server.ReviewRating;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
interface ReviewRatingService {
    List<ReviewRating> getReviewRatings();

    void addNewReviewRating(ReviewRating reviewRating);

    List<ReviewRating> getUsersReviewRating(String userId);

    List<ReviewRating> getBookingsReviewRating(String bookingId);
}
