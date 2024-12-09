package com.example.task_app.service;
import com.example.task_app.model.Task;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.Map;

public class TaskService {

    private List<Task> tasks = new ArrayList<>();
    private Long taskIdSequence = 1L; // Simulate auto-increment ID.

    public List<Task> getAllTasksForUser(Long userId) {
        return tasks.stream()
                .filter(task -> task.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    public Task createTask(Task task) {
        task.setId(taskIdSequence++);
        tasks.add(task);
        return task;
    }

    public boolean deleteTask(Long taskId, Long userId) {
        return tasks.removeIf(task -> task.getId().equals(taskId) && task.getUser().getId().equals(userId));
    }

    public Task updateTask(Long taskId, Task updatedTask, Long userId) {
        for (Task task : tasks) {
            if (task.getId().equals(taskId) && task.getUser().getId().equals(userId)) {
                task.setTitle(updatedTask.getTitle());
                task.setDescription(updatedTask.getDescription());
                task.setDueDate(updatedTask.getDueDate());
                task.setStatus(updatedTask.getStatus());
                task.setPriority(updatedTask.getPriority());
                return task;
            }
        }
        return null;
    }

    public List<Task> searchTasks(String keyword) {
        return tasks.stream()
                .filter(task -> task.getTitle().toLowerCase().contains(keyword.toLowerCase())
                        || task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Task> getAllTasks() {
        return tasks;
    }
}
