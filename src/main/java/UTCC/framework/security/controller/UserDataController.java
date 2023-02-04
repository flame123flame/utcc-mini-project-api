package UTCC.framework.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UTCC.framework.security.service.UserDataService;
import UTCC.framework.security.vo.UserDetailResponse;
import UTCC.framework.utils.UserLoginUtil;

@RestController
@RequestMapping(value = "api/user/")
public class UserDataController {
	
	@Autowired
	private UserDataService UserDataService;
	
	@GetMapping("get-data-from-token")
	public UserDetailResponse getData() {
		return UserDataService.setResponse(UserLoginUtil.getUsername());
	}

}
