package com.kukri.moviecatalog.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for summarizing a Rating.
 * <p>
 * This class is used to transfer summary data of a rating, including its ID and score.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingSummaryDTO {

    /**
     * The unique identifier of the rating.
     */
    private Long id;

    /**
     * The score of the rating (e.g., from 1 to 10).
     */
    private int score;
}
