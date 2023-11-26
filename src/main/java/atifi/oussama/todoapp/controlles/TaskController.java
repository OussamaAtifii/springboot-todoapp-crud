package atifi.oussama.todoapp.controlles;

import atifi.oussama.todoapp.models.Task;
import atifi.oussama.todoapp.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class TaskController {
    private TaskService taskService;

    // Get endpoint to get all tasks
    @GetMapping("/all")
    public ResponseEntity<Iterable<Task>> getTasks() {
        return taskService.getAllTasks();
    }

    // Post endpoint to add a task
    @PostMapping("/add")
    public ResponseEntity<HashMap<String, Object>> addTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    // Delete endpoint to delete a task
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HashMap<String, Object>> deleteTask(@PathVariable Integer id) {
        return taskService.deleteTask(id);
    }

    // Put endpoint to update a task
    @PutMapping("/update/{id}")
    public ResponseEntity<HashMap<String, Object>> updateTask(@PathVariable Integer id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }
}
