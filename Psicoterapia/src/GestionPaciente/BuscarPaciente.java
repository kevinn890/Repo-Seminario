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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

public class BuscarPaciente extends JFrame {

	private JPanel contentPane;
    private OpcionesPacientes v1;
    private JTextField busContNom;
    private JTextField busContTel;
    private JTextField busEmail;
    private JTextField busDni;
    private JTextField busTel;
    private JTextField busCuil;
    private JTextField busApe;
    private JTextField busDomi;
    private JTextField busCivil;
    private JTextField busNom;
    private JTextField busDiag;
    private JTextField dniBuscar;
    private JCheckBox busDerivado;
    private JCheckBox busPsiqui;
    private JDateChooser busNacimiento;
    private JComboBox busOS;
    private int contCombo=0;
    
    GrupoConviviente v2 = new GrupoConviviente();
    
	public void setV1(OpcionesPacientes v1) {
		this.v1 = v1;
	}
	
	public void actualizarCombo() {
		
		//conexion con la BD
		Conexion.ConexionBD conexion = new Conexion.ConexionBD();
	    Connection con = conexion.conectar();
		
		try {
			String consulta = "SELECT nombre FROM obrasocial";
    	    PreparedStatement ps1 = con.prepareStatement(consulta);	
    	    ResultSet rs = ps1.executeQuery();
    	    int cont=0;
    	    if(contCombo==0) {
    	    	while(rs.next()==true) {
    			    busOS.addItem(rs.getString(1));
    			    contCombo++;
    	    	}
    	    }else {
    	    	while(rs.next()) {
    	    		cont++;
    	    		System.out.println("contador"+cont);
    	    		if(contCombo<cont) {
    	    			busOS.addItem(rs.getString(1));
        			    contCombo++;
    	    		}
    	    	}
    		
    		}
			
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "No se pudo actualizar combo"+ex);
		}finally {
			try {
				con.close();
			}catch(Exception exc) {
				JOptionPane.showMessageDialog(null, "No se pudo cerrar la conexion"+exc);
			}
		}
		
		
	}
	
	
public void actualizarCombo1() {
		
		//conexion con la BD
		Conexion.ConexionBD conexion = new Conexion.ConexionBD();
	    Connection con = conexion.conectar();
		
		try {
			busOS.removeAllItems();
			String consulta = "SELECT nombre FROM obrasocial";
    	    PreparedStatement ps1 = con.prepareStatement(consulta);	
    	    ResultSet rs = ps1.executeQuery();
    	    	while(rs.next()==true) {
    			    busOS.addItem(rs.getString(1));
    	    	}
			
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "No se pudo actualizar combo"+ex);
		}finally {
			try {
				con.close();
			}catch(Exception exc) {
				JOptionPane.showMessageDialog(null, "No se pudo cerrar la conexion"+exc);
			}
		}
		
		
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarPaciente frame = new BuscarPaciente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public boolean verificarCheck(String a) {
		if(a.equals("0")) {
			return false;
		}else {
			return true;
		}
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
	public BuscarPaciente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sistema de Gestion y asistencia en sesion Psicoterapeutica");
		lblNewLabel.setBounds(20, 11, 401, 23);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_4 = new JButton("Atras");
		btnNewButton_4.setBounds(419, 330, 155, 34);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v1.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel_1 = new JLabel("Buscar Paciente");
		lblNewLabel_1.setBounds(20, 45, 141, 14);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Contacto Emergencia");
		lblNewLabel_1_1_1_1.setBounds(321, 150, 113, 14);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNewLabel_1_1_1_1);
		
		busContNom = new JTextField();
		busContNom.setBounds(321, 184, 88, 20);
		busContNom.setColumns(10);
		contentPane.add(busContNom);
		
		JLabel lblNewLabel_1_1_11 = new JLabel("APELLIDO Y NOMBRE");
		lblNewLabel_1_1_11.setBounds(321, 169, 88, 14);
		lblNewLabel_1_1_11.setFont(new Font("Tahoma", Font.PLAIN, 8));
		contentPane.add(lblNewLabel_1_1_11);
		
		JLabel lblNewLabel_1_1_12 = new JLabel("TELEFONO CONTACTO");
		lblNewLabel_1_1_12.setBounds(321, 205, 88, 14);
		lblNewLabel_1_1_12.setFont(new Font("Tahoma", Font.PLAIN, 8));
		contentPane.add(lblNewLabel_1_1_12);
		
		busContTel = new JTextField();
		busContTel.setBounds(321, 220, 88, 20);
		busContTel.setColumns(10);
		contentPane.add(busContTel);
		
		JButton btnNewButton_1 = new JButton("Agregar Grupo conviviente");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				v2.setDni(dniBuscar.getText());
				v2.llenarTabla(dniBuscar.getText());
				v2.setV1(BuscarPaciente.this);
				v2.setVisible(true);
				setVisible(false);

			}
		});
		btnNewButton_1.setBounds(397, 285, 177, 34);
		contentPane.add(btnNewButton_1);
		
		JButton btnModificarDatosDel = new JButton("Modificar datos");
		btnModificarDatosDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//conexion con la BD
				Conexion.ConexionBD conexion = new Conexion.ConexionBD();
			    Connection con = conexion.conectar();
				
				String nombre = busNom.getText();
				String apellido = busApe.getText();
				String email = busEmail.getText();
				String cuil = busCuil.getText();
				String dni = busDni.getText();
				String telefono = busTel.getText();
				String domicilio = busDomi.getText();
				String nacimiento = ((JTextField)busNacimiento.getDateEditor().getUiComponent()).getText();
				int psiqui = valorCheck(busPsiqui);
				int deri = valorCheck(busDerivado);
				String emerNombre = busContNom.getText();
				String emerTelefono = busContTel.getText(); 
				String estadoCivil = busCivil.getText();
				String diagnostico = busDiag.getText();
				int IDObrasocial=0;
				String obraSocial=busOS.getSelectedItem().toString();
				
				try {
					    PreparedStatement ps1 = con.prepareStatement("SELECT idObraSocial FROM obrasocial WHERE nombre LIKE ?");
					    ps1.setString(1,obraSocial);
						ResultSet rs = ps1.executeQuery();
						while(rs.next()==true) {
							IDObrasocial = rs.getInt(1);
						}
				}catch(Exception exce) {
					JOptionPane.showMessageDialog(null, "No se encontro id obra social");
				}
	
				
				try {
					String consulta = "UPDATE paciente SET  cuil= '"+cuil+"', nombre= '"+nombre+"', apellido= '"+apellido+"', email= '"+email+"', telefono= '"+telefono+"', domicilio= '"+domicilio+"', estadoCivil= '"+estadoCivil+"', pacientePsiquiatrico= '"+psiqui+"', pacienteDerivado= '"+deri+"', diagnosticoPresuntivo= '"+diagnostico+"', contEmerNombre= '"+emerNombre+"', contEmerTelefono= '"+emerTelefono+"', ObraSocial_idObraSocial= '"+IDObrasocial+"',fechaNacimiento= '"+nacimiento+"' WHERE dni = ?";
					PreparedStatement ps = con.prepareStatement(consulta);
					ps.setString(1, dni);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "datos actualizados correctamente");
				}catch(Exception exc) {
					JOptionPane.showMessageDialog(null, "No se pudieron actualizar los datos"+"  "+ exc);
				}finally {
		    		try {
		    			con.close();
		    		}catch(Exception exc) {
		    			JOptionPane.showMessageDialog(null, "No se pudo cerrar conexion"+"  "+exc);
		    		}
		    	}
				
				
			}
		});
		btnModificarDatosDel.setBounds(421, 194, 153, 34);
		contentPane.add(btnModificarDatosDel);
		
		busDerivado = new JCheckBox("¿Ha sido derivado?");
		busDerivado.setBounds(216, 297, 123, 23);
		contentPane.add(busDerivado);
		
		busPsiqui = new JCheckBox("¿Paciente Psiquiatrico?");
		busPsiqui.setBounds(216, 262, 133, 23);
		contentPane.add(busPsiqui);
		
		busOS = new JComboBox();
		busOS.setBounds(216, 183, 88, 22);
		contentPane.add(busOS);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Datos Obra Social");
		lblNewLabel_1_1_1.setBounds(216, 150, 98, 14);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNewLabel_1_1_1);
		
		busEmail = new JTextField();
		busEmail.setBounds(118, 184, 88, 20);
		busEmail.setColumns(10);
		contentPane.add(busEmail);
		
		JLabel lblNewLabel_1_1_7 = new JLabel("EMAIL");
		lblNewLabel_1_1_7.setBounds(118, 169, 88, 14);
		lblNewLabel_1_1_7.setFont(new Font("Tahoma", Font.PLAIN, 8));
		contentPane.add(lblNewLabel_1_1_7);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("DNI");
		lblNewLabel_1_1_2.setBounds(20, 169, 88, 14);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 8));
		contentPane.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Datos personales");
		lblNewLabel_1_1.setBounds(20, 150, 98, 14);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNewLabel_1_1);
		
		busDni = new JTextField();
		busDni.setBounds(20, 184, 88, 20);
		busDni.setColumns(10);
		contentPane.add(busDni);
		
		busTel = new JTextField();
		busTel.setBounds(118, 220, 88, 20);
		busTel.setColumns(10);
		contentPane.add(busTel);
		
		busCuil = new JTextField();
		busCuil.setBounds(20, 220, 88, 20);
		busCuil.setColumns(10);
		contentPane.add(busCuil);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("CUIL");
		lblNewLabel_1_1_3.setBounds(20, 205, 88, 14);
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 8));
		contentPane.add(lblNewLabel_1_1_3);
		
		JLabel lblNewLabel_1_1_8 = new JLabel("TELEFONO");
		lblNewLabel_1_1_8.setBounds(118, 205, 88, 14);
		lblNewLabel_1_1_8.setFont(new Font("Tahoma", Font.PLAIN, 8));
		contentPane.add(lblNewLabel_1_1_8);
		
		JLabel lblNewLabel_1_1_4 = new JLabel("APELLIDO");
		lblNewLabel_1_1_4.setBounds(20, 248, 88, 14);
		lblNewLabel_1_1_4.setFont(new Font("Tahoma", Font.PLAIN, 8));
		contentPane.add(lblNewLabel_1_1_4);
		
		JLabel PacDomicili = new JLabel("DOMICILIO");
		PacDomicili.setBounds(118, 248, 88, 14);
		PacDomicili.setFont(new Font("Tahoma", Font.PLAIN, 8));
		contentPane.add(PacDomicili);
		
		busApe = new JTextField();
		busApe.setBounds(20, 263, 88, 20);
		busApe.setColumns(10);
		contentPane.add(busApe);
		
		busDomi = new JTextField();
		busDomi.setBounds(118, 263, 88, 20);
		busDomi.setColumns(10);
		contentPane.add(busDomi);
		
		JLabel PacEstado123 = new JLabel("ESTADO CIVIL");
		PacEstado123.setBounds(118, 283, 88, 14);
		PacEstado123.setFont(new Font("Tahoma", Font.PLAIN, 8));
		contentPane.add(PacEstado123);
		
		JLabel lblNewLabel_1_1_5 = new JLabel("NOMBRES");
		lblNewLabel_1_1_5.setBounds(20, 283, 88, 14);
		lblNewLabel_1_1_5.setFont(new Font("Tahoma", Font.PLAIN, 8));
		contentPane.add(lblNewLabel_1_1_5);
		
		busCivil = new JTextField();
		busCivil.setBounds(118, 298, 88, 20);
		busCivil.setColumns(10);
		contentPane.add(busCivil);
		
		busNom = new JTextField();
		busNom.setBounds(20, 298, 88, 20);
		busNom.setColumns(10);
		contentPane.add(busNom);
		
		JLabel lblNewLabel_1_1_6 = new JLabel("FECHA DE NACIMIENTO");
		lblNewLabel_1_1_6.setBounds(20, 329, 88, 14);
		lblNewLabel_1_1_6.setFont(new Font("Tahoma", Font.PLAIN, 8));
		contentPane.add(lblNewLabel_1_1_6);
		
		busNacimiento = new JDateChooser();
		busNacimiento.setBounds(20, 344, 88, 20);
		busNacimiento.setDateFormatString("yyyy-MM-dd");
		contentPane.add(busNacimiento);
		
		busDiag = new JTextField();
		busDiag.setBounds(118, 344, 88, 20);
		busDiag.setColumns(10);
		contentPane.add(busDiag);
		
		JLabel lblNewLabel_1_1_10 = new JLabel("DIAGNOSTICO PRESUNTIVO");
		lblNewLabel_1_1_10.setBounds(118, 329, 106, 14);
		lblNewLabel_1_1_10.setFont(new Font("Tahoma", Font.PLAIN, 8));
		contentPane.add(lblNewLabel_1_1_10);
		
		dniBuscar = new JTextField();
		dniBuscar.setColumns(10);
		dniBuscar.setBounds(20, 85, 88, 20);
		contentPane.add(dniBuscar);
		
		JButton btnNewButton_4_1 = new JButton("Buscar");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//conexion con la BD
				Conexion.ConexionBD conexion = new Conexion.ConexionBD();
			    Connection con = conexion.conectar();
			    
			    String dni= dniBuscar.getText();
			    
			    if(dni.isEmpty()) {
			    	JOptionPane.showMessageDialog(null, "debe escribir un DNI");
			    }else {
			    	try {
						
					    PreparedStatement ps1 = con.prepareStatement("SELECT* FROM paciente WHERE dni = ?");
					    ps1.setString(1,dni);
						ResultSet rs = ps1.executeQuery();
						
						
						List<String> datos = new ArrayList<>();
						int IDObra=0;
						java.util.Date fechaNacimiento =null;
						
						while(rs.next()==true) {
							IDObra = rs.getInt(14);
							fechaNacimiento = rs.getDate(15);
							for(int i=1;i<15;i++ ) {
								String obtenido = rs.getString(i);
								datos.add(obtenido) ;
							}
						}
						
						busDni.setText(dni);
						busCuil.setText(datos.get(1));
						busNom.setText(datos.get(2));
						busApe.setText(datos.get(3));
						busEmail.setText(datos.get(4));
						busTel.setText(datos.get(5));
						busDomi.setText(datos.get(6));
						busCivil.setText(datos.get(7));
						busPsiqui.setSelected(verificarCheck(datos.get(8)));
						busDerivado.setSelected(verificarCheck(datos.get(9)));
						busDiag.setText(datos.get(10));
						busContNom.setText(datos.get(11));
						busContTel.setText(datos.get(12));
						IDObra = IDObra -1;
						busOS.setSelectedIndex(IDObra);
						busNacimiento.setDate(fechaNacimiento);
						
			    	}catch(Exception ex) {
			    		JOptionPane.showMessageDialog(null, "No se encontro dni buscado"+"  "+ex);
			    	}finally {
			    		try {
			    			con.close();
			    		}catch(Exception exc) {
			    			JOptionPane.showMessageDialog(null, "No se pudo cerrar conexion"+"  "+exc);
			    		}
			    	}
			    }
			}
		});
		
		
		
		btnNewButton_4_1.setBounds(118, 84, 89, 23);
		contentPane.add(btnNewButton_4_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Ingrese el DNI y presione buscar");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_2.setBounds(20, 60, 204, 14);
		contentPane.add(lblNewLabel_1_2);
		
		JButton btnEliminarPaciente = new JButton("Eliminar paciente");
		btnEliminarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//conexion con la BD
				Conexion.ConexionBD conexion = new Conexion.ConexionBD();
			    Connection con = conexion.conectar();
			    
			    String dni= dniBuscar.getText();
			    
			    if(dni.isEmpty()) {
			    	JOptionPane.showMessageDialog(null, "debe escribir un DNI");
			    }else {
			    	int option = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea borrar este registro?");
			    	if(option==0){
			    		try {
							
						    PreparedStatement ps1 = con.prepareStatement("DELETE FROM paciente WHERE dni = ?");
						    ps1.setString(1,dni);
							ps1.executeUpdate();
							JOptionPane.showMessageDialog(null, "Registro borrado con exito");
					
				            }catch(Exception ex) {
				            	JOptionPane.showMessageDialog(null, "No se pudo borrar registro");
				            }finally {
					    		try {
					    			con.close();
					    		}catch(Exception exc) {
					    			JOptionPane.showMessageDialog(null, "No se pudo cerrar conexion"+"  "+exc);
					    		}
					    	}
			    	}
			    	
			    }
			 }
		});
		btnEliminarPaciente.setForeground(new Color(255, 0, 0));
		btnEliminarPaciente.setBounds(421, 240, 153, 34);
		contentPane.add(btnEliminarPaciente);
	}
}
