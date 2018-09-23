package vista;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Producto;
import servicios.ProductoServicio;

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
                //String input = JOptionPane.showInputDialog("Ingrese codigo de producto: ");
                ProductForm form = new ProductForm(null);
                FormDialog formCreation = new FormDialog(form);
                formCreation.setTitle("Alta de productos");
                formCreation.setSize(400, 380);
                formCreation.setLocationRelativeTo(null);
                formCreation.setVisible(true);
                break;
            case "Modificar":
                String input2 = JOptionPane.showInputDialog("Ingrese codigo de producto: ");
                if(input2 != null && !input2.isEmpty()){
                	Producto p = ProductoServicio.getInstancia().buscarProducto(Integer.parseInt(input2));
	                ProductForm form2 = new ProductForm(p);
	                FormDialog formCreation2 = new FormDialog(form2);
	                formCreation2.setTitle("Modificación de productos");
	                formCreation2.setSize(400, 380);
	                formCreation2.setLocationRelativeTo(null);
	                formCreation2.setVisible(true);
                }
                break;
            case "Generar reporte":
                onItemReporte();
                break;
            case "Buscar":
            	if(search.getText().isEmpty()) {
            		cargarProductos(0,30);
            		break;
            	}
                Producto p = ProductoServicio.getInstancia().buscarProducto(Integer.parseInt(search.getText()));
                searchModel.setRowCount(0);
                if(p != null) {
                	agregarProductoTabla(p);
                }else {
                	JOptionPane.showMessageDialog(null, "Busqueda sin resultados");
                }
            break;
        }

        System.out.println(e.getActionCommand());
    }

    @Override
    protected String[] getColumnsForList() {
        return new String[]{"codigo", "nombre", "stock" ,"descripcion", "marca", "modelo"};
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
        
        itemsCount = ProductoServicio.getInstancia().getCantProd();
        setPagesInfo();
        
        back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentPage > 0){
					currentPage--;
					cargarProductos(currentPage*30,currentPage *30 +30);
					setPagesInfo();
				}
			}
		});
        
        next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((currentPage+1) *30) < itemsCount){
					currentPage++;
					cargarProductos(currentPage*30,currentPage *30 +30);
					setPagesInfo();
				}
			}
		});
        
        cargarProductos(0,30);
    }
    
    private void cargarProductos(int inicio, int fin) {
    	searchModel.setRowCount(0);
        List<Producto> productos = ProductoServicio.getInstancia().obtenerProductos(inicio, fin);
        productos.forEach(p -> agregarProductoTabla(p));
    }
    
    private void agregarProductoTabla(Producto p) {
        searchModel.addRow(new Object[]{p.getCodigoProducto(), p.getNombre(), p.getStock(), p.getDescripcion(), p.getDescripcion(), p.getMarca(), p.getModelo()});
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
