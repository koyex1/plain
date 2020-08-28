package net.olu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.olu.models.Task;

@Repository("taskRepository")
public interface TaskRepository extends CrudRepository<Task, Integer>{
public Task findById(int id);

@Query(value="SELECT * from task t WHERE NOT name='admin'",nativeQuery=true)
public List<Task> findAllExceptOne();
}
