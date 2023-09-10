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
import UTCC.project.work.service.BusDivisionService;
import UTCC.project.work.vo.BusDivisionVo;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/bus-division/")
@Slf4j
public class BusDivisionController {

	@Autowired
	private BusDivisionService busDivisionService;
	
	@GetMapping("search")
	public ResponseData<List<BusDivisionVo.Response>> search( ) {
		ResponseData<List<BusDivisionVo.Response>> responseData = new ResponseData<>();
		try {
			responseData.setData(busDivisionService.getList());
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
	public ResponseData<?> save(@RequestBody BusDivisionVo.Request req) {
		ResponseData<?> responseData = new ResponseData<>();
		try {
			busDivisionService.save(req);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("edit")
	public ResponseData<?> edit(@RequestBody BusDivisionVo.Request req) {
		ResponseData<?> responseData = new ResponseData<>();
		try {
			busDivisionService.edit(req);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("delete/{id}")
	public ResponseData<?> delete(@PathVariable("id") Long id) {
		ResponseData<?> responseData = new ResponseData<>();
		try {
			busDivisionService.deleteBusDivision(id);
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error("UserContontroller: deleteUser ", e);
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
}
