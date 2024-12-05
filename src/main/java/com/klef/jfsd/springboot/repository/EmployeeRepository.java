package com.klef.jfsd.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.springboot.model.Employee;

import jakarta.transaction.Transactional;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	@Query("select e from Employee e where e.email=?1 and e.password=?2 and e.status=?3 ")
	public Employee checkemplogin(String email, String pwd, String status);
	
	@Query("update Employee e set e.status=?1 where  e.id=?2 ")
	@Modifying
	@Transactional
	public int updateempstatus(String status, int id);
	
	@Query("select e from Employee e where e.status=?1")
	public List<Employee> viewRegisteredEmps(String status);
	
	@Query("select count(e) from Employee e where e.status=?1")
	public long countRegistered(String status);
	
	// no need to write queries explicitly
	public List<Employee> findByDepartment(String department);
	public List<Employee> findByDepartmentAndGender(String department, String gender);
	public List<Employee> findByGender(String gender);
	
	
}
