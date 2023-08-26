package UTCC.project.work.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.work.model.BusTerminal;

@Repository
public interface BusTerminalRepository extends CrudRepository<BusTerminal, Long> {

}
