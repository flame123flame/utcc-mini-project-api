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
@Table(name = "bus_terminal")
public class BusTerminal implements Serializable{
	
	private static final long serialVersionUID = 8721499111623986553L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_terminal_id")
    private long busTerminalId;
	
	@Column(name = "bus_terminal_name")
    private String busTerminalName;
	
	@Column(name = "bus_terminal_locate")
    private String busTerminalLocate;
	
	@Column(name = "create_by", length = 50,nullable = false)
    private String createBy;

    @Column(name = "update_by", length = 50,nullable = false)
    private String updatedBy;

    @Column(name = "create_date", length = 6, nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "update_date", length = 6, nullable = false)
    private LocalDateTime updateDate = LocalDateTime.now();
}
