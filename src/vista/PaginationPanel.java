package vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaginationPanel extends JPanel implements ActionListener {

    public PaginationPanel() {
        JButton back = new JButton("<");
        this.add(back);

        JLabel lblNewLabel = new JLabel("New label");
        this.add(lblNewLabel);

        JButton next = new JButton(">");
        this.add(next);
    }


    public void actionPerformed(ActionEvent e) {
        System.out.println("Something was pressed");
    }
}
