package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import modelo.Reclamo;
import modelo.Venta;

public class ReclamosForm extends JPanel implements ActionListener{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBox;
	private Venta venta;
	/**
     * Create the panel.
     */
    public ReclamosForm(Venta v) {
    	String idVenta = String.valueOf(v.getNumeroVenta());
        setBackground(UIManager.getColor("Button.shadow"));
        setLayout(null);
        
        JLabel lblIdReclamo = new JLabel("Id venta:");
        lblIdReclamo.setBounds(38, 46, 104, 15);
        add(lblIdReclamo);

        JLabel lblDescripcion = new JLabel("Descripci\u00F3n:");
        lblDescripcion.setBounds(38, 93, 94, 15);
        add(lblDescripcion);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(38, 197, 76, 15);
        add(lblEstado);
        
        JTextArea txtDescripcion = new JTextArea();
        txtDescripcion.setBounds(151, 88, 176, 72);
        add(txtDescripcion);
        
        JLabel lblNewLabel = new JLabel(idVenta);
        lblNewLabel.setBounds(152, 46, 70, 15);
        add(lblNewLabel);

        comboBox = new JComboBox<String>();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"En proceso", "Resuelto"}));
        comboBox.setBounds(149, 192, 178, 24);
        add(comboBox);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
        case "Guardar":
        	if(comboBox.getSelectedItem().toString().equals("En proceso")) {
            	JOptionPane.showMessageDialog(null, "Reclamo registrado correctamente");
        	}else{
        		Reclamo reclamo = new Reclamo();
        		reclamo.setEstado("Resuelto");
        		reclamo.setDescripcion("Reclamo resuelto");
            	//EnvioMapper.getInstancia().update(envio);
            	JOptionPane.showMessageDialog(null, "Envio modificado correctamente");
        	}
        	JDialog parentDialog=(JDialog)SwingUtilities.getWindowAncestor(this);
			parentDialog.dispose();
        	break;
        }
	}
}
