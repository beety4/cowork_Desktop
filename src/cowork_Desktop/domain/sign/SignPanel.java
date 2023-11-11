package cowork_Desktop.domain.sign;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cowork_Desktop.config.CustomUtility;
import cowork_Desktop.domain.space.SpacePanel;
import cowork_Desktop.dto.UserDTO;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class SignPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel logo = new JLabel();
	
	private JPanel pnlLogin = new JPanel();
	private JLabel lblLogin = new JLabel("Login");
	private JLabel lblID = new JLabel("ID :");
	private JLabel lblPW = new JLabel("PW :");
	private JTextField tbxID = new JTextField();
	private JPasswordField tbxPW = new JPasswordField();
	private JButton btnLogin = new JButton("Login");
	private JCheckBox chkMaintain = new JCheckBox("로그인 유지");
	private JButton btnGoRegister = new JButton("아이디가 없나요? 지금 회원가입하세요!");
	
	private JPanel pnlRegister = new JPanel();
	private JLabel lblRegister= new JLabel("Register");
	private JLabel lblRID = new JLabel("ID :");
	private JLabel lblRPW = new JLabel("PW :");
	private JLabel lblPwChk = new JLabel("PW Chk:");
	private JLabel lblName = new JLabel("Name :");
	private JLabel lblBirth = new JLabel("Birth :");
	private JLabel lblGender = new JLabel("Gender : ");
	private JLabel lblEmail = new JLabel("Email :");
	private JLabel lblEmailchk = new JLabel("EmailChk");
	private JTextField tbxRID = new JTextField();
	private JPasswordField tbxRPW = new JPasswordField();
	private JPasswordField tbxRPWChk = new JPasswordField();
	private JTextField tbxName = new JTextField();
	private JTextField tbxBirth = new JTextField();
	private JRadioButton rdbMale = new JRadioButton("M");
	private JRadioButton rdbFemale = new JRadioButton("F");
	private JTextField tbxEmail = new JTextField();
	private JTextField tbxEmailchk = new JTextField();
	private JButton btnRegister = new JButton("Register");
	private JButton btnGoLogin = new JButton("아이디가 있으신가요? 로그인하세요!");
	
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
	public SignPanel(JFrame win) {
		CustomUtility cu = new CustomUtility();
		CustomSession session = new CustomSession();
		CustomCookie customCookie = new CustomCookie();
		setBounds(0, 0, 1100, 700);
		setSize(1100, 700);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);		
		
	
		pnlLogin.setBounds(374, 345, 344, 279);
		pnlLogin.setVisible(true);
		pnlLogin.setLayout(null);
		pnlLogin.setBackground(Color.WHITE);
		add(pnlLogin);
		
		pnlRegister.setBounds(374, 250, 344, 404);
		pnlRegister.setVisible(false);
		pnlRegister.setLayout(null);
		pnlRegister.setBackground(Color.WHITE);
		add(pnlRegister);
		

		
		
		logo.setBounds(408, 74, 271, 166);
		cu.setImg(logo, "src/cowork_Desktop/assets/img/COWORK_white.png", 271, 166);
		add(logo);
		
		lblLogin.setBounds(131, 10, 87, 38);
		lblLogin.setFont(new Font("굴림", Font.BOLD, 32));
		pnlLogin.add(lblLogin);
		
		lblID.setBounds(23, 68, 56, 38);
		lblID.setFont(new Font("굴림", Font.BOLD, 26));
		pnlLogin.add(lblID);
		
		tbxID.setBounds(80, 71, 231, 29);
		tbxID.setFont(new Font("굴림", Font.PLAIN, 32));
		tbxID.setColumns(10);
		pnlLogin.add(tbxID);
		
		tbxPW.setBounds(80, 119, 231, 29);
		tbxPW.setFont(new Font("굴림", Font.PLAIN, 32));
		pnlLogin.add(tbxPW);
		
		btnLogin.setBounds(105, 158, 142, 55);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tbxID.getText();
				String pw = new String(tbxPW.getPassword());
				
				if(cu.isNullOrEmpty(new String[] {id, pw})) {
					JOptionPane.showMessageDialog(null, "값을 입력해 주세요!");
					return;
				}
				
				UserDAO userDAO = new UserDAO();
				int result = userDAO.login(id, pw);
				switch(result) {
					case 0:
						session.setAttributes("sName", userDAO.getNameByID(id));
						
						// 자동 로그인 체크 시
						if(chkMaintain.isSelected()) {
							customCookie.setCookie();
						}
						
						win.setContentPane(new SpacePanel(win));
						win.revalidate();
						break;
					case -1:
						JOptionPane.showMessageDialog(null, "ID가 올바르지 않습니다.");
						break;
					case 1:
						JOptionPane.showMessageDialog(null, "PW가 올바르지 않습니다.");
						break;
				}
			}
		});
		cu.setImg(btnLogin, "src/cowork_Desktop/assets/img/btnLogin.png", 193, 83);
		pnlLogin.add(btnLogin);
		
		lblPW.setBounds(12, 116, 67, 38);
		lblPW.setFont(new Font("굴림", Font.BOLD, 26));
		pnlLogin.add(lblPW);
		
		chkMaintain.setBounds(131, 219, 107, 23);
		pnlLogin.add(chkMaintain);
		
		btnGoRegister.setBounds(40, 254, 271, 15);
		btnGoRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlLogin.setVisible(false);
				pnlRegister.setVisible(true);
			}
		});
		btnGoRegister.setBorderPainted(false);
		btnGoRegister.setContentAreaFilled(false);
		pnlLogin.add(btnGoRegister);
		
		
		
		
		
		
		lblRegister.setBounds(104, 10, 150, 38);
		lblRegister.setFont(new Font("굴림", Font.BOLD, 32));
		pnlRegister.add(lblRegister);
		
		lblRID.setBounds(10, 51, 56, 24);
		lblRID.setFont(new Font("굴림", Font.BOLD, 20));
		pnlRegister.add(lblRID);
		
		tbxRID.setBounds(127, 51, 182, 24);
		tbxRID.setFont(new Font("굴림", Font.PLAIN, 25));
		tbxRID.setColumns(10);
		pnlRegister.add(tbxRID);
		
		lblRPW.setBounds(10, 82, 67, 24);
		lblRPW.setFont(new Font("굴림", Font.BOLD, 20));
		pnlRegister.add(lblRPW);
		
		tbxRPW.setBounds(127, 85, 182, 24);
		tbxRPW.setFont(new Font("굴림", Font.PLAIN, 25));
		pnlRegister.add(tbxRPW);
		
		lblPwChk.setFont(new Font("굴림", Font.BOLD, 20));
		lblPwChk.setBounds(10, 116, 96, 24);
		pnlRegister.add(lblPwChk);
		
		tbxRPWChk.setFont(new Font("굴림", Font.PLAIN, 25));
		tbxRPWChk.setBounds(127, 119, 182, 24);
		pnlRegister.add(tbxRPWChk);
		
		

		
		
		
		
		
		lblName.setFont(new Font("굴림", Font.BOLD, 20));
		lblName.setBounds(10, 150, 96, 24);
		pnlRegister.add(lblName);
		
		
		tbxName.setFont(new Font("굴림", Font.PLAIN, 25));
		tbxName.setColumns(10);
		tbxName.setBounds(127, 153, 182, 24);
		pnlRegister.add(tbxName);
		
		lblBirth.setFont(new Font("굴림", Font.BOLD, 20));
		lblBirth.setBounds(10, 187, 96, 24);
		pnlRegister.add(lblBirth);
		
		
		tbxBirth.setFont(new Font("굴림", Font.PLAIN, 25));
		tbxBirth.setColumns(10);
		tbxBirth.setBounds(127, 187, 182, 24);
		pnlRegister.add(tbxBirth);
		
		
		lblGender.setFont(new Font("굴림", Font.BOLD, 20));
		lblGender.setBounds(10, 221, 96, 24);
		pnlRegister.add(lblGender);
		
		lblEmail.setFont(new Font("굴림", Font.BOLD, 20));
		lblEmail.setBounds(10, 258, 96, 24);
		pnlRegister.add(lblEmail);
		
		tbxEmail.setFont(new Font("굴림", Font.PLAIN, 25));
		tbxEmail.setColumns(10);
		tbxEmail.setBounds(127, 258, 182, 24);
		pnlRegister.add(tbxEmail);
		
		
		rdbMale.setBounds(127, 223, 56, 24);
		pnlRegister.add(rdbMale);
		
		rdbFemale.setBounds(255, 224, 54, 23);
		pnlRegister.add(rdbFemale);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbMale);
		bg.add(rdbFemale);
		
		
		lblEmailchk.setFont(new Font("굴림", Font.BOLD, 20));
		lblEmailchk.setBounds(10, 292, 96, 24);
		pnlRegister.add(lblEmailchk);
		
		tbxEmailchk.setFont(new Font("굴림", Font.PLAIN, 25));
		tbxEmailchk.setColumns(10);
		tbxEmailchk.setBounds(127, 292, 182, 24);
		pnlRegister.add(tbxEmailchk);
		
		btnRegister.setBounds(112, 325, 142, 55);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * 
				 * Register 구현 부분
				 * 
				 * 
				 * 
				 */
				String id = tbxID.getText();
				String pw = new String(tbxPW.getPassword());
				
				UserDTO userDTO = new UserDTO();
				if(cu.isNullOrEmpty(new String[] {id, pw})) {
					JOptionPane.showMessageDialog(null, "값을 입력해 주세요!");
					return;
				}
				
				UserDAO userDAO = new UserDAO();
				int result = userDAO.register(userDTO);
				switch(result) {
					case 0:
						JOptionPane.showMessageDialog(null, "성공!");
						break;
					case -1:
						JOptionPane.showMessageDialog(null, "ID가 올바르지 않습니다.");
						break;
					case 1:
						JOptionPane.showMessageDialog(null, "PW가 올바르지 않습니다.");
						break;
				}
			}
		});
		cu.setImg(btnRegister, "src/cowork_Desktop/assets/img/btnRegister.png", 193, 83);
		pnlRegister.add(btnRegister);
		
		btnGoLogin.setBounds(65, 389, 244, 15);
		btnGoLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlLogin.setVisible(true);
				pnlRegister.setVisible(false);
			}
		});
		btnGoLogin.setBorderPainted(false);
		btnGoLogin.setContentAreaFilled(false);
		pnlRegister.add(btnGoLogin);
		
	}
}
