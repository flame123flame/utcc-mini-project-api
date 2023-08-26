package UTCC.project.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.project.work.model.BusType;
import UTCC.project.work.repositories.BusTypeRespository;



@Service
public class BusTypeService {

	@Autowired
	private BusTypeRespository busTypeRepository;
	
	
	public List<BusType> getBusType(){
		return (List<BusType>) busTypeRepository.findAll();
	}

}
