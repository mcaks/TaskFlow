package com.example.ToDo_App.service;

import com.example.ToDo_App.model.ToDo;
import com.example.ToDo_App.repo.IToDoRepo;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

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
        if ("Doing".equals(todo.getStatus())) {
            todo.setDoneTime(new Date()); // Record the time when the task is done
        } else if ("ToDo".equals(todo.getStatus())) {
            todo.setDoingStartTime(new Date()); // Record the time when the task starts "Doing"
        }

        todo.setStatus("Done");
        boolean isUpdated = saveOrUpdateToDoItem(todo);

        if (isUpdated) {
            // sendCompletionEmail(todo);
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

    // Calculate number of tasks by status
    public long getTaskCountByStatus(String status) {
        return repo.findByStatus(status).size();
    }

    // Calculate total number of tasks
    public long getTotalTaskCount() {
        return repo.count();
    }

    //reports
    @Column
    private Date doingStartTime;

    @Column
    private Date doneTime;
    public double calculateAverageDoingTime() {
        List<ToDo> doingTasks = repo.findByStatus("Doing");
        if (doingTasks.isEmpty()) return 0;

        return doingTasks.stream()
                .mapToLong(task -> Duration.between(task.getTime(), LocalTime.now()).toMinutes())
                .average()
                .orElse(0.0);
    }

    // Calculate percentage of "Done" tasks
    public double calculateDoneTaskPercentage() {
        long totalTasks = getTotalTaskCount();
        long doneTasks = getTaskCountByStatus("Done");

        if (totalTasks == 0) return 0;

        return (doneTasks * 100.0) / totalTasks;
    }
}

