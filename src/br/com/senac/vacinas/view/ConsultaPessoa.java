package br.com.senac.vacinas.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.senac.vacinas.controller.PesquisadorController;
import br.com.senac.vacinas.controller.PessoaController;
import br.com.senac.vacinas.model.exception.DataVaziaException;
import br.com.senac.vacinas.model.vo.PesquisadorVO;
import br.com.senac.vacinas.model.vo.PessoaVO;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JTable;

public class ConsultaPessoa extends JFrame {	
	
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldInst;
	private JFormattedTextField formattedTextFieldCpf;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String sexoSelecionado;
	private boolean isVoluntario = false;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	private JTable tablerResultado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaPessoa frame = new ConsultaPessoa();
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
	public ConsultaPessoa() {
		setTitle("Consulta de Pessoas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(32, 178, 170));
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
		
		final JLabel lblInst = new JLabel("INSTITUIÇÃO");
		lblInst.setForeground(Color.DARK_GRAY);
		lblInst.setFont(new Font("Dialog", Font.BOLD, 14));
		lblInst.setBounds(10, 154, 381, 24);
		contentPane.add(lblInst);
		lblInst.setVisible(false);
		
		JLabel lblSexo = new JLabel("SEXO");
		lblSexo.setForeground(Color.DARK_GRAY);
		lblSexo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSexo.setBounds(297, 0, 50, 24);
		contentPane.add(lblSexo);
		
		final JRadioButton rdbtnMasc = new JRadioButton("MASCULINO");
		buttonGroup.add(rdbtnMasc);
		rdbtnMasc.setForeground(Color.DARK_GRAY);
		rdbtnMasc.setFont(new Font("Dialog", Font.BOLD, 12));
		rdbtnMasc.setBackground(new Color(224, 255, 255));
		rdbtnMasc.setBounds(212, 26, 109, 23);
		contentPane.add(rdbtnMasc);
		
		final JRadioButton rdbtnFem = new JRadioButton("FEMININO");
		buttonGroup.add(rdbtnFem);
		rdbtnFem.setForeground(Color.DARK_GRAY);
		rdbtnFem.setFont(new Font("Dialog", Font.BOLD, 12));
		rdbtnFem.setBackground(new Color(224, 255, 255));
		rdbtnFem.setBounds(325, 27, 109, 23);
		contentPane.add(rdbtnFem);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		textFieldNome.setBounds(10, 26, 192, 30);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		try {
			MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");			
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");
		
		formattedTextFieldCpf = new JFormattedTextField(mascaraCpf);
		formattedTextFieldCpf.setFont(new Font("Dialog", Font.PLAIN, 14));
		formattedTextFieldCpf.setBounds(10, 81, 414, 30);
		contentPane.add(formattedTextFieldCpf);
		
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro no sistema, entre em contato com o administrador.");
			System.out.println("Causa da exceção: " + e.getMessage());
		}
				
		final JCheckBox chckbxVoluntario = new JCheckBox("VOLUNTÁRIO");
		chckbxVoluntario.setForeground(Color.DARK_GRAY);
		chckbxVoluntario.setBackground(new Color(224, 255, 255));
		chckbxVoluntario.setFont(new Font("Dialog", Font.BOLD, 14));
		chckbxVoluntario.setBounds(58, 124, 148, 23);
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
		chckbxPesq.setBackground(new Color(224, 255, 255));
		chckbxPesq.setBounds(250, 124, 148, 23);
		contentPane.add(chckbxPesq);		
			
		textFieldInst = new JTextField();
		textFieldInst.setFont(new Font("Dialog", Font.PLAIN, 14));
		textFieldInst.setColumns(10);
		textFieldInst.setBounds(10, 178, 414, 30);
		contentPane.add(textFieldInst);		
		textFieldInst.setVisible(false);
		
		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PessoaVO pessoa = new PessoaVO();
				PesquisadorVO pesquisador = new PesquisadorVO();
				pessoa.setNome(textFieldNome.getText());
				pessoa.setCpf(obterNumerosCpf(formattedTextFieldCpf.getText()));
						
				
				if (rdbtnMasc.isSelected()) {
					sexoSelecionado = "M";
				}else if (rdbtnFem.isSelected()){
					sexoSelecionado = "F";
				}else {
					sexoSelecionado = null;
				}
				pessoa.setSexo(sexoSelecionado);
				
				if (chckbxVoluntario.isSelected()) {
					isVoluntario = true;
				} else {
					isVoluntario = false;
				}
				pessoa.setVoluntario(isVoluntario);				
							
				PessoaController pessoaController = new PessoaController();
				String mensagem = pessoaController.salvar(pessoa);
				JOptionPane.showMessageDialog(contentPane, mensagem);
				
				if (chckbxPesq.isSelected()) {
					pesquisador.setInstituicao(textFieldInst.getText());
					pesquisador.setIdPessoa(pessoa.getIdPessoa());
					pesquisador.setNome(pessoa.getNome());
					PesquisadorController pesquisadorController = new PesquisadorController();
					pesquisadorController.salvar(pesquisador);	
				}
				
				textFieldNome.setText("");
				formattedTextFieldCpf.setText("");
				rdbtnFem.setSelected(false);
				rdbtnMasc.setSelected(false);
				chckbxPesq.setSelected(false);
				chckbxVoluntario.setSelected(false);
				textFieldInst.setVisible(false);
				lblInst.setVisible(false);
				
			}

			private String obterNumerosCpf(String cpf) {
				String digito = cpf.replace(".", "");
				String novoCpf = digito.replace("-", "");
				return novoCpf;				
			}
			
			private boolean validarData(String strDate) {
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);				
			    try {
			    	LocalDate date = LocalDate.parse(strDate, dateFormatter);
			        return true;
			    } catch (DateTimeParseException e) {			    	
			    	return false;			       
			    } 
			}
			
			private LocalDate obterData(String dataNascimento) {
				LocalDate data = null;				
				if (validarData(dataNascimento)) {
					data = LocalDate.parse(dataNascimento, dateFormat);
				}	
				return data;
			}
				
		});
		btnExcluir.setFont(new Font("Dialog", Font.BOLD, 11));
		btnExcluir.setBounds(220, 219, 95, 35);
		contentPane.add(btnExcluir);		
		
		tablerResultado = new JTable();
		tablerResultado.setBounds(10, 273, 414, 127);
		contentPane.add(tablerResultado);
		
		JButton btnpPesquisar = new JButton("PESQUISAR");
		btnpPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnpPesquisar.setFont(new Font("Dialog", Font.BOLD, 11));
		btnpPesquisar.setBounds(10, 219, 95, 35);
		contentPane.add(btnpPesquisar);
		
		JButton btnpEditar = new JButton("EDITAR");
		btnpEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnpEditar.setFont(new Font("Dialog", Font.BOLD, 11));
		btnpEditar.setBounds(115, 219, 95, 35);
		contentPane.add(btnpEditar);
		
		JButton btnLimpar = new JButton("LIMPAR");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNome.setText("");
				buttonGroup.clearSelection();
				formattedTextFieldCpf.setText("");
				textFieldInst.setText("");
				chckbxPesq.setSelected(false);
				chckbxVoluntario.setSelected(false);
			}
		});
		btnLimpar.setFont(new Font("Dialog", Font.BOLD, 11));
		btnLimpar.setBounds(329, 219, 95, 35);
		contentPane.add(btnLimpar);
	}
}
