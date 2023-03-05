package UTCC.project.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import UTCC.framework.constant.ResponseConstant.RESPONSE_MESSAGE;
import UTCC.framework.constant.ResponseConstant.RESPONSE_STATUS;
import UTCC.framework.model.ResponseData;
import UTCC.project.user.service.UserService;
import UTCC.project.user.vo.RegisterUserReq;
import UTCC.project.user.vo.RegisterUserRes;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/user/")
@Slf4j
public class UserContontroller {

	@Autowired
	private UserService userService;

	
	@PostMapping("check-pin")
	public ResponseData<String> checkPin(@Valid @RequestBody RegisterUserReq req) {
		ResponseData<String> responseData = new ResponseData<>();
		try {
			responseData.setData(userService.validatePin(req));	
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error("checkPin: register ", e);
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("set-pin")
	public ResponseData<?> setPin(@Valid @RequestBody RegisterUserReq req) {
		ResponseData<?> responseData = new ResponseData<>();
		try {
			userService.setPin(req);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error("setPin: register ", e);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	
	@PostMapping("register")
	public ResponseData<String> register(@Valid @RequestBody RegisterUserReq req) {
		ResponseData<String> responseData = new ResponseData<String>();
		try {
			responseData.setData(userService.register(req));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error("UserContontroller: register ", e);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("get-user")
	public ResponseData<List<RegisterUserRes>> getListUser(@Valid @RequestBody RegisterUserReq req) {
		ResponseData<List<RegisterUserRes>> responseData = new ResponseData<List<RegisterUserRes>>();
		try {
			responseData.setData(userService.getListUser(req));
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error("UserContontroller: getListUser ", e);
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@GetMapping("get-by-id/{id}")
    @ResponseBody
	public ResponseData<RegisterUserRes> getByid(@PathVariable("id") String id) {
		ResponseData<RegisterUserRes> responseData = new ResponseData<RegisterUserRes>();
		try {
			responseData.setData(userService.getById(id));
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error("UserContontroller: deleteUser ", e);
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	
	@GetMapping("delete-user/{username}")
	public ResponseData<?> deleteUsername(@PathVariable("username") String username) {
		ResponseData<?> responseData = new ResponseData<>();
		try {
			userService.deleteUsername(username);
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error("UserContontroller: deleteUser ", e);
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
