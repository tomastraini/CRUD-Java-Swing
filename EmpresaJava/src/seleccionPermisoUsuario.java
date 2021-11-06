import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class seleccionPermisoUsuario{

	private static JPanel contentPane;
	public static JFrame frameseleperus = new JFrame();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		frameseleperus.setTitle("Selecci\u00F3n de permisos");
		frameseleperus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameseleperus.setBounds(100, 100, 271, 272);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(205, 92, 92));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		try {
			 ImageIcon img = new ImageIcon("./images/icon.png");
			    Image Image = img.getImage();
			    frameseleperus.setIconImage(Image);
			 
		 } catch (Exception e) {
			    JOptionPane.showMessageDialog(null, e);
		}
		
		frameseleperus.	setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(205, 92, 92));
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameseleperus.setVisible(false);
			}
		});
		btnNewButton.setBounds(157, 183, 78, 29);
		desktopPane.add(btnNewButton);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameseleperus.setVisible(false);
				
				cargarUsuarios.textField_3us.setText("admin");
				
			}
		});
		btnAdmin.setBounds(10, 86, 78, 29);
		desktopPane.add(btnAdmin);
		
		JButton btnUsuario = new JButton("Usuario");
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameseleperus.setVisible(false);
				
				cargarUsuarios.textField_3us.setText("user");
				
			}
			
		});
		btnUsuario.setBounds(157, 86, 78, 29);
		desktopPane.add(btnUsuario);
		
		JLabel lblNewLabel = new JLabel("Seleccionar permiso para usuario");
		lblNewLabel.setBounds(10, 25, 225, 14);
		desktopPane.add(lblNewLabel);
		
		
		frameseleperus.setVisible(true);
	}
					
					
	
}
