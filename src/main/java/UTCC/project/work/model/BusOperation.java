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
@Table(name = "bus_operation")
public class BusOperation implements Serializable{
	
	private static final long serialVersionUID = -8327782600880500873L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_operation_id")
    private long busOperationId;
	
	@Column(name = "bus_operation_desc")
    private String busOperationDesc;
	
	@Column(name = "create_by", length = 50,nullable = false)
    private String createBy;

    @Column(name = "update_by", length = 50,nullable = false)
    private String updateBy;

    @Column(name = "create_date", length = 6, nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "update_date", length = 6, nullable = false)
    private LocalDateTime updateDate = LocalDateTime.now();
}
