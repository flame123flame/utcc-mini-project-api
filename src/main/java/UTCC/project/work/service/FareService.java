package UTCC.project.work.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.framework.utils.UserLoginUtil;
import UTCC.project.work.model.Fare;
import UTCC.project.work.repositories.FareRepository;
import UTCC.project.work.vo.FareVo;



@Service
public class FareService {

	@Autowired
	private FareRepository fareRepository;
	
	public List<Fare> getFare(){
		return (List<Fare>) fareRepository.findAll();
	}
	
	public void save(FareVo.Request req) {
		Fare fare = new Fare();
		fare.setFareValue(req.getFareValue());
		fare.setFareDesc(req.getFareDesc());
		fare.setCreateDate(LocalDateTime.now());
		fare.setCreateBy(UserLoginUtil.getUsername());
		fareRepository.save(fare);
	}
	
	public void edit(FareVo.Request req) {
		Fare fare = fareRepository.findById(req.getFareId()).get();
		fare.setFareValue(req.getFareValue());
		fare.setFareDesc(req.getFareDesc());
		fare.setUpdateDate(LocalDateTime.now());
		fare.setUpdateBy(UserLoginUtil.getUsername());
		fareRepository.save(fare);
	}
	
	public void deleteFare(Long id) {
		fareRepository.deleteById(id);
	}

}
