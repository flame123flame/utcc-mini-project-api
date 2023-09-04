package UTCC.project.work.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.framework.utils.UserLoginUtil;
import UTCC.project.work.model.BusLines;
import UTCC.project.work.model.BusTerminal;
import UTCC.project.work.repositories.BusTerminalRepository;
import UTCC.project.work.vo.BusLinesVo;
import UTCC.project.work.vo.BusTerminalVo;

@Service
public class BusTerminalService {

	@Autowired
	private BusTerminalRepository busTerminalRepository;
	
	
	public List<BusTerminal> getBusTerminal(){
		return (List<BusTerminal>) busTerminalRepository.findAll();
	}
	
	public void save(BusTerminalVo.Request req) {
		BusTerminal busTerminal = new BusTerminal();
		busTerminal.setBusTerminalName(req.getBusTerminalName());
		busTerminal.setCreateDate(LocalDateTime.now());
		busTerminal.setCreateBy(UserLoginUtil.getUsername());
		busTerminalRepository.save(busTerminal);
	}
	
	public void edit(BusTerminalVo.Request req) {
		BusTerminal busTerminal = busTerminalRepository.findById(req.getBusTerminalId()).get();
		busTerminal.setBusTerminalName(req.getBusTerminalName());
		busTerminal.setUpdateDate(LocalDateTime.now());
		busTerminal.setUpdateBy(UserLoginUtil.getUsername());
		busTerminalRepository.save(busTerminal);
	}
	
}
  