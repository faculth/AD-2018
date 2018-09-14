package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class EnviosForm extends JPanel implements ActionListener{
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private JLabel lbldinamic;
	    private JComboBox<String> comboBox;
	    
	    /**
	     * Create the panel.
	     */
	    public EnviosForm() {
	    	String idVenta = "1";
	        setBackground(UIManager.getColor("Button.shadow"));
	        setLayout(null);
	        
	        JLabel lblIdReclamo = new JLabel("Id Venta");
	        lblIdReclamo.setBounds(185, 97, 70, 15);
	        add(lblIdReclamo);

	        JLabel lblEstado = new JLabel("Estado:");
	        lblEstado.setBounds(185, 157, 76, 15);
	        add(lblEstado);
	        
	        lbldinamic = new JLabel(idVenta);
	        lbldinamic.setBounds(287, 97, 70, 15);
	        add(lbldinamic);
	        
	        comboBox = new JComboBox<String>();
	        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Despachado", "Entregado"}));
	        comboBox.setBounds(287, 157, 117, 24);
	        add(comboBox);
	    }

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
}
