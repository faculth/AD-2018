package vista;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Usuarios extends ItemPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected String[] getColumnsForList() {
        return new String[]{"Nombre", "DNI", "Rol"};
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Agregar":
                UserForm form = new UserForm();
                FormDialog formCreation = new FormDialog(form);
                formCreation.setLocationRelativeTo(null);
                formCreation.setVisible(true);
                break;
            case "Eliminar":
                int input = JOptionPane.showConfirmDialog(null,"Borrar Usuario?");
        }
        System.out.println(e.getActionCommand());

    }

    protected void configureActions() {
        actionButton1.setText("Agregar");
        actionButton2.setText("Modificar");
        actionButton3.setText("Eliminar");
        actionButton4.setVisible(false);
        lblSearch.setText("Buscar por DNI: ");
        actionButton1.addActionListener(this);
        actionButton2.addActionListener(this);
        actionButton3.addActionListener(this);
        actionButton4.addActionListener(this);
    }

    @Override
    protected String getSectionText() {
        return "Usuarios";
    }
}
