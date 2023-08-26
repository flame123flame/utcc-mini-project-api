package UTCC.project.work.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.work.model.WorkshHbusop;

@Repository
public interface WorkshHbusopRepository extends CrudRepository<WorkshHbusop, Long>{

}
