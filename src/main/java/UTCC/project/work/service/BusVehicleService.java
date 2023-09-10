package UTCC.project.work.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.framework.utils.UserLoginUtil;
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
	
	
	public void saveForm(BusVehicleVo.Request req) {
		BusVehicle busVehicle = new BusVehicle();
		busVehicle.setBusVehicleNumber(req.getBusVehicleNumber());
		busVehicle.setBusVehiclePlateNo(req.getBusVehiclePlateNo());
		busVehicle.setBusVehiclePlateProv(req.getBusVehiclePlateProv());
		busVehicle.setBusLinesId(req.getBusLinesId());
		busVehicle.setBusTypeId(req.getBusTypeId());
		busVehicle.setBusDivisionId(req.getBusDivisionId());
		busVehicle.setCreateDate(LocalDateTime.now());
		busVehicle.setCreateBy(UserLoginUtil.getUsername());
		busVehicleRepository.save(busVehicle);
	}
	
	public void deleteBusVehicle(Long id) {
		busVehicleRepository.deleteById(id);
	}
	
	public List<BusVehicle> getDropdownListBusVehicle(){
		return (List<BusVehicle>) busVehicleRepository.findAll();
	}
	
	public List<BusVehicleVo.Response> getDropdownList(){
		return busVehicleDao.getDropdownListBusVehicle();
	}
	
	public List<BusVehicleVo.Response> getList(){
		return busVehicleDao.getListBusVehicle();
	}
	
}
