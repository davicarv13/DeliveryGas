package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.ControllerScreen;
import model.dao.AdminDao;
import model.dao.DaoFactory;
import model.entities.Admin;

public class ViewLogin {

	public JFrame frame;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public void NewViewLogin() {
		 try {
	            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        	System.err.println(ex);
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewLogin window = new ViewLogin();
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
	public ViewLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		ControllerScreen screenController = new ControllerScreen();
		frame.setBounds(0, 0, screenController.getWidth(), screenController.getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Nome");
		lblLogin.setBounds(109, 127, 55, 16);
		frame.getContentPane().add(lblLogin);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(109, 155, 236, 20);
		frame.getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(109, 173, 55, 31);
		frame.getContentPane().add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(109, 203, 236, 20);
		frame.getContentPane().add(txtSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkLogin(txtLogin.getText(), new String(txtSenha.getPassword()))){
					ViewMainFrame viewMainFrame = new ViewMainFrame();
					viewMainFrame.NewViewMainFrame();
					frame.dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "Acesso Negado", "Login", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEntrar.setBounds(179, 235, 98, 26);
		frame.getContentPane().add(btnEntrar);
	}
	
	public boolean checkLogin(String login, String senha){
		AdminDao adminDao = DaoFactory.createAdminDao();
		Admin admin = adminDao.findByNomePassword(login, senha);
		
		if(admin != null){
			return true;
		}
		else{
			return false;
		}
	}
}
