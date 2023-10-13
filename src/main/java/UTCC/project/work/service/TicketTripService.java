package UTCC.project.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.project.work.model.TicketTrip;
import UTCC.project.work.repositories.TicketTripRepository;

@Service
public class TicketTripService {

	@Autowired
	private TicketTripRepository ticketTripRepository;
	
	public List<TicketTrip> getBusDepot(){
		return (List<TicketTrip>) ticketTripRepository.findAll();
	}
}
