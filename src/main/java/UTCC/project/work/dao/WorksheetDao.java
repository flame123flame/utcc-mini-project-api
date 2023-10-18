package UTCC.project.work.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import UTCC.framework.utils.CommonJdbcTemplate;
import UTCC.framework.utils.UserLoginUtil;
import UTCC.project.employee.module.Employee;
import UTCC.project.employee.repo.jpa.EmployeeRepo;
import UTCC.project.work.vo.WorksheetVo;

@Repository
public class WorksheetDao {

	

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Autowired
	private EmployeeRepo employeeRepo;

	public List<WorksheetVo.Response> getDataFarecollect(String status) {
	    Employee employee = employeeRepo.findByUsername(UserLoginUtil.getUsername());

	    String sql = "SELECT * " +
	                 "FROM worksheet " +
	                 "JOIN employee ON worksheet.worksheet_driver = employee.username " +
	                 "WHERE worksheet.bus_lines_id = ? " +
	                 "AND employee.employee_shift = ? " +
	                 "AND worksheet.worksheet_status = ?";

	    Object[] params = {employee.getBuslinesId(), employee.getEmployeeShift(), status};
	    System.out.println(sql);

	    return commonJdbcTemplate.executeQuery(sql, params, BeanPropertyRowMapper.newInstance(WorksheetVo.Response.class));
	}
}
