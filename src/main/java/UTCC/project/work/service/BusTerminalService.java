package UTCC.project.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.project.work.model.BusTerminal;
import UTCC.project.work.repositories.BusTerminalRepository;

@Service
public class BusTerminalService {

	@Autowired
	private BusTerminalRepository busTerminalRepository;
	
	
	public List<BusTerminal> getBusTerminal(){
		return (List<BusTerminal>) busTerminalRepository.findAll();
	}
}
  