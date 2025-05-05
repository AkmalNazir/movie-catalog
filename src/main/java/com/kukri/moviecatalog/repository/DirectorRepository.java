package com.kukri.moviecatalog.repository;

import com.kukri.moviecatalog.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing {@link Director} entities.
 *
 * Extends {@link JpaRepository} to provide basic CRUD and pagination operations.
 */
@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {

}
