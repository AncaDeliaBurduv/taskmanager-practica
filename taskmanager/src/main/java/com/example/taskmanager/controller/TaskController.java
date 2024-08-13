package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "task-list";
    }

    @GetMapping("/details/{id}")
    public String getTaskDetails(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "task-details";
    }

    @GetMapping("/form")
    public String getTaskForm(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            Task task = taskService.getTaskById(id);
            model.addAttribute("task", task);
        } else {
            model.addAttribute("task", new Task());
        }
        return "task-form";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task) {
        if (task == null || task.getTitle() == null || task.getTitle().isEmpty()) {
            return "redirect:/tasks?error"; // Handle error case
        }
        taskService.createTask(task);
        return "redirect:/tasks";
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task) {
        task.setId(id);
        taskService.updateTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}
