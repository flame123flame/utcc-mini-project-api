package UTCC.project.work.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.work.model.BusDivision;

@Repository
public interface BusDivisionRepository extends CrudRepository<BusDivision, Long>{

}
