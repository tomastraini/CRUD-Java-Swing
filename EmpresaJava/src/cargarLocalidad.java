import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JButton;

public class cargarLocalidad {

	private JFrame frameLoc;
	private JTable table;
	private JTextField textField;
	public static JTextField txfCP;
	public static JTextField txfProvincia;
	public static JTextField txfLoc;
	public static JTextField txfIdprov;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cargarLocalidad window = new cargarLocalidad();
					
					window.frameLoc.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public cargarLocalidad() {
		initialize();
	}


	private void initialize() {
		frameLoc = new JFrame();
		frameLoc.setBounds(100, 100, 816, 538);
		frameLoc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(205, 92, 92));
		desktopPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frameLoc.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		
		
		Button button = new Button("Volver");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 11, 574, 439);
		desktopPane.add(panel);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameLoc.setVisible(false);
				menuPrincipal.main(null);
				
			}
		});
		
		DefaultTableModel model = new DefaultTableModel(new String[]{"Código postal", "Localidad", "Provincia"}, 0);
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

			try (PreparedStatement clie = conn.prepareStatement("select cp, localidad, provincia from localidades join provincias on provincias.id_provincia = localidades.id_provincia")) {
				ResultSet clien = clie.executeQuery();
				
				
				while(clien.next())
				{
				   
				    String n = clien.getString("cp");
				    String t = clien.getString("localidad");
				    String l = clien.getString("provincia");
				    model.addRow(new Object[]{n, t, l});
				
				}
				
			        
		    }
			
		}catch(Exception ex){
			 JOptionPane.showMessageDialog(null, ex);
		}
		
		
		
		button.setBounds(710, 451, 80, 30);
		desktopPane.add(button);
		
		JLabel lblNewLabel = new JLabel("Buscar localidad");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 11));
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
		
		
		//"select cp, localidad, provincia from localidades join provincias on provincias.provincia = localidades.provincia where concat(cp, localidad, provincia) " + " like '%" +textField.getText()+ "%'"
		//    String n = clien.getString("cp");
		// String t = clien.getString("localidad");
		// String l = clien.getString("provincia");
		
		textField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
					try {
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						String conexion = connextion.conexion;
						Connection conn = DriverManager.getConnection(conexion);

						try (PreparedStatement clie = conn.prepareStatement("select cp, localidad, provincia from localidades join provincias on provincias.id_provincia = localidades.id_provincia where concat(cp, localidad, provincia) like '%" +textField.getText()+ "%'")) {
							ResultSet clien = clie.executeQuery();
							DefaultTableModel model = new DefaultTableModel(new String[]{"cp", "localidad", "provincia"}, 0);
							
							table.setModel(model);
							
							while(clien.next())
							{
							   
								String n = clien.getString("cp");
								String t = clien.getString("localidad");
								String l = clien.getString("provincia");
							    model.addRow(new Object[]{n, t, l});
							
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

						try (PreparedStatement clie = conn.prepareStatement("select cp, localidad, provincia from localidades join provincias on provincias.id_provincia = localidades.id_provincia")) {
							ResultSet clien = clie.executeQuery();
							DefaultTableModel model = new DefaultTableModel(new String[]{"cp", "localidad", "provincia"}, 0);
							
							
							
							table.setModel(model);
						
							
							while(clien.next())
							{
							   
							    String n = clien.getString("cp");
							    String t = clien.getString("localidad");
							    String l = clien.getString("provincia");
							    model.addRow(new Object[]{n, t, l});
							
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

						try (PreparedStatement clie = conn.prepareStatement("select cp, localidad, provincia from localidades join provincias on provincias.id_provincia = localidades.id_provincia where concat(cp, localidad, provincia) like '%" +textField.getText()+ "%'")) {
							ResultSet clien = clie.executeQuery();
							DefaultTableModel model = new DefaultTableModel(new String[]{"cp", "localidad", "provincia"}, 0);
							
							table.setModel(model);
							
							while(clien.next())
							{
							   
								String n = clien.getString("cp");
								 String t = clien.getString("localidad");
								 String l = clien.getString("provincia");
							    model.addRow(new Object[]{n, t, l});
							
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

						try (PreparedStatement clie = conn.prepareStatement("select cp, localidad, provincia, localidades.id_provincia from localidades join provincias on provincias.id_provincia = localidades.id_provincia")) {
							ResultSet clien = clie.executeQuery();
							DefaultTableModel modelnew = new DefaultTableModel(new String[]{"cp", "localidad", "provincia", "id_provincia"}, 0);
							
							JTable tableforclick = new JTable();
							tableforclick.setModel(modelnew);
							
							
							
							while(clien.next())
							{
							    String z = clien.getString("cp");
							    String n = clien.getString("localidad");
							    String t = clien.getString("provincia");
							    String y = clien.getString("id_provincia");
							    modelnew.addRow(new Object[]{z,n, t, y});
							    
							    int rows = tableforclick.rowAtPoint(evt.getPoint());

						        if (rows >= 0) {
						        	txfCP.setText(tableforclick.getModel().getValueAt(rows, 0)+"");
						        	txfLoc.setText(tableforclick.getModel().getValueAt(row, 1)+"");
						        	txfProvincia.setText(tableforclick.getModel().getValueAt(row, 2)+"");
						        	txfIdprov.setText(tableforclick.getModel().getValueAt(row, 3)+"");
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
		
		JLabel lblCP = new JLabel("C\u00F3digo postal (Num\u00E9rico)");
		lblCP.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCP.setBounds(562, 11, 228, 14);
		desktopPane.add(lblCP);
		
		txfCP = new JTextField();
		txfCP.setBounds(562, 36, 86, 20);
		desktopPane.add(txfCP);
		txfCP.setColumns(10);
		
		JLabel lblProvincia = new JLabel("Buscar provincia");
		lblProvincia.setFont(new Font("Arial", Font.PLAIN, 12));
		lblProvincia.setBounds(562, 126, 139, 14);
		desktopPane.add(lblProvincia);
		
		txfProvincia = new JTextField();
		txfProvincia.setEditable(false);
		txfProvincia.setBounds(562, 151, 86, 20);
		desktopPane.add(txfProvincia);
		txfProvincia.setColumns(10);
		
		txfIdprov = new JTextField();
		txfIdprov.setBounds(703, 92, 87, 20);
		desktopPane.add(txfIdprov);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionProvinciaLoc.main(null);
			}
			
		});
		btnNewButton.setBounds(653, 150, 86, 23);
		desktopPane.add(btnNewButton);
		
		JLabel lblLoc = new JLabel("Nombre de localidad");
		lblLoc.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLoc.setBounds(562, 67, 139, 14);
		desktopPane.add(lblLoc);
		
		txfLoc = new JTextField();
		txfLoc.setBounds(562, 92, 86, 20);
		desktopPane.add(txfLoc);
		txfLoc.setColumns(10);
		
		
		
		
		
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					String conexion = connextion.conexion;
					Connection conn = DriverManager.getConnection(conexion);

					try {
					
						String cp = txfCP.getText();
						String localidad = txfLoc.getText().toString();
						String provincia = txfIdprov.getText();
					    int provincia1 = Integer.parseInt(provincia);
					    int cp1 = Integer.parseInt(cp);
						PreparedStatement clie = conn.prepareStatement("insert into localidades values("+ cp1 +","+ provincia1 +" ,'"+localidad+"')");
						
						
						
						clie.executeUpdate();
						txfCP.setText("");
						txfLoc.setText("");
						txfProvincia.setText("");
						txfIdprov.setText("");
						
						txfCP.requestFocus();
						 
						
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

					try (PreparedStatement clie = conn.prepareStatement("select cp, localidad, provincia from localidades join provincias on provincias.id_provincia = localidades.id_provincia")) {
						ResultSet clien = clie.executeQuery();
						DefaultTableModel model = new DefaultTableModel(new String[]{"cp", "localidad", "provincia"}, 0);
						
						
						
						table.setModel(model);
						txfCP.requestFocus();
						
						
						while(clien.next())
						{
						   
						    String n = clien.getString("cp");
						    String t = clien.getString("localidad");
						    String l = clien.getString("provincia");
						    model.addRow(new Object[]{n, t, l});
						    txfCP.requestFocus();
						}
						txfCP.requestFocus();
					        
				    }
					
				}catch(Exception ex){
					 JOptionPane.showMessageDialog(null, ex);
				}
				
			}
		});
		
		
		
		
		
		
		
		btnCargar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnCargar.setBounds(537, 261, 73, 23);
		desktopPane.add(btnCargar);
		
		
		
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					String conexion = connextion.conexion;
					Connection conn = DriverManager.getConnection(conexion);

					try {
					
						String cp = txfCP.getText();
						String localidad = txfLoc.getText().toString();
						String provincia = txfIdprov.getText();
					    int provincia1 = Integer.parseInt(provincia);
					    int cp1 = Integer.parseInt(cp);
						PreparedStatement clie = conn.prepareStatement("update localidades set cp = '"+ cp1 +"', id_provincia = '"+ provincia1 +"', localidad = '"+ localidad +"' where cp = '"+ cp1 +"' and id_provincia = '"+ provincia1 +"'");
						//update localidades set cp = '"+ cp1 +"', id_provincia = '"+ provincia1 +"', localidad = '"+ localidad +"' where cp = '"+ cp1 +"' and id_provincia = '"+ provincia1 +"'
						
						
						clie.executeUpdate();
						txfCP.setText("");
						txfLoc.setText("");
						txfProvincia.setText("");
						txfIdprov.setText("");
						txfCP.requestFocus();
						 
						
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

					try (PreparedStatement clie = conn.prepareStatement("select cp, localidad, provincia from localidades join provincias on provincias.id_provincia = localidades.id_provincia")) {
						ResultSet clien = clie.executeQuery();
						DefaultTableModel model = new DefaultTableModel(new String[]{"cp", "localidad", "provincia"}, 0);
						
						
						
						table.setModel(model);
						txfCP.requestFocus();
						
						
						while(clien.next())
						{
						   
						    String n = clien.getString("cp");
						    String t = clien.getString("localidad");
						    String l = clien.getString("provincia");
						    model.addRow(new Object[]{n, t, l});
						    txfCP.requestFocus();
						}
						txfCP.requestFocus();
					        
				    }
					
				}catch(Exception ex){
					 JOptionPane.showMessageDialog(null, ex);
				}
				
			}
		});
		
		
		
		
		btnModificar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnModificar.setBounds(626, 261, 81, 23);
		desktopPane.add(btnModificar);
		
		
		
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					String conexion = connextion.conexion;
					Connection conn = DriverManager.getConnection(conexion);

					try {
					
						String cp = txfCP.getText();
						int cp1 = Integer.parseInt(cp);
						PreparedStatement clie = conn.prepareStatement("delete from localidades where cp = '"+cp1+"'");
						//update localidades set cp = '"+ cp1 +"', id_provincia = '"+ provincia1 +"', localidad = '"+ localidad +"' where cp = '"+ cp1 +"' and id_provincia = '"+ provincia1 +"'
						
						
						clie.executeUpdate();
						txfCP.setText("");
						txfLoc.setText("");
						txfProvincia.setText("");
						txfIdprov.setText("");
						txfCP.requestFocus();
						 
						
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

					try (PreparedStatement clie = conn.prepareStatement("select cp, localidad, provincia from localidades join provincias on provincias.id_provincia = localidades.id_provincia")) {
						ResultSet clien = clie.executeQuery();
						DefaultTableModel model = new DefaultTableModel(new String[]{"cp", "localidad", "provincia"}, 0);
						
						
						
						table.setModel(model);
						txfCP.requestFocus();
						
						
						while(clien.next())
						{
						   
						    String n = clien.getString("cp");
						    String t = clien.getString("localidad");
						    String l = clien.getString("provincia");
						    model.addRow(new Object[]{n, t, l});
						    txfCP.requestFocus();
						}
						txfCP.requestFocus();
					        
				    }
					
				}catch(Exception ex){
					 JOptionPane.showMessageDialog(null, ex);
				}				
			}
		});
		
		
		
		
		
		
		
		btnBorrar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnBorrar.setBounds(717, 261, 73, 23);
		desktopPane.add(btnBorrar);
		
		JButton btnnew = new JButton("+");
		btnnew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txfCP.setText("");
				txfLoc.setText("");
				txfProvincia.setText("");
			}
		});
		btnnew.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnnew.setBounds(728, 34, 62, 23);
		desktopPane.add(btnnew);
		
		
		
		
		
		frameLoc.addWindowListener( new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		        txfCP.requestFocus();
		    }
		}); 
		
		txfIdprov.setVisible(false);
	}
}
