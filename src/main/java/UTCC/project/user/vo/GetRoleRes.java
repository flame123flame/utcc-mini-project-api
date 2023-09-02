package UTCC.project.user.vo;

import lombok.Data;

@Data
public class GetRoleRes {
	private Long fwRoleId;
	private String roleCode;
	private String roleName;
	private String[] menuList;
	private String roleDescription;
	private String createBy;
	private String createDate;
	private String updateBy;
	private String updateDate;
	private String platform;
}
