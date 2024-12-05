package com.klef.jfsd.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.klef.jfsd.springboot.model.Employee;
import com.klef.jfsd.springboot.service.EmployeeService;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	 private JavaMailSender mailSender;
	
	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("home");
		return mv;
	}
	@GetMapping("empreg")
	public ModelAndView empreg() {
		ModelAndView mv = new ModelAndView("empreg");
		return mv;
	}
	@GetMapping("emplogin")
	public ModelAndView emplogin() {
		ModelAndView mv = new ModelAndView("emplogin");
		return mv;
	}
	@PostMapping("insertemp")
	public ModelAndView insertemp(HttpServletRequest request){
		String name = request.getParameter("ename");
	    String gender = request.getParameter("egender");
	    String dob = request.getParameter("edob");
	    String dept = request.getParameter("edept");
	    double salary = Double.parseDouble(request.getParameter("esalary"));
	    String location = request.getParameter("elocation");
	    String email = request.getParameter("eemail");
	    String password = request.getParameter("epwd");
	    String contact = request.getParameter("econtact");
	    String status = "Registered";
	    
	    Employee emp = new Employee();
	    emp.setName(name);
	    emp.setGender(gender);
	    emp.setDepartment(dept);
	    emp.setDateofbirth(dob);
	    emp.setSalary(salary);
	    emp.setLocation(location);
	    emp.setEmail(email);
	    emp.setPassword(password);
	    emp.setContact(contact);
	    emp.setStatus(status);
	      
	    String msg = employeeService.employeeRegistration(emp);
	      
	    ModelAndView mv = new ModelAndView("regsuccess");
	    mv.addObject("message", msg);
	    
	    return mv;
	}
	
	@PostMapping("checkemplogin")
	public ModelAndView checkemplogin(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
	     
		String eemail = request.getParameter("eemail");
		String epwd = request.getParameter("epwd");
	     
		Employee emp =  employeeService.checkEmployeeLogin(eemail, epwd, "Accepted");
	     
		if(emp!=null){
			// session 
			HttpSession session = request.getSession();
			session.setAttribute("employee",emp); // employee is session variable
			
			mv.setViewName("emphome");
			//session.setMaxInactiveInterval(5);
		}
		else{
			mv.setViewName("emplogin");
			mv.addObject("message", "Login Failed");
		} 
		return mv;
	}
	
	@GetMapping("emphome")
	public ModelAndView emphome() {
		ModelAndView mv = new ModelAndView("emphome");
		return mv;
	}
	
	@GetMapping("empprofile")
	public ModelAndView empprofile() {
		ModelAndView mv = new ModelAndView("empprofile");
		return mv;
	}
	
	@GetMapping("empcontactus")
	public ModelAndView empcontactus() {
		ModelAndView mv = new ModelAndView("empcontactus");
		return mv;
	}
	
	@GetMapping("emplogout")
	public ModelAndView emplogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("employee"); // employee is session variable
		
		//session.invalidate(); // removes all session attributes
		
		ModelAndView mv = new ModelAndView("emplogin");
		return mv;
	}
	
	@GetMapping("updateemp")
	public ModelAndView updateemp() {
		ModelAndView mv = new ModelAndView("updateemp");
		return mv;
	}
	
	@PostMapping("updateempprofile")
	public ModelAndView updateempprofile(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
     
		try{
			int id = Integer.parseInt(request.getParameter("eid"));
			String name = request.getParameter("ename");
			String gender = request.getParameter("egender");
			String dob = request.getParameter("edob");
			String dept = request.getParameter("edept");
			double salary = Double.parseDouble(request.getParameter("esalary"));
			String password = request.getParameter("epwd");
			String location = request.getParameter("elocation");
			String contact = request.getParameter("econtact");
      
			Employee emp = new Employee();
			emp.setId(id);
			emp.setName(name);
			emp.setGender(gender);
			emp.setDepartment(dept);
			emp.setDateofbirth(dob);
			emp.setSalary(salary);
			emp.setPassword(password);
			emp.setLocation(location);
			emp.setContact(contact);
    
			String msg = employeeService.updateEmployeeProfile(emp);
    
			Employee e = employeeService.displayEmployeeByID(id);
    
			HttpSession session = request.getSession();
			session.setAttribute("employee",e); //employee is session variable
   
   
			mv.setViewName("updatesuccess");
			mv.addObject("message", msg);
      
		}
		catch(Exception e){
			mv.setViewName("updateerror");
			mv.addObject("message", e);
		}
		return mv;
	}
	
	@GetMapping("empsessionexpiry")
	public ModelAndView empsessionexpiry() {
		ModelAndView mv = new ModelAndView("empsessionexpiry");
		return mv;
	}
	
	@GetMapping("viewempsbydept")
	public ModelAndView viewempsbydept(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("viewempsbydept");
		HttpSession session = request.getSession();
		Employee e = (Employee) session.getAttribute("employee");
		List<Employee> emplist = employeeService.displayEmployeeByDept(e.getDepartment());
		mv.addObject("emplist",emplist);
		return mv;
	}
	
	@PostMapping("sendemail")
	 public ModelAndView sendEmail(HttpServletRequest request) throws Exception
	 {
	 String name = request.getParameter("name");
	 String toemail = request.getParameter("email");
	 String subject = request.getParameter("subject");
	 String msg = request.getParameter("message");
	 MimeMessage mimeMessage = mailSender.createMimeMessage();
	 MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

	 int otp = (int)(Math.random() * 99999); // random number generation
	 helper.setTo(toemail);
	 helper.setSubject(subject);
	 helper.setFrom("praveenkumarnaidu88@gmail.com");
	 String htmlContent =
	 "<h3>Contact Form Details</h3>" +
	 "<p><strong>Name:</strong> " + name + "</p>" +
	 "<p><strong>Email:</strong> " + toemail + "</p>" +
	 "<p><strong>Subject:</strong> " + subject + "</p>" +
	 "<p><strong>Message:</strong> " + msg + "</p>" +
	 "<p><strong>OTP:</strong> " + otp + "</p>";
	 helper.setText(htmlContent, true);
	 mailSender.send(mimeMessage);

	 ModelAndView mv = new ModelAndView("mailsuccess");
	 mv.addObject("message", "Email Sent Successfully");
	 return mv;
	 }
}
