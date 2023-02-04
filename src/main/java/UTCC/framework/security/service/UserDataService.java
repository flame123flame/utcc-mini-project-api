package UTCC.framework.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.framework.security.vo.UserDetailResponse;
import UTCC.project.employee.module.Employee;
import UTCC.project.employee.repo.jpa.EmployeeRepo;
import UTCC.project.user.model.FwUser;
import UTCC.project.user.repo.FwUserRepo;
import UTCC.project.user.service.RoleService;
import UTCC.project.user.vo.GetRoleRes;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDataService {
	
	@Autowired
	private FwUserRepo fwUserRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private RoleService roleService;
	
	public UserDetailResponse setResponse(String username) {
		UserDetailResponse res = new UserDetailResponse(username);
		Employee employee = employeeRepo.findByUsername(username);
		if (employee != null) {
			res.setPhoneNumber(employee.getPhoneNumber());
			res.setPosition(employee.getPosition());
			res.setEmployeeCode(employee.getEmployeeCode());
			res.setEmail(employee.getEmail());
		}

		try {
			FwUser user = fwUserRepo.findByUsername(username);
			GetRoleRes role = roleService.getRoleByRoleCode(user.getRoleCode());
			res.setRole(role);
		} catch (Exception e) {
			log.error("Login::JwtUserDetailsService::setResponse", e.getMessage());
		}
		return res;
	}
}
