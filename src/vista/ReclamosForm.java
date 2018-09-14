package vista;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ReclamosForm extends JPanel{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Create the panel.
     */
    public ReclamosForm() {
        setBackground(UIManager.getColor("Button.shadow"));
        setLayout(null);
        
        JLabel lblIdReclamo = new JLabel("Id venta:");
        lblIdReclamo.setBounds(189, 46, 104, 15);
        add(lblIdReclamo);

        JLabel lblDescripcion = new JLabel("Descripci\u00F3n:");
        lblDescripcion.setBounds(189, 111, 94, 15);
        add(lblDescripcion);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(189, 197, 76, 15);
        add(lblEstado);
        
        JTextArea txtDescripcion = new JTextArea();
        txtDescripcion.setBounds(382, 111, 176, 72);
        add(txtDescripcion);
        
        JLabel lblNewLabel = new JLabel("1");
        lblNewLabel.setBounds(382, 46, 70, 15);
        add(lblNewLabel);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"En proceso", "Resuelto"}));
        comboBox.setBounds(380, 197, 178, 24);
        add(comboBox);
    }
}
