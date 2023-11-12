package UTCC.project.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.framework.constant.ConstantsWorksheetStatus;
import UTCC.project.work.dao.DriverDao;
import UTCC.project.work.vo.DriverVo;

@Service
public class DriverService {

	
	@Autowired
	private DriverDao driverDao;
	

	public List<DriverVo.Response> getDataFarecollectProgress(){
		return driverDao.getDataFarecollect(ConstantsWorksheetStatus.IN_PROGRESS);
	}
	
	public List<DriverVo.Response> getDataSuperVisor(String status){
		return driverDao.getDataSuperVisor(status);
	}
	
	public List<DriverVo.Response> getDataFarecollectSuccess(){
		return driverDao.getDataFarecollectOrEnd(ConstantsWorksheetStatus.SUCCESS,ConstantsWorksheetStatus.END_PROGRESS);
	}
	
	public List<DriverVo.Response> getDataDriverProgress(){
		return driverDao.getDataDriver(ConstantsWorksheetStatus.IN_PROGRESS);
	}
		
	public List<DriverVo.Response> getDataDriverSuccess(){
		return driverDao.getDataDriverOrEnd(ConstantsWorksheetStatus.SUCCESS,ConstantsWorksheetStatus.END_PROGRESS);
	}
		
}
