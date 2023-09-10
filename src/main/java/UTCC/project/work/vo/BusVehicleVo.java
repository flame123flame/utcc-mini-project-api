package UTCC.project.work.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BusVehicleVo {
   
	    @Builder
	    @Getter
	    @Setter
	    @AllArgsConstructor
	    @NoArgsConstructor
	    public static class Request {
	        private String busVehiclePlateNo;
	        private String busVehiclePlateProv;
	        private String busVehicleNumber;
	        private long busLinesId;
	        private long busTypeId;
	        private long busDivisionId;
	        private String busVehicleStatus;
	    }
	    
	    @Getter
	    @Setter
	    public static class Response extends Request{
	    	  
	    }
}
