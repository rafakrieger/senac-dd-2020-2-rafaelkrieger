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

public class BuscaVacina extends JPanel {
	
	private JComboBox comboBoxPais;
	private JComboBox comboBoxEstagio;
	private JComboBox comboBoxPesq;
	private JFormattedTextField formattedTextFieldData;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	private JTable tableResultadoVacinas;
	private static final String[] PAISES = {"China", "Rússia"};
	private static final String[] ESTAGIO = {"1 - Inicial", "2 - Testes", "3 - Aplicação em massa"};
	private static final String[] TESTE_PESSOAS = {"Rafael Teste", "Altieste Teste", "Gustavo Teste"};
	
	/**
	 * Create the panel.
	 */
	public BuscaVacina() {
		
		setBounds(100, 100, 450, 450);
		this.setBackground(new Color(32, 178, 170));
		this.setBorder(null);
		this.setLayout(null);
		
		JLabel lblPais = new JLabel("PAÍS DE ORIGEM");
		lblPais.setForeground(Color.DARK_GRAY);
		lblPais.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPais.setBounds(10, 36, 134, 25);
		this.add(lblPais);		
		
		comboBoxPais = new JComboBox(PAISES);
		comboBoxPais.setSelectedIndex(-1);
		comboBoxPais.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxPais.setBounds(10, 61, 179, 30);
		this.add(comboBoxPais);
		
		JLabel lblEstagio = new JLabel("ESTÁGIO DE PESQUISA");
		lblEstagio.setForeground(Color.DARK_GRAY);
		lblEstagio.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblEstagio.setBounds(214, 36, 179, 25);
		this.add(lblEstagio);		
		
		comboBoxEstagio = new JComboBox(ESTAGIO);
		comboBoxEstagio.setSelectedIndex(-1);
		comboBoxEstagio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxEstagio.setBounds(214, 61, 210, 30);
		this.add(comboBoxEstagio);
		
		//PesquisadorDAO dao = new PesquisadorDAO();
		//List<PesquisadorVO> pesquisadores = dao.pesquisarTodos();
		comboBoxPesq = new JComboBox(TESTE_PESSOAS);
		comboBoxPesq.setSelectedIndex(-1);
		comboBoxPesq.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxPesq.setBounds(10, 126, 179, 30);
		this.add(comboBoxPesq);
		
		JLabel lblPesqResp = new JLabel("PESQUISADOR RESPONSÁVEL");
		lblPesqResp.setForeground(Color.DARK_GRAY);
		lblPesqResp.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPesqResp.setBounds(10, 102, 210, 25);
		this.add(lblPesqResp);
		
		JLabel lblDataInicio = new JLabel("DATA DE INÍCIO DA PESQUISA");
		lblDataInicio.setForeground(Color.DARK_GRAY);
		lblDataInicio.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblDataInicio.setBounds(214, 102, 230, 25);
		this.add(lblDataInicio);
		
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
		

		
		JButton btnPesquisarVacina = new JButton("PESQUISAR");
		btnPesquisarVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VacinaVO vacina = new VacinaVO();
				vacina.setPaisOrigem((String) comboBoxPais.getSelectedItem());
				vacina.setEstagioPesquisa(comboBoxEstagio.getSelectedIndex()+1);
				vacina.setPesquisador((PesquisadorVO) comboBoxPesq.getSelectedItem());
			
				VacinaController vacinaController = new VacinaController();
				String mensagem = vacinaController.salvar(vacina);
				JOptionPane.showMessageDialog(null, mensagem);				
			}
		});
		btnPesquisarVacina.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnPesquisarVacina.setBounds(10, 167, 95, 38);
		this.add(btnPesquisarVacina);
		
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
		btnLimpar.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnLimpar.setBounds(329, 167, 95, 38);
		add(btnLimpar);

	}

}
