package UTCC.project.work.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import UTCC.project.work.vo.BusDivisionVo;

@Repository
public class BusDivisionDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<BusDivisionVo.Response> getList() {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT "
				+ "    bdiv.bus_division_id,  "
				+ "    bdiv.bus_division_no, "
				+ "    bdiv.bus_division_name, "
				+ "    bdiv.bmta_zone, "
				+ "    bdiv.create_date, "
				+ "    bdiv.update_date, "
				+ "    bdepot.bus_depot_id , "
				+ "    bdepot.depot_name "
				+ " FROM "
				+ "    bus_division bdiv "
				+ " INNER JOIN bus_depot bdepot ON "
				+ "    bdiv.bus_depot_id = bdepot.bus_depot_id ");
		List<BusDivisionVo.Response> datas = this.jdbcTemplate.query(sql.toString(), params.toArray(), dataRowmapper);
		return datas;
	}

	private RowMapper<BusDivisionVo.Response> dataRowmapper = new RowMapper<BusDivisionVo.Response>() {
		@Override
		public BusDivisionVo.Response mapRow(ResultSet rs, int arg1) throws SQLException {
			BusDivisionVo.Response vo = new BusDivisionVo.Response();
			vo.setBusDivisionId(rs.getLong("bus_division_id"));
			vo.setBusDivisionNo(rs.getLong("bus_division_no"));
			vo.setBusDivisionName(rs.getString("bus_division_name"));
			vo.setBmtaZone(rs.getLong("bmta_zone"));
			vo.setCreateDate(rs.getString("create_date"));
			vo.setUpdateDate(rs.getNString("update_date"));
			vo.setDepotName(rs.getString("depot_name"));
			return vo;
		}
	};
}
