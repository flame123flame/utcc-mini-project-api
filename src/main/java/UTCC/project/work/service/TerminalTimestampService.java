package UTCC.project.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.project.work.dao.TerminalTimestampDao;
import UTCC.project.work.vo.TerminalTimestampVo;

@Service
public class TerminalTimestampService {

	
	@Autowired
	private TerminalTimestampDao terminalTimestampDao;
	
	
	
	public List<TerminalTimestampVo.Response> getTerminalTimestamp(){
		return terminalTimestampDao.getData("WAITING_TIMESTAMP");
	}
	
	
	
}
