package UTCC.project.user.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.user.model.FwRole;

@Repository
public interface FwRoleRepo extends CrudRepository<FwRole, Long>{
	
	public FwRole findByRoleCode(String roleCode); 
	
	public void deleteByRoleCode(String roleCode);
}
