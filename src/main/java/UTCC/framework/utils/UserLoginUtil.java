package UTCC.framework.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import UTCC.framework.model.UserDetailModel;
import UTCC.project.employee.repo.dao.EmployeeDao;



public class UserLoginUtil {
	@Autowired
	static EmployeeDao employeeDao;

	public static UserDetailModel getCurrentUserBean() {
		UserDetailModel userBean = null;

		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = "";
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
			}
			userBean = new UserDetailModel(username, null);
		} else {
			String username = "NO LOGIN";
			userBean = new UserDetailModel(username, null);
		}
		return userBean;
	}

	public static String getUsername() {

		return UserLoginUtil.getCurrentUserBean().getUsername();
	}


}
