package vista;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.*;

public class FormReporteVentas extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblHasta;

	/**
	 * Create the panel.
	 */
	public FormReporteVentas() {
		setPreferredSize( new Dimension( 300, 480 ) );
		setBackground(UIManager.getColor("Button.shadow"));
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(218, 29, 146, 19);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(218, 74, 146, 19);
		add(textField_1);
		
		JLabel lblDesde = new JLabel("Desde: ");
		lblDesde.setBounds(54, 31, 70, 15);
		add(lblDesde);
		
		lblHasta = new JLabel("Hasta:");
		lblHasta.setBounds(54, 76, 70, 15);
		add(lblHasta);

	}
}
