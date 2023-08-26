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
@Table(name = "bus_depot")
public class BusDepot implements Serializable {

    private static final long serialVersionUID = -839822370605016277L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_depot_id")
    private long busDepotId;

    @Column(name = "depot_name")
    private String depotName;

    @Column(name = "depot_location")
    private String depotLocation;

    @Column(name = "create_by", length = 50, nullable = false)
    private String createBy;

    @Column(name = "update_by", length = 50, nullable = false)
    private String updateBy;

    @Builder.Default
    @Column(name = "create_date", length = 6, nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @Builder.Default
    @Column(name = "update_date", length = 6, nullable = false)
    private LocalDateTime updateDate = LocalDateTime.now();
}
