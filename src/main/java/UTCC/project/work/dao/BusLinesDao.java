package UTCC.project.work.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import UTCC.project.work.vo.BusLinesVo;

@Repository
public class BusLinesDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<BusLinesVo.Response> getList() {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT "
				+ "    bl.bus_lines_id,  "
				+ "    bl.bus_lines_no, "
				+ "    bl.bus_lines_origin, "
				+ "    bl.bus_lines_destination, "
				+ "    bl.bus_lines_expressway, "
				+ "    bl.bus_lines_nightshift, "
				+ "    bl.create_date, "
				+ "    bl.update_date, "
				+ "    blhbt.buslines_h_busterminal_id, "
				+ "    bt.bus_terminal_id, "
				+ "    bt.bus_terminal_name "
				+ " FROM "
				+ "    bus_lines bl "
				+ " INNER JOIN buslines_h_busterminal blhbt ON "
				+ "    bl.bus_lines_id = blhbt.bus_lines_id "
				+ " INNER JOIN bus_terminal bt ON "
				+ "    blhbt.bus_terminal_id = bt.bus_terminal_id; ");
		List<BusLinesVo.Response> datas = this.jdbcTemplate.query(sql.toString(), params.toArray(), dataRowmapper);
		return datas;
	}

	private RowMapper<BusLinesVo.Response> dataRowmapper = new RowMapper<BusLinesVo.Response>() {
		@Override
		public BusLinesVo.Response mapRow(ResultSet rs, int arg1) throws SQLException {
			BusLinesVo.Response vo = new BusLinesVo.Response();
			vo.setBusLinesId(rs.getLong("bus_lines_id"));
			vo.setBusLinesNo(rs.getString("bus_lines_no"));
			vo.setBusLinesOrigin(rs.getString("bus_lines_origin"));
			vo.setBusLinesDestination(rs.getString("bus_lines_destination"));
			vo.setBusLinesExpressway(rs.getLong("bus_lines_expressway"));
			vo.setBusLinesNightshift(rs.getLong("bus_lines_nightshift"));
			vo.setCreateDate(rs.getString("create_date"));
			vo.setUpdateDate(rs.getString("update_date"));
			vo.setBuslinesHbusterminalId(rs.getLong("buslines_h_busterminal_id"));
			vo.setBusTerminalName(rs.getString("bus_terminal_name"));
			return vo;
		}
	};
}