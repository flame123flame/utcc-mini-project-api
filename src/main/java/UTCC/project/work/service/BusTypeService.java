package UTCC.project.work.service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.framework.utils.UserLoginUtil;
import UTCC.project.work.dao.BusTypeDao;
import UTCC.project.work.model.BusType;
import UTCC.project.work.model.TypeHfare;
import UTCC.project.work.repositories.BusTypeRepository;
import UTCC.project.work.repositories.TypeHfareRepository;
import UTCC.project.work.vo.BusTypeVo;
import UTCC.project.work.vo.FareVo;



@Service
public class BusTypeService {

	@Autowired
	private BusTypeDao busTypeDao;
	
	@Autowired
	private BusTypeRepository busTypeRepository;
	
	@Autowired
	private TypeHfareRepository typeHfareRepository;
	
	public List<BusTypeVo.Response> getList(){
		return busTypeDao.getList();
	}
	
	public List<BusType> getBusType(){
		return (List<BusType>) busTypeRepository.findAll();
	}
	
	public void save(BusTypeVo.Request req) {
		BusType busType = new BusType();
		busType.setBusTypeName(req.getBusTypeName());
		busType.setCreateDate(LocalDateTime.now());
		busType.setCreateBy(UserLoginUtil.getUsername());
		long id = busTypeRepository.save(busType).getBusTypeId();
		TypeHfare typeHfare = null;
		for(FareVo.Request item : req.getListDetail()) {
			typeHfare = new TypeHfare();
			typeHfare.setTypeId(id);
			typeHfare.setFareId(item.getFareId());
			typeHfare.setCreateDate(LocalDateTime.now());
			typeHfare.setCreateBy(UserLoginUtil.getUsername());
			typeHfareRepository.save(typeHfare);
		}
	}

}
