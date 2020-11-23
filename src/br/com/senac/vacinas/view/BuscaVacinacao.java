package br.com.senac.vacinas.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.senac.vacinas.controller.PessoaController;
import br.com.senac.vacinas.controller.VacinaController;
import br.com.senac.vacinas.model.dao.PesquisadorDAO;
import br.com.senac.vacinas.model.vo.PesquisadorVO;
import br.com.senac.vacinas.model.vo.VacinaVO;

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

public class BuscaVacinacao extends JPanel {
	
	private JComboBox comboBoxVacina;
	private JComboBox comboBoxPessoa;
	private JComboBox comboBoxAvaliacao;
	private JFormattedTextField formattedTextFieldData;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	private JTable tableResultadoVacinas;
	private static final String[] TESTE_PESSOAS = {"Rafael Teste", "Altieste Teste", "Gustavo Teste"};
	private static final String[] PAISES = {"China", "Rússia"};
	private static final String[] AVALIACAO = {"1 - Péssima", "2 - Ruim", "3 - Regular", "4 - Boa", "5 - Ótima"};

	/**
	 * Create the panel.
	 */
	public BuscaVacinacao() {
		
		setBounds(100, 100, 450, 450);
		this.setBackground(new Color(32, 178, 170));
		this.setBorder(null);
		this.setLayout(null);
		
		JLabel lblVacina = new JLabel("VACINA");
		lblVacina.setForeground(Color.DARK_GRAY);
		lblVacina.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblVacina.setBounds(10, 36, 134, 25);
		this.add(lblVacina);
		
		comboBoxVacina = new JComboBox(PAISES);
		comboBoxVacina.setSelectedIndex(-1);
		comboBoxVacina.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxVacina.setBounds(10, 61, 179, 30);
		this.add(comboBoxVacina);
		
		JLabel lblPessoa = new JLabel("PESSOA");
		lblPessoa.setForeground(Color.DARK_GRAY);
		lblPessoa.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPessoa.setBounds(214, 36, 179, 25);
		this.add(lblPessoa);		
		
		comboBoxPessoa = new JComboBox(TESTE_PESSOAS);
		comboBoxPessoa.setSelectedIndex(-1);
		comboBoxPessoa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxPessoa.setBounds(214, 61, 210, 30);
		this.add(comboBoxPessoa);		
		
		comboBoxAvaliacao = new JComboBox(AVALIACAO);
		comboBoxAvaliacao.setSelectedIndex(-1);
		comboBoxAvaliacao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxAvaliacao.setBounds(10, 126, 179, 30);
		this.add(comboBoxAvaliacao);
		
		JLabel lblAvaliacao = new JLabel("AVALIAÇÃO");
		lblAvaliacao.setForeground(Color.DARK_GRAY);
		lblAvaliacao.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblAvaliacao.setBounds(10, 102, 210, 25);
		this.add(lblAvaliacao);
		
		JLabel lblData = new JLabel("DATA");
		lblData.setForeground(Color.DARK_GRAY);
		lblData.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblData.setBounds(214, 102, 230, 25);
		this.add(lblData);
		
		try {
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");		
			formattedTextFieldData = new JFormattedTextField(mascaraData);
			formattedTextFieldData.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			formattedTextFieldData.setBounds(214, 126, 210, 30);
			this.add(formattedTextFieldData);
		
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro no sistema, entre em contato com o administrador.");
			System.out.println("Causa da exceção: " + e.getMessage());
		}
		

		
		JButton btnPesquisarVacinacao = new JButton("PESQUISAR");
		btnPesquisarVacinacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VacinaVO vacina = new VacinaVO();
				vacina.setPaisOrigem((String) comboBoxVacina.getSelectedItem());
				vacina.setEstagioPesquisa(comboBoxPessoa.getSelectedIndex()+1);
				vacina.setPesquisador((PesquisadorVO) comboBoxAvaliacao.getSelectedItem());
			
				VacinaController vacinaController = new VacinaController();
				String mensagem = vacinaController.salvar(vacina);
				JOptionPane.showMessageDialog(null, mensagem);				
			}
		});
		btnPesquisarVacinacao.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnPesquisarVacinacao.setBounds(10, 167, 95, 38);
		this.add(btnPesquisarVacinacao);
		
		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnExcluir.setBounds(224, 167, 95, 38);
		this.add(btnExcluir);
		
		tableResultadoVacinas = new JTable();
		tableResultadoVacinas.setBounds(10, 222, 414, 178);
		this.add(tableResultadoVacinas);
		
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnEditar.setBounds(115, 167, 95, 38);
		this.add(btnEditar);
		
		JButton btnLimpar = new JButton("LIMPAR");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxVacina.setSelectedIndex(-1);
				comboBoxPessoa.setSelectedIndex(-1);
				comboBoxAvaliacao.setSelectedIndex(-1);
				formattedTextFieldData.setText("");
			}
		});
		btnLimpar.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnLimpar.setBounds(329, 167, 95, 38);
		add(btnLimpar);

	}

}
