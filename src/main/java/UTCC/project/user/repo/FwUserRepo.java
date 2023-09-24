package UTCC.project.user.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.user.model.FwUser;


@Repository
public interface FwUserRepo extends CrudRepository<FwUser, Long> {
	FwUser findByUsername(String username);
	
	public void deleteByUsername(String username);
	List<FwUser> findAll();
	List<FwUser> findAllByOrderByCreateDateDesc();
	
	@Query(value = "SELECT  * FROM fw_user ",nativeQuery = true)
	public List<FwUser> findUser();
	 
	@Query(value = "SELECT  * FROM fw_user where role_code = ?1  ",nativeQuery = true)
	public List<FwUser> findUserByDriver(String roleCode);
	
	@Query(value = "SELECT  * FROM fw_user where username = ?1 ",nativeQuery = true)
	public List<FwUser> findUser(String date);
	
	@Query(value = "SELECT  * FROM fw_user where username = ?1 ",nativeQuery = true)
	public List<FwUser> findUser(String date , String usrrname);
	
	
}
