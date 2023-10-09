package UTCC.project.work.vo;

import java.math.BigDecimal;

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
	        private BigDecimal worksheetHours;
	        private BigDecimal worksheetHoursOt;
	        private Long busLinesId;
	        private String busLinesNo;
	        private Long busDivisionId;
	        private String busDivisionName;
	        private long busVehicleId;
	        private String busVehiclePlateNo;
	        private String busVehicleNumber;
	        private String worksheetDispatcher;
	        private String worksheetDriver;
	        private String worksheetFarecollect;
	        private String worksheetTerminalAgent;
	        private String worksheetBuslinesManager;
	        private String worksheetStatus;
	    }
	  
}
