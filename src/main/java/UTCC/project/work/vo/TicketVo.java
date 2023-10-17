package UTCC.project.work.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TicketVo {

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String ticketNo;
        private String ticketNoSum;
        private String sumPrice;
        private Boolean ticketBegin;
        private Boolean ticketEnd;
        private long fareId;
        private long worksheetId;
        private long trip;
        private long busTerminalId;
        private List<RequesTicket> typeHfare;
    } 
    
    @Getter
    @Setter
    public static class Response extends Request  {
     
    }
    
    
    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestDropdown {
        private String busTerminalName;
        private Long busTerminalId;
    }
    
    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequesTicket {
        private long fareId;
        private String ticketNo;
    }
    
}
