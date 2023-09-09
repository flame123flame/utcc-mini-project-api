package UTCC.project.work.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.work.model.TerminalTimestamp;

@Repository
public interface TerminalTimestampRepository extends CrudRepository<TerminalTimestamp, Long>{

}
