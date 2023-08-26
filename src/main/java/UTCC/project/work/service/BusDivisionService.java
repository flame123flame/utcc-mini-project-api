package UTCC.project.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.project.work.dao.BusDivisionDao;
import UTCC.project.work.vo.BusDivisionVo;

@Service
public class BusDivisionService {

	@Autowired
	private BusDivisionDao busDivisionDao;
	
	public List<BusDivisionVo.Response> getList(){
		return busDivisionDao.getList();
	}
	
}
