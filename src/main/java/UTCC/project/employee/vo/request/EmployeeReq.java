package UTCC.project.employee.vo.request;

import lombok.Data;

@Data
public class EmployeeReq {
	private Long employeeId;
	private String password;
	private String employeeCode;
	private String username;
	private String prefix;
	private String firstName;
	private String lastName;
	private String lastNameEn;
	private String email;
	private String image64;
	private String phoneNumber;
	private String position;
	private String roleCode;
	private String employeeShift;
	private String employeeStatus;
	private Long buslinesId;
	private Long busTerminalId;
	private String userType;
	
}
