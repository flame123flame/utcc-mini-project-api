package UTCC.project.employee.service;

import java.util.Date;
import java.util.List;

//import org.apache.catalina.User;
//import org.apache.commons.lang3.StringUtils;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import UTCC.framework.model.DataTableResponse;
import UTCC.framework.model.DatatableRequest;
import UTCC.framework.utils.ConvertDateUtils;
import UTCC.framework.utils.UserLoginUtil;
import UTCC.project.common.service.GenerateRandomString;
import UTCC.project.employee.module.Employee;
import UTCC.project.employee.repo.dao.EmployeeDao;
import UTCC.project.employee.repo.jpa.EmployeeRepo;
import UTCC.project.employee.vo.request.EmployeeReq;
import UTCC.project.employee.vo.response.EmployeeRes;
import UTCC.project.user.model.FwUser;
import UTCC.project.user.repo.FwUserRepo;
import UTCC.project.user.service.UserService;
import UTCC.project.user.vo.RegisterUserReq;
import UTCC.project.user.vo.RegisterUserRes;


@Service
public class EmployeeService {
	
	@Autowired
	EmployeeDao employeeDao;

	@Autowired
	EmployeeRepo employeeRepo;
	@Autowired
	FwUserRepo fwUserRepo;
	@Autowired
	UserService userService;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	

	public List<EmployeeRes> getNewEmployee(EmployeeReq req) {
		return employeeDao.getNewEmployee(req);
	}

	
	public Employee getById(EmployeeReq req) {
		return employeeRepo.findById(req.getEmployeeId()).get();
	}
	
	
	
	public EmployeeRes getEmployee(EmployeeReq req) {
		return employeeDao.getEmployee(req).get(0);
	}
	
	

	public DataTableResponse<EmployeeRes> pagiante(DatatableRequest req) {
		DataTableResponse<EmployeeRes> paginateData = employeeDao.paginate(req);
		DataTableResponse<EmployeeRes> dataTable = new DataTableResponse<>();
		List<EmployeeRes> data = paginateData.getData();
		dataTable.setRecordsTotal(paginateData.getRecordsTotal());
		dataTable.setDraw(paginateData.getDraw());
		dataTable.setData(data);
		dataTable.setPage(req.getPage());
		return paginateData;
	}



	public Employee save(RegisterUserReq req) {
		Employee data = new Employee();
		data.setEmployeeCode(GenerateRandomString.generate());
		data.setUsername(req.getUsername());
		data.setPrefix(req.getPrefix());
		data.setFirstName(req.getFirstName());
		data.setLastName(req.getLastName());
		data.setPosition(req.getPosition());
		data.setPhoneNumber(req.getPhoneNumber());
		data.setEmail(req.getEmail());
		data.setEmployeeShift(req.getEmployeeShift());
		data.setEmployeeStatus("AVAILABLE");
		data.setBuslinesId(req.getBuslinesId());
		data.setBusTerminalId(req.getBusTerminalId());
		data.setCreateBy(UserLoginUtil.getCurrentUserBean().getUsername());
		data.setUserType(req.getUserType());
		
		
		data.setCreateDate(new Date());
		return employeeRepo.save(data);
	}

	public Employee edit(EmployeeReq req) {
		Employee data = new Employee();
		data = employeeRepo.findByUsername(req.getUsername());
		String olduser = data.getUsername();
		data.setCreateBy(UserLoginUtil.getCurrentUserBean().getUsername());
		data.setCreateDate(new Date());
		data.setEmail(req.getEmail());
		data.setPrefix(req.getPrefix());
		data.setUserType(req.getUserType());
		data.setPhoneNumber(req.getPhoneNumber());
		data.setFirstName(req.getFirstName());
		data.setLastName(req.getLastName());
		data.setUsername(req.getUsername());
		data.setEmployeeStatus(req.getEmployeeStatus());
		data.setPosition(req.getPosition());
		data.setEmployeeShift(req.getEmployeeShift());
		data.setEmployeeStatus(req.getEmployeeStatus());
		data.setBuslinesId(req.getBuslinesId());
		data.setBusTerminalId(req.getBusTerminalId());
		FwUser user = fwUserRepo.findByUsername(olduser);
		user.setRoleCode(req.getRoleCode());
	
		fwUserRepo.save(user);
		return employeeRepo.save(data);
	}

	private ModelMapper modelMapper = new ModelMapper();

	public EmployeeRes getByUsername(String code) {
		Employee employee = employeeRepo.findByUsername(code);
		EmployeeRes employeeRes = modelMapper.map(employee, new TypeToken<EmployeeRes>() {
		}.getType());
		return employeeRes;
	}
	
	public RegisterUserRes getById(String id) {
		RegisterUserRes data = new RegisterUserRes();
		FwUser user = fwUserRepo.findById(Long.valueOf(id)).get();
		Employee employee = employeeRepo.findByUsername(user.getUsername());
		data.setUsername(user.getUsername());
		data.setEmail(employee.getEmail());
		data.setPhoneNumber(employee.getPhoneNumber());
		data.setPosition(employee.getPosition());
		data.setEmployeeCode(employee.getEmployeeCode());
		data.setRoleCode(user.getRoleCode());
		return data;
	}

	public EmployeeRes getByCode(String code) {
		Employee employee = employeeRepo.findByEmployeeCode(code);
		EmployeeRes employeeRes = modelMapper.map(employee, new TypeToken<EmployeeRes>() {
		}.getType());
		return employeeRes;
	}
	public void delete(EmployeeReq req) {
		Employee data = new Employee();
		data = employeeRepo.findByEmployeeCode(req.getEmployeeCode());
		FwUser user = fwUserRepo.findByUsername(req.getUsername());
		employeeRepo.delete(data);
		fwUserRepo.delete(user);
	}
	
	public List<Employee> getAllDetail()
	{
		List<Employee> dataRes = employeeRepo.findAll();
		return dataRes;
	}

}
