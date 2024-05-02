package org.example.carsharing_server.ReviewRating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface ReviewRatingRepository extends JpaRepository<ReviewRating, Integer>, JpaSpecificationExecutor<ReviewRating> {

}
