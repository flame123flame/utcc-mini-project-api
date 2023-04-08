package UTCC.project.work.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
		worksheet.setWorksheetStatus("IN_PROGRESS");		
		worksheet.setCreateBy(UserLoginUtil.getUsername());
		worksheetRepository.save(worksheet);
	}
	
	public List<Worksheet> getList(String status,WorksheetVo.Request data){
		if(data.getWorksheetId() != null) {
			return (List<Worksheet>) worksheetRepository.findByStatus(status,data.getWorksheetId());
		}
		if(data.getWorksheetDate() != null) {
			return (List<Worksheet>) worksheetRepository.findByStatus(status,data.getWorksheetDate());
		}
		return (List<Worksheet>) worksheetRepository.findByStatus(status);
	}
	
	public void upDateStatus(Long id) {
		Worksheet data = worksheetRepository.findById(id).get();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		Date date = new Date();
		data.setWorksheetStatus("SUCCESS");
		data.setWorksheetTimeEnd(formatter.format(date));
		data.setWorksheetBuslinesManager(UserLoginUtil.getUsername());
		worksheetRepository.save(data);
	}
	
	
	
}
