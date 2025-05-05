package com.kukri.moviecatalog.service;

import com.kukri.moviecatalog.model.Movie;
import com.kukri.moviecatalog.model.MovieDTO;
import com.kukri.moviecatalog.model.MovieMapper;
import com.kukri.moviecatalog.model.Director;
import com.kukri.moviecatalog.repository.MovieRepository;
import com.kukri.moviecatalog.repository.DirectorRepository;
import com.kukri.moviecatalog.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service class for handling business logic related to Movies.
 */
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;
    private final RatingRepository ratingRepository;
    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);
    @Autowired
    public MovieService(MovieRepository movieRepository, DirectorRepository directorRepository, RatingRepository ratingRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
        this.ratingRepository = ratingRepository;
    }

    /**
     * Retrieves all movies and maps them to DTOs.
     *
     * @return list of MovieDTOs
     */
    public List<MovieDTO> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
            .map(MovieMapper::toMovieDTO)
            .collect(Collectors.toList());
    }

    /**
     * Retrieves movies by director name.
     *
     * @param name the director's name
     * @return list of matching MovieDTOs
     */
    public List<MovieDTO> getMoviesByDirector(String name) {
        List<Movie> movies = movieRepository.findByDirectorNameContainingIgnoreCase(name);
        return movies.stream()
                .map(MovieMapper::toMovieDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves movies with average rating above a threshold.
     *
     * @param minScore the minimum rating score
     * @return list of MovieDTOs
     */
    public List<MovieDTO> getMoviesByRatingThreshold(int minScore) {
        List<Movie> movies = movieRepository.findMoviesWithRatingAbove(minScore);
        return movies.stream()
                .map(MovieMapper::toMovieDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a movie by its ID.
     *
     * @param id the movie ID
     * @return the Movie entity or null if not found
     */
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    /**
     * Saves a new movie.
     *
     * @param movie the movie to save
     * @return the saved Movie entity
     */
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    /**
     * Updates an existing movie.
     *
     * @param id the ID of the movie to update
     * @param updatedMovie the updated movie data
     * @return the updated Movie entity
     */

    public Movie updateMovie(Long id, Movie updatedMovie) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        existingMovie.setTitle(updatedMovie.getTitle());
        existingMovie.setReleaseYear(updatedMovie.getReleaseYear());

        // Update director if provided
        if (updatedMovie.getDirector() != null) {
            Director director = updatedMovie.getDirector();

            if (director.getId() != null) {
                directorRepository.findById(director.getId())
                        .orElseThrow(() -> new RuntimeException("Director not found"));
            }

            existingMovie.setDirector(director);
        }

        Movie savedMovie = movieRepository.save(existingMovie);

        // Save and attach ratings
        if (updatedMovie.getRatings() != null) {
            updatedMovie.getRatings().forEach(rating -> {
                rating.setMovie(savedMovie);
                ratingRepository.save(rating);
            });

            savedMovie.setRatings(updatedMovie.getRatings());
        }

        return movieRepository.save(savedMovie);
    }

    /**
     * Deletes a movie by its ID.
     *
     * @param id the movie ID
     */
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
