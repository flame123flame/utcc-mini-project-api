package UTCC.project.work.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import UTCC.project.work.vo.TicketVo;
@Repository
public class TicketDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public List<TicketVo.RequestDropdown> getData(Long id) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM worksheet join bus_vehicle on worksheet.bus_vehicle_plate_no = bus_vehicle.bus_vehicle_plate_no join bus_lines on bus_vehicle.bus_lines_id = bus_lines.bus_lines_id "
				+ "join buslines_h_busterminal on "
				+ "	bus_lines.bus_lines_id = buslines_h_busterminal.bus_lines_id "
				+ "join bus_terminal on "
				+ "	buslines_h_busterminal.bus_terminal_id = bus_terminal.bus_terminal_id "
				+ "WHERE "
				+ "	worksheet.worksheet_id = ? ");
		params.add(id);
		List<TicketVo.RequestDropdown> datas = this.jdbcTemplate.query(sql.toString(), params.toArray(), dataApproveRowmapper);
		return datas;
	}
	
	private RowMapper<TicketVo.RequestDropdown> dataApproveRowmapper = new RowMapper<TicketVo.RequestDropdown>() {
		@Override
		public TicketVo.RequestDropdown mapRow(ResultSet rs, int arg1) throws SQLException {
			TicketVo.RequestDropdown vo = new TicketVo.RequestDropdown();
			vo.setBusTerminalId(rs.getLong("bus_terminal_id"));
			vo.setBusTerminalName(rs.getString("bus_terminal_name"));
			return vo;
		}
	};
	
	
	public List<TicketVo.Response> getDataById(Long id) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM ticket  "
				+ "WHERE "
				+ "	ticket.worksheet_id = ? ");
		params.add(id);
		System.out.println(sql.toString());
		List<TicketVo.Response> datas = this.jdbcTemplate.query(sql.toString(), params.toArray(), rowmapper);
		return datas;
	}
	
	private RowMapper<TicketVo.Response> rowmapper = new RowMapper<TicketVo.Response>() {
		@Override
		public TicketVo.Response mapRow(ResultSet rs, int arg1) throws SQLException {
			TicketVo.Response vo = new TicketVo.Response();
			vo.setFareId(rs.getLong("fare_id"));
			vo.setTicketBegin(rs.getBoolean("ticket_begin"));
			vo.setTicketEnd(rs.getBoolean("ticket_end"));
			vo.setTicketEnd(rs.getBoolean("ticket_end"));
			vo.setTicketNo(rs.getString("ticket_no"));
			vo.setTrip(rs.getLong("trip"));
			vo.setWorksheetId(rs.getLong("worksheet_id"));
			return vo;
		}
	};
	
	
	
	
}
