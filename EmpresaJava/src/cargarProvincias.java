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

public class cargarProvincias{

	private JFrame frmCargaDeProvincias;
	private JTable table;
	private JTextField textField;
	private JTextField txfIDProv;
	private JTextField txfProv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cargarProvincias window = new cargarProvincias();
					window.frmCargaDeProvincias.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public cargarProvincias() {
		initialize();
	}


	private void initialize() {
		frmCargaDeProvincias = new JFrame();
		frmCargaDeProvincias.setBounds(100, 100, 773, 538);
		frmCargaDeProvincias.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCargaDeProvincias.setTitle("Carga de provincias");
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(205, 92, 92));
		desktopPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frmCargaDeProvincias.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		Button button = new Button("Volver");
		
		
		
		
		
		
		
		Button btnCargar = new Button("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					String conexion = connextion.conexion;
					Connection conn = DriverManager.getConnection(conexion);

					try {
					
						String id_provincia = txfIDProv.getText();
						String provincia = txfProv.getText().toString();
						
					    int id_provincia1 = Integer.parseInt(id_provincia);
					    int  provincia1= Integer.parseInt(provincia);
						PreparedStatement clie = conn.prepareStatement("insert into localidades values("+ id_provincia1 +",'"+ provincia1 +"')");
						
						
						
						  			clie.executeUpdate();
						  			txfIDProv.setText("");
						  			txfProv.setText("");
						
						  			txfIDProv.requestFocus();
						 
						
				}catch(Exception ex){
					 JOptionPane.showMessageDialog(null, ex);
				}
			
				}catch(Exception ex){
					 JOptionPane.showMessageDialog(null, ex);
				}
				
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					String conexion = connextion.conexion;
					Connection conn = DriverManager.getConnection(conexion);

					try (PreparedStatement clie = conn.prepareStatement("select * from provincias")) {
						ResultSet clien = clie.executeQuery();
						DefaultTableModel model = new DefaultTableModel(new String[]{"id_provincia", "provincia"}, 0);
						
						
						
						table.setModel(model);
						txfIDProv.requestFocus();
						
						
						while(clien.next())
						{
						   
						    String n = clien.getString("id_provincia");
						    String t = clien.getString("provincia");
						    model.addRow(new Object[]{n, t});
						     	    txfIDProv.requestFocus();
						}
						  	txfIDProv.requestFocus();
					        
				    }
					
				}catch(Exception ex){
					 JOptionPane.showMessageDialog(null, ex);
				}
				
				
				
			}
		});
		
		
		
		
		
		
		
		
		
		btnCargar.setBounds(550, 214, 58, 30);
		desktopPane.add(btnCargar);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 11, 574, 439);
		desktopPane.add(panel);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCargaDeProvincias.setVisible(false);
				
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
		
		
		
		button.setBounds(656, 451, 80, 30);
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
						        	
						        	txfIDProv.setText(tableforclick.getModel().getValueAt(row, 0)+"");
						        	txfProv.setText(tableforclick.getModel().getValueAt(rows, 1)+"");
						        	
						        	
						        }
							}
							
						        
					    }
						
					}catch(Exception ex){
						 JOptionPane.showMessageDialog(null, ex);
					}
		            
		            
		        }
		    }
		});
		
		JLabel lblNewLabel_1 = new JLabel("ID de provincia");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(550, 21, 132, 14);
		desktopPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre de provincia");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(550, 78, 132, 14);
		desktopPane.add(lblNewLabel_1_1);
		
		txfIDProv = new JTextField();
		txfIDProv.setBounds(550, 46, 86, 20);
		desktopPane.add(txfIDProv);
		txfIDProv.setColumns(10);
		
		txfProv = new JTextField();
		txfProv.setColumns(10);
		txfProv.setBounds(550, 103, 86, 20);
		desktopPane.add(txfProv);
		
		
		
		
		
		
		
		Button btnModif = new Button("Modificar");
		btnModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					String conexion = connextion.conexion;
					Connection conn = DriverManager.getConnection(conexion);

					try {
					
						String id_provincia = txfIDProv.getText();
						String provincia = txfProv.getText().toString();
						
					    int id_provincia1 = Integer.parseInt(id_provincia);

						PreparedStatement clie = conn.prepareStatement("update provincias set provincia ="+ id_provincia1 +" where id_provincia ="+ provincia +")");
						
						
						
						  			clie.executeUpdate();
						  			txfIDProv.setText("");
						  			txfProv.setText("");
						
						  			txfIDProv.requestFocus();
						 
						
				}catch(Exception ex){
					 JOptionPane.showMessageDialog(null, ex);
				}
			
				}catch(Exception ex){
					 JOptionPane.showMessageDialog(null, ex);
				}
				
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					String conexion = connextion.conexion;
					Connection conn = DriverManager.getConnection(conexion);

					try (PreparedStatement clie = conn.prepareStatement("select * from provincias")) {
						ResultSet clien = clie.executeQuery();
						DefaultTableModel model = new DefaultTableModel(new String[]{"ID provincia", "Provincia"}, 0);
						
						
						
						table.setModel(model);
						txfIDProv.requestFocus();
						
						
						while(clien.next())
						{
						   
						    String n = clien.getString("id_provincia");
						    String l = clien.getString("provincia");
						    model.addRow(new Object[]{n, l});
						     	    txfIDProv.requestFocus();
						}
						  	txfIDProv.requestFocus();
					        
				    }
					
				}catch(Exception ex){
					 JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		
		
		
		
		
		
		btnModif.setBounds(614, 214, 58, 30);
		desktopPane.add(btnModif);
		
		Button btnBorrar = new Button("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					String conexion = connextion.conexion;
					Connection conn = DriverManager.getConnection(conexion);

					try {
					
						String id_provincia = txfIDProv.getText();						
					    int id_provincia1 = Integer.parseInt(id_provincia);
						PreparedStatement clie = conn.prepareStatement("delete from provincias where id_provincia ="+ id_provincia1 +")");
						
						
						
						  			clie.executeUpdate();
						  			txfIDProv.setText("");
						  			txfProv.setText("");
						
						  			txfIDProv.requestFocus();
						 
						
				}catch(Exception ex){
					 JOptionPane.showMessageDialog(null, ex);
				}
			
				}catch(Exception ex){
					 JOptionPane.showMessageDialog(null, ex);
				}
				
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					String conexion = connextion.conexion;
					Connection conn = DriverManager.getConnection(conexion);

					try (PreparedStatement clie = conn.prepareStatement("select * from localidades provincias")) {
						ResultSet clien = clie.executeQuery();
						DefaultTableModel model = new DefaultTableModel(new String[]{"id_provincia", "provincia"}, 0);
						
						
						
						table.setModel(model);
						txfIDProv.requestFocus();
						
						
						while(clien.next())
						{
						   
						    String n = clien.getString("id_provincia");
						    String t = clien.getString("provincia");
						    model.addRow(new Object[]{n, t});
						     	    txfIDProv.requestFocus();
						}
						  	txfIDProv.requestFocus();
					        
				    }
					
				}catch(Exception ex){
					 JOptionPane.showMessageDialog(null, ex);
				}
				
			}
		});
		btnBorrar.setBounds(678, 214, 58, 30);
		desktopPane.add(btnBorrar);
		
		panel.setOpaque(false);
		
	}
}
