package com.springboot.springrest.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.springrest.employee.model.Employee;
import com.springboot.springrest.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
    EmployeeRepository empRepository;
	
	// CREATE 
	public Employee createEmployee(Employee emp) {
		Employee userValidation = empRepository.findByEmail(emp.getEmailId());
		if(userValidation != null) throw new RuntimeException("User already Exists");
	    return empRepository.save(emp);
	}

	// READ
	public List<Employee> getEmployees() {
		
	    return empRepository.findAll();
	}

	// DELETE
	public void deleteEmployee(String email) {
	    empRepository.deleteByEmail(email);
	}
	    
	 // UPDATE
	    public Employee updateEmployee(Long empId, Employee employeeDetails) {
	            Employee emp = empRepository.findById(empId).get();
	            emp.setFirstName(employeeDetails.getFirstName());
	            emp.setLastName(employeeDetails.getLastName());
	            emp.setEmailId(employeeDetails.getEmailId());
	            
	            return empRepository.save(emp);                                
	    }
	    
	    public String valiDateEmployee(String email, String password,Employee emp) {
	    	Employee validate =empRepository.findByEmail(email);
	    	if(null != empRepository && validate.getPassword().equals(password)){
	    		return "User Verified";
	    	}else{
	    		return "Invalid Credentials";
	    	}
	    	    	
	    }


}
