package UTCC.project.work.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.framework.utils.UserLoginUtil;
import UTCC.project.employee.module.Employee;
import UTCC.project.employee.repo.jpa.EmployeeRepo;
import UTCC.project.work.model.BusVehicle;
import UTCC.project.work.model.Worksheet;
import UTCC.project.work.repositories.BusVehicleRepository;
import UTCC.project.work.repositories.WorksheetRepository;
import UTCC.project.work.vo.WorksheetVo;

@Service
public class WorksheetService {
	
	@Autowired
	private WorksheetRepository worksheetRepository;
	
	
	@Autowired
	private BusVehicleRepository busVehicleRepository;
	
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	public void saveFrom(WorksheetVo.Request req) {
	    BusVehicle busVehicle = busVehicleRepository.findByBusVehiclePlateNo(req.getBusVehiclePlateNo());
	    if (busVehicle == null) {
	        return;
	    }
	    Employee driver = getAndUpdateEmployeeStatus(req.getWorksheetDriver(), "UNAVAILABLE");
	    Employee fareCollector = getAndUpdateEmployeeStatus(req.getWorksheetFarecollect(), "UNAVAILABLE");

	    if (driver != null && fareCollector != null) {
	        Worksheet worksheet = new Worksheet();
	        worksheet.setWorksheetDate(req.getWorksheetDate()); 
	        worksheet.setWorksheetTimeBegin(req.getWorksheetTimeBegin());
	        worksheet.setBusVehiclePlateNo(req.getBusVehiclePlateNo());
	        worksheet.setWorksheetDispatcher(UserLoginUtil.getUsername());
	        worksheet.setWorksheetDriver(req.getWorksheetDriver());
	        worksheet.setWorksheetFarecollect(req.getWorksheetFarecollect());
	        worksheet.setWorksheetStatus("IN_PROGRESS");
	        worksheet.setCreateBy(UserLoginUtil.getUsername());
	        worksheet.setBusVehicleId(busVehicle.getBusVehicleId());
	        worksheet.setBusLinesId(busVehicle.getBusLinesId());
	        worksheet.setBusDivisionId(busVehicle.getBusDivisionId());
	        worksheet.setCreateDate(LocalDateTime.now());
	        worksheetRepository.save(worksheet);
	        busVehicle.setBusVehicleStatus("UNAVAILABLE");
	        busVehicleRepository.save(busVehicle);
	    }
	}

	private Employee getAndUpdateEmployeeStatus(String username, String status) {
	    Employee employee = employeeRepo.findByUsername(username);
	    
	    if (employee != null) {
	        employee.setEmployeeStatus(status);
	        employeeRepo.save(employee);
	    }
	    
	    return employee;
	}

	
	
	public List<Worksheet> getList(String status,WorksheetVo.Request data){
		if(data.getWorksheetId() != null) {
			return (List<Worksheet>) worksheetRepository.findByStatus(status,data.getWorksheetId());
		}
		if(data.getWorksheetDate() != null) {
			
			return (List<Worksheet>) worksheetRepository.findByStatus(status,data.getWorksheetDate(),data.getWorksheetDate().toLocalDate().atTime(LocalTime.MAX).truncatedTo(ChronoUnit.SECONDS));
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
