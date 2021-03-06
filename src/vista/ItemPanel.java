package vista;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

abstract class ItemPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JTextField search;
	protected JTable table;
	protected JPanel topPanel;
	protected int currentPage;
	protected int itemsCount;
    protected SpringLayout layout;
    protected JButton actionButton1;
    protected JButton actionButton2;
    protected JButton actionButton3;
    protected JButton actionButton4;
    protected JButton actionButton5;
    protected JButton actionButton6;
    private JLabel lblNewLabel_1;
    protected JLabel lblSearch;
    protected JScrollPane scrollPane;
    protected DefaultTableModel searchModel;
    protected JButton back;
    protected JButton next;
    private JLabel lblPagination;
    
    abstract protected String[] getColumnsForList();

    public ItemPanel() {
        currentPage = 0;
        // TODO : Poner el item count posta;
        itemsCount = 100;

		setLayout(null);
		
		topPanel = new JPanel();
		topPanel.setBounds(12, 38, 822, 64);
		add(topPanel);
        layout  = new SpringLayout();
        topPanel.setLayout(layout);

        Object[][] tableData2 = {};
        searchModel = new DefaultTableModel(tableData2, getColumnsForList()) {
 			private static final long serialVersionUID = 8095720010196161749L;

			@Override
            public boolean isCellEditable(int row, int column) {
               //Ninguna celda editable
               return false;
            }
        };

		JPanel PaginationPanel = new JPanel();
		PaginationPanel.setBounds(12, 620, 822, 45);
		add(PaginationPanel);
		
		back = new JButton("<");
		PaginationPanel.add(back);
		
		lblPagination = new JLabel(String.valueOf(1 + currentPage*30) +" - "+ String.valueOf(currentPage *30 +30) + " de " + itemsCount);
		PaginationPanel.add(lblPagination);
		
		next = new JButton(">");
		PaginationPanel.add(next);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 113, 822, 505);
		add(scrollPane);
		
		table = new JTable(searchModel);
		table.setCellSelectionEnabled(false);
		scrollPane.setViewportView(table);
		
		lblNewLabel_1 = new JLabel(getSectionText());
		lblNewLabel_1.setBounds(12, 12, 192, 15);
		add(lblNewLabel_1);
		
		buildTopElements();
	}


	protected void buildTopElements() {
		habilitarBotones();
		configureActions();
	}

	protected void configureActions(){

    }

    protected String getSectionText() {
        return "Section:";
    }

	protected String getTootltipForSearchBar() {
		return "Search by : ";
	}
	
	protected void setPagesInfo(){
		lblPagination.setText(String.valueOf(1 + currentPage*30) +" - "+ String.valueOf(currentPage *30 +30) + " de " + itemsCount);
	}
	
	private void habilitarBotones(){
        actionButton1 = new JButton("Action1");
		layout.putConstraint(SpringLayout.WEST, actionButton1, 10, SpringLayout.WEST, topPanel);
		topPanel.add(actionButton1);

        actionButton2 = new JButton("Action2");
        layout.putConstraint(SpringLayout.WEST, actionButton2, 10, SpringLayout.EAST, actionButton1);
        topPanel.add(actionButton2);

        actionButton3 = new JButton("Action3");
        layout.putConstraint(SpringLayout.WEST, actionButton3, 10, SpringLayout.EAST, actionButton2);
        topPanel.add(actionButton3);

        actionButton4 = new JButton("Action4");
		layout.putConstraint(SpringLayout.WEST, actionButton4, 10, SpringLayout.EAST, actionButton3);
		topPanel.add(actionButton4);
		
		search = new JTextField();
		search.setColumns(15);
		layout.putConstraint(SpringLayout.EAST, search, 300, SpringLayout.EAST, actionButton4);
		search.setToolTipText("");
		search.setHorizontalAlignment(SwingConstants.LEFT);
		topPanel.add(search);
		
		lblSearch = new JLabel();
		layout.putConstraint(SpringLayout.EAST, lblSearch, 0, SpringLayout.WEST, search);
		topPanel.add(lblSearch);
		
		actionButton5 = new JButton("Action5");
		actionButton5.setText("Buscar");
		layout.putConstraint(SpringLayout.WEST, actionButton5, 0, SpringLayout.EAST, search);
		topPanel.add(actionButton5);
		
		actionButton6 = new JButton("Action6");
		layout.putConstraint(SpringLayout.NORTH, actionButton6, 8, SpringLayout.SOUTH, actionButton2);
		layout.putConstraint(SpringLayout.WEST, actionButton6, 0, SpringLayout.WEST, actionButton2);
		layout.putConstraint(SpringLayout.SOUTH, actionButton6, 31, SpringLayout.SOUTH, actionButton1);
		layout.putConstraint(SpringLayout.EAST, actionButton6, 0, SpringLayout.EAST, actionButton3);
		topPanel.add(actionButton6);
	}
}
