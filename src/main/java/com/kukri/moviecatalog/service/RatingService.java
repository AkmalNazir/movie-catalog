package com.kukri.moviecatalog.service;

import com.kukri.moviecatalog.model.Rating;
import com.kukri.moviecatalog.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing Rating entities.
 * <p>
 * Contains the business logic for saving, updating, and deleting ratings.
 * </p>
 */
@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    /**
     * Saves a new rating.
     *
     * @param rating the rating to be saved.
     * @return the saved Rating.
     */
    public Rating saveRating(@RequestBody Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * Updates an existing rating by its ID.
     *
     * @param id the ID of the rating to update.
     * @param newRatingData the updated rating data.
     * @return the updated Rating.
     */
    public Rating updateRating(Long id, Rating newRatingData) {
        Rating existingRating = ratingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rating not found"));

        existingRating.setScore(newRatingData.getScore());

        return ratingRepository.save(existingRating);
    }

    /**
     * Deletes a rating by its ID.
     *
     * @param id the ID of the rating to delete.
     */
    public void deleteRating(@PathVariable Long id) {
        ratingRepository.deleteById(id);
    }
}
