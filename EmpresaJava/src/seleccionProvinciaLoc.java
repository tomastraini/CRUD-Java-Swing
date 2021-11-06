import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class seleccionProvinciaLoc{

	private JFrame frame;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					seleccionProvinciaLoc window = new seleccionProvinciaLoc();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public seleccionProvinciaLoc() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 622, 538);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Selección de provincias");
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(205, 92, 92));
		desktopPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		Button button = new Button("Volver");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 11, 574, 439);
		desktopPane.add(panel);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				
			}
		});
		
		DefaultTableModel model = new DefaultTableModel(new String[]{"ID provincia", "Provincia"}, 0);
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		panel.add(table.getTableHeader(), BorderLayout.NORTH);
		
		table.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		table.setModel(model);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);

		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String conexion = connextion.conexion;
			Connection conn = DriverManager.getConnection(conexion);

			try (PreparedStatement clie = conn.prepareStatement("select * from provincias")) {
				ResultSet clien = clie.executeQuery();
				
				
				while(clien.next())
				{
				   
				    String n = clien.getString("id_provincia");
				    String t = clien.getString("provincia");

				    model.addRow(new Object[]{n, t});
				
				}
				
			        
		    }
			
		}catch(Exception ex){
			 JOptionPane.showMessageDialog(null, ex);
		}
		
		
		
		button.setBounds(516, 459, 80, 30);
		desktopPane.add(button);
		
		JLabel lblNewLabel = new JLabel("Buscar provincia");
		lblNewLabel.setBounds(251, 447, 163, 14);
		desktopPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(240, 461, 127, 20);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		Button button_1 = new Button("Salir");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_1.setBounds(20, 459, 80, 30);
		desktopPane.add(button_1);
		
		
		//"select * from localidades where concat(cp, localidad, id_provincia) " + " like '%" +textField.getText()+ "%'"
		//    String n = clien.getString("cp");
		// String t = clien.getString("localidad");
		// String l = clien.getString("id_provincia");
		
		textField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
					try {
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						String conexion = connextion.conexion;
						Connection conn = DriverManager.getConnection(conexion);

						try (PreparedStatement clie = conn.prepareStatement("select * from provincias where concat(id_provincia, provincia) " + " like '%" +textField.getText()+ "%'")) {
							ResultSet clien = clie.executeQuery();
							DefaultTableModel model = new DefaultTableModel(new String[]{"ID provincia", "provincia"}, 0);
							
							table.setModel(model);
							
							while(clien.next())
							{
							   
								String n = clien.getString("id_provincia");
								String t = clien.getString("provincia");
							    model.addRow(new Object[]{n, t});
							
							}
							
						        
					    }
						
					}catch(Exception ex){
						 JOptionPane.showMessageDialog(null, ex);
					}
			  }
			  public void removeUpdate(DocumentEvent e) {
				  try {
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						String conexion = connextion.conexion;
						Connection conn = DriverManager.getConnection(conexion);

						try (PreparedStatement clie = conn.prepareStatement("select * from provincias")) {
							ResultSet clien = clie.executeQuery();
							DefaultTableModel model = new DefaultTableModel(new String[]{"ID provincia", "Provincia"}, 0);
							
							
							
							table.setModel(model);
						
							
							while(clien.next())
							{
							   
							    String n = clien.getString("id_provincia");
							    String t = clien.getString("provincia");
							    model.addRow(new Object[]{n, t});
							
							}
							
						        
					    }
						
					}catch(Exception ex){
						 JOptionPane.showMessageDialog(null, ex);
					}
					
			  }
			  public void insertUpdate(DocumentEvent e) {
					try {
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						String conexion = connextion.conexion;
						Connection conn = DriverManager.getConnection(conexion);

						try (PreparedStatement clie = conn.prepareStatement("select * from provincias where concat(id_provincia, provincia) " + " like '%" +textField.getText()+ "%'")) {
							ResultSet clien = clie.executeQuery();
							DefaultTableModel model = new DefaultTableModel(new String[]{"ID provincia", "Provincia"}, 0);
							
							table.setModel(model);
							
							while(clien.next())
							{
							   
								String n = clien.getString("id_provincia");
								 String t = clien.getString("provincia");
							    model.addRow(new Object[]{n, t});
							
							}
							
						        
					    }
						
					}catch(Exception ex){
						 JOptionPane.showMessageDialog(null, ex);
					}
					
			  }
			  });
		
		
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = table.rowAtPoint(evt.getPoint());
		        
		        if (row >= 0) {
		            
		            try {
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						String conexion = connextion.conexion;
						Connection conn = DriverManager.getConnection(conexion);

						try (PreparedStatement clie = conn.prepareStatement("select * from provincias")) {
							ResultSet clien = clie.executeQuery();
							DefaultTableModel modelnew = new DefaultTableModel(new String[]{"id_provincia", "provincia"}, 0);
							
							JTable tableforclick = new JTable();
							tableforclick.setModel(modelnew);
							
							
							
							while(clien.next())
							{
							    String z = clien.getString("id_provincia");
							    String n = clien.getString("provincia");

							    modelnew.addRow(new Object[]{z,n});
							    
							    int rows = tableforclick.rowAtPoint(evt.getPoint());

						        if (rows >= 0) {
						        	
						        	cargarLocalidad.txfProvincia.setText(tableforclick.getModel().getValueAt(row, 1)+"");
						        	cargarLocalidad.txfIdprov.setText(tableforclick.getModel().getValueAt(rows, 0)+"");
						        	frame.setVisible(false);
						        	
						        }
							}
							
						        
					    }
						
					}catch(Exception ex){
						 JOptionPane.showMessageDialog(null, ex);
					}
		            
		            
		        }
		    }
		});
		panel.setOpaque(false);
		
		
		
	}
}
