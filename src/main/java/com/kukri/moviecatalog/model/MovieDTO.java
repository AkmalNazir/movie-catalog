package com.kukri.moviecatalog.model;

import lombok.Data;
import java.util.List;

/**
 * Data Transfer Object (DTO) for Movie.
 * <p>
 * This class is used to transfer movie data without exposing the full entity structure.
 * </p>
 */
@Data
public class MovieDTO {

    /**
     * The unique identifier of the movie.
     */
    private Long id;

    /**
     * The title of the movie.
     */
    private String title;

    /**
     * The release year of the movie.
     */
    private int releaseYear;

    /**
     * The name of the movie's director.
     */
    private String directorName;

    /**
     * List of ratings associated with this movie.
     * Contains summary data of ratings (score).
     */
    private List<RatingSummaryDTO> ratings;

    public void setRatings(List<RatingSummaryDTO> ratings) {
        this.ratings = ratings;
    }

}