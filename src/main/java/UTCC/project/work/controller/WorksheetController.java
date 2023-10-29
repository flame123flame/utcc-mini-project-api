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
import UTCC.project.work.service.WorksheetService;
import UTCC.project.work.vo.WorksheetVo;

@RestController
@RequestMapping("api/worksheet")
public class WorksheetController {

	@Autowired
	private WorksheetService worksheetService;
	
    @PostMapping("/save")
    @ResponseBody
    public ResponseData<?> save(@RequestBody WorksheetVo.Request request) {
        ResponseData<?> response = new ResponseData<>();
        try {
        	worksheetService.saveFrom(request);
            response.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }
    
	@PostMapping("/get-list-progress")
    @ResponseBody
	public ResponseData<List<WorksheetVo.Response>> getDropdownListBusVehicleProgress(@RequestBody WorksheetVo.Request request ) {
		ResponseData<List<WorksheetVo.Response>> responseData = new ResponseData<>();
		try {
			responseData.setData(worksheetService.getList("IN_PROGRESS"));
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/get-list-success")
    @ResponseBody
	public ResponseData<List<WorksheetVo.Response>> getDropdownListBusVehicle(@RequestBody WorksheetVo.Request request  ) {
		ResponseData<List<WorksheetVo.Response>> responseData = new ResponseData<>();
		try {
			responseData.setData(worksheetService.getList("SUCCESS"));
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/update-status/{id}")
	public ResponseData<?> updateStatus(@PathVariable("id") Long id) {
		ResponseData<?> responseData = new ResponseData<>();
		try {
			worksheetService.upDateStatus(id);
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
    
}
