package vista;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class FormReporteProductos extends JPanel implements ActionListener {
    private JTextField textField_1;
    private JLabel lblHasta;
    private JComboBox comboBox;

    /**
     * Create the panel.
     */
    public FormReporteProductos() {
        setPreferredSize( new Dimension(529, 142) );
        setBackground(UIManager.getColor("Button.shadow"));
        setLayout(null);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(244, 74, 146, 19);
        add(textField_1);

        JLabel lblDesde = new JLabel("Generar reporte por :");
        lblDesde.setBounds(54, 31, 205, 15);
        add(lblDesde);

        lblHasta = new JLabel("Ingrese modelo:");
        lblHasta.setBounds(54, 74, 140, 19);
        add(lblHasta);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Marca", "Modelo"}));
        comboBox.setBounds(244, 26, 146, 24);
        add(comboBox);
        comboBox.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (comboBox.getSelectedItem().toString() == "Marca") {
            lblHasta.setText("Ingrese marca:");

        } else {
            lblHasta.setText("Ingrese modelo:");
        }
        textField_1.setVisible(true);
    }
}