package UTCC.project.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.framework.utils.UserLoginUtil;
import UTCC.project.work.dao.DriverDao;
import UTCC.project.work.vo.DriverVo;

@Service
public class DriverService {

	
	@Autowired
	private DriverDao driverDao;
	

	public List<DriverVo.Response> getDataFarecollectProgress(){
		return driverDao.getDataFarecollect(UserLoginUtil.getUsername(),"IN_PROGRESS");
	}
	
	public List<DriverVo.Response> getDataFarecollectSuccess(){
		return driverDao.getDataFarecollect(UserLoginUtil.getUsername(),"SUCCESS");
	}
	
	public List<DriverVo.Response> getDataDriverProgress(){
		return driverDao.getDataDriver(UserLoginUtil.getUsername(),"IN_PROGRESS");
	}
		
	public List<DriverVo.Response> getDataDriverSuccess(){
		return driverDao.getDataDriver(UserLoginUtil.getUsername(),"SUCCESS");
	}
		
}
