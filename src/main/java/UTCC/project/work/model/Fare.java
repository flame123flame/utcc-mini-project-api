package UTCC.project.work.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "fare")
public class Fare implements Serializable{
	
	private static final long serialVersionUID = 6013050480005215177L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fare_id")
    private long fareId;
	
	@Column(name = "fare_desc")
    private String fareDesc;
	
	@Column(name = "fare_value")
    private BigDecimal fareValue;
	
	@Column(name = "create_by", length = 50,nullable = false)
    private String createBy;

    @Column(name = "update_by", length = 50,nullable = false)
    private String updateBy;

    @Column(name = "create_date", length = 6, nullable = false)
    private LocalDateTime createDate;

    @Column(name = "update_date", length = 6, nullable = false)
    private LocalDateTime updateDate;
}
