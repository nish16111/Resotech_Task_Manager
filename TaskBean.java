package com.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskBean {
	Integer tid;
	String task_name;
	String task_description;
	Boolean isComplete;
	TaskBean(){
		
	}
}