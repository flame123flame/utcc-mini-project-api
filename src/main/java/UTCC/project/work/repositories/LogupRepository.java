package UTCC.project.work.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.work.model.Logup;

@Repository
public interface LogupRepository extends CrudRepository<Logup, Long>{

}
