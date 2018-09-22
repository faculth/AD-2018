package vista;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Reclamos extends ItemPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected String[] getColumnsForList() {
        return new String[]{"id", "fecha", "id_venta", "Estado"};
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
        ReclamosForm form  = new ReclamosForm();
        FormDialog formCreation = new FormDialog(form);
        formCreation.setLocationRelativeTo(null);
        formCreation.setVisible(true);
    }
}
