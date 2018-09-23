package vista;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.Cliente;
import servicios.ClienteServicio;

public class Clientes extends ItemPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected String[] getColumnsForList() {
        return new String[]{"Nombre","dni/cuit","email","telefono", "Tipo Cliente"};
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
            break;
            case "Eliminar":
                int input = JOptionPane.showConfirmDialog(null,"Borrar cliente?");
            break;
            case "Buscar":
            	if(search.getText().isEmpty()) {
            		cargarClientes();
            		break;
            	}
                Cliente cl = ClienteServicio.getInstancia().buscarCliente(Integer.parseInt(search.getText()));
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();//.setValueAt("PRUEBA", 0, 2);
                tableModel.setRowCount(0);
                if(cl != null) {
	                agregarClienteTabla(cl);
                }else {
                	JOptionPane.showMessageDialog(null, "Busqueda sin resultados");
                }
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
        actionButton5.addActionListener(this);
        cargarClientes();
    }
    
    private void cargarClientes() {
    	List<Cliente> clientes = ClienteServicio.getInstancia().obtenerClientes();
        clientes.forEach(c -> agregarClienteTabla(c));
    }
    
    private void agregarClienteTabla(Cliente cl) {
        searchModel.addRow(new Object[]{cl.getNombre()+" "+cl.getApellido(), cl.getDni(), cl.getEmail(), cl.getTelefono(), cl.getTipoCliente()});
    }

    @Override
    protected String getSectionText() {
        return "Clientes";
    }
}
