package com.kukri.moviecatalog.model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class to convert Movie entities to MovieDTO objects.
 */
public class MovieMapper {

    /**
     * Converts a Movie entity to a MovieDTO.
     *
     * @param movie the Movie entity
     * @return the corresponding MovieDTO
     */
    public static MovieDTO toMovieDTO(Movie movie) {
        if (movie == null) return null;

        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setReleaseYear(movie.getReleaseYear());

        if (movie.getDirector() != null) {
            dto.setDirectorName(movie.getDirector().getName());
        }

        if (movie.getRatings() != null) {
            List<RatingSummaryDTO> ratingSummaries = movie.getRatings().stream().map(rating -> {
                RatingSummaryDTO summary = new RatingSummaryDTO();
                summary.setId(rating.getId());
                summary.setScore(rating.getScore());
                return summary;
            }).collect(Collectors.toList());

            dto.setRatings(ratingSummaries);
        }

        return dto;
    }
}
