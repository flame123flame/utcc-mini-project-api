package UTCC.project.work.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DriverVo {

	    @Builder
	    @Getter
	    @Setter
	    @AllArgsConstructor
	    @NoArgsConstructor
	    public static class Response {
	        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	        private long worksheetId;
	        private String worksheetDate;
	        private String worksheetTimeBegin;
	        private String worksheetTimeEnd;
	        private String busVehiclePlateNo;
	        private String worksheetDispatcher;
	        private String worksheetDriver;
	        private String worksheetFarecollect;
	        private String busVehicleNumber;
	        
	    }
	  
}
