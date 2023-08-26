package UTCC.project.work.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.work.model.TypeHfare;

@Repository
public interface TypeHfareRespository extends CrudRepository<TypeHfare, Long>{

}
