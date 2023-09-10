package UTCC.project.work.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.framework.utils.UserLoginUtil;
import UTCC.project.work.dao.BusDivisionDao;
import UTCC.project.work.model.BusDivision;
import UTCC.project.work.repositories.BusDivisionRepository;
import UTCC.project.work.vo.BusDivisionVo;

@Service
public class BusDivisionService {

	@Autowired
	private BusDivisionDao busDivisionDao;
	
	
	@Autowired
	private BusDivisionRepository busDivisionRepository;
	

	public List<BusDivisionVo.Response> getList(){
		return busDivisionDao.getList();
	}
	
	public void save(BusDivisionVo.Request req) {
		BusDivision busDivision = new BusDivision();
		busDivision.setBusDepotId(req.getBusDepotId());
		busDivision.setBusDivisionName(req.getBusDivisionName());
		busDivision.setBusDivisionNo(req.getBusDivisionNo());
		busDivision.setBmtaZone(req.getBmtaZone());
		busDivision.setCreateDate(LocalDateTime.now());
		busDivision.setCreateBy(UserLoginUtil.getUsername());
		busDivisionRepository.save(busDivision);
	}
	
	public void edit(BusDivisionVo.Request req) {
		BusDivision busDivision =  busDivisionRepository.findById(req.getBusDivisionId()).get();
		busDivision.setBusDepotId(req.getBusDepotId());
		busDivision.setBusDivisionName(req.getBusDivisionName());
		busDivision.setBusDivisionNo(req.getBusDivisionNo());
		busDivision.setBmtaZone(req.getBmtaZone());
		busDivision.setUpdateDate(LocalDateTime.now());
		busDivision.setUpdateBy(UserLoginUtil.getUsername());
		busDivisionRepository.save(busDivision);
	}
	
	public void deleteBusVehicle(Long id) {
		busDivisionRepository.deleteById(id);
	}
}
