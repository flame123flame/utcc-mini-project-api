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
@Table(name = "worksheet")
public class Worksheet implements Serializable {

    private static final long serialVersionUID = 8236012833776542908L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worksheet_id")
    private Long worksheetId;
   
    @Column(name = "worksheet_date", nullable = false)
    private LocalDateTime worksheetDate;
    
    @Column(name = "worksheet_time_begin", nullable = false)
    private String worksheetTimeBegin;
    
    @Column(name = "worksheet_time_end", nullable = false)
    private String worksheetTimeEnd;
    
    @Column(name = "worksheet_hours", nullable = false)
    private BigDecimal worksheetHours;
    
    @Column(name = "worksheet_hours_ot", nullable = false)
    private BigDecimal worksheetHoursOt;
    
    @Column(name = "worksheet_status", nullable = false)
    private String worksheetStatus;
    
    @Column(name = "bus_vehicle_plate_no", nullable = false)
    private String busVehiclePlateNo;
    
    @Column(name = "worksheet_dispatcher", nullable = false)
    private String worksheetDispatcher;
    
    @Column(name = "worksheet_driver", nullable = false)
    private String worksheetDriver;
    
    @Column(name = "worksheet_farecollect", nullable = false)
    private String worksheetFarecollect;
    
    @Column(name = "worksheet_buslines_manager", nullable = false)
    private String worksheetBuslinesManager;
    
    @Column(name = "bus_lines_id")
    private Long busLinesId;
    
    @Column(name = "bus_division_id")
    private Long busDivisionId;
    
    @Column(name = "create_by", length = 50,nullable = false)
    private String createBy;

    @Column(name = "update_by", length = 50,nullable = false)
    private String updateBy;
    
    @Column(name = "create_date", length = 6, nullable = false)
    private LocalDateTime createDate;


    @Column(name = "update_date", length = 6, nullable = false)
    private LocalDateTime updateDate;
}
