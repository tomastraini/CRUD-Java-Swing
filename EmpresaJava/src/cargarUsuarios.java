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

public class cargarUsuarios{
	public static JTable tableus;
	public static JTextField textFieldus;
	public static JTextField textField_1us;

	public static JFrame frameCargaclius ;
	public static JLabel lblNewLabel_2;
	static JTextField textField_3us;
	public static void main(String[] args) {
	JFrame frameCargaprov = new JFrame();
	
	
	
	frameCargaprov.setTitle("Carga de usuarios");

	lblNewLabel_2 = new JLabel();
	
	JDesktopPane desktoppPaneprov = new JDesktopPane();
	desktoppPaneprov.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	frameCargaprov.getContentPane().add(desktoppPaneprov, BorderLayout.CENTER);
	
	
	
	
	
	
	
	
	JButton btnNewButton = new JButton("Salir");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	});
	
	JPanel panel = new JPanel();
	panel.setBackground(Color.LIGHT_GRAY);
	panel.setBounds(10, 11, 496, 459);
	desktoppPaneprov.add(panel);
	
	btnNewButton.setBounds(598, 520, 89, 23);
	desktoppPaneprov.add(btnNewButton);
	
	
	JButton btnVolver = new JButton("Volver");
	
	//menuPrincipal.frameMenu
	
	
	
	
	
	btnVolver.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frameCargaprov.setVisible(false);
			if(menuPrincipal.principal == null) {
				menuPrincipal.main(null);
			
			}else {
				menuPrincipal.frameMenu.setVisible(true);
			}
			
		}
	});
	
	btnVolver.setBounds(10, 520, 89, 23);
	desktoppPaneprov.add(btnVolver);
	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String conexion = connextion.conexion;
		Connection conn = DriverManager.getConnection(conexion);

		try (PreparedStatement clie = conn.prepareStatement("select usuario, pass, permiso from usuarios")) {
			ResultSet clien = clie.executeQuery();
			DefaultTableModel model = new DefaultTableModel(new String[]{"Usuario", "pass", "permiso"}, 0);
			tableus = new JTable();
			tableus.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableus.setFont(new Font("Arial", Font.PLAIN, 14));
			tableus.setFillsViewportHeight(true);
			JScrollPane scrollPane = new JScrollPane(tableus);
			panel.add(scrollPane);
			panel.add(tableus.getTableHeader(), BorderLayout.NORTH);
			
			tableus.setBorder(new BevelBorder(BevelBorder.RAISED));
			
			tableus.setModel(model);
			
			JScrollBar scrollBar = new JScrollBar();
			scrollPane.setRowHeaderView(scrollBar);
			
			while(clien.next())
			{
			   
			    String n = clien.getString("usuario");
			    String t = clien.getString("pass");
			    String l = clien.getString("permiso");
			    model.addRow(new Object[]{n, t, l});
			
			}
			
		        
	    }
		
	}catch(Exception ex){
		 JOptionPane.showMessageDialog(null, ex);
	}
	
	
	
	
	
	
	
	
	try {
		 ImageIcon img = new ImageIcon("./images/icon.png");
		    Image Image = img.getImage();
		    frameCargaprov.setIconImage(Image);
		 
	 } catch (Exception e) {
		    throw new RuntimeException(e);
		}
	
	
	  // JOptionPane.showMessageDialog(null, "dsasaddas"); tfBuscarcli
 
	JTextField tfBuscarcli = new JTextField();
	desktoppPaneprov.add(tfBuscarcli);
	tfBuscarcli.setBounds(231, 521, 134, 20);
	tfBuscarcli.setColumns(10);
	
	tfBuscarcli.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					String conexion = connextion.conexion;
					Connection conn = DriverManager.getConnection(conexion);

					try (PreparedStatement clie = conn.prepareStatement("select usuario, pass, permiso from usuarios where concat(usuario, pass, permiso) like '%"+ tfBuscarcli.getText() +"%'")) {
						ResultSet clien = clie.executeQuery();
						DefaultTableModel model = new DefaultTableModel(new String[]{"Usuario", "pass", "permiso"}, 0);
						tableus.setModel(model);
						frameCargaprov.setBackground(Color.black);
						
						while(clien.next())
						{
						    String n = clien.getString("usuario");
						    String t = clien.getString("pass");
						    String l = clien.getString("permiso");
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

					try (PreparedStatement clie = conn.prepareStatement("select usuario, pass, permiso from usuarios where concat(usuario, pass, permiso) like '%"+ tfBuscarcli.getText() +"%'")) {
						ResultSet clien = clie.executeQuery();
						DefaultTableModel model = new DefaultTableModel(new String[]{"Usuario", "pass", "permiso"}, 0);
						tableus.setModel(model);
						frameCargaprov.setBackground(Color.black);
						
						while(clien.next())
						{
						    String n = clien.getString("usuario");
						    String t = clien.getString("pass");
						    String l = clien.getString("permiso");
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

					try (PreparedStatement clie = conn.prepareStatement("select usuario, pass, permiso from usuarios where concat(usuario, pass, permiso) like '%"+ tfBuscarcli.getText() +"%'")) {
						ResultSet clien = clie.executeQuery();
					
						DefaultTableModel model = new DefaultTableModel(new String[]{"Usuario", "pass", "permiso"}, 0);
						tableus.setModel(model);
						frameCargaprov.setBackground(Color.black);
						
						while(clien.next())
						{
						    String n = clien.getString("usuario");
						    String t = clien.getString("pass");
						    String l = clien.getString("permiso");
						    model.addRow(new Object[]{n, t, l});
						
						}
						
					        
				    }
					
				}catch(Exception ex){
					 JOptionPane.showMessageDialog(null, ex);
				}
		  }
		  });
	
	
	
	
	
	frameCargaprov.setSize(724, 596);
	desktoppPaneprov.setBackground(new Color(205, 92, 92));
	

	
	
	JLabel lblNewLabel = new JLabel("Buscar usuarios");
	lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 11));
	lblNewLabel.setLabelFor(tfBuscarcli);
	lblNewLabel.setBounds(231, 505, 128, 14);
	desktoppPaneprov.add(lblNewLabel);
	
	
	
	
	
	
	
	
	
	
	
	
	
	JButton cargarnuevo = new JButton("Cargar");
	cargarnuevo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String conexion = connextion.conexion;
				Connection conn = DriverManager.getConnection(conexion);

				try {
				//	textField.getText().toString()   textField_1.getText().toString()
					String cliens = textFieldus.getText().toString();
					String pass = textField_1us.getText().toString();
					String permiso = textField_3us.getText();

					PreparedStatement clie = conn.prepareStatement("insert into usuarios values('"+cliens+"', '"+pass+"','"+ permiso +"')");
					
					
					clie.executeUpdate();
					textFieldus.setText("");
					textField_1us.setText("");
					textField_3us.setText("");
					lblNewLabel_2.setText("");
					textField_3us.setText("");
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

				try (PreparedStatement clie = conn.prepareStatement("select usuario, pass, permiso from usuarios")) {
					ResultSet clien = clie.executeQuery();
					DefaultTableModel model = new DefaultTableModel(new String[]{"Usuario", "pass", "permiso"}, 0);
					
					
					
					tableus.setModel(model);
					
					
					
					while(clien.next())
					{
					   
					    String n = clien.getString("usuario");
					    String t = clien.getString("pass");
					    String l = clien.getString("permiso");
					    model.addRow(new Object[]{n, t, l});
					
					}
					
				        
			    }
				
			}catch(Exception ex){
				 JOptionPane.showMessageDialog(null, ex);
			}
			
	}
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	cargarnuevo.setBounds(406, 470, 89, 38);
	desktoppPaneprov.add(cargarnuevo);
	
	JButton btnModificar = new JButton("Modificar");
	btnModificar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String conexion = connextion.conexion;
				Connection conn = DriverManager.getConnection(conexion);

				try {
				//	textField.getText().toString()   textField_1.getText().toString()
					String cliens = textFieldus.getText().toString();
					String pass = textField_1us.getText().toString();
					String permiso = textField_3us.getText();
				    
				    String id_clis = lblNewLabel_2.getText();
				    int id_clisnum = Integer.parseInt(id_clis);
					PreparedStatement clie = conn.prepareStatement("update usuarios set usuario='"+cliens+"', pass = '"+pass+"', permiso = '"+permiso+"' where id_usuario = '"+id_clisnum+"'");
					
					clie.execute();
					
					textFieldus.setText("");
					textField_1us.setText("");
					textField_3us.setText("");
					lblNewLabel_2.setText("");
					textField_3us.setText("");
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

				try (PreparedStatement clie = conn.prepareStatement("select usuario, pass, permiso from usuarios")) {
					ResultSet clien = clie.executeQuery();
					DefaultTableModel model = new DefaultTableModel(new String[]{"Usuario", "pass", "permiso"}, 0);
					
					
					
					tableus.setModel(model);
					
					
					
					while(clien.next())
					{
					   
					    String n = clien.getString("usuario");
					    String t = clien.getString("pass");
					    String l = clien.getString("permiso");
					    model.addRow(new Object[]{n, t, l});
					
					}
					
				        
			    }
				
			}catch(Exception ex){
				 JOptionPane.showMessageDialog(null, ex);
			}
			
		}
		
	});
	
	
	
	tableus.addMouseListener(new java.awt.event.MouseAdapter() {
	    @Override
	    public void mouseClicked(java.awt.event.MouseEvent evt) {
	    	
	    	//select usuario_cli, pass, permiso from usuarios join permisos on permisos.id_permiso = usuarios.id_permiso
	    	try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String conexion = connextion.conexion;
				Connection conn = DriverManager.getConnection(conexion);

				try (PreparedStatement clie = conn.prepareStatement("select id_usuario, usuario, pass, permiso from usuarios")) {
					ResultSet clien = clie.executeQuery();
					DefaultTableModel modelnew = new DefaultTableModel(new String[]{"id_usuario","Usuario", "pass", "permiso"}, 0);
					
					JTable tableforclick = new JTable();
					tableforclick.setModel(modelnew);
					
					
					
					while(clien.next())
					{
					    String z = clien.getString("id_usuario");
					    String n = clien.getString("usuario");
					    String t = clien.getString("pass");
		
					    String l1 = clien.getString("permiso");
					    modelnew.addRow(new Object[]{z,n, t, l1});
					    
					    int row = tableforclick.rowAtPoint(evt.getPoint());

				        if (row >= 0) {
				        	lblNewLabel_2.setText(tableforclick.getModel().getValueAt(row, 0)+"");
				            textFieldus.setText(tableforclick.getModel().getValueAt(row, 1)+"");
				            textField_1us.setText(tableforclick.getModel().getValueAt(row, 2)+"");
				            textField_3us.setText(tableforclick.getModel().getValueAt(row, 3)+"");
				            
				        }
					}
					
				        
			    }catch(Exception vi) {
			    	JOptionPane.showMessageDialog(null, vi + "asdmsdaois");
			    }
				
			}catch(Exception ex){
				 JOptionPane.showMessageDialog(null, ex);
			}
	    	
	    	
	    	
	    	
	    	
	    	
	        
	    }
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	btnModificar.setBounds(504, 470, 89, 38);
	desktoppPaneprov.add(btnModificar);
	
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
					PreparedStatement clie = conn.prepareStatement("delete from usuarios where id_usuario = '"+id_clisnum+"'");
					
					clie.execute();
					textFieldus.setText("");
					textField_1us.setText("");
					
					lblNewLabel_2.setText("");
					textField_3us.setText("");
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

				try (PreparedStatement clie = conn.prepareStatement("select usuario, pass, permiso from usuarios")) {
					ResultSet clien = clie.executeQuery();
					DefaultTableModel model = new DefaultTableModel(new String[]{"Usuario", "pass", "permiso"}, 0);
					
					
					
					tableus.setModel(model);
					
					
					
					while(clien.next())
					{
					   
					    String n = clien.getString("usuario");
					    String t = clien.getString("pass");
					    String l = clien.getString("permiso");
					    model.addRow(new Object[]{n, t, l});
					
					}
					
				        
			    }
				
			}catch(Exception ex){
				 JOptionPane.showMessageDialog(null, ex);
			}
			
		}
		
	});
	btnBorrar.setBounds(598, 470, 89, 38);
	desktoppPaneprov.add(btnBorrar);
	
	
	panel.setOpaque(false);
	
	JLabel lblNewLabel_1 = new JLabel("Nombre de usuario");
	lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
	lblNewLabel_1.setForeground(Color.BLACK);
	lblNewLabel_1.setBackground(Color.WHITE);
	lblNewLabel_1.setBounds(516, 11, 192, 14);
	desktoppPaneprov.add(lblNewLabel_1);
	
	textFieldus = new JTextField();
	textFieldus.setBounds(516, 35, 134, 20);
	desktoppPaneprov.add(textFieldus);
	textFieldus.setColumns(10);
	
	JLabel lblNewLabel_1_1 = new JLabel("Contrase\u00F1a");
	lblNewLabel_1_1.setForeground(Color.BLACK);
	lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
	lblNewLabel_1_1.setBackground(Color.WHITE);
	lblNewLabel_1_1.setBounds(516, 86, 144, 14);
	desktoppPaneprov.add(lblNewLabel_1_1);
	
	textField_1us = new JTextField();
	textField_1us.setColumns(10);
	textField_1us.setBounds(516, 110, 134, 20);
	desktoppPaneprov.add(textField_1us);
	
	JLabel lblNewLabel_1_2 = new JLabel("Permiso");
	lblNewLabel_1_2.setForeground(Color.BLACK);
	lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
	lblNewLabel_1_2.setBackground(Color.WHITE);
	lblNewLabel_1_2.setBounds(516, 155, 78, 14);
	desktoppPaneprov.add(lblNewLabel_1_2);
	
	textField_3us = new JTextField();

	
	JButton btnNewButton_1 = new JButton("Buscar");
	btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			seleccionPermisoUsuario.main(null);
		}
	});
	btnNewButton_1.setBounds(612, 175, 89, 30);
	desktoppPaneprov.add(btnNewButton_1);
	
	textField_3us = new JTextField();
	textField_3us.setEditable(false);
	textField_3us.setBounds(516, 180, 86, 20);
	desktoppPaneprov.add(textField_3us);
	textField_3us.setColumns(10);
	
	JButton btnNewButton_2 = new JButton("+");
	
	btnNewButton_2.setFont(new Font("Arial", Font.BOLD, 11));
	btnNewButton_2.setBounds(660, 29, 41, 30);
	desktoppPaneprov.add(btnNewButton_2);
	
	//./images/carrito-super.jpg
	
	
	

	
	btnNewButton_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				textFieldus.setText("");
				textField_1us.setText("");
				textField_3us.setText("");
				lblNewLabel_2.setText("");
				textField_3us.setText("");
				textField_3us.setText(null);
			}catch(Exception ev) {
				JOptionPane.showMessageDialog(null, ev);
			}
		
			
		}
	});

	frameCargaprov.setVisible(true);
		
}
}
