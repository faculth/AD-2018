package vista;

import controlador.LoginController;
import servicios.UsuarioServicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;
	private TextField userField;
    private final JButton loginButton;
    private LoginController loginController;

    /**
	 * Create the panel.
	 */

	public Login() {
    	setBackground(UIManager.getColor("Button.shadow"));
		setLayout(null);
		
		userField = new TextField();
        userField.setBounds(130, 50, 263, 21);
		add(userField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(130, 85, 263, 21);
		add(passwordField);
		
		Label userNameLabel = new Label("DNI");
		userNameLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		userNameLabel.setForeground(UIManager.getColor("Button.foreground"));
		userNameLabel.setBounds(80, 50, 68, 21);
		add(userNameLabel);
		
		Label PasswordLabel = new Label("Contrasena");
		PasswordLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		PasswordLabel.setForeground(UIManager.getColor("Button.foreground"));
		PasswordLabel.setBounds(55, 79, 116, 33);
		add(PasswordLabel);

        loginButton = new JButton("Aceptar");
		loginButton.setBounds(130, 130, 116, 21);
		loginButton.addActionListener(this);
		add(loginButton);

		setForeground(Color.GRAY);
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.addActionListener(this);
		cancelar.setBounds(255, 130, 116, 21);
		add(cancelar);
	}

	public void setLoginController(LoginController controller) {
		loginController = controller;
	}

	public String[] getLoginContent() {
		char[] arrayC = passwordField.getPassword(); 
		String pass = new String(arrayC); 
	    String[] loginContent = {userField.getText(),pass};
        return loginContent;
    }


	public void showEmptyFieldLabel() {
        Label emptyField = new Label("Usuario o password vacios");
        emptyField.setBounds(130, 162, 250, 15);
        add(emptyField);
    }

    public void showWrongUserOrPassword() {
        Label emptyField = new Label("El usuario no existe o la password es incorrecta");
        emptyField.setBounds(130, 162, 250, 15);
        add(emptyField);
    }

	public void showLoginError() {
		Label emptyField = new Label("Couldn't login: check email or password");
		emptyField.setBounds(130, 162, 250, 15);
		add(emptyField);
	}

	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "Aceptar":
				String[] loginInfo = this.getLoginContent();
				if (loginInfo[0].isEmpty() || loginInfo[1].isEmpty()) {
					this.showEmptyFieldLabel();
				} 
				else if(validarUser(loginInfo) == 0 || validarUser(loginInfo) == -1){
					this.showWrongUserOrPassword();
				}
				else  {
					this.loginController.doLogin(loginInfo[0]);
				}
				break;
			case "Cancelar":
				this.loginController.doExit();
				break;
			case "Create new account":
				this.loginController.renderUserForm();
			default:
				System.out.println(e.getActionCommand());
				break;
		}
	}

	private int validarUser(String[] loginInfo) {
		return UsuarioServicio.getInstancia().validarUser(loginInfo);
	}
}
