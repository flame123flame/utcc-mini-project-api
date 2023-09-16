package UTCC.project.work.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BusDepotVo {

	@Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
		private long busDepotId;
		private String depotName;
		private String depotLat;
		private String depotLong;
		private String createDate;
		private String updateDate;
	}
	
	@Getter
    @Setter
    public static class Request extends Response {
    	   
    }

}
