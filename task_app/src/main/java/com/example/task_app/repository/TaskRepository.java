package com.example.task_app.repository;
import com.example.task_app.model.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepository {

    private final List<Task> tasks = new ArrayList<>();
    private Long taskIdSequence = 1L;

    public List<Task> findAll() {
        return tasks;
    }

    public Task save(Task task) {
        task.setId(taskIdSequence++);
        tasks.add(task);
        return task;
    }

    public Optional<Task> findById(Long id) {
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst();
    }

    public boolean deleteById(Long id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }

    public List<Task> findByUserId(Long userId) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getUser().getId().equals(userId)) {
                result.add(task);
            }
        }
        return result;
    }
}
