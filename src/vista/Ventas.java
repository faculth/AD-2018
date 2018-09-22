package vista;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Ventas extends ItemPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField priceField;

    @Override
    protected String[] getColumnsForList() {
        return new String[]{"id", "fecha", "dni/cuit cliente", "Total", "Retirada"};
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Nueva":
                onNuevaVenta();
            break;
            case "Generar Reporte":
                onVentaReporte();
            break;
        }
    }

    protected void configureActions() {
        actionButton1.setText("Nueva");
        actionButton2.setText("Generar Reporte");
        actionButton3.setVisible(false);
        actionButton4.setVisible(false);
        lblSearch.setText("Buscar por Id: ");
        actionButton1.addActionListener(this);
        actionButton2.addActionListener(this);
    }

    @Override
    protected String getSectionText() {
        return "Ventas";
    }

    private void onNuevaVenta() {
        String input = JOptionPane.showInputDialog("DNI/CUIT comprador");
        VentasForm form  = new VentasForm(input, "John Doe");
        FormDialog formCreation = new FormDialog(form);
        formCreation.setLocationRelativeTo(null);
        formCreation.setVisible(true);
    }

    private void onVentaReporte() {
        FormReporteVentas form  = new FormReporteVentas();
        FormDialog formCreation = new FormDialog(form);
        formCreation.setLocationRelativeTo(null);
        formCreation.setVisible(true);
    }


}
