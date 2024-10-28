package Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

import GestionCitas.OpcionesCitas;
import GestionObraSocial.OpcionesObraSocial;
import GestionPaciente.OpcionesPacientes;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	OpcionesPacientes v2 = new OpcionesPacientes();
	OpcionesCitas v3 = new OpcionesCitas();
	OpcionesObraSocial v4 = new OpcionesObraSocial();
	OpcionesSesion v5 = new OpcionesSesion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
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
		
		JLabel lblNewLabel_1 = new JLabel("Bienvenido seleccione una seccion");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 76, 258, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnGestionDePacientes = new JButton("Gestion de pacientes");
		btnGestionDePacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				v2.setV1(VentanaPrincipal.this);
				v2.setVisible(true);
				setVisible(false);
			}
		});
		btnGestionDePacientes.setBounds(10, 109, 207, 23);
		contentPane.add(btnGestionDePacientes);
		
		JButton btnNewButton_1 = new JButton("Gestion de citas");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v3.setV1(VentanaPrincipal.this);
				v3.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(10, 143, 207, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Gestion de obras sociales");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v4.setV1(VentanaPrincipal.this);
				v4.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_2.setBounds(10, 177, 207, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Gestion de sesiones");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v5.setV1(VentanaPrincipal.this);
				v5.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_3.setBounds(10, 211, 207, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Salir");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_4.setBounds(322, 227, 89, 23);
		contentPane.add(btnNewButton_4);
	}

}
