package UTCC.project.work.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.work.model.BusOperation;

@Repository
public interface BusOperationRepository extends CrudRepository<BusOperation, Long>{

}
