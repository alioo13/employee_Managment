package com.employeesystem.employee.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeesystem.employee.entity.EmployeeEntity;
import com.employeesystem.employee.model.Employee;
import com.employeesystem.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepo;
	
	@Override
	public Employee createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setEmailId(employee.getEmailId());
       
        employeeRepo.save(employeeEntity);
        return employee ;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<EmployeeEntity> employentities = employeeRepo.findAll();
		
		List<Employee> employees = employentities
				 .stream()
				.map(emp -> new Employee(
				emp.getId(), 
				emp.getFirstName(), 
				emp.getLastName(), 
				emp.getEmailId())) 
				.collect(Collectors.toList());
		return employees;
	}

	@Override
	public boolean deleteEmployee(Long id) {
		EmployeeEntity employee= employeeRepo.findById(id).get();
		employeeRepo.delete(employee);
		return true;
	}

	@Override
	public Employee getEmployeeById(Long id) {
		EmployeeEntity employeeEntity= employeeRepo.findById(id).get();
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeEntity, employee);
		return employee;	
//		Employee employees = ((Collection<Employee>) employeeEntity)
//				    .stream()
//					.filter(t -> id.equals(t.getId()))
//					.findFirst()
//					.orElse(null);

//		EmployeeEntity employeeEntity1= employeeRepo.getById(id);
//		return employeeRepo.getById(id)
	}

	@Override
	public Employee updateEmployee(Long id, Employee employee) {
		EmployeeEntity employeeEntity= employeeRepo.findById(id).get();
		
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setEmailId(employee.getEmailId());
       
        employeeRepo.save(employeeEntity);
		
		return employee;
	}
	


}
