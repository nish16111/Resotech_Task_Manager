package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.TaskBean;
import com.bean.UserBean;
import com.dao.TaskDao;
//import com.bean.UserBean;
import com.dao.UserDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class User_controller {
	
	@Autowired 
	UserDao ud;
	
	@Autowired
	TaskDao td;
	
	@Autowired
	private HttpServletRequest request;
	@GetMapping("/login")
	public String login() {
		return "User_Login";
	}
	
	
	@GetMapping("/addtask")
	public String addtask() {
		return "Add_task";
	}	
	
	
	@GetMapping("/")
	public String homePage() {
		return "homePage";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "User_Signup";
	}


	@PostMapping("/changeTaskStatus")
    @ResponseBody
    public ResponseEntity<String> changeTaskStatus(@RequestParam("taskId") String taskId) {
        // Your logic to handle the taskId
        // For example, update the task status in the database
		HttpSession session = request.getSession();
		int taskIdInt = Integer.parseInt(taskId);
		int curr_val = (int) session.getAttribute("toggleValue");
        // Toggle between 0 and 1
        int val = curr_val == 0 ? 1 : 0;

		
		System.out.println("Val " + val);
		session.setAttribute("toggleValue", val);
		td.updateTask(val,taskIdInt);
        // Return a response if needed
        return ResponseEntity.ok("Task ID received: " + taskId);
    }
	
	
	@PostMapping("/deleteTask")
	@ResponseBody
    public ResponseEntity<String> deleteTask(@RequestParam("taskId") String taskId) {
        // Your logic to handle the taskId
        // For example, update the task status in the database
		HttpSession session = request.getSession();
		String fname = (String)session.getAttribute("fname");
		int taskIdInt = Integer.parseInt(taskId);
//		int curr_val = (int) session.getAttribute("toggleValue");
        // Toggle between 0 and 1
//        int val = curr_val == 0 ? 1 : 0;

		
//		System.out.println("Val " + val);
//		session.setAttribute("toggleValue", val);
		td.deleteTask(taskIdInt);
		ud.deleteTaskUser(fname,taskIdInt);
        // Return a response if needed
        return ResponseEntity.ok("Task ID received: " + taskId);
    }
	
	@PostMapping("/addtask")
	public String add_task(TaskBean tb) {
		System.out.println("In add task ");
		HttpSession session = request.getSession();
		String fname = (String)session.getAttribute("fname");
		System.out.println("In add task " + fname);
		td.addTask(tb,fname);
		return "Alltask";
	}	
	
	@PostMapping("/signup")
	public String signup(UserBean ub) {
		ub.setPass(BCrypt.hashpw(ub.getPass(), BCrypt.gensalt()));
		ud.addUser(ub);
		HttpSession session = request.getSession();
		session.setAttribute("fname",ub.getFname());
		return "homePage";
	}
	
	
	@PostMapping("/login")
	public String login(UserBean ub,Model model) {
		UserBean b = ud.checkPass(ub);
		System.out.println("In controller");
		if(BCrypt.checkpw(ub.getPass(),b.getPass())) {
			b = ud.getfname(ub);
			System.out.println("b.getFname(): " + b.getFname());
			HttpSession session = request.getSession();
			session.setAttribute("fname",b.getFname());
			
			
			System.out.println("In controller");
			List <TaskBean> tasks = null;
			if (b.getAll_task() !=null) {
				
				List<Integer> idList = ud.parseIds(b.getAll_task());
				tasks = td.getTasksByIds(idList);
				System.out.println("ID lisT: " + idList);
			}
			else {
				tasks = null;
			}
			session.setAttribute("alltasks", tasks);
			System.out.println("Tasks in controller: " + tasks);
			
			return "Alltask";
//			System.out.println("All tasks: "+b.getBooks_issued());
//			BookBean showIssuedbooks = sd.showIssuedbooks(b.getBooks_issued());
//			model.addAttribute("books_issued",showIssuedbooks);
//			System.out.println("Overdued Books: "+b.getBooks_overdue());
//			showIssuedbooks = sd.showIssuedbooks(b.getBooks_overdue());
//			model.addAttribute("books_overdued",showIssuedbooks);
//			return "homepageJSP";			
		}
		else {	
			return "loginJSP";
		}
	}
	
	
	
//	@PostMapping("/returnBook")
//	public void returnBook() {
//		HttpSession session = request.getSession();
//		String bid = (String) session.getAttribute("bid");
//		String fname = (String) session.getAttribute("fname");
//		sd.returnBooks(bid,fname);
//	}
//	
//	@PostMapping("/showbooks")
//	public String showbooks(Model model) {
//		HttpSession session = request.getSession();
//		List<BookBean>showbooks = sd.showbooks((String)session.getAttribute("fname"));
//		model.addAttribute("books",showbooks);
//		return "bookJSP";
//	}
//	@GetMapping("/signup")
//	public String signup() {
//		return "signupJSP";
//	}
	
//	@RequestMapping("/issueBook")
//	public void issueBook(@RequestParam("bid") String bid, @RequestParam("fname") String fname) {
//		LocalDateTime clickTime = LocalDateTime.now();
//		LocalDate currentDate = clickTime.toLocalDate();
//		System.out.println("Date: "+ clickTime );		
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        
//        // Format the LocalDate object as a string
//        String dateString = currentDate.format(formatter);		
//		
//		sd.issueBook(bid,fname,dateString);
//    }
}

