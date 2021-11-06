import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class menuPrincipal {

static JMenuBar principal;	

public static JMenu cargar;
public static JMenu ventas;
public static JMenu productos;
public static JMenu configuracion;

public static JFrame frameMenu;

public static JMenuItem cargarcli;
public static JMenuItem cargarventa;
public static JMenuItem cargarprod;
public static JMenuItem cargarprov;
public static JMenuItem cargarprovi;
public static JMenuItem cargarloca;
public static JMenuItem usuarios;

public static JButton volver;
public static JButton salirMenu;



@SuppressWarnings("serial")
public static void main(String permistring){
	
	JFrame frameMenu = new JFrame("Men\u00fa principal");
	frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frameMenu.setResizable(false);
	frameMenu.setSize(660, 432);
	
	
	
	
	
	
	try {
	    final Image backgroundImage = ImageIO.read(new File("./images/carrito-super.jpg"));
	    frameMenu.setContentPane(new JPanel() {
			@Override public void paintComponent(Graphics g) {
	            g.drawImage(backgroundImage, 0, 0, null);
	        }
	    });
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
	
	try {
		 ImageIcon img = new ImageIcon("./images/icon.png");
		    Image Image = img.getImage();
		    frameMenu.setIconImage(Image);
		 
	 } catch (Exception e) {
		    throw new RuntimeException(e);
		}
	
	
	
	 principal = new JMenuBar();
	 try {
		 
		
	 }catch(Exception pers) {
		 JOptionPane.showMessageDialog(null, pers);
	 }
	
	 
	 cargar=new JMenu("Cargar"); 
	 ventas = new JMenu("Ventas");
	 productos = new JMenu("Productos");
	 configuracion = new JMenu("Configuración");
	 
	 cargarcli = new JMenuItem("Cargar clientes");
	 cargarprov= new JMenuItem ("Cargar proveedores");
	 cargarprovi= new JMenuItem ("Cargar provincias");
	 cargarloca= new JMenuItem("Cargar localidades");
	 cargarventa= new JMenuItem("Cargar ventas");
	 cargarprod= new JMenuItem("Cargar productos");
	 usuarios= new JMenuItem("Sistema de usuarios");
	 
/////////////////////////////////////////////
	 JLabel permiso = new JLabel(login.permistring);
	 frameMenu.add(permiso);
	 permiso.setBounds(200,120, 150,30); 
	 permiso.setVisible(false);
/////////////////////////////////////////////	
	 
	 
	 
	 
	 
	 principal.add(cargar);
	 principal.add(ventas);
	 principal.add(productos);
	 principal.add(configuracion);
	 
	 cargar.add(cargarcli);
	 cargar.add(cargarprov);
	 cargar.add(cargarprovi);
	 cargar.add(cargarloca);
	 
	 ventas.add(cargarventa);
	 
	 productos.add(cargarprod);
	 
	 configuracion.add(usuarios);
	 
	 frameMenu.add(principal);

	 volver = new JButton("Volver");
	 frameMenu.add(volver);
	 volver.setBounds(10, 340, 80, 40);
	 
	 salirMenu = new JButton("Salir");
	 frameMenu.add(salirMenu);
	 salirMenu.setBounds(555, 340, 80, 40);
	 
	 
	 principal.setBounds(0, 0, 400, 20);
	 cargar.setBounds(500, 50, 75, 100);
	 
	 cargarcli.setBounds(500, 50, 50, 50);
	 cargarprov.setBounds(500, 50, 50, 50);
	 cargarprovi.setBounds(500, 50, 50, 50);
	 cargarloca.setBounds(500, 50, 50, 50);
	 cargarventa.setBounds(500, 50, 50, 50);
	 cargarprod.setBounds(500, 50, 50, 50);
	 
	 configuracion.setBounds(500, 500, 50, 50);
	 
	

	 
	 
	 frameMenu.setLocationRelativeTo(null);
	 frameMenu.setLayout(null);
	 frameMenu.setVisible(true);
	 
	 
	 cargarloca.addActionListener(new AbstractAction("Cargar localidad") {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 	try {
			 		cargarLocalidad.main(null);
			 	}catch (Exception x){
			 		JOptionPane.showMessageDialog(null, x);
			 	}
			 
			 
		    }
		});
	 
	 
	 cargarprovi.addActionListener(new AbstractAction("Cargar provincia") {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 	try {
			 		cargarProvincias.main(null);
			 	}catch (Exception x){
			 		JOptionPane.showMessageDialog(null, x);
			 	}
			 
			 
		    }
		});
	 
	 
	 
	 
	 
	 cargarcli.addActionListener(new AbstractAction("Cargar cliente") {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 	try {
		
			 		cargarClientes.main(null);
			 		
			 	}catch (Exception x){
			 		JOptionPane.showMessageDialog(null, x);
			 	}
			 
			 
		    }
		});
	 
	 
	 
	 cargarprov.addActionListener(new AbstractAction("Cargar proveedor") {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 	try {
		
			 		cargarProveedores.main(null);
			 		
			 	}catch (Exception x){
			 		JOptionPane.showMessageDialog(null, x);
			 	}
			 
			 
		    }
		});
	 
	 
	 
	 
	 usuarios.addActionListener(new AbstractAction("Sistema de usuarios") {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 	try {
			 		cargarUsuarios.main(null);
			 		
			 	}catch (Exception x){
			 		JOptionPane.showMessageDialog(null, x);
			 	}
			 
			 
		    }
		});
	 
	 
	 
	 
	 
	 
	 
	 salirMenu.addActionListener(new ActionListener(){  
		    @Override
			public void actionPerformed(ActionEvent e) {

				try {
					System.exit(0);
					
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}  
		   
		 	});
	 
	 
	 
	 
	 
	 volver.addActionListener(new ActionListener(){  
		    @Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					frameMenu.setVisible(false);
					login.main(null);
					
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}
			}  
		   
		 	});
	 
	 if(permiso.getText().equals( "user")) {
			usuarios.setEnabled(false);
		}
	 
	 
	 
	 
	 
}
}
