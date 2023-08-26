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
@Builder
@Entity
@Table(name = "buslines_h_busterminal")
public class BuslinesHbusterminal implements Serializable{
	
	private static final long serialVersionUID = -8844034447641050716L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buslines_h_busterminal_id")
    private long buslinesHbusterminalId;
	
	@Column(name = "bus_lines_id")
    private long busLinesId;
	
	@Column(name = "bus_terminal_id")
    private long busTerminalId;
	
	@Column(name = "create_by", length = 50,nullable = false)
    private String createBy;

    @Column(name = "update_by", length = 50,nullable = false)
    private String updatedBy;

    @Builder.Default
    @Column(name = "create_date", length = 6, nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @Builder.Default
    @Column(name = "update_date", length = 6, nullable = false)
    private LocalDateTime updateDate = LocalDateTime.now();
}
