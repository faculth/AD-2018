package vista;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JOptionPane;
import controlador.LoginController;
import modelo.Producto;
import modelo.Usuario;
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
                break;
            case "Modificar":
                String input2 = JOptionPane.showInputDialog("Ingrese codigo de producto: ");
                if(input2 != null && !input2.isEmpty()){
                	Producto p = ProductoServicio.getInstancia().buscarProducto(Integer.parseInt(input2));
                	if(p != null){
                		ProductForm form2 = new ProductForm(p,"Modificar");
    	                FormDialog formCreation2 = new FormDialog(form2);
    	                formCreation2.setTitle("Modificación de productos");
    	                formCreation2.setSize(400, 380);
    	                formCreation2.setLocationRelativeTo(null);
    	                formCreation2.setVisible(true);
    	                formCreation2.addWindowListener(new WindowAdapter() {
    		                @Override
    		                public void windowClosed(WindowEvent e) {
    		                	cargarProductos(currentPage*30, 30);
    		                }
    			        });
                	}
                	else
                		JOptionPane.showMessageDialog(null, "Busqueda sin resultados.");
                }
                break;
            case "Actualizar Stock":
            		   input2 = JOptionPane.showInputDialog("Ingrese codigo de producto: ");
                if(input2 != null && !input2.isEmpty()){
                	Producto p = ProductoServicio.getInstancia().buscarProducto(Integer.parseInt(input2));
                	if(p != null){
		                ProductForm form2 = new ProductForm(p,"Stock");
		                FormDialog formCreation2 = new FormDialog(form2);
		                formCreation2.setTitle("Actualizacion de Stock");
		                formCreation2.setSize(400, 380);
		                formCreation2.setLocationRelativeTo(null);
		                formCreation2.setVisible(true);
    	                formCreation2.addWindowListener(new WindowAdapter() {
    		                @Override
    		                public void windowClosed(WindowEvent e) {
    		                	cargarProductos(currentPage*30, 30);
    		                }
    			        });
                	}
                	else
                        JOptionPane.showMessageDialog(null, "Busqueda sin resultados.");
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
                	JOptionPane.showMessageDialog(null, "Busqueda sin resultados.");
                }
            break;
        }
    }

    @Override
    protected String[] getColumnsForList() {
        return new String[]{"Codigo", "Nombre", "Stock" ,"Descripcion", "Marca", "Modelo"};
    }

    @Override
    protected void configureActions() {
        actionButton1.setText("Agregar");
        actionButton2.setText("Modificar");
        actionButton3.setText("Eliminar");
        actionButton4.setText("Generar reporte");
        actionButton6.setText("Actualizar Stock");
        lblSearch.setText("Buscar por Codigo: ");
        actionButton1.addActionListener(this);
        actionButton2.addActionListener(this);
        actionButton3.addActionListener(this);
        actionButton4.addActionListener(this);
        actionButton5.addActionListener(this);
        actionButton6.addActionListener(this);
        
        this.addComponentListener(new ComponentListener() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				cargarProductos(currentPage*30, 30);
			}
			
			@Override
			public void componentResized(ComponentEvent arg0) {}
			
			@Override
			public void componentMoved(ComponentEvent arg0) {}
			
			@Override
			public void componentHidden(ComponentEvent arg0) {}
		});
        bloquearBotones();
        
        itemsCount = ProductoServicio.getInstancia().getCantProd();
        setPagesInfo();
        
        back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentPage > 0){
					currentPage--;
					cargarProductos(currentPage*30, 30);
					setPagesInfo();
				}
			}
		});
        
        next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((currentPage+1) *30) < itemsCount){
					currentPage++;
					cargarProductos(currentPage*30, 30);
					setPagesInfo();
				}
			}
		});
        
        cargarProductos(0,30);
    }
    
    private void bloquearBotones() {
		 Usuario user = LoginController.getUsuarioLogueado();
		 if(user.getRol().getNombre().equals("Encargado")){
			 actionButton1.setEnabled(false);
	         actionButton2.setEnabled(false);
	         actionButton3.setEnabled(false);
	         actionButton6.setEnabled(false);
		 }
		 else if(user.getRol().getNombre().equals("Vendedor")){
			 actionButton4.setEnabled(false);
		 }
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
        formCreation.setTitle("Reporte de Productos");
        formCreation.setSize(529, 200);
        formCreation.setVisible(true);
    }

    @Override
    protected String getSectionText() {
        return "Productos";
    }
}
