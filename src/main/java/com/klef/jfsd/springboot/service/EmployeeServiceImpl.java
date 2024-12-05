package com.klef.jfsd.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.klef.jfsd.springboot.model.Employee;
import com.klef.jfsd.springboot.repository.EmployeeRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public String employeeRegistration(Employee e) {
		employeeRepository.save(e);
		return "Student Added Successfully";
	}

	@Override
	public Employee checkEmployeeLogin(String email, String pwd, String status) {
		return employeeRepository.checkemplogin(email, pwd, status);
	}

	@Override
	public Employee displayEmployeeByID(int eid) {
		return employeeRepository.findById(eid).get();
	}

	@Override
	public String updateEmployeeProfile(Employee emp) {
		Employee e = employeeRepository.findById(emp.getId()).get();
	    
	    e.setContact(emp.getContact());
	    e.setDateofbirth(emp.getDateofbirth());
	    e.setDepartment(emp.getDepartment());
	    e.setGender(emp.getGender());
	    e.setLocation(emp.getLocation());
	    e.setName(emp.getName());
	    e.setPassword(emp.getPassword());
	    e.setSalary(emp.getSalary());
	    
	    employeeRepository.save(e);
	    
	    return "Employee Updated Successfully";
	}

	@Override
	public List<Employee> displayEmployeeByDept(String dept) {
		return employeeRepository.findByDepartment(dept);
	}
	
}
