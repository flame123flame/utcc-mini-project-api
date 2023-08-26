package UTCC.project.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.project.work.model.Fare;
import UTCC.project.work.repositories.FareRepository;



@Service
public class FareService {

	@Autowired
	private FareRepository fareRepository;
	
	
	public List<Fare> getFare(){
		return (List<Fare>) fareRepository.findAll();
	}

}
