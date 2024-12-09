package com.example.task_app.controller;
import com.example.task_app.model.Task;
import com.example.task_app.repository.TaskRepository;
import com.example.task_app.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public class TaskController {

    private final TaskRepository taskRepository = new TaskRepository();

    public String addTask(String title, String description, String priority, Long userId) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setPriority(priority);
        task.setUser(new com.example.task_app.model.User());
        task.getUser().setId(userId);

        taskRepository.save(task);
        return "Task created successfully";
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public String deleteTask(Long taskId) {
        if (taskRepository.deleteById(taskId)) {
            return "Task deleted successfully";
        }
        return "Task not found";
    }

    public List<Task> getUserTasks(Long userId) {
        return taskRepository.findByUserId(userId);
    }
}
