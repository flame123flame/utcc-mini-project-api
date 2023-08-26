package UTCC.project.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UTCC.framework.constant.ResponseConstant.RESPONSE_MESSAGE;
import UTCC.framework.constant.ResponseConstant.RESPONSE_STATUS;
import UTCC.framework.model.ResponseData;
import UTCC.project.work.model.BusType;
import UTCC.project.work.service.BusTypeService;

@RestController
@RequestMapping("api/bus-type/")
public class BusTypeController {

	@Autowired
	private BusTypeService busTypeService;
	
	@GetMapping("search")
	public ResponseData<List<BusType>> search( ) {
		ResponseData<List<BusType>> responseData = new ResponseData<>();
		try {
			responseData.setData(busTypeService.getBusType());
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
