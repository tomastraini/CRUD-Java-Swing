import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


class login{
public static JLabel permiso;
public static String permistring;
public login() throws InterruptedException{
	
 
	
	
 JFrame frameLogin = new JFrame("Login de empresas");
 frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
 frameLogin.setSize(400, 250);     


 JTextField usuario = new JTextField(10);
 frameLogin.add(usuario);
 usuario.setBounds(10,25, 150,30); 

 JLabel lblUsuario = new JLabel("Ingresar usuario");
 frameLogin.add(lblUsuario);
 lblUsuario.setBounds(10,0, 150,30); 


 JPasswordField passw = new JPasswordField();
 passw.setEchoChar('*');
 frameLogin.add(passw);
 passw.setBounds(10,110, 150,30);
 

 JLabel lblpassw  = new JLabel("Ingresar contrase\u00f1a");
 frameLogin.add(lblpassw);
 lblpassw.setBounds(10,85, 150,30); 
 
 //////////PERMISO//////////
 
 JLabel permiso = new JLabel("Permisos");
 frameLogin.add(permiso);
 permiso.setBounds(200,120, 150,30); 
 permistring = permiso.getText();
 //permiso.setVisible(false);
 
//////////PERMISO//////////
 
 JButton aceptar = new JButton("Aceptar");
 frameLogin.add(aceptar);
 aceptar.setBounds(275, 170, 100,30);

 JButton salir = new JButton("Salir");
 frameLogin.add(salir);
 salir.setBounds(10, 170, 100,30);

///////////DATE///////////
 Calendar cal = new GregorianCalendar();
 
 
 int dia = cal.get(Calendar.DAY_OF_MONTH);
 int mes = cal.get(Calendar.MONTH);
 int anio = cal.get(Calendar.YEAR);
 JLabel lbldate = new JLabel("<html>La fecha actual es:<br>" +dia+"/"+(mes+1)+"/"+anio+ "");
 frameLogin.add(lbldate);
 lbldate.setBounds(200,25, 150,30); 
 
 

 Runnable runnable = new Runnable() {

     @Override
     public void run() {
       while (true) {
    	   JLabel lblhora = new JLabel("");
    	   
    	   Calendar cal1 = new GregorianCalendar();
    	   int hora;
    	   int min;
    	   int sec;
    	   hora = cal1.get(Calendar.HOUR);
    	   min = cal1.get(Calendar.MINUTE);
    	   sec = cal1.get(Calendar.SECOND);
    	   int length = String.valueOf(sec).length();
    	   if(length == 1) {
    		   lblhora.setText("" + hora + ":"+ min + ":0" + sec + "");
        	   frameLogin.add(lblhora);
        	   lblhora.setBounds(260,33, 150,30); 
    	   }else {
    		   lblhora.setText("" + hora + ":"+ min + ":" + sec + "");
        	   frameLogin.add(lblhora);
        	   lblhora.setBounds(260,33, 150,30); 
    	   }
    	   
    	   
         try {
           Thread.sleep(1000);
           lblhora.setText("");
         }
         catch (InterruptedException e) {
           e.printStackTrace();
         }
       }
     }
 };

   Thread t = new Thread(runnable);
   t.start();
 

 
 
 
 
 
 



 
 
///////////DATE///////////END
 
 JLabel usu = new JLabel("<html>Usted va a ingresar como: <html>");
 frameLogin.add(usu);
 usu.setBounds(200, 75, 150, 30);
 usu.setVisible(false);
 


 
 passw.addKeyListener(new KeyAdapter() {
     @Override
     public void keyPressed(KeyEvent e) {
         if(e.getKeyCode() == KeyEvent.VK_ENTER){
     		try {
    			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    			String conexion = connextion.conexion;
    			Connection conn = DriverManager.getConnection(conexion);
    			try (@SuppressWarnings("deprecation")
				PreparedStatement users = conn.prepareStatement("Select * from usuarios where usuario = '" + usuario.getText() + "' and pass = '"+ passw.getText().toString() +"'")) {
    				ResultSet user = users.executeQuery();
    				if(user.next()){
    						frameLogin.setVisible(false);
    						
    						menuPrincipal.main(permistring);
    						
    						}else{
    							JOptionPane.showMessageDialog(null, "Usuario o contrase\u00f1a incorrectos!");
    						}
    			}
    		}catch(Exception ex){
    			 JOptionPane.showMessageDialog(null, ex);
    		}
         }
     }

 });
 
 
 
 
 usuario.addFocusListener(new java.awt.event.FocusAdapter() {
     
     public void focusLost(java.awt.event.FocusEvent evt) {
    	 try {
 			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
 			String conexion = connextion.conexion;
 			Connection conn = DriverManager.getConnection(conexion);
 			try (PreparedStatement users = conn.prepareStatement("Select * from usuarios where usuario = '" + usuario.getText() + "'")) {
 				ResultSet user = users.executeQuery();
 				if(user.next()){
 						usu.setVisible(true);
 						usu.setText("<html>Usted va a ingresar como: <BR>" + usuario.getText() + "<html>");
 						String t = user.getString("permiso");
 						permiso.setText(t);
 						permistring = permiso.getText();
 						}else{
 							JOptionPane.showMessageDialog(null, "Usuario inexistente!");
 						}
 			}
 		}catch(Exception ex){
 			 JOptionPane.showMessageDialog(null, ex);
 		}
     }
 });


 
 
passw.addFocusListener(new java.awt.event.FocusAdapter() {
     
     public void focusLost(java.awt.event.FocusEvent evt) {
    	 usu.setVisible(false);
     }
 });
 
 
 
 
 
 
 
 
 
 
 
 salir.addActionListener(new ActionListener(){  
	    @Override
		public void actionPerformed(ActionEvent e) {
			
			 System.exit(1);
		}  
	   
	 	});
	 


 aceptar.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
		
		try {
		
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			String conexion = connextion.conexion;
			Connection conn = DriverManager.getConnection(conexion);
			
			
			try (@SuppressWarnings("deprecation")
			PreparedStatement users = conn.prepareStatement("Select * from usuarios where usuario = '" + usuario.getText() + "' and pass = '"+ passw.getText().toString() +"'")) {
				ResultSet user = users.executeQuery();
				if(user.next()){
						frameLogin.setVisible(false);
						
						menuPrincipal.main(permistring);

						}else{
							JOptionPane.showMessageDialog(null, "Usuario o contrase\u00f1a incorrectos!");
						}
			}
		}catch(Exception ex){
			 JOptionPane.showMessageDialog(null, ex);
		}
		
		
		
				
			

}

});
 //"./bin/icon.ico"
 //C:/Users/locos/OneDrive/Documentos/Java%20proyectos/EmpresaJava/Images/icon.ico
 
 try {
	 ImageIcon img = new ImageIcon("./images/icon.png");
	    Image Image = img.getImage();
	    frameLogin.setIconImage(Image);
	 
 } catch (Exception e) {
	    throw new RuntimeException(e);
	}
 
 
 
 
 
 permiso.setVisible(false);
 
 frameLogin.setLayout(null);
 frameLogin.setVisible(true);
 frameLogin.setLocationRelativeTo(null);
 frameLogin.getContentPane().setBackground(new Color(205,92,92));
}


public static void main(String[] args) throws InterruptedException{
	
  new login();


}
}


