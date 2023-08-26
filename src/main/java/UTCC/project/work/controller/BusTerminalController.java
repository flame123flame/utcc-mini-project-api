package UTCC.project.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UTCC.framework.constant.ResponseConstant.RESPONSE_MESSAGE;
import UTCC.framework.constant.ResponseConstant.RESPONSE_STATUS;
import UTCC.framework.model.ResponseData;
import UTCC.project.work.model.BusTerminal;
import UTCC.project.work.service.BusTerminalService;


@RestController
@RequestMapping("api/bus-terminal/")
public class BusTerminalController {
		
	
	@Autowired
	private BusTerminalService busTerminalService;
	
	@GetMapping("search")
	public ResponseData<List<BusTerminal>> search( ) {
		ResponseData<List<BusTerminal>> responseData = new ResponseData<>();
		try {
			responseData.setData(busTerminalService.getBusTerminal());
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	
	
}
