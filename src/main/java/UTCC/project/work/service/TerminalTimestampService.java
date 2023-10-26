package UTCC.project.work.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.framework.constant.ConstantsTimestamp;
import UTCC.framework.constant.ConstantsWorksheetStatus;
import UTCC.framework.utils.UserLoginUtil;
import UTCC.project.employee.module.Employee;
import UTCC.project.employee.repo.jpa.EmployeeRepo;
import UTCC.project.work.dao.TerminalTimestampDao;
import UTCC.project.work.dao.TicketTripDao;
import UTCC.project.work.model.TerminalTimestamp;
import UTCC.project.work.model.Worksheet;
import UTCC.project.work.repositories.TerminalTimestampRepository;
import UTCC.project.work.repositories.WorksheetRepository;
import UTCC.project.work.vo.TerminalTimestampVo;
import UTCC.project.work.vo.TicketTripVo;

@Service
public class TerminalTimestampService {

	
	@Autowired
	private TerminalTimestampDao terminalTimestampDao;
	
	
	@Autowired
	private TerminalTimestampRepository terminalTimestampRepository;
	
	@Autowired
	private WorksheetRepository worksheetRepository;
	
	
	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private TicketTripDao ticketTripDao;
	
	
	
	public List<TerminalTimestampVo.Response> getTerminalTimestamp(){
		return terminalTimestampDao.getData(ConstantsTimestamp.WAITING_TIMESTAMP);
	}
	
	
	public List<TerminalTimestampVo.Response> getTerminalTimestampSucess(){
		return terminalTimestampDao.getDataSuccess(ConstantsTimestamp.SUCCESS_TIMESTAMP);
	}
	
	public void setTimestamp(TicketTripVo.Request req) {
		TerminalTimestamp data = terminalTimestampRepository.findById(req.getTerminalTimestampId()).get();
		data.setTerminalTimeDeparture(req.getTerminalTimeDeparture());
		data.setTerminalTimestampStatus(ConstantsTimestamp.SUCCESS_TIMESTAMP);
		data.setBusTerminalAgent(UserLoginUtil.getUsername());
		data.setUpdateBy(UserLoginUtil.getUsername());
		data.setUpdateDate(LocalDateTime.now());
		terminalTimestampRepository.save(data);
	}
	

	public void setTimestampEnd(TicketTripVo.Request req) {
		Worksheet  worksheet = worksheetRepository.findById(req.getWorksheetId()).get();
		worksheet.setWorksheetTimeEnd(req.getBusTerminalDepartureDes());
		worksheet.setWorksheetStatus(ConstantsWorksheetStatus.END_PROGRESS);
		worksheet.setWorksheetTerminalAgent(UserLoginUtil.getUsername());
		worksheetRepository.save(worksheet);
		
		TerminalTimestamp data = terminalTimestampRepository.findById(req.getTerminalTimestampId()).get();
		data.setTerminalTimeDeparture(req.getBusTerminalDepartureDes());
		data.setTerminalTimestampStatus(ConstantsTimestamp.END_TIMESTAMP);
		data.setBusTerminalAgent(UserLoginUtil.getUsername());
		data.setUpdateBy(UserLoginUtil.getUsername());
		data.setUpdateDate(LocalDateTime.now());
		terminalTimestampRepository.save(data);
	}
	
	
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
			ticket.setTerminalTimestampId(firstTicketTime.getTerminalTimestampId());			
			Employee employee = employeeRepo.findByUsername(UserLoginUtil.getUsername());
			Employee employeeAgent = employeeRepo.findByUsername(firstTicketTime.getBusTerminalAgent());
			ticket.setBusTerminalAgentName(employeeAgent.getFirstName().concat(" ").concat(employeeAgent.getLastName()));		
			if (ConstantsTimestamp.WAITING_TIMESTAMP.equals(firstTicketTime.getTerminalTimestampStatus()) &&
				    employee.getBusTerminalId() == firstTicketTime.getBusTerminalId() &&
				    firstTicketTime.getTerminalTimeDeparture() == null) {
				    ticket.setIsTimestamp(true);
			}else {
				    ticket.setIsTimestamp(false);
			}
			
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
