package com.example.ToDo_App.service;

import com.example.ToDo_App.repo.IToDoRepo;
import com.example.ToDo_App.model.ToDo;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class) // Aktivacija Mockito ekstenzije
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Uporaba iste instance razreda za vse teste
public class ToDoServiceTest {

    @Mock
    private IToDoRepo toDoRepo;

    @InjectMocks
    private ToDoService toDoService;

    /**
     * Test za uspešno čuvanje ToDo elementa
     */
    @Test
    public void testSaveToDoItem_Success() {
        ToDo todo = new ToDo();
        todo.setId(1L);
        todo.setTitle("Test Task");

        // Simuliraj obnašanje mock objekta
        when(toDoRepo.save(todo)).thenReturn(todo);
        when(toDoRepo.findById(1L)).thenReturn(Optional.of(todo));

        // Izvrši metodo
        boolean result = toDoService.saveOrUpdateToDoItem(todo);

        // Preveri rezultat
        assertTrue(result); // Pričakovana vrednost: true
        verify(toDoRepo, times(1)).save(todo); // Preveri, da je metoda bila klicana
    }


    /**
     * Test za neuspešno čuvanje ToDo stavke
     */
    @Test
    public void testSaveToDoItem_Failure() {
        ToDo todo = new ToDo();
        todo.setTitle("Test Task");

        // Simuliraj ponašanje mock objekta - vraća prazan rezultat
        when(toDoRepo.save(todo)).thenReturn(todo);
        when(toDoRepo.findById(null)).thenReturn(java.util.Optional.empty());

        // Izvrši metodu
        boolean result = toDoService.saveOrUpdateToDoItem(todo);

        // Proveri rezultat
        assertFalse(result); // Očekivana vrednost: false
        verify(toDoRepo, times(1)).save(todo); // Proveri da je metoda pozvana
    }

    /**
     * Test za uspešno pridobivanje ToDo elementa po ID-ju
     */
    @Test
    @Tag("Critical") // Označimo test kot kritičnega
    public void testGetToDoItemById_Success() {
        ToDo todo = new ToDo();
        todo.setId(1L);
        todo.setTitle("Test Task");

        // Simuliraj, da element obstaja
        when(toDoRepo.findById(1L)).thenReturn(Optional.of(todo));

        // Izvrši metodo
        ToDo result = toDoService.getToDoItemById(1L);

        // Preveri rezultat
        assertNotNull(result); // Pričakujemo, da rezultat ni null
        assertEquals("Test Task", result.getTitle()); // Preverimo naslov naloge
        assertEquals(1L, result.getId()); // Preverimo ID
        verify(toDoRepo, times(1)).findById(1L); // Preverimo, da je metoda bila klicana
    }

    /**
     * Test za neuspešno pridobivanje ToDo elementa po ID-ju
     */
    @Test
    @DisplayName("Test za neuspešno pridobivanje po ID-ju")
    public void testGetToDoItemById_Failure() {
        // Simuliraj, da element z ID-jem ne obstaja
        when(toDoRepo.findById(99L)).thenReturn(Optional.empty());

        // Izvrši metodo
        ToDo result = toDoService.getToDoItemById(99L);

        // Preveri rezultat
        assertNull(result); // Pričakovana vrednost: null
        verify(toDoRepo, times(1)).findById(99L); // Preverimo, da je metoda bila klicana
    }








    /**
     * Test za pridobitev nalog glede na status
     */
    @Test
    public void testGetToDoItemsByStatus_Success() {
        // Simuliraj nekaj nalog z različnimi statusi
        ToDo todo1 = new ToDo();
        todo1.setId(1L);
        todo1.setTitle("Task 1");
        todo1.setStatus("In Progress");

        ToDo todo2 = new ToDo();
        todo2.setId(2L);
        todo2.setTitle("Task 2");
        todo2.setStatus("Done");

        List<ToDo> todos = new ArrayList<>();
        todos.add(todo1);
        todos.add(todo2);

        // Simuliraj, da repo vrača naloge z določenim statusom
        when(toDoRepo.findByStatus("Done")).thenReturn(todos.subList(1, 2));

        // Izvrši metodo
        List<ToDo> result = toDoService.getToDoItemsByStatus("Done");

        // Preveri rezultat
        assertEquals(1, result.size()); // Pričakujemo en rezultat
        assertEquals("Task 2", result.get(0).getTitle()); // Preverimo naslov naloge
        assertEquals("Done", result.get(0).getStatus()); // Preverimo status
        verify(toDoRepo, times(1)).findByStatus("Done"); // Preverimo, da je bila metoda klicana
    }

    /**
     * Test za pridobitev nalog za status, ki ne obstaja
     */
    @Test
    public void testGetToDoItemsByStatus_NoItemsFound() {
        // Simuliraj prazno seznam nalog
        when(toDoRepo.findByStatus("NonExistingStatus")).thenReturn(new ArrayList<>());

        // Izvrši metodo
        List<ToDo> result = toDoService.getToDoItemsByStatus("NonExistingStatus");

        // Preveri rezultat
        assertTrue(result.isEmpty()); // Pričakujemo prazno listo, ker ni nalog s takim statusom
        verify(toDoRepo, times(1)).findByStatus("NonExistingStatus"); // Preverimo, da je bila metoda klicana
    }

    @BeforeAll // Anotacija za inicializacijo pred vsemi testi
    void setup() {
        System.out.println("Inicializacija testov za ToDoService...");
    }

    @AfterAll // Anotacija za izvajanje po vseh testih
    void teardown() {
        System.out.println("Vsi testi za ToDoService so končani.");
    }

}
