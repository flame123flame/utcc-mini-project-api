package UTCC.project.work.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.project.work.dao.TicketTripDao;
import UTCC.project.work.model.TicketTrip;
import UTCC.project.work.repositories.TicketTripRepository;
import UTCC.project.work.vo.TicketTripVo;

@Service
public class TicketTripService {

	@Autowired
	private TicketTripRepository ticketTripRepository;

	@Autowired
	private TicketTripDao ticketTripDao;
	
	public List<TicketTrip> getTicketTrip(){
		return (List<TicketTrip>) ticketTripRepository.findAll();
	}

	public List<TicketTripVo.Response> getTicketTripByWorksheetId(long id){
		List<TicketTripVo.Response> dataRespons = new ArrayList<>();  
		List<TicketTripVo.Response> dataTicketTrip = ticketTripDao.getTicketTripByWorksheetId(id);
		TicketTripVo.Response ticket = null;
		for(TicketTripVo.Response item : dataTicketTrip) {
			ticket = new TicketTripVo.Response();
			ticket.setTicketBegin(item.getTicketBegin());
			ticket.setTicketEnd(item.getTicketEnd());
			ticket.setTicketTripId(item.getTicketTripId());
			ticket.setWorksheetId(item.getWorksheetId());
        	ticket.setTrip(item.getTrip());
			List<TicketTripVo.TicketAndFare> dataDeatil = ticketTripDao.getTicketTripDetail(item.getTicketTripId());
			TicketTripVo.TicketAndFare ticketAndFare = null;
			List<TicketTripVo.TicketAndFare> ticketAndFareList = new ArrayList<>();  
			for(TicketTripVo.TicketAndFare sub : dataDeatil) {
				ticketAndFare = new TicketTripVo.TicketAndFare();
				ticketAndFare.setFareDesc(sub.getFareDesc());
				ticketAndFare.setFareId(sub.getFareId());
				ticketAndFare.setFareValue(sub.getFareValue());
				ticketAndFare.setTicketNo(sub.getTicketNo());
				ticketAndFare.setTicketTripId(item.getTicketTripId());
				ticketAndFareList.add(ticketAndFare);
			}
			ticket.setTicketList(ticketAndFareList);
			dataRespons.add(ticket);
		}
		return calSumPrice(dataRespons);
	}
	
	public List<TicketTripVo.Response> calSumPrice(List<TicketTripVo.Response> dataRespons) {
	    BigDecimal sero = BigDecimal.ZERO;
	    for (int dataIndex = 1; dataIndex < dataRespons.size(); dataIndex++) {
		    BigDecimal sum = BigDecimal.ZERO;
		    long resultReturn = 0L; 
	        TicketTripVo.Response data = dataRespons.get(dataIndex);
	        for (int subIndex = 0; subIndex < data.getTicketList().size(); subIndex++) {
	            TicketTripVo.TicketAndFare sub = data.getTicketList().get(subIndex);
	            long result = Long.valueOf(sub.getTicketNo()) - Long.valueOf(dataRespons.get(dataIndex - 1).getTicketList().get(subIndex).getTicketNo());
	            resultReturn += result;
	            BigDecimal fareValue = sub.getFareValue();
	            BigDecimal valueToAdd = BigDecimal.valueOf(result).multiply(fareValue);
	            sum = sum.add(valueToAdd).setScale(2, RoundingMode.HALF_UP);
	        }
	        dataRespons.get(0).setSumPrice(sero.setScale(2, RoundingMode.HALF_UP));
	        data.setSumPrice(sum);
	        data.setSumTicket(resultReturn);
	    }
	    return dataRespons;
	}
	
	
}
