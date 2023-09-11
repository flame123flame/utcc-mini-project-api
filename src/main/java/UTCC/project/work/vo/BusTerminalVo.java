package UTCC.project.work.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BusTerminalVo {

	@Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
		private long busTerminalId;
		private String busTerminalName;
	    private String createDate;
	    private String updateDate;
	}
	
	@Getter
	@Setter
	public static class Request extends Response {
 	   
	}

}
