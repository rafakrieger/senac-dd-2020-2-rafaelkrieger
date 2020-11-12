package br.com.senac.vacinas.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import br.com.senac.vacinas.controller.VacinaController;
import br.com.senac.vacinas.model.dao.PesquisadorDAO;
import br.com.senac.vacinas.model.vo.PesquisadorVO;
import br.com.senac.vacinas.model.vo.VacinaVO;

public class AddVacina extends JPanel {
	
	private JComboBox comboBoxPais;
	private JComboBox comboBoxEstagio;
	private JComboBox comboBoxPesq;
	private JFormattedTextField formattedTextFieldData;
	private static final String[] PAISES = {"China", "Rússia"};
	private static final String[] ESTAGIO = {"1 - Inicial", "2 - Testes", "3 - Aplicação em massa"};
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	private static final String[] TESTE_PESSOAS = {"Rafael Teste", "Altieste Teste", "Gustavo Teste"};

	/**
	 * Create the panel.
	 */
	public AddVacina() {
		
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
		comboBoxPais.setBounds(10, 61, 414, 30);
		this.add(comboBoxPais);
		
		JLabel lblEstagio = new JLabel("ESTÁGIO DE PESQUISA");
		lblEstagio.setForeground(Color.DARK_GRAY);
		lblEstagio.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblEstagio.setBounds(10, 98, 179, 25);
		this.add(lblEstagio);
				
		comboBoxEstagio = new JComboBox(ESTAGIO);
		comboBoxEstagio.setSelectedIndex(-1);
		comboBoxEstagio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxEstagio.setBounds(10, 121, 414, 30);
		this.add(comboBoxEstagio);
		
		//PesquisadorDAO dao = new PesquisadorDAO();
		//List<PesquisadorVO> pesquisadores = dao.pesquisarTodos();
		comboBoxPesq = new JComboBox(TESTE_PESSOAS);
		comboBoxPesq.setSelectedIndex(-1);
		comboBoxPesq.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxPesq.setBounds(10, 187, 414, 30);
		this.add(comboBoxPesq);
		
		JLabel lblPesqResp = new JLabel("PESQUISADOR RESPONSÁVEL");
		lblPesqResp.setForeground(Color.DARK_GRAY);
		lblPesqResp.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPesqResp.setBounds(10, 162, 230, 25);
		this.add(lblPesqResp);
		
		JLabel lblDataInicio = new JLabel("DATA DE INÍCIO DA PESQUISA");
		lblDataInicio.setForeground(Color.DARK_GRAY);
		lblDataInicio.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblDataInicio.setBounds(10, 228, 230, 25);
		this.add(lblDataInicio);
		
		try {
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");		
			formattedTextFieldData = new JFormattedTextField(mascaraData);
			formattedTextFieldData.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			formattedTextFieldData.setBounds(10, 252, 414, 30);
			this.add(formattedTextFieldData);
		
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro no sistema, entre em contato com o administrador.");
			System.out.println("Causa da exceção: " + e.getMessage());
		}
		

		
		JButton btnSalvarVacina = new JButton("SALVAR");
		btnSalvarVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VacinaVO vacina = new VacinaVO();
				vacina.setPaisOrigem((String) comboBoxPais.getSelectedItem());
				vacina.setEstagioPesquisa(comboBoxEstagio.getSelectedIndex()+1);
				vacina.setPesquisador((PesquisadorVO) comboBoxPesq.getSelectedItem());
				vacina.setDataInicio(obterData(formattedTextFieldData.getText()));
				
				VacinaController vacinaController = new VacinaController();
				String mensagem = vacinaController.salvar(vacina);
				JOptionPane.showMessageDialog(null, mensagem);				
			}
		});
		btnSalvarVacina.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnSalvarVacina.setBounds(64, 324, 296, 38);
		this.add(btnSalvarVacina);
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

}
