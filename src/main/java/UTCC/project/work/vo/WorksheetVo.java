package UTCC.project.work.vo;

import java.time.LocalDateTime;

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
        private String worksheetTimeBegin;
        private String worksheetTimeEnd;
        private String busVehiclePlateNo;
        private String worksheetDispatcher;
        private String worksheetDriver;
        private String worksheetFarecollect;
        private String busVehicleNumber;
        
        
    }
    
    @Getter
    @Setter
    public static class Response extends Request  {
     
    }

}
