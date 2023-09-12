package UTCC.project.work.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BusDivisionVo {

	@Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
		   private long busDivisionId;
    	   private long busDivisionNo;
	       private String busDivisionName;
	       private long bmtaZone;
	       private String createDate;
	       private String updateDate;
	       private String depotName;
		   private long busDepotId;
    }
	

    @Getter
    @Setter
    public static class Request extends Response {

    }


}
