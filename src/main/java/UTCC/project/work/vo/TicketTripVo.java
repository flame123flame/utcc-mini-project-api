package UTCC.project.work.vo;

import java.math.BigDecimal;
import java.util.List;

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
        private List<TicketTripVo.TicketAndFare> ticketList;
        
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

}
