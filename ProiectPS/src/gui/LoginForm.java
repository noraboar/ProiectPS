package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;




import bl.UserManager;
import models.User;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginForm {

	public JFrame frame;
	private JLabel background;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JButton btnForgotPassword;
	private JButton btnNewButton;
	
	private UserManager userManager = new UserManager();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginForm() {
		initialize();
		initActions();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 652, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(239, 165, 190, 25);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(239, 201, 190, 25);
		frame.getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		btnLogin = new JButton("Log in");
		btnLogin.setBounds(319, 254, 110, 33);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(152, 162, 96, 25);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(152, 201, 96, 25);
		frame.getContentPane().add(lblPassword);
		
		btnForgotPassword = new JButton("Forgot password");
		btnForgotPassword.setBounds(149, 254, 150, 33);
		frame.getContentPane().add(btnForgotPassword);
		
		btnNewButton = new JButton("Create new user account");
		btnNewButton.setBounds(152, 309, 277, 38);
		frame.getContentPane().add(btnNewButton);
		
		background = new JLabel(new ImageIcon("cioco.png"));
		background.setBounds(0, 0, 652, 520);
		background.setVisible(true);
		frame.getContentPane().add(background);
		
	}
	
	public void initActions(){
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username=usernameField.getText();
				String password=passwordField.getText();
				User user = userManager.login(username, password);
				if(user==null){
					JOptionPane.showMessageDialog(null,"Username sau parola gresita!!!");
				}
				else{
					frame.setVisible(false);
					if(user.getTip().equals("ADMIN")){
						AdminForm adminFrame=new AdminForm();
						adminFrame.frame.setVisible(true);
					}
					else{
						UserForm userForm=new UserForm();
						userForm.frame.setVisible(true);
					}
				}
			}
		});
		
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!userManager.verificareUsername(usernameField.getText())){
					JOptionPane.showMessageDialog(null,"Username inexistent!!!");
				}
				else{
					JTextField username = new JTextField();
					username.setEditable(false);
					username.setText(usernameField.getText());
					JPasswordField password = new JPasswordField();
					
					Object[] message = {
						    "Username:", username,
						    "Password:", password,
						  };
						int option = JOptionPane.showConfirmDialog(null, message, "Date", JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION) {
						userManager.passwordChange(username.getText(), String.valueOf(password.getPassword()));
						}
				}
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField nume = new JTextField();
				JTextField username = new JTextField();
				JPasswordField password = new JPasswordField();
				
				Object[] message = {
					    "Nume:", nume,
					    "Username:", username,
					    "Password:", password,
					  };
					int option = JOptionPane.showConfirmDialog(null, message, "Date", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
					User user=new User();
					user.setNume(nume.getText());
					user.setUsername(username.getText());
					user.setPassword(String.valueOf(password.getPassword()));
					userManager.createUser(user);
					}
			}
		});
	}
}
