package com.klef.jfsd.springboot.service;

import java.util.List;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Customer;
import com.klef.jfsd.springboot.model.Employee;

public interface AdminService {
	public List<Employee> viewAllEmployees();
	public Admin checkadminlogin(String uname, String pwd);
	public String deleteEmployee(int eid);
	public Employee viewEmployeeByID(int eid);
	public long employeesCount();
	public String updateEmpStatus(String status, int eid);
	public List<Employee> viewRegisteredEmps(String status);
	public long employeesRegisteredCount(String status);
	
	public String addCustomer(Customer c);
}
