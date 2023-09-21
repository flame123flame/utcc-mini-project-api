package UTCC.project.user.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_category")
@Data
public class UserCategory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4980441923496770510L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_category_id")
	private Long userCategoryId;

	@Column(name = "user_category_code")
	private String userCategoryCode;

	@Column(name = "user_category_name")
	private String userCategoryName;
	
	@Column(name = "user_category_desc")
	private String userCategoryDesc;
	
	@Column(name = "create_by", length = 25)
	private String createBy;

	@Column(name = "create_date")
	private LocalDateTime createDate;

	@Column(name = "update_by", length = 25)
	private String updateBy;

	@Column(name = "update_date")
	private LocalDateTime updateDate;
	
}
