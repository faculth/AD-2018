package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import modelo.Cliente;
import servicios.ClienteServicio;

import java.awt.event.ActionEvent;

public class Clientes extends ItemPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected String[] getColumnsForList() {
        return new String[]{"Nombre","dni/cuit","email","telefono", "Particular?"};
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
                Cliente cl = ClienteServicio.getInstancia().buscarCliente(Integer.parseInt(search.getText()));
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();//.setValueAt("PRUEBA", 0, 2);
                tableModel.setRowCount(0);
                if(cl != null) {
	                tableModel.addRow(new Object[]{cl.getNombre()+" "+cl.getApellido(), cl.getDni(), cl.getEmail(), cl.getEmail(), cl.getTelefono(), cl.getTipoCliente()});
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
    }

    @Override
    protected String getSectionText() {
        return "Clientes";
    }
}
