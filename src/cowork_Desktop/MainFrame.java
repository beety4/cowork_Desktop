package cowork_Desktop;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JButton;

//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
//import com.jgoodies.forms.layout.FormSpecs;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setSize(1100, 700);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 965, 581); 
		
		ImageIcon icon = new ImageIcon("src/cowork_Desktop/assets/img/MainBackground.jpg");
		JPanel bgPane = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				//Dimension d = getSize();
				//g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		bgPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(bgPane);
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 965, 581); 
//		contentPane = new JPanel();
//		contentPane.setBackground(new Color(255, 255, 255));
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		
//		setContentPane(contentPane);
		
		//로고 이미지
		ImageIcon imgIcon = new ImageIcon("src/cowork_Desktop/assets/img/COWORK_black.png");
		
		Image img = imgIcon.getImage();
		Image updateImg = img.getScaledInstance(340, 208, Image.SCALE_SMOOTH);
		
		//새 Image 객체로 ImageIcon 객체 생성
		ImageIcon updateIcon = new ImageIcon(updateImg);
		bgPane.setLayout(null);
		JLabel lblImg = new JLabel(updateIcon);
		lblImg.setBounds(360, 100, 340, 208);
		lblImg.setHorizontalAlignment(JLabel.CENTER);
		lblImg.setIcon(updateIcon);
		
		bgPane.add(lblImg);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(312, 375, 97, 23);
		bgPane.add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(583, 375, 97, 23);
		bgPane.add(btnRegister);
	}
}
