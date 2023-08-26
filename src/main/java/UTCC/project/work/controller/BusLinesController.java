package UTCC.project.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UTCC.framework.constant.ResponseConstant.RESPONSE_MESSAGE;
import UTCC.framework.constant.ResponseConstant.RESPONSE_STATUS;
import UTCC.framework.model.ResponseData;
import UTCC.project.work.model.BusLines;
import UTCC.project.work.service.BusLinesService;


@RestController
@RequestMapping("api/bus-lines/")
public class BusLinesController {

	@Autowired
	private BusLinesService busLinesService;
	
	@GetMapping("search")
	public ResponseData<List<BusLines>> search( ) {
		ResponseData<List<BusLines>> responseData = new ResponseData<>();
		try {
			responseData.setData(busLinesService.getBusLines());
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}