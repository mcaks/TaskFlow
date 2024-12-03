package com.example.ToDo_App.controller;

import com.example.ToDo_App.service.ToDoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@WebMvcTest // Testira samo sloj kontrolera
public class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDoService toDoService;

    /**
     * Test za uspešno dohvatanje svih ToDo stavki
     */
    @Test
    public void testViewAllToDoItems() throws Exception {
        mockMvc.perform(get("/viewToDoList"))
                .andExpect(status().isOk());
    }

    /**
     * Test za negativni scenarij - Nepostojeći URL
     */
    @Test
    public void testViewAllToDoItems_NotFound() throws Exception {
        mockMvc.perform(get("/nonExistentPath"))
                .andExpect(status().isNotFound());
    }
}
