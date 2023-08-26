package UTCC.project.work.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import UTCC.project.work.vo.BusVehicleVo;

@Repository
public class BusVehicleDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<BusVehicleVo.Response> getDropdownListBusVehicle() {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM  bus_vehicle as bv  join bus_type as ty  on bv.bus_type_id  = ty.bus_type_id   ");
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
			vo.setTypeName(rs.getString("type_name"));
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
			vo.setTypeName(rs.getString("bus_type_name"));
			vo.setBusLinesNo(rs.getString("bus_lines_no"));
			vo.setBusDivisionName(rs.getString("bus_division_name"));
			return vo;
		}
	};

}
