package org.example.carsharing_server.ReviewRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.carsharing_server.ReviewRating.ReviewRatingSpecification.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class ReviewRatingServiceImpl implements ReviewRatingService {

    private final ReviewRatingRepository reviewRatingRepository;

    @Autowired
    public ReviewRatingServiceImpl(ReviewRatingRepository reviewRatingRepository) {this.reviewRatingRepository = reviewRatingRepository;}

    @Override
    public List<ReviewRating> getReviewRatings() {return reviewRatingRepository.findAll();}

    @Override
    public void addNewReviewRating(ReviewRating reviewRating) {reviewRatingRepository.save(reviewRating);}

    @Override
    public List<ReviewRating> getUsersReviewRating(String userId) {
        int userIdNum = Integer.parseInt(userId);
        return reviewRatingRepository.findAll(usersReviewRating(userIdNum));
    }

    @Override
    public List<ReviewRating> getBookingsReviewRating(String bookingId) {
        int bookingIdNum = Integer.parseInt(bookingId);
        return reviewRatingRepository.findAll(bookingReviewRating(bookingIdNum));
    }

}
