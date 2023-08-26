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
@Table(name = "bus_vehicle")
public class BusVehicle implements Serializable {

    private static final long serialVersionUID = 6440842463197306528L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_vehicle_id")
    private long busVehicleId;

    @Column(name = "bus_vehicle_plate_no", length = 15, nullable = false)
    private String busVehiclePlateNo;

    @Column(name = "bus_vehicle_plate_prov", length = 100, nullable = false)
    private String busVehiclePlateProv;

    @Column(name = "bus_vehicle_number", length = 15, nullable = false)
    private String busVehicleNumber;

    @Column(name = "bus_lines_id")
    private long busLinesId;

    @Column(name = "bus_type_id")
    private long busTypeId;

    @Column(name = "bus_division_id")
    private long busDivisionId;

    @Column(name = "bus_vehicle_status")
    private long busVehicleStatus;

    @Column(name = "create_by", length = 50, nullable = false)
    private String createBy;

    @Column(name = "update_by", length = 50, nullable = false)
    private String updateBy;

    @Column(name = "create_date", length = 6, nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "update_date", length = 6, nullable = false)
    private LocalDateTime updateDate = LocalDateTime.now();

}
