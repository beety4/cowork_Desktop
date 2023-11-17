package cowork_Desktop.domain.sign;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import cowork_Desktop.config.Mysql;
import cowork_Desktop.dto.UserDTO;

public class UserDAO {
	private BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
	private Connection conn = Mysql.getConnection();
	
	// 로그인 DAO
	public int login(String id, String password) {
		String query = "SELECT password FROM user_table WHERE id = ?";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
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
	
	// 회원가입 DAO
	public int register(UserDTO userDTO) {
		String query = "INSERT INTO user_table(id,password,name,birth,gender,email) VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			String digest = bcryptEncoder.encode(userDTO.getPassword());
			
			pstmt.setString(1, userDTO.getId());
			pstmt.setString(2, digest);
			pstmt.setString(3, userDTO.getName());
			pstmt.setString(4, userDTO.getBirth());
			pstmt.setString(5, userDTO.getGender());
			pstmt.setString(6, userDTO.getEmail());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	// ID로 Name 가져오기
	public String getNameByID(String id) {
		String query = "SELECT name FROM user_table WHERE id = ?";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	// 파일해쉬 저장 DAO
	public int setFileHash(String id, String fileHash) {
		String query = "INSERT INTO user_hash_table(id, hash) VALUES(?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, fileHash);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	// id값 기준 파일해쉬 값 list로 가져오는 DAO
	public ArrayList<String> getFileHash(String id) {
		String query = "SELECT hash FROM user_hash_table WHERE id = ?";
		ArrayList<String> list = new ArrayList<>();
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// id값 기준 파일해쉬 값 중 처음 값 삭제 DAO
	public void rmFileHash(String id, String hash) {
		String query = "DELETE FROM hash_table WHERE id = ? AND hash = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, hash);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
