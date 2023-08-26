package UTCC.project.work.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.work.model.BusopKfare;

@Repository
public interface BusopKfareRespository extends CrudRepository<BusopKfare, Long>{

}
