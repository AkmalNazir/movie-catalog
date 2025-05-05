package com.kukri.moviecatalog.repository;

import com.kukri.moviecatalog.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Movie entities.
 * Extends JpaRepository to provide basic CRUD operations and custom queries.
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    /**
     * Finds all movies whose director's name contains the given string, ignoring case.
     *
     * @param directorName the director name to search for
     * @return list of matching movies
     */
    List<Movie> findByDirectorNameContainingIgnoreCase(String directorName);

    /**
     * Finds movies with at least one rating above the given score.
     *
     * @param score the minimum score threshold
     * @return list of movies with high ratings
     */
    @Query("SELECT DISTINCT m FROM Movie m JOIN m.ratings r WHERE r.score > :score")
    List<Movie> findMoviesWithRatingAbove(@Param("score") int score);
}
