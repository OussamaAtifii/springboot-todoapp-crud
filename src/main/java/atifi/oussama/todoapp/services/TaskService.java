package atifi.oussama.todoapp.services;

import atifi.oussama.todoapp.models.Task;
import atifi.oussama.todoapp.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;

    // Get all the tasks from the database using the findAll() method from the TaskRepository interface
    public ResponseEntity<Iterable<Task>> getAllTasks() {
        return ResponseEntity.ok(taskRepository.findAll());
    }

    // Create a task using the save() method from the TaskRepository interface
    public ResponseEntity<HashMap<String, Object>> createTask(Task task) {
        HashMap<String, Object> data = new HashMap<>();

        if (task.getTitle().isEmpty()) {
            data.put("created", false);
            data.put("response", "Error: Title cannot be empty");
            return ResponseEntity.badRequest().body(data);
        }

        if (task.getTitle().length() < 5) {
            data.put("created", false);
            data.put("response", "Error: Title must be at least 5 characters long");
            return ResponseEntity.badRequest().body(data);
        }

        taskRepository.save(task);
        data.put("created", true);
        data.put("Response", "Task successfully saved");

        return ResponseEntity.ok(data);
    }

    // Delete a task using the deleteById() method from the TaskRepository interface
    public ResponseEntity<HashMap<String, Object>> deleteTask(Integer id) {
        Optional<Task> task = taskRepository.findById(id);
        HashMap<String, Object> data = new HashMap<>();

        if (task.isEmpty()) {
            data.put("deleted", false);
            data.put("response", "Error: Task not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
        }

        taskRepository.deleteById(id);
        data.put("deleted", true);
        data.put("response", "Task successfully deleted");

        return ResponseEntity.ok(data);
    }

    // Update a task using the findByid() and save() methods from the TaskRepository interface
    // Get the task by id, update it and save it
    public ResponseEntity<HashMap<String, Object>> updateTask(Integer id, Task task) {
        Optional<Task> taskData = taskRepository.findById(id);
        HashMap<String, Object> data = new HashMap<>();

        if (taskData.isEmpty()) {
            data.put("updated", false);
            data.put("response", "Error: Task not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
        }

        if (task.getTitle().isEmpty()) {
            data.put("updated", false);
            data.put("response", "Error: Title cannot be empty");
            return ResponseEntity.badRequest().body(data);
        }

        if (task.getTitle().length() < 5) {
            data.put("updated", false);
            data.put("response", "Error: Title must be at least 5 characters long");
            return ResponseEntity.badRequest().body(data);
        }

        taskData.get().setTitle(task.getTitle());
        taskData.get().setDescription(task.getDescription());
        taskData.get().setCompleted(task.isCompleted());

        taskRepository.save(taskData.get());
        data.put("updated", true);
        data.put("response", "Task successfully updated");

        return ResponseEntity.ok(data);
    }
}
