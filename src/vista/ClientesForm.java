package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ClientesForm extends JPanel implements ActionListener{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtDniCuit;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtDireccion;
    private JTextField txtMail;
    private JTextField txtTelefono;

    /**
     * Create the panel.
     */
    public ClientesForm() {
        setBackground(UIManager.getColor("Button.shadow"));
        setLayout(null);

        JLabel lblDniCuit = new JLabel("DNI/ CUIT:");
        lblDniCuit.setBounds(60, 50, 70, 15);
		add(lblDniCuit);
		
        JLabel lblName = new JLabel("Nombre:");
        lblName.setBounds(60, 90, 70, 15);
		add(lblName);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(60, 130, 70, 15);
		add(lblApellido);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setBounds(60, 170, 94, 15);
		add(lblDireccion);
		
		JLabel lblMail = new JLabel("Mail:");
		lblMail.setBounds(60, 210, 94, 15);
		add(lblMail);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(60, 250, 94, 15);
		add(lblTelefono);
		
        txtDniCuit = new JTextField();
        txtDniCuit.setBounds(129, 47, 182, 19);
        add(txtDniCuit);
        txtDniCuit.setColumns(10);
        
        txtNombre = new JTextField();
        txtNombre.setColumns(10);
        txtNombre.setBounds(129, 87, 182, 19);
        add(txtNombre);
        
        txtApellido = new JTextField();
        txtApellido.setColumns(10);
        txtApellido.setBounds(129, 127, 182, 19);
        add(txtApellido);
        
        txtDireccion = new JTextField();
        txtDireccion.setColumns(10);
        txtDireccion.setBounds(129, 167, 182, 19);
        add(txtDireccion);
        
        txtMail = new JTextField();
        txtMail.setColumns(10);
        txtMail.setBounds(129, 207, 182, 19);
        add(txtMail);
        
        txtTelefono = new JTextField();
        txtTelefono.setColumns(10);
        txtTelefono.setBounds(129, 247, 182, 19);
        add(txtTelefono);
        
        JCheckBox checkParticular = new JCheckBox("Particular");
        checkParticular.setBounds(129, 286, 97, 23);
        add(checkParticular);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
