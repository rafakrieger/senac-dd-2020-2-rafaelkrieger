package br.com.senac.vacinas.view;

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
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JTable;

public class BuscaPessoa extends JPanel {	
	
	private JTextField textFieldNome;
	private JTextField textFieldInst;
	private JFormattedTextField formattedTextFieldCpf;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String sexoSelecionado;
	private boolean isVoluntario = false;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	private JTable tablerResultado;
	private JLabel formattedTextFieldData;


	/**
	 * Create the panel.
	 */
	public BuscaPessoa() {				
		
		setBounds(100, 100, 450, 450);		
		this.setBackground(new Color(32, 178, 170));
		this.setBorder(new EmptyBorder(5, 5, 5, 5));		
		this.setLayout(null);
		
		JLabel lblNome = new JLabel("NOME");
		lblNome.setBounds(10, 0, 381, 24);
		lblNome.setForeground(Color.DARK_GRAY);
		lblNome.setFont(new Font("Segoe UI", Font.BOLD, 14));
		this.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(Color.DARK_GRAY);
		lblCpf.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblCpf.setBounds(10, 58, 375, 24);
		this.add(lblCpf);
		
		final JLabel lblInst = new JLabel("INSTITUIÇÃO");
		lblInst.setForeground(Color.DARK_GRAY);
		lblInst.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblInst.setBounds(10, 154, 381, 24);
		this.add(lblInst);
		lblInst.setVisible(false);
		
		JLabel lblSexo = new JLabel("SEXO");
		lblSexo.setForeground(Color.DARK_GRAY);
		lblSexo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblSexo.setBounds(297, 0, 50, 24);
		this.add(lblSexo);
		
		final JRadioButton rdbtnMasc = new JRadioButton("MASCULINO");
		buttonGroup.add(rdbtnMasc);
		rdbtnMasc.setForeground(Color.DARK_GRAY);
		rdbtnMasc.setFont(new Font("Segoe UI", Font.BOLD, 12));
		rdbtnMasc.setBackground(new Color(32, 178, 170));
		rdbtnMasc.setBounds(212, 26, 109, 23);
		this.add(rdbtnMasc);
		
		final JRadioButton rdbtnFem = new JRadioButton("FEMININO");
		buttonGroup.add(rdbtnFem);
		rdbtnFem.setForeground(Color.DARK_GRAY);
		rdbtnFem.setFont(new Font("Segoe UI", Font.BOLD, 12));
		rdbtnFem.setBackground(new Color(32, 178, 170));
		rdbtnFem.setBounds(325, 27, 109, 23);
		this.add(rdbtnFem);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldNome.setBounds(10, 26, 192, 30);
		this.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		try {
			MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");			
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");
		
		formattedTextFieldCpf = new JFormattedTextField(mascaraCpf);
		formattedTextFieldCpf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		formattedTextFieldCpf.setBounds(10, 81, 414, 30);
		this.add(formattedTextFieldCpf);
		
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro no sistema, entre em contato com o administrador.");
			System.out.println("Causa da exceção: " + e.getMessage());
		}
				
		final JCheckBox chckbxVoluntario = new JCheckBox("VOLUNTÁRIO");
		chckbxVoluntario.setForeground(Color.DARK_GRAY);
		chckbxVoluntario.setBackground(new Color(32, 178, 170));
		chckbxVoluntario.setFont(new Font("Segoe UI", Font.BOLD, 14));
		chckbxVoluntario.setBounds(58, 124, 148, 23);
		this.add(chckbxVoluntario);		
		
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
		chckbxPesq.setFont(new Font("Segoe UI", Font.BOLD, 14));
		chckbxPesq.setBackground(new Color(32, 178, 170));
		chckbxPesq.setBounds(250, 124, 148, 23);
		this.add(chckbxPesq);		
			
		textFieldInst = new JTextField();
		textFieldInst.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldInst.setColumns(10);
		textFieldInst.setBounds(10, 178, 414, 30);
		this.add(textFieldInst);		
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
				JOptionPane.showMessageDialog(null, mensagem);
				
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
		btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnExcluir.setBounds(226, 219, 95, 35);
		this.add(btnExcluir);		
		
		tablerResultado = new JTable();
		tablerResultado.setBounds(10, 273, 414, 127);
		this.add(tablerResultado);
		
		JButton btnpPesquisar = new JButton("PESQUISAR");
		btnpPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnpPesquisar.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnpPesquisar.setBounds(10, 219, 95, 35);
		this.add(btnpPesquisar);
		
		JButton btnpEditar = new JButton("EDITAR");
		btnpEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnpEditar.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnpEditar.setBounds(115, 219, 95, 35);
		this.add(btnpEditar);
		
		JButton btnLimpar = new JButton("LIMPAR");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNome.setText("");
				formattedTextFieldCpf.setText("");
				chckbxPesq.setSelected(false);
				chckbxVoluntario.setSelected(false);
				textFieldInst.setVisible(false);
				lblInst.setVisible(false);
				buttonGroup.clearSelection();
			}
		});
		btnLimpar.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnLimpar.setBounds(331, 219, 95, 35);
		add(btnLimpar);

	}
}
