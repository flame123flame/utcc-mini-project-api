package UTCC.project.work.vo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class TerminalTimestampVo {


	@Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
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
	        private long trip;
	        private long busVehicleId;
	        private String busVehiclePlateNo;
	        private String busVehicleNumber;
	        private String worksheetDispatcher;
	        private String worksheetDriver;
	        private String worksheetFarecollect;
	        private String worksheetTerminalAgent;
	        private String worksheetBuslinesManager;
	        private String worksheetStatus;
	        private String terminalTimestampStatus;
	        
	}
	

	
}
