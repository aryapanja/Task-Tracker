package com.task.tracker.service;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.tracker.model.Tasks;
import com.task.tracker.taskdao.TaskDao;

@Service
public class TaskService {
	
	@Autowired
	private TaskDao taskDao;
	
	//function to return all the tasks available in the dataset
	public List<Tasks> getAllTasks() {
		// TODO Auto-generated method stub
		return taskDao.findAll();
	}
	
	//function to get task by id
	public Tasks getTaskById(String id) {
		return taskDao.findTaskById(id);
	}
	
	@Transactional
	public boolean updateTask(String id, String title, String description, Date duedate) {
	    Tasks task = taskDao.findById(id).orElse(null);

	    if (task == null) {
	        return false; // Task not found, no update happened
	    }

	    boolean updated = false;

	    if (title != null && title.length() > 0 && !Objects.equals(task.getTitle(), title)) {
	        task.setTitle(title);
	        updated = true;
	    }
	    if (description != null && description.length() > 0 && !Objects.equals(task.getDescription(), description)) {
	        task.setDescription(description);
	        updated = true;
	    }
	    if (duedate != null && !Objects.equals(task.getDuedate(), duedate)) {
	        task.setDuedate((java.sql.Date) duedate);
	        updated = true;
	    }

	    if (updated) {
	        taskDao.save(task); // Save the updated task
	    }

	    return updated;
	}


	public Tasks addTask(Tasks task) {// TODO Auto-generated method stub
		return taskDao.save(task);
	}

	public boolean deleteTask(String id) {
	    boolean exists = taskDao.existsById(id);
	    if (!exists) {
	        return false; // Task does not exist, no deletion happened
	    }
	    
	    taskDao.deleteById(id);
	    return true; // Deletion successful
	}

}
