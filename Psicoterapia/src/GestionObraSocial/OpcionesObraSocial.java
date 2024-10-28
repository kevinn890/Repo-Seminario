package GestionObraSocial;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Frames.VentanaPrincipal;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OpcionesObraSocial extends JFrame {

	private JPanel contentPane;
	private VentanaPrincipal v1;

	
	
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
		setBounds(100, 100, 450, 300);
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
		
		JLabel lblNewLabel_2 = new JLabel("Seleccione una opcion");
		lblNewLabel_2.setBounds(10, 75, 165, 23);
		contentPane.add(lblNewLabel_2);
		
		JButton btnB = new JButton("b1");
		btnB.setBounds(10, 109, 207, 23);
		contentPane.add(btnB);
		
		JButton btnNewButton_1 = new JButton("b2");
		btnNewButton_1.setBounds(10, 143, 207, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("b3");
		btnNewButton_2.setBounds(10, 177, 207, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("b4");
		btnNewButton_3.setBounds(10, 211, 207, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Atras");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v1.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_4.setBounds(322, 227, 89, 23);
		contentPane.add(btnNewButton_4);
	}

}
