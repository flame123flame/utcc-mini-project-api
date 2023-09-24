package UTCC.framework.security.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import UTCC.framework.security.vo.JwtResponse;
import UTCC.project.employee.module.Employee;
import UTCC.project.employee.repo.jpa.EmployeeRepo;
import UTCC.project.user.model.FwUser;
import UTCC.project.user.repo.FwUserRepo;
import UTCC.project.user.service.RoleService;
import UTCC.project.user.vo.GetRoleRes;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private FwUserRepo fwUserRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private RoleService roleService;
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		FwUser user = fwUserRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

	public JwtResponse setResponse(String token, String username) {
		JwtResponse res = new JwtResponse(token, username);
		Employee employee = employeeRepo.findByUsername(username);
//		FwRole
		if (employee != null) {
			res.setEmployeeId(employee.getEmployeeId());
			res.setPrefix(employee.getPrefix());
			res.setFirstName(employee.getFirstName());
			res.setLastName(employee.getLastName());
			res.setPhoneNumber(employee.getPhoneNumber());
			res.setPosition(employee.getPosition());
			res.setEmployeeCode(employee.getEmployeeCode());
			res.setEmail(employee.getEmail());
			res.setBuslinesId(employee.getBuslinesId());	
			res.setBusTerminalId(employee.getBusTerminalId());
			res.setEmployeeShift(employee.getEmployeeShift());
			res.setEmployeeStatus(employee.getEmployeeStatus());
		}

		try {
			FwUser user = fwUserRepo.findByUsername(username);
			GetRoleRes role = roleService.getRoleByRoleCode(user.getRoleCode());
			String str = Arrays.toString(role.getMenuList());
			res.setRoleCode(str.replace("[", "").replace("]", "").replace(" ", ""));
			res.setPlatform(role.getPlatform());
		} catch (Exception e) {
			log.error("Login::JwtUserDetailsService::setResponse", e.getMessage());
		}
		return res;
	}



}
