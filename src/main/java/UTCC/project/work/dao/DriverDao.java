package UTCC.project.work.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import UTCC.framework.utils.CommonJdbcTemplate;
import UTCC.framework.utils.UserLoginUtil;
import UTCC.project.work.vo.DriverVo;

@Repository
public class DriverDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<DriverVo.Response> getDataDriver(String type, String status) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(
				" SELECT * FROM  worksheet join  bus_vehicle  on  worksheet.bus_vehicle_plate_no = bus_vehicle.bus_vehicle_plate_no   ");
		sql.append(" WHERE worksheet_driver = ?  AND worksheet.worksheet_status = ? ");
		params.add(UserLoginUtil.getUsername());
		params.add(status);

		List<DriverVo.Response> datas = this.jdbcTemplate.query(sql.toString(), params.toArray(), dataDriverRowmapper);
		return datas;
	}

	private RowMapper<DriverVo.Response> dataDriverRowmapper = new RowMapper<DriverVo.Response>() {
		@Override
		public DriverVo.Response mapRow(ResultSet rs, int arg1) throws SQLException {
			DriverVo.Response vo = new DriverVo.Response();
			vo.setBusVehicleNumber(rs.getString("bus_vehicle_number"));
			vo.setBusVehiclePlateNo(rs.getString("bus_vehicle_plate_no"));
			vo.setWorksheetDate(rs.getString("worksheet_date"));
			vo.setWorksheetFarecollect(rs.getString("worksheet_farecollect"));
			vo.setWorksheetId(rs.getLong("worksheet_id"));
			return vo;
		}
	};

	public List<DriverVo.Response> getDataFarecollect(String status) {
		String sql = "SELECT w.worksheet_id, w.worksheet_date, w.worksheet_time_begin, bl.bus_lines_no, "
				+ "w.worksheet_time_end, w.worksheet_status, w.bus_vehicle_plate_no, "
				+ "w.create_by, w.create_date, w.update_by, w.update_date, w.worksheet_hours, "
				+ "w.worksheet_hours_ot, w.bus_lines_id, w.bus_division_id, w.bus_vehicle_id, "
				+ "bd.bus_division_name, w.bus_vehicle_number, "
				+ "CONCAT(dispatcher.first_name, ' ', dispatcher.last_name) AS worksheet_dispatcher, "
				+ "CONCAT(driver.first_name, ' ', driver.last_name) AS worksheet_driver, "
				+ "CONCAT(farecollect.first_name, ' ', farecollect.last_name) AS worksheet_farecollect, "
				+ "CONCAT(terminal_agent.first_name, ' ', terminal_agent.last_name) AS worksheet_terminal_agent, "
				+ "CONCAT(buslines_manager.first_name, ' ', buslines_manager.last_name) AS worksheet_buslines_manager "
				+ "FROM worksheet w " + "JOIN bus_vehicle bv ON w.bus_vehicle_plate_no = bv.bus_vehicle_plate_no "
				+ "JOIN bus_division bd ON bd.bus_division_id = w.bus_division_id "
				+ "JOIN bus_lines bl ON bl.bus_lines_id = w.bus_lines_id "
				+ "JOIN employee dispatcher ON w.worksheet_dispatcher = dispatcher.username "
				+ "JOIN employee driver ON w.worksheet_driver = driver.username "
				+ "JOIN employee farecollect ON w.worksheet_farecollect = farecollect.username "
				+ "LEFT JOIN employee terminal_agent ON w.worksheet_terminal_agent = terminal_agent.username "
				+ "LEFT JOIN employee buslines_manager ON w.worksheet_buslines_manager = buslines_manager.username "
				+ "WHERE w.worksheet_farecollect = ? AND w.worksheet_status = ? ";

		List<Object> params = new ArrayList<>();
		params.add(UserLoginUtil.getUsername());
		params.add(status);
		System.out.println(sql);
		return commonJdbcTemplate.executeQuery(sql, params.toArray(),
				BeanPropertyRowMapper.newInstance(DriverVo.Response.class));
	}

}
