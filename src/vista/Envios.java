package vista;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Envio;
import modelo.Venta;
import servicios.EnvioServicio;
import servicios.VentaServicio;

public class Envios extends ItemPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected String[] getColumnsForList() {
        return new String[]{"id", "estado"};
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch(e.getActionCommand()) {
            case "Nuevo":
                onNewEnvio();
                break;
            case "Editar":
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
        actionButton1.setText("Nuevo");
        actionButton2.setText("Editar");
        actionButton3.setVisible(false);
        actionButton4.setVisible(false);
        lblSearch.setText("Buscar por Id: ");
        actionButton1.addActionListener(this);
        actionButton2.addActionListener(this);
        actionButton5.addActionListener(this);
        
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
        		}
        	}else {
        		JOptionPane.showMessageDialog(null, "No existe venta con ese Id");
        	}
        }
    }
    
	private void onEdit() {
        String input = JOptionPane.showInputDialog("Ingrese Id de venta: ");
        if(input != null && !input.isEmpty()){
        	Venta v = VentaServicio.getInstancia().buscarVenta(Integer.parseInt(input));
        	if(v != null && v.getEnvio() != null){
		        EnviosForm form  = new EnviosForm(v);
		        FormDialog formCreation = new FormDialog(form);
		        formCreation.setSize(350, 380);
		        formCreation.setLocationRelativeTo(null);
		        formCreation.setVisible(true);
        	}else {
        		JOptionPane.showMessageDialog(null, "La venta no existe o no tiene envio registrado");
        	}
        }
	}
}
