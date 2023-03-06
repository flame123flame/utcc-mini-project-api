package UTCC.project.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.project.work.dao.BusVehicleDao;
import UTCC.project.work.model.BusVehicle;
import UTCC.project.work.repositories.BusVehicleRepository;
import UTCC.project.work.vo.BusVehicleVo;

@Service
public class BusVehicleService {
	
	@Autowired
	private BusVehicleRepository busVehicleRepository;
	
	@Autowired
	private BusVehicleDao busVehicleDao;
	
	public List<BusVehicle> getDropdownListBusVehicle(){
		return (List<BusVehicle>) busVehicleRepository.findAll();
	}
	
	public List<BusVehicleVo.Response> getDropdownList(){
		return busVehicleDao.getDropdownListBusVehicle();
	}
	
}
