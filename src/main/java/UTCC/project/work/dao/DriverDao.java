package UTCC.project.work.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import UTCC.framework.utils.UserLoginUtil;
import UTCC.project.work.vo.DriverVo;

@Repository
public class DriverDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public List<DriverVo.Response> getData(String type,String status) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM  worksheet join  bus_vehicle  on  worksheet.bus_vehicle_plate_no = bus_vehicle.bus_vehicle_plate_no   ");
		if("farecollect".equals(type)) {
			sql.append(" WHERE worksheet_farecollect = ? AND worksheet.worksheet_status = ? ");
			params.add(UserLoginUtil.getUsername());
			params.add(status);
		}else if("driver".equals(type)) {
			sql.append(" WHERE worksheet_driver = ?  AND worksheet.worksheet_status = ? ");
			params.add(UserLoginUtil.getUsername());
			params.add(status);
		}
		
		System.out.println(UserLoginUtil.getUsername());
		System.out.println(status);
		System.out.println(sql.toString());

		List<DriverVo.Response> datas = this.jdbcTemplate.query(sql.toString(), params.toArray(), dataApproveRowmapper);
		return datas;
	}
	
	private RowMapper<DriverVo.Response> dataApproveRowmapper = new RowMapper<DriverVo.Response>() {
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
