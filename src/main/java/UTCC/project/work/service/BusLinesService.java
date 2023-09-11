package UTCC.project.work.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.framework.utils.UserLoginUtil;
import UTCC.project.work.dao.BusLinesDao;
import UTCC.project.work.model.BusLines;
import UTCC.project.work.model.BuslinesHbusterminal;
import UTCC.project.work.repositories.BusLinesRepository;
import UTCC.project.work.repositories.BuslinesHbusterminalRepository;
import UTCC.project.work.vo.BusLinesVo;
import UTCC.project.work.vo.BusTerminalVo;



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
		busLines.setBusLinesNo(req.getBusLinesNo());
		busLines.setBusLinesOrigin(req.getBusLinesOrigin());
		busLines.setBusLinesDestination(req.getBusLinesDestination());
		busLines.setBusLinesExpressway(req.getBusLinesExpressway());
		busLines.setBusLinesNightshift(req.getBusLinesNightshift());
		busLines.setCreateDate(LocalDateTime.now());
		busLines.setCreateBy(UserLoginUtil.getUsername());
		long id = busLinesRepository.save(busLines).getBusLinesId();
		BuslinesHbusterminal blHbt = null;
		for(BusTerminalVo.Request item : req.getListDetail()) {
			blHbt = new BuslinesHbusterminal();
			blHbt.setBusLinesId(id);
			blHbt.setBusTerminalId(item.getBusTerminalId());
			blHbt.setCreateDate(LocalDateTime.now());
			blHbt.setCreateBy(UserLoginUtil.getUsername());
			blHbtRepository.save(blHbt);
		}
	}
	
	public void edit(BusLinesVo.Request req) {
		BusLines busLines = busLinesRepository.findById(req.getBusLinesId()).get();
		busLines.setBusLinesNo(req.getBusLinesNo());
		busLines.setBusLinesOrigin(req.getBusLinesOrigin());
		busLines.setBusLinesDestination(req.getBusLinesDestination());
		busLines.setBusLinesExpressway(req.getBusLinesExpressway());
		busLines.setBusLinesNightshift(req.getBusLinesNightshift());
		busLines.setCreateDate(LocalDateTime.now());
		busLines.setCreateBy(UserLoginUtil.getUsername());
		long id = busLinesRepository.save(busLines).getBusLinesId();
		BuslinesHbusterminal blHbt = null;
		for(BusTerminalVo.Request item : req.getListDetail()) {
			blHbt = new BuslinesHbusterminal();
			blHbt.setBusLinesId(id);
			blHbt.setBusTerminalId(item.getBusTerminalId());
			blHbt.setCreateDate(LocalDateTime.now());
			blHbt.setCreateBy(UserLoginUtil.getUsername());
			blHbtRepository.save(blHbt);
		}
	}
	
	public void deleteBusLines(Long id) {
		busLinesRepository.deleteById(id);
	}
	
}
