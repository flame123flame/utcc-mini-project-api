package UTCC.project.work.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WorksheetVo {

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime worksheetDate;
        private long worksheetId;
        private String worksheetTimeBegin;
        private String worksheetTimeEnd;
        private Long worksheetHours;
        private Long worksheetHoursOt;
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
        private Long worksheetSumTicket;
        private BigDecimal worksheetSumIncome;
        
    }
    
    @Getter
    @Setter
    public static class Response extends Request  {
     
    }
    
  
    

}
