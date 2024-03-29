package UTCC.project.work.model;

import java.io.Serializable;
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
@Table(name = "bus_lines")
public class BusLines implements Serializable{
	
	private static final long serialVersionUID = 8245403850568328329L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_lines_id")
    private long busLinesId;
	
	@Column(name = "bus_lines_no")
    private String busLinesNo;
	
	@Column(name = "bus_lines_origin")
    private String busLinesOrigin;
	
	@Column(name = "bus_lines_destination")
    private String busLinesDestination;
	
	@Column(name = "bus_lines_expressway")
    private String busLinesExpressway;
	
	@Column(name = "bus_lines_nightshift")
    private Boolean busLinesNightshift;
	
	@Column(name = "create_by", length = 50,nullable = false)
    private String createBy;

    @Column(name = "update_by", length = 50,nullable = false)
    private String updateBy;

    @Column(name = "create_date", length = 6, nullable = false)
    private LocalDateTime createDate;

    @Column(name = "update_date", length = 6, nullable = false)
    private LocalDateTime updateDate;
}
