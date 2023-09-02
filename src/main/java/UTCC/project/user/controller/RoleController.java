package UTCC.project.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UTCC.framework.constant.ResponseConstant.RESPONSE_MESSAGE;
import UTCC.framework.constant.ResponseConstant.RESPONSE_STATUS;
import UTCC.framework.model.ResponseData;
import UTCC.project.user.service.RoleService;
import UTCC.project.user.vo.GetRoleRes;
import UTCC.project.user.vo.SaveRoleReq;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/role/")
@Slf4j
public class RoleController {
	
	@Autowired
	private RoleService roleService;

	@GetMapping("get-list")
	public ResponseData<List<GetRoleRes>> getRoleList( ) {
		ResponseData<List<GetRoleRes>> responseData = new ResponseData<>();
		try {
			responseData.setData(roleService.getAllList());
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error("RoleController: getRoleList ", e);
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("find-by-id/{fwRoleId}")
	public ResponseData<GetRoleRes> findById(@PathVariable("fwRoleId") Long fwRoleId ) {
		ResponseData<GetRoleRes> responseData = new ResponseData<>();
		try {
			responseData.setData(roleService.getRoleByID(fwRoleId));
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error("RoleController: getRoleList ", e);
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	
	@PostMapping("save")
	public ResponseData<String> saveRole(@RequestBody SaveRoleReq req)  throws Exception {
		ResponseData<String> responseData = new ResponseData<>();
		try {
			if("DUPICATE_ROLECODE".equals(roleService.saveRole(req))) {
				throw new Exception("DUPICATE_ROLECODE");
			}
			responseData.setData(roleService.saveRole(req));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error("RoleController: saveRole ", e);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
			if("DUPICATE_ROLECODE".equals(roleService.saveRole(req))) {
				throw new Exception("DUPICATE_ROLECODE");
			}
		}
		return responseData;
	}
	
	@GetMapping("delete-role/{roleCode}")
	public ResponseData<?> deleteRole(@PathVariable("roleCode") String roleCode) {
		ResponseData<?> responseData = new ResponseData<>();
		try {
			roleService.deleteRole(roleCode);
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error("RoleController: deleteRole ", e);
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PutMapping("edit")
	public ResponseData<String> editRole(@RequestBody SaveRoleReq req) {
		ResponseData<String> responseData = new ResponseData<>();
		try {
			responseData.setData(roleService.editRole(req));
			responseData.setMessage(RESPONSE_MESSAGE.EDIT.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error("RoleController: editRole ", e);
			responseData.setMessage(RESPONSE_MESSAGE.EDIT.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	
	
}
