package br.com.senac.vacinas.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.senac.vacinas.controller.PesquisadorController;
import br.com.senac.vacinas.controller.PessoaController;
import br.com.senac.vacinas.model.vo.PesquisadorVO;
import br.com.senac.vacinas.model.vo.PessoaVO;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class CadastroPessoa extends JFrame {	
	
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldInst;
	private JFormattedTextField formattedTextFieldCpf;
	private JFormattedTextField formattedTextFieldData;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String sexoSelecionado;
	private boolean isVoluntario = false;
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
		
		final JLabel lblInst = new JLabel("INSTITUIÇÃO");
		lblInst.setForeground(Color.DARK_GRAY);
		lblInst.setFont(new Font("Dialog", Font.BOLD, 14));
		lblInst.setBounds(10, 270, 424, 24);
		contentPane.add(lblInst);
		lblInst.setVisible(false);
		
		JLabel lblSexo = new JLabel("SEXO");
		lblSexo.setForeground(Color.DARK_GRAY);
		lblSexo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSexo.setBounds(10, 170, 363, 24);
		contentPane.add(lblSexo);
		
		final JRadioButton rdbtnMasc = new JRadioButton("MASCULINO");
		buttonGroup.add(rdbtnMasc);
		rdbtnMasc.setForeground(Color.DARK_GRAY);
		rdbtnMasc.setFont(new Font("Dialog", Font.BOLD, 12));
		rdbtnMasc.setBackground(Color.CYAN);
		rdbtnMasc.setBounds(10, 200, 109, 23);
		contentPane.add(rdbtnMasc);
		
		final JRadioButton rdbtnFem = new JRadioButton("FEMININO");
		buttonGroup.add(rdbtnFem);
		rdbtnFem.setForeground(Color.DARK_GRAY);
		rdbtnFem.setFont(new Font("Dialog", Font.BOLD, 12));
		rdbtnFem.setBackground(Color.CYAN);
		rdbtnFem.setBounds(123, 201, 109, 23);
		contentPane.add(rdbtnFem);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		textFieldNome.setBounds(10, 26, 414, 30);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		try {
			MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");			
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");
		
		formattedTextFieldCpf = new JFormattedTextField(mascaraCpf);
		formattedTextFieldCpf.setFont(new Font("Dialog", Font.PLAIN, 14));
		formattedTextFieldCpf.setBounds(10, 81, 414, 30);
		contentPane.add(formattedTextFieldCpf);
		
		formattedTextFieldData = new JFormattedTextField(mascaraData);
		formattedTextFieldData.setFont(new Font("Dialog", Font.PLAIN, 14));
		formattedTextFieldData.setBounds(10, 140, 414, 30);		
		contentPane.add(formattedTextFieldData);
		
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro no sistema, entre em contato com o administrador.");
			System.out.println("Causa da exceção: " + e.getMessage());
		}
				
		final JCheckBox chckbxVoluntario = new JCheckBox("VOLUNTÁRIO");
		chckbxVoluntario.setForeground(Color.DARK_GRAY);
		chckbxVoluntario.setBackground(Color.CYAN);
		chckbxVoluntario.setFont(new Font("Dialog", Font.BOLD, 14));
		chckbxVoluntario.setBounds(84, 240, 148, 23);
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
		chckbxPesq.setBounds(243, 240, 148, 23);
		contentPane.add(chckbxPesq);		
			
		textFieldInst = new JTextField();
		textFieldInst.setFont(new Font("Dialog", Font.PLAIN, 14));
		textFieldInst.setColumns(10);
		textFieldInst.setBounds(10, 301, 414, 30);
		contentPane.add(textFieldInst);		
		textFieldInst.setVisible(false);
		
		
		JButton btnSalvarPessoa = new JButton("SALVAR");
		btnSalvarPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PessoaVO pessoa = new PessoaVO();
				PesquisadorVO pesquisador = new PesquisadorVO();
				pessoa.setNome(textFieldNome.getText());
				pessoa.setCpf(formattedTextFieldCpf.getText());
				pessoa.setDataNascimento(LocalDate.parse(formattedTextFieldData.getText(), dateFormatter));
				
				if (rdbtnMasc.isSelected()) {
					sexoSelecionado = "M";
				}else if (rdbtnFem.isSelected()){
					sexoSelecionado = "F";
				}else {
					sexoSelecionado = "null";
				}
				pessoa.setSexo(sexoSelecionado);
				
				if (chckbxVoluntario.isSelected()) {
					isVoluntario = true;
				} else {
					isVoluntario = false;
				}
				pessoa.setVoluntario(isVoluntario);
				
				if (chckbxPesq.isSelected()) {
					pesquisador.setInstituicao(textFieldNome.getText());
					PesquisadorController pesquisadorController = new PesquisadorController();
					pesquisadorController.salvar(pesquisador);	
				}
				
				PessoaController pessoaController = new PessoaController();
				String mensagem = pessoaController.salvar(pessoa);
				JOptionPane.showMessageDialog(contentPane, mensagem);
				
			}
		});
		btnSalvarPessoa.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSalvarPessoa.setBounds(123, 349, 177, 35);
		contentPane.add(btnSalvarPessoa);
		
	}
}
