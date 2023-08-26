package UTCC.project.work.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.work.model.BusLines;

@Repository
public interface BusLinesRepository extends CrudRepository<BusLines, Long>{

}
