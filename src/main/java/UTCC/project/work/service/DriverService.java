package UTCC.project.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.project.work.dao.DriverDao;
import UTCC.project.work.vo.DriverVo;

@Service
public class DriverService {

	
	@Autowired
	private DriverDao driverDao;
	

	public List<DriverVo.Response> getDataFarecollect(){
		return driverDao.getData("farecollect");
	}
	
	public List<DriverVo.Response> getDataDriver(){
		return driverDao.getData("driver");
	}
		
		
}
