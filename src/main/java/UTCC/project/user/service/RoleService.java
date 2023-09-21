package UTCC.project.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.framework.utils.ConvertDateUtils;
import UTCC.framework.utils.UserLoginUtil;
import UTCC.project.user.model.FwRole;
import UTCC.project.user.repo.FwRoleRepo;
import UTCC.project.user.vo.GetRoleRes;
import UTCC.project.user.vo.SaveRoleReq;

@Service
public class RoleService {

	@Autowired
	private FwRoleRepo fwRoleRepo;


	public List<GetRoleRes> getAllList() {
		List<GetRoleRes> dataRes = new ArrayList<GetRoleRes>();
		GetRoleRes dataSet;

		// loop set dataRes
		List<FwRole> fwRole = (List<FwRole>) fwRoleRepo.findAll();

		for (FwRole fwRoleSet : fwRole) {
			dataSet = new GetRoleRes();
			dataSet.setFwRoleId(fwRoleSet.getFwRoleId());
			dataSet.setRoleCode(fwRoleSet.getRoleCode());
			dataSet.setMenuList(fwRoleSet.getMenuList().split(","));
			dataSet.setRoleName(fwRoleSet.getRoleName());
			dataSet.setRoleDescription(fwRoleSet.getRoleDescription());
			dataSet.setCreateBy(fwRoleSet.getCreateBy());
			dataSet.setCreateDate(ConvertDateUtils.formatDateToString(fwRoleSet.getCreateDate(), ConvertDateUtils.DD_MM_YYYY_HHMM));
			dataSet.setUpdateBy(fwRoleSet.getUpdateBy());
			dataSet.setUpdateDate(ConvertDateUtils.formatDateToString(fwRoleSet.getUpdateDate(), ConvertDateUtils.DD_MM_YYYY_HHMM));
			dataSet.setPlatform(fwRoleSet.getPlatform());
			dataSet.setUserCategoryCode(fwRoleSet.getUserCategoryCode());
			dataRes.add(dataSet);
	
		}
		return dataRes;
	}

	public GetRoleRes getRoleByID(Long fwRoleId)  throws Exception  {
		GetRoleRes dataRes = new GetRoleRes();
		FwRole fwRole = fwRoleRepo.findById(fwRoleId).get();
		dataRes.setFwRoleId(fwRole.getFwRoleId());
		dataRes.setRoleCode(fwRole.getRoleCode());
		dataRes.setRoleName(fwRole.getRoleName());
		dataRes.setRoleDescription(fwRole.getRoleDescription());
		dataRes.setCreateBy(fwRole.getCreateBy());
		dataRes.setMenuList(fwRole.getMenuList().split(","));
		dataRes.setCreateDate(ConvertDateUtils.formatDateToString(fwRole.getCreateDate(), ConvertDateUtils.DD_MM_YYYY_HHMM));
		dataRes.setUpdateBy(fwRole.getUpdateBy());
		dataRes.setUpdateDate(ConvertDateUtils.formatDateToString(fwRole.getUpdateDate(), ConvertDateUtils.DD_MM_YYYY_HHMM));
		dataRes.setPlatform(fwRole.getPlatform());
		dataRes.setUserCategoryCode(fwRole.getUserCategoryCode());
		return dataRes;
	}

	public GetRoleRes getRoleByRoleCode(String roleCode)  throws Exception  {

		GetRoleRes dataRes = new GetRoleRes();
	
		FwRole fwRole = fwRoleRepo.findByRoleCode(roleCode);
		dataRes.setFwRoleId(fwRole.getFwRoleId());
		dataRes.setRoleCode(fwRole.getRoleCode());
		dataRes.setRoleName(fwRole.getRoleName());
		dataRes.setMenuList(fwRole.getMenuList().split(","));
		dataRes.setRoleDescription(fwRole.getRoleDescription());
		dataRes.setCreateBy(fwRole.getCreateBy());
		dataRes.setCreateDate(ConvertDateUtils.formatDateToString(fwRole.getCreateDate(), ConvertDateUtils.DD_MM_YYYY_HHMM));
		dataRes.setUpdateBy(fwRole.getUpdateBy());
		dataRes.setUpdateDate(ConvertDateUtils.formatDateToString(fwRole.getUpdateDate(), ConvertDateUtils.DD_MM_YYYY_HHMM));
		dataRes.setPlatform(fwRole.getPlatform());
		dataRes.setUserCategoryCode(fwRole.getUserCategoryCode());
	
		return dataRes;
	}

	@Transactional
	public String saveRole(SaveRoleReq req) {
		FwRole findDup = fwRoleRepo.findByRoleCode(req.getRoleCode());
		if (findDup != null) {
			return "DUPICATE_ROLECODE";
		}
		// save Role
		FwRole fwRole = new FwRole();
		fwRole.setRoleCode(req.getRoleCode());
		fwRole.setRoleName(req.getRoleName());
		fwRole.setRoleDescription(req.getRoleDescription());
		fwRole.setMenuList(req.getMenuList());	
		fwRole.setCreateBy(UserLoginUtil.getUsername());
		fwRole.setPlatform(req.getPlatform());
		fwRole.setUserCategoryCode(req.getUserCategoryCode());
		fwRole.setCreateDate(new Date());
		fwRoleRepo.save(fwRole);
		return req.getRoleCode();
	}

	@Transactional
	public void deleteRole(String roleCode) {
		
		fwRoleRepo.deleteByRoleCode(roleCode);
	}

	@Transactional
	public String editRole(SaveRoleReq req) {
		FwRole fwRole = fwRoleRepo.findById(req.getFwRoleId()).get();
		fwRole.setRoleCode(req.getRoleCode());
		fwRole.setRoleName(req.getRoleName());
		fwRole.setRoleDescription(req.getRoleDescription());
		fwRole.setMenuList(req.getMenuList());	
		fwRole.setRoleDescription(req.getRoleDescription());
		fwRole.setUpdateBy(UserLoginUtil.getUsername());
		fwRole.setUpdateDate(new Date());
		fwRole.setUserCategoryCode(req.getUserCategoryCode());
		fwRoleRepo.save(fwRole);

		return req.getRoleCode();
	}

	
	
	
	
}
