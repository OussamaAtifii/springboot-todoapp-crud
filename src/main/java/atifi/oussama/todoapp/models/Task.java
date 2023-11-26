package atifi.oussama.todoapp.models;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {
    // The @Id annotation is used to specify the primary key of an entity
    // The @GeneratedValue annotation is used to specify how the primary key should be generated
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    // The @Column annotation is used to specify the details of the column to which a field or property will be mapped
    @Column(columnDefinition = "boolean default false")
    private boolean completed;
}
