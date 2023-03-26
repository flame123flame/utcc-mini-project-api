package UTCC.project.work.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.project.work.dao.TicketDao;
import UTCC.project.work.model.Ticket;
import UTCC.project.work.repositories.TicketRepository;
import UTCC.project.work.vo.TicketVo;

@Service
public class TicketService {

	@Autowired
	private TicketDao ticketDao;
	@Autowired
	private TicketRepository ticketRepository;
	
	
	
	public List<TicketVo.RequestDropdown> getDataBusline(Long id){
		return ticketDao.getData(id);
	}
	
	public List<TicketVo.Response> getDataById(Long id) {
	    List<TicketVo.Response> data = ticketDao.getDataById(id);
	    List<TicketVo.Response> dataSend = new ArrayList<>(data.size());
	    long sum = 0;
	    for (int i = 1; i < data.size(); i++) {
	        TicketVo.Response dataSet1 = data.get(i);
	        TicketVo.Response dataSet2 = data.get(i - 1);
	        if (dataSet1.getTrip() > 0) {
	            long ticKetCal = Long.parseLong(dataSet1.getTicketNo()) - Long.parseLong(dataSet2.getTicketNo());
	            sum += ticKetCal*8;
	            TicketVo.Response dataSet = new TicketVo.Response();
	            dataSet.setTicketNo(dataSet1.getTicketNo());
	            dataSet.setTicketNoSum(String.valueOf(ticKetCal));
	            dataSet.setTrip(dataSet1.getTrip());
	            dataSet.setSumPrice(String.valueOf(ticKetCal*8));
	            dataSet.setFareId(8);
	            dataSend.add(dataSet);
	        }
	    }

	    return dataSend;
	}

		
	
	public void saveForm(TicketVo.Request req) {
		Ticket data = new Ticket();
		data.setFareId(4);
		data.setTicketBegin(req.getTicketBegin());
		data.setTicketEnd(req.getTicketBegin());
		data.setWorksheetId(req.getWorksheetId());
		data.setTicketNo(req.getTicketNo());
		data.setTrip(req.getTrip());
		ticketRepository.save(data);
	}
	
	
}
