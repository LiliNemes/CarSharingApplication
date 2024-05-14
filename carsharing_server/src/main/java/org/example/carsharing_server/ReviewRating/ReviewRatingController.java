package org.example.carsharing_server.ReviewRating;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviewratings")
public class ReviewRatingController {

    private final ReviewRatingService reviewRatingService;

    public ReviewRatingController(ReviewRatingService reviewRatingService) {this.reviewRatingService = reviewRatingService;}

    @GetMapping
    public ResponseEntity<String> getReviewRatings() {
        List<ReviewRating> reviewRatings = reviewRatingService.getReviewRatings();
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(reviewRatings);
        } catch (JsonProcessingException e) {
            res = reviewRatings.toString();
        }
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public void addNewReviewRating(@RequestBody ReviewRating reviewRating) {
        try {
            reviewRatingService.addNewReviewRating(reviewRating);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<String> getUsersReviewRatings(@PathVariable String userId) {
        List<ReviewRating> reviewRatings = reviewRatingService.getUsersReviewRating(userId);
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(reviewRatings);
        } catch (JsonProcessingException e) {
            res = reviewRatings.toString();
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<String> getBookingsReviewRatings(@PathVariable String bookingId) {
        List<ReviewRating> reviewRatings = reviewRatingService.getBookingsReviewRating(bookingId);
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(reviewRatings);
        } catch (JsonProcessingException e) {
            res = reviewRatings.toString();
        }
        return ResponseEntity.ok(res);
    }
}
