package UTCC.project.bus.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "bus")
@Data
public class BusModel implements Serializable {
	private static final long serialVersionUID = 975475225859123194L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "bus_no", length = 255)
	private String busNo;
	
	@Column(name = "bus_plate", length = 100)
	private String busPlate;
	
	@Column(name = "bus_province", length = 50)
	private String busProvince;
	
	@Column(name = "fare", length = 255)
	private BigDecimal fare;
	
	@Column(name = "discount_fare", length = 255)
	private BigDecimal discountFare;
	
	@Column(name = "bus_type", length = 255)
	private String busType;
	
	@Column(name = "create_by", length = 25)
	private String createBy;

	@Column(name = "create_date")
	private Date createDate = new Date();

	@Column(name = "update_by", length = 25)
	private String updateBy;

	@Column(name = "update_date")
	private Date updateDate;

}
