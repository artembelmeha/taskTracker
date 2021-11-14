package example.repository;

import example.model.Task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "select * from tasks where owner_id = ?1", nativeQuery = true)
    List<Task> getAllTaskByUserId(long ownerId);
}
