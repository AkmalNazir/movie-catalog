package com.kukri.moviecatalog.controller;

import com.kukri.moviecatalog.model.Director;
import com.kukri.moviecatalog.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.List;

/**
 * REST controller for handling operations related to Directors.
 * Provides endpoints for creating, retrieving, updating, and deleting director records.
 */
@RestController
@RequestMapping("/directors")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    /**
     * Creates a new director.
     *
     * @param director The director object to be saved.
     * @return The saved Director entity.
     */
    @PostMapping
    public Director saveDirector(@RequestBody Director director) {
        return directorService.saveDirector(director);
    }

    /**
     * Retrieves a director by ID.
     *
     * @param id The ID of the director to retrieve.
     * @return The Director entity if found.
     */
    @GetMapping("/{id}")
    public Director getDirector(@PathVariable Long id) {
        return directorService.getDirectorById(id);
    }

    /**
     * Retrieves all directors.
     *
     * @return A list of all Director entities.
     */
    @GetMapping
    public List<Director> getAllDirectors() {
        return directorService.getAllDirectors();
    }

    /**
     * Updates an existing director by ID.
     *
     * @param id The ID of the director to update.
     * @param director The updated director data.
     * @return A ResponseEntity containing the updated Director.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Director> updateDirector(@PathVariable Long id, @RequestBody Director director) {
        Director updatedDirector = directorService.updateDirector(id, director);
        return ResponseEntity.ok(updatedDirector);
    }

    /**
     * Deletes a director by ID.
     *
     * @param id The ID of the director to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteDirector(@PathVariable Long id) {
        directorService.deleteDirector(id);
    }
}