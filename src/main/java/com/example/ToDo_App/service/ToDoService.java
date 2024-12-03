package com.example.ToDo_App.service;

import com.example.ToDo_App.model.ToDo;
import com.example.ToDo_App.repo.IToDoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoService {

    @Autowired
    private IToDoRepo repo;

    @Autowired
    private JavaMailSender mailSender;

    // Get all ToDo items
    public List<ToDo> getAllToDoItems() {
        ArrayList<ToDo> todoList = new ArrayList<>();
        repo.findAll().forEach(todo -> todoList.add(todo));
        return todoList;
    }

    // Get a ToDo item by its ID
    public ToDo getToDoItemById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // Update status to "Done" and send email notification
    public boolean updateStatus(Long id) {
        ToDo todo = getToDoItemById(id);
        if (todo == null) return false;

        todo.setStatus("Done");
        boolean isUpdated = saveOrUpdateToDoItem(todo);

        if (isUpdated) {
            sendCompletionEmail(todo);
        }

        return isUpdated;
    }

    // Save or update a ToDo item
    public boolean saveOrUpdateToDoItem(ToDo todo) {
        ToDo updatedObj = repo.save(todo);
        return repo.findById(updatedObj.getId()).isPresent();
    }

    // Delete a ToDo item
    public boolean deleteToDoItem(Long id) {
        repo.deleteById(id);
        return repo.findById(id).isEmpty();
    }

    // Get ToDo items filtered by status
    public List<ToDo> getToDoItemsByStatus(String status) {
        ArrayList<ToDo> filteredList = new ArrayList<>();
        if (status.equals("All")) {
            repo.findAll().forEach(filteredList::add);
        } else {
            repo.findByStatus(status).forEach(filteredList::add);
        }
        return filteredList;
    }

    // Send email notification for completed task
    private void sendCompletionEmail(ToDo todo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("*****@gmail.com"); // Use the recipient's email address here + dont
        // forget to change senders email + password in application.properties

        /* IMPORTANT
        1. Enable "2-Step Verification" in Google Account
            Go to your Google Account: https://myaccount.google.com/.
            Navigate to Security > 2-Step Verification and enable it.
        2. Generate an App Password
           Once 2-Step Verification is enabled:
           Go to Security > App Passwords in your Google account.
           Select the app as Mail and the device as Other (e.g., "Spring App").
           Copy the generated App Password.
           Replace your password in application.properties with this App Password:
         */
        message.setSubject("\uD83D\uDEA8 Task Completed \uD83D\uDEA8");
        message.setText(
                "The task '" + todo.getTitle() + "' has been marked as completed.\n\n" +
                        "Keep up the great work! 💪\n\n" +
                        "Best regards,\n" +
                        "Team TaskFlow"
        );
        mailSender.send(message);
    }
}
