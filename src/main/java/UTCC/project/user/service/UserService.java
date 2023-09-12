package UTCC.project.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import UTCC.framework.security.authorization.TrippleDes;
import UTCC.framework.utils.ConvertDateUtils;
import UTCC.framework.utils.UserLoginUtil;
import UTCC.project.employee.module.Employee;
import UTCC.project.employee.repo.jpa.EmployeeRepo;
import UTCC.project.employee.service.EmployeeService;
import UTCC.project.employee.vo.response.EmployeeRes;
import UTCC.project.user.constants.UserConstant;
import UTCC.project.user.model.FwUser;
import UTCC.project.user.repo.FwRoleRepo;
import UTCC.project.user.repo.FwUserRepo;
import UTCC.project.user.vo.RegisterUserReq;
import UTCC.project.user.vo.RegisterUserRes;

@Service
public class UserService {

	@Autowired
	private FwUserRepo fwUserRepo;
	
	@Autowired
	private FwRoleRepo fwRoleRepo;
	
	
	@Autowired
	private EmployeeRepo employeeRepo;
	

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	EmployeeService employeeService;
	
	
	@Transactional
	public String register(RegisterUserReq req) throws Exception {
		FwUser fwUser = fwUserRepo.findByUsername(req.getUsername());

		// validate req
		if (fwUser != null)
			return UserConstant.STATUS.DUPICATE_USER;
		if (StringUtils.isBlank(req.getPassword()) || StringUtils.isBlank(req.getConfirmPassword()))
			return UserConstant.STATUS.WRONG_PASSWORD;
		if (!req.getPassword().equals(req.getConfirmPassword()))
			return UserConstant.STATUS.WRONG_PASSWORD;
		if(StringUtils.isBlank(req.getRoleCode()))
			return UserConstant.STATUS.NULL;

		// save
		fwUser = new FwUser();
		fwUser.setUsername(req.getUsername().trim().toLowerCase());
		fwUser.setPassword(bcryptEncoder.encode(req.getPassword().trim()));
		fwUser.setRoleCode(req.getRoleCode());
		fwUser.setCreateBy(UserLoginUtil.getUsername());
		fwUserRepo.save(fwUser);
		employeeService.save(req);
		return UserConstant.STATUS.SUCCESS;
	}
	
	public List<RegisterUserRes> getListUser(RegisterUserReq req) {
		List<RegisterUserRes> dataRes = new ArrayList<RegisterUserRes>();
		RegisterUserRes userSet;
		Employee employee;
		List<FwUser> dataUserFind = fwUserRepo.findUser();
		for(FwUser userGet:dataUserFind) {
			
			employee  = employeeRepo.findByUsername(userGet.getUsername());
//			fwRoleRepo.findByRoleCode(userGet.getRoleCode());
			userSet = new RegisterUserRes();
			userSet.setEmployeeId(employee.getEmployeeId());
			userSet.setFirstName(employee.getFirstName());
			userSet.setLastName(employee.getLastName());
			userSet.setEmployeeCode(employee.getEmployeeCode());
			userSet.setId(userGet.getId());
			userSet.setFullName(employee.getFirstName() + ' ' + employee.getLastName() );	
			userSet.setUsername(userGet.getUsername());
			userSet.setRoleCode(userGet.getRoleCode());
			userSet.setPosition(employee.getPosition());
			userSet.setPrefix(employee.getPrefix());
			userSet.setEmail(employee.getEmail());
			userSet.setPhoneNumber(employee.getPhoneNumber());
			userSet.setCreateDate(userGet.getCreateDate().toString());
			if(fwRoleRepo.findByRoleCode(userGet.getRoleCode()) != null)
			userSet.setPlatform(fwRoleRepo.findByRoleCode(userGet.getRoleCode()).getPlatform());
			dataRes.add(userSet);
			
		}
		return dataRes;
	}
	
	
	public void setPin(RegisterUserReq req) throws Exception {
		  TrippleDes td = new TrippleDes();
	      String encrypt = td.encrypt(req.getPin());
	      FwUser data =  fwUserRepo.findByUsername(req.getUsername());
	      data.setPin(encrypt);
	      fwUserRepo.save(data);
	}
	
	public String validatePin(RegisterUserReq  req) throws Exception {
	    TrippleDes td = new TrippleDes();
	    FwUser user = fwUserRepo.findByUsername(req.getUsername());
	    if(user.getPin() == null || "".equals( user.getPin().trim())) {
	    	return "NOT_DATA";
	    }
	    String decryptedPin = td.decrypt(user.getPin());
	    return req.getPin().equals(decryptedPin) ? "SUCCESS" : "NOT_SUCCESS";
	}


	@Transactional
	public void deleteUsername(String username) {
		fwUserRepo.deleteByUsername(username);
	}
	@Transactional
	public RegisterUserRes getById(String id) {
		return employeeService.getById(id);
	}
	
	@Transactional
	public EmployeeRes getUsernameById(String username) {
		return employeeService.getByUsername(username);
	}
	public List<FwUser> getNewsDetail()
	{
		List<FwUser> dataRes = fwUserRepo.findAll();
		return dataRes;
	}
}
