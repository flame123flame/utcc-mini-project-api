package UTCC.project.user.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.user.model.UserCategory;
import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface UserCategoryRepository extends CrudRepository<UserCategory, Long>{
	
	public UserCategory findByUserCategoryCode(String userCategoryCode); 
	
	public void deleteByUserCategoryCode(String userCategoryCode);
	
//	@Query(value = "SELECT * FROM user_category uc WHERE uc.user_category_id = :userCategoryId;", nativeQuery = true)
//    List<UserCategory> findByUserCategoryById(@Param("userCategoryId") long userCategoryId);
}
