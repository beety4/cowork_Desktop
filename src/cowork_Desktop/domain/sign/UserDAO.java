package cowork_Desktop.domain.sign;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import cowork_Desktop.domain.Mysql;
import cowork_Desktop.dto.UserDTO;

public class UserDAO {
	Connection conn = Mysql.getConnection();
	
	public int login(String id, String password) {
		String query = "SELECT password FROM user_table WHERE id = ?";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			String digest = Encrypt.getSHA512(password);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(digest)) {
					return 0;
				} else {
					return 1;
				}
			}
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int join(UserDTO userDTO) {
		String SQL = "INSERT INTO user_table (id,pw,name,email,emailcheck) VALUES (?,?,?,?,0)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			String digest = Encrypt.getSHA512(userDTO.getPassword());
			pstmt.setString(1, userDTO.getId());
			pstmt.setString(2, digest);
			pstmt.setString(3, userDTO.getName());
			pstmt.setString(4, userDTO.getEmail());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
	
	
	
	
}
