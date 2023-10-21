package UTCC.project.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UTCC.framework.constant.ResponseConstant.RESPONSE_MESSAGE;
import UTCC.framework.constant.ResponseConstant.RESPONSE_STATUS;
import UTCC.framework.model.ResponseData;
import UTCC.project.work.service.TerminalTimestampService;
import UTCC.project.work.vo.TerminalTimestampVo;

@RestController
@RequestMapping("api/timestamp/")
public class TerminalTimestampController {

	
	@Autowired
	private TerminalTimestampService terminalTimestampService;
	
	@GetMapping("get")
	public ResponseData<List<TerminalTimestampVo.Response>> search( ) {
		ResponseData<List<TerminalTimestampVo.Response>> responseData = new ResponseData<>();
		try {
			responseData.setData(terminalTimestampService.getTerminalTimestamp());
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
