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
    	   private String busDivisionNo;
	       private String busDivisionName;
	       private String bmtaZone;
	       private String createDate;
	       private String updateDate;
	       private String depotName;
    }

}
