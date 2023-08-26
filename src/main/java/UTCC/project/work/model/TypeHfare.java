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
@Table(name = "type_h_fare")
public class TypeHfare implements Serializable {

    private static final long serialVersionUID = 376529227784843628L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_h_fare_id")
    private long typeHfareId;

    @Column(name = "bus_type_id")
    private long typeId;

    @Column(name = "fare_id")
    private long fareId;

    @Column(name = "create_by", length = 50, nullable = false)
    private String createBy;

    @Column(name = "update_by", length = 50, nullable = false)
    private String updatedBy;

    @Builder.Default
    @Column(name = "create_date", length = 6, nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @Builder.Default
    @Column(name = "update_date", length = 6, nullable = false)
    private LocalDateTime updateDate = LocalDateTime.now();
}
