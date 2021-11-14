package example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long taskId;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name of task cannot be empty")
    private String name;

    @Column(name = "priority")
    @NotBlank(message = "Priority cannot be empty")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @NotBlank(message = "The 'title' cannot be empty")
    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @NotBlank(message = "The 'owner_id' cannot be null")
    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

}
