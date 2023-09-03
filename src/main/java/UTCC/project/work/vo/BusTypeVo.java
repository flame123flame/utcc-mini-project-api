package UTCC.project.work.vo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BusTypeVo {

	@Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
		private long busTypeId;
		private String busTypeName;
	    private String createDate;
	    private String updateDate;
	    private long typeHfareId;
	    private BigDecimal fareValue;
	    private String fareDesc;
	}
	
	@Getter
	@Setter
	public static class Request extends Response {
		private long fareId;
	}

}
