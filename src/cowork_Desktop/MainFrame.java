package cowork_Desktop;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import cowork_Desktop.domain.sign.SignPanel;

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
					frame.setSize(1100, 700);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					frame.setTitle("Cowork");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					Image img = kit.getImage("src/cowork_Desktop/assets/img/COWORK_black.png");
					frame.setIconImage(img);
					frame.setContentPane(new SignPanel(frame));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
