package com.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//import com.bean.StudentBean;
import com.bean.UserBean;

@Repository
public class UserDao {
	@Autowired
	JdbcTemplate stmt;
	public void addUser(UserBean ub) {
		try {
			int rows = stmt.update("insert into user (fname, lname, uname, pass) values(?,?,?,?)",ub.getFname(),ub.getLname(),ub.getUname(),ub.getPass());
			System.out.println(rows);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void deleteTaskUser(String fname, int taskIdInt) {
		try {
			System.out.println("-------------------------------In delete Task");
		    int rows = stmt.update("UPDATE user SET ALL_TASK = JSON_SET(ALL_TASK, '$', (SELECT JSON_ARRAYAGG(element) FROM JSON_TABLE(ALL_TASK, '$[*]' COLUMNS (element INT PATH '$')) AS jt WHERE element != ?)) WHERE fname = ?", taskIdInt, fname);
		    System.out.println(rows);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
	}
	
	
	public UserBean checkPass(UserBean ub) {
		try {
			System.out.println(ub.getUname());
			System.out.println(ub.getPass());
			return stmt.queryForObject("Select * from user where UNAME=?", new BeanPropertyRowMapper<UserBean>(UserBean.class), new Object[] {ub.getUname()});
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ub;
	}
	
	
	public UserBean getfname(UserBean ub) {
		try {
			return stmt.queryForObject("Select * from user where UNAME=?", new BeanPropertyRowMapper<UserBean>(UserBean.class), new Object[] {ub.getUname()});

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ub;
	}
	
	
	public void addTask(int tid,String fname) {
		try {
			int rows = stmt.update("UPDATE user SET All_TASK = COALESCE(JSON_ARRAY_APPEND(All_TASK, '$', ?), JSON_ARRAY(?)) WHERE FNAME=?",tid,tid,fname);
			System.out.println(rows);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public List<Integer> parseIds(String all_task) {
		List<Integer> idList = new ArrayList<>();
		String[] idStrings = all_task.replaceAll("\\[|\\]", "").split(",");
		for (String idString : idStrings) {
            try {
                int id = Integer.parseInt(idString.trim());
                idList.add(id);
            } catch (NumberFormatException e) {
                // Handle invalid numbers if necessary
                e.printStackTrace();
            }
        }
		return idList;
	}
	
}
