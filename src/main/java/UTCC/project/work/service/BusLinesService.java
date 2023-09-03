package UTCC.project.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.project.work.dao.BusLinesDao;
import UTCC.project.work.model.BusLines;
import UTCC.project.work.model.BuslinesHbusterminal;
import UTCC.project.work.repositories.BusLinesRepository;
import UTCC.project.work.repositories.BuslinesHbusterminalRepository;
import UTCC.project.work.vo.BusLinesVo;



@Service
public class BusLinesService {

	@Autowired
	private BusLinesDao busLinesDao;
	
	@Autowired
	private BusLinesRepository busLinesRepository;
	
	@Autowired
	private BuslinesHbusterminalRepository blHbtRepository;
	
	public List<BusLinesVo.Response> getList(){
		return busLinesDao.getList();
	}
	
	public List<BusLines> getBusLines(){
		return (List<BusLines>) busLinesRepository.findAll();
	}
	
	public void save(BusLinesVo.Request req) {
		BusLines busLines = new BusLines();
		BuslinesHbusterminal blHbt = new BuslinesHbusterminal();
		busLines.setBusLinesNo(req.getBusLinesNo());
		busLines.setBusLinesOrigin(req.getBusLinesOrigin());
		busLines.setBusLinesDestination(req.getBusLinesDestination());
		busLines.setBusLinesExpressway(req.getBusLinesExpressway());
		busLines.setBusLinesNightshift(req.getBusLinesNightshift());
		blHbt.setBusLinesId(req.getBusLinesId());
		blHbt.setBusTerminalId(req.getBusTerminalId());
		busLinesRepository.save(busLines);
		blHbtRepository.save(blHbt);
	}
	
}
