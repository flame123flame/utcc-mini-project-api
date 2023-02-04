package UTCC.framework.model;

import UTCC.framework.constant.ResponseConstant.RESPONSE_STATUS;
import lombok.Data;

@Data
public class ResponseData<T> {
	private RESPONSE_STATUS status = RESPONSE_STATUS.FAILED;
	private String message;
	private T data;
}
