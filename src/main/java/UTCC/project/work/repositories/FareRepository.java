package UTCC.project.work.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.work.model.Fare;

@Repository
public interface FareRepository extends CrudRepository<Fare, Long>{

}
