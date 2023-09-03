package UTCC.project.work.vo;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FareVo {

	@Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
		private long fareId;
		private BigDecimal fareValue;
	    private String fareDesc;
	    private String createDate;
	    private String updateDate;
	}
	
	@Getter
	@Setter
	public static class Request extends Response {
 	   
	}

}
