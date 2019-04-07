package vista;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import modelo.Venta;
import servicios.VentaServicio;

public class Reclamos extends ItemPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected String[] getColumnsForList() {
        return new String[]{"Id Reclamo", "Fecha", "Id Venta", "Estado"};
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Agregar":
                onNuevoReclamo();
                break;
            case "Modificar":
                break;
        }
    }

    protected void configureActions() {
        actionButton1.setText("Agregar");
        actionButton2.setText("Modificar");
        actionButton3.setVisible(false);
        actionButton4.setVisible(false);
        actionButton6.setVisible(false);
        lblSearch.setText("Buscar por Id: ");
        actionButton1.addActionListener(this);
        actionButton2.addActionListener(this);
    }

    @Override
    protected String getSectionText() {
        return "Reclamos";
    }

    private void onNuevoReclamo() {
        String input = JOptionPane.showInputDialog("Ingrese Id de venta: ");
        Venta v = null;
        if(input != null && !input.isEmpty()){
        	v = VentaServicio.getInstancia().buscarVenta(Integer.parseInt(input));
        	if(v.getReclamo() != null){
        			JOptionPane.showMessageDialog(null, "Esta venta ya tiene un reclamo registrado");
        		}else{
        			 ReclamosForm form  = new ReclamosForm(v);
        			 FormDialog formCreation = new FormDialog(form);
        		     formCreation.setLocationRelativeTo(null);
        		     formCreation.setTitle("Nuevo Reclamo");
        		     formCreation.setSize(400, 350);
        		     formCreation.setVisible(true);
        		}
        }
        else{
        	JOptionPane.showMessageDialog(null, "No existe venta con ese Id");
        }
    }
}
