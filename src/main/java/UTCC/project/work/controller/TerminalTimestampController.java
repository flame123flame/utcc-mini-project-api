package UTCC.project.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UTCC.framework.constant.ResponseConstant.RESPONSE_MESSAGE;
import UTCC.framework.constant.ResponseConstant.RESPONSE_STATUS;
import UTCC.framework.model.ResponseData;
import UTCC.project.work.service.TerminalTimestampService;
import UTCC.project.work.vo.TerminalTimestampVo;
import UTCC.project.work.vo.TicketTripVo;

@RestController
@RequestMapping("api/timestamp/")
public class TerminalTimestampController {

	
	@Autowired
	private TerminalTimestampService terminalTimestampService;
	
	@GetMapping("waiting")
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
	
	@GetMapping("success")
	public ResponseData<List<TerminalTimestampVo.Response>> success( ) {
		ResponseData<List<TerminalTimestampVo.Response>> responseData = new ResponseData<>();
		try {
			responseData.setData(terminalTimestampService.getTerminalTimestampSucess());
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	

	@GetMapping("end")
	public ResponseData<List<TerminalTimestampVo.Response>> end() {
		ResponseData<List<TerminalTimestampVo.Response>> responseData = new ResponseData<>();
		try {
			responseData.setData(terminalTimestampService.getTerminalTimestampEnd());
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	
	@PostMapping("set-timestamp")
	public ResponseData<?> setTimestamp(@RequestBody TicketTripVo.Request req ) {
		ResponseData<?> responseData = new ResponseData<>();
		try {
			terminalTimestampService.setTimestamp(req);
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}


	@PostMapping("set-timestamp-end")
	public ResponseData<?> setTimestampEnd(@RequestBody TicketTripVo.Request req ) {
		ResponseData<?> responseData = new ResponseData<>();
		try {
			terminalTimestampService.setTimestampEnd(req);
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	
	
	
	@GetMapping("find-by-worksheet-id/{id}")
	public ResponseData<List<TicketTripVo.Response>> findByWorksheetId(@PathVariable("id") long id ) {
		ResponseData<List<TicketTripVo.Response>> responseData = new ResponseData<>();
		try {
			responseData.setData(terminalTimestampService.getTicketTripByWorksheetId(id));
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
