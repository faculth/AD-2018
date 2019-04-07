package Launcher;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import controlador.IController;
import controlador.LoginController;
import controlador.Sistema;

public class Launcher {

        private JFrame frame;
        private IController controller;

        public Launcher () {
            frame  = new JFrame("NG-PERGAL");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(480, 250);
            frame.setLocationRelativeTo(null);
            controller = new LoginController(frame);
            controller.setMain(this);
        }

        public void run() {
            controller.updateView();
        }
        
        public void exit() {
        	System.exit(0);
        }

        public void OnUserLogout() {
            controller = new LoginController(frame);
            controller.setMain(this);
            run();
        }

        public void onUserLogin() {
            controller = new Sistema(frame);
            controller.setMain(this);
            this.frame.setSize(1070, 830);
            frame.setLocationRelativeTo(null);
            run();
        }
}
