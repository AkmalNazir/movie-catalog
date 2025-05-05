package com.kukri.moviecatalog.controller;

import com.kukri.moviecatalog.model.Movie;
import com.kukri.moviecatalog.model.MovieDTO;
import com.kukri.moviecatalog.model.RatingSummaryDTO;
import com.kukri.moviecatalog.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    private List<MovieDTO> movies;

    @BeforeEach
    public void setUp() {
        // Create a RatingSummaryDTO object to set in the MovieDTO
        List<RatingSummaryDTO> ratingSummary1 = Arrays.asList(new RatingSummaryDTO(1L, 87), new RatingSummaryDTO(1L, 56));

        // Set up dummy MovieDTOs for testing
        MovieDTO movie1 = new MovieDTO();
        movie1.setId(1L);
        movie1.setTitle("Inception");
        movie1.setReleaseYear(2010);
        movie1.setDirectorName("Christopher Nolan");
        movie1.setRatings(ratingSummary1);

        List<RatingSummaryDTO> ratingSummary2 = Arrays.asList(new RatingSummaryDTO(1L, 60), new RatingSummaryDTO(1L, 93));

        MovieDTO movie2 = new MovieDTO();
        movie2.setId(2L);
        movie2.setTitle("Interstellar");
        movie2.setReleaseYear(2014);
        movie2.setDirectorName("Christopher Nolan");
        movie2.setRatings(ratingSummary2);

        movies = Arrays.asList(movie1, movie2);  // List of MovieDTOs with RatingSummaryDTO
    }

    @Test
    public void testGetAllMovies() throws Exception {
        // Mock the service method
        when(movieService.getAllMovies()).thenReturn(movies);

        // Perform the GET request and validate the response
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Inception"))
                .andExpect(jsonPath("$[1].title").value("Interstellar"))
                .andExpect(jsonPath("$[0].ratings[0].id").value(1))
                .andExpect(jsonPath("$[0].ratings[0].score").value(87))
                .andExpect(jsonPath("$[0].ratings[1].id").value(1))
                .andExpect(jsonPath("$[0].ratings[1].score").value(56))
                .andExpect(jsonPath("$[1].ratings[0].id").value(1))
                .andExpect(jsonPath("$[1].ratings[0].score").value(60))
                .andExpect(jsonPath("$[1].ratings[1].id").value(1))
                .andExpect(jsonPath("$[1].ratings[1].score").value(93));
    }

    @Test
    public void testSaveMovie() throws Exception {
        // Prepare test data
        Movie movieToSave = new Movie(null, "Dunkirk", 2017, null, null);
        Movie savedMovie = new Movie(3L, "Dunkirk", 2017, null, null);  // Mock saved Movie entity

        // Mock the saveMovie method to return saved Movie
        when(movieService.saveMovie(any(Movie.class))).thenReturn(savedMovie);

        // JSON content for the POST request (matching the expected Movie structure)
        String movieJson = "{ \"title\": \"Dunkirk\", \"releaseYear\": 2017, \"director\": { \"id\": 1, \"name\": \"Christopher Nolan\" }, \"ratings\": [] }";

        // Perform the POST request and validate the response
        mockMvc.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(movieJson))
                .andExpect(status().isOk())  // Expecting 200 OK
                .andExpect(jsonPath("$.id").value(3))  // Validate that movie ID is 3
                .andExpect(jsonPath("$.title").value("Dunkirk"))  // Validate the movie title
                .andExpect(jsonPath("$.releaseYear").value(2017));  // Validate release year
    }

    @Test
    public void testGetMoviesByDirector() throws Exception {
        // Mock the service method
        when(movieService.getMoviesByDirector("Christopher Nolan")).thenReturn(movies);

        // Perform the GET request for movies by director
        mockMvc.perform(get("/movies/search/by-director?name=Christopher Nolan"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))  // Two movies should be returned
                .andExpect(jsonPath("$[0].title").value("Inception"))
                .andExpect(jsonPath("$[1].title").value("Interstellar"))
                .andExpect(jsonPath("$[0].ratings[0].id").value(1))
                .andExpect(jsonPath("$[0].ratings[0].score").value(87))
                .andExpect(jsonPath("$[0].ratings[1].id").value(1))
                .andExpect(jsonPath("$[0].ratings[1].score").value(56))
                .andExpect(jsonPath("$[1].ratings[0].id").value(1))
                .andExpect(jsonPath("$[1].ratings[0].score").value(60))
                .andExpect(jsonPath("$[1].ratings[1].id").value(1))
                .andExpect(jsonPath("$[1].ratings[1].score").value(93));
    }

    @Test
    public void testGetMoviesByRating() throws Exception {
        // Mock the service method to return movies with rating above 4
        when(movieService.getMoviesByRatingThreshold(4)).thenReturn(movies);

        // Perform the GET request for movies by rating
        mockMvc.perform(get("/movies/search/by-rating?minScore=4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))  // Expecting 2 movies above rating threshold
                .andExpect(jsonPath("$[0].title").value("Inception"))
                .andExpect(jsonPath("$[1].title").value("Interstellar"))
                .andExpect(jsonPath("$[0].ratings[0].id").value(1))
                .andExpect(jsonPath("$[0].ratings[0].score").value(87))
                .andExpect(jsonPath("$[0].ratings[1].id").value(1))
                .andExpect(jsonPath("$[0].ratings[1].score").value(56))
                .andExpect(jsonPath("$[1].ratings[0].id").value(1))
                .andExpect(jsonPath("$[1].ratings[0].score").value(60))
                .andExpect(jsonPath("$[1].ratings[1].id").value(1))
                .andExpect(jsonPath("$[1].ratings[1].score").value(93));
    }

    @Test
    public void testGetMovieById() throws Exception {

        MovieDTO movie1 = movies.get(0);  // Fetch the first movie
        when(movieService.getMovieById(1L)).thenReturn(new Movie(1L, "Inception", 2010, null, null));

        // Perform the GET request for a specific movie by ID
        mockMvc.perform(get("/movies/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Inception"))
                .andExpect(jsonPath("$.releaseYear").value(2010));
    }

    @Test
    public void testDeleteMovie() throws Exception {
        // Perform the DELETE request
        mockMvc.perform(delete("/movies/{id}", 1L))
                .andExpect(status().isNoContent());  // Expecting 204 No Content response
    }
}
