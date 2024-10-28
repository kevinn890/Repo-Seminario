package Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GestionPaciente.OpcionesPacientes;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField LoginEmail;
	private JPasswordField LoginContraseña;
	RegistroUsuario v2 = new RegistroUsuario();
	VentanaPrincipal v3 = new VentanaPrincipal();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sistema de Gestion y asistencia en sesion Psicoterapeutica");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 401, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 73, 141, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_4 = new JButton("Salir");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_4.setBounds(322, 227, 89, 23);
		contentPane.add(btnNewButton_4);
		
		LoginEmail = new JTextField();
		LoginEmail.setBounds(10, 114, 131, 20);
		contentPane.add(LoginEmail);
		LoginEmail.setColumns(10);
		
		JButton btnNewButton = new JButton("Registrar un usuario");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v2.setV1(Login.this);
				v2.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 227, 198, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Entrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String email = LoginEmail.getText();
				String contraseña = LoginContraseña.getText();
				//conexion con la BD
			    Conexion.ConexionBD conexion = new Conexion.ConexionBD();
				Connection con = conexion.conectar();
				if(!email.equals("")&&!contraseña.equals("")) {
					try {
						
						PreparedStatement ps = con.prepareStatement("SELECT rol from cuenta WHERE email='"+email+"' AND contraseña='"+contraseña+"'");
						ResultSet rs = ps.executeQuery();
						if(rs.next()) {
							String rol = rs.getString("rol");
							if(rol.equalsIgnoreCase("Psicologo")||rol.equalsIgnoreCase("administrador")) {
								
								v3.setVisible(true);
								setVisible(false);
							}
						}
					}catch(Exception ex) {
						
						JOptionPane.showMessageDialog(null, "Los datos ingresados no coinciden con la base de datos");
					}finally {
						try {
							con.close();
						   
						}catch(Exception exep) {
							JOptionPane.showMessageDialog(null,"La conexion con la BD no se pudo cerrar" +exep);
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "Debe completar los datos");
				}
				
			}
		});
		btnNewButton_1.setBounds(10, 188, 131, 23);
		contentPane.add(btnNewButton_1);
		
		LoginContraseña = new JPasswordField();
		LoginContraseña.setToolTipText("");
		LoginContraseña.setBounds(10, 157, 131, 20);
		contentPane.add(LoginContraseña);
		
		JLabel lblNewLabel_1_1 = new JLabel("email de usuario");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1.setBounds(10, 98, 141, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("contraseña");
		lblNewLabel_1_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		lblNewLabel_1_2.setBounds(10, 142, 141, 14);
		contentPane.add(lblNewLabel_1_2);
	}
}
