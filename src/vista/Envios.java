package vista;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Envios extends ItemPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected String[] getColumnsForList() {
        return new String[]{"id", "fecha", "id_venta", "estado"};
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch(e.getActionCommand()) {
            case "Nuevo":
                onNewEnvio();
                break;
            case "Editar":
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
    }

    @Override
    protected String getSectionText() {
        return "Envios";
    }

    private void onNewEnvio() {
        String input = JOptionPane.showInputDialog("Ingrese Id de venta: ");
        EnviosForm form  = new EnviosForm();
        FormDialog formCreation = new FormDialog(form);
        formCreation.setLocationRelativeTo(null);
        formCreation.setVisible(true);
    }
}
