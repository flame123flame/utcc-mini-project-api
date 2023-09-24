package UTCC.project.employee.repo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import UTCC.framework.model.DataTableResponse;
import UTCC.framework.model.DatatableRequest;
import UTCC.framework.utils.CommonJdbcTemplate;
import UTCC.framework.utils.ConvertDateUtils;
import UTCC.framework.utils.DatatableUtils;
import UTCC.framework.utils.UserLoginUtil;
import UTCC.project.employee.module.Employee;
import UTCC.project.employee.vo.request.EmployeeReq;
import UTCC.project.employee.vo.response.EmployeeRes;


@Repository
public class EmployeeDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public Employee getByUsername() {
		StringBuilder stringBuilder = new StringBuilder();

		List<Object> params = new ArrayList<>(); // not have IMG
		stringBuilder.append(" select top(1) [employee_id] ,[employee_code] "
				+ "     ,[remark] ,[image_id] ,[email]  "
				+ "      ,[username]  "
				+ "     ,[phone_number] ,[create_date]  "
				+ "      ,[create_by] ,[update_date] ,[update_by] ,[is_delete]  "
				+ "      ,[first_name_th] ,[last_name_th]  from employee");
		stringBuilder.append(" where is_delete = 'N' ");
		stringBuilder.append(" and username  = ?");
		params.add(UserLoginUtil.getUsername());
		List<Employee> data = new ArrayList<Employee>();
		data = commonJdbcTemplate.executeQuery(stringBuilder.toString(), params.toArray(),
				BeanPropertyRowMapper.newInstance(Employee.class));
		Employee res = new Employee();
		if (null != data) {
			if (data.size() > 0) {
				res = data.get(0);
				return res;
			} else {
				return res;
			}
		} else {
			return res;
		}
	}



	public DataTableResponse<EmployeeRes> paginate(DatatableRequest req) {
		DataTableResponse<EmployeeRes> dataTable = new DataTableResponse<EmployeeRes>();
		StringBuilder sqlBuilder = new StringBuilder(" select ");
		String from = " employee A  left join fw_user D  on D.username = A.username ";
		sqlBuilder.append(
				"  A.employee_id,A.first_name_th,A.last_name_th,A.email,A.phone_number,A.remark,");
		
		sqlBuilder.append(" A.username,A.employee_code, A.email, A.create_date, D.role_code");
		sqlBuilder.append(" from " + from);

		String sqlCount = DatatableUtils.customQueryWithPageCount(from.toString(), req.getFilter());
		String sqlData = DatatableUtils.customQueryWithPage(sqlBuilder.toString(), req.getPage(), req.getLength(),
				req.getFilter(), null, " a.employee_id desc", req.getSort());
		List<Object> params = new ArrayList<>();
		Integer count = commonJdbcTemplate.executeQueryForObject(sqlCount, params.toArray(), Integer.class);
		List<EmployeeRes> data = commonJdbcTemplate.executeQuery(sqlData, params.toArray(),
				BeanPropertyRowMapper.newInstance(EmployeeRes.class));
		dataTable.setData(data);
		dataTable.setRecordsTotal(count);
		return dataTable;
	}




	public List<EmployeeRes> getEmployee(EmployeeReq req) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder(" select ");
		sqlBuilder.append("  a.*,c.role_description   FROM [employee] a   left join fw_user b "
				+ "  on a.username = b.username    left join fw_role c   on c.role_code = b.role_code "
				+ "  where 1=1 ");
		if (StringUtils.isNotBlank(req.getEmployeeCode())) {
			sqlBuilder.append(" and a.employee_code =? ");
			params.add((req.getEmployeeCode().trim()));
		}
		if (StringUtils.isNotBlank(req.getUsername())) {
			sqlBuilder.append(" and a.username =? ");
			params.add((req.getUsername().trim()));
		}
		List<EmployeeRes> dataRes = commonJdbcTemplate.executeQuery(sqlBuilder.toString(), params.toArray(),
				BeanPropertyRowMapper.newInstance(EmployeeRes.class));
		return dataRes;
	}

	public List<EmployeeRes> getNewEmployee(EmployeeReq req) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder(" select ");
		sqlBuilder.append(" username  ");
		sqlBuilder.append(" from fw_user  ");
		sqlBuilder.append(" where username not in( select username from employee ) ");
		if (StringUtils.isNotBlank(req.getUsername())) {
			sqlBuilder.append("  and username like ? ");
			params.add("%".concat(req.getUsername().trim()).concat("%"));
		}
		List<EmployeeRes> dataRes = jdbcTemplate.query(sqlBuilder.toString(), params.toArray(), rowMapperNewEmployee);
		return dataRes;
	}

	private RowMapper<EmployeeRes> rowMapperNewEmployee = new RowMapper<EmployeeRes>() {
		@Override
		public EmployeeRes mapRow(ResultSet rs, int arg1) throws SQLException {
			EmployeeRes vo = new EmployeeRes();
			vo.setUsername(rs.getString("username"));
			return vo;
		}
	};
	

}
