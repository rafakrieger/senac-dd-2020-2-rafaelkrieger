package br.com.senac.vacinas.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class CadastroVacina extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroVacina frame = new CadastroVacina();
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
	public CadastroVacina() {
		setTitle("Cadastro de vacinas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 350);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPais = new JLabel("PAÍS DE ORIGEM");
		lblPais.setForeground(Color.DARK_GRAY);
		lblPais.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPais.setBounds(10, 0, 134, 25);
		contentPane.add(lblPais);
		
		JComboBox comboBoxPais = new JComboBox();
		comboBoxPais.setFont(new Font("Dialog", Font.PLAIN, 14));
		comboBoxPais.setBounds(10, 25, 294, 30);
		contentPane.add(comboBoxPais);
		
		JLabel lblEstagio = new JLabel("ESTÁGIO DE PESQUISA");
		lblEstagio.setForeground(Color.DARK_GRAY);
		lblEstagio.setFont(new Font("Dialog", Font.BOLD, 14));
		lblEstagio.setBounds(10, 62, 179, 25);
		contentPane.add(lblEstagio);
		
		JComboBox comboBoxPais_1 = new JComboBox();
		comboBoxPais_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		comboBoxPais_1.setBounds(10, 85, 294, 30);
		contentPane.add(comboBoxPais_1);
		
		JComboBox comboBoxPais_1_1 = new JComboBox();
		comboBoxPais_1_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		comboBoxPais_1_1.setBounds(10, 151, 294, 30);
		contentPane.add(comboBoxPais_1_1);
		
		JLabel lblPesqResp = new JLabel("PESQUISADOR RESPONSÁVEL");
		lblPesqResp.setForeground(Color.DARK_GRAY);
		lblPesqResp.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPesqResp.setBounds(10, 126, 230, 25);
		contentPane.add(lblPesqResp);
		
		JLabel lblDataInicio = new JLabel("DATA DE INÍCIO DA PESQUISA");
		lblDataInicio.setForeground(Color.DARK_GRAY);
		lblDataInicio.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDataInicio.setBounds(10, 192, 230, 25);
		contentPane.add(lblDataInicio);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(10, 216, 294, 30);
		contentPane.add(formattedTextField);
		
		JButton btnSalvarVacina = new JButton("SALVAR");
		btnSalvarVacina.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSalvarVacina.setBounds(66, 262, 185, 38);
		contentPane.add(btnSalvarVacina);
	}
}
