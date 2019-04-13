package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SpringLayout;

import modelo.Envio;
import modelo.Venta;
import servicios.EnvioServicio;
import servicios.VentaServicio;

public class Envios extends ItemPanel {
	public Envios() {
		super();
	}
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected String[] getColumnsForList() {
        return new String[]{"ID Envio", "Estado de Envio"};
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "Registrar Nuevo Envio":
                onNewEnvio();
                break;
            case "Actualizar Estado de Envio":
            	onEdit();
                break;
            case "Buscar":
            	if(search.getText().isEmpty()) {
            		cargarEnvios(0,30);
            		break;
            	}
                Envio envio = EnvioServicio.getInstancia().buscarEnvio(Integer.parseInt(search.getText()));
                searchModel.setRowCount(0);
                if(envio != null) {
                	agregarEnvioTabla(envio);
                }else {
                	JOptionPane.showMessageDialog(null, "Busqueda sin resultados");
                }
                break;
        }
    }

	protected void configureActions() {
        actionButton1.setText("Registrar Nuevo Envio");
        actionButton2.setText("Actualizar Estado de Envio");
        actionButton3.setVisible(false);
        actionButton4.setVisible(false);
        actionButton6.setVisible(false);
        layout.putConstraint(SpringLayout.EAST, search, 100, SpringLayout.EAST, actionButton4);
        actionButton1.addActionListener(this);
        actionButton2.addActionListener(this);
        actionButton5.addActionListener(this);
        lblSearch.setText("Buscar por Id: ");
        cargarEnvios(0,30);
    }
    
    private void cargarEnvios(int inicio, int fin) {
    	searchModel.setRowCount(0);
        List<Envio> envios = EnvioServicio.getInstancia().obtenerEnvios();
        envios.forEach(e -> agregarEnvioTabla(e));
    }
    
    private void agregarEnvioTabla(Envio e) {
        searchModel.addRow(new Object[]{e.getNumEnvio(), e.getEstado()});
    }

    @Override
    protected String getSectionText() {
        return "Envios";
    }

    private void onNewEnvio() {
        String input = JOptionPane.showInputDialog("Ingrese Id de venta: ");
        if(input != null && !input.isEmpty()){
        	Venta v = VentaServicio.getInstancia().buscarVenta(Integer.parseInt(input));
        	if(v != null){
        		if(v.getEnvio() != null) {
        			JOptionPane.showMessageDialog(null, "Esta venta ya tiene un envio registrado");
        		}else{
			        EnviosForm form  = new EnviosForm(v);
			        form.setComboEnabled(false);
			        FormDialog formCreation = new FormDialog(form);
			        formCreation.setSize(350, 380);
			        formCreation.setLocationRelativeTo(null);
			        formCreation.setVisible(true);
			        formCreation.setTitle("Nuevo Envío");
			        formCreation.addWindowListener(new WindowAdapter() {
		                @Override
		                public void windowClosed(WindowEvent e) {
		                	cargarEnvios(currentPage*30, 30);
		                }
			        });
        		}
        	}else {
        		JOptionPane.showMessageDialog(null, "No existe venta con ese Id");
        	}
        }
    }
    
	private void onEdit() {
        int status = 0;
        String input = JOptionPane.showInputDialog("Ingrese Id de venta: ");
        if(input != null && !input.isEmpty()){
        	status = EnvioServicio.getInstancia().buscarEnvioPorVenta(Integer.parseInt(input));
        	if(status == 1){
        		Venta v = VentaServicio.getInstancia().buscarVenta(Integer.parseInt(input));
        		if(v != null && v.getEnvio() != null){
    		        EnviosForm form  = new EnviosForm(v);
    		        FormDialog formCreation = new FormDialog(form);
    		        formCreation.setSize(350, 380);
    		        formCreation.setLocationRelativeTo(null);
    		        formCreation.setTitle("Editar Envío");
    		        formCreation.setVisible(true);
			        formCreation.addWindowListener(new WindowAdapter() {
		                @Override
		                public void windowClosed(WindowEvent e) {
		                	cargarEnvios(currentPage*30, 30);
		                }
			        });
        		}
        	}
        	else if(status == 0) {
        		JOptionPane.showMessageDialog(null, "La venta no posee un envio registrado");
        	}
        	else if(status == -1){
        		JOptionPane.showMessageDialog(null, "La venta no existe");
        	}
        }
	}
}
