package Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class RegistroUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField UsuEmail;
	private JTextField UsuApellido;
	private JTextField UsuNombre;
	private JPasswordField UsuContraseña;
	private Login v1;
	

	
	
	public void setV1(Login v1) {
		this.v1 = v1;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroUsuario frame = new RegistroUsuario();
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
	public RegistroUsuario() {
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
		
		JLabel lblNewLabel_1 = new JLabel("Registro de Usuario");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 44, 258, 14);
		contentPane.add(lblNewLabel_1);
		
		UsuEmail = new JTextField();
		UsuEmail.setBounds(10, 96, 86, 20);
		contentPane.add(UsuEmail);
		UsuEmail.setColumns(10);
		
		UsuApellido = new JTextField();
		UsuApellido.setBounds(10, 147, 86, 20);
		contentPane.add(UsuApellido);
		UsuApellido.setColumns(10);
		
		UsuNombre = new JTextField();
		UsuNombre.setBounds(128, 147, 86, 20);
		contentPane.add(UsuNombre);
		UsuNombre.setColumns(10);
		
		UsuContraseña = new JPasswordField();
		UsuContraseña.setBounds(128, 96, 86, 20);
		contentPane.add(UsuContraseña);
		
		JLabel lblNewLabel_1_1 = new JLabel("contraseña");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1.setBounds(128, 69, 86, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("email");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1_1.setBounds(10, 69, 86, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Apellido");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1_1_1.setBounds(10, 127, 86, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Nombre");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1_1_2.setBounds(128, 127, 86, 14);
		contentPane.add(lblNewLabel_1_1_1_2);
		
		JComboBox UsuRol = new JComboBox();
		UsuRol.setModel(new DefaultComboBoxModel(new String[] {"Psicologo", "Administrador"}));
		UsuRol.setBounds(10, 203, 86, 22);
		contentPane.add(UsuRol);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Rol");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1_1_1_1.setBounds(10, 178, 86, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1);
		
		JButton btnNewButton_4 = new JButton("Atras");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v1.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_4.setBounds(322, 227, 89, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_4_1 = new JButton("Registrar usuario");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//conexion con la BD
				Conexion.ConexionBD conexion = new Conexion.ConexionBD();
			    Connection con = conexion.conectar();
				String nombre = UsuNombre.getText();
				String apellido = UsuApellido.getText();
				String email = UsuEmail.getText();
				String contraseña = UsuContraseña.getText();
				String rol = UsuRol.getSelectedItem().toString();
				
				if (nombre.isEmpty()||apellido.isEmpty()||email.isEmpty()||contraseña.isEmpty()||rol.isEmpty()) {
					JOptionPane.showMessageDialog(null, "debe completar todos los datos");
				}else {
					try {
						String consulta = "INSERT INTO cuenta (email, contraseña, nombre, rol, apellido) VALUES ('"+email+"', '"+contraseña+"', '"+nombre+"', '"+rol+"', '"+apellido+"')";
						PreparedStatement ps = con.prepareStatement(consulta);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "Usuario Registrado correctamente");
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "No se pudo registrar usuario"+ex);
					}finally {
						try {
							con.close();
						}catch(Exception exe) {
							JOptionPane.showMessageDialog(null, "No se pudo cerrar la conexion con la BD"+exe);
						}
					}
				}
			}
		});
		btnNewButton_4_1.setBounds(182, 227, 115, 23);
		contentPane.add(btnNewButton_4_1);
	}
}
