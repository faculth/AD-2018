package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import modelo.Envio;
import modelo.Venta;
import persistencia.EnvioMapper;

public class EnviosForm extends JPanel implements ActionListener{
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private JLabel lbldinamic;
	    private JComboBox<String> comboBox;
	    private JLabel lblCliente;
	    private JLabel lblFecha;
	    private JLabel lblTotal;
	    private Venta venta;
	    
	    /**
	     * Create the panel.
	     */
	    public EnviosForm(Venta v) {
	    	venta = v;
	    	String idVenta = String.valueOf(v.getNumeroVenta());
	        setBackground(UIManager.getColor("Button.shadow"));
	        setLayout(null);
	        
	        JLabel lblIdVenta = new JLabel("Id Venta:");
	        lblIdVenta.setBounds(64, 54, 70, 15);
	        add(lblIdVenta);

	        JLabel lblEstado = new JLabel("Estado:");
	        lblEstado.setBounds(64, 192, 76, 15);
	        add(lblEstado);
	        
	        lbldinamic = new JLabel(idVenta);
	        lbldinamic.setBounds(144, 54, 70, 15);
	        add(lbldinamic);
	        
	        comboBox = new JComboBox<String>();
	        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Despachado", "Entregado"}));
	        comboBox.setBounds(124, 187, 117, 24);
	        add(comboBox);
	        
	        lblCliente = new JLabel("Cliente:");
	        lblCliente.setBounds(64, 87, 46, 14);
	        add(lblCliente);
	        
	        lblFecha = new JLabel("Fecha: ");
	        lblFecha.setBounds(64, 119, 46, 14);
	        add(lblFecha);
	        
	        lblTotal = new JLabel("Total:");
	        lblTotal.setBounds(64, 156, 46, 14);
	        add(lblTotal);
	        
	        JLabel lblClienteStamp = new JLabel("");
	        lblClienteStamp.setBounds(144, 87, 97, 14);
	        add(lblClienteStamp);
	        lblClienteStamp.setText(v.getCliente().getNombre() + " " + v.getCliente().getApellido());
	        
	        JLabel lblFechaStamp = new JLabel("");
	        lblFechaStamp.setBounds(144, 119, 97, 14);
	        add(lblFechaStamp);
	        lblFechaStamp.setText(v.getFechaVenta());
	        
	        JLabel lblTotalStamp = new JLabel("");
	        lblTotalStamp.setBounds(144, 156, 70, 14);
	        add(lblTotalStamp);
	        lblTotalStamp.setText(String.valueOf(v.getTotal()));
	    }

		@Override
		public void actionPerformed(ActionEvent e) {
	        switch (e.getActionCommand()) {
            case "Guardar":
            	if(comboBox.getSelectedItem().toString().equals("Despachado")) {
        			EnvioMapper.getInstancia().registrarEnvio(venta);
        			JOptionPane.showMessageDialog(null, "Envio registrado correctamente");
            	}else{
            		Envio envio = new Envio();
            		envio.setNumEnvio(venta.getEnvio().getNumEnvio());
            		envio.setEstado(comboBox.getSelectedItem().toString());
	            	EnvioMapper.getInstancia().update(envio);
	            	JOptionPane.showMessageDialog(null, "Envio modificado correctamente");
            	}
            	JDialog parentDialog=(JDialog)SwingUtilities.getWindowAncestor(this);
				parentDialog.dispose();
            	break;
	        }
		}
		
		public void setComboEnabled(boolean value) {
			this.comboBox.setEnabled(value);
		}
}
