package UTCC.project.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UTCC.framework.constant.ResponseConstant.RESPONSE_MESSAGE;
import UTCC.framework.constant.ResponseConstant.RESPONSE_STATUS;
import UTCC.framework.model.ResponseData;
import UTCC.project.work.model.TicketTrip;
import UTCC.project.work.service.TicketTripService;
import UTCC.project.work.vo.TicketTripVo;

@RestController
@RequestMapping("api/ticket-trip/")
public class TicketTripController {

	@Autowired
	private TicketTripService ticketTripService;
	
	@GetMapping("search")
	public ResponseData<List<TicketTrip>> search( ) {
		ResponseData<List<TicketTrip>> responseData = new ResponseData<>();
		try {
			responseData.setData(ticketTripService.getTicketTrip());
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
			responseData.setData(ticketTripService.getTicketTripByWorksheetId(id));
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
