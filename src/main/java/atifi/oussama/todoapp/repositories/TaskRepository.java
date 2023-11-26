package atifi.oussama.todoapp.repositories;

import atifi.oussama.todoapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    // TaskRepository extends JpaRepository, which is a Spring Data interface
    // for generic CRUD operations on a repository of a specific type.
}
