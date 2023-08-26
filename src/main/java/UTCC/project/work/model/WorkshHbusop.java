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
@Table(name = "worksh_h_busop")
public class WorkshHbusop implements Serializable{
	
	private static final long serialVersionUID = 7861977099637532220L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worksh_h_busop_id")
    private long workshHbusopId;
	
	@Column(name = "worksheet_id")
    private long worksheetId;
	
	@Column(name = "bus_operation_id")
    private long busOperationId;
	
	@Column(name = "trip")
    private long trip;
	
	@Column(name = "create_by", length = 50,nullable = false)
    private String createBy;

    @Column(name = "update_by", length = 50,nullable = false)
    private String updatedBy;

    @Column(name = "create_date", length = 6, nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "update_date", length = 6, nullable = false)
    private LocalDateTime updateDate = LocalDateTime.now();
}
