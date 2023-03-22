package UTCC.project.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UTCC.framework.constant.ResponseConstant.RESPONSE_MESSAGE;
import UTCC.framework.constant.ResponseConstant.RESPONSE_STATUS;
import UTCC.framework.model.ResponseData;
import UTCC.project.work.service.DriverService;
import UTCC.project.work.vo.DriverVo;

@RestController
@RequestMapping("api/driver")
public class DriverController {
	
	
	@Autowired
	private DriverService driverService;
	
	
	@GetMapping("/farecollect")
	public ResponseData<List<DriverVo.Response>> getDataFarecollect() {
		ResponseData<List<DriverVo.Response>> responseData = new ResponseData<>();
		try {
			responseData.setData(driverService.getDataFarecollect());
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/driver")
	public ResponseData<List<DriverVo.Response>> getDataDriver() {
		ResponseData<List<DriverVo.Response>> responseData = new ResponseData<>();
		try {
			responseData.setData(driverService.getDataDriver());
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
}
