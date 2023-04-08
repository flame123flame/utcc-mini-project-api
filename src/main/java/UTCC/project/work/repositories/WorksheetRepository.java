package UTCC.project.work.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import UTCC.project.work.model.Worksheet;

@Repository
public interface WorksheetRepository  extends CrudRepository<Worksheet, Long> {

	@Query( value = "select * from worksheet where worksheet_status = ? AND worksheet_id = ?  "  , nativeQuery = true)
	public List<Worksheet> findByStatus(String status, Long id);
	

	@Query( value = "select * from worksheet where worksheet_status = ? AND worksheet_date = ?  "  , nativeQuery = true)
	public List<Worksheet> findByStatus(String status, LocalDateTime date);
	
	
	@Query( value = "select * from worksheet where worksheet_status = ?  "  , nativeQuery = true)
	public List<Worksheet> findByStatus(String status);
	
    @Transactional
    @Modifying
    @Query(value = "UPDATE worksheet SET worksheet_status = 'SUCCESS' " +
            "WHERE worksheet_id = ? ", nativeQuery = true)
    void updateStatusById(Long worksheetId);
	
}
