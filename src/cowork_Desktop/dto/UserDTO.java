package cowork_Desktop.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class UserDTO {
	private String id;
	private String password;
	private String name;
	private LocalDate birth;
	private String gender;
	private String email;
	private String joindate;
	private String img;
	private String depart;
	private String roles;
}
