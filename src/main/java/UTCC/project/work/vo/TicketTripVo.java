package UTCC.project.work.vo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TicketTripVo {

	@Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private long ticketTripId;
        private long worksheetId;
        private long trip;
        private Boolean ticketBegin;
        private Boolean ticketEnd;
        private String terminalTimeDeparture;
        private String terminalTimeArrive;
        private String busTerminalDepartureDes;
        private String busTerminalArrive;
        private Boolean isTimestamp;
        private long terminalTimestampId;
        private String busTerminalAgentName;
        private List<TicketTripVo.TicketAndFare> ticketList;
        private Long worksheetSumTicket;
        private BigDecimal worksheetSumIncome;
    }
    
    @Getter
    @Setter
    public static class Response extends Request  {
    	  private BigDecimal sumPrice;
     	  private long sumTicket;
    }
    
    
    @Getter
    @Setter
    public static class TicketAndFare   {
        private String ticketNo;
        private Long fareId;
        private Long ticketTripId; 
        private String fareDesc;
        private BigDecimal fareValue;
        
    }
    
    @Getter
    @Setter
    public static class TicketTime   {
        private String busTerminalAgent;
        private String terminalTimeArrive;
        private String terminalTimeDeparture;
        private String terminalTimestampStatus;
        private String busTerminalName;
        private long terminalTimestampId;
        private long busTerminalId;
        private long trip;
     
    }

}
