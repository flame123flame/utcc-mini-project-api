package UTCC.project.work.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.work.model.Ticket;
import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {

    @Query(value = "SELECT * FROM ticket WHERE ticket_trip_id = :ticketTripId", nativeQuery = true)
    Ticket findTicketTripById(@Param("ticketTripId") Long ticketTripId);
}
