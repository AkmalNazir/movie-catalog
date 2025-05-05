package com.kukri.moviecatalog.repository;

import com.kukri.moviecatalog.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on Rating entities.
 * <p>
 * Extends JpaRepository to provide CRUD functionality for Rating entities.
 * </p>
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
}
