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
@Table(name = "ticket")
public class Ticket {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "ticket_id")
	    private long ticketId;
	    
	    @Column(name = "ticket_no", length = 7)
	    private String ticketNo;
	    
	    @Column(name = "ticket_begin")
	    private Boolean ticketBegin;
	    
	    @Column(name = "ticket_end")
	    private Boolean ticketEnd;
	    
	    @Column(name = "fare_id")
	    private long fareId;
	    
	    @Column(name = "worksheet_id")
	    private long worksheetId;
	    
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
