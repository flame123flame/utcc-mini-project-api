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
	
	public List<DriverVo.Response> getDataDriver(String type,String status) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM  worksheet join  bus_vehicle  on  worksheet.bus_vehicle_plate_no = bus_vehicle.bus_vehicle_plate_no   ");
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
	
	
	
	
	public List<DriverVo.Response> getDataFarecollect(String type,String status) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT\n"
				+ "	*\n"
				+ "FROM\n"
				+ "	worksheet\n"
				+ "join bus_vehicle on\n"
				+ "	worksheet.bus_vehicle_plate_no = bus_vehicle.bus_vehicle_plate_no\n"
				+ "	join bus_division on bus_division.bus_division_id  = worksheet.bus_division_id \n"
				+ "	JOIN bus_lines  on bus_lines.bus_lines_id  = worksheet.bus_lines_id \n"
				+ "   ");

			sql.append(" WHERE worksheet_farecollect = ? AND worksheet.worksheet_status = ? ");
			params.add(UserLoginUtil.getUsername());
			params.add(status);
			
			List<DriverVo.Response> data = commonJdbcTemplate.executeQuery(sql.toString(), params.toArray(),
					BeanPropertyRowMapper.newInstance(DriverVo.Response.class));
			
		return data;
	}
	
	private RowMapper<DriverVo.Response> dataFarecollecteRowmapper = new RowMapper<DriverVo.Response>() {
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
	
	
	
	
}
