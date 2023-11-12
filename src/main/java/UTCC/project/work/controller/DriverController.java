package UTCC.project.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UTCC.framework.constant.ConstantsWorksheetStatus;
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
	
	
	@GetMapping("/super-visor-end")
	public ResponseData<List<DriverVo.Response>> getDataSupervisorEnd() {
		ResponseData<List<DriverVo.Response>> responseData = new ResponseData<>();
		try {
			responseData.setData(driverService.getDataSuperVisor(ConstantsWorksheetStatus.END_PROGRESS));
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/super-visor-success")
	public ResponseData<List<DriverVo.Response>> getDataSupervisorSuccess() {
		ResponseData<List<DriverVo.Response>> responseData = new ResponseData<>();
		try {
			responseData.setData(driverService.getDataSuperVisor(ConstantsWorksheetStatus.SUCCESS));
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	
	
	@GetMapping("/farecollect-progress")
	public ResponseData<List<DriverVo.Response>> getDataFarecollectProgress() {
		ResponseData<List<DriverVo.Response>> responseData = new ResponseData<>();
		try {
			responseData.setData(driverService.getDataFarecollectProgress());
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/farecollect-success")
	public ResponseData<List<DriverVo.Response>> getDataFarecollectSuccess() {
		ResponseData<List<DriverVo.Response>> responseData = new ResponseData<>();
		try {
			responseData.setData(driverService.getDataFarecollectSuccess());
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/driver-progress")
	public ResponseData<List<DriverVo.Response>> getDataDriverProgress() {
		ResponseData<List<DriverVo.Response>> responseData = new ResponseData<>();
		try {
			responseData.setData(driverService.getDataDriverProgress());
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/driver-success")
	public ResponseData<List<DriverVo.Response>> getDataDriverSuccess() {
		ResponseData<List<DriverVo.Response>> responseData = new ResponseData<>();
		try {
			responseData.setData(driverService.getDataDriverSuccess());
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
}
