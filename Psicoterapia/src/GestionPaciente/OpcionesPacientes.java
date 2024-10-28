package GestionPaciente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Frames.VentanaPrincipal;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

public class OpcionesPacientes extends JFrame {

	private JPanel contentPane;
	private VentanaPrincipal v1;
	RegistroPacientes1 v2 = new RegistroPacientes1();
	BuscarPaciente v3 = new BuscarPaciente();
	ModificarPaciente v4 = new ModificarPaciente();
	EliminarPaciente v5 = new EliminarPaciente();

	
	
	public void setV1(VentanaPrincipal v1) {
		this.v1 = v1;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpcionesPacientes frame = new OpcionesPacientes();
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
	public OpcionesPacientes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Registrar paciente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v2.setV1(OpcionesPacientes.this);
				v2.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(23, 109, 207, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Buscar paciente");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v3.setV1(OpcionesPacientes.this);
				v3.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(23, 143, 207, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Modificar datos de paciente");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v4.setV1(OpcionesPacientes.this);
				v4.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_2.setBounds(23, 177, 207, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Eliminar paciente");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v5.setV1(OpcionesPacientes.this);
				v5.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_3.setBounds(23, 211, 207, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Atras");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v1.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_4.setBounds(335, 227, 89, 23);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel = new JLabel("Sistema de Gestion y asistencia en sesion Psicoterapeutica");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(23, 11, 401, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Seccion Pacientes");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(23, 45, 141, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Seleccione una opcion");
		lblNewLabel_2.setBounds(23, 75, 165, 23);
		contentPane.add(lblNewLabel_2);
	}
	
}
