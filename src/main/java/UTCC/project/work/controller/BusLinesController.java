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
import UTCC.project.work.model.BusLines;
import UTCC.project.work.service.BusLinesService;
import UTCC.project.work.vo.BusLinesVo;


@RestController
@RequestMapping("api/bus-lines/")
public class BusLinesController {

	@Autowired
	private BusLinesService busLinesService;
	
	
	@PostMapping("find-by-id")
	public ResponseData<BusLinesVo.Request > findById( @RequestBody BusLinesVo.Request  req ) {
		ResponseData<BusLinesVo.Request > responseData = new ResponseData<>();
		try {
			responseData.setData(busLinesService.findById(req));
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("find-by-bus-lines-id")
	public ResponseData<List<BusLinesVo.Response> > findByBusLinesId( @RequestBody BusLinesVo.Request  req ) {
		ResponseData<List<BusLinesVo.Response> > responseData = new ResponseData<>();
		try {
			responseData.setData(busLinesService.getBusLinesById(req.getBusLinesId()));
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("search-all")
	public ResponseData<List<BusLines>> search( ) {
		ResponseData<List<BusLines>> responseData = new ResponseData<>();
		try {
			responseData.setData(busLinesService.getBusLines());
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("search")
	public ResponseData<List<BusLinesVo.Response>> searchDao( ) {
		ResponseData<List<BusLinesVo.Response>> responseData = new ResponseData<>();
		try {
			responseData.setData(busLinesService.getList());
			responseData.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("save")
	public ResponseData<?> save(@RequestBody BusLinesVo.Request req) {
		ResponseData<?> responseData = new ResponseData<>();
		try {
			busLinesService.save(req);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("edit")
	public ResponseData<?> edit(@RequestBody BusLinesVo.Request req) {
		ResponseData<?> responseData = new ResponseData<>();
		try {
			busLinesService.editNew(req);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("delete/{id}")
	public ResponseData<?> delete(@PathVariable("id") Long id) throws Exception{
		ResponseData<?> responseData = new ResponseData<>();
		try {
			busLinesService.deleteBusLines(id);
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
			throw new Exception("DELETE_FAILED", e);
		}
		return responseData;
	}

}
