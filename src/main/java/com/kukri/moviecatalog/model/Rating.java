package com.kukri.moviecatalog.model;

import lombok.Data;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Entity class representing a Rating for a Movie.
 * <p>
 * A Rating has an ID, a score, and belongs to a Movie.
 * </p>
 */
@Entity
@Data
public class Rating {

    /**
     * The unique identifier for the rating.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The score of the rating (e.g., from 1 to 10).
     */
    private int score;

    /**
     * The movie that this rating is associated with.
     * A rating belongs to one movie.
     */
    @ManyToOne
    @JsonBackReference
    private Movie movie;
}
