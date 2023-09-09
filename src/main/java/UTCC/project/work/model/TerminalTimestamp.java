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
@Table(name = "terminal_timestamp")
public class TerminalTimestamp implements Serializable{
	
	private static final long serialVersionUID = 8364012181735537827L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "terminal_timestamp_id")
    private long terminalTimestampId;
	
	@Column(name = "worksheet_id")
    private long worksheetId;
	
	@Column(name = "bus_terminal_id")
    private long busTerminalId;
	
	@Column(name = "trip")
    private long trip;
	
	@Column(name = "terminal_time_arrive")
    private String terminalTimeArrive;
	
	@Column(name = "terminal_time_departure")
    private String terminalTimeDeparture;
	
	@Column(name = "bus_terminal_agent")
    private String busTerminalAgent;
	
	@Column(name = "create_by", length = 50,nullable = false)
    private String createBy;

    @Column(name = "update_by", length = 50,nullable = false)
    private String updateBy;

    @Column(name = "create_date", length = 6, nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "update_date", length = 6, nullable = false)
    private LocalDateTime updateDate = LocalDateTime.now();
}
