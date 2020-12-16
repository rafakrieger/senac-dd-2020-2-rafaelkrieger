package br.com.senac.vacinas.view;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import br.com.senac.vacinas.controller.PesquisadorController;
import br.com.senac.vacinas.controller.PessoaController;
import br.com.senac.vacinas.model.seletores.SeletorPessoa;
import br.com.senac.vacinas.model.vo.PesquisadorVO;
import br.com.senac.vacinas.model.vo.PessoaVO;
import br.com.senac.vacinas.utils.GeradorPlanilha;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class BuscaPessoa extends JPanel {

	private JTextField textFieldNome;
	private JFormattedTextField formattedTextFieldCpf;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String sexoSelecionado;
	private boolean isVoluntario = false;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	private JTable tablerResultado;
	private JLabel formattedTextFieldData;
	private List<PessoaVO> pessoasConsultadas;

	/**
	 * Create the panel.
	 */
	public BuscaPessoa() {

		setBounds(0, 60, 450, 500);
		this.setBackground(new Color(32, 178, 170));
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);

		JLabel lblNome = new JLabel("NOME");
		lblNome.setBounds(10, 65, 172, 24);
		lblNome.setForeground(Color.DARK_GRAY);
		lblNome.setFont(new Font("Segoe UI", Font.BOLD, 14));
		this.add(lblNome);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(Color.DARK_GRAY);
		lblCpf.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblCpf.setBounds(10, 134, 95, 24);
		this.add(lblCpf);

		final JRadioButton rdbtnMasc = new JRadioButton("MASCULINO");
		buttonGroup.add(rdbtnMasc);
		rdbtnMasc.setForeground(Color.DARK_GRAY);
		rdbtnMasc.setFont(new Font("Segoe UI", Font.BOLD, 14));
		rdbtnMasc.setBackground(new Color(32, 178, 170));
		rdbtnMasc.setBounds(320, 157, 109, 23);
		this.add(rdbtnMasc);

		final JRadioButton rdbtnFem = new JRadioButton("FEMININO");
		buttonGroup.add(rdbtnFem);
		rdbtnFem.setForeground(Color.DARK_GRAY);
		rdbtnFem.setFont(new Font("Segoe UI", Font.BOLD, 14));
		rdbtnFem.setBackground(new Color(32, 178, 170));
		rdbtnFem.setBounds(200, 157, 109, 23);
		this.add(rdbtnFem);

		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldNome.setBounds(10, 93, 430, 30);
		this.add(textFieldNome);
		textFieldNome.setColumns(10);

		try {
			MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");

			formattedTextFieldCpf = new JFormattedTextField(mascaraCpf);
			formattedTextFieldCpf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			formattedTextFieldCpf.setBounds(10, 157, 172, 30);
			this.add(formattedTextFieldCpf);

		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro no sistema, entre em contato com o administrador.");
			System.out.println("Causa da exceção: " + e.getMessage());
		}

		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PessoaVO pessoa = new PessoaVO();
				int selRow = tablerResultado.getSelectedRow();

				String value = tablerResultado.getModel().getValueAt(selRow, 0).toString();

				pessoa.setIdPessoa(Integer.parseInt(value));

				PessoaController pessoaController = new PessoaController();
				String mensagem = pessoaController.excluir(pessoa);
				JOptionPane.showMessageDialog(null, mensagem);

				limparTabela();

			}



		});
		btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnExcluir.setBounds(233, 212, 95, 35);
		this.add(btnExcluir);

		tablerResultado = new JTable();
		this.limparTabela();
		tablerResultado.setBounds(10, 266, 430, 204);
		tablerResultado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablerResultado.getColumnModel().getColumn(0).setPreferredWidth(10);
		tablerResultado.getColumnModel().getColumn(1).setPreferredWidth(150);
		tablerResultado.getColumnModel().getColumn(3).setPreferredWidth(10);
		tablerResultado.getColumnModel().getColumn(4).setPreferredWidth(45);
		this.add(tablerResultado);

		JButton btnpPesquisar = new JButton("PESQUISAR");
		btnpPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PessoaController controlador = new PessoaController();
				SeletorPessoa seletor = new SeletorPessoa();

				seletor.setNome(textFieldNome.getText());

				if (rdbtnMasc.isSelected()) {
					sexoSelecionado = "M";
				} else if (rdbtnFem.isSelected()) {
					sexoSelecionado = "F";
				} else {
					sexoSelecionado = null;
				}
				seletor.setSexo(sexoSelecionado);

				seletor.setCpf(obterNumerosCpf(formattedTextFieldCpf.getText()));

				List<PessoaVO> pessoas = controlador.listarPessoas(seletor);
				try {
					atualizarTabelaPessoas(pessoas);
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro no sistema, entre em contato com o administrador.");
					System.out.println("Causa da exceção: " + e1.getMessage());
				}
				tablerResultado.getColumnModel().getColumn(0).setPreferredWidth(10);
				tablerResultado.getColumnModel().getColumn(1).setPreferredWidth(150);
				tablerResultado.getColumnModel().getColumn(3).setPreferredWidth(10);
				tablerResultado.getColumnModel().getColumn(4).setPreferredWidth(45);
			}
		});

		btnpPesquisar.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnpPesquisar.setBounds(10, 212, 95, 35);
		this.add(btnpPesquisar);

		JButton btnpEditar = new JButton("EDITAR");
		btnpEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PessoaVO pessoa = new PessoaVO();
				String id = null;
				int selRow = tablerResultado.getSelectedRow();
				if (selRow >= 0) {
					id = tablerResultado.getModel().getValueAt(selRow, 0).toString();
				}
				
				if (id != null) {
					pessoa.setIdPessoa(Integer.parseInt(id));
				}
				pessoa.setNome(textFieldNome.getText());
				pessoa.setCpf(obterNumerosCpf(formattedTextFieldCpf.getText()));
				if (rdbtnMasc.isSelected()) {
					sexoSelecionado = "M";
				} else if (rdbtnFem.isSelected()) {
					sexoSelecionado = "F";
				} else {
					sexoSelecionado = null;
				}
				pessoa.setSexo(sexoSelecionado);

				PessoaController pessoaController = new PessoaController();
				String mensagem = pessoaController.atualizarBusca(pessoa);
				JOptionPane.showMessageDialog(null, mensagem);

			}
		});
		btnpEditar.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnpEditar.setBounds(123, 212, 95, 35);
		this.add(btnpEditar);

		JButton btnLimpar = new JButton("LIMPAR");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNome.setText("");
				formattedTextFieldCpf.setText("");
				buttonGroup.clearSelection();
				limparTabela();
			}

		});
		btnLimpar.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnLimpar.setBounds(345, 212, 95, 35);
		add(btnLimpar);

		JLabel lblConsultaDePessoas = new JLabel("Consulta de pessoas");
		lblConsultaDePessoas.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsultaDePessoas.setForeground(Color.WHITE);
		lblConsultaDePessoas.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblConsultaDePessoas.setBounds(10, 11, 177, 31);
		add(lblConsultaDePessoas);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 42, 213, 12);
		add(separator);
		
		
		JButton btnNewButton = new JButton("Imprimir");
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = "";
				if (textFieldNome.getText().length() != 0) {
					titulo = "Pesquisa por nome "+textFieldNome.getText().toUpperCase();
				} else if (obterNumerosCpf(formattedTextFieldCpf.getText()).length() == 11) {
					titulo = "Pesquisa por CPF "+formattedTextFieldCpf.getText();
				} else if (rdbtnFem.isSelected() || rdbtnMasc.isSelected()) {
					titulo = "Pesquisa por sexo";
				} else {
					titulo = "Pesquisar todos";
				}				
				
				JFileChooser janelaArquivos = new JFileChooser();

				int opcaoSelecionada = janelaArquivos.showSaveDialog(null);

				if (opcaoSelecionada == JFileChooser.APPROVE_OPTION) {
					String caminho = janelaArquivos.getSelectedFile().getAbsolutePath();

					PessoaController controller = new PessoaController();
					String mensagem = controller.gerarPlanilha(pessoasConsultadas, caminho, titulo);

					JOptionPane.showMessageDialog(null, mensagem);
				}
			}
		});
		btnNewButton.setBounds(340, 19, 89, 23);
		add(btnNewButton);


	}	
	

	private String obterNumerosCpf(String cpf) {
		String digito = cpf.replace(".", "");
		String espaco = digito.replace("-", "");
		String novoCpf = espaco.replace(" ", "");
		
		return novoCpf;
	}

	protected void atualizarTabelaPessoas(List<PessoaVO> pessoas) throws ParseException {

		pessoasConsultadas = pessoas;

		this.limparTabela();

		DefaultTableModel modelo = (DefaultTableModel) tablerResultado.getModel();		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		

		for (PessoaVO pessoa : pessoas) {
			String dataFormatada = pessoa.getDataNascimento().format(formatter);
			String[] novaLinha = new String[] { pessoa.getIdPessoa() + "", pessoa.getNome(), 
					formatarCpf(pessoa.getCpf(), "###.###.###-##"), pessoa.getSexo(), dataFormatada};
			modelo.addRow(novaLinha);
		}

	}
	
	private String formatarCpf(String cpf, String mascara) throws ParseException {
        MaskFormatter mf = new MaskFormatter(mascara);
        mf.setValueContainsLiteralCharacters(false);
        return mf.valueToString(cpf);
    }

	private void limparTabela() {
		tablerResultado.setModel(new DefaultTableModel(new String[][] { { "#", "Nome", "CPF", "Sexo", "Nascimento" }, },
				new String[] { "#", "Nome", "CPF", "Sexo", "Nascimento" }));
	}



	public void limpar() {
		textFieldNome.setText("");
		formattedTextFieldCpf.setText("");
		buttonGroup.clearSelection();
		limparTabela();
		
	}
}

