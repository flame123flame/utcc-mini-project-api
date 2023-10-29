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
	    sql.append("SELECT "
	    		+ "	w.worksheet_id, "
	    		+ "	w.worksheet_date, "
	    		+ "	w.worksheet_time_begin, "
	    		+ "	bl.bus_lines_no, "
	    		+ "	w.worksheet_time_end, "
	    		+ "	w.worksheet_status, "
	    		+ "	w.bus_vehicle_plate_no, "
	    		+ "	w.create_by, "
	    		+ "	w.create_date, "
	    		+ "	w.update_by, "
	    		+ "	w.update_date, "
	    		+ "	w.worksheet_hours, "
	    		+ "	w.worksheet_hours_ot, "
	    		+ "	w.bus_lines_id, "
	    		+ "	w.bus_division_id, "
	    		+ "	w.bus_vehicle_id, "
	    		+ "	bd.bus_division_name, "
	    		+ "	w.bus_vehicle_number, "
	    		+ "	CONCAT(dispatcher.first_name, ' ', dispatcher.last_name) AS worksheet_dispatcher, "
	    		+ "	CONCAT(driver.first_name, ' ', driver.last_name) AS worksheet_driver, "
	    		+ "	CONCAT(farecollect.first_name, ' ', farecollect.last_name) AS worksheet_farecollect, "
	    		+ "	CONCAT(terminal_agent.first_name, ' ', terminal_agent.last_name) AS worksheet_terminal_agent, "
	    		+ "	CONCAT(buslines_manager.first_name, ' ', buslines_manager.last_name) AS worksheet_buslines_manager, "
	    		+ "	tertime.terminal_timestamp_id, "
	    		+ "	tertime.worksheet_id, "
	    		+ "	tertime.bus_terminal_id, "
	    		+ "	tertime.trip, "
	    		+ "	tertime.terminal_time_arrive, "
	    		+ "	tertime.terminal_time_departure, "
	    		+ "	tertime.bus_terminal_agent, "
	    		+ "	tertime.terminal_timestamp_status, "
	    		+ "	tertime.create_by, "
	    		+ "	tertime.create_date, "
	    		+ "	tertime.update_by, "
	    		+ "	tertime.update_date "
	    		+ "FROM "
	    		+ "	worksheet w "
	    		+ "INNER JOIN terminal_timestamp tertime ON "
	    		+ "	tertime.worksheet_id = w.worksheet_id "
	    		+ "JOIN bus_vehicle bv ON "
	    		+ "	w.bus_vehicle_plate_no = bv.bus_vehicle_plate_no "
	    		+ "JOIN bus_division bd ON "
	    		+ "	bd.bus_division_id = w.bus_division_id "
	    		+ "JOIN bus_lines bl ON "
	    		+ "	bl.bus_lines_id = w.bus_lines_id "
	    		+ "JOIN employee dispatcher ON "
	    		+ "	w.worksheet_dispatcher = dispatcher.username "
	    		+ "JOIN employee driver ON "
	    		+ "	w.worksheet_driver = driver.username "
	    		+ "JOIN employee farecollect ON "
	    		+ "	w.worksheet_farecollect = farecollect.username "
	    		+ "LEFT JOIN employee terminal_agent ON "
	    		+ "	w.worksheet_terminal_agent = terminal_agent.username "
	    		+ "LEFT JOIN employee buslines_manager ON "
	    		+ "	w.worksheet_buslines_manager = buslines_manager.username "
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
	    sql.append("SELECT"
	    		+ "	w.worksheet_id, "
	    		+ "	w.worksheet_date, "
	    		+ "	w.worksheet_time_begin, "
	    		+ "	bl.bus_lines_no, "
	    		+ "	w.worksheet_time_end, "
	    		+ "	w.worksheet_status, "
	    		+ "	w.bus_vehicle_plate_no, "
	    		+ "	w.create_by, "
	    		+ "	w.create_date, "
	    		+ "	w.update_by, "
	    		+ "	w.update_date, "
	    		+ "	w.worksheet_hours, "
	    		+ "	w.worksheet_hours_ot, "
	    		+ "	w.bus_lines_id, "
	    		+ "	w.bus_division_id, "
	    		+ "	w.bus_vehicle_id, "
	    		+ "	bd.bus_division_name, "
	    		+ "	w.bus_vehicle_number, "
	    		+ "	CONCAT(dispatcher.first_name, ' ', dispatcher.last_name) AS worksheet_dispatcher, "
	    		+ "	CONCAT(driver.first_name, ' ', driver.last_name) AS worksheet_driver, "
	    		+ "	CONCAT(farecollect.first_name, ' ', farecollect.last_name) AS worksheet_farecollect, "
	    		+ "	CONCAT(terminal_agent.first_name, ' ', terminal_agent.last_name) AS worksheet_terminal_agent, "
	    		+ "	CONCAT(buslines_manager.first_name, ' ', buslines_manager.last_name) AS worksheet_buslines_manager, "
	    		+ "	tertime.terminal_timestamp_id, "
	    		+ "	tertime.worksheet_id, "
	    		+ "	tertime.bus_terminal_id, "
	    		+ "	tertime.trip, "
	    		+ "	tertime.terminal_time_arrive, "
	    		+ "	tertime.terminal_time_departure, "
	    		+ "	tertime.bus_terminal_agent, "
	    		+ "	tertime.terminal_timestamp_status, "
	    		+ "	tertime.create_by, "
	    		+ "	tertime.create_date, "
	    		+ "	tertime.update_by, "
	    		+ "	tertime.update_date "
	    		+ "FROM "
	    		+ "	worksheet w "
	    		+ "INNER JOIN terminal_timestamp tertime ON "
	    		+ "	tertime.worksheet_id = w.worksheet_id "
	    		+ "JOIN bus_vehicle bv ON "
	    		+ "	w.bus_vehicle_plate_no = bv.bus_vehicle_plate_no "
	    		+ "JOIN bus_division bd ON "
	    		+ "	bd.bus_division_id = w.bus_division_id "
	    		+ "JOIN bus_lines bl ON "
	    		+ "	bl.bus_lines_id = w.bus_lines_id "
	    		+ "JOIN employee dispatcher ON "
	    		+ "	w.worksheet_dispatcher = dispatcher.username "
	    		+ "JOIN employee driver ON "
	    		+ "	w.worksheet_driver = driver.username "
	    		+ "JOIN employee farecollect ON "
	    		+ "	w.worksheet_farecollect = farecollect.username "
	    		+ "LEFT JOIN employee terminal_agent ON "
	    		+ "	w.worksheet_terminal_agent = terminal_agent.username "
	    		+ "LEFT JOIN employee buslines_manager ON "
	    		+ "	w.worksheet_buslines_manager = buslines_manager.username "
	    		+ "WHERE "
	    		+ "	tertime.terminal_timestamp_status = ? "
	    		+ "	AND tertime.bus_terminal_id = ? ");
	 
	    return sql.toString();
	}
	
	
	
	

}
