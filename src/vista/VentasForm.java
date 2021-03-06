package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import controlador.LoginController;
import modelo.Cliente;
import modelo.ItemVenta;
import modelo.Producto;
import modelo.Usuario;
import modelo.Venta;
import servicios.ProductoServicio;
import servicios.VentaServicio;

public class VentasForm extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 183896326489861259L;
	
	private JTextField txtDescuento;
	private Cliente cliente;
    private final JTable table1;
    private final DefaultTableModel tableModel;
    private JLabel totalLabel;
    private JLabel cantItems;
    private JLabel lblFechaStamp;
    private List<ItemVenta> items;
    private JTextField txtPrecioConDescuento;
    Venta v;
    Usuario usr;
    /**
	 * Create the panel.
	 */
	public VentasForm(Cliente c) {
		this.cliente = c;
		setBackground(UIManager.getColor("Button.shadow"));
		setLayout(null);
		
		items = new ArrayList<ItemVenta>();
		
		JLabel lblFecha = new JLabel("Fecha: ");
		lblFecha.setBounds(88, 23, 70, 15);
		add(lblFecha);
		
		
		lblFechaStamp = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
		lblFechaStamp.setBounds(348, 29, 112, 15);
		add(lblFechaStamp);
		
		JLabel lblNombreCompradod = new JLabel("Nombre comprador:");
		lblNombreCompradod.setBounds(88, 61, 156, 15);
		add(lblNombreCompradod);
		
		JLabel lblNombre = new JLabel(c.getNombre() +" "+ c.getApellido());
		lblNombre.setBounds(348, 60, 112, 15);
		add(lblNombre);
		
		JLabel lblDnicuitComprador = new JLabel("DNI/CUIT comprador:");
		lblDnicuitComprador.setBounds(88, 99, 156, 15);
		add(lblDnicuitComprador);
		
		JLabel lblDNI = new JLabel(Integer.toString(c.getDni()));
		lblDNI.setBounds(348, 101, 112, 15);
		add(lblDNI);

		
        String[] columnNames = {"Nombre","Codigo", "Precio", "Cantidad"};
        Object[][] tableData = {};
        
        tableModel = new DefaultTableModel(tableData, columnNames) {
 			private static final long serialVersionUID = 8095720010196161749L;

			@Override
            public boolean isCellEditable(int row, int column) {
               //Ninguna celda editable
               return false;
            }
        };
        
        table1 = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table1);
		scrollPane.setBounds(88, 174, 402, 140);
		add(scrollPane);
		
		JLabel lblItems = new JLabel("Items: ");
		lblItems.setBounds(88, 137, 70, 15);
		add(lblItems);
		
		JButton btnAgregarItem = new JButton("Agregar Item");
		btnAgregarItem.setBounds(88, 339, 126, 25);
		add(btnAgregarItem);
		btnAgregarItem.addActionListener(this);
		
		JButton btnEliminarItem = new JButton("Eliminar Item");
		btnEliminarItem.setBounds(224, 339, 126, 25);
		add(btnEliminarItem);
		btnEliminarItem.addActionListener(this);

		
		JLabel lblSubtotal = new JLabel("Subtotal: $");
		lblSubtotal.setBounds(88, 386, 97, 15);
		add(lblSubtotal);
		
		JLabel lblDescuento = new JLabel("Descuento:   %");
		lblDescuento.setBounds(276, 386, 97, 15);
		add(lblDescuento);
		
		txtDescuento = new JTextField();
		txtDescuento.setText("0");
		txtDescuento.setBounds(359, 385, 70, 15);
		txtDescuento.setColumns(3);
		add(txtDescuento);

		
		totalLabel = new JLabel("0");
		totalLabel.setBounds(183, 386, 70, 15);
		add(totalLabel);
		
		cantItems = new JLabel("");
		cantItems.setBounds(347, 137, 46, 14);
		add(cantItems);
		
		JButton btnAplicDesc = new JButton("Aplicar descuento");
		btnAplicDesc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!txtDescuento.getText().isEmpty()) {
					float total = 0;
					float descuento = 0;
					float totalDesc = 0;
					for(int i = 0;i<items.size();i++){
						total = total + items.get(i).calcularSubtotal();
					}
					descuento = total*Integer.parseInt(txtDescuento.getText())/100;
					totalDesc = total - descuento;
					txtPrecioConDescuento.setText(String.valueOf(totalDesc));
				}else {
					txtPrecioConDescuento.setText("");
				}
			}
		});
		btnAplicDesc.setBounds(360, 339, 156, 25);
		add(btnAplicDesc);
		
		txtPrecioConDescuento = new JTextField();
		txtPrecioConDescuento.setText("0");
		txtPrecioConDescuento.setColumns(3);
		txtPrecioConDescuento.setEditable(false);
		txtPrecioConDescuento.setBounds(359, 412, 70, 15);
		add(txtPrecioConDescuento);
		
		JLabel lblPrecioConDescuento = new JLabel("Precio total con descuento:");
		lblPrecioConDescuento.setBounds(183, 413, 167, 15);
		add(lblPrecioConDescuento);
	}
	
	private void setVariables() {
        cantItems.setText(String.valueOf(table1.getRowCount()));
        int value = 0;
        int precioUnit = 0;
        int cant = 0;
        for (int i = 0 ; i < table1.getRowCount() ; i++) {
        	precioUnit = Integer.valueOf(table1.getValueAt(i, 2).toString());
        	cant = Integer.valueOf(table1.getValueAt(i, 3).toString());
            value+= precioUnit * cant;
        }
        totalLabel.setText(String.valueOf(value));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Agregar Item":
	        String input = JOptionPane.showInputDialog("Ingrese codigo de producto");
	        if(input != null && !input.isEmpty()){
	        	Producto p = ProductoServicio.getInstancia().buscarProducto(Integer.parseInt(input));
	            if(p != null) {
	            	if(p.getStock() > 0) {
	            		input = JOptionPane.showInputDialog("Ingrese cantidad");
	            		if(p.getStock() >= Integer.parseInt(input)) {
	            			tableModel.addRow(new Object[]{p.getNombre(), p.getCodigoProducto(), p.getPrecio(), Integer.parseInt(input)});
	            			ItemVenta item = new ItemVenta(p, Integer.parseInt(input), p.getPrecio());
	            			items.add(item);
	            		}else{
	            			JOptionPane.showMessageDialog(null, "No hay stock suficiente para "+ p.getNombre() + ". Stock:"+p.getStock());
	            		}
	            	}else {
	            		JOptionPane.showMessageDialog(null, "No hay stock para "+ p.getNombre());
	            	}
	            }else {
	            	JOptionPane.showMessageDialog(null, "Busqueda sin resultados");
	            }
	            setVariables();
	        }
	        break;
		case "Eliminar Item":
			int row = table1.getSelectedRow();
			if(row != -1) {
				Producto p = new Producto();
				p.setCodigoProducto(Integer.parseInt(table1.getValueAt(row, 1).toString()));
				ItemVenta item = new ItemVenta(p, 0, 0);
				items.remove(item);
				tableModel.removeRow(table1.getSelectedRow());
				setVariables();
			}
			break;
		case "Guardar":
			if(items != null && !items.isEmpty()) {
				usr = LoginController.getUsuarioLogueado();
				v = new Venta(lblFechaStamp.getText(), usr, cliente, items, Integer.parseInt(txtDescuento.getText()));
				int res = VentaServicio.getInstancia().generarVenta(v);
				if(res > 0){
					JOptionPane.showMessageDialog(null, "Venta guardada correctamente", "", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Error al guardar venta", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				JDialog parentDialog=(JDialog)SwingUtilities.getWindowAncestor(this);
				parentDialog.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "La venta no tiene items para guardar");
			}
			break;
		}
    }
}
