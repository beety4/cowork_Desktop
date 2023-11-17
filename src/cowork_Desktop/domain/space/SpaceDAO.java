package cowork_Desktop.domain.space;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import cowork_Desktop.config.Mysql;
import cowork_Desktop.dto.RoomDTO;
import cowork_Desktop.dto.SpaceDTO;
import cowork_Desktop.dto.SpaceUserDTO;

public class SpaceDAO {
	private Connection conn = Mysql.getConnection();
	
	// space 제작
	public void createSpace(SpaceDTO spaceDTO) {
		String query = "INSERT INTO space_table (spaceName, spaceImg, owner) "
					 + "VALUES (?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, spaceDTO.getSpaceName());
			pstmt.setString(2, spaceDTO.getSpaceImg());
			pstmt.setString(3, spaceDTO.getOwner());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	// space 맴버 추가
	public void addSpaceMember(SpaceUserDTO spaceUserDTO) {
		String query = "INSERT space_user_table (spaceNo, name) "
					 + "VALUES (?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, spaceUserDTO.getSpaceNo());
			pstmt.setString(2, spaceUserDTO.getName());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	// Name으로 Space 정보 가져오기
	public ArrayList<SpaceDTO> getSpaceByName(String name) {
		String query = "SELECT space_table.* FROM space_table "
					 + "INNER JOIN space_user_table ON space_table.spaceNo = space_user_table.spaceNo "
					 + "WHERE space_user_table.name = ?";
		ArrayList<SpaceDTO> spaceList = new ArrayList<>();
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SpaceDTO spaceDTO = new SpaceDTO();
				spaceDTO.setSpaceNo(rs.getInt(1));
				spaceDTO.setSpaceName(rs.getString(2));
				spaceDTO.setSpaceImg(rs.getString(3));
				spaceDTO.setOwner(rs.getString(4));
				spaceList.add(spaceDTO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return spaceList;
	}
	
	// 파라미터의 유저가 이 Space에 존재하는지 확인
	public boolean isIncludeSpace(SpaceUserDTO spaceUserDTO) {
		String query = "SELECT spaceNo FROM space_user_table "
					 + "WHERE name = ? and spaceNo = ?";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, spaceUserDTO.getName());
			pstmt.setInt(2, spaceUserDTO.getSpaceNo());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// spaceNo의 Room 목록 가져오기
	public ArrayList<RoomDTO> getRoom(int spaceNo) {
		String query = "SELECT * FROM room_table WHERE spaceNo = ?";
		ArrayList<RoomDTO> roomList = new ArrayList<>();
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, spaceNo);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				RoomDTO roomDTO = new RoomDTO();
				roomDTO.setRoomNo(rs.getInt(1));
				roomDTO.setSpaceNo(rs.getInt(2));
				roomDTO.setRoomName(rs.getString(3));
				roomDTO.setRoomType(rs.getString(4));
				roomList.add(roomDTO);
			}
		} catch(Exception e) { 
			e.printStackTrace();
		}
		return roomList;
	}
	
	// 마지막 space 번호 가져오기
	public int getLastSpaceNo() {
		String query = "SELECT MAX(spaceNo) FROM space_table";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1) + 1;
			} else {
				return 1;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	// space에 기본적으로 추가되는 room 게시판 데이터 만들기
	public void createSampleRoomData1(int spaceNo) {
		String query ="INSERT INTO room_table(spaceNo, roomName, roomType) "
					 + "VALUES(?, '게시판', 'BOARD')";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, spaceNo);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	// space에 기본적으로 추가되는 room 채팅방 데이터 만들기
	public void createSampleRoomData2(int spaceNo) {
		String query = "INSERT INTO room_table(spaceNo, roomName, roomType) "
					 + "VALUES(?, '채팅방', 'CHAT')";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, spaceNo);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	// spaceNo로 space 정보 가져오기
	public SpaceDTO getSpace(int spaceNo) {
		String query = "SELECT * FROM space_table WHERE spaceNo = #{spaceNo}";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, spaceNo);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				SpaceDTO spaceDTO = new SpaceDTO();
				spaceDTO.setSpaceNo(rs.getInt(1));
				spaceDTO.setSpaceName(rs.getString(2));
				spaceDTO.setSpaceImg(rs.getString(3));
				spaceDTO.setOwner(rs.getString(4));
				return spaceDTO;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
