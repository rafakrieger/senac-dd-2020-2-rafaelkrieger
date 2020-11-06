package br.com.senac.vacinas.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;


import br.com.senac.vacinas.controller.VacinacaoController;
import br.com.senac.vacinas.model.dao.PessoaDAO;
import br.com.senac.vacinas.model.vo.VacinacaoVO;
import br.com.senac.vacinas.model.dao.VacinaDAO;
import br.com.senac.vacinas.model.vo.VacinaVO;
import br.com.senac.vacinas.model.vo.PessoaVO;

import java.awt.Color;
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

public class AddVacinacao extends JPanel {

	private static final String[] AVALIACAO = {"1 - Péssima", "2 - Ruim", "3 - Regular", "4 - Boa", "5 - Ótima"};
	private JFormattedTextField formattedTextFieldData;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	/**
	 * Create the panel.
	 */
	public AddVacinacao() {
		setBounds(100, 100, 450, 450);		
		this.setBackground(SystemColor.menu);
		this.setBorder(null);		
		this.setLayout(null);
		
		JLabel lblVacina = new JLabel("VACINA");
		lblVacina.setForeground(Color.DARK_GRAY);
		lblVacina.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblVacina.setBounds(10, 36, 134, 25);
		this.add(lblVacina);		
		
		VacinaDAO vacinaDao = new VacinaDAO();
		List<VacinaVO> vacinas = vacinaDao.pesquisarTodos();
		final JComboBox comboBoxVacina = new JComboBox(vacinas.toArray());
		comboBoxVacina.setSelectedIndex(-1);
		comboBoxVacina.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxVacina.setBounds(10, 61, 414, 30);
		this.add(comboBoxVacina);
		
		JLabel lblPessoa = new JLabel("PESSOA");
		lblPessoa.setForeground(Color.DARK_GRAY);
		lblPessoa.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPessoa.setBounds(10, 98, 179, 25);
		this.add(lblPessoa);
		
		PessoaDAO pessoaDao = new PessoaDAO();
		List<PessoaVO> pessoas = pessoaDao.pesquisarTodos();
		final JComboBox comboBoxPessoa= new JComboBox(pessoas.toArray());
		comboBoxPessoa.setSelectedIndex(-1);
		comboBoxPessoa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxPessoa.setBounds(10, 121, 414, 30);
		this.add(comboBoxPessoa);
		
		final JComboBox comboBoxAvaliacao = new JComboBox(AVALIACAO);
		comboBoxAvaliacao.setSelectedIndex(-1);
		comboBoxAvaliacao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxAvaliacao.setBounds(10, 187, 414, 30);
		this.add(comboBoxAvaliacao);
		
		JLabel lblAvaliacao = new JLabel("AVALIAÇÃO");
		lblAvaliacao.setForeground(Color.DARK_GRAY);
		lblAvaliacao.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblAvaliacao.setBounds(10, 162, 230, 25);
		this.add(lblAvaliacao);
		
		JLabel lblData = new JLabel("DATA");
		lblData.setForeground(Color.DARK_GRAY);
		lblData.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblData.setBounds(10, 228, 230, 25);
		this.add(lblData);
		
		
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
		

		
		JButton btnSalvarVacinacao = new JButton("SALVAR");
		btnSalvarVacinacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VacinacaoVO vacinacao = new VacinacaoVO();
				vacinacao.setVacina((VacinaVO) comboBoxVacina.getSelectedItem());
				vacinacao.setPessoa((PessoaVO) comboBoxPessoa.getSelectedItem());
				vacinacao.setAvaliacao(comboBoxAvaliacao.getSelectedIndex()+1);
				vacinacao.setDataVacinacao(obterData(formattedTextFieldData.getText()));
				
				VacinacaoController vacinacaoController = new VacinacaoController();
				String mensagem = vacinacaoController.salvar(vacinacao);
				JOptionPane.showMessageDialog(null, mensagem);				
			}
		});
		btnSalvarVacinacao.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnSalvarVacinacao.setBounds(64, 324, 296, 38);
		this.add(btnSalvarVacinacao);
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


