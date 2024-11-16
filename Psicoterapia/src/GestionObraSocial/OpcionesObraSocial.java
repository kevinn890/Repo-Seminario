package GestionObraSocial;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Frames.VentanaPrincipal;
import GestionCitas.BuscarCita;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;
import javax.swing.JTextField;
import java.awt.Color;

public class OpcionesObraSocial extends JFrame {

	private JPanel contentPane;
	private VentanaPrincipal v1;
	private JTable table;
	Prestaciones v3 = new Prestaciones();
	private JTextField idtxt;
	private JTextField nombretxt;
	private JTextField tipotxt;
	private JTextField teltxt;
	
	
	public void setV1(VentanaPrincipal v1) {
		this.v1 = v1;
	}
	
	public void llenarTabla(String id) {
		DefaultTableModel tabla =  (DefaultTableModel) table.getModel();
		tabla.setRowCount(0);
		
		//conexion con la BD
		Conexion.ConexionBD conexion = new Conexion.ConexionBD();
	    Connection con = conexion.conectar();
	    String consulta =null;
	    try {
	    	if(id==null){
	    		consulta = "SELECT idObraSocial, nombre, tipo, telefono FROM obrasocial";
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
	    	PreparedStatement ps1 = con.prepareStatement("SELECT idObraSocial, nombre, tipo, telefono FROM obrasocial WHERE idObraSocial = ?");
		    ps1.setString(1,id);
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
					OpcionesObraSocial frame = new OpcionesObraSocial();
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
	public OpcionesObraSocial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sistema de Gestion y asistencia en sesion Psicoterapeutica");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 401, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Seccion Obra Social");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 45, 141, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnB = new JButton("Agregar");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//conexion con la BD
				Conexion.ConexionBD conexion = new Conexion.ConexionBD();
			    Connection con = conexion.conectar();
				
				
				String nombre = nombretxt.getText();
				String tipo = tipotxt.getText();
				String tel = teltxt.getText();
				
				if(nombre.isEmpty()||tipo.isEmpty()||tel.isEmpty()) {
					JOptionPane.showMessageDialog(null, "debe completar todos los datos");
				}else {
					try {
						String consulta = "INSERT INTO obrasocial ( nombre, tipo, telefono) VALUES ('"+nombre+"','"+tipo+"','"+tel+"')";
						PreparedStatement ps = con.prepareStatement(consulta);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "Obra Social registrada correctamente");
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
		btnB.setBounds(393, 117, 146, 23);
		contentPane.add(btnB);
		
		JButton btnNewButton_1 = new JButton("Modificar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id= idtxt.getText();
				String nombre= nombretxt.getText();
				String tipo = tipotxt.getText();
				String tel = teltxt.getText();
				
				//conexion con la BD
				Conexion.ConexionBD conexion = new Conexion.ConexionBD();
			    Connection con = conexion.conectar();
				
				try {
				    
					String consulta1 = "UPDATE obrasocial SET  nombre='"+nombre+"', tipo='"+tipo+"', telefono='"+tel+"'  WHERE idObraSocial= ?";
					PreparedStatement ps1 = con.prepareStatement(consulta1);
					ps1.setString(1, id);
					ps1.executeUpdate();
					JOptionPane.showMessageDialog(null, "Modificado con exito");
	
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
		btnNewButton_1.setBounds(393, 151, 146, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Eliminar");
		btnNewButton_2.setForeground(Color.RED);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//conexion con la BD
				Conexion.ConexionBD conexion = new Conexion.ConexionBD();
			    Connection con = conexion.conectar();
			    
			    String id= idtxt.getText();
			    
			    if(id.isEmpty()) {
			    	JOptionPane.showMessageDialog(null, "debe escribir un DNI");
			    }else {
			    	int option = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea borrar este registro?");
			    	if(option==0){
			    		try {
							
						    PreparedStatement ps1 = con.prepareStatement("DELETE FROM obrasocial WHERE idObraSocial = ?");
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
			}
		});
		btnNewButton_2.setBounds(393, 185, 146, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Ver prestaciones");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id =null;
				id=idtxt.getText();

				v3.setV1(OpcionesObraSocial.this);
				if(id==null) {
					v3.llenarTabla(null);
				}else {
					v3.llenarTabla(id);
				}
				
				v3.actualizarCombo1();
				
				v3.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_3.setBounds(393, 68, 146, 38);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Atras");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v1.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_4.setBounds(426, 249, 113, 23);
		contentPane.add(btnNewButton_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 241, 204);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//conexion con la BD
				Conexion.ConexionBD conexion = new Conexion.ConexionBD();
			    Connection con = conexion.conectar();
				
				try {
					int fila = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(fila, 0).toString());
					
				    
					String consulta = "SELECT idObraSocial, nombre, tipo, telefono FROM obrasocial WHERE idObraSocial = ?";
					PreparedStatement ps = con.prepareStatement(consulta);
					ps.setInt(1, id);
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()) {
						idtxt.setText(rs.getString(1));
						nombretxt.setText(rs.getString(2));
						tipotxt.setText(rs.getString(3));
						teltxt.setText(rs.getString(4));
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
		table.setModel(new DefaultTableModel(
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
			},
			new String[] {
				"ID", "Nombre", "Tipo", "Telefono"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(85);
		table.getColumnModel().getColumn(2).setPreferredWidth(95);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		scrollPane.setViewportView(table);
		
		idtxt = new JTextField();
		idtxt.setEditable(false);
		idtxt.setBounds(261, 84, 99, 23);
		contentPane.add(idtxt);
		idtxt.setColumns(10);
		
		nombretxt = new JTextField();
		nombretxt.setColumns(10);
		nombretxt.setBounds(261, 141, 99, 23);
		contentPane.add(nombretxt);
		
		tipotxt = new JTextField();
		tipotxt.setColumns(10);
		tipotxt.setBounds(261, 196, 99, 23);
		contentPane.add(tipotxt);
		
		teltxt = new JTextField();
		teltxt.setColumns(10);
		teltxt.setBounds(261, 249, 99, 23);
		contentPane.add(teltxt);
		
		JLabel lblNewLabel_1_1 = new JLabel("ID");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1.setBounds(261, 68, 141, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Nombre");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1_1.setBounds(261, 118, 99, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Tipo");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1_2.setBounds(261, 175, 99, 14);
		contentPane.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Telefono");
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1_3.setBounds(261, 230, 112, 14);
		contentPane.add(lblNewLabel_1_1_3);
	}

}
