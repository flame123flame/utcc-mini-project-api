package UTCC.project.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UTCC.framework.constant.ResponseConstant.RESPONSE_MESSAGE;
import UTCC.framework.constant.ResponseConstant.RESPONSE_STATUS;
import UTCC.framework.model.ResponseData;
import UTCC.project.user.model.UserCategory;
import UTCC.project.user.service.UserCategoryService;
import UTCC.project.user.vo.UserCategoryVo;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/user-category/")
@Slf4j
public class UserCategoryController {
	
	@Autowired
	private UserCategoryService userCategoryService;
	
	@GetMapping("get-all")
	public ResponseData<List<UserCategory>> getUserCategoryAll( ) {
		ResponseData<List<UserCategory>> responseData = new ResponseData<>();
		try {
			responseData.setData(userCategoryService.getUserCategory());
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("find-by-id/{userCategoryId}")
	public ResponseData<UserCategoryVo.Response > findById(@PathVariable("userCategoryId") Long userCategoryId) {
		ResponseData<UserCategoryVo.Response > responseData = new ResponseData<>();
		try {
			responseData.setData(userCategoryService.getUserCategoryByID(userCategoryId));
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("find-by-code/{userCategoryCode}")
	public ResponseData<UserCategoryVo.Response > findByCode(@PathVariable("userCategoryCode") String userCategoryCode) {
		ResponseData<UserCategoryVo.Response > responseData = new ResponseData<>();
		try {
			responseData.setData(userCategoryService.getUserCategoryByCode(userCategoryCode));
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("save")
	public ResponseData<String> save(@RequestBody UserCategoryVo.Request req)  throws Exception {
		ResponseData<String> responseData = new ResponseData<>();
		try {
//			if("DUPICATE_USER_CATEGORY_CODE".equals(userCategoryService.save(req))) {
//				throw new Exception("DUPICATE_USER_CATEGORY_CODE");
//			}
			responseData.setData(userCategoryService.save(req));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
//			if("DUPICATE_USER_CATEGORY_CODE".equals(userCategoryService.save(req))) {
//				throw new Exception("DUPICATE_USER_CATEGORY_CODE");
//			}
		}
		return responseData;
	}
	
	@PostMapping("edit")
	public ResponseData<String> edit(@RequestBody UserCategoryVo.Request req) {
		ResponseData<String> responseData = new ResponseData<>();
		try {
			responseData.setData(userCategoryService.edit(req));
			responseData.setMessage(RESPONSE_MESSAGE.EDIT.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(RESPONSE_MESSAGE.EDIT.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("delete/{userCategoryCode}")
	public ResponseData<?> deleteRole(@PathVariable("userCategoryCode") String userCategoryCode) {
		ResponseData<?> responseData = new ResponseData<>();
		try {
			userCategoryService.deleteUserCategory(userCategoryCode);
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
}
