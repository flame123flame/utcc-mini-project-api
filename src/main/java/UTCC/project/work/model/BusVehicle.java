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
@Table(name = "bus_vehicle")
public class BusVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_vehicle_id")
    private long busVehicleId;
    
    @Column(name = "bus_vehicle_plate_no",length = 15 ,nullable = false)
    private String busVehiclePlateNo;
    
    @Column(name = "bus_vehicle_plate_prov",length = 100 ,nullable = false)
    private String busVehiclePlateProv;
    
    @Column(name = "bus_vehicle_number",length = 15 ,nullable = false)
    private String busVehicleNumber;
    
    @Column(name = "bus_lines_id")
    private long busLinesId;
    
    @Column(name = "type_id")
    private long typeId;
    
    @Column(name = "bus_division_id")
    private long busDivisionId;
    
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
