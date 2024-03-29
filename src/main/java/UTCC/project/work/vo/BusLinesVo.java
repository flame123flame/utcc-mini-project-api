package UTCC.project.work.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BusLinesVo {

	@Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
		private long busLinesId;
		private String busLinesNo;
		private String busLinesOrigin;
		private String busLinesDestination;
		private String busLinesExpressway;
		private Boolean busLinesNightshift;
	    private String createDate;
	    private String updateDate;
	    private long buslinesHbusterminalId;
		private String busTerminalName;
		private long busTerminalId;
		private List<BusTerminalVo.Request> listDetail;
	}
	
	@Getter
	@Setter
	public static class Request extends Response {
		
	}

}
