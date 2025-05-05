package com.kukri.moviecatalog.controller;

import com.kukri.moviecatalog.model.Rating;
import com.kukri.moviecatalog.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.List;

/**
 * Controller for managing Ratings.
 * <p>
 * Provides endpoints for saving, updating, and deleting ratings for movies.
 * </p>
 */
@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    /**
     * Creates a new rating.
     * 
     * @param rating the rating to be saved.
     * @return the saved Rating.
     */
    @PostMapping
    public ResponseEntity<Rating> saveRating(@RequestBody Rating rating) {
        Rating savedRating = ratingService.saveRating(rating);
        return new ResponseEntity<>(savedRating, HttpStatus.CREATED);
    }

    /**
     * Updates an existing rating by its ID.
     * 
     * @param id the ID of the rating to update.
     * @param Rating the updated rating data.
     * @return the updated Rating.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable Long id, @RequestBody Rating Rating) {
        Rating updatedRating = ratingService.updateRating(id, Rating);
        return ResponseEntity.ok(updatedRating);
    }

    /**
     * Deletes a rating by its ID.
     * 
     * @param id the ID of the rating to delete.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
