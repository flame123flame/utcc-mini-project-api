package UTCC.project.user.vo;

import lombok.Data;

@Data
public class GetRoleRes {
	private Long fwRoleId;
	private String roleCode;
	private String roleName;
	private String[] munuList;
	private String roleDescription;
	private String createBy;
	private String createDate;
	private String updateBy;
	private String updateDate;
}
