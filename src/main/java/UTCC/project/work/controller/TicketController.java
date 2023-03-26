package UTCC.project.work.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import UTCC.framework.constant.ResponseConstant.RESPONSE_MESSAGE;
import UTCC.framework.constant.ResponseConstant.RESPONSE_STATUS;
import UTCC.framework.model.ResponseData;
import UTCC.project.work.model.Ticket;
import UTCC.project.work.service.TicketService;
import UTCC.project.work.vo.TicketVo;
@RestController
@RequestMapping("api/ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;
	
	
    @PostMapping("/save")
    @ResponseBody
    public ResponseData<?> save(@RequestBody TicketVo.Request request) {
        ResponseData<?> response = new ResponseData<>();
        try {
        	TicketService.saveFrom(request);
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
	public ResponseData<List<Ticket>> getDropdownListBusVehicle( ) {
		ResponseData<List<Ticket>> responseData = new ResponseData<>();
		try {
			responseData.setData(TicketService.getList());
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

    
}
