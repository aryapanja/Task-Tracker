package com.task.tracker.controller;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.task.tracker.model.Tasks;
import com.task.tracker.service.TaskService;
import javax.validation.Valid;

@RestController
@RequestMapping("task-tracker")
public class TaskController implements ErrorResponseMessages {

    @Autowired
    private TaskService taskService;

    @GetMapping("tasks")
    public ResponseEntity<List<Tasks>> getAllTasks() {
        List<Tasks> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("tasks/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable String id) {
        Tasks task = taskService.getTaskById(id);
        
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TASK_NOT_FOUND);
        }
    }


    @PostMapping("tasks")
    public ResponseEntity<String> addTask(@Valid @RequestBody Tasks task, BindingResult result) {
        if (result.hasErrors()) {
            // Handle validation errors
            return ResponseEntity.badRequest().body("Invalid input data");
        }
        
        taskService.addTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(TASK_ADDED_SUCCESS);
    }

    @PutMapping("tasks/{id}")
    public ResponseEntity<String> updateTask(
            @PathVariable String id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Date duedate
    ) {
        // Data validation
        if ((title == null || title.isEmpty()) && (description == null || description.isEmpty()) && duedate == null) {
            return ResponseEntity.badRequest().body("No valid update data provided");
        }

        boolean updated = taskService.updateTask(id, title, description, duedate);
        if (updated) {
            return ResponseEntity.ok(TASK_UPDATED_SUCCESS);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TASK_NOT_FOUND);
        }
    }

    @DeleteMapping("tasks/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable String id) {
        boolean deleted = taskService.deleteTask(id);
        if (deleted) {
            return ResponseEntity.ok(TASK_DELETED_SUCCESS);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TASK_NOT_FOUND);
        }
    }
}
