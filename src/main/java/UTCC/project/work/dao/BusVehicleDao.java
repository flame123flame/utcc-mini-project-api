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
import UTCC.project.employee.module.Employee;
import UTCC.project.employee.repo.jpa.EmployeeRepo;
import UTCC.project.work.vo.BusLinesVo;
import UTCC.project.work.vo.BusVehicleVo;

@Repository
public class BusVehicleDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	
	public List<BusVehicleVo.ResponseTicket> getDataByWorksheetId(long worksheetId) {
	    String sql = "SELECT * " +
	                 "FROM worksheet ws " +
	                 "JOIN bus_vehicle bv ON ws.bus_vehicle_id = bv.bus_vehicle_id " +
	                 "JOIN type_h_fare thf ON bv.bus_type_id = thf.type_id " +
	                 "JOIN fare f ON thf.fare_id = f.fare_id " +
	                 "WHERE ws.worksheet_id = ? ";

	    return commonJdbcTemplate.executeQuery(
	        sql,
	        new Object[]{worksheetId},
	        BeanPropertyRowMapper.newInstance(BusVehicleVo.ResponseTicket.class)
	    );
	}

	

	public List<BusVehicleVo.Response> getDropdownListBusVehicle() {
		Employee employee = employeeRepo.findByUsername("100100");
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM  bus_vehicle  bv  join bus_type  ty  on bv.bus_type_id  = ty.bus_type_id where   bv.bus_lines_id = ? and bv.bus_vehicle_status = 'AVAILABLE' ");
		params.add(employee.getBuslinesId());
//		UserLoginUtil.getCurrentUserBean().getEmployee().getBuslinesId();
		System.out.println(" = = == = = " + employee.getBuslinesId());
		List<BusVehicleVo.Response> datas = this.jdbcTemplate.query(sql.toString(), params.toArray(),
				dataApproveRowmapper);
		return datas;
	}

	private RowMapper<BusVehicleVo.Response> dataApproveRowmapper = new RowMapper<BusVehicleVo.Response>() {
		@Override
		public BusVehicleVo.Response mapRow(ResultSet rs, int arg1) throws SQLException {
			BusVehicleVo.Response vo = new BusVehicleVo.Response();
			vo.setBusVehicleNumber(rs.getString("bus_vehicle_number"));
			vo.setBusVehiclePlateNo(rs.getString("bus_vehicle_plate_no"));
			vo.setBusVehiclePlateProv(rs.getString("bus_vehicle_plate_prov"));
			vo.setBusTypeName(rs.getString("bus_type_name"));
			return vo;
		}
	};

	public List<BusVehicleVo.Response> getListBusVehicle() {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT "
				+ "    bv.*, bl.bus_lines_no , bus_t.bus_type_name ,bd.* "
				+ "FROM "
				+ "    bus_vehicle bv "
				+ "INNER JOIN "
				+ "    bus_lines bl ON bv.bus_lines_id = bl.bus_lines_id "
				+ "INNER JOIN "
				+ "    bus_type bus_t ON bv.bus_type_id = bus_t.bus_type_id "
				+ "INNER JOIN "
				+ "    bus_division bd ON bv.bus_division_id = bd.bus_division_id   ");

		List<BusVehicleVo.Response> datas = this.jdbcTemplate.query(sql.toString(), params.toArray(), dataRowmapper);
		return datas;
	}

	private RowMapper<BusVehicleVo.Response> dataRowmapper = new RowMapper<BusVehicleVo.Response>() {
		@Override
		public BusVehicleVo.Response mapRow(ResultSet rs, int arg1) throws SQLException {
			BusVehicleVo.Response vo = new BusVehicleVo.Response();
			vo.setBusVehicleNumber(rs.getString("bus_vehicle_number"));
			vo.setBusVehiclePlateNo(rs.getString("bus_vehicle_plate_no"));
			vo.setBusVehiclePlateProv(rs.getString("bus_vehicle_plate_prov"));
			vo.setBusTypeName(rs.getString("bus_type_name"));
			vo.setBusLinesNo(rs.getString("bus_lines_no"));
			vo.setBusDivisionName(rs.getString("bus_division_name"));
			vo.setBusDivisionId(rs.getLong("bus_division_id"));
			vo.setBusLinesId(rs.getLong("bus_lines_id"));
			vo.setBusTypeId(rs.getLong("bus_Type_id"));
			vo.setBusVehicleStatus(rs.getString("bus_vehicle_status"));
			vo.setBusVehicleId(rs.getLong("bus_vehicle_id"));
			return vo;
		}
	};
	
	

}
