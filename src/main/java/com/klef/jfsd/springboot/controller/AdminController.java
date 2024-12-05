package com.klef.jfsd.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Customer;
import com.klef.jfsd.springboot.model.Employee;
import com.klef.jfsd.springboot.service.AdminService;


import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
  
	@GetMapping("adminlogin")
	public ModelAndView adminlogin(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("adminlogin");
		return mv;
	}
	
	@GetMapping("viewallemps")
	public ModelAndView viewallemps(){
		ModelAndView mv = new ModelAndView();
		List<Employee> emplist = adminService.viewAllEmployees();
		mv.setViewName("viewallemps");
		long count = adminService.employeesCount();
		mv.addObject("count",count);
		mv.addObject("emplist",emplist);
		return mv;
	}
	
	@GetMapping("deleteemp")
	public ModelAndView deleteemp(){
		ModelAndView mv = new ModelAndView();
		List<Employee> emplist = adminService.viewAllEmployees();
		mv.setViewName("deleteemp");
		long count = adminService.employeesCount();
		mv.addObject("count",count);
		mv.addObject("emplist",emplist);
		return mv;
	}
	
	@GetMapping("delete")
	public String deleteOperation( @RequestParam("id") int eid) {
		adminService.deleteEmployee(eid);
		return "redirect:/deleteemp"; // redirection to specific path
	}
	
	@GetMapping("updateempstatus")
	public ModelAndView updateempstatus(){
		ModelAndView mv = new ModelAndView();
		List<Employee> emplist = adminService.viewRegisteredEmps("Registered");
		mv.setViewName("updateempstatus");
		long count = adminService.employeesRegisteredCount("Registered");
		if(count>0) {
			mv.addObject("count",count);
			mv.addObject("emplist",emplist);
		}else {
			mv.setViewName("noupdateempstatus");
			mv.addObject("message","No Registered Employees");
		}
		return mv;
	}
	
	@GetMapping("updatestatus")
	public String updatestatus( @RequestParam("id") int eid, @RequestParam("status") String status) {
		adminService.updateEmpStatus(status, eid);
		return "redirect:/updateempstatus"; // redirection to specific path
	}
	
	@PostMapping("checkadminlogin")
	//@ResponseBody
	public ModelAndView checkadminlogin(HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView();
		String auname = request.getParameter("auname");
		String apwd = request.getParameter("apwd");
		
		Admin admin = adminService.checkadminlogin(auname, apwd);
		
		if(admin!=null) {
			//return "Admin Login Successfully";
			mv.setViewName("adminhome");
			long count = adminService.employeesCount();
			mv.addObject("count",count);
		}else {
			//return "Admin Login Fail";
			mv.setViewName("adminloginfail");
			mv.addObject("message","Login Failed");
		}
		return mv;
	}
	
	@GetMapping("adminhome")
	public ModelAndView adminhome(){
		ModelAndView mv = new ModelAndView();
		//List<Employee> emplist = adminService.viewAllEmployees();
		mv.setViewName("adminhome");
		//mv.addObject("emplist",emplist);
		long count = adminService.employeesCount();
		mv.addObject("count",count);
		return mv;
	}
	
	@GetMapping("adminlogout")
	public ModelAndView adminlogout(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("adminlogin");
		return mv;
	}
	
	@GetMapping("addcustomer")
    public String addcustomer(Model m){
		m.addAttribute("customer", new Customer());
		return "addcustomer"; //addcustomer.jsp
    }
	
	@PostMapping("insertcustomer")
    public ModelAndView insertcustomer(@ModelAttribute("customer") Customer c){
      ModelAndView mv = new ModelAndView();
      
      mv.setViewName("customersuccess");
      
      String msg = adminService.addCustomer(c);
      mv.addObject("message", msg);
      
      return mv;
    }
	
}