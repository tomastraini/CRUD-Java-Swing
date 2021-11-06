import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

public class cargarClientes {
	public static JTable table;
	public static JTextField textField;
	public static JTextField textField_1;
	public static JTextField textField_2;
	public static JFrame frameCargacli ;
	public static JLabel lblNewLabel_2;
	static JTextField textField_3;
	
	public static void main(String[] args) {
		JFrame frameCargacli = new JFrame();
		
		
		
		frameCargacli.setTitle("Carga de clientes");

		
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frameCargacli.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		
		
		
		
		
		
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 11, 496, 459);
		desktopPane.add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("ID CLIENTE");
		lblNewLabel_2.setBounds(521, 257, 139, 14);
		desktopPane.add(lblNewLabel_2);
		
		btnNewButton.setBounds(598, 520, 89, 23);
		desktopPane.add(btnNewButton);
		
		
		JButton btnVolver = new JButton("Volver");
		
		//menuPrincipal.frameMenu
		
		
		
		
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameCargacli.setVisible(false);
				if(menuPrincipal.principal == null) {
					menuPrincipal.main(null);
				
				}else {
					menuPrincipal.frameMenu.setVisible(true);
				}
				
			}
		});
		
		btnVolver.setBounds(10, 520, 89, 23);
		desktopPane.add(btnVolver);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String conexion = connextion.conexion;
			Connection conn = DriverManager.getConnection(conexion);

			try (PreparedStatement clie = conn.prepareStatement("select razon_social, telefono, localidad from clientes join localidades on localidades.cp = clientes.cp")) {
				ResultSet clien = clie.executeQuery();
				DefaultTableModel model = new DefaultTableModel(new String[]{"Razón social del cliente", "Teléfono", "Localidad"}, 0);
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
				
				while(clien.next())
				{
				   
				    String n = clien.getString("razon_social");
				    String t = clien.getString("telefono");
				    String l = clien.getString("localidad");
				    model.addRow(new Object[]{n, t, l});
				
				}
				
			        
		    }
			
		}catch(Exception ex){
			 JOptionPane.showMessageDialog(null, ex);
		}
		
		
		
		
		
		
		
		
		try {
			 ImageIcon img = new ImageIcon("./images/icon.png");
			    Image Image = img.getImage();
			    frameCargacli.setIconImage(Image);
			 
		 } catch (Exception e) {
			    throw new RuntimeException(e);
			}
		
		
		  // JOptionPane.showMessageDialog(null, "dsasaddas"); tfBuscarcli
	 
		JTextField tfBuscarcli = new JTextField();
		desktopPane.add(tfBuscarcli);
		tfBuscarcli.setBounds(231, 521, 134, 20);
		tfBuscarcli.setColumns(10);
		
		tfBuscarcli.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
					try {
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						String conexion = connextion.conexion;
						Connection conn = DriverManager.getConnection(conexion);

						try (PreparedStatement clie = conn.prepareStatement("select razon_social, telefono, localidad from clientes join localidades on \r\n"
								+ "localidades.cp = clientes.cp where concat(razon_social, telefono, localidad) like '%"+ tfBuscarcli.getText() +"%'")) {
							ResultSet clien = clie.executeQuery();
							DefaultTableModel model = new DefaultTableModel(new String[]{"Razón social del cliente", "Teléfono", "Localidad"}, 0);
							table.setModel(model);
							frameCargacli.setBackground(Color.black);
							
							while(clien.next())
							{
							    String n = clien.getString("razon_social");
							    String t = clien.getString("telefono");
							    String l = clien.getString("localidad");
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

						try (PreparedStatement clie = conn.prepareStatement("select razon_social, telefono, localidad from clientes join localidades on \r\n"
								+ "localidades.cp = clientes.cp where concat(razon_social, telefono, localidad) like '%"+ tfBuscarcli.getText() +"%'")) {
							ResultSet clien = clie.executeQuery();
							DefaultTableModel model = new DefaultTableModel(new String[]{"Razón social del cliente", "Teléfono", "Localidad"}, 0);
							table.setModel(model);
							frameCargacli.setBackground(Color.black);
							
							while(clien.next())
							{
							    String n = clien.getString("razon_social");
							    String t = clien.getString("telefono");
							    String l = clien.getString("localidad");
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

						try (PreparedStatement clie = conn.prepareStatement("select razon_social, telefono, localidad from clientes join localidades on \r\n"
								+ "localidades.cp = clientes.cp where concat(razon_social, telefono, localidad) like '%"+ tfBuscarcli.getText() +"%'")) {
							ResultSet clien = clie.executeQuery();
						
							DefaultTableModel model = new DefaultTableModel(new String[]{"Razón social del cliente", "Teléfono", "Localidad"}, 0);
							table.setModel(model);
							frameCargacli.setBackground(Color.black);
							
							while(clien.next())
							{
							    String n = clien.getString("razon_social");
							    String t = clien.getString("telefono");
							    String l = clien.getString("localidad");
							    model.addRow(new Object[]{n, t, l});
							
							}
							
						        
					    }
						
					}catch(Exception ex){
						 JOptionPane.showMessageDialog(null, ex);
					}
			  }
			  });
		
		
		
		
		
		frameCargacli.setSize(724, 596);
		desktopPane.setBackground(new Color(205, 92, 92));
		

		
		
		JLabel lblNewLabel = new JLabel("Buscar clientes");
		lblNewLabel.setLabelFor(tfBuscarcli);
		lblNewLabel.setBounds(246, 501, 101, 14);
		desktopPane.add(lblNewLabel);
		
		
		
		
		
		
		
		
		
		
		
		
		
		JButton cargarnuevo = new JButton("Cargar");
		cargarnuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					String conexion = connextion.conexion;
					Connection conn = DriverManager.getConnection(conexion);

					try {
					//	textField.getText().toString()   textField_1.getText().toString()
						String cliens = textField.getText().toString();
						String telefono = textField_1.getText().toString();
						String localidad = textField_2.getText();
					    int localidad1 = Integer.parseInt(localidad);
						PreparedStatement clie = conn.prepareStatement("insert into clientes values('"+cliens+"', '"+telefono+"','"+ localidad1 +"')");
						
						
						
						clie.executeUpdate();
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						lblNewLabel_2.setText("");
						textField_3.setText("");
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

					try (PreparedStatement clie = conn.prepareStatement("select razon_social, telefono, localidad from clientes join localidades on localidades.cp = clientes.cp")) {
						ResultSet clien = clie.executeQuery();
						DefaultTableModel model = new DefaultTableModel(new String[]{"Razón social del cliente", "Teléfono", "Localidad"}, 0);
						
						
						
						table.setModel(model);
						
						
						
						while(clien.next())
						{
						   
						    String n = clien.getString("razon_social");
						    String t = clien.getString("telefono");
						    String l = clien.getString("localidad");
						    model.addRow(new Object[]{n, t, l});
						
						}
						
					        
				    }
					
				}catch(Exception ex){
					 JOptionPane.showMessageDialog(null, ex);
				}
				
		}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		cargarnuevo.setBounds(406, 470, 89, 38);
		desktopPane.add(cargarnuevo);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					String conexion = connextion.conexion;
					Connection conn = DriverManager.getConnection(conexion);

					try {
					//	textField.getText().toString()   textField_1.getText().toString()
						String cliens = textField.getText().toString();
						String telefono = textField_1.getText().toString();
						String localidad = textField_2.getText();
					    int localidad1 = Integer.parseInt(localidad);
					    String id_clis = lblNewLabel_2.getText();
					    int id_clisnum = Integer.parseInt(id_clis);
						PreparedStatement clie = conn.prepareStatement("update clientes set razon_social='"+cliens+"', telefono = '"+telefono+"', cp = '"+localidad1+"' where id_cliente = '"+id_clisnum+"'");
						
						clie.execute();
						
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						lblNewLabel_2.setText("");
						textField_3.setText("");
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

					try (PreparedStatement clie = conn.prepareStatement("select razon_social, telefono, localidad from clientes join localidades on localidades.cp = clientes.cp")) {
						ResultSet clien = clie.executeQuery();
						DefaultTableModel model = new DefaultTableModel(new String[]{"Razón social del cliente", "Teléfono", "Localidad"}, 0);
						
						
						
						table.setModel(model);
						
						
						
						while(clien.next())
						{
						   
						    String n = clien.getString("razon_social");
						    String t = clien.getString("telefono");
						    String l = clien.getString("localidad");
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
		    	
		    	//select razon_social_cli, telefono, localidad from clientes join localidades on localidades.id_localidad = clientes.id_localidad
		    	try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					String conexion = connextion.conexion;
					Connection conn = DriverManager.getConnection(conexion);

					try (PreparedStatement clie = conn.prepareStatement("select id_cliente, razon_social, telefono, localidades.cp, localidad from clientes join localidades on localidades.cp = clientes.cp")) {
						ResultSet clien = clie.executeQuery();
						DefaultTableModel modelnew = new DefaultTableModel(new String[]{"id_cliente","Razón social del cliente", "Teléfono", "cp", "localidad"}, 0);
						
						JTable tableforclick = new JTable();
						tableforclick.setModel(modelnew);
						
						
						
						while(clien.next())
						{
						    String z = clien.getString("id_cliente");
						    String n = clien.getString("razon_social");
						    String t = clien.getString("telefono");
						    String l = clien.getString("cp");
						    String l1 = clien.getString("localidad");
						    modelnew.addRow(new Object[]{z,n, t, l, l1});
						    
						    int row = tableforclick.rowAtPoint(evt.getPoint());

					        if (row >= 0) {
					        	lblNewLabel_2.setText(tableforclick.getModel().getValueAt(row, 0)+"");
					            textField.setText(tableforclick.getModel().getValueAt(row, 1)+"");
					            textField_1.setText(tableforclick.getModel().getValueAt(row, 2)+"");
					            textField_2.setText(tableforclick.getModel().getValueAt(row, 3)+"");
					            textField_3.setText(tableforclick.getModel().getValueAt(row, 4)+"");
					        }
						}
						
					        
				    }
					
				}catch(Exception ex){
					 JOptionPane.showMessageDialog(null, ex);
				}
		    	
		    	
		    	
		    	
		    	
		    	
		        
		    }
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		btnModificar.setBounds(504, 470, 89, 38);
		desktopPane.add(btnModificar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					String conexion = connextion.conexion;
					Connection conn = DriverManager.getConnection(conexion);

					try {
					
					    String id_clis = lblNewLabel_2.getText();
					    int id_clisnum = Integer.parseInt(id_clis);
						PreparedStatement clie = conn.prepareStatement("delete from clientes where id_cliente = '"+id_clisnum+"'");
						
						clie.execute();
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						lblNewLabel_2.setText("");
						textField_3.setText("");
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

					try (PreparedStatement clie = conn.prepareStatement("select razon_social, telefono, localidad from clientes join localidades on localidades.cp = clientes.cp")) {
						ResultSet clien = clie.executeQuery();
						DefaultTableModel model = new DefaultTableModel(new String[]{"Razón social del cliente", "Teléfono", "Localidad"}, 0);
						
						
						
						table.setModel(model);
						
						
						
						while(clien.next())
						{
						   
						    String n = clien.getString("razon_social");
						    String t = clien.getString("telefono");
						    String l = clien.getString("localidad");
						    model.addRow(new Object[]{n, t, l});
						
						}
						
					        
				    }
					
				}catch(Exception ex){
					 JOptionPane.showMessageDialog(null, ex);
				}
				
			}
			
		});
		btnBorrar.setBounds(598, 470, 89, 38);
		desktopPane.add(btnBorrar);
		
		
		panel.setOpaque(false);
		
		JLabel lblNewLabel_1 = new JLabel("Raz\u00F3n social del cliente");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(516, 11, 192, 14);
		desktopPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(516, 35, 134, 20);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tel\u00E9fono");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_1.setBackground(Color.WHITE);
		lblNewLabel_1_1.setBounds(516, 86, 144, 14);
		desktopPane.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(516, 110, 134, 20);
		desktopPane.add(textField_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Localidad");
		lblNewLabel_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_2.setBackground(Color.WHITE);
		lblNewLabel_1_2.setBounds(516, 155, 78, 14);
		desktopPane.add(lblNewLabel_1_2);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setColumns(10);
		textField_2.setBounds(598, 251, 101, 20);
		desktopPane.add(textField_2);
		
		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionlocalidadcliente.main(args);
			}
		});
		btnNewButton_1.setBounds(612, 175, 89, 30);
		desktopPane.add(btnNewButton_1);
		
		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setBounds(516, 180, 86, 20);
		desktopPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("+");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				lblNewLabel_2.setText("");
				textField_3.setText("");
			}
		});
		btnNewButton_2.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_2.setBounds(660, 29, 41, 30);
		desktopPane.add(btnNewButton_2);
		
		//./images/carrito-super.jpg
		
		
		
	
		lblNewLabel_2.setVisible(false);
		textField_2.setVisible(false);
		
		frameCargacli.setVisible(true);
			
	}
}