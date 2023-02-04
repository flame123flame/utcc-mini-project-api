package UTCC.project.user.vo;

import UTCC.project.employee.vo.response.EmployeeRes;
import lombok.Data;

@Data
public class RegisterUserRes extends  EmployeeRes{
	private Long id;
	private String username;
	private String roleCode;
	private String createDate;
}
