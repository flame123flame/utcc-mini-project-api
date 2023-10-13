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
@Table(name = "ticket")
public class Ticket  implements Serializable  {
	
	private static final long serialVersionUID = 5536708434132416743L;

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "ticket_id")
	    private long ticketId;
		
		@Column(name = "ticket_trip_id")
	    private long ticketTripId;
	    
	    @Column(name = "ticket_no", length = 7)
	    private String ticketNo;
	    
	    @Column(name = "ticket_begin")
	    private Boolean ticketBegin;
	    
	    @Column(name = "ticket_end")
	    private Boolean ticketEnd;
	    
	    @Column(name = "fare_id")
	    private long fareId;
	    
	    @Column(name = "trip")
	    private long trip;
	    
	    @Column(name = "worksheet_id")
	    private long worksheetId;
	    
	    @Column(name = "create_by", length = 50,nullable = false)
	    private String createBy;

	    @Column(name = "update_by", length = 50,nullable = false)
	    private String updateBy;

	    @Column(name = "create_date", length = 6, nullable = false)
	    private LocalDateTime createDate;

	    @Column(name = "update_date", length = 6, nullable = false)
	    private LocalDateTime updateDate;

}
