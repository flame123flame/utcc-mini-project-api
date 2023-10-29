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
import UTCC.project.work.vo.TerminalTimestampVo;

@Repository
public class TerminalTimestampDao {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	
	public void updateStatusTerminalTimestamp(String status,long id) {
	    String sql = " UPDATE terminal_timestamp SET terminal_timestamp_status = ? WHERE worksheet_id = ? ";
	    List<Object> params = new ArrayList<>();
	    params.add(status);
	    params.add(id);
	    commonJdbcTemplate.executeInsert(sql, params.toArray());
	}

	
	
	
	public List<TerminalTimestampVo.Response> getDataSuccess(String status) {
	    Employee employee = employeeRepo.findByUsername(UserLoginUtil.getUsername());
	    String sql = buildQuerySuccess();
	    List<Object> params = new ArrayList<>();
	    params.add(status);
	    params.add(employee.getBusTerminalId());
	    return commonJdbcTemplate.executeQuery(sql, params.toArray(), BeanPropertyRowMapper.newInstance(TerminalTimestampVo.Response.class));
	}

	
	
	
	private String buildQuerySuccess() {
	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT\n"
	    		+ "	w.worksheet_id,\n"
	    		+ "	w.worksheet_date,\n"
	    		+ "	w.worksheet_time_begin,\n"
	    		+ "	bl.bus_lines_no,\n"
	    		+ "	w.worksheet_time_end,\n"
	    		+ "	w.worksheet_status,\n"
	    		+ "	w.bus_vehicle_plate_no,\n"
	    		+ "	w.create_by,\n"
	    		+ "	w.create_date,\n"
	    		+ "	w.update_by,\n"
	    		+ "	w.update_date,\n"
	    		+ "	w.worksheet_hours,\n"
	    		+ "	w.worksheet_hours_ot,\n"
	    		+ "	w.bus_lines_id,\n"
	    		+ "	w.bus_division_id,\n"
	    		+ "	w.bus_vehicle_id,\n"
	    		+ "	bd.bus_division_name,\n"
	    		+ "	w.bus_vehicle_number,\n"
	    		+ "	CONCAT(dispatcher.first_name, ' ', dispatcher.last_name) AS worksheet_dispatcher,\n"
	    		+ "	CONCAT(driver.first_name, ' ', driver.last_name) AS worksheet_driver,\n"
	    		+ "	CONCAT(farecollect.first_name, ' ', farecollect.last_name) AS worksheet_farecollect,\n"
	    		+ "	CONCAT(terminal_agent.first_name, ' ', terminal_agent.last_name) AS worksheet_terminal_agent,\n"
	    		+ "	CONCAT(buslines_manager.first_name, ' ', buslines_manager.last_name) AS worksheet_buslines_manager,\n"
	    		+ "	tertime.terminal_timestamp_id,\n"
	    		+ "	tertime.worksheet_id,\n"
	    		+ "	tertime.bus_terminal_id,\n"
	    		+ "	tertime.trip,\n"
	    		+ "	tertime.terminal_time_arrive,\n"
	    		+ "	tertime.terminal_time_departure,\n"
	    		+ "	tertime.bus_terminal_agent,\n"
	    		+ "	tertime.terminal_timestamp_status,\n"
	    		+ "	tertime.create_by,\n"
	    		+ "	tertime.create_date,\n"
	    		+ "	tertime.update_by,\n"
	    		+ "	tertime.update_date\n"
	    		+ "FROM\n"
	    		+ "	worksheet w\n"
	    		+ "INNER JOIN terminal_timestamp tertime ON\n"
	    		+ "	tertime.worksheet_id = w.worksheet_id\n"
	    		+ "JOIN bus_vehicle bv ON\n"
	    		+ "	w.bus_vehicle_plate_no = bv.bus_vehicle_plate_no\n"
	    		+ "JOIN bus_division bd ON\n"
	    		+ "	bd.bus_division_id = w.bus_division_id\n"
	    		+ "JOIN bus_lines bl ON\n"
	    		+ "	bl.bus_lines_id = w.bus_lines_id\n"
	    		+ "JOIN employee dispatcher ON\n"
	    		+ "	w.worksheet_dispatcher = dispatcher.username\n"
	    		+ "JOIN employee driver ON\n"
	    		+ "	w.worksheet_driver = driver.username\n"
	    		+ "JOIN employee farecollect ON\n"
	    		+ "	w.worksheet_farecollect = farecollect.username\n"
	    		+ "LEFT JOIN employee terminal_agent ON\n"
	    		+ "	w.worksheet_terminal_agent = terminal_agent.username\n"
	    		+ "LEFT JOIN employee buslines_manager ON\n"
	    		+ "	w.worksheet_buslines_manager = buslines_manager.username\n"
	    		+ "WHERE "
	    		+ "	tertime.terminal_timestamp_status = ? "
	    		+ "	AND tertime.bus_terminal_id = ?  AND tertime.trip = (SELECT MAX(trip) FROM terminal_timestamp WHERE worksheet_id = w.worksheet_id) ");
	 
	    return sql.toString();
	}

	
	
	
	public List<TerminalTimestampVo.Response> getData(String status) {
	    Employee employee = employeeRepo.findByUsername(UserLoginUtil.getUsername());
	    String sql = buildQuery();
	    List<Object> params = new ArrayList<>();
	    params.add(status);
	    params.add(employee.getBusTerminalId());
	    return commonJdbcTemplate.executeQuery(sql, params.toArray(), BeanPropertyRowMapper.newInstance(TerminalTimestampVo.Response.class));
	}

	private String buildQuery() {
	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT\n"
	    		+ "	w.worksheet_id,\n"
	    		+ "	w.worksheet_date,\n"
	    		+ "	w.worksheet_time_begin,\n"
	    		+ "	bl.bus_lines_no,\n"
	    		+ "	w.worksheet_time_end,\n"
	    		+ "	w.worksheet_status,\n"
	    		+ "	w.bus_vehicle_plate_no,\n"
	    		+ "	w.create_by,\n"
	    		+ "	w.create_date,\n"
	    		+ "	w.update_by,\n"
	    		+ "	w.update_date,\n"
	    		+ "	w.worksheet_hours,\n"
	    		+ "	w.worksheet_hours_ot,\n"
	    		+ "	w.bus_lines_id,\n"
	    		+ "	w.bus_division_id,\n"
	    		+ "	w.bus_vehicle_id,\n"
	    		+ "	bd.bus_division_name,\n"
	    		+ "	w.bus_vehicle_number,\n"
	    		+ "	CONCAT(dispatcher.first_name, ' ', dispatcher.last_name) AS worksheet_dispatcher,\n"
	    		+ "	CONCAT(driver.first_name, ' ', driver.last_name) AS worksheet_driver,\n"
	    		+ "	CONCAT(farecollect.first_name, ' ', farecollect.last_name) AS worksheet_farecollect,\n"
	    		+ "	CONCAT(terminal_agent.first_name, ' ', terminal_agent.last_name) AS worksheet_terminal_agent,\n"
	    		+ "	CONCAT(buslines_manager.first_name, ' ', buslines_manager.last_name) AS worksheet_buslines_manager,\n"
	    		+ "	tertime.terminal_timestamp_id,\n"
	    		+ "	tertime.worksheet_id,\n"
	    		+ "	tertime.bus_terminal_id,\n"
	    		+ "	tertime.trip,\n"
	    		+ "	tertime.terminal_time_arrive,\n"
	    		+ "	tertime.terminal_time_departure,\n"
	    		+ "	tertime.bus_terminal_agent,\n"
	    		+ "	tertime.terminal_timestamp_status,\n"
	    		+ "	tertime.create_by,\n"
	    		+ "	tertime.create_date,\n"
	    		+ "	tertime.update_by,\n"
	    		+ "	tertime.update_date\n"
	    		+ "FROM\n"
	    		+ "	worksheet w\n"
	    		+ "INNER JOIN terminal_timestamp tertime ON\n"
	    		+ "	tertime.worksheet_id = w.worksheet_id\n"
	    		+ "JOIN bus_vehicle bv ON\n"
	    		+ "	w.bus_vehicle_plate_no = bv.bus_vehicle_plate_no\n"
	    		+ "JOIN bus_division bd ON\n"
	    		+ "	bd.bus_division_id = w.bus_division_id\n"
	    		+ "JOIN bus_lines bl ON\n"
	    		+ "	bl.bus_lines_id = w.bus_lines_id\n"
	    		+ "JOIN employee dispatcher ON\n"
	    		+ "	w.worksheet_dispatcher = dispatcher.username\n"
	    		+ "JOIN employee driver ON\n"
	    		+ "	w.worksheet_driver = driver.username\n"
	    		+ "JOIN employee farecollect ON\n"
	    		+ "	w.worksheet_farecollect = farecollect.username\n"
	    		+ "LEFT JOIN employee terminal_agent ON\n"
	    		+ "	w.worksheet_terminal_agent = terminal_agent.username\n"
	    		+ "LEFT JOIN employee buslines_manager ON\n"
	    		+ "	w.worksheet_buslines_manager = buslines_manager.username\n"
	    		+ "WHERE\n"
	    		+ "	tertime.terminal_timestamp_status = ? "
	    		+ "	AND tertime.bus_terminal_id = ? ");
	 
	    return sql.toString();
	}
	
	
	
	

}
