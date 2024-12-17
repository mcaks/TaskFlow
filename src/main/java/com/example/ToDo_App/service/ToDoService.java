package com.example.ToDo_App.service;

import com.example.ToDo_App.model.ToDo;
import com.example.ToDo_App.repo.IToDoRepo;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    // Update status to "Done" and handle repeatable tasks
    public boolean updateStatus(Long id) {
        ToDo originalTask = getToDoItemById(id);
        if (originalTask == null) return false;

        if ("Doing".equals(originalTask.getStatus())) {
            originalTask.setDoneTime(new Date());
        }
        originalTask.setStatus("Done");
        repo.save(originalTask); // Save the completed task

        // Process repeatable task: Create a new task if needed
        if (originalTask.getRepeatFrequency() != null && !"/".equals(originalTask.getRepeatFrequency())) {
            createNewRepeatableTask(originalTask);
        }

        return true;
    }


    // Create a new task with updated due date for repeatable tasks
    private void createNewRepeatableTask(ToDo originalTask) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(originalTask.getNextDueDate() != null ? originalTask.getNextDueDate() : originalTask.getDate());

        // Update the next due date based on repeat frequency
        switch (originalTask.getRepeatFrequency()) {
            case "Daily":
                calendar.add(Calendar.DATE, 1);
                break;
            case "Weekly":
                calendar.add(Calendar.DATE, 7);
                break;
            case "Monthly":
                calendar.add(Calendar.MONTH, 1);
                break;
            case "Yearly":
                calendar.add(Calendar.YEAR, 1);
                break;
        }

        // Create a new task instance
        ToDo newTask = new ToDo();
        newTask.setTitle(originalTask.getTitle());
        newTask.setDate(calendar.getTime());
        newTask.setTime(originalTask.getTime());
        newTask.setStatus("ToDo");
        newTask.setRepeatFrequency(originalTask.getRepeatFrequency());
        newTask.setNextDueDate(calendar.getTime());

        repo.save(newTask);
    }

    // Fetch repeatable tasks
    public List<ToDo> getRepeatableTasks(String status) {
        List<ToDo> tasks = getToDoItemsByStatus(status); // Fetch tasks by status first

        // Filter repeatable tasks
        return tasks.stream()
                .filter(task -> task.getRepeatFrequency() != null &&
                        (task.getRepeatFrequency().equalsIgnoreCase("Daily") ||
                                task.getRepeatFrequency().equalsIgnoreCase("Weekly") ||
                                task.getRepeatFrequency().equalsIgnoreCase("Monthly") ||
                                task.getRepeatFrequency().equalsIgnoreCase("Yearly")))
                .collect(Collectors.toList());
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
        return status.equals("All") ? repo.findAll() : repo.findByStatus(status);
    }

    // Send email notification for completed task
    private void sendCompletionEmail(ToDo todo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("recipient@example.com");
        message.setSubject("Task Completed 🎉");
        message.setText("The task '" + todo.getTitle() + "' has been marked as completed. Keep it up!");
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














