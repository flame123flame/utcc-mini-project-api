package UTCC.project.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.project.work.dao.BusDivisionDao;
import UTCC.project.work.model.BusDivision;
import UTCC.project.work.repositories.BusDivisionRepository;
import UTCC.project.work.vo.BusDivisionVo;

@Service
public class BusDivisionService {

	@Autowired
	private BusDivisionDao busDivisionDao;
	
	
	@Autowired
	private BusDivisionRepository BusDivisionRepository;
	

	public List<BusDivisionVo.Response> getList(){
		return busDivisionDao.getList();
	}
	
	public void save(BusDivisionVo.Request req) {
		BusDivision busDivision = new BusDivision();
		busDivision.setBusDepotId(req.getDepotId());
		busDivision.setBusDivisionName(req.getBusDivisionName());
		busDivision.setBusDivisionNo(req.getBusDivisionNo());
		busDivision.setBmtaZone(req.getBmtaZone());
		BusDivisionRepository.save(busDivision);
	}
}
