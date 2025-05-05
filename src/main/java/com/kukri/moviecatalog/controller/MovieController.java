package com.kukri.moviecatalog.controller;

import com.kukri.moviecatalog.model.Movie;
import com.kukri.moviecatalog.model.MovieDTO;
import com.kukri.moviecatalog.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * REST Controller for handling Movie-related endpoints.
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    /**
     * Saves a new movie.
     *
     * @param movie the movie to be saved
     * @return the saved movie
     */
    @PostMapping
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    /**
     * Retrieves all movies as DTOs.
     *
     * @return list of movie DTOs
     */
    @GetMapping
    public List<MovieDTO> getAllMovies() {
        return movieService.getAllMovies();
    }

    /**
     * Retrieves a movie by its ID.
     *
     * @param id the movie ID
     * @return the movie if found
     */
    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    /**
     * Retrieves movies by director name.
     *
     * @param name the director's name
     * @return list of movies by the specified director
     */
    @GetMapping("/search/by-director")
    public List<MovieDTO> getMoviesByDirector(@RequestParam String name) {
        return movieService.getMoviesByDirector(name);
    }

    /**
     * Retrieves movies with average rating above a given threshold.
     *
     * @param minScore the minimum rating score
     * @return list of qualifying movies
     */
    @GetMapping("/search/by-rating")
    public List<MovieDTO> getMoviesByRatingThreshold(@RequestParam int minScore) {
        return movieService.getMoviesByRatingThreshold(minScore);
    }

    /**
     * Updates an existing movie.
     *
     * @param id the movie ID
     * @param movie the updated movie data
     * @return updated movie entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        Movie updatedMovie = movieService.updateMovie(id, movie);
        return ResponseEntity.ok(updatedMovie);
    }

    /**
     * Deletes a movie by ID.
     *
     * @param id the ID of the movie to delete
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
