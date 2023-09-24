package UTCC.project.employee.repo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.employee.module.Employee;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long> {

	public Employee findByEmployeeCode(String text);
	
//	public Employee findByUsername(String userName);
	 @Query(value = "SELECT * FROM Employee e WHERE e.username = ?1", nativeQuery = true)
	    public Employee findByUsername(String userName);
	
    @Query(value = "SELECT * FROM employee e WHERE e.username = ?1 AND e.bus_lines_id = ?2 AND  e.employee_shift = ?3 and employee_status = 'AVAILABLE' ", nativeQuery = true)
    public Employee findByUsernameBusLinesId(String userName, Long busLinesId,String employeeShift);
	
	
	public void deleteByUsername(String username);
	
	public List <Employee> findAll();
	
}
