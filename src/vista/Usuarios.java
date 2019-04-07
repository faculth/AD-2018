package vista;

import java.awt.event.ActionEvent;
import java.util.List;

import modelo.Usuario;
import servicios.UsuarioServicio;

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
                formCreation.setTitle("Nuevo Usuario");
                formCreation.setVisible(true);
                break;
            case "Eliminar":
        }
        System.out.println(e.getActionCommand());

    }

    protected void configureActions() {
        actionButton1.setText("Agregar");
        actionButton2.setText("Modificar");
        actionButton3.setText("Eliminar");
        actionButton4.setVisible(false);
        actionButton6.setVisible(false);
        lblSearch.setText("Buscar por DNI: ");
        actionButton1.addActionListener(this);
        actionButton2.addActionListener(this);
        actionButton3.addActionListener(this);
        actionButton4.addActionListener(this);
        cargarUsuarios();
    }
    
    private void cargarUsuarios() {
    	searchModel.setRowCount(0);
        List<Usuario> usuarios = UsuarioServicio.getInstancia().obtenerUsuarios();
        usuarios.forEach(u -> agregarUsuarioTabla(u));
    }
    
    private void agregarUsuarioTabla(Usuario u) {
        searchModel.addRow(new Object[]{u.getNombre(), u.getDni(), u.getRol().getNombre()});
    }

    @Override
    protected String getSectionText() {
        return "Usuarios";
    }
}
