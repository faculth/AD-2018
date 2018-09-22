package vista;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Clientes extends ItemPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected String[] getColumnsForList() {
        return new String[]{"Nombre","dni/cuit","email","telefono", "Particlar?"};
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Agregar":
                ClientesForm form = new ClientesForm();
                FormDialog formCreation = new FormDialog(form);
                formCreation.setSize(400,450);
                formCreation.setLocationRelativeTo(null);
                formCreation.setVisible(true);
                formCreation.setTitle("Alta de clientes");
                //FormDialog.main(form);
            break;
            case "Eliminar":
                int input = JOptionPane.showConfirmDialog(null,"Borrar cliente?");
            break;
        }
    }


    protected void configureActions() {
        actionButton1.setText("Agregar");
        actionButton2.setText("Modificar");
        actionButton3.setText("Eliminar");
        actionButton4.setVisible(false);
        lblSearch.setText("Buscar por DNI/CUIT: ");
        actionButton1.addActionListener(this);
        actionButton2.addActionListener(this);
        actionButton3.addActionListener(this);

    }

    @Override
    protected String getSectionText() {
        return "Clientes";
    }
}
