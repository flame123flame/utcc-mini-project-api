package UTCC.project.employee.repo.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.employee.module.Employee;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long> {

	public Employee findByEmployeeCode(String text);
	
	public Employee findByUsername(String userName);
	
	public List <Employee> findAll();
	
}
