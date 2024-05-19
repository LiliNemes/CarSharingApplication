package org.example.carsharing_server.ReviewRating;

import org.example.carsharing_server.Booking.Booking;
import org.example.carsharing_server.Booking.BookingService;
import org.example.carsharing_server.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.carsharing_server.ReviewRating.ReviewRatingSpecification.bookingReviewRating;
import static org.example.carsharing_server.ReviewRating.ReviewRatingSpecification.usersReviewRating;

@Service
public class ReviewRatingServiceImpl implements ReviewRatingService {

    private final ReviewRatingRepository reviewRatingRepository;
    private final UserService userService;
    private final BookingService bookingService;

    @Autowired
    public ReviewRatingServiceImpl(ReviewRatingRepository reviewRatingRepository, UserService userService, BookingService bookingService) {this.reviewRatingRepository = reviewRatingRepository;
        this.userService = userService;
        this.bookingService = bookingService;
    }

    @Override
    public List<ReviewRating> getReviewRatings() {return reviewRatingRepository.findAll();}

    @Override
    public void addNewReviewRating(ReviewRating reviewRating, UserDetails userDetails) {
        if (!userService.getUser(reviewRating.getAuthor().getUser_id()).getEmail().equals(userDetails.getUsername())) {
            throw new IllegalArgumentException("User is trying to access other's review ratings!");
        }
        if (reviewRating.getBooking() != null) {
            try {
                reviewRating.setBooking(bookingService.getBooking(reviewRating.getBooking().getBookingId()));
            } catch (Exception e) {
                throw new IllegalArgumentException("Booking does not exist");
            }
        }
        else {
            throw new IllegalArgumentException("Booking is required");
        }
        if (reviewRating.getBooking().getReview() == null){
            reviewRatingRepository.save(reviewRating);
            bookingService.addBookingReview(reviewRating.getBooking(), reviewRating, userDetails);
        }
    }

    @Override
    public List<ReviewRating> getUsersReviewRating(int userId) {
        return reviewRatingRepository.findAll(usersReviewRating(userId));
    }

    @Override
    public Optional<ReviewRating> getBookingsReviewRating(int bookingId) {
        return reviewRatingRepository.findOne(bookingReviewRating(bookingId));
    }

    @Override
    public Optional<ReviewRating> getReviewRating(int reviewRatingId) {
        return reviewRatingRepository.findById(reviewRatingId);
    }

    @Override
    public List<ReviewRating> getCarsReviewRating(String licensePlate) {
        List<Booking> bookings = bookingService.getBookingsByCar(licensePlate);
        ArrayList<ReviewRating> reviewRatings = new ArrayList<>();
        for (Booking booking : bookings) {
            Optional<ReviewRating> result = reviewRatingRepository.findOne(bookingReviewRating(booking.getBookingId()));
            result.ifPresent(reviewRatings::add);
        }
        return reviewRatings;
    }
}
