package UTCC.project.work.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.work.model.TypeHfare;
import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface TypeHfareRepository extends CrudRepository<TypeHfare, Long>{

    @Query(value = "SELECT * FROM type_h_fare WHERE type_id = :typeId", nativeQuery = true)
    List<TypeHfare> findByTypeId(@Param("typeId") long typeId);
    
}
