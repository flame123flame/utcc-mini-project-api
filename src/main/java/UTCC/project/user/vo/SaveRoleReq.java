package UTCC.project.user.vo;

import lombok.Data;

@Data
public class SaveRoleReq {
	private Long fwRoleId;
	private String roleCode;
	private String roleName;
	private String roleDescription;
	private String menuList;
	private String platform;
}
