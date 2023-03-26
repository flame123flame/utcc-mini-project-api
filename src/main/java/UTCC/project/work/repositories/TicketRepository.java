package UTCC.project.work.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.work.model.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {

}
