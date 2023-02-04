package UTCC.framework.model;

import UTCC.project.employee.module.Employee;
import lombok.Data;

@Data
public class UserDetailModel {
	
	private final String username;
	private final Employee employee;
}
