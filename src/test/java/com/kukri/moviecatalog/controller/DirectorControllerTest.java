package com.kukri.moviecatalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kukri.moviecatalog.model.Director;
import com.kukri.moviecatalog.service.DirectorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DirectorController.class)
public class DirectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DirectorService directorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllDirectors() throws Exception {
        List<Director> directors = Arrays.asList(
                new Director(1L, "Christopher Nolan"),
                new Director(2L, "James Cameron")
        );

        when(directorService.getAllDirectors()).thenReturn(directors);

        mockMvc.perform(get("/directors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testSaveDirector() throws Exception {
        Director director = new Director(null, "Steven Spielberg");
        Director savedDirector = new Director(1L, "Steven Spielberg");

        when(directorService.saveDirector(any(Director.class))).thenReturn(savedDirector);

        mockMvc.perform(post("/directors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(director)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void testDeleteDirector() throws Exception {
        doNothing().when(directorService).deleteDirector(1L);

        mockMvc.perform(delete("/directors/1"))
                .andExpect(status().isOk());

        verify(directorService, times(1)).deleteDirector(1L);
    }
}
