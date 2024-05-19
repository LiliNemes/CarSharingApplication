package org.example.carsharing_server.ReviewRating;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public void addNewReviewRating(@Valid @RequestBody ReviewRating reviewRating, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            reviewRatingService.addNewReviewRating(reviewRating, userDetails);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<String> getUsersReviewRatings(@Valid @PathVariable @Min(1) Integer userId) {
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

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<String> getBookingsReviewRating(@Valid @PathVariable @Min(0) Integer bookingId) {
        Optional<ReviewRating> result = reviewRatingService.getBookingsReviewRating(bookingId);
        if (result.isPresent()){
            ObjectMapper objectMapper = new ObjectMapper();
            String res;
            try {
                res = objectMapper.writeValueAsString(result.get());
            } catch (JsonProcessingException e) {
                res = result.get().toString();
            }
            return ResponseEntity.ok(res);

        }
        return ResponseEntity.ok("");
    }

    @GetMapping("/car/{licensePlate}")
    public ResponseEntity<String> getCarsReviewRatings(@Valid @PathVariable String licensePlate) {
        List<ReviewRating> reviewRatings = reviewRatingService.getCarsReviewRating(licensePlate);
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
