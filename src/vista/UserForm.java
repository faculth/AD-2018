package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controlador.LoginController;


public class UserForm extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField name;
	private JTextField userDni;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private LoginController controller;
	private int usrId;

	/**
	 * Create the panel.
	 */
	public UserForm() {
		setBackground(UIManager.getColor("Button.shadow"));
		usrId = -1;
		setLayout(null);
		
		name = new JTextField();
		name.setBounds(376, 85, 182, 19);
		add(name);
		name.setColumns(10);


		userDni = new JTextField();
		userDni.setColumns(10);
		userDni.setBounds(376, 136, 182, 19);
		add(userDni);
		
		JLabel Name = new JLabel("Nombre:");
		Name.setBounds(189, 87, 70, 15);
		add(Name);

		JLabel dnilbl = new JLabel("DNI:");
		dnilbl.setBounds(189, 138, 70, 15);
		add(dnilbl);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setBounds(189, 251, 94, 15);
		add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(376, 249, 182, 19);
		add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(376, 307, 182, 19);
		add(passwordField_1);
		
		JLabel lblConfirmarContrasena = new JLabel("Confirmar contrase\u00F1a:");
		lblConfirmarContrasena.setBounds(189, 309, 169, 15);
		add(lblConfirmarContrasena);
		
		JLabel lblRol = new JLabel("Rol :");
		lblRol.setBounds(189, 193, 70, 15);
		add(lblRol);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Encargado", "Vendedor"}));
		comboBox.setBounds(376, 188, 182, 24);
		add(comboBox);
	}

	public void addNameInfo(String name) {
		this.name.setText(name);
	}

	public void addEmailInfo(String emailInfo) {
		this.userDni.setText(emailInfo);
	}

	public void addPasswordInfo(String passwordInfo) {
		this.passwordField.setText(passwordInfo);
		this.passwordField_1.setText(passwordInfo);
	}

	public void setUserController(LoginController userController) {
		this.controller = userController;
	}

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "Save" :
				boolean saved  = this.doSave();
				if (saved) {
					this.controller.renderMain();
				}
				break;
			case "Cancel":
				this.controller.renderMain();
				break;
			case "Delete account" :
				this.controller.deleteAccount();
				break;
		}
	}

	private boolean doSave() {
		String name = this.name.getText();
		String userEmail = this.userDni.getText();
		String password = this.passwordField.getText();
		String repassword = this.passwordField_1.getText();
		if (!password.equals(repassword)) {
			showDifferentPasswordLabel();
			return  false;
		}
		if (!userEmail.contains("@") || !userEmail.contains(".com")) {
			this.showWrongEmailLabel();
			return false;
		}
		if (usrId == -1) {
//			controller.saveUser(name, userEmail ,password, usrId);
		} else {
//			controller.saveUser(name, userEmail,  password, usrId);
		}
		return true;
 	}

	private void showDifferentPasswordLabel() {

	}

	private void showWrongEmailLabel() {

	}
}
