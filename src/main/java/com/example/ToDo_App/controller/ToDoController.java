package com.example.ToDo_App.controller;

import com.example.ToDo_App.model.ToDo;
import com.example.ToDo_App.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
public class ToDoController {

    @Autowired
    private ToDoService service;

    // View all ToDo items (filtered by status)
    @GetMapping({"/", "/viewToDoList"})
    public String viewAllToDoItems(@RequestParam(value = "status", required = false, defaultValue = "All") String status,
                                   Model model, @ModelAttribute("message") String message) {
        model.addAttribute("list", service.getToDoItemsByStatus(status));
        model.addAttribute("message", message);
        model.addAttribute("selectedStatus", status);
        return "ViewToDoList";
    }

    // Mark the task as complete (update status to "Done")
    @GetMapping("/updateToDoStatus/{id}")
    public String updateToDoStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (service.updateStatus(id)) {
            redirectAttributes.addFlashAttribute("message", "Task marked as complete and email sent.");
            return "redirect:/viewToDoList";
        }
        redirectAttributes.addFlashAttribute("message", "Error marking task as complete.");
        return "redirect:/viewToDoList";
    }

    // Add new ToDo item
    @GetMapping("/addToDoItem")
    public String addToDoItem(Model model) {
        model.addAttribute("todo", new ToDo());
        return "AddToDoItem";
    }

    // Save new ToDo item
    @PostMapping("/saveToDoItem")
    public String saveToDoItem(ToDo todo, RedirectAttributes redirectAttributes) {
        if (service.saveOrUpdateToDoItem(todo)) {
            redirectAttributes.addFlashAttribute("message", "Save Success");
            return "redirect:/viewToDoList";
        }
        redirectAttributes.addFlashAttribute("message", "Save Failure");
        return "redirect:/addToDoItem";
    }

    // Edit existing ToDo item
    @GetMapping("/editToDoItem/{id}")
    public String editToDoItem(@PathVariable Long id, Model model) {
        model.addAttribute("todo", service.getToDoItemById(id));
        return "EditToDoItem";
    }

    // Save edited ToDo item
    @PostMapping("/editSaveToDoItem")
    public String editSaveToDoItem(ToDo todo, RedirectAttributes redirectAttributes) {
        if (service.saveOrUpdateToDoItem(todo)) {
            redirectAttributes.addFlashAttribute("message", "Edit Success");
            return "redirect:/viewToDoList";
        }
        redirectAttributes.addFlashAttribute("message", "Edit Failure");
        return "redirect:/editToDoItem/" + todo.getId();
    }

    // Delete a ToDo item
    @GetMapping("/deleteToDoItem/{id}")
    public String deleteToDoItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (service.deleteToDoItem(id)) {
            redirectAttributes.addFlashAttribute("message", "Delete Success");
            return "redirect:/viewToDoList";
        }
        redirectAttributes.addFlashAttribute("message", "Delete Failure");
        return "redirect:/viewToDoList";
    }

    //reports
    @GetMapping("/reports")
    public String viewReports(Model model) {
        long todoCount = service.getTaskCountByStatus("ToDo");
        long doingCount = service.getTaskCountByStatus("Doing");
        long doneCount = service.getTaskCountByStatus("Done");
        long totalCount = service.getTotalTaskCount();


        model.addAttribute("todoCount", todoCount);
        model.addAttribute("doingCount", doingCount);
        model.addAttribute("doneCount", doneCount);
        model.addAttribute("totalCount", totalCount);


        return "Reports";
    }

}
