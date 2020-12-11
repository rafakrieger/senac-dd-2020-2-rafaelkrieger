package br.com.senac.vacinas.view;



import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import br.com.senac.vacinas.controller.VacinacaoController;
import br.com.senac.vacinas.model.seletores.SeletorVacinacao;
import br.com.senac.vacinas.model.vo.VacinacaoVO;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;

public class BuscaVacinacao extends JPanel {
	private JComboBox comboBoxAvaliacao;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	private JTable tableResultado;
	private static final String[] AVALIACAO = { "1 - Péssima", "2 - Ruim", "3 - Regular", "4 - Boa", "5 - Ótima" };
	private List<VacinacaoVO> vacinacoesConsultadas;
	private JFormattedTextField formattedTextFieldDataInicio;
	private JFormattedTextField formattedTextFieldDataFim;

	/**
	 * Create the panel.
	 */
	public BuscaVacinacao() {

		setBounds(0, 60, 450, 500);
		this.setBackground(new Color(32, 178, 170));
		this.setBorder(null);
		this.setLayout(null);

		JLabel lblPessoa = new JLabel("AVALIAÇÃO");
		lblPessoa.setForeground(Color.DARK_GRAY);
		lblPessoa.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPessoa.setBounds(10, 132, 179, 25);
		this.add(lblPessoa);

		comboBoxAvaliacao = new JComboBox(AVALIACAO);
		comboBoxAvaliacao.setSelectedIndex(-1);
		comboBoxAvaliacao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxAvaliacao.setBounds(10, 156, 430, 30);
		this.add(comboBoxAvaliacao);		

		JButton btnPesquisarVacinacao = new JButton("PESQUISAR");
		btnPesquisarVacinacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VacinacaoController controlador = new VacinacaoController();
				SeletorVacinacao seletor = new SeletorVacinacao();				
				
				if (comboBoxAvaliacao.getSelectedIndex() > 0) {
					seletor.setAvaliacao(comboBoxAvaliacao.getSelectedIndex() + 1);
				}
				seletor.setDataInicio(obterData(formattedTextFieldDataInicio.getText()));
				seletor.setDataFim(obterData(formattedTextFieldDataFim.getText()));

				List<VacinacaoVO> vacinacoes = controlador.listarVacinacoes(seletor);
				
				atualizarTabelaVacinacao(vacinacoes);
				
				tableResultado.getColumnModel().getColumn(0).setPreferredWidth(20);
				tableResultado.getColumnModel().getColumn(1).setPreferredWidth(40);
				tableResultado.getColumnModel().getColumn(2).setPreferredWidth(200);
			}
		});
		btnPesquisarVacinacao.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnPesquisarVacinacao.setBounds(10, 212, 95, 35);
		this.add(btnPesquisarVacinacao);

		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				VacinacaoVO vacinacao = new VacinacaoVO();
				int selRow = tableResultado.getSelectedRow();

				String value = tableResultado.getModel().getValueAt(selRow, 0).toString();

				vacinacao.setIdVacinacao(Integer.parseInt(value));

				VacinacaoController vacinacaoController = new VacinacaoController();
				String mensagem = vacinacaoController.excluir(vacinacao);
				JOptionPane.showMessageDialog(null, mensagem);

				formattedTextFieldDataInicio.setText("");
				formattedTextFieldDataFim.setText("");
				comboBoxAvaliacao.setSelectedIndex(-1);

				limparTabela();

			}
		});
		btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnExcluir.setBounds(233, 212, 95, 35);
		this.add(btnExcluir);

		tableResultado = new JTable();
		tableResultado.setBounds(10, 270, 430, 204);
		tableResultado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.limparTabela();
		this.add(tableResultado);

		JButton btnEditar = new JButton("EDITAR");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				VacinacaoVO vacinacao = new VacinacaoVO();
				String id = null;
				int selRow = tableResultado.getSelectedRow();
				if (selRow >= 0) {
					id = tableResultado.getModel().getValueAt(selRow, 0).toString();
				}
				
				if (id != null) {
					vacinacao.setIdVacinacao(Integer.parseInt(id));
				}

				if (comboBoxAvaliacao.getSelectedIndex() >= 0) {
					vacinacao.setAvaliacao(comboBoxAvaliacao.getSelectedIndex() + 1);					
					}

					VacinacaoController vacinacaoController = new VacinacaoController();
					String mensagem = vacinacaoController.atualizarBusca(vacinacao);
					JOptionPane.showMessageDialog(null, mensagem);
					
				}			
		});
		btnEditar.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnEditar.setBounds(123, 212, 95, 35);
		this.add(btnEditar);

		JButton btnLimpar = new JButton("LIMPAR");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formattedTextFieldDataInicio.setText("");
				formattedTextFieldDataFim.setText("");
				comboBoxAvaliacao.setSelectedIndex(-1);
				limparTabela();
			}
		});
		btnLimpar.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnLimpar.setBounds(345, 212, 95, 35);
		add(btnLimpar);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 42, 233, 12);
		add(separator);

		JLabel lblConsultaDeAplicaes = new JLabel("Consulta de aplicações");
		lblConsultaDeAplicaes.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsultaDeAplicaes.setForeground(Color.WHITE);
		lblConsultaDeAplicaes.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblConsultaDeAplicaes.setBounds(10, 11, 210, 31);
		add(lblConsultaDeAplicaes);

		JButton btnNewButton = new JButton("Imprimir");
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser janelaArquivos = new JFileChooser();

				int opcaoSelecionada = janelaArquivos.showSaveDialog(null);

				if (opcaoSelecionada == JFileChooser.APPROVE_OPTION) {
					String caminho = janelaArquivos.getSelectedFile().getAbsolutePath();

					VacinacaoController controller = new VacinacaoController();
					String mensagem = controller.gerarPlanilha(vacinacoesConsultadas, caminho);

					JOptionPane.showMessageDialog(null, mensagem);
				}
			}
		});
		btnNewButton.setBounds(340, 19, 89, 23);
		add(btnNewButton);
		
		try {
			MaskFormatter mascaraDataInicio = new MaskFormatter("##/##/####");		
			formattedTextFieldDataInicio = new JFormattedTextField(mascaraDataInicio);
			formattedTextFieldDataInicio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			formattedTextFieldDataInicio.setBounds(10, 90, 210, 30);
			this.add(formattedTextFieldDataInicio);	
		
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro no sistema, entre em contato com o administrador.");
			System.out.println("Causa da exceção: " + e.getMessage());
		}
		
		try {
			MaskFormatter mascaraDataFim = new MaskFormatter("##/##/####");		
			formattedTextFieldDataFim = new JFormattedTextField(mascaraDataFim);
			formattedTextFieldDataFim.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			formattedTextFieldDataFim.setBounds(230, 90, 210, 30);
			this.add(formattedTextFieldDataFim);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro no sistema, entre em contato com o administrador.");
			System.out.println("Causa da exceção: " + e.getMessage());
		}

		
		JLabel lblDataInicio = new JLabel("DATA INICIAL");
		lblDataInicio.setForeground(Color.DARK_GRAY);
		lblDataInicio.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblDataInicio.setBounds(10, 65, 134, 25);
		this.add(lblDataInicio);
		
		
		JLabel lblDataFim = new JLabel("DATA FINAL");
		lblDataFim.setForeground(Color.DARK_GRAY);
		lblDataFim.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblDataFim.setBounds(230, 66, 210, 25);
		this.add(lblDataFim);

	}

	private LocalDate obterData(String data) {
		LocalDate dataFormatada = null;				
		if (validarData(data)) {
			dataFormatada = LocalDate.parse(data, dateFormat);
		}	
		return dataFormatada;
	}

	private boolean validarData(String data) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);				
	    try {
	    	LocalDate date = LocalDate.parse(data, dateFormatter);
	        return true;
	    } catch (DateTimeParseException e) {			    	
	    	return false;			       
	    } 
	}

	protected void atualizarTabelaVacinacao(List<VacinacaoVO> vacinacoes) {

		vacinacoesConsultadas = vacinacoes;

		this.limparTabela();

		DefaultTableModel modelo = (DefaultTableModel) tableResultado.getModel();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		for (VacinacaoVO vacinacao : vacinacoes) {
			String dataFormatada = vacinacao.getDataVacinacao().format(formatter);
			String[] novaLinha = new String[] { vacinacao.getIdVacinacao() + "",
					vacinacao.getVacina().getIdVacina() + "", vacinacao.getPessoa().getNome(),
					vacinacao.getAvaliacao() + "", dataFormatada };
			modelo.addRow(novaLinha);

		}

	}

	private void limparTabela() {
		tableResultado
				.setModel(new DefaultTableModel(new String[][] { { "#", "Vacina", "Pessoa", "Avaliação", "Data" }, },
						new String[] { "#", "Vacina", "Pessoa", "Avaliação", "Data" }));

	}
}
