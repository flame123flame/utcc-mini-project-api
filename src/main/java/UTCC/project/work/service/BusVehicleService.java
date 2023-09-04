package UTCC.project.work.service;

import java.time.LocalDateTime;
import java.util.Date;
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
		BusVehicle data = new BusVehicle();
		data.setBusVehicleNumber(req.getBusVehicleNumber());
		data.setBusVehiclePlateNo(req.getBusVehiclePlateNo());
		data.setBusVehiclePlateProv(req.getBusVehiclePlateProv());
		data.setBusLinesId(1);
		data.setBusTypeId(2);
		data.setBusDivisionId(1);
		data.setCreateDate(LocalDateTime.now());
		data.setCreateBy(UserLoginUtil.getUsername());
		busVehicleRepository.save(data);
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
