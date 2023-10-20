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
import UTCC.project.work.vo.TicketTripVo.TicketTime;

@Service
public class TicketTripService {

	@Autowired
	private TicketTripRepository ticketTripRepository;

	@Autowired
	private TicketTripDao ticketTripDao;
	
	public List<TicketTrip> getTicketTrip(){
		return (List<TicketTrip>) ticketTripRepository.findAll();
	}

//	getTicketTripTime


	public List<TicketTripVo.Response> getTicketTripByWorksheetId(long id) {
		List<TicketTripVo.Response> dataRespons = new ArrayList<>();
		List<TicketTripVo.Response> dataTicketTrip = ticketTripDao.getTicketTripByWorksheetId(id);
		BigDecimal sum = BigDecimal.ZERO;

		for (int index = 0; index < dataTicketTrip.size(); index++) {
			TicketTripVo.Response ticket = createTicketResponse(dataTicketTrip, index, dataRespons, sum);
			dataRespons.add(ticket);

			updatePreviousAndFirstTickets(dataRespons, index);
		}

		return calSumPrice(dataRespons);
	}

	private TicketTripVo.Response createTicketResponse(
			List<TicketTripVo.Response> dataTicketTrip,
			int index,
			List<TicketTripVo.Response> dataRespons,
			BigDecimal sum
	) {
		TicketTripVo.Response item = dataTicketTrip.get(index);
		TicketTripVo.Response ticket = new TicketTripVo.Response();

		List<TicketTripVo.TicketTime> ticketTime = ticketTripDao.getTicketTripTime(item.getWorksheetId(), item.getTrip());
		setTicketTimeInfo(ticket, ticketTime);

		if (index == 0) {
			ticket.setTerminalTimeArrive(null);
		}

		setBusTerminalArrivalInfo(ticket, index, dataRespons);

		ticket.setTicketBegin(item.getTicketBegin());
		ticket.setTicketEnd(item.getTicketEnd());
		ticket.setTicketTripId(item.getTicketTripId());
		ticket.setWorksheetId(item.getWorksheetId());
		ticket.setTrip(item.getTrip());
		ticket.setSumPrice(sum.setScale(2, RoundingMode.HALF_UP));

		List<TicketTripVo.TicketAndFare> ticketAndFareList = createTicketAndFareList(item);
		ticket.setTicketList(ticketAndFareList);

		return ticket;
	}

	private void setTicketTimeInfo(TicketTripVo.Response ticket, List<TicketTripVo.TicketTime> ticketTime) {
		if (!ticketTime.isEmpty()) {
			TicketTripVo.TicketTime firstTicketTime = ticketTime.get(0);
			ticket.setTerminalTimeArrive(firstTicketTime.getTerminalTimeArrive());
			ticket.setTerminalTimeDeparture(firstTicketTime.getTerminalTimeDeparture());
			ticket.setBusTerminalDepartureDes(firstTicketTime.getBusTerminalName());
		}
	}

	private void setBusTerminalArrivalInfo(
			TicketTripVo.Response ticket,
			int index,
			List<TicketTripVo.Response> dataRespons
	) {
		if (index > 0) {
			TicketTripVo.Response previousData = dataRespons.get(index - 1);
			ticket.setBusTerminalArrive(previousData.getBusTerminalDepartureDes());
		}
	}

	private List<TicketTripVo.TicketAndFare> createTicketAndFareList(TicketTripVo.Response item) {
		List<TicketTripVo.TicketAndFare> ticketAndFareList = new ArrayList<>();
		List<TicketTripVo.TicketAndFare> dataDetail = ticketTripDao.getTicketTripDetail(item.getTicketTripId());

		for (TicketTripVo.TicketAndFare sub : dataDetail) {
			TicketTripVo.TicketAndFare ticketAndFare = new TicketTripVo.TicketAndFare();
			ticketAndFare.setFareDesc(sub.getFareDesc());
			ticketAndFare.setFareId(sub.getFareId());
			ticketAndFare.setFareValue(sub.getFareValue());
			ticketAndFare.setTicketNo(sub.getTicketNo());
			ticketAndFare.setTicketTripId(item.getTicketTripId());
			ticketAndFareList.add(ticketAndFare);
		}

		return ticketAndFareList;
	}

	private void updatePreviousAndFirstTickets(List<TicketTripVo.Response> dataRespons, int index) {
		if (index == 1) {
			dataRespons.get(0).setBusTerminalArrive(dataRespons.get(1).getBusTerminalDepartureDes());
		}

		if (index > 0) {
			int previousIndex = index - 1;
			dataRespons.get(previousIndex).setTerminalTimeArrive(dataRespons.get(index).getTerminalTimeArrive());
			dataRespons.get(previousIndex).setBusTerminalArrive(dataRespons.get(index).getBusTerminalDepartureDes());
		}

		if (index == dataRespons.size() - 1) {
			dataRespons.get(index).setTerminalTimeArrive(null);
			dataRespons.get(index).setBusTerminalArrive(null);
		}
	}

//	public List<TicketTripVo.Response> getTicketTripByWorksheetId(long id){
//		List<TicketTripVo.Response> dataRespons = new ArrayList<>();
//		List<TicketTripVo.Response> dataTicketTrip = ticketTripDao.getTicketTripByWorksheetId(id);
//		BigDecimal sum = BigDecimal.ZERO;
//		TicketTripVo.Response ticket = null;
//		for( int index = 0; index < dataTicketTrip.size(); index++) {
//			ticket = new TicketTripVo.Response();
//			TicketTripVo.Response item = dataTicketTrip.get(index);
//			List<TicketTripVo.TicketTime> ticketTime = ticketTripDao.getTicketTripTime(item.getWorksheetId(), item.getTrip());
//			if (!ticketTime.isEmpty()) {
//				TicketTime firstTicketTime = ticketTime.get(0);
//				ticket.setTerminalTimeArrive(firstTicketTime.getTerminalTimeArrive());
//				ticket.setTerminalTimeDeparture(
//						firstTicketTime.getTerminalTimeDeparture() == null ? null : firstTicketTime.getTerminalTimeDeparture()
//				);
//				ticket.setBusTerminalDepartureDes(firstTicketTime.getBusTerminalName());
//			}
//			if (index == 0) {
//				ticket.setTerminalTimeArrive(null);
//			}
//			if (index > 0 ) {
//				TicketTripVo.Response previousData = dataRespons.get(index - 1);
//				ticket.setBusTerminalArrive(previousData.getBusTerminalDepartureDes());
//			}
//			ticket.setTicketBegin(item.getTicketBegin());
//			ticket.setTicketEnd(item.getTicketEnd());
//			ticket.setTicketTripId(item.getTicketTripId());
//			ticket.setWorksheetId(item.getWorksheetId());
//			ticket.setTrip(item.getTrip());
//			ticket.setSumPrice(sum.setScale(2, RoundingMode.HALF_UP));
//			List<TicketTripVo.TicketAndFare> dataDeatil = ticketTripDao.getTicketTripDetail(item.getTicketTripId());
//			TicketTripVo.TicketAndFare ticketAndFare = null;
//			List<TicketTripVo.TicketAndFare> ticketAndFareList = new ArrayList<>();
//			for(TicketTripVo.TicketAndFare sub : dataDeatil) {
//				ticketAndFare = new TicketTripVo.TicketAndFare();
//				ticketAndFare.setFareDesc(sub.getFareDesc());
//				ticketAndFare.setFareId(sub.getFareId());
//				ticketAndFare.setFareValue(sub.getFareValue());
//				ticketAndFare.setTicketNo(sub.getTicketNo());
//				ticketAndFare.setTicketTripId(item.getTicketTripId());
//				ticketAndFareList.add(ticketAndFare);
//			}
//			ticket.setTicketList(ticketAndFareList);
//			dataRespons.add(ticket);
//			if (index == 1) {
//				dataRespons.get(0).setBusTerminalArrive(dataRespons.get(1).getBusTerminalDepartureDes());
//			}
//			if (index > 0) {
//				int previousIndex = index - 1;
//				dataRespons.get(previousIndex).setTerminalTimeArrive(dataRespons.get(index).getTerminalTimeArrive());
//				dataRespons.get(previousIndex).setBusTerminalArrive(dataRespons.get(index).getBusTerminalDepartureDes());
//			}
//			if (index == dataRespons.size() - 1) {
//				dataRespons.get(index).setTerminalTimeArrive(null);
//				dataRespons.get(index).setBusTerminalArrive(null);
//			}
//		}
//		return calSumPrice(dataRespons);
//	}
//
//	public List<TicketTripVo.Response> calSumPrice(List<TicketTripVo.Response> dataRespons) {
//		BigDecimal sero = BigDecimal.ZERO;
//		for (int dataIndex = 1; dataIndex < dataRespons.size(); dataIndex++) {
//			BigDecimal sum = BigDecimal.ZERO;
//			long resultReturn = 0L;
//			TicketTripVo.Response data = dataRespons.get(dataIndex);
//			for (int subIndex = 0; subIndex < data.getTicketList().size(); subIndex++) {
//				TicketTripVo.TicketAndFare sub = data.getTicketList().get(subIndex);
//				long result = Long.valueOf(sub.getTicketNo()) - Long.valueOf(dataRespons.get(dataIndex - 1).getTicketList().get(subIndex).getTicketNo());
//				resultReturn += result;
//				BigDecimal fareValue = sub.getFareValue();
//				BigDecimal valueToAdd = BigDecimal.valueOf(result).multiply(fareValue);
//				sum = sum.add(valueToAdd).setScale(2, RoundingMode.HALF_UP);
//			}
//			dataRespons.get(0).setSumPrice(sero.setScale(2, RoundingMode.HALF_UP));
//			data.setSumPrice(sum);
//			data.setSumTicket(resultReturn);
//		}
//		return dataRespons;
//	}


	public List<TicketTripVo.Response> calSumPrice(List<TicketTripVo.Response> dataRespons) {
		if (dataRespons.isEmpty()) {
			return dataRespons;
		}

		BigDecimal zero = BigDecimal.ZERO;
		TicketTripVo.Response firstData = dataRespons.get(0);
		firstData.setSumPrice(zero.setScale(2, RoundingMode.HALF_UP));

		for (int dataIndex = 1; dataIndex < dataRespons.size(); dataIndex++) {
			TicketTripVo.Response data = dataRespons.get(dataIndex);
			TicketTripVo.Response previousData = dataRespons.get(dataIndex - 1);

			BigDecimal sum = calculateSumForTicketList(data.getTicketList(), previousData.getTicketList());
			data.setSumPrice(sum);
			data.setSumTicket(calculateSumTicketForTicketList(data.getTicketList(), previousData.getTicketList()));
		}

		return dataRespons;
	}

	private BigDecimal calculateSumForTicketList(List<TicketTripVo.TicketAndFare> currentList, List<TicketTripVo.TicketAndFare> previousList) {
		BigDecimal sum = BigDecimal.ZERO;

		for (int subIndex = 0; subIndex < currentList.size(); subIndex++) {
			TicketTripVo.TicketAndFare currentTicket = currentList.get(subIndex);
			TicketTripVo.TicketAndFare previousTicket = previousList.get(subIndex);

			long result = Long.valueOf(currentTicket.getTicketNo()) - Long.valueOf(previousTicket.getTicketNo());
			BigDecimal fareValue = currentTicket.getFareValue();

			BigDecimal valueToAdd = BigDecimal.valueOf(result).multiply(fareValue);
			sum = sum.add(valueToAdd).setScale(2, RoundingMode.HALF_UP);
		}

		return sum;
	}

	private long calculateSumTicketForTicketList(List<TicketTripVo.TicketAndFare> currentList, List<TicketTripVo.TicketAndFare> previousList) {
		long resultReturn = 0L;

		for (int subIndex = 0; subIndex < currentList.size(); subIndex++) {
			TicketTripVo.TicketAndFare currentTicket = currentList.get(subIndex);
			TicketTripVo.TicketAndFare previousTicket = previousList.get(subIndex);

			long result = Long.valueOf(currentTicket.getTicketNo()) - Long.valueOf(previousTicket.getTicketNo());
			resultReturn += result;
		}

		return resultReturn;
	}



}
