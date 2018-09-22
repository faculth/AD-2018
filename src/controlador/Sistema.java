package controlador;
import Launcher.Launcher;
import modelo.Usuario;
import vista.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;



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
