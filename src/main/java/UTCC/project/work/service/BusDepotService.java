package UTCC.project.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		busDepotRepository.save(busDepot);
	}
	
}
