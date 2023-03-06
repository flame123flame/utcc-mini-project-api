package UTCC.project.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.framework.utils.UserLoginUtil;
import UTCC.project.work.model.Worksheet;
import UTCC.project.work.repositories.WorksheetRepository;
import UTCC.project.work.vo.WorksheetVo;

@Service
public class WorksheetService {
	
	@Autowired
	private WorksheetRepository worksheetRepository;
	
	
	public void saveFrom(WorksheetVo.Request req) {
		Worksheet worksheet = new Worksheet();
		worksheet.setWorksheetDate(req.getWorksheetDate()); 
		worksheet.setWorksheetTimeBegin(req.getWorksheetTimeBegin());
		worksheet.setBusVehiclePlateNo(req.getBusVehiclePlateNo());
		worksheet.setWorksheetDispatcher(UserLoginUtil.getUsername());
		worksheet.setWorksheetDriver(req.getWorksheetDriver());
		worksheet.setWorksheetFarecollect(req.getWorksheetFarecollect());
		worksheet.setCreateBy(UserLoginUtil.getUsername());
		worksheetRepository.save(worksheet);
	}
	
	public List<Worksheet> getList(){
		return (List<Worksheet>) worksheetRepository.findAll();
	}
	
	
	
}
