package UTCC.framework.security.vo;

import UTCC.project.user.vo.GetRoleRes;
import lombok.Data;

@Data
public class JwtResponse {
	private final String jwttoken;
	private final String username;
	private GetRoleRes role;
	private Long employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String prefix;
	private String phoneNumber;
	private String employeeCode;
	private String createDate;
	private String roleCode;
	private String position;
}
