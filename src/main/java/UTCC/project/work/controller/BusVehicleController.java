package UTCC.project.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import UTCC.project.work.model.BusVehicle;
import UTCC.project.work.service.BusVehicleService;
import UTCC.project.work.vo.BusVehicleVo;
import UTCC.project.work.vo.WorksheetVo;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/bus-vehicle/")
@Slf4j
public class BusVehicleController {

	@Autowired
	private BusVehicleService busVehicleService;
	
    @PostMapping("save")
    @ResponseBody
    public ResponseData<?> save(@RequestBody BusVehicleVo.Request request) {
        ResponseData<?> response = new ResponseData<>();
        try {
        	busVehicleService.saveForm(request);
            response.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }
    
	
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
	

	@GetMapping("delete/{id}")
	public ResponseData<?> delete(@PathVariable("id") Long id) {
		ResponseData<?> responseData = new ResponseData<>();
		try {
			busVehicleService.deleteBusVehicle(id);
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error("UserContontroller: deleteUser ", e);
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.FAILED);
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
