package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentasForm extends JPanel implements ActionListener {
	private JTable table;
	private JTextField textField;
	private String nombreComprador;
	private String dniComprador;
    private final JTable table1;
    private final DefaultTableModel tableModel;
    private JLabel totalLabel;

    /**
	 * Create the panel.
	 */
	public VentasForm(String dniComprador, String nombreComprador) {
		this.dniComprador = dniComprador;
		this.nombreComprador = nombreComprador;
		setBackground(UIManager.getColor("Button.shadow"));
		setLayout(null);
		
		JLabel lblFecha = new JLabel("Fecha: ");
		lblFecha.setBounds(88, 23, 70, 15);
		add(lblFecha);
		
		JLabel lblNewLabel = new JLabel("2010/01/01");
		lblNewLabel.setBounds(348, 29, 112, 15);
		add(lblNewLabel);
		
		JLabel lblNombreCompradod = new JLabel("Nombre comprador:");
		lblNombreCompradod.setBounds(88, 61, 156, 15);
		add(lblNombreCompradod);
		
		JLabel lblPepeZaranga = new JLabel(nombreComprador);
		lblPepeZaranga.setBounds(348, 60, 112, 15);
		add(lblPepeZaranga);
		
		JLabel lblDnicuitComprador = new JLabel("DNI/CUIT comprador:");
		lblDnicuitComprador.setBounds(88, 99, 156, 15);
		add(lblDnicuitComprador);
		
		JLabel label = new JLabel(dniComprador);
		label.setBounds(348, 101, 112, 15);
		add(label);

		
        String[] columnNames = {"Nombre","Codigo", "precio"};
        Object[][] tableData = {};
        tableModel = new DefaultTableModel(tableData, columnNames);
        table1 = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table1);
		scrollPane.setBounds(149, 175, 402, 140);
		add(scrollPane);
		
		JLabel lblItems = new JLabel("Items: ");
		lblItems.setBounds(88, 137, 70, 15);
		add(lblItems);
		
		JButton btnNewButton = new JButton("Agregar Item");
		btnNewButton.setBounds(425, 339, 126, 25);
		add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		JLabel lblSubtotal = new JLabel("Subtotal: $");
		lblSubtotal.setBounds(88, 386, 97, 15);
		add(lblSubtotal);
		
		JLabel lblDescuento = new JLabel("Descuento:");
		lblDescuento.setBounds(88, 424, 97, 15);
		add(lblDescuento);
		
		textField = new JTextField();
		textField.setText("0");
		textField.setBounds(183, 422, 36, 19);
		add(textField);
		textField.setColumns(10);
		totalLabel = new JLabel("0");
		totalLabel.setBounds(183, 386, 70, 15);
		add(totalLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
        String input = JOptionPane.showInputDialog("Ingrese codigo de producto");
        Object[] dataToAdd =  new Object[]{"Algun repuesto",input, "300"};
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        tableModel.addRow(dataToAdd);
        int value = 0;
        for (int i = 0 ; i < table1.getRowCount() ; i++) {
             value+= Integer.valueOf(table1.getValueAt(i, 2).toString());
        }
        totalLabel.setText(String.valueOf(value));
    }
}
