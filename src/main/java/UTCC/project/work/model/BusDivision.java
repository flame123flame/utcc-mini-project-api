package UTCC.project.work.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bus_division")
public class BusDivision implements Serializable{
	
	private static final long serialVersionUID = 3321248948609237090L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_division_id")
    private long busDivisionId;

    @Column(name = "bus_division_no")
    private long busDivisionNo;

    @Column(name = "bus_division_name")
    private String busDivisionName;

    @Column(name = "bmta_zone")
    private long busZone;
    
    @Column(name = "bus_depot_id")
    private long busDepotId;
    
    @Column(name = "create_by", length = 50, nullable = false)
    private String createBy;

    @Column(name = "update_by", length = 50, nullable = false)
    private String updateBy;

    @Column(name = "create_date", length = 6, nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "update_date", length = 6, nullable = false)
    private LocalDateTime updateDate = LocalDateTime.now();

}
