package UTCC.project.work.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import UTCC.framework.utils.CommonJdbcTemplate;
import UTCC.project.work.vo.TicketTripVo;
@Repository
public class TicketTripDao {

	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	
	public List<TicketTripVo.Response> getTicketTripByWorksheetId(long id) {
		String sql = " SELECT * FROM ticket_trip WHERE ticket_trip.worksheet_id = ? ";

		List<Object> params = new ArrayList<>();
		params.add(id);
		System.out.println(sql);
		return commonJdbcTemplate.executeQuery(sql, params.toArray(),
				BeanPropertyRowMapper.newInstance(TicketTripVo.Response.class));
	}
	
	
	public List<TicketTripVo.TicketAndFare> getTicketTripDetail(long id) {
		String sql = " SELECT * FROM ticket join fare on  ticket.fare_id = fare.fare_id WHERE ticket.ticket_trip_id = ? ";
		List<Object> params = new ArrayList<>();
		params.add(id);
		return commonJdbcTemplate.executeQuery(sql, params.toArray(),
				BeanPropertyRowMapper.newInstance(TicketTripVo.TicketAndFare.class));
	}
}
