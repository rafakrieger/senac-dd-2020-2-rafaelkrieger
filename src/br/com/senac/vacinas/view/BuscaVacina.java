package br.com.senac.vacinas.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.senac.vacinas.controller.PessoaController;
import br.com.senac.vacinas.controller.VacinaController;
import br.com.senac.vacinas.model.dao.PesquisadorDAO;
import br.com.senac.vacinas.model.seletores.SeletorPessoa;
import br.com.senac.vacinas.model.seletores.SeletorVacina;
import br.com.senac.vacinas.model.vo.PesquisadorVO;
import br.com.senac.vacinas.model.vo.PessoaVO;
import br.com.senac.vacinas.model.vo.VacinaVO;

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
import java.time.format.DateTimeFormatter;

import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class BuscaVacina extends JPanel {
	
	private JComboBox comboBoxPais;
	private JComboBox comboBoxEstagio;
	private JComboBox comboBoxPesq;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	private JTable tableResultadoVacinas;
	private static final String[] PAISES = {"China", "Rússia"};
	private static final String[] ESTAGIO = {"1 - Inicial", "2 - Testes", "3 - Aplicação em massa"};
	private List<VacinaVO> vacinasConsultadas;
	
	/**
	 * Create the panel.
	 */	
	
	public BuscaVacina() {
		
		setBounds(0, 60, 450, 500);
		this.setBackground(new Color(32, 178, 170));
		this.setBorder(null);
		this.setLayout(null);
		
		JLabel lblPais = new JLabel("PAÍS DE ORIGEM");
		lblPais.setForeground(Color.DARK_GRAY);
		lblPais.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPais.setBounds(10, 70, 134, 25);
		this.add(lblPais);		
		
		comboBoxPais = new JComboBox(PAISES);
		comboBoxPais.setSelectedIndex(-1);
		comboBoxPais.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxPais.setBounds(10, 95, 210, 30);
		this.add(comboBoxPais);
		
		JLabel lblEstagio = new JLabel("ESTÁGIO DE PESQUISA");
		lblEstagio.setForeground(Color.DARK_GRAY);
		lblEstagio.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblEstagio.setBounds(230, 70, 210, 25);
		this.add(lblEstagio);		
		
		comboBoxEstagio = new JComboBox(ESTAGIO);
		comboBoxEstagio.setSelectedIndex(-1);
		comboBoxEstagio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxEstagio.setBounds(230, 95, 210, 30);
		this.add(comboBoxEstagio);
		
		PesquisadorDAO dao = new PesquisadorDAO();
		List<PesquisadorVO> pesquisadores = dao.pesquisarTodos();
		comboBoxPesq = new JComboBox(pesquisadores.toArray());
		comboBoxPesq.setSelectedIndex(-1);
		comboBoxPesq.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxPesq.setBounds(10, 160, 430, 30);
		this.add(comboBoxPesq);
		
		JLabel lblPesqResp = new JLabel("PESQUISADOR RESPONSÁVEL");
		lblPesqResp.setForeground(Color.DARK_GRAY);
		lblPesqResp.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPesqResp.setBounds(10, 136, 210, 25);
		this.add(lblPesqResp);
		
		JButton btnPesquisarVacina = new JButton("PESQUISAR");
		btnPesquisarVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VacinaController controlador = new VacinaController();
				SeletorVacina seletor = new SeletorVacina();
				
				if (comboBoxPesq.getSelectedIndex() >= 0) {
					seletor.setPesquisador((PesquisadorVO) comboBoxPesq.getSelectedItem());
				}
				if (comboBoxPais.getSelectedIndex() >= 0) {
					seletor.setPaisOrigem(comboBoxPais.getSelectedItem().toString());

				}
				if (comboBoxEstagio.getSelectedIndex() >= 0) {
					seletor.setEstagioPesquisa(comboBoxEstagio.getSelectedIndex()+1);

				}

				List<VacinaVO> vacinas = controlador.listarVacinas(seletor);
				atualizarTabelaVacinas(vacinas);
			}
		});
		btnPesquisarVacina.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnPesquisarVacina.setBounds(10, 212, 95, 35);
		this.add(btnPesquisarVacina);
		
		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VacinaVO vacina = new VacinaVO();
				int selRow = tableResultadoVacinas.getSelectedRow();

				String value = tableResultadoVacinas.getModel().getValueAt(selRow, 0).toString();

				vacina.setIdVacina(Integer.parseInt(value));

				VacinaController vacinaController = new VacinaController();
				String mensagem = vacinaController.excluir(vacina);
				JOptionPane.showMessageDialog(null, mensagem);

				comboBoxEstagio.setSelectedIndex(-1);
				comboBoxPais.setSelectedIndex(-1);
				comboBoxPesq.setSelectedIndex(-1);
				
				limparTabela();
				
			}
		});
		btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnExcluir.setBounds(233, 212, 95, 35);
		this.add(btnExcluir);
		
		tableResultadoVacinas = new JTable();
		tableResultadoVacinas.setBounds(10, 270, 430, 204);
		this.add(tableResultadoVacinas);
		
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VacinaVO vacina = new VacinaVO();
				int selRow = tableResultadoVacinas.getSelectedRow();
				String id = tableResultadoVacinas.getModel().getValueAt(selRow, 0).toString();

				vacina.setIdVacina(Integer.parseInt(id));
				vacina.setPesquisador((PesquisadorVO) comboBoxPesq.getSelectedItem());
				vacina.setPaisOrigem(comboBoxPais.getSelectedItem().toString());
				String estagio = comboBoxEstagio.getSelectedItem().toString();
				vacina.setEstagioPesquisa(Integer.parseInt(estagio));

				VacinaController vacinaController = new VacinaController();
				String mensagem = vacinaController.atualizarBusca(vacina);
				JOptionPane.showMessageDialog(null, mensagem);

				comboBoxPais.setSelectedIndex(-1);
				comboBoxEstagio.setSelectedIndex(-1);
				comboBoxPesq.setSelectedIndex(-1);
				limparTabela();
				
			}
		});
		btnEditar.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnEditar.setBounds(123, 212, 95, 35);
		this.add(btnEditar);
		
		JButton btnLimpar = new JButton("LIMPAR");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxPais.setSelectedIndex(-1);
				comboBoxEstagio.setSelectedIndex(-1);
				comboBoxPesq.setSelectedIndex(-1);
				limparTabela();

			}
		});
		btnLimpar.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnLimpar.setBounds(345, 212, 95, 35);
		add(btnLimpar);
		
		JLabel lblConsultaDeVacinas = new JLabel("Consulta de vacinas");
		lblConsultaDeVacinas.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsultaDeVacinas.setForeground(Color.WHITE);
		lblConsultaDeVacinas.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblConsultaDeVacinas.setBounds(10, 11, 177, 31);
		add(lblConsultaDeVacinas);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 42, 213, 12);
		add(separator);
		
		JButton btnNewButton = new JButton("Imprimir");
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser janelaArquivos = new JFileChooser();

				int opcaoSelecionada = janelaArquivos.showSaveDialog(null);

				if (opcaoSelecionada == JFileChooser.APPROVE_OPTION) {
					String caminho = janelaArquivos.getSelectedFile().getAbsolutePath();

					VacinaController controller = new VacinaController();
					String mensagem = controller.gerarPlanilha(vacinasConsultadas, caminho);

					JOptionPane.showMessageDialog(null, mensagem);
				}
			}
		});
		btnNewButton.setBounds(340, 19, 89, 23);
		add(btnNewButton);


	}
	
	protected void atualizarTabelaVacinas(List<VacinaVO> vacinas) {
		// atualiza o atributo produtosConsultados

		vacinasConsultadas = vacinas;

		this.limparTabela();

		DefaultTableModel modelo = (DefaultTableModel) tableResultadoVacinas.getModel();

		
		for (VacinaVO vacina: vacinas) {
			String[] novaLinha = new String[] { vacina.getIdVacina()+ "", vacina.getPaisOrigem(), vacina.getPesquisador().toString(),
					vacina.getEstagioPesquisa() + "", };
			modelo.addRow(novaLinha);
		}

	}

	private void limparTabela() {
		tableResultadoVacinas.setModel(new DefaultTableModel(new String[][] { { "#", "País", "Pesquisador", "Estágio" }, },
				new String[] { "#", "País", "Pesquisador", "Estágio" }));

}
}
