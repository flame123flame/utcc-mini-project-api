package UTCC.project.work.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import UTCC.project.work.vo.TicketVo.Request;
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
        
    }
    
    @Getter
    @Setter
    public static class Response extends Request  {
     
    }

}
