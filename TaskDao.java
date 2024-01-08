package com.dao;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.TaskBean;
//import com.bean.UserBean;
//import com.dao.UserDao;

@Repository
public class TaskDao {

	
	@Autowired
	JdbcTemplate stmt;
	
	@Autowired
	UserDao ud;
	
	
	public void addTask(TaskBean tb,String fname) {	
		try {
				int rows = stmt.update("insert into Tasks (task_name, task_description) values(?,?)",tb.getTask_name(),tb.getTask_description());
				System.out.println(rows);
				TaskBean tb1 = stmt.queryForObject("Select * from Tasks where Task_name=?", new BeanPropertyRowMapper<TaskBean>(TaskBean.class), new Object[] {tb.getTask_name()});
				ud.addTask(tb1.getTid(),fname)
; 
				
			}
		catch(Exception e) {
				e.printStackTrace();
			}
		}
	public List<TaskBean> getTasksByIds(List<Integer> taskIds) {
		List<TaskBean> tasks = new ArrayList<>();
		System.out.println("In task dao");
		for (Integer num : taskIds) {
			tasks.add(stmt.queryForObject("Select * from Tasks where tid=?", new BeanPropertyRowMapper<TaskBean>(TaskBean.class), new Object[] {num}));
		}
		System.out.println(" In TaskDao: " + tasks);
		return tasks;
    }
	public void updateTask(int val,int taskIdInt) {
		try {			
			int rows = stmt.update("update tasks set isComplete=? where tid = ?",val,taskIdInt);
			System.out.println(rows);
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	public void deleteTask(int taskIdInt) {
		try {
		    int rows = stmt.update("DELETE FROM tasks WHERE tid = ?", taskIdInt);
		    System.out.println(rows);
		} catch (Exception e) {
		    e.printStackTrace();
		}

		
	}
}
		
