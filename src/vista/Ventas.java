package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controlador.LoginController;
import modelo.Cliente;
import modelo.Usuario;
import modelo.Venta;
import persistencia.VentaMapper;
import servicios.ClienteServicio;
import servicios.VentaServicio;

public class Ventas extends ItemPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Override
    protected String[] getColumnsForList() {
        return new String[]{"Id Venta", "Fecha", "DNI/CUIT Cliente", "Total", "Envio"};
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Nueva Venta":
                onNuevaVenta();
            break;
            case "Generar Reporte":
                onVentaReporte();
            break;
            case "Buscar":
            	if(search.getText().isEmpty()) {
            		cargarVentas(0,30);
            		break;
            	}
                Venta v = VentaServicio.getInstancia().buscarVenta(Integer.parseInt(search.getText()));
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();//.setValueAt("PRUEBA", 0, 2);
                tableModel.setRowCount(0);
                if(v != null) {
                	agregarVentaTabla(v);
                }else {
                	JOptionPane.showMessageDialog(null, "Busqueda sin resultados");
                }
            break;
        }
    }

    protected void configureActions() {
        actionButton1.setText("Nueva Venta");
        actionButton2.setText("Generar Reporte");
        actionButton3.setVisible(false);
        actionButton4.setVisible(false);
        actionButton6.setVisible(false);
        lblSearch.setText("Buscar por Id: ");
        actionButton1.addActionListener(this);
        actionButton2.addActionListener(this);
        actionButton5.addActionListener(this);
        
        bloquearBotones();
        
        itemsCount = VentaMapper.getInstancia().getCantVentas();
        setPagesInfo();
        
        back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentPage > 0){
					currentPage--;
					cargarVentas(currentPage*30, 30);
					setPagesInfo();
				}
			}
		});
        
        next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((currentPage+1) *30) < itemsCount){
					currentPage++;
					cargarVentas(currentPage*30, 30);
					setPagesInfo();
				}
			}
		});
        
        cargarVentas(0,30);
        
        this.addComponentListener(new ComponentListener() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				cargarVentas(currentPage*30, 30);
			}
			
			@Override
			public void componentResized(ComponentEvent arg0) {}
			
			@Override
			public void componentMoved(ComponentEvent arg0) {}
			
			@Override
			public void componentHidden(ComponentEvent arg0) {}
		});
    }
    
    private void bloquearBotones() {
	    Usuario user = LoginController.getUsuarioLogueado();
	   	 if(user.getRol().getNombre().equals("Vendedor")){
            actionButton2.setEnabled(false);
	   	 }
	   	 else if(user.getRol().getNombre().equals("Encargado")){
	   		actionButton1.setEnabled(false);
	   	 }
	}

	private void cargarVentas(int inicio, int fin) {
    	searchModel.setRowCount(0);
        List<Venta> ventas = VentaServicio.getInstancia().obtenerVentas(inicio, fin);
        ventas.forEach(v -> agregarVentaTabla(v));
        
    }
    
    private void agregarVentaTabla(Venta v) {
        String estadoEnvio = v.getEnvio() == null ? "" : v.getEnvio().getEstado();
        searchModel.addRow(new Object[]{v.getNumeroVenta(), v.getFechaVenta(), v.getCliente().getDni(), v.getTotal(), estadoEnvio});
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
		        formCreation.addWindowListener(new WindowAdapter() {
		                @Override
		                public void windowClosed(WindowEvent e) {
		                	cargarVentas(currentPage*30, 30);
		                }
		        });

	        }else {
	        	JOptionPane.showMessageDialog(null, "Cliente no encontrado");
	        }
        }
    }

    private void onVentaReporte() {
        FormReporteVentas form  = new FormReporteVentas();
        FormDialog formCreation = new FormDialog(form);
        formCreation.setLocationRelativeTo(null);
        formCreation.setTitle("Reporte de Ventas");
        formCreation.setSize(400,250);
        formCreation.setVisible(true);
    }


}
