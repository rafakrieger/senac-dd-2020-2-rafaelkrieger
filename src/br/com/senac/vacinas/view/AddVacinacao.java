package br.com.senac.vacinas.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import br.com.senac.vacinas.controller.PessoaController;
import br.com.senac.vacinas.controller.VacinaController;
import br.com.senac.vacinas.controller.VacinacaoController;
import br.com.senac.vacinas.model.vo.VacinacaoVO;
import br.com.senac.vacinas.model.vo.VacinaVO;
import br.com.senac.vacinas.model.vo.PessoaVO;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class AddVacinacao extends JPanel {

	private JComboBox comboBoxAvaliacao;
	private JComboBox comboBoxVacina;
	private JComboBox comboBoxPessoa;
	private int estagio;
	private static final String[] AVALIACAO = { "1 - Péssima", "2 - Ruim", "3 - Regular", "4 - Boa", "5 - Ótima" };
	private JFormattedTextField formattedTextFieldData;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	PessoaController pessoaController = new PessoaController();
	private List<PessoaVO> pessoas = pessoaController.pesquisarTodos();

	/**
	 * Create the panel.
	 */
	public AddVacinacao() {
		setBounds(0, 50, 450, 450);
		this.setBackground(new Color(32, 178, 170));
		this.setBorder(null);
		this.setLayout(null);

		JLabel lblVacina = new JLabel("VACINA");
		lblVacina.setForeground(Color.DARK_GRAY);
		lblVacina.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblVacina.setBounds(10, 72, 134, 25);
		this.add(lblVacina);

		VacinaController vacinaController = new VacinaController();
		List<VacinaVO> vacinas = vacinaController.pesquisarTodos();
		comboBoxVacina = new JComboBox(vacinas.toArray());
		comboBoxVacina.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				VacinaVO vacina = (VacinaVO) comboBoxVacina.getSelectedItem();
				if (comboBoxVacina.getSelectedIndex() < 0) {
					estagio = 0;
				} else {
					estagio = vacina.getEstagioPesquisa();
				}
				if (estagio == 1) {
					comboBoxPessoa.removeAllItems();
					pessoas = pessoaController.pesquisarPesquisadores();
					for (PessoaVO person : pessoas) {
						comboBoxPessoa.addItem(person);
					}
				} else if (estagio == 2) {
					comboBoxPessoa.removeAllItems();
					pessoas = pessoaController.pesquisarVoluntarios();
					for (PessoaVO person : pessoas) {
						comboBoxPessoa.addItem(person);
					}
				} else if (estagio == 3) {
					comboBoxPessoa.removeAllItems();
					pessoas = pessoaController.pesquisarTodos();
					for (PessoaVO person : pessoas) {
						comboBoxPessoa.addItem(person);
					}
				}
			}
		});
		comboBoxVacina.setSelectedIndex(-1);
		comboBoxVacina.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxVacina.setBounds(10, 97, 414, 30);
		this.add(comboBoxVacina);

		JLabel lblPessoa = new JLabel("PESSOA");
		lblPessoa.setForeground(Color.DARK_GRAY);
		lblPessoa.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPessoa.setBounds(10, 134, 179, 25);
		this.add(lblPessoa);

		comboBoxPessoa = new JComboBox(pessoas.toArray());
		comboBoxPessoa.setSelectedIndex(-1);
		comboBoxPessoa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxPessoa.setBounds(10, 157, 414, 30);
		this.add(comboBoxPessoa);

		comboBoxAvaliacao = new JComboBox(AVALIACAO);
		comboBoxAvaliacao.setSelectedIndex(-1);
		comboBoxAvaliacao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxAvaliacao.setBounds(10, 223, 414, 30);
		this.add(comboBoxAvaliacao);

		JLabel lblAvaliacao = new JLabel("AVALIAÇÃO");
		lblAvaliacao.setForeground(Color.DARK_GRAY);
		lblAvaliacao.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblAvaliacao.setBounds(10, 198, 230, 25);
		this.add(lblAvaliacao);

		JLabel lblData = new JLabel("DATA");
		lblData.setForeground(Color.DARK_GRAY);
		lblData.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblData.setBounds(10, 264, 230, 25);
		this.add(lblData);

		try {
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");
			formattedTextFieldData = new JFormattedTextField(mascaraData);
			formattedTextFieldData.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			formattedTextFieldData.setBounds(10, 288, 414, 30);
			this.add(formattedTextFieldData);

		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro no sistema, entre em contato com o administrador.");
			System.out.println("Causa da exceção: " + e.getMessage());
		}

		JButton btnSalvarVacinacao = new JButton("SALVAR");
		btnSalvarVacinacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VacinacaoVO vacinacao = new VacinacaoVO();
				vacinacao.setVacina((VacinaVO) comboBoxVacina.getSelectedItem());
				vacinacao.setPessoa((PessoaVO) comboBoxPessoa.getSelectedItem());
				vacinacao.setAvaliacao(comboBoxAvaliacao.getSelectedIndex() + 1);
				vacinacao.setDataVacinacao(obterData(formattedTextFieldData.getText()));

				VacinacaoController vacinacaoController = new VacinacaoController();
				String mensagem = vacinacaoController.salvar(vacinacao);
				JOptionPane.showMessageDialog(null, mensagem);
				
				comboBoxVacina.setSelectedIndex(-1);
				comboBoxPessoa.setSelectedIndex(-1);
				comboBoxAvaliacao.setSelectedIndex(-1);
				formattedTextFieldData.setText("");
				
			}
		});
		btnSalvarVacinacao.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnSalvarVacinacao.setBounds(64, 360, 296, 38);
		this.add(btnSalvarVacinacao);
		
		JLabel lblCadastroDeAplicaes = new JLabel("Cadastro de aplicações");
		lblCadastroDeAplicaes.setHorizontalAlignment(SwingConstants.LEFT);
		lblCadastroDeAplicaes.setForeground(Color.WHITE);
		lblCadastroDeAplicaes.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblCadastroDeAplicaes.setBounds(10, 0, 203, 42);
		add(lblCadastroDeAplicaes);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 39, 213, 12);
		add(separator);
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

	private LocalDate obterData(String dataFinal) {
		LocalDate data = null;
		if (validarData(dataFinal)) {
			data = LocalDate.parse(dataFinal, dateFormat);
		}
		return data;

	}

}
