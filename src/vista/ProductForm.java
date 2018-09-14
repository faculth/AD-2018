package vista;

import controlador.LoginController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductForm  extends JPanel implements ActionListener {
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private LoginController controller;
    private int usrId;
    private JTextField txtPrecio;
    private JTextField txtCantidad;
    private JTextField txtMarca;
    private JTextField txtModelo;

    /**
     * Create the panel.
     */
    public ProductForm(String productId) {
        setBackground(UIManager.getColor("Button.shadow"));
        usrId = -1;
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
        
        txtNombre = new JTextField();
        txtNombre.setBounds(130, 73, 182, 19);
        add(txtNombre);
        txtNombre.setColumns(10);
        
        JTextArea txtDescripcion = new JTextArea();
        txtDescripcion.setBounds(131, 105, 182, 38);
        add(txtDescripcion);
        
        txtPrecio = new JTextField();
        txtPrecio.setColumns(10);
        txtPrecio.setBounds(130, 218, 182, 19);
        add(txtPrecio);
        
        txtCantidad = new JTextField();
        txtCantidad.setColumns(10);
        txtCantidad.setBounds(129, 248, 182, 19);
        add(txtCantidad);
        
        txtMarca = new JTextField();
        txtMarca.setColumns(10);
        txtMarca.setBounds(130, 188, 182, 19);
        add(txtMarca);
        
        txtModelo = new JTextField();
        txtModelo.setColumns(10);
        txtModelo.setBounds(131, 156, 182, 19);
        add(txtModelo);
    }

    public void addNameInfo(String name) {
        this.txtNombre.setText(name);
    }



    public void setUserController(LoginController userController) {
        this.controller = userController;
    }

    public void setUsrId(int usrId) {
        this.usrId = usrId;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Guardar":
                boolean saved = this.doSave();
                if (saved) {
                    System.out.println("Producto Guardado");
                    //LIMPIAR CAMPOS
                }
                break;
        }
    }

    private boolean doSave() {
        return true;
    }
    
    
}