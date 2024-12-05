package com.klef.jfsd.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Customer;
import com.klef.jfsd.springboot.model.Employee;
import com.klef.jfsd.springboot.repository.AdminRepository;
import com.klef.jfsd.springboot.repository.CustomerRepository;
import com.klef.jfsd.springboot.repository.EmployeeRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public List<Employee> viewAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Admin checkadminlogin(String uname, String pwd) {
		return adminRepository.checkadminlogin(uname, pwd);
	}

	@Override
	public String deleteEmployee(int eid) {
		employeeRepository.deleteById(eid);
		return "Student Deleted Successfully";
	}

	@Override
	public Employee viewEmployeeByID(int eid) {
		return employeeRepository.findById(eid).get();
	}

	@Override
	public long employeesCount() {
		return employeeRepository.count();
	}

	@Override
	public String updateEmpStatus(String status, int eid) {
		employeeRepository.updateempstatus(status, eid);
		return "Employee Status Updated Successfully";
	}

	@Override
	public String addCustomer(Customer c) {
		customerRepository.save(c);
		return "Customer Added Successfully";
	}

	@Override
	public List<Employee> viewRegisteredEmps(String status) {
		return employeeRepository.viewRegisteredEmps(status);
	}

	@Override
	public long employeesRegisteredCount(String status) {
		return employeeRepository.countRegistered(status);
	}
}
