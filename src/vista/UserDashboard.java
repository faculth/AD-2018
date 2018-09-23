package vista;



import controlador.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.CardLayout;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;


public class UserDashboard extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -978932224043486391L;
	private JPanel mainPanel;
    private CardLayout cardLayout;
    private Sistema controller;
    private JPanel defaultPanel;

    public void addToCardLayout(JPanel panel, String identifier) {
        mainPanel.add(panel, identifier);
    }

   public void setController(Sistema controller) {
        this.controller = controller;
    }

    public UserDashboard() {
    	setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
    	setBackground(UIManager.getColor("InternalFrame.borderDarkShadow"));
		setForeground(Color.DARK_GRAY);
		setLayout(null);
		//** TODO GENERATE ABSTRACT CLASS FROM JPANEL TO SHOW CONTENT
        //** EMPTY first time
		mainPanel = new JPanel();
		mainPanel.setBounds(125, 59, 887, 697);
		add(mainPanel);
		cardLayout = new CardLayout(0, 0);
		JPanel cardLayout2 = new JPanel();
		mainPanel.setLayout(cardLayout);
		defaultPanel = new JPanel();
		defaultPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		defaultPanel.setBackground(UIManager.getColor("Button.shadow"));
		mainPanel.add(defaultPanel, "default");

		Button productos = new Button("Productos");
		productos.setBackground(Color.LIGHT_GRAY);
		productos.addActionListener(this);
		productos.setBounds(10, 187, 99, 23);
		add(productos);
		
		Button reclamos = new Button("Reclamos");
		reclamos.setBackground(Color.LIGHT_GRAY);
		reclamos.addActionListener(this);
		reclamos.setBounds(10, 245, 99, 23);
		add(reclamos);
		
		Button clientes = new Button("Clientes");
		clientes.setBackground(Color.LIGHT_GRAY);
		clientes.addActionListener(this);
		clientes.setBounds(10, 274, 99, 23);
		add(clientes);
		
		Button ventas = new Button("Ventas");
		ventas.setBackground(Color.LIGHT_GRAY);
		ventas.setBounds(10, 158, 99, 23);
		ventas.addActionListener(this);
		add(ventas);
		
		Label welcomeMsg = new Label("NG PERGAL S.R.L");
		welcomeMsg.setFont(new Font("Dialog", Font.BOLD, 19));
		welcomeMsg.setAlignment(Label.CENTER);
		welcomeMsg.setBounds(286, 26, 394, 21);
		add(welcomeMsg);
		
		Button envios = new Button("Envios");
		envios.setBackground(Color.LIGHT_GRAY);
		envios.setBounds(10, 216, 99, 23);
		envios.addActionListener(this);
		add(envios);

		Button logout = new Button("Salir");
		logout.setBackground(Color.LIGHT_GRAY);
		logout.setBounds(10, 704, 99, 23);
		logout.addActionListener(this);
		add(logout);
		
		Button usuarios = new Button("Usuarios");
		usuarios.setBackground(Color.LIGHT_GRAY);
		usuarios.setBounds(10, 303, 99, 23);
		usuarios.addActionListener(this);
		add(usuarios);


	}

	public void setMainPanel(JPanel mainPanel) {
       /* mainPanel = new ListCreationForm();
        mainPanel.setBounds(118, 65, 704, 466);
        add(mainPanel);*/
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getActionCommand() == "Salir") {
			System.exit(0);
		}
    	controller.showCard(e.getActionCommand());
    }

    public void  showDefault() {
		cardLayout.show(mainPanel, "default");
	}

	public void showPanel(String panelName) {
    	cardLayout.show(mainPanel, panelName);
	}
}
