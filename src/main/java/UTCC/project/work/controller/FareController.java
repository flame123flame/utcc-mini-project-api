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
import UTCC.project.work.model.Fare;
import UTCC.project.work.service.FareService;
import UTCC.project.work.vo.FareVo;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("api/fare/")
@Slf4j
public class FareController {

	@Autowired
	private FareService fareService;
	
	@GetMapping("search")
	public ResponseData<List<Fare>> search( ) {
		ResponseData<List<Fare>> responseData = new ResponseData<>();
		try {
			responseData.setData(fareService.getFare());
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
	public ResponseData<?> save(@RequestBody FareVo.Request req) {
		ResponseData<?> responseData = new ResponseData<>();
		try {
			fareService.save(req);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("edit")
	public ResponseData<?> edit(@RequestBody FareVo.Request req) {
		ResponseData<?> responseData = new ResponseData<>();
		try {
			fareService.edit(req);
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
			fareService.deleteFare(id);
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
