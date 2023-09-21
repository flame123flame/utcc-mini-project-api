package UTCC.project.user.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.framework.utils.ConvertDateUtils;
import UTCC.framework.utils.UserLoginUtil;
import UTCC.project.user.model.FwRole;
import UTCC.project.user.model.UserCategory;
import UTCC.project.user.repo.UserCategoryRepository;
import UTCC.project.user.vo.GetRoleRes;
import UTCC.project.user.vo.UserCategoryVo;

@Service
public class UserCategoryService {

	@Autowired
	private UserCategoryRepository userCategoryRepository;
	
	public List<UserCategory> getUserCategory(){
		return (List<UserCategory>) userCategoryRepository.findAll();
	}
	
	public UserCategoryVo.Response getUserCategoryByID(Long userCategoryId)  throws Exception  {
		UserCategoryVo.Response dataRes = new UserCategoryVo.Response();
		UserCategory userCategory = userCategoryRepository.findById(userCategoryId).get();
		dataRes.setUserCategoryId(userCategory.getUserCategoryId());
		dataRes.setUserCategoryCode(userCategory.getUserCategoryCode());
		dataRes.setUserCategoryName(userCategory.getUserCategoryName());
		dataRes.setUserCategoryDesc(userCategory.getUserCategoryDesc());
		dataRes.setCreateBy(userCategory.getCreateBy());
		dataRes.setUpdateBy(userCategory.getUpdateBy());
		dataRes.setCreateDate(ConvertDateUtils.formatLocalDateTimeToString(userCategory.getCreateDate(), ConvertDateUtils.DD_MM_YYYY_HHMM));
		dataRes.setUpdateDate(ConvertDateUtils.formatLocalDateTimeToString(userCategory.getUpdateDate(), ConvertDateUtils.DD_MM_YYYY_HHMM));
		return dataRes;
	}
	
	public UserCategoryVo.Response getUserCategoryByCode(String userCategoryCode)  throws Exception  {
		UserCategoryVo.Response dataRes = new UserCategoryVo.Response();
		UserCategory userCategory = userCategoryRepository.findByUserCategoryCode(userCategoryCode);
		dataRes.setUserCategoryId(userCategory.getUserCategoryId());
		dataRes.setUserCategoryCode(userCategory.getUserCategoryCode());
		dataRes.setUserCategoryName(userCategory.getUserCategoryName());
		dataRes.setUserCategoryDesc(userCategory.getUserCategoryDesc());
		dataRes.setCreateBy(userCategory.getCreateBy());
		dataRes.setUpdateBy(userCategory.getUpdateBy());
		dataRes.setCreateDate(ConvertDateUtils.formatLocalDateTimeToString(userCategory.getCreateDate(), ConvertDateUtils.DD_MM_YYYY_HHMM));
		dataRes.setUpdateDate(ConvertDateUtils.formatLocalDateTimeToString(userCategory.getUpdateDate(), ConvertDateUtils.DD_MM_YYYY_HHMM));
		return dataRes;
	}
	
	@Transactional
	public String save(UserCategoryVo.Request req) {
		UserCategory findDup = userCategoryRepository.findByUserCategoryCode(req.getUserCategoryCode());
		if (findDup != null) {
			return "DUPICATE_USER_CATEGORY_CODE";
		}
		UserCategory userCategory = new UserCategory();
		userCategory.setUserCategoryCode(req.getUserCategoryCode());
		userCategory.setUserCategoryName(req.getUserCategoryName());
		userCategory.setUserCategoryDesc(req.getUserCategoryDesc());
		userCategory.setCreateDate(LocalDateTime.now());
		userCategory.setCreateBy(UserLoginUtil.getUsername());
		userCategoryRepository.save(userCategory);
		return req.getUserCategoryCode();
	}
	
	@Transactional
	public String edit(UserCategoryVo.Request req) {
		UserCategory userCategory = userCategoryRepository.findById(req.getUserCategoryId()).get();
		userCategory.setUserCategoryCode(req.getUserCategoryCode());
		userCategory.setUserCategoryName(req.getUserCategoryName());
		userCategory.setUserCategoryDesc(req.getUserCategoryDesc());
		userCategory.setUpdateDate(LocalDateTime.now());
		userCategory.setUpdateBy(UserLoginUtil.getUsername());
		userCategoryRepository.save(userCategory);
		return req.getUserCategoryCode();
	}
	
	@Transactional
	public void deleteUserCategory(String userCategoryCode) {
		userCategoryRepository.deleteByUserCategoryCode(userCategoryCode);;
	}
	

}
