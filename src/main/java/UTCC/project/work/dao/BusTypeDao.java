package UTCC.project.work.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import UTCC.project.work.vo.BusTypeVo;

@Repository
public class BusTypeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<BusTypeVo.Response> getList() {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT "
				+ "    btype.bus_type_id,  "
				+ "    btype.bus_type_name, "
				+ "    btype.create_date, "
				+ "    btype.update_date, "
				+ "    thf.type_h_fare_id, "
				+ "    fare.fare_id, "
				+ "    fare.fare_value, "
				+ "    fare.fare_desc "
				+ " FROM "
				+ "    bus_type btype "
				+ " INNER JOIN type_h_fare thf ON "
				+ "    btype.bus_type_id = thf.type_id "
				+ " INNER JOIN fare ON "
				+ "    thf.fare_id = fare.fare_id; ");
		List<BusTypeVo.Response> datas = this.jdbcTemplate.query(sql.toString(), params.toArray(), dataRowmapper);
		return datas;
	}

	private RowMapper<BusTypeVo.Response> dataRowmapper = new RowMapper<BusTypeVo.Response>() {
		@Override
		public BusTypeVo.Response mapRow(ResultSet rs, int arg1) throws SQLException {
			BusTypeVo.Response vo = new BusTypeVo.Response();
			vo.setBusTypeId(rs.getLong("bus_type_id"));
			vo.setBusTypeName(rs.getString("bus_type_name"));
			vo.setCreateDate(rs.getString("create_date"));
			vo.setUpdateDate(rs.getString("update_date"));
			vo.setTypeHfareId(rs.getLong("type_h_fare_id"));
			vo.setFareValue(rs.getBigDecimal("fare_value"));
			vo.setFareDesc(rs.getString("fare_desc"));
			return vo;
		}
	};

}
