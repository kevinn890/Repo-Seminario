package GestionPaciente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegistroPacientes1 extends JFrame {

	private JPanel contentPane;
	private OpcionesPacientes v1;
	private JTextField PacDni;
	private JLabel lblNewLabel_1_1_3;
	private JTextField PacCuil;
	private JLabel lblNewLabel_1_1_4;
	private JTextField PacApellido;
	private JLabel lblNewLabel_1_1_5;
	private JTextField PacNombres;
	private JTextField PacNacimiento;
	private JLabel lblNewLabel_1_1_6;
	private JTextField PacEmail;
	private JLabel lblNewLabel_1_1_7;
	private JTextField PacTelefono;
	private JLabel lblNewLabel_1_1_8;
	private JLabel lblNewLabel_1_1_10;
	private JLabel lblNewLabel_1_1_11;
	private JLabel lblNewLabel_1_1_12;
	private JCheckBox PacDerivado;
	private JTextField PacEstado;
	private JLabel PacEstado123;
	private JTextField textField;
	private JLabel PacDomici;
	private JCheckBox PacPsiqui;
	private JTextField PacEMTelefono;
	private JTextField PacEMNombre;
	private JTextField PacIDObraSocial;
	private JComboBox PacOS;
	
	
	public void setV1(OpcionesPacientes v1) {
		this.v1 = v1;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroPacientes1 frame = new RegistroPacientes1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public int valorCheck(JCheckBox check) {
		if (check.isSelected()) {
			return 1;
		}else {
			return 0;
		}
	}

	/**
	 * Create the frame.
	 */
	public RegistroPacientes1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Registro de pacientes");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 11, 258, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Guardar y Continuar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//conexion con la BD
				Conexion.ConexionBD conexion = new Conexion.ConexionBD();
			    Connection con = conexion.conectar();
				
				String nombre = PacNombres.getText();
				String apellido = PacApellido.getText();
				String email = PacEmail.getText();
				String cuil = PacCuil.getText();
				String dni = PacDni.getText();
				String telefono = PacTelefono.getText();
				String domicilio = PacDomici.getText();
				String nacimiento = PacNacimiento.getText();
				int psiqui = valorCheck(PacPsiqui);
				int deri = valorCheck(PacPsiqui);
				String emerNombre = PacEMNombre.getText();
				String emerTelefono = PacEMTelefono.getText(); 
				String estadoCivil = PacEstado.getText();
				String obraSocial = PacOS.getSelectedItem().toString();
				String diagnostico = PacIDObraSocial.getText();
				int IDObrasocial;
			   
				
				if (nombre.isEmpty()||apellido.isEmpty()||email.isEmpty()||cuil.isEmpty()||dni.isEmpty()||telefono.isEmpty()||domicilio.isEmpty()||nacimiento.isEmpty()||emerNombre.isEmpty()||emerTelefono.isEmpty()||estadoCivil.isEmpty()||obraSocial.isEmpty()||diagnostico.isEmpty()) {
					JOptionPane.showMessageDialog(null, "debe completar todos los datos");
				}else {
					try {
						String consulta1 = "SELECT idObraSocial FROM obrasocial WHERE nombre LIKE '%"+obraSocial+"%' ";
					    Statement ps1 = con.createStatement();
			    
		
						ResultSet rs = ps1.executeQuery(consulta1);
						IDObrasocial = rs.getInt(1);
						System.out.println("id obrasocial"+IDObrasocial);
						
						String consulta = "INSERT INTO paciente (dni, cuil, nombre, apellido, email, telefono, domicilio, estadoCivil, pacientePsiquiatrico, pacienteDerivado, diagnosticoPresuntivo, contEmerNombre, contEmerTelefono, ObraSocial_idObraSocial) VALUES ('"+dni+"', '"+cuil+"', '"+nombre+"', '"+apellido+"', '"+email+"', '"+telefono+"', '"+domicilio+"', '"+estadoCivil+"', '"+psiqui+"', '"+deri+"', '"+diagnostico+"', '"+emerNombre+"', '"+emerTelefono+"', '"+obraSocial+"')";
						PreparedStatement ps = con.prepareStatement(consulta);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "paciente Registrado correctamente");
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "No se pudo registrar paciente"+ex);
					}finally{
						try {
							con.close();
						}catch(Exception ex){
							JOptionPane.showInternalMessageDialog(null, "No se pudo cerrar la conexion con la base de datos"+ex);
						}
						
					}
				}
				
			}
		});
		btnNewButton.setBounds(277, 227, 147, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v1.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(335, 194, 89, 23);
		contentPane.add(btnNewButton_1);
		
		PacDni = new JTextField();
		PacDni.setBounds(10, 70, 88, 20);
		contentPane.add(PacDni);
		PacDni.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Datos personales");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1.setBounds(10, 36, 98, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Datos Obra Social");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1_1.setBounds(206, 36, 98, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Contacto Emergencia");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1_1_1.setBounds(311, 36, 113, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("DNI");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_1_1_2.setBounds(10, 55, 88, 14);
		contentPane.add(lblNewLabel_1_1_2);
		
		lblNewLabel_1_1_3 = new JLabel("CUIL");
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_1_1_3.setBounds(10, 91, 88, 14);
		contentPane.add(lblNewLabel_1_1_3);
		
		PacCuil = new JTextField();
		PacCuil.setColumns(10);
		PacCuil.setBounds(10, 106, 88, 20);
		contentPane.add(PacCuil);
		
		lblNewLabel_1_1_4 = new JLabel("APELLIDO");
		lblNewLabel_1_1_4.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_1_1_4.setBounds(10, 134, 88, 14);
		contentPane.add(lblNewLabel_1_1_4);
		
		PacApellido = new JTextField();
		PacApellido.setColumns(10);
		PacApellido.setBounds(10, 149, 88, 20);
		contentPane.add(PacApellido);
		
		lblNewLabel_1_1_5 = new JLabel("NOMBRES");
		lblNewLabel_1_1_5.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_1_1_5.setBounds(10, 169, 88, 14);
		contentPane.add(lblNewLabel_1_1_5);
		
		PacNombres = new JTextField();
		PacNombres.setColumns(10);
		PacNombres.setBounds(10, 184, 88, 20);
		contentPane.add(PacNombres);
		
		PacNacimiento = new JTextField();
		PacNacimiento.setColumns(10);
		PacNacimiento.setBounds(10, 230, 88, 20);
		contentPane.add(PacNacimiento);
		
		lblNewLabel_1_1_6 = new JLabel("FECHA DE NACIMIENTO");
		lblNewLabel_1_1_6.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_1_1_6.setBounds(10, 215, 88, 14);
		contentPane.add(lblNewLabel_1_1_6);
		
		PacEmail = new JTextField();
		PacEmail.setColumns(10);
		PacEmail.setBounds(108, 70, 88, 20);
		contentPane.add(PacEmail);
		
		lblNewLabel_1_1_7 = new JLabel("EMAIL");
		lblNewLabel_1_1_7.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_1_1_7.setBounds(108, 55, 88, 14);
		contentPane.add(lblNewLabel_1_1_7);
		
		PacTelefono = new JTextField();
		PacTelefono.setColumns(10);
		PacTelefono.setBounds(108, 106, 88, 20);
		contentPane.add(PacTelefono);
		
		lblNewLabel_1_1_8 = new JLabel("TELEFONO");
		lblNewLabel_1_1_8.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_1_1_8.setBounds(108, 91, 88, 14);
		contentPane.add(lblNewLabel_1_1_8);
		
		lblNewLabel_1_1_10 = new JLabel("DIAGNOSTICO PRESUNTIVO");
		lblNewLabel_1_1_10.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_1_1_10.setBounds(108, 215, 106, 14);
		contentPane.add(lblNewLabel_1_1_10);
		
		lblNewLabel_1_1_11 = new JLabel("APELLIDO Y NOMBRE");
		lblNewLabel_1_1_11.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_1_1_11.setBounds(311, 55, 88, 14);
		contentPane.add(lblNewLabel_1_1_11);
		
		lblNewLabel_1_1_12 = new JLabel("TELEFONO CONTACTO");
		lblNewLabel_1_1_12.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_1_1_12.setBounds(311, 91, 88, 14);
		contentPane.add(lblNewLabel_1_1_12);
		
		PacDerivado = new JCheckBox("¿Ha sido derivado?");
		PacDerivado.setBounds(206, 183, 123, 23);
		contentPane.add(PacDerivado);
		
		PacEstado = new JTextField();
		PacEstado.setColumns(10);
		PacEstado.setBounds(108, 184, 88, 20);
		contentPane.add(PacEstado);
		
		PacEstado123 = new JLabel("ESTADO CIVIL");
		PacEstado123.setFont(new Font("Tahoma", Font.PLAIN, 8));
		PacEstado123.setBounds(108, 169, 88, 14);
		contentPane.add(PacEstado123);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(108, 149, 88, 20);
		contentPane.add(textField);
		
		PacDomici = new JLabel("DOMICILIO");
		PacDomici.setFont(new Font("Tahoma", Font.PLAIN, 8));
		PacDomici.setBounds(108, 134, 88, 14);
		contentPane.add(PacDomici);
		
		PacPsiqui = new JCheckBox("¿Paciente Psiquiatrico?");
		PacPsiqui.setBounds(206, 148, 133, 23);
		contentPane.add(PacPsiqui);
		
		PacEMTelefono = new JTextField();
		PacEMTelefono.setColumns(10);
		PacEMTelefono.setBounds(311, 106, 88, 20);
		contentPane.add(PacEMTelefono);
		
		PacEMNombre = new JTextField();
		PacEMNombre.setColumns(10);
		PacEMNombre.setBounds(311, 70, 88, 20);
		contentPane.add(PacEMNombre);
		
		PacIDObraSocial = new JTextField();
		PacIDObraSocial.setColumns(10);
		PacIDObraSocial.setBounds(108, 230, 88, 20);
		contentPane.add(PacIDObraSocial);
		
		PacOS = new JComboBox();
		PacOS.setModel(new DefaultComboBoxModel(new String[] {"Largavida", "bienestar"}));
		PacOS.setBounds(206, 69, 88, 22);
		contentPane.add(PacOS);
	}
}
