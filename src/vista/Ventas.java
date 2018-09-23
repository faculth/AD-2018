package vista;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.Cliente;
import modelo.Venta;
import servicios.ClienteServicio;
import servicios.VentaServicio;

public class Ventas extends ItemPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Override
    protected String[] getColumnsForList() {
        return new String[]{"id", "fecha", "dni/cuit cliente", "Total", "Envio"};
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Nueva":
                onNuevaVenta();
            break;
            case "Generar Reporte":
                onVentaReporte();
            break;
            case "Buscar":
            	if(search.getText().isEmpty()) {
            		break;
            	}
                Venta venta = VentaServicio.getInstancia().buscarVenta(Integer.parseInt(search.getText()));
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();//.setValueAt("PRUEBA", 0, 2);
                tableModel.setRowCount(0);
                if(venta != null) {
                	String estadoEnvio = venta.getEnvio() == null ? "" : venta.getEnvio().getEstado();  
	                tableModel.addRow(new Object[]{venta.getNumeroVenta(), venta.getFechaVenta(), venta.getCliente().getDni(), venta.getTotal(), estadoEnvio});
                }else {
                	JOptionPane.showMessageDialog(null, "Busqueda sin resultados");
                }
            break;
        }
    }

    protected void configureActions() {
        actionButton1.setText("Nueva");
        actionButton2.setText("Generar Reporte");
        actionButton3.setVisible(false);
        actionButton4.setVisible(false);
        lblSearch.setText("Buscar por Id: ");
        actionButton1.addActionListener(this);
        actionButton2.addActionListener(this);
        actionButton5.addActionListener(this);
    }

    @Override
    protected String getSectionText() {
        return "Ventas";
    }

    private void onNuevaVenta() {
        String input = JOptionPane.showInputDialog("DNI/CUIT cliente");
        if(input != null && !input.isEmpty()){
	        Cliente c = ClienteServicio.getInstancia().buscarCliente(Integer.parseInt(input));
	        if(c != null){
		        VentasForm form  = new VentasForm(c);
		        FormDialog formCreation = new FormDialog(form);
		        formCreation.setSize(600, 500);
		        formCreation.setTitle("Nueva Venta");
		        formCreation.setLocationRelativeTo(null);
		        formCreation.setVisible(true);
	        }else {
	        	JOptionPane.showMessageDialog(null, "Cliente no encontrado");
	        }
        }
    }

    private void onVentaReporte() {
        FormReporteVentas form  = new FormReporteVentas();
        FormDialog formCreation = new FormDialog(form);
        formCreation.setLocationRelativeTo(null);
        formCreation.setVisible(true);
    }


}
