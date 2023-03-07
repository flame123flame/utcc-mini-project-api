package UTCC.project.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UTCC.framework.constant.ResponseConstant.RESPONSE_MESSAGE;
import UTCC.framework.constant.ResponseConstant.RESPONSE_STATUS;
import UTCC.framework.model.ResponseData;
import UTCC.project.work.model.BusVehicle;
import UTCC.project.work.service.BusVehicleService;
import UTCC.project.work.vo.BusVehicleVo;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/bus-vehicle/")
@Slf4j
public class BusVehicleController {

	@Autowired
	private BusVehicleService busVehicleService;
	
	
	@GetMapping("get-list")
	public ResponseData<List<BusVehicle>> getDropdownListBusVehicle( ) {
		ResponseData<List<BusVehicle>> responseData = new ResponseData<>();
		try {
			responseData.setData(busVehicleService.getDropdownListBusVehicle());
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error("BusVehicleController: getDropdownListBusVehicle ", e);
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("get-list-dropdown2")
	public ResponseData<List<BusVehicleVo.Response>> getDropdownList2( ) {
		ResponseData<List<BusVehicleVo.Response>> responseData = new ResponseData<>();
		try {
			responseData.setData(busVehicleService.getDropdownList());
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error("BusVehicleController: getDropdownListBusVehicle ", e);
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("get-list-dropdown")
	public ResponseData<List<BusVehicleVo.Response>> getDropdownList( ) {
		ResponseData<List<BusVehicleVo.Response>> responseData = new ResponseData<>();
		try {
			responseData.setData(busVehicleService.getDropdownList());
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error("BusVehicleController: getDropdownListBusVehicle ", e);
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
}
