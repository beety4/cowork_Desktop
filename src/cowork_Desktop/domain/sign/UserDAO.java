package cowork_Desktop.domain.sign;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import cowork_Desktop.config.Mysql;
import cowork_Desktop.dto.UserDTO;

public class UserDAO {
	private BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
	private Connection conn = Mysql.getConnection();
	
	public int login(String id, String password) {
		String query = "SELECT password FROM user_table WHERE id = ?";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				// if(rs.getString(1).equals(digest)) {
				if(bcryptEncoder.matches(password, rs.getString(1))) {
					return 0;
				} else {
					return 1;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int join(UserDTO userDTO) {
		String query = "INSERT INTO user_table(id,password,name,birth,gender,email) VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			String digest = bcryptEncoder.encode(userDTO.getPassword());
			pstmt.setString(1, userDTO.getId());
			pstmt.setString(2, digest);
			pstmt.setString(3, userDTO.getName());
			pstmt.setDate(4, (Date)userDTO.getBirth());
			pstmt.setString(5, userDTO.getGender());
			pstmt.setString(6, userDTO.getEmail());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
	
	
	
	
}
