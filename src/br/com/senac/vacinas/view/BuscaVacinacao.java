package br.com.senac.vacinas.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.senac.vacinas.controller.PessoaController;
import br.com.senac.vacinas.controller.VacinaController;
import br.com.senac.vacinas.controller.VacinacaoController;
import br.com.senac.vacinas.model.dao.PesquisadorDAO;
import br.com.senac.vacinas.model.dao.PessoaDAO;
import br.com.senac.vacinas.model.dao.VacinaDAO;
import br.com.senac.vacinas.model.seletores.SeletorVacina;
import br.com.senac.vacinas.model.seletores.SeletorVacinacao;
import br.com.senac.vacinas.model.vo.PesquisadorVO;
import br.com.senac.vacinas.model.vo.PessoaVO;
import br.com.senac.vacinas.model.vo.VacinaVO;
import br.com.senac.vacinas.model.vo.VacinacaoVO;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;

import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class BuscaVacinacao extends JPanel {
	
	private JComboBox comboBoxVacina;
	private JComboBox comboBoxAvaliacao;
	private JComboBox comboBoxPessoa;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	private JTable tableResultado;
	private static final String[] AVALIACAO = {"1 - Péssima", "2 - Ruim", "3 - Regular", "4 - Boa", "5 - Ótima"};
	private List<VacinacaoVO> vacinacoesConsultadas;

	/**
	 * Create the panel.
	 */
	public BuscaVacinacao() {
		
		setBounds(0, 60, 450, 500);
		this.setBackground(new Color(32, 178, 170));
		this.setBorder(null);
		this.setLayout(null);
		
		JLabel lblVacina = new JLabel("VACINA");
		lblVacina.setForeground(Color.DARK_GRAY);
		lblVacina.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblVacina.setBounds(10, 65, 134, 25);
		this.add(lblVacina);
		
		VacinaDAO dao = new VacinaDAO();
		List<VacinaVO> vacinas = dao.pesquisarTodos();		
		comboBoxVacina = new JComboBox(vacinas.toArray());
		comboBoxVacina.setSelectedIndex(-1);
		comboBoxVacina.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxVacina.setBounds(10, 90, 210, 30);
		this.add(comboBoxVacina);
		
		JLabel lblPessoa = new JLabel("PESSOA");
		lblPessoa.setForeground(Color.DARK_GRAY);
		lblPessoa.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPessoa.setBounds(10, 132, 179, 25);
		this.add(lblPessoa);	
		
		comboBoxAvaliacao = new JComboBox(AVALIACAO);
		comboBoxAvaliacao.setSelectedIndex(-1);
		comboBoxAvaliacao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxAvaliacao.setBounds(230, 90, 210, 30);
		this.add(comboBoxAvaliacao);		
			
		PessoaDAO daoPessoa = new PessoaDAO();
		List<PessoaVO> pessoas = daoPessoa.pesquisarTodos();
		comboBoxPessoa = new JComboBox(pessoas.toArray());
		comboBoxPessoa.setSelectedIndex(-1);
		comboBoxPessoa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxPessoa.setBounds(10, 156, 430, 30);
		add(comboBoxPessoa);

		
		JLabel lblAvaliacao = new JLabel("AVALIAÇÃO");
		lblAvaliacao.setForeground(Color.DARK_GRAY);
		lblAvaliacao.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblAvaliacao.setBounds(230, 66, 210, 25);
		this.add(lblAvaliacao);

		
		JButton btnPesquisarVacinacao = new JButton("PESQUISAR");
		btnPesquisarVacinacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VacinacaoController controlador = new VacinacaoController();
				SeletorVacinacao seletor = new SeletorVacinacao();
				
				seletor.setAvaliacao(comboBoxAvaliacao.getSelectedIndex() + 1);
				seletor.setPessoa((PessoaVO)comboBoxPessoa.getSelectedItem());
				seletor.setVacina((VacinaVO) comboBoxVacina.getSelectedItem());
				
				List<VacinacaoVO> vacinacoes = controlador.listarVacinacoes(seletor);
				atualizarTabelaVacinacao(vacinacoes);	
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

				comboBoxVacina.setSelectedIndex(-1);
				comboBoxPessoa.setSelectedIndex(-1);
				comboBoxAvaliacao.setSelectedIndex(-1);
				
				limparTabela();
				
			}
		});
		btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnExcluir.setBounds(233, 212, 95, 35);
		this.add(btnExcluir);
		
		tableResultado = new JTable();
		tableResultado.setBounds(10, 270, 430, 204);
		this.add(tableResultado);
		
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VacinacaoVO vacinacao = new VacinacaoVO();
				int selRow = tableResultado.getSelectedRow();
				String id = tableResultado.getModel().getValueAt(selRow, 0).toString();

				vacinacao.setIdVacinacao(Integer.parseInt(id));
				vacinacao.setAvaliacao(comboBoxAvaliacao.getSelectedIndex() + 1);
				vacinacao.setPessoa((PessoaVO)comboBoxPessoa.getSelectedItem());
				vacinacao.setVacina((VacinaVO) comboBoxVacina.getSelectedItem());

				VacinacaoController vacinacaoController = new VacinacaoController();
				String mensagem = vacinacaoController.atualizarBusca(vacinacao);
				JOptionPane.showMessageDialog(null, mensagem);

				comboBoxVacina.setSelectedIndex(-1);
				comboBoxPessoa.setSelectedIndex(-1);
				comboBoxAvaliacao.setSelectedIndex(-1);
				limparTabela();
			}
		});
		btnEditar.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnEditar.setBounds(123, 212, 95, 35);
		this.add(btnEditar);
		
		JButton btnLimpar = new JButton("LIMPAR");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxVacina.setSelectedIndex(-1);
				comboBoxPessoa.setSelectedIndex(-1);
				comboBoxAvaliacao.setSelectedIndex(-1);
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
		

	}
	
	protected void atualizarTabelaVacinacao(List<VacinacaoVO> vacinacoes) {
		
		vacinacoesConsultadas = vacinacoes;

		this.limparTabela();

		DefaultTableModel modelo = (DefaultTableModel) tableResultado.getModel();
		
		for (VacinacaoVO vacinacao: vacinacoes) {
			
			String[] novaLinha = new String[] { vacinacao.getIdVacinacao()+ "", vacinacao.getVacina().getIdVacina()+ "", vacinacao.getPessoa().getNome(),
					vacinacao.getAvaliacao() + "", };
			modelo.addRow(novaLinha);
		}

	}

	private void limparTabela() {
		tableResultado.setModel(new DefaultTableModel(new String[][] { { "#", "Vacina", "Pessoa", "Avaliação" }, },
				new String[] { "#", "Vacina", "Pessoa", "Avaliação" }));

}
}
