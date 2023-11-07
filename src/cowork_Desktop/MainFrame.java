package cowork_Desktop;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cowork_Desktop.domain.sign.SignPanel;
import cowork_Desktop.domain.space.SpacePanel;
import cowork_Desktop.domain.sign.CustomCookie;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					Toolkit kit = Toolkit.getDefaultToolkit();
					Image img = kit.getImage("src/cowork_Desktop/assets/img/COWORK_black.png");
					frame.setIconImage(img);
					
					frame.setSize(1100, 700);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					frame.setTitle("COWORK");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
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
		CustomCookie customCookie = new CustomCookie();
		switch(customCookie.getCookie()) {
		case 0:	// 로그인 성공
			setContentPane(new SpacePanel(this));
			revalidate();
			break;
		case 1:	// 무결성 검사 실패! 위변조 감지
			setContentPane(new SignPanel(this));
			revalidate();
			JOptionPane.showMessageDialog(null, "자동 로그인 중 오류가 발생했습니다. 다시 로그인해주세요!");
			break;
		case 2:	// 쿠키 만료. 로그인 필요
			setContentPane(new SignPanel(this));
			revalidate();
			break;
		default:	// 기본값 -> 로그인 화면
			setContentPane(new SignPanel(this));
			revalidate();
		}
	}
	
	
}
