package UTCC.project.work.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.work.model.BusDepot;

@Repository
public interface BusDepotRespository extends CrudRepository<BusDepot, Long>{

}
