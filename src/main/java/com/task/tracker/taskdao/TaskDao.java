package com.task.tracker.taskdao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.task.tracker.model.Tasks;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskDao extends JpaRepository<Tasks, String>{
	
	//function to return task by id
	Tasks findTaskById(String id);
}