package cowork_Desktop.domain.sign;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tbxID;
	private JTextField tbxPW;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignFrame frame = new SignFrame();
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
	public SignFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1003, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(447, 53, 57, 15);
		contentPane.add(lblLogin);
		
		tbxID = new JTextField();
		tbxID.setBounds(421, 130, 116, 21);
		contentPane.add(tbxID);
		tbxID.setColumns(10);
		
		tbxPW = new JTextField();
		tbxPW.setBounds(421, 190, 116, 21);
		contentPane.add(tbxPW);
		tbxPW.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tbxID.getText();
				String pw = tbxPW.getText();
				
				UserDAO userDAO = new UserDAO();
				int result = userDAO.login(id, pw);
				switch(result) {
					case -1:
						JOptionPane.showMessageDialog(null, "DB오류 입니다.");
						break;
					case 1:
						JOptionPane.showMessageDialog(null, "ID/PW가 올바르지 않습니다.");
						break;
				}
			}
		});
		btnLogin.setBounds(421, 265, 97, 23);
		contentPane.add(btnLogin);
		
		JLabel lblID = new JLabel("ID");
		lblID.setBounds(321, 133, 57, 15);
		contentPane.add(lblID);
		
		JLabel lblPW = new JLabel("PW");
		lblPW.setBounds(321, 193, 57, 15);
		contentPane.add(lblPW);
	}
}
