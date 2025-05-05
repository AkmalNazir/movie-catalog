package com.kukri.moviecatalog.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.kukri.moviecatalog.controller.RatingController;
import com.kukri.moviecatalog.service.RatingService;
import com.kukri.moviecatalog.model.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class RatingControllerTest {

    @Mock
    private RatingService ratingService;

    @InjectMocks
    private RatingController ratingController;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testSaveRating() {
        Rating rating = new Rating();
        rating.setId(1L);
        rating.setScore(79);

        when(ratingService.saveRating(any(Rating.class))).thenReturn(rating);

        ResponseEntity<Rating> response = ratingController.saveRating(rating);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(rating, response.getBody());
        verify(ratingService, times(1)).saveRating(rating);
    }

    @Test
    void testUpdateRating() {
        Long ratingId = 1L;
        Rating inputRating = new Rating();
        inputRating.setScore(90);

        Rating updatedRating = new Rating();
        updatedRating.setId(ratingId);
        updatedRating.setScore(67);

        when(ratingService.updateRating(eq(ratingId), any(Rating.class)))
            .thenReturn(updatedRating);

        ResponseEntity<Rating> response = ratingController.updateRating(ratingId, inputRating);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedRating, response.getBody());
        verify(ratingService, times(1)).updateRating(ratingId, inputRating);
    }

    @Test
    void testDeleteRating() {
        Long ratingId = 1L;

        ResponseEntity<Void> response = ratingController.deleteRating(ratingId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(ratingService, times(1)).deleteRating(ratingId);
    }
}
