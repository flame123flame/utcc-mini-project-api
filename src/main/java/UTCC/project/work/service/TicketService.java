package UTCC.project.work.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.framework.constant.ConstantsTimestamp;
import UTCC.project.work.dao.TicketDao;
import UTCC.project.work.model.TerminalTimestamp;
import UTCC.project.work.model.Ticket;
import UTCC.project.work.repositories.TerminalTimestampRepository;
import UTCC.project.work.repositories.TicketRepository;
import UTCC.project.work.vo.TicketVo;
import UTCC.project.work.vo.TicketVo.RequesTicket;

@Service
public class TicketService {

	@Autowired
	private TicketDao ticketDao;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private TerminalTimestampRepository terminalTimestampRepository;

	public List<TicketVo.RequestDropdown> getDataBusline(Long id) {
		return ticketDao.getData(id);
	}

	public List<TicketVo.Response> getDataById(Long id) {
		List<TicketVo.Response> data = ticketDao.getDataById(id);
		List<TicketVo.Response> dataSend = new ArrayList<>(data.size());

		for (int i = 0; i < data.size(); i++) {
			TicketVo.Response currentData = data.get(i);
			TicketVo.Response previousData = i == 0 ? null : data.get(i - 1);

			long ticketCal = 0;
			if (currentData.getTrip() > 0 && previousData != null) {
				ticketCal = Long.parseLong(currentData.getTicketNo()) - Long.parseLong(previousData.getTicketNo());
			}

			TicketVo.Response dataSet = new TicketVo.Response();
			dataSet.setTicketNo(currentData.getTicketNo());
			dataSet.setTicketNoSum(String.valueOf(ticketCal));
			dataSet.setTrip(currentData.getTrip());
			dataSet.setSumPrice(String.valueOf(ticketCal * 8));
			dataSet.setFareId(8);
			dataSend.add(dataSet);
		}

		return dataSend;
	}

	
	
	public List<TicketVo.Response> getDataByIdNew(Long id) {
		List<TicketVo.Response> data = ticketDao.getDataById(id);
		List<TicketVo.Response> dataSend = new ArrayList<>(data.size());

		for (int i = 0; i < data.size(); i++) {
			TicketVo.Response currentData = data.get(i);
			TicketVo.Response previousData = i == 0 ? null : data.get(i - 1);

			long ticketCal = 0;
			if (currentData.getTrip() > 0 && previousData != null) {
				ticketCal = Long.parseLong(currentData.getTicketNo()) - Long.parseLong(previousData.getTicketNo());
			}

			TicketVo.Response dataSet = new TicketVo.Response();
			dataSet.setTicketNo(currentData.getTicketNo());
			dataSet.setTicketNoSum(String.valueOf(ticketCal));
			dataSet.setTrip(currentData.getTrip());
			dataSet.setSumPrice(String.valueOf(ticketCal * 8));
			dataSet.setFareId(8);
			dataSend.add(dataSet);
		}

		return dataSend;
	}

	public void saveForm(TicketVo.Request req) {
		Ticket data = null;
		for(RequesTicket dataTicket:req.getTypeHfare()) {
			data = new Ticket();
			data.setTicketBegin(req.getTicketBegin());
			data.setTicketEnd(req.getTicketEnd());
			data.setWorksheetId(req.getWorksheetId());
			data.setTrip(req.getTrip());
			
			data.setTicketNo(dataTicket.getTicketNo());
			data.setFareId(dataTicket.getFareId());
			
			ticketRepository.save(data);
		}
	
		

		TerminalTimestamp terminalTimestamp = new TerminalTimestamp();
		terminalTimestamp.setWorksheetId(req.getWorksheetId());
		terminalTimestamp.setBusTerminalId(req.getBusTerminalId());
		terminalTimestamp.setTrip(req.getTrip());
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String formattedTime = dateFormat.format(currentDate);
		terminalTimestamp.setTerminalTimeArrive(formattedTime);
		terminalTimestamp.setTerminalTimestampStatus(ConstantsTimestamp.WAITING_TIMESTAMP);
		terminalTimestampRepository.save(terminalTimestamp);

	}

}
