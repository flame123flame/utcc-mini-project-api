
package UTCC.project.bus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UTCC.framework.constant.ResponseConstant.RESPONSE_MESSAGE;
import UTCC.framework.constant.ResponseConstant.RESPONSE_STATUS;
import UTCC.framework.model.ResponseData;
import UTCC.project.bus.service.BusService;
import UTCC.project.bus.vo.BusReq;
import UTCC.project.bus.vo.BusRes;
import UTCC.project.user.vo.GetRoleRes;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("api/bus/")
@Slf4j
public class BusController {
	
	@Autowired
	private BusService busService;
	
	@PostMapping("save")
	public ResponseData<?> saveBus(@RequestBody BusReq req) {
		ResponseData<?> responseData = new ResponseData<>();
		try {
			busService.saveBus(req);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error("BusController: saveBus ", e);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("get-list")
	public ResponseData<List<BusRes>> getBusList( ) {
		ResponseData<List<BusRes>> responseData = new ResponseData<>();
		try {
			responseData.setData(busService.getAllList());
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error("BusController: BusRes ", e);
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
}
