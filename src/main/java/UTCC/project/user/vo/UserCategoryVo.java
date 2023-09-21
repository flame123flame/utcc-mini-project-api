package UTCC.project.user.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserCategoryVo {

	@Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
		private long userCategoryId;
		private String userCategoryCode;
		private String userCategoryName;
		private String userCategoryDesc;
		private String createDate;
		private String createBy;
		private String updateDate;
		private String updateBy;
	}
	
	@Getter
    @Setter
    public static class Request extends Response {
		
    }

}
