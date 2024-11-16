package GestionPaciente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GrupoConviviente extends JFrame {

	private JPanel contentPane;
	private BuscarPaciente v1;
	private JTable table;
	private JTextField rel;
	private JTextField nom;
	private JTextField edad;
	private String dni;
	private String idGrupo;
	
	
	public void setID(String ID) {
		this.idGrupo = ID;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public void setV1(BuscarPaciente v1) {
		this.v1 = v1;
	}
	
	public void llenarTabla(String dni) {
		DefaultTableModel tabla =  (DefaultTableModel) table.getModel();
		tabla.setRowCount(0);
		
		//conexion con la BD
		Conexion.ConexionBD conexion = new Conexion.ConexionBD();
	    Connection con = conexion.conectar();
	    
	    try {
	    	PreparedStatement ps1 = con.prepareStatement("SELECT idmiembro,Relacion,nombre,edad FROM grupoconviviente WHERE Paciente_dni = ?");
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
					GrupoConviviente frame = new GrupoConviviente();
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
	public GrupoConviviente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_4 = new JButton("Atras");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v1.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_4.setBounds(384, 276, 140, 34);
		contentPane.add(btnNewButton_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 36, 314, 253);
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
					
				    
					String consulta = "SELECT  idmiembro, Relacion, nombre, edad FROM grupoconviviente WHERE idmiembro= ?";
					PreparedStatement ps = con.prepareStatement(consulta);
					ps.setInt(1, id);
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()) {
						setID(rs.getString(1));
						rel.setText(rs.getString(2));
						nom.setText(rs.getString(3));
						edad.setText(rs.getString(4));
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
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"ID", "Relacion", "Nombre y Apellido", "Edad"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Grupo conviviente");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(28, 11, 187, 14);
		contentPane.add(lblNewLabel);
		
		rel = new JTextField();
		rel.setBounds(384, 30, 131, 20);
		contentPane.add(rel);
		rel.setColumns(10);
		
		nom = new JTextField();
		nom.setColumns(10);
		nom.setBounds(384, 75, 131, 20);
		contentPane.add(nom);
		
		edad = new JTextField();
		edad.setColumns(10);
		edad.setBounds(384, 117, 131, 20);
		contentPane.add(edad);
		
		JButton btnNewButton_4_1 = new JButton("Borrar");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//conexion con la BD
				Conexion.ConexionBD conexion = new Conexion.ConexionBD();
			    Connection con = conexion.conectar();
			    
			    String idMiembro = idGrupo;
			    

			    	int option = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea borrar este registro?");
			    	if(option==0){
			    		try {
							
						    PreparedStatement ps1 = con.prepareStatement("DELETE FROM grupoconviviente WHERE idmiembro= ?");
						    ps1.setString(1,idMiembro);
							ps1.executeUpdate();
							JOptionPane.showMessageDialog(null, "Registro borrado con exito");
					
				            }catch(Exception ex) {
				            	JOptionPane.showMessageDialog(null, "No se pudo borrar registro");
				            }finally {
					    		try {
					    			llenarTabla(dni);
					    			con.close();
					    		}catch(Exception exc) {
					    			JOptionPane.showMessageDialog(null, "No se pudo cerrar conexion"+"  "+exc);
					    		}
					    	}
			    	}
			    	
			    
				
			}
		});
		btnNewButton_4_1.setBounds(384, 237, 140, 28);
		contentPane.add(btnNewButton_4_1);
		
		JButton btnNewButton_4_2 = new JButton("Modificar");
		btnNewButton_4_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String relacion= rel.getText();
				String nombre= nom.getText();
				String edades = edad.getText();
				String id = null;
				
				//conexion con la BD
				Conexion.ConexionBD conexion = new Conexion.ConexionBD();
			    Connection con = conexion.conectar();
				
				try {
					
				
				    String idMiembro = idGrupo;
				    
					String consulta1 = "UPDATE grupoconviviente SET   Relacion='"+relacion+"', nombre='"+nombre+"', edad='"+edades+"'  WHERE idmiembro= ?";
					PreparedStatement ps1 = con.prepareStatement(consulta1);
					ps1.setString(1, idMiembro);
					ps1.executeUpdate();
	
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "No se pudo modificar Registro"+"  "+ex);
				}finally {
					try {
						con.close();
						llenarTabla(dni);
					}catch(Exception exc) {
						JOptionPane.showMessageDialog(null, "No se pudo cerrar conexion con BD"+"  "+exc);
					}
				}
			}
		});
		btnNewButton_4_2.setBounds(384, 198, 140, 28);
		contentPane.add(btnNewButton_4_2);
		
		JButton btnNewButton_4_2_1 = new JButton("Agregar");
		btnNewButton_4_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//conexion con la BD
				Conexion.ConexionBD conexion = new Conexion.ConexionBD();
			    Connection con = conexion.conectar();
			    
			    String relacion=rel.getText();
			    String nombre=nom.getText();
			    String ed = edad.getText();
			    
			    if(relacion.isEmpty()||nombre.isEmpty()||ed.isEmpty()) {
			    	JOptionPane.showMessageDialog(null, "debe completar todos los datos");
			    }else {
			    	try {
			    		String consulta = "INSERT INTO grupoconviviente (Paciente_dni, Relacion, nombre, edad) VALUES ('"+dni+"', '"+relacion+"', '"+nombre+"', '"+ed+"')";
						PreparedStatement ps = con.prepareStatement(consulta);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "Conviviente Registrado correctamente");
			    	}catch(Exception ex) {
			    		JOptionPane.showMessageDialog(null, "No se pudo agregar Registro"+"  "+ex);
			    	}finally {
			    		try {
			    			llenarTabla(dni);
			    			con.close();
			    		}catch(Exception exc) {
			    			JOptionPane.showMessageDialog(null, "No se pudo cerrar conexion con BD"+"  "+exc);
			    		}
			    	}
			    }
			}
		});
		btnNewButton_4_2_1.setBounds(384, 159, 140, 28);
		contentPane.add(btnNewButton_4_2_1);
		
		JLabel lblNewLabel_1 = new JLabel("Relacion");
		lblNewLabel_1.setBounds(384, 13, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre y Apellido");
		lblNewLabel_1_1.setBounds(384, 61, 131, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Edad");
		lblNewLabel_1_2.setBounds(384, 102, 46, 14);
		contentPane.add(lblNewLabel_1_2);
	}
}
