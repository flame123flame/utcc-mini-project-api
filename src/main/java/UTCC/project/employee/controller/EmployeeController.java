package UTCC.project.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import UTCC.framework.constant.ResponseConstant;
import UTCC.framework.constant.ResponseConstant.RESPONSE_MESSAGE;
import UTCC.framework.constant.ResponseConstant.RESPONSE_STATUS;
import UTCC.framework.model.DataTableResponse;
import UTCC.framework.model.DatatableRequest;
import UTCC.framework.model.ResponseData;
import UTCC.project.employee.module.Employee;
import UTCC.project.employee.service.EmployeeService;
import UTCC.project.employee.vo.request.EmployeeReq;
import UTCC.project.employee.vo.response.EmployeeRes;
import UTCC.project.user.vo.RegisterUserReq;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("api/employee/")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

 
    @PostMapping("paginate")
    @ResponseBody
    private ResponseData<DataTableResponse<EmployeeRes>> paginate(@RequestBody DatatableRequest req){
        ResponseData<DataTableResponse<EmployeeRes>> response = new ResponseData<DataTableResponse<EmployeeRes>>();
		try {
			response.setData(employeeService.pagiante(req));
			response.setMessage(ResponseConstant.RESPONSE_MESSAGE.GET.SUCCESS);
			response.setStatus(ResponseConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ResponseConstant.RESPONSE_MESSAGE.GET.FAILED);
			response.setStatus(ResponseConstant.RESPONSE_STATUS.FAILED);
		}
		return response;
    }

 
    @PostMapping("/get-employee-code")
    @ResponseBody
    public ResponseData<EmployeeRes> getEmployee(@RequestBody EmployeeReq request) {
        ResponseData<EmployeeRes> response = new ResponseData<EmployeeRes>();
        try {
            response.setData(employeeService.getEmployee(request));
            response.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(RESPONSE_MESSAGE.GET.FAILED);
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }
    
    @PostMapping("/find-by-id")
    @ResponseBody
    public ResponseData<Employee> getFindById(@RequestBody EmployeeReq request) {
        ResponseData<Employee> response = new ResponseData<Employee>();
        try {
            response.setData(employeeService.getById(request));
            response.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(RESPONSE_MESSAGE.GET.FAILED);
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }
    
    @GetMapping("/get-by-code/{code}")
    @ResponseBody
    public ResponseData<EmployeeRes> getAll(@PathVariable("code") String code) {
        ResponseData<EmployeeRes> response = new ResponseData<EmployeeRes>();
        try {
            response.setData(employeeService.getByCode(code));
            response.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(RESPONSE_MESSAGE.GET.FAILED);
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }
    @GetMapping("/get-by-name/{code}")
    @ResponseBody
    public ResponseData<EmployeeRes> getByName(@PathVariable("code") String code) {
        ResponseData<EmployeeRes> response = new ResponseData<EmployeeRes>();
        try {
            response.setData(employeeService.getByUsername(code));
            response.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(RESPONSE_MESSAGE.GET.FAILED);
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }


    @PostMapping("/get-new")
    @ResponseBody
    public ResponseData<List<EmployeeRes>> getNewEmployee(@RequestBody EmployeeReq request) {
        ResponseData<List<EmployeeRes>> response = new ResponseData<List<EmployeeRes>>();
        try {
            response.setData(employeeService.getNewEmployee(request));
            response.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(RESPONSE_MESSAGE.GET.FAILED);
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseData<Employee> save(@RequestBody RegisterUserReq request) {
        ResponseData<Employee> response = new ResponseData<Employee>();
        try {
            response.setData(employeeService.save(request));
            response.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }

    @PostMapping("/edit")
    @ResponseBody
    public ResponseData<Employee> edit(@RequestBody EmployeeReq request) {
        ResponseData<Employee> response = new ResponseData<Employee>();
        try {
            response.setData(employeeService.edit(request));
            response.setMessage(RESPONSE_MESSAGE.EDIT.SUCCESS);
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(RESPONSE_MESSAGE.EDIT.FAILED);
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }
    
	@GetMapping("get-all")
	public ResponseData<List<Employee>> getNewsDetail()
	{
		ResponseData<List<Employee>> response = new ResponseData<List<Employee>>();
		try {
			response.setData(employeeService.getAllDetail());
			response.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
			log.info("Success Calling API NewsmanagementController => getNewsDetail");
		} catch (Exception e) {
			response.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
			log.error("Error Calling API NewsmanagementController => getNewsDetail :" + e);
		}
		return response;
	}
    @PostMapping("/delete")
    @ResponseBody
    public ResponseData<Employee> delete(@RequestBody EmployeeReq request) {
        ResponseData<Employee> response = new ResponseData<Employee>();
        try {
        	employeeService.delete(request);
            response.setMessage(RESPONSE_MESSAGE.EDIT.SUCCESS);
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(RESPONSE_MESSAGE.EDIT.FAILED);
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }
 
}
