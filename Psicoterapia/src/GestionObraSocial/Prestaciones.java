package GestionObraSocial;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Frames.VentanaPrincipal;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class Prestaciones extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private OpcionesObraSocial v1;
	private JComboBox idOs;
	private JTextField portxt;
	private JTextField nombretxt;
	private JTextField idtxt;
	private JComboBox combotxt;
	private int contCombo=0;
	
	
	public void setCombo(int cont) {
		this.contCombo = cont;
	}
	
	
	public void setV1(OpcionesObraSocial v1) {
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
    			    combotxt.addItem(rs.getString(1));
    			    idOs.addItem(rs.getString(1));
    			    contCombo++;
    	    	}
    	    }else {
    	    	while(rs.next()) {
    	    		cont++;
    	    		System.out.println("contador"+cont);
    	    		if(contCombo<cont) {
    	    			combotxt.addItem(rs.getString(1));
        			    idOs.addItem(rs.getString(1));
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
			combotxt.removeAllItems();
			idOs.removeAllItems();
			String consulta = "SELECT nombre FROM obrasocial";
    	    PreparedStatement ps1 = con.prepareStatement(consulta);	
    	    ResultSet rs = ps1.executeQuery();
    	    	while(rs.next()==true) {
    			    combotxt.addItem(rs.getString(1));
    			    idOs.addItem(rs.getString(1));
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
	
	
	public void llenarTabla(String id) {
		DefaultTableModel tabla =  (DefaultTableModel) table.getModel();
		tabla.setRowCount(0);
		
		//conexion con la BD
		Conexion.ConexionBD conexion = new Conexion.ConexionBD();
	    Connection con = conexion.conectar();
	    String consulta =null;
	    try {
	    	if(id==null){
	    		consulta = "SELECT idPrestacion, nombre, porcentajeCobertura, ObraSocial_idObraSocial FROM prestacion";
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
	    	PreparedStatement ps1 = con.prepareStatement("SELECT idPrestacion, nombre, porcentajeCobertura, ObraSocial_idObraSocial FROM prestacion WHERE ObraSocial_idObraSocial = ?");
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
					Prestaciones frame = new Prestaciones();
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
	public Prestaciones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
					
				    
					String consulta = "SELECT idPrestacion, nombre, porcentajeCobertura, ObraSocial_idObraSocial FROM prestacion WHERE idPrestacion = ?";
					PreparedStatement ps = con.prepareStatement(consulta);
					ps.setInt(1, id);
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()) {
						idtxt.setText(rs.getString(1));
						nombretxt.setText(rs.getString(2));
						portxt.setText(rs.getString(3));
						int IDObra = rs.getInt(4);
						IDObra = IDObra-1;
						idOs.setSelectedIndex(IDObra);
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
				"ID", "Nombre", "Porcentaje cobertura", "ID obra social"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Double.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(114);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Seccion Obra Social");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 45, 141, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Sistema de Gestion y asistencia en sesion Psicoterapeutica");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 401, 23);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_3 = new JButton("Filtrar por obra social");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//conexion con la BD
				Conexion.ConexionBD conexion = new Conexion.ConexionBD();
			    Connection con = conexion.conectar();
			    String nombre = combotxt.getSelectedItem().toString();
			    String id=null;
			    
				try {
					PreparedStatement ps1 = con.prepareStatement("SELECT idObraSocial FROM obrasocial WHERE nombre = ?");
				    ps1.setString(1,nombre);
				    ResultSet rs = ps1.executeQuery();
				    
				    while(rs.next()) {
				    	id=rs.getString(1);
				    }
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "No se pudo filtrar"+"  "+ex);
				}finally {
					try {
						llenarTabla(id);
						con.close();
					}catch(Exception exc) {
						JOptionPane.showMessageDialog(null, "No se pudo cerrar conexion con BD"+"  "+exc);
					}
					
				}
				
			}
		});
		btnNewButton_3.setBounds(161, 294, 188, 29);
		contentPane.add(btnNewButton_3);
		
		JButton btnB = new JButton("Agregar");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//conexion con la BD
				Conexion.ConexionBD conexion = new Conexion.ConexionBD();
			    Connection con = conexion.conectar();
				
				
				String nombre = nombretxt.getText();
				String por = portxt.getText();
				String obraSocial = idOs.getSelectedItem().toString();
				String id=null;
				
				if(nombre.isEmpty()||por.isEmpty()||obraSocial.isEmpty()) {
					JOptionPane.showMessageDialog(null, "debe completar todos los datos");
				}else {
					try {
						
						PreparedStatement ps1 = con.prepareStatement("SELECT idObraSocial FROM obrasocial WHERE nombre = ?");
					    ps1.setString(1,obraSocial);
					    ResultSet rs = ps1.executeQuery();
					    
					    while(rs.next()) {
					    	id=rs.getString(1);
					    }
						
						String consulta = "INSERT INTO prestacion ( nombre, porcentajeCobertura, ObraSocial_idObraSocial) VALUES ('"+nombre+"','"+por+"','"+id+"')";
						PreparedStatement ps = con.prepareStatement(consulta);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "Prestacion registrada correctamente");
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
		btnB.setBounds(405, 133, 122, 23);
		contentPane.add(btnB);
		
		JButton btnNewButton_1 = new JButton("Modificar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id= idtxt.getText();
				String nombre= nombretxt.getText();
				String porcentaje = portxt.getText();
				String obrasocial = idOs.getSelectedItem().toString();
				String idOsocial;
				
				//conexion con la BD
				Conexion.ConexionBD conexion = new Conexion.ConexionBD();
			    Connection con = conexion.conectar();
				
				try {
					idOsocial=null;
					PreparedStatement ps = con.prepareStatement("SELECT idObraSocial FROM obrasocial WHERE nombre = ?");
				    ps.setString(1,obrasocial);
				    ResultSet rs = ps.executeQuery();
				    
				    while(rs.next()) {
				    	idOsocial=rs.getString(1);
				    }
				    
					String consulta1 = "UPDATE prestacion SET  nombre='"+nombre+"', porcentajeCobertura='"+porcentaje+"', ObraSocial_idObraSocial='"+idOsocial+"'  WHERE idPrestacion= ?";
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
		btnNewButton_1.setBounds(405, 167, 122, 23);
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
							
						    PreparedStatement ps1 = con.prepareStatement("DELETE FROM prestacion WHERE idPrestacion = ?");
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
		btnNewButton_2.setBounds(405, 201, 122, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("Atras");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v1.setVisible(true);
				v1.llenarTabla(null);
				setVisible(false);
			}
		});
		btnNewButton_4.setBounds(438, 249, 89, 23);
		contentPane.add(btnNewButton_4);
		
		idOs = new JComboBox();
		idOs.setBounds(272, 249, 99, 23);
		contentPane.add(idOs);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Obra Social");
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1_3.setBounds(272, 230, 112, 14);
		contentPane.add(lblNewLabel_1_1_3);
		
		portxt = new JTextField();
		portxt.setColumns(10);
		portxt.setBounds(272, 196, 99, 23);
		contentPane.add(portxt);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Porcentaje cobertura");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1_2.setBounds(272, 175, 112, 14);
		contentPane.add(lblNewLabel_1_1_2);
		
		nombretxt = new JTextField();
		nombretxt.setColumns(10);
		nombretxt.setBounds(272, 141, 99, 23);
		contentPane.add(nombretxt);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Nombre");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1_1.setBounds(272, 118, 99, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		idtxt = new JTextField();
		idtxt.setEditable(false);
		idtxt.setColumns(10);
		idtxt.setBounds(272, 84, 99, 23);
		contentPane.add(idtxt);
		
		JLabel lblNewLabel_1_1 = new JLabel("ID");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1.setBounds(272, 68, 141, 14);
		contentPane.add(lblNewLabel_1_1);
		
		combotxt  = new JComboBox();
		combotxt.setBounds(10, 294, 141, 29);
		contentPane.add(combotxt);
		
		JButton btnNewButton_3_1 = new JButton("Quitar filtro");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTabla(null);
			}
		});
		btnNewButton_3_1.setBounds(369, 294, 158, 29);
		contentPane.add(btnNewButton_3_1);
	}
}
