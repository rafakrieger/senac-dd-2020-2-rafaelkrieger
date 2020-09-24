package br.com.senac.vacinas.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroPessoa extends JFrame {	
	
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldInst;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroPessoa frame = new CadastroPessoa();
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
	public CadastroPessoa() {
		setTitle("Vacinação covid-19 - Cadastro de pessoas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("NOME");
		lblNome.setBounds(10, 0, 381, 24);
		lblNome.setForeground(Color.DARK_GRAY);
		lblNome.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPane.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(Color.DARK_GRAY);
		lblCpf.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCpf.setBounds(10, 58, 375, 24);
		contentPane.add(lblCpf);
		
		JLabel lbDataNasc = new JLabel("DATA DE NASCIMENTO");
		lbDataNasc.setForeground(Color.DARK_GRAY);
		lbDataNasc.setFont(new Font("Dialog", Font.BOLD, 14));
		lbDataNasc.setBounds(10, 115, 363, 24);
		contentPane.add(lbDataNasc);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		textFieldNome.setBounds(10, 26, 414, 30);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		try {
			MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");			
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");
		
		JFormattedTextField formattedTextFieldCpf = new JFormattedTextField(mascaraCpf);
		formattedTextFieldCpf.setFont(new Font("Dialog", Font.PLAIN, 14));
		formattedTextFieldCpf.setBounds(10, 81, 414, 30);
		contentPane.add(formattedTextFieldCpf);
		
		JFormattedTextField formattedTextFieldData = new JFormattedTextField(mascaraData);
		formattedTextFieldData.setFont(new Font("Dialog", Font.PLAIN, 14));
		formattedTextFieldData.setBounds(10, 140, 414, 30);		
		contentPane.add(formattedTextFieldData);
		
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro no sistema, entre em contato com o administrador.");
			System.out.println("Causa da exceção: " + e.getMessage());
		}
		
		
		final JLabel lblInst = new JLabel("INSTITUIÇÃO");
		lblInst.setForeground(Color.DARK_GRAY);
		lblInst.setFont(new Font("Dialog", Font.BOLD, 14));
		lblInst.setBounds(10, 270, 424, 24);
		contentPane.add(lblInst);
		lblInst.setVisible(false);
			
		textFieldInst = new JTextField();
		textFieldInst.setFont(new Font("Dialog", Font.PLAIN, 14));
		textFieldInst.setColumns(10);
		textFieldInst.setBounds(10, 301, 414, 30);
		contentPane.add(textFieldInst);		
		textFieldInst.setVisible(false);
		
		
		JButton btnSalvarPessoa = new JButton("SALVAR");
		btnSalvarPessoa.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSalvarPessoa.setBounds(123, 349, 177, 35);
		contentPane.add(btnSalvarPessoa);
		
		JCheckBox chckbxVoluntario = new JCheckBox("VOLUNTÁRIO");
		chckbxVoluntario.setForeground(Color.DARK_GRAY);
		chckbxVoluntario.setBackground(Color.CYAN);
		chckbxVoluntario.setFont(new Font("Dialog", Font.BOLD, 14));
		chckbxVoluntario.setBounds(10, 191, 148, 23);
		contentPane.add(chckbxVoluntario);
		
		final JCheckBox chckbxPesq = new JCheckBox("PESQUISADOR");		
		chckbxPesq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxPesq.isSelected()) {
					lblInst.setVisible(true);
					textFieldInst.setVisible(true);
				} else {
					lblInst.setVisible(false);
					textFieldInst.setVisible(false);
				}
			}
		});
		chckbxPesq.setForeground(Color.DARK_GRAY);
		chckbxPesq.setFont(new Font("Dialog", Font.BOLD, 14));
		chckbxPesq.setBackground(Color.CYAN);
		chckbxPesq.setBounds(10, 232, 148, 23);
		contentPane.add(chckbxPesq);
		
		
		
	}
}
