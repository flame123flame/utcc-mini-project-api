package UTCC.framework.security.vo;

import UTCC.project.employee.vo.response.EmployeeRes;
import UTCC.project.user.vo.GetRoleRes;
import lombok.Data;

@Data
public class UserDetailResponse extends EmployeeRes {
	private final String username;
	private String position;
	private String firstNameTh;
	private String lastNameTh;
	private GetRoleRes role;
}
