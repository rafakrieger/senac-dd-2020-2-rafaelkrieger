package br.com.senac.vacinas.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.com.senac.vacinas.controller.PesquisadorController;
import br.com.senac.vacinas.controller.PessoaController;
import br.com.senac.vacinas.model.vo.PesquisadorVO;
import br.com.senac.vacinas.model.vo.PessoaVO;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class AddPessoa extends JPanel {

	private JTextField textFieldNome;
	private JTextField textFieldInst;
	private JFormattedTextField formattedTextFieldCpf;
	private JFormattedTextField formattedTextFieldData;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String sexoSelecionado;
	private boolean isVoluntario = false;
	private JCheckBox chckbxVoluntario;
	private JCheckBox chckbxPesq;
	private JButton btnEditarPessoa;
	private JButton btnSalvarPessoa;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");

	/**
	 * Create the panel.
	 */
	public AddPessoa() {

		this.setBounds(0, 60, 450, 450);
		this.setBackground(new Color(32, 178, 170));
		this.setBorder(null);
		this.setLayout(null);

		JLabel lblNome = new JLabel("NOME");
		lblNome.setBounds(10, 110, 381, 24);
		lblNome.setForeground(Color.DARK_GRAY);
		lblNome.setFont(new Font("Segoe UI", Font.BOLD, 14));
		this.add(lblNome);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(Color.DARK_GRAY);
		lblCpf.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblCpf.setBounds(10, 50, 375, 24);
		this.add(lblCpf);

		JLabel lbDataNasc = new JLabel("DATA DE NASCIMENTO");
		lbDataNasc.setForeground(Color.DARK_GRAY);
		lbDataNasc.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lbDataNasc.setBounds(10, 165, 363, 24);
		this.add(lbDataNasc);

		final JLabel lblInst = new JLabel("INSTITUIÇÃO");
		lblInst.setForeground(Color.DARK_GRAY);
		lblInst.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblInst.setBounds(10, 320, 424, 24);
		this.add(lblInst);
		lblInst.setVisible(false);

		JLabel lblSexo = new JLabel("SEXO");
		lblSexo.setForeground(Color.DARK_GRAY);
		lblSexo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblSexo.setBounds(10, 220, 363, 24);
		this.add(lblSexo);

		final JRadioButton rdbtnMasc = new JRadioButton("MASCULINO");
		buttonGroup.add(rdbtnMasc);
		rdbtnMasc.setForeground(Color.DARK_GRAY);
		rdbtnMasc.setFont(new Font("Segoe UI", Font.BOLD, 12));
		rdbtnMasc.setBackground(new Color(32, 178, 170));
		rdbtnMasc.setBounds(10, 250, 109, 23);
		this.add(rdbtnMasc);

		final JRadioButton rdbtnFem = new JRadioButton("FEMININO");
		buttonGroup.add(rdbtnFem);
		rdbtnFem.setForeground(Color.DARK_GRAY);
		rdbtnFem.setFont(new Font("Segoe UI", Font.BOLD, 12));
		rdbtnFem.setBackground(new Color(32, 178, 170));
		rdbtnFem.setBounds(123, 251, 109, 23);
		this.add(rdbtnFem);

		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		textFieldNome.setBounds(10, 136, 414, 30);
		this.add(textFieldNome);
		textFieldNome.setColumns(10);

		try {
			MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");

			formattedTextFieldCpf = new JFormattedTextField(mascaraCpf);
			formattedTextFieldCpf.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PessoaController controller = new PessoaController();
					PesquisadorController pController = new PesquisadorController();
					PessoaVO pessoa = new PessoaVO();
					String cpf = obterNumerosCpf(formattedTextFieldCpf.getText());
					if (controller.pesquisarPorCpf(cpf) != null) {
						pessoa = controller.pesquisarPorCpf(cpf);
						textFieldNome.setText(pessoa.getNome());
						formattedTextFieldData.setText(pessoa.getDataNascimento().format(dateFormat).toString());
						if (pessoa.getSexo().equalsIgnoreCase("M")) {
							rdbtnMasc.setSelected(true);
							rdbtnFem.setSelected(false);
						} else {
							rdbtnFem.setSelected(true);
							rdbtnMasc.setSelected(false);
						}
						if (pessoa.isVoluntario()) {
							chckbxVoluntario.setSelected(true);
						}
						if (pController.pesquisarPorIdPessoa(pessoa.getIdPessoa()) != null) {
							PesquisadorVO pesquisador = new PesquisadorVO();
							pesquisador = pController.pesquisarPorIdPessoa(pessoa.getIdPessoa());
							chckbxPesq.setSelected(true);
							textFieldInst.setVisible(true);
							textFieldInst.setText(pesquisador.getInstituicao());
						}
						btnEditarPessoa.setVisible(true);
						btnSalvarPessoa.setVisible(false);
					}
				}
			});
			formattedTextFieldCpf.setFont(new Font("Dialog", Font.PLAIN, 14));
			formattedTextFieldCpf.setBounds(10, 73, 414, 30);
			this.add(formattedTextFieldCpf);

			formattedTextFieldData = new JFormattedTextField(mascaraData);
			formattedTextFieldData.setFont(new Font("Dialog", Font.PLAIN, 14));
			formattedTextFieldData.setBounds(10, 190, 414, 30);
			this.add(formattedTextFieldData);

		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro no sistema, entre em contato com o administrador.");
			System.out.println("Causa da exceção: " + e.getMessage());
		}

		chckbxVoluntario = new JCheckBox("VOLUNTÁRIO");
		chckbxVoluntario.setForeground(Color.DARK_GRAY);
		chckbxVoluntario.setBackground(new Color(32, 178, 170));
		chckbxVoluntario.setFont(new Font("Segoe UI", Font.BOLD, 14));
		chckbxVoluntario.setBounds(84, 290, 148, 23);
		this.add(chckbxVoluntario);

		chckbxPesq = new JCheckBox("PESQUISADOR");
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
		chckbxPesq.setBounds(243, 290, 148, 23);
		this.add(chckbxPesq);

		textFieldInst = new JTextField();
		textFieldInst.setFont(new Font("Dialog", Font.PLAIN, 14));
		textFieldInst.setColumns(10);
		textFieldInst.setBounds(10, 351, 414, 30);
		this.add(textFieldInst);
		textFieldInst.setVisible(false);

		btnSalvarPessoa = new JButton("SALVAR");
		btnSalvarPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PessoaVO pessoa = new PessoaVO();
				PesquisadorVO pesquisador = new PesquisadorVO();
				pessoa.setNome(textFieldNome.getText());
				pessoa.setCpf(obterNumerosCpf(formattedTextFieldCpf.getText()));
				pessoa.setDataNascimento(obterData(formattedTextFieldData.getText()));

				if (rdbtnMasc.isSelected()) {
					sexoSelecionado = "M";
				} else if (rdbtnFem.isSelected()) {
					sexoSelecionado = "F";
				} else {
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
				formattedTextFieldData.setText("");
				rdbtnFem.setSelected(false);
				rdbtnMasc.setSelected(false);
				chckbxPesq.setSelected(false);
				chckbxVoluntario.setSelected(false);
				textFieldInst.setVisible(false);
				lblInst.setVisible(false);

			}

		});
		btnSalvarPessoa.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSalvarPessoa.setBounds(133, 404, 177, 35);
		this.add(btnSalvarPessoa);

		btnEditarPessoa = new JButton("ATUALIZAR");
		btnEditarPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PessoaVO pessoa = new PessoaVO();
				PesquisadorVO pesquisador = new PesquisadorVO();
				pessoa.setNome(textFieldNome.getText());
				pessoa.setCpf(obterNumerosCpf(formattedTextFieldCpf.getText()));
				pessoa.setDataNascimento(obterData(formattedTextFieldData.getText()));

				if (rdbtnMasc.isSelected()) {
					sexoSelecionado = "M";
				} else if (rdbtnFem.isSelected()) {
					sexoSelecionado = "F";
				} else {
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
				String mensagem = pessoaController.atualizar(pessoa);
				JOptionPane.showMessageDialog(null, mensagem);

				if (chckbxPesq.isSelected()) {
					pesquisador.setInstituicao(textFieldInst.getText());
					pesquisador.setIdPessoa(pessoa.getIdPessoa());
					pesquisador.setNome(pessoa.getNome());
					PesquisadorController pesquisadorController = new PesquisadorController();
					pesquisadorController.atualizar(pesquisador);
				}

				textFieldNome.setText("");
				formattedTextFieldCpf.setText("");
				formattedTextFieldData.setText("");
				rdbtnFem.setSelected(false);
				rdbtnMasc.setSelected(false);
				chckbxPesq.setSelected(false);
				chckbxVoluntario.setSelected(false);
				textFieldInst.setVisible(false);
				lblInst.setVisible(false);
				btnEditarPessoa.setVisible(false);
				btnSalvarPessoa.setVisible(true);
			}
		});
		btnEditarPessoa.setFont(new Font("Dialog", Font.BOLD, 14));
		btnEditarPessoa.setBounds(133, 404, 177, 35);
		btnEditarPessoa.setVisible(false);
		add(btnEditarPessoa);

		JLabel lblCadastroDePessoas = new JLabel("Cadastro de pessoas");
		lblCadastroDePessoas.setHorizontalAlignment(SwingConstants.LEFT);
		lblCadastroDePessoas.setForeground(Color.WHITE);
		lblCadastroDePessoas.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblCadastroDePessoas.setBounds(10, 0, 177, 42);
		add(lblCadastroDePessoas);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 39, 203, 12);
		add(separator);

	}

	private String obterNumerosCpf(String cpf) {
		String digito = cpf.replace(".", "");
		String novoCpf = digito.replace("-", "");
		return novoCpf;
	}

	private boolean validarData(String strDate) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
				.withResolverStyle(ResolverStyle.STRICT);
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
}
