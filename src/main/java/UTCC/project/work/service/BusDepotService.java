package UTCC.project.work.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.framework.utils.UserLoginUtil;
import UTCC.project.work.model.BusDepot;
import UTCC.project.work.repositories.BusDepotRepository;
import UTCC.project.work.vo.BusDepotVo;



@Service
public class BusDepotService {

	@Autowired
	private BusDepotRepository busDepotRepository;
	
	public List<BusDepot> getBusDepot(){
		return (List<BusDepot>) busDepotRepository.findAll();
	}

	public void save(BusDepotVo.Request req) {
		BusDepot busDepot = new BusDepot();
		busDepot.setDepotName(req.getDepotName());
		busDepot.setCreateDate(LocalDateTime.now());
		busDepot.setCreateBy(UserLoginUtil.getUsername());
		busDepotRepository.save(busDepot);
	}
	
	public void edit(BusDepotVo.Request req) {
		BusDepot busDepot = busDepotRepository.findById(req.getBusDepotId()).get();
		busDepot.setDepotName(req.getDepotName());
		busDepot.setUpdateDate(LocalDateTime.now());
		busDepot.setUpdateBy(UserLoginUtil.getUsername());
		busDepotRepository.save(busDepot);
	}
	
}
