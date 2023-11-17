package cowork_Desktop.domain.space;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cowork_Desktop.config.CustomUtility;
import cowork_Desktop.domain.sign.CustomSession;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class SpacePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	@Override
	public void paintComponent(Graphics g) {
		Image bg = new ImageIcon("src/cowork_Desktop/assets/img/MainImgTest.jpg").getImage();
		g.drawImage(bg, 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
	
	
	/**
	 * Create the panel.
	 */
	public SpacePanel(JFrame win) {
		CustomUtility cu = new CustomUtility();
		CustomSession session = new CustomSession();
		setBounds(0, 0, 1100, 700);
		setSize(1100, 700);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("WELCOME TO COWORK MAIN PAGE");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 41));
		lblNewLabel.setBounds(184, 103, 752, 155);
		add(lblNewLabel);
		
		
		/*
		 * 
		 * == ToDo ==
		 * 1. 현재 로그인한 세션의 Space 가져오기
		 * 2. Space 만들기
		 * 3. DAO -> createSpace, addMember 등등 만들기
		 * 4. Room 가져오기
		 * 
		 * 
		 */
		
		
		
		


	}
}
