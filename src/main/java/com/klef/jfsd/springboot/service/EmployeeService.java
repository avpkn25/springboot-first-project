package com.klef.jfsd.springboot.service;

import java.util.List;

import com.klef.jfsd.springboot.model.Employee;

public interface EmployeeService {
	public String employeeRegistration(Employee e);
	public Employee checkEmployeeLogin(String email, String pwd, String status);
	public Employee displayEmployeeByID(int eid);
	public String updateEmployeeProfile(Employee emp);
	
	public List<Employee> displayEmployeeByDept(String dept);
}
