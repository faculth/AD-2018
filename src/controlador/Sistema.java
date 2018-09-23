package controlador;
import javax.swing.JFrame;

import Launcher.Launcher;
import vista.Clientes;
import vista.Envios;
import vista.Productos;
import vista.Reclamos;
import vista.UserDashboard;
import vista.Usuarios;
import vista.Ventas;



public class Sistema implements  IController{

	private JFrame frame;
	private Launcher main;
	private UserDashboard userDashboard;
	
	public Sistema(JFrame frame) {
		this.frame	= frame;
		userDashboard = new UserDashboard();
		userDashboard.addToCardLayout(new Productos(), "Productos");
		userDashboard.addToCardLayout(new Ventas(), "Ventas");
		userDashboard.addToCardLayout(new Envios(), "Envios");
		userDashboard.addToCardLayout(new Reclamos(), "Reclamos");
		userDashboard.addToCardLayout(new Clientes(), "Clientes");
		userDashboard.addToCardLayout(new Usuarios(), "Usuarios");
		userDashboard.setController(this);
	}


	@Override
	public void updateView() {
		frame.setContentPane(userDashboard);
		frame.revalidate();
		frame.repaint();
	}

	@Override
	public void setMain(Launcher launcher) {
		this.main = launcher;
	}


	public void  showCard(String cardName) {
		userDashboard.showPanel(cardName);
	}
}
