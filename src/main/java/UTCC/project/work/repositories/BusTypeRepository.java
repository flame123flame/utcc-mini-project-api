package UTCC.project.work.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.work.model.BusType;

@Repository
public interface BusTypeRepository extends CrudRepository<BusType, Long>{

}
