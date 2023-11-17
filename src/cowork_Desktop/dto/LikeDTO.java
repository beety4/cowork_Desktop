package cowork_Desktop.dto;

import lombok.Data;

@Data
public class LikeDTO {
	private int likeNo;
	private int boardNo;
	private String name;
	private int likeType;
}
