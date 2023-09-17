package UTCC.project.work.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	
	public BusTypeVo.Response findById(BusTypeVo.Request req) {
	    BusType busType = busTypeRepository.findById(req.getBusTypeId()).get();
	    if (busType == null) {
	        return null;
	    }
	    BusTypeVo.Response res = new BusTypeVo.Response();
	    res.setBusTypeId(busType.getBusTypeId());
	    res.setBusTypeName(busType.getBusTypeName());
	    List<FareVo.Request> typeHfareArrayList = new ArrayList<>();
	    for (TypeHfare typeHfare : typeHfareRepository.findByTypeId(busType.getBusTypeId())) {
	        FareVo.Request fareRequest = new FareVo.Request();
	        fareRequest.setFareId(typeHfare.getFareId());
	        typeHfareArrayList.add(fareRequest);
	    }
	    res.setListDetail(typeHfareArrayList);
	    return res;
	}

	
	
	public void edit(BusTypeVo.Request req) {
		BusType busType = busTypeRepository.findById(req.getBusTypeId()).get();
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
	
	public void editNew(BusTypeVo.Request req) {
	    BusType busTypeOptional = busTypeRepository.findById(req.getBusTypeId()).get();
	    if (busTypeOptional == null) {
	        return;
	    }
	    BusType existingBusType = busTypeOptional;
	    existingBusType.setBusTypeName(req.getBusTypeName());
	    existingBusType.setCreateDate(LocalDateTime.now());
	    existingBusType.setCreateBy(UserLoginUtil.getUsername());
	    existingBusType = busTypeRepository.save(existingBusType);
	    List<TypeHfare> existingTypeHfareList = typeHfareRepository.findByTypeId(existingBusType.getBusTypeId());
	    typeHfareRepository.deleteAll(existingTypeHfareList);
	    final long typeId = existingBusType.getBusTypeId();
	    List<TypeHfare> typeHfareList = req.getListDetail().stream()
	            .map(item -> {
	                TypeHfare typeHfare = new TypeHfare();
	                typeHfare.setTypeId(typeId); 
	                typeHfare.setFareId(item.getFareId());
	                typeHfare.setCreateDate(LocalDateTime.now());
	                typeHfare.setCreateBy(UserLoginUtil.getUsername());
	                return typeHfare;
	            })
	            .collect(Collectors.toList());
	    typeHfareRepository.saveAll(typeHfareList);
	}

	
	public void deleteBusType(Long id) {
	    List<TypeHfare> existingTypeHfareList = typeHfareRepository.findByTypeId(id);
	    typeHfareRepository.deleteAll(existingTypeHfareList);
		busTypeRepository.deleteById(id);
	}

}
