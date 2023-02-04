package UTCC.project.bus.vo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class BusReq {
	private Long id;
	private String busNo;
	private BigDecimal fare;
	private BigDecimal discountFare;
	private String busType;
	private String busPlate;
	private String busProvince;
}
