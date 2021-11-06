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

public class cargarProveedores{
	public static JTable tableprove;
	public static JTextField textFieldprove;
	public static JTextField textField_1prove;
	public static JTextField textField_2prove;
	public static JFrame frameCargacliprove ;
	public static JLabel lblNewLabel_2;
	static JTextField textField_3prove;
	public static void main(String[] args) {
	JFrame frameCargaprov = new JFrame();
	
	
	
	frameCargaprov.setTitle("Carga de proveedores");

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
	
	JLabel lblNewLabel_2 = new JLabel("ID PROVEEDOR");
	lblNewLabel_2.setBounds(516, 254, 139, 14);
	desktoppPaneprov.add(lblNewLabel_2);
	
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

		try (PreparedStatement clie = conn.prepareStatement("select razon_social, telefono, localidad from proveedores join localidades on localidades.cp = proveedores.cp")) {
			ResultSet clien = clie.executeQuery();
			DefaultTableModel model = new DefaultTableModel(new String[]{"Razón social del proveedor", "Teléfono", "Localidad"}, 0);
			tableprove = new JTable();
			tableprove.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableprove.setFont(new Font("Arial", Font.PLAIN, 14));
			tableprove.setFillsViewportHeight(true);
			JScrollPane scrollPane = new JScrollPane(tableprove);
			panel.add(scrollPane);
			panel.add(tableprove.getTableHeader(), BorderLayout.NORTH);
			
			tableprove.setBorder(new BevelBorder(BevelBorder.RAISED));
			
			tableprove.setModel(model);
			
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

					try (PreparedStatement clie = conn.prepareStatement("select razon_social, telefono, localidad from proveedores join localidades on \r\n"
							+ "localidades.cp = proveedores.cp where concat(razon_social, telefono, localidad) like '%"+ tfBuscarcli.getText() +"%'")) {
						ResultSet clien = clie.executeQuery();
						DefaultTableModel model = new DefaultTableModel(new String[]{"Razón social del proveedor", "Teléfono", "Localidad"}, 0);
						tableprove.setModel(model);
						frameCargaprov.setBackground(Color.black);
						
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

					try (PreparedStatement clie = conn.prepareStatement("select razon_social, telefono, localidad from proveedores join localidades on \r\n"
							+ "localidades.cp = proveedores.cp where concat(razon_social, telefono, localidad) like '%"+ tfBuscarcli.getText() +"%'")) {
						ResultSet clien = clie.executeQuery();
						DefaultTableModel model = new DefaultTableModel(new String[]{"Razón social del proveedor", "Teléfono", "Localidad"}, 0);
						tableprove.setModel(model);
						frameCargaprov.setBackground(Color.black);
						
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

					try (PreparedStatement clie = conn.prepareStatement("select razon_social, telefono, localidad from proveedores join localidades on \r\n"
							+ "localidades.cp = proveedores.cp where concat(razon_social, telefono, localidad) like '%"+ tfBuscarcli.getText() +"%'")) {
						ResultSet clien = clie.executeQuery();
					
						DefaultTableModel model = new DefaultTableModel(new String[]{"Razón social del proveedor", "Teléfono", "Localidad"}, 0);
						tableprove.setModel(model);
						frameCargaprov.setBackground(Color.black);
						
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
	
	
	
	
	
	frameCargaprov.setSize(724, 596);
	desktoppPaneprov.setBackground(new Color(205, 92, 92));
	

	
	
	JLabel lblNewLabel = new JLabel("Buscar proveedores");
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
					String cliens = textFieldprove.getText().toString();
					String telefono = textField_1prove.getText().toString();
					String localidad = textField_2prove.getText();
				    int localidad1 = Integer.parseInt(localidad);
					PreparedStatement clie = conn.prepareStatement("insert into proveedores values('"+cliens+"', '"+telefono+"','"+ localidad1 +"')");
					
					
					clie.executeUpdate();
					textFieldprove.setText("");
					textField_1prove.setText("");
					textField_2prove.setText("");
					lblNewLabel_2.setText("");
					textField_3prove.setText("");
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

				try (PreparedStatement clie = conn.prepareStatement("select razon_social, telefono, localidad from proveedores join localidades on localidades.cp = proveedores.cp")) {
					ResultSet clien = clie.executeQuery();
					DefaultTableModel model = new DefaultTableModel(new String[]{"Razón social del proveedor", "Teléfono", "Localidad"}, 0);
					
					
					
					tableprove.setModel(model);
					
					
					
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
					String cliens = textFieldprove.getText().toString();
					String telefono = textField_1prove.getText().toString();
					String localidad = textField_2prove.getText();
				    int localidad1 = Integer.parseInt(localidad);
				    String id_clis = lblNewLabel_2.getText();
				    int id_clisnum = Integer.parseInt(id_clis);
					PreparedStatement clie = conn.prepareStatement("update proveedores set razon_social='"+cliens+"', telefono = '"+telefono+"', cp = '"+localidad1+"' where id_proveedor = '"+id_clisnum+"'");
					
					clie.execute();
					
					textFieldprove.setText("");
					textField_1prove.setText("");
					textField_2prove.setText("");
					lblNewLabel_2.setText("");
					textField_3prove.setText("");
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

				try (PreparedStatement clie = conn.prepareStatement("select razon_social, telefono, localidad from proveedores join localidades on localidades.cp = proveedores.cp")) {
					ResultSet clien = clie.executeQuery();
					DefaultTableModel model = new DefaultTableModel(new String[]{"Razón social del proveedor", "Teléfono", "Localidad"}, 0);
					
					
					
					tableprove.setModel(model);
					
					
					
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
	
	
	
	tableprove.addMouseListener(new java.awt.event.MouseAdapter() {
	    @Override
	    public void mouseClicked(java.awt.event.MouseEvent evt) {
	    	
	    	//select razon_social_cli, telefono, localidad from proveedores join localidades on localidades.id_localidad = proveedores.id_localidad
	    	try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String conexion = connextion.conexion;
				Connection conn = DriverManager.getConnection(conexion);

				try (PreparedStatement clie = conn.prepareStatement("select id_proveedor, razon_social, telefono, localidades.cp, localidad from proveedores join localidades on localidades.cp = proveedores.cp")) {
					ResultSet clien = clie.executeQuery();
					DefaultTableModel modelnew = new DefaultTableModel(new String[]{"id_proveedor","Razón social del proveedor", "Teléfono", "cp", "localidad"}, 0);
					
					JTable tableforclick = new JTable();
					tableforclick.setModel(modelnew);
					
					
					
					while(clien.next())
					{
					    String z = clien.getString("id_proveedor");
					    String n = clien.getString("razon_social");
					    String t = clien.getString("telefono");
					    String l = clien.getString("cp");
					    String l1 = clien.getString("localidad");
					    modelnew.addRow(new Object[]{z,n, t, l, l1});
					    
					    int row = tableforclick.rowAtPoint(evt.getPoint());

				        if (row >= 0) {
				        	lblNewLabel_2.setText(tableforclick.getModel().getValueAt(row, 0)+"");
				            textFieldprove.setText(tableforclick.getModel().getValueAt(row, 1)+"");
				            textField_1prove.setText(tableforclick.getModel().getValueAt(row, 2)+"");
				            textField_2prove.setText(tableforclick.getModel().getValueAt(row, 3)+"");
				            textField_3prove.setText(tableforclick.getModel().getValueAt(row, 4)+"");
				        }
					}
					
				        
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
					PreparedStatement clie = conn.prepareStatement("delete from proveedores where id_proveedor = '"+id_clisnum+"'");
					
					clie.execute();
					textFieldprove.setText("");
					textField_1prove.setText("");
					textField_2prove.setText("");
					lblNewLabel_2.setText("");
					textField_3prove.setText("");
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

				try (PreparedStatement clie = conn.prepareStatement("select razon_social, telefono, localidad from proveedores join localidades on localidades.cp = proveedores.cp")) {
					ResultSet clien = clie.executeQuery();
					DefaultTableModel model = new DefaultTableModel(new String[]{"Razón social del proveedore", "Teléfono", "Localidad"}, 0);
					
					
					
					tableprove.setModel(model);
					
					
					
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
	desktoppPaneprov.add(btnBorrar);
	
	
	panel.setOpaque(false);
	
	JLabel lblNewLabel_1 = new JLabel("Raz\u00F3n social del proveedor");
	lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
	lblNewLabel_1.setForeground(Color.BLACK);
	lblNewLabel_1.setBackground(Color.WHITE);
	lblNewLabel_1.setBounds(516, 11, 192, 14);
	desktoppPaneprov.add(lblNewLabel_1);
	
	textFieldprove = new JTextField();
	textFieldprove.setBounds(516, 35, 134, 20);
	desktoppPaneprov.add(textFieldprove);
	textFieldprove.setColumns(10);
	
	JLabel lblNewLabel_1_1 = new JLabel("Tel\u00E9fono");
	lblNewLabel_1_1.setForeground(Color.BLACK);
	lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
	lblNewLabel_1_1.setBackground(Color.WHITE);
	lblNewLabel_1_1.setBounds(516, 86, 144, 14);
	desktoppPaneprov.add(lblNewLabel_1_1);
	
	textField_1prove = new JTextField();
	textField_1prove.setColumns(10);
	textField_1prove.setBounds(516, 110, 134, 20);
	desktoppPaneprov.add(textField_1prove);
	
	JLabel lblNewLabel_1_2 = new JLabel("Localidad");
	lblNewLabel_1_2.setForeground(Color.BLACK);
	lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
	lblNewLabel_1_2.setBackground(Color.WHITE);
	lblNewLabel_1_2.setBounds(516, 155, 78, 14);
	desktoppPaneprov.add(lblNewLabel_1_2);
	
	textField_2prove = new JTextField();
	textField_2prove.setEnabled(false);
	textField_2prove.setColumns(10);
	textField_2prove.setBounds(598, 251, 101, 20);
	desktoppPaneprov.add(textField_2prove);
	
	JButton btnNewButton_1 = new JButton("Buscar");
	btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			seleccionlocalidadproveedor.main(null);
		}
	});
	btnNewButton_1.setBounds(612, 175, 89, 30);
	desktoppPaneprov.add(btnNewButton_1);
	
	textField_3prove = new JTextField();
	textField_3prove.setEditable(false);
	textField_3prove.setBounds(516, 180, 86, 20);
	desktoppPaneprov.add(textField_3prove);
	textField_3prove.setColumns(10);
	
	JButton btnNewButton_2 = new JButton("+");
	
	btnNewButton_2.setFont(new Font("Arial", Font.BOLD, 11));
	btnNewButton_2.setBounds(660, 29, 41, 30);
	desktoppPaneprov.add(btnNewButton_2);
	
	//./images/carrito-super.jpg
	
	
	

	lblNewLabel_2.setVisible(true);
	textField_2prove.setVisible(true);
	btnNewButton_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				textFieldprove.setText("");
				textField_1prove.setText("");
				textField_2prove.setText("");
				lblNewLabel_2.setText("");
				textField_3prove.setText("");
				textField_3prove.setText(null);
			}catch(Exception ev) {
				JOptionPane.showMessageDialog(null, ev);
			}
		
			
		}
	});
	
	lblNewLabel_2.setVisible(false);
	textField_2prove.setVisible(false);
	frameCargaprov.setVisible(true);
		
}
}
