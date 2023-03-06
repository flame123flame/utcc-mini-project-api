package UTCC.project.work.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.work.model.Worksheet;

@Repository
public interface WorksheetRepository  extends CrudRepository<Worksheet, Long> {

	
}
