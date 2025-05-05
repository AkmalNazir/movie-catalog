package com.kukri.moviecatalog.service;

import com.kukri.moviecatalog.model.Director;
import com.kukri.moviecatalog.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for handling business logic related to {@link Director} entities.
 * Acts as an intermediary between the controller and repository.
 */
@Service
public class DirectorService {

    private final DirectorRepository directorRepository;

    /**
     * Constructor for injecting {@link DirectorRepository}.
     *
     * @param directorRepository the repository used for director operations
     */
    @Autowired
    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    /**
     * Retrieves all directors from the database.
     *
     * @return a list of all directors
     */
    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    /**
     * Retrieves a director by its ID.
     *
     * @param id the ID of the director
     * @return the director if found, otherwise null
     */
    public Director getDirectorById(Long id) {
        return directorRepository.findById(id).orElse(null);
    }

    /**
     * Saves a new director to the database.
     *
     * @param director the director to be saved
     * @return the saved director
     */
    public Director saveDirector(Director director) {
        return directorRepository.save(director);
    }

    /**
     * Updates an existing director.
     *
     * @param id the ID of the director to update
     * @param updatedDirector the new director data
     * @return the updated director
     */
    public Director updateDirector(Long id, Director updatedDirector) {
        Director existing = directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Director not found"));
        existing.setName(updatedDirector.getName());
        return directorRepository.save(existing);
    }

    /**
     * Deletes a director by ID.
     *
     * @param id the ID of the director to delete
     */
    public void deleteDirector(Long id) {
        directorRepository.deleteById(id);
    }
}