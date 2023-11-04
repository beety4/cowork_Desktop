package cowork_Desktop.config;

import java.awt.Image;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class CustomUtility {
	// JLabel 혹은 JButton에 이미지 삽입과 크기를 조정하는 메소드
	public <T> void setImg(T jImg, String path, int w, int h) {
		Image icon = new ImageIcon(path).getImage();
		Image change = icon.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		if(jImg instanceof JButton) {
			((JButton) jImg).setIcon(new ImageIcon(change));
		} else {
			((JLabel) jImg).setIcon(new ImageIcon(change));
		}
	}
	
	// String 혹은 String[]에 Null값 혹은 미입력 값이 있는지 확인
	public <T> boolean isNullOrEmpty(T comp) {
		if(comp instanceof String) {
			String value = (String)comp;
			return (value == null || value.isBlank());
		} else {
			String[] value = (String[])comp;
			return Arrays.asList(value).stream().allMatch(x -> x == null || x.isBlank());
		}
	}
	
	
	
	
}
