package vista;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import modelo.Venta;
import servicios.ExportExcelServicio;
import servicios.VentaServicio;

public class FormReporteVentas extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2331083517691239878L;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblHasta;
	private String ruta;
	/**
	 * Create the panel.
	 */
	public FormReporteVentas(){
		setPreferredSize( new Dimension(480, 480) );
		setBackground(UIManager.getColor("Button.shadow"));
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(118, 28, 146, 19);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(118, 73, 146, 19);
		textField_1.setColumns(10);
		add(textField_1);
		
		JLabel lblDesde = new JLabel("Desde: ");
		lblDesde.setBounds(54, 31, 70, 15);
		add(lblDesde);
		
		lblHasta = new JLabel("Hasta:");
		lblHasta.setBounds(54, 76, 70, 15);
		add(lblHasta);
		
		JLabel lblRuta = new JLabel("Ruta de Destino:");
		lblRuta.setBounds(54, 118, 100, 24);
		add(lblRuta);
		
		JButton btnExaminar = new JButton("Examinar...");
		btnExaminar.setBounds(164, 119, 100, 23);
		btnExaminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
		    	fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    	
		    	int option = fileChooser.showDialog(null,"Seleccionar");

		    	if (option == JFileChooser.APPROVE_OPTION) {
		    	    File f = fileChooser.getSelectedFile();
		    	    // if the user accidently click a file, then select the parent directory.
		    	    if (!f.isDirectory()) {
		    	        f = f.getParentFile();
		    	    }
		    	    ruta = f.getAbsolutePath();
		    	}
			}
		});
		add(btnExaminar);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
        case "Guardar":
        	if(!textField.getText().isEmpty() && !textField_1.getText().isEmpty()) {
        		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        		LocalDate dateIni = null;
        		LocalDate dateFin = null;
        		try {
        			dateIni = LocalDate.parse(textField.getText(),formatter);
        			dateFin = LocalDate.parse(textField_1.getText(),formatter);
        		}catch (Exception ex){
        			JOptionPane.showMessageDialog(null, "Fechas inv�lidas");
        			break;
        		}
        		if(dateIni.isBefore(dateFin) || dateIni.isEqual(dateFin)){
        			formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        			int procesoRes = onReport(dateIni.format(formatter), dateFin.format(formatter));
        			if(procesoRes == 0) {
        				JOptionPane.showMessageDialog(null, "Reporte generado correctamente");
        			}else if(procesoRes == 1) {
        				JOptionPane.showMessageDialog(null, "Error al generar el reporte");
        			}else {
        				JOptionPane.showMessageDialog(null, "No se registraron ventas para ese per�odo");
        			}
					JDialog parentDialog=(JDialog)SwingUtilities.getWindowAncestor(this);
					parentDialog.dispose();
        		}else{
        			JOptionPane.showMessageDialog(null, "La fecha de inicio no debe superar a la fecha de fin");
        		}
        	}
        	else{
        		JOptionPane.showMessageDialog(null, "Debe ingresar las fechas.");
        	}
        break;		
        }
	}


	private int onReport(String fecIni, String fecFin) {
		List <Venta> ventas = new ArrayList<Venta>();
		int contador = 2;
		String scontador = String.valueOf(contador);
		String fVenta = "";
		String fechaDesde = textField.getText();
		String fechaHasta = textField_1.getText();
		ventas = VentaServicio.getInstancia().nuevoReporteVentas(fechaDesde,fechaHasta);
		if(!ventas.isEmpty()){
			Map<String, Object[]> data = new TreeMap<String, Object[]>();
			data.put("1", new Object[] {"Id de la venta", "Fecha", "Total","Cliente","Vendedor","Descuento"});
			for(int i = 0;i<ventas.size();i++){
				fVenta = ventas.get(i).getFechaVenta();
				String total = String.valueOf(ventas.get(i).getTotal());
				int dniCli = ventas.get(i).getCliente().getDni();
				int dniUser = ventas.get(i).getUsuario().getDni();
				String descuento = String.valueOf(ventas.get(i).getDescuento());
				data.put(scontador, new Object[] {ventas.get(i).getNumeroVenta(),fVenta,total,dniCli,dniUser,descuento});
				contador++;
				scontador = String.valueOf(contador);
			 }
			 boolean res = ExportExcelServicio.exportExcel("DatosVentas",data,"Ventas_"+fecIni+"_"+fecFin+".xlsx",ruta);
			 if(res) {
				 return 0;
			 }else {
				 return 1;
			 }
		}else {
			return 2;
		}
	}
}
	
	
