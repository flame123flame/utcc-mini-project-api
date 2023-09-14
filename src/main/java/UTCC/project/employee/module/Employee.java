package UTCC.project.employee.module;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class Employee implements Serializable {
	
	private static final long serialVersionUID = -7691528618295079572L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Long employeeId;
	
	@Column(name = "employee_code")
	private String employeeCode;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "create_by")
	private String createBy;
	
	@Column(name = "update_date")
	private Date updateDate;

	@Column(name = "update_by")
	private String updateBy;

	@Column(name = "is_delete")
	private String isDelete = "N";
	
	@Column(name = "prefix")
	private String prefix;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "position")
	private String position;
	
	@Column(name = "employee_shift")
	private String employeeShift;
	
	@Column(name = "employee_status")
	private String employeeStatus;
	
	@Column(name = "bus_lines_id")
	private Long buslinesId;
	
	@Column(name = "bus_terminal_id")
	private Long busTerminalId;
}
