package UTCC.project.user.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RegisterUserReq {
//	@NotBlank
	private String username;
//	@NotBlank
	private String password;
//	@NotBlank
	private String confirmPassword;
//	@NotBlank
	private String pin;
	private LocalDate createDate;
	private String roleCode;
	private Long employeeId;
	private String employeeCode;
	private String firstName;
	private String lastName;
	private String prefix;
	private String email;
	private String phoneNumber;
	private String position;
}
