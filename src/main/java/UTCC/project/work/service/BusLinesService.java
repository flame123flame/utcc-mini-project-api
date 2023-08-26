package UTCC.project.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.project.work.model.BusLines;
import UTCC.project.work.repositories.BusLinesRepository;



@Service
public class BusLinesService {

	@Autowired
	private BusLinesRepository busLinesRepository;
	
	
	public List<BusLines> getBusLines(){
		return (List<BusLines>) busLinesRepository.findAll();
	}

}
