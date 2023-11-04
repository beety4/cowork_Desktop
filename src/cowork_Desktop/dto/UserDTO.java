package cowork_Desktop.dto;

import java.util.Date;
import lombok.Data;

@Data
public class UserDTO {
	private String id;
	private String password;
	private String name;
	private Date birth;
	private String gender;
	private String email;
	private String joindate;
	private String img;
	private String depart;
	private String roles;
}
