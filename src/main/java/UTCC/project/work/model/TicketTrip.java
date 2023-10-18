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
@Table(name = "ticket_trip")
public class TicketTrip implements Serializable{
	
	private static final long serialVersionUID = 7291715488206045455L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_trip_id")
    private long ticketTripId;
    
	@Column(name = "worksheet_id")
    private long worksheetId;
	
	@Column(name = "trip")
    private long trip;
    
    @Column(name = "ticket_begin")
    private Boolean ticketBegin;
    
    @Column(name = "ticket_end")
    private Boolean ticketEnd;
    
    @Column(name = "create_by", length = 50,nullable = false)
    private String createBy;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "create_date", length = 6, nullable = false)
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

}
