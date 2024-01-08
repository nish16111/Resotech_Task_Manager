package com.bean;



import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserBean {

	String uid;
	String fname;
	String lname;
	String uname;
	String pass;
	String remaining_task;
	String done_task;
	String all_task;
	UserBean(){
		
	}
}
