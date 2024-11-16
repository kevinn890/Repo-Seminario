package GestionCitas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Frames.VentanaPrincipal;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.toedter.calendar.JDateChooser;

public class BuscarCita extends JFrame {

	private JPanel contentPane;
	private VentanaPrincipal v1;
	private JTable table;
	private JTextField dnibuscartxt;
	private JTable table_1;
	private JTextField idtxt;
	private JTextField dnitxt;
	private JTextField proftxt;
	private JDateChooser fechatxt;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JButton btnNewButton;
	private JButton btnNewButton_4_2;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	
	public void setV1(VentanaPrincipal v1) {
		this.v1 = v1;
	}
	
	public void llenarTabla(String dni) {
		DefaultTableModel tabla =  (DefaultTableModel) table_1.getModel();
		tabla.setRowCount(0);
		
		//conexion con la BD
		Conexion.ConexionBD conexion = new Conexion.ConexionBD();
	    Connection con = conexion.conectar();
	    String consulta =null;
	    try {
	    	if(dni==null){
	    		consulta = "SELECT idCita, Paciente_dni, profesional, fechaYHora FROM cita";
	    	    PreparedStatement ps1 = con.prepareStatement(consulta);	
	    	    ResultSet rs = ps1.executeQuery();
	    		ResultSetMetaData rsmd = rs.getMetaData();
				int col = rsmd.getColumnCount();
				while(rs.next()) {
					Object[] fila = new Object[col];
					for(int i = 0; i<col; i++) {
						fila[i]= rs.getObject(i+1);
					}
					tabla.addRow(fila);
				}
	    	}else {
	    	PreparedStatement ps1 = con.prepareStatement("SELECT idCita, Paciente_dni, profesional, fechaYHora FROM cita WHERE Paciente_dni = ?");
		    ps1.setString(1,dni);
		    ResultSet rs = ps1.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int col = rsmd.getColumnCount();
			while(rs.next()) {
				Object[] fila = new Object[col];
				for(int i = 0; i<col; i++) {
					fila[i]= rs.getObject(i+1);
				}
				tabla.addRow(fila);
			}
	    	}
		
	    }catch(Exception ex) {
	    	JOptionPane.showMessageDialog(null, "No se pudo cargar la tabla"+ex);
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
					BuscarCita frame = new BuscarCita();
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
	public BuscarCita() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 576, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sistema de Gestion y asistencia en sesion Psicoterapeutica");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 401, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Buscar citas por DNI");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 58, 141, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_4 = new JButton("Atras");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				v1.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_4.setBounds(437, 318, 89, 23);
		contentPane.add(btnNewButton_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 141, 339, 200);
		contentPane.add(scrollPane);
		
		
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//conexion con la BD
				Conexion.ConexionBD conexion = new Conexion.ConexionBD();
			    Connection con = conexion.conectar();
				
				try {
					int fila = table_1.getSelectedRow();
					int id = Integer.parseInt(table_1.getValueAt(fila, 0).toString());
					
				    
					String consulta = "SELECT  idCita, Paciente_dni, profesional, fechaYHora FROM cita WHERE idCita= ?";
					PreparedStatement ps = con.prepareStatement(consulta);
					ps.setInt(1, id);
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()) {
						idtxt.setText(rs.getString(1));
						dnitxt.setText(rs.getString(2));
						proftxt.setText(rs.getString(3));
						Date fechaNacimiento = rs.getTime(4);
						fechatxt.setDate(fechaNacimiento);
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "No se encontraron los datos"+"  "+ex);
				}finally {
					try {
						con.close();
					}catch(Exception exc) {
						JOptionPane.showMessageDialog(null, "No se pudo cerrar conexion con BD"+"  "+exc);
					}
				}
			}
			
	
		});
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"ID", "DNI paciente", "Profesional", "Fecha y hora"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_1.getColumnModel().getColumn(0).setPreferredWidth(57);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(90);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(120);
		scrollPane.setViewportView(table_1);
		
		dnibuscartxt = new JTextField();
		dnibuscartxt.setColumns(10);
		dnibuscartxt.setBounds(10, 108, 88, 20);
		contentPane.add(dnibuscartxt);
		
		JButton btnNewButton_4_1 = new JButton("Filtrar");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = dnibuscartxt.getText();
				llenarTabla(dni);
			}
		});
		btnNewButton_4_1.setBounds(108, 107, 89, 23);
		contentPane.add(btnNewButton_4_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Ingrese el DNI y presione filtrar");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_2.setBounds(10, 83, 204, 14);
		contentPane.add(lblNewLabel_1_2);
		
		idtxt = new JTextField();
		idtxt.setEditable(false);
		idtxt.setColumns(10);
		idtxt.setBounds(438, 104, 88, 20);
		contentPane.add(idtxt);
		
		dnitxt = new JTextField();
		dnitxt.setColumns(10);
		dnitxt.setBounds(438, 135, 88, 20);
		contentPane.add(dnitxt);
		
		proftxt = new JTextField();
		proftxt.setColumns(10);
		proftxt.setBounds(438, 166, 88, 20);
		contentPane.add(proftxt);
		
		fechatxt = new JDateChooser();
		fechatxt.setDateFormatString("yyyy-MM-dd HH:mm:ss");
		fechatxt.setBounds(438, 197, 88, 20);
		contentPane.add(fechatxt);
		
		lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setBounds(359, 111, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("DNI paciente");
		lblNewLabel_3.setBounds(359, 141, 69, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("profesional");
		lblNewLabel_4.setBounds(359, 169, 69, 14);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("fecha y hora");
		lblNewLabel_5.setBounds(359, 200, 69, 14);
		contentPane.add(lblNewLabel_5);
		
		btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//conexion con la BD
				Conexion.ConexionBD conexion = new Conexion.ConexionBD();
			    Connection con = conexion.conectar();
				
			    int citaActiva= 1;
				String dni = dnitxt.getText();
				String prof = proftxt.getText();
				String fecha = ((JTextField)fechatxt.getDateEditor().getUiComponent()).getText();
				
				if(dni.isEmpty()||prof.isEmpty()||fecha.isEmpty()) {
					JOptionPane.showMessageDialog(null, "debe completar todos los datos");
				}else {
					try {
						String consulta = "INSERT INTO cita ( Paciente_dni, fechaYHora, profesional, citaActiva) VALUES ('"+dni+"','"+fecha+"','"+prof+"','"+citaActiva+"')";
						PreparedStatement ps = con.prepareStatement(consulta);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "Cita registrada correctamente");
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "No se pudo realizar el registro"+"  "+ex);
					}finally {
						try {
							llenarTabla(null);
							con.close();
						}catch(Exception exc) {
							JOptionPane.showMessageDialog(null, "No se pudo cerrar la conexion con la BD"+"  "+exc);
						}
					}
					
				}
				
			}
		});
		btnNewButton.setBounds(437, 226, 89, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_4_2 = new JButton("Quitar filtro");
		btnNewButton_4_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTabla(null);
			}
		});
		btnNewButton_4_2.setBounds(207, 107, 89, 23);
		contentPane.add(btnNewButton_4_2);
		
		btnNewButton_1 = new JButton("Modificar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id= idtxt.getText();
				String dni= dnitxt.getText();
				String prof = proftxt.getText();
				String fecha = ((JTextField)fechatxt.getDateEditor().getUiComponent()).getText();
				int Active = 1;
				
				//conexion con la BD
				Conexion.ConexionBD conexion = new Conexion.ConexionBD();
			    Connection con = conexion.conectar();
				
				try {
				    
					String consulta1 = "UPDATE cita SET  Paciente_dni='"+dni+"', fechaYHora='"+fecha+"',citaActiva='"+Active+"',profesional='"+prof+"'  WHERE idCita= ?";
					PreparedStatement ps1 = con.prepareStatement(consulta1);
					ps1.setString(1, id);
					ps1.executeUpdate();
					JOptionPane.showMessageDialog(null, "Registro modificado con exito");
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "No se pudo modificar Registro"+"  "+ex);
				}finally {
					try {
						con.close();
						llenarTabla(null);
					}catch(Exception exc) {
						JOptionPane.showMessageDialog(null, "No se pudo cerrar conexion con BD"+"  "+exc);
					}
				}
				
				
			}
		});
		btnNewButton_1.setBounds(437, 260, 89, 23);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Borrar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//conexion con la BD
				Conexion.ConexionBD conexion = new Conexion.ConexionBD();
			    Connection con = conexion.conectar();
			    
			    String id = idtxt.getText();
			    

			    	int option = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea borrar este registro?");
			    	if(option==0){
			    		try {
							
						    PreparedStatement ps1 = con.prepareStatement("DELETE FROM cita WHERE idCita= ?");
						    ps1.setString(1,id);
							ps1.executeUpdate();
							JOptionPane.showMessageDialog(null, "Registro borrado con exito");
					
				            }catch(Exception ex) {
				            	JOptionPane.showMessageDialog(null, "No se pudo borrar registro");
				            }finally {
					    		try {
					    			llenarTabla(null);
					    			con.close();
					    		}catch(Exception exc) {
					    			JOptionPane.showMessageDialog(null, "No se pudo cerrar conexion"+"  "+exc);
					    		}
					    	}
			    	}
			}
		});
		btnNewButton_2.setBounds(437, 289, 89, 23);
		contentPane.add(btnNewButton_2);
	}
}
