package cowork_Desktop.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RoomBoardDTO {
	private int boardNo;
	private int roomNo;
	private String name;
	private String title;
	
	private LocalDate date;
	
	private String category;
	private String content;
	private int available;
	
	// private ArrayList<LikeTypeDTO> likeType;
	
}
