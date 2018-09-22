package vista;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class Productos extends ItemPanel {



    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Productos() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Agregar":
                String input = JOptionPane.showInputDialog("Ingrese codigo de producto: ");
                ProductForm form = new ProductForm(input);
                FormDialog formCreation = new FormDialog(form);
                formCreation.setTitle("Alta de productos");
                formCreation.setSize(400, 380);
                formCreation.setLocationRelativeTo(null);
                formCreation.setVisible(true);
                break;
            case "Eliminar":
                int asd = JOptionPane.showConfirmDialog(null, "Borrar Producto?");
                break;
            case "Generar reporte":
                onItemReporte();
                break;
        }

        System.out.println(e.getActionCommand());
    }

    @Override
    protected String[] getColumnsForList() {
        return new String[]{"id", "nombre", "codigo", "stock" ,"descripcion", "marca", "modelo"};
    }

    @Override
    protected void configureActions() {
        actionButton1.setText("Agregar");
        actionButton2.setText("Modificar");
        actionButton3.setText("Eliminar");
        actionButton4.setText("Generar reporte");
        lblSearch.setText("Buscar por Id: ");
        actionButton1.addActionListener(this);
        actionButton2.addActionListener(this);
        actionButton3.addActionListener(this);
        actionButton4.addActionListener(this);
        actionButton5.addActionListener(this);
    }

    private void onItemReporte() {
        FormReporteProductos form  = new FormReporteProductos();
        FormDialog formCreation = new FormDialog(form);
        formCreation.setLocationRelativeTo(null);
        formCreation.setVisible(true);
    }

    @Override
    protected String getSectionText() {
        return "Productos";
    }
}
