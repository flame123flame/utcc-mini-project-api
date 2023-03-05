package UTCC.project.work.model;

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
@Builder
@Entity
@Table(name = "bus_lines")
public class BusLines {
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
	
	@Column(name = "bus_lines_express")
    private String busLinesExpress;
	
	@Column(name = "bus_lines_nightshift")
    private long busLinesNightshift;
	
	@Column(name = "create_by", length = 50,nullable = false)
    private String createBy;

    @Column(name = "updated_by", length = 50,nullable = false)
    private String updatedBy;

    @Builder.Default
    @Column(name = "create_date", length = 6, nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @Builder.Default
    @Column(name = "update_date", length = 6, nullable = false)
    private LocalDateTime updateDate = LocalDateTime.now();
}
