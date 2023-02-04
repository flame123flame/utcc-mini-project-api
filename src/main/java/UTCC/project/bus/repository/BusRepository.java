package UTCC.project.bus.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.bus.model.BusModel;

@Repository
public interface BusRepository extends CrudRepository<BusModel, Long> {

}
