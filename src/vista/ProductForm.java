package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import modelo.Producto;
import servicios.ProductoServicio;

public class ProductForm  extends JPanel implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtCantidad;
    private JTextField txtMarca;
    private JTextField txtModelo;
    private JTextArea txtDescripcion;

    /**
     * Create the panel.
     */
    public ProductForm(Producto producto,String accion) {
        setBackground(UIManager.getColor("Button.shadow"));
        setLayout(null);

        JLabel lblName = new JLabel("Nombre:");
        lblName.setBounds(50, 73, 70, 15);
        add(lblName);

        JLabel lbldescripcion = new JLabel("Descripcion:");
        lbldescripcion.setBounds(51, 107, 94, 15);
        add(lbldescripcion);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(49, 157, 94, 15);
        add(lblPrecio);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(50, 191, 169, 15);
        add(lblCantidad);
        
        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setBounds(50, 221, 169, 15);
        add(lblMarca);
        
        JLabel lblModelo = new JLabel("Modelo:");
        lblModelo.setBounds(49, 251, 169, 15);
        add(lblModelo);
        
        JLabel lblCodigo = new JLabel("Codigo:");
        lblCodigo.setBounds(50, 42, 70, 15);
        add(lblCodigo);
        
        txtCodigo = new JTextField();
        txtCodigo.setBounds(130, 39, 182, 19);
        add(txtCodigo);
        txtCodigo.setColumns(10);
        txtCodigo.setText(String.valueOf(producto.getCodigoProducto()));
        txtCodigo.setEditable(false);
        if(accion != null && accion.equals("Stock")){
        	txtCodigo.setEditable(false);
        }
        
        txtNombre = new JTextField();
        txtNombre.setBounds(130, 73, 182, 19);
        add(txtNombre);
        txtNombre.setColumns(10);
        txtNombre.setText(producto.getNombre());
        if(accion != null && accion.equals("Stock")){
        	txtNombre.setEditable(false);
        }
        
        txtDescripcion = new JTextArea();
        txtDescripcion.setBounds(131, 105, 182, 38);
        add(txtDescripcion);
        txtDescripcion.setText(producto.getDescripcion());
        if(accion != null && accion.equals("Stock")){
        	txtDescripcion.setEditable(false);
        }
        
        txtPrecio = new JTextField();
        txtPrecio.setColumns(10);
        txtPrecio.setBounds(130, 157, 182, 19);
        add(txtPrecio);
        txtPrecio.setText(String.valueOf(producto.getPrecio()));
        if(accion != null && accion.equals("Stock")){
        	txtPrecio.setEditable(false);
        }
        
        txtCantidad = new JTextField();
        txtCantidad.setColumns(10);
        txtCantidad.setBounds(130, 188, 182, 19);
        add(txtCantidad);
        txtCantidad.setText(String.valueOf(producto.getStock()));
        if(accion != null && accion.equals("Modificar")){
        	txtCantidad.setEditable(false);
        }
        
        txtMarca = new JTextField();
        txtMarca.setColumns(10);
        txtMarca.setBounds(130, 218, 182, 19);
        add(txtMarca);
        txtMarca.setText(producto.getMarca());
        if(accion != null && accion.equals("Stock")){
        	txtMarca.setEditable(false);
        }
        
        
        txtModelo = new JTextField();
        txtModelo.setColumns(10);
        txtModelo.setBounds(130, 247, 182, 19);
        add(txtModelo);
        txtModelo.setText(producto.getModelo());
        if(accion != null && accion.equals("Stock")){
        	txtModelo.setEditable(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Guardar":
            	Producto p = new Producto();
				p.setCodigoProducto(Integer.parseInt(txtCodigo.getText()));
				p.setNombre(txtNombre.getText());
				p.setPrecio(Integer.parseInt(txtPrecio.getText()));
				p.setDescripcion(txtDescripcion.getText());
				p.setStock(Integer.parseInt(txtCantidad.getText()));
				p.setMarca(txtMarca.getText());
				p.setModelo(txtModelo.getText());
                boolean saved = ProductoServicio.getInstancia().update(p);
                if (saved) {
                	JOptionPane.showMessageDialog(null, "Producto actualizado correctamente");
                }else{
                	JOptionPane.showMessageDialog(null, "Error al actualizar el producto");
                }
                JDialog parentDialog=(JDialog)SwingUtilities.getWindowAncestor(this);
				parentDialog.dispose();
                break;
        }
    }   
    
}