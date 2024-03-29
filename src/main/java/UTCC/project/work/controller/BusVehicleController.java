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

@RestController
@RequestMapping("api/bus-vehicle/")
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
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("find-by-worksheet-id")
    @ResponseBody
	public ResponseData<List<BusVehicleVo.ResponseTicket>> getDropdownListBusVehicle(@RequestBody BusVehicleVo.Request request ) {
		ResponseData<List<BusVehicleVo.ResponseTicket> > responseData = new ResponseData<>();
		try {
			responseData.setData(busVehicleService.getDataByWorksheetId(request.getWorksheetId()));
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@GetMapping("search")
	public ResponseData<List<BusVehicleVo.Response>> getListBusVehicle( ) {
		ResponseData<List<BusVehicleVo.Response>> responseData = new ResponseData<>();
		try {
			responseData.setData(busVehicleService.getList());
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("edit")
	public ResponseData<?> edit(@RequestBody BusVehicleVo.Request request) {
		ResponseData<?> responseData = new ResponseData<>();
		try {
			busVehicleService.editForm(request);
			responseData.setMessage(RESPONSE_MESSAGE.EDIT.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(RESPONSE_MESSAGE.EDIT.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("delete/{id}")
	public ResponseData<?> delete(@PathVariable("id") Long id) throws Exception{
		ResponseData<?> responseData = new ResponseData<>();
		try {
			busVehicleService.deleteBusVehicle(id);
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
			throw new Exception("DELETE_FAILED", e);
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
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
}
