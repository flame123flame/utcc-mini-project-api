package UTCC.project.employee.vo.response;

import lombok.Data;

@Data
public class EmployeeRes {

	private Long employeeId;
	private String username;
	private String firstName;
	private String lastName;
	private String fullName;
	private String email;
	private String phoneNumber;
	private String employeeCode;
	private String createDate;
	private String roleCode;
	private String position;

}
