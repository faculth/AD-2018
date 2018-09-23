package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class FormDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel;

	/**
	 * Create the dialog.
	 */
	public FormDialog(JPanel form) {
		this.setResizable(false);
		contentPanel = form;
        System.out.println(form.getWidth());
        System.out.println( form.getHeight());
		if (form.getPreferredSize() != null) {
            setSize( getPreferredSize().width + 50 , getPreferredSize().height +50);
        }
		setBounds(100, 100, 733, 484);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Guardar");
				okButton.setActionCommand("Guardar");
				okButton.addActionListener((ActionListener) form);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						closeForm();					
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void closeForm() {
		this.dispose();
	}


}
