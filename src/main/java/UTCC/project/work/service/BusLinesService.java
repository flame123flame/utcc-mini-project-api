package UTCC.project.work.service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.framework.utils.UserLoginUtil;
import UTCC.project.work.dao.BusLinesDao;
import UTCC.project.work.model.BusLines;
import UTCC.project.work.model.BusType;
import UTCC.project.work.model.BuslinesHbusterminal;
import UTCC.project.work.model.TypeHfare;
import UTCC.project.work.repositories.BusLinesRepository;
import UTCC.project.work.repositories.BuslinesHbusterminalRepository;
import UTCC.project.work.vo.BusLinesVo;
import UTCC.project.work.vo.BusTerminalVo;
import UTCC.project.work.vo.BusTypeVo;



@Service
public class BusLinesService {

	@Autowired
	private BusLinesDao busLinesDao;
	
	@Autowired
	private BusLinesRepository busLinesRepository;
	
	@Autowired
	private BuslinesHbusterminalRepository blHbtRepository;
	
	public List<BusLinesVo.Response> getList(){
		return busLinesDao.getList();
	}
	
	public List<BusLines> getBusLines(){
		return (List<BusLines>) busLinesRepository.findAll();
	}

	
	public BusLinesVo.Request findById(BusLinesVo.Request req) {
	   BusLines optionalBusLines = busLinesRepository.findById(req.getBusLinesId()).get();
	        BusLines busLines = optionalBusLines;
	        List<BusTerminalVo.Request> buslinesHbusterminals = blHbtRepository.findByBusLinesId(busLines.getBusLinesId())
	                .stream()
	                .map(item -> {
	                    BusTerminalVo.Request blHbt = new BusTerminalVo.Request();
	                    blHbt.setBusTerminalId(item.getBusTerminalId());
	                    return blHbt;
	                })
	                .collect(Collectors.toList());

	        BusLinesVo.Request busLinesRes = new BusLinesVo.Request();
	        busLinesRes.setBusLinesDestination(busLines.getBusLinesDestination());
	        busLinesRes.setBusLinesExpressway(busLines.getBusLinesExpressway());
	        busLinesRes.setBusLinesOrigin(busLines.getBusLinesOrigin());
	        busLinesRes.setBusLinesId(busLines.getBusLinesId());
	        busLinesRes.setBusLinesNightshift(busLines.getBusLinesNightshift());
	        busLinesRes.setBusLinesNo(busLines.getBusLinesNo());
	        busLinesRes.setListDetail(buslinesHbusterminals);
	        return busLinesRes;
	
	}

	
	public void save(BusLinesVo.Request req) {
		BusLines busLines = new BusLines();
		busLines.setBusLinesNo(req.getBusLinesNo());
		busLines.setBusLinesOrigin(req.getBusLinesOrigin());
		busLines.setBusLinesDestination(req.getBusLinesDestination());
		busLines.setBusLinesExpressway(req.getBusLinesExpressway());
		busLines.setBusLinesNightshift(req.getBusLinesNightshift());
		busLines.setCreateDate(LocalDateTime.now());
		busLines.setCreateBy(UserLoginUtil.getUsername());
		long id = busLinesRepository.save(busLines).getBusLinesId();
		BuslinesHbusterminal blHbt = null;
		for(BusTerminalVo.Request item : req.getListDetail()) {
			blHbt = new BuslinesHbusterminal();
			blHbt.setBusLinesId(id);
			blHbt.setBusTerminalId(item.getBusTerminalId());
			blHbt.setCreateDate(LocalDateTime.now());
			blHbt.setCreateBy(UserLoginUtil.getUsername());
			blHbtRepository.save(blHbt);
		}
	}
	
	
	public void editNew(BusLinesVo.Request req) {
		BusLines busLines = busLinesRepository.findById(req.getBusLinesId()).get();
	    if (busLines == null) {
	        return;
	    }
	    BusLines busLinesSet = busLines;
	    busLinesSet.setBusLinesNo(req.getBusLinesNo());
	    busLinesSet.setBusLinesOrigin(req.getBusLinesOrigin());
	    busLinesSet.setBusLinesDestination(req.getBusLinesDestination());
	    busLinesSet.setBusLinesExpressway(req.getBusLinesExpressway());
	    busLinesSet.setBusLinesNightshift(req.getBusLinesNightshift());
	    busLinesSet.setCreateDate(LocalDateTime.now());
	    busLinesSet.setCreateBy(UserLoginUtil.getUsername());
	    busLinesSet = busLinesRepository.save(busLines);
	    List<BuslinesHbusterminal> existingHbusterminals = blHbtRepository.findByBusLinesId(busLines.getBusLinesId());
	    blHbtRepository.deleteAll(existingHbusterminals);
	    final long busLinesId = busLines.getBusLinesId();
		BuslinesHbusterminal blHBusTerminal = null;
	    for(BusTerminalVo.Request blHBusTerminalList : req.getListDetail() ) {
        	blHBusTerminal = new BuslinesHbusterminal();
        	blHBusTerminal.setBusTerminalId(blHBusTerminalList.getBusTerminalId());
        	blHBusTerminal.setBusLinesId(busLinesId);
        	blHBusTerminal.setCreateDate(LocalDateTime.now());
        	blHBusTerminal.setCreateBy(UserLoginUtil.getUsername());
        	blHbtRepository.save(blHBusTerminal);
	    }
	}

	
	
	
	
	public void edit(BusLinesVo.Request req) {
	    try {
	        BusLines busLines = updateBusLines(req);
	        busLinesRepository.save(busLines);
	        updateBuslineHbusterminals(req.getBusLinesId(), req.getListDetail());
	    } catch (Exception e) {
	        // Handle exceptions
	        e.printStackTrace();
	        // Consider logging or throwing a custom exception here
	    }
	}

	private BusLines updateBusLines(BusLinesVo.Request req) {
	    BusLines busLines = busLinesRepository.findById(req.getBusLinesId()).get();

	    busLines.setBusLinesNo(req.getBusLinesNo());
	    busLines.setBusLinesOrigin(req.getBusLinesOrigin());
	    busLines.setBusLinesDestination(req.getBusLinesDestination());
	    busLines.setBusLinesExpressway(req.getBusLinesExpressway());
	    busLines.setBusLinesNightshift(req.getBusLinesNightshift());
	    busLines.setCreateDate(LocalDateTime.now());
	    busLines.setCreateBy(UserLoginUtil.getUsername());

	    return busLines;
	}

	private void updateBuslineHbusterminals(long busLinesId, List<BusTerminalVo.Request> listDetail) {
	    List<BuslinesHbusterminal> existingHbusterminals = blHbtRepository.findByBusLinesId(busLinesId);
	    blHbtRepository.deleteAll(existingHbusterminals);

	    List<BuslinesHbusterminal> newHbusterminals = listDetail.stream()
	        .map(item -> {
	            BuslinesHbusterminal blHbt = new BuslinesHbusterminal();
	            blHbt.setBusLinesId(busLinesId);
	            blHbt.setBusTerminalId(item.getBusTerminalId());
	            blHbt.setCreateDate(LocalDateTime.now());
	            blHbt.setCreateBy(UserLoginUtil.getUsername());
	            return blHbt;
	        })
	        .collect(Collectors.toList());

	    blHbtRepository.saveAll(newHbusterminals);
	}

	
	public void deleteBusLines(Long id) {
		busLinesRepository.deleteById(id);
	}
	
}
