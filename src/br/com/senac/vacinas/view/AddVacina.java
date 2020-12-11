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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import br.com.senac.vacinas.controller.PesquisadorController;
import br.com.senac.vacinas.controller.VacinaController;
import br.com.senac.vacinas.model.vo.PesquisadorVO;
import br.com.senac.vacinas.model.vo.VacinaVO;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class AddVacina extends JPanel {
	
	private JComboBox comboBoxPais;
	private JComboBox comboBoxEstagio;
	private JComboBox comboBoxPesq;
	private JFormattedTextField formattedTextFieldData;
	private static final String[] PAISES = {"China", "Rússia", "EUA", "Alemanha", "Reino Unido", "Brasil", "França", "Outros"};
	private static final String[] ESTAGIO = {"1 - Inicial", "2 - Testes", "3 - Aplicação em massa"};
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");	

	/**
	 * Create the panel.
	 */
	public AddVacina() {
		
		setBounds(0, 50, 450, 450);		
		this.setBackground(new Color(32, 178, 170));
		this.setBorder(null);		
		this.setLayout(null);
		
		JLabel lblPais = new JLabel("PAÍS DE ORIGEM");
		lblPais.setForeground(Color.DARK_GRAY);
		lblPais.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPais.setBounds(10, 72, 134, 25);
		this.add(lblPais);		
		
		comboBoxPais = new JComboBox(PAISES);
		comboBoxPais.setSelectedIndex(-1);
		comboBoxPais.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxPais.setBounds(10, 97, 414, 30);
		this.add(comboBoxPais);
		
		JLabel lblEstagio = new JLabel("ESTÁGIO DE PESQUISA");
		lblEstagio.setForeground(Color.DARK_GRAY);
		lblEstagio.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblEstagio.setBounds(10, 134, 179, 25);
		this.add(lblEstagio);
				
		comboBoxEstagio = new JComboBox(ESTAGIO);
		comboBoxEstagio.setSelectedIndex(-1);
		comboBoxEstagio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxEstagio.setBounds(10, 157, 414, 30);
		this.add(comboBoxEstagio);
		
		PesquisadorController controller = new PesquisadorController();
		List<PesquisadorVO> pesquisadores = controller.pesquisarTodos();
		comboBoxPesq = new JComboBox(pesquisadores.toArray());
		comboBoxPesq.setSelectedIndex(-1);
		comboBoxPesq.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxPesq.setBounds(10, 223, 414, 30);
		this.add(comboBoxPesq);
		
		JLabel lblPesqResp = new JLabel("PESQUISADOR RESPONSÁVEL");
		lblPesqResp.setForeground(Color.DARK_GRAY);
		lblPesqResp.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPesqResp.setBounds(10, 198, 230, 25);
		this.add(lblPesqResp);
		
		JLabel lblDataInicio = new JLabel("DATA DE INÍCIO DA PESQUISA");
		lblDataInicio.setForeground(Color.DARK_GRAY);
		lblDataInicio.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblDataInicio.setBounds(10, 264, 230, 25);
		this.add(lblDataInicio);
		
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
		btnSalvarVacina.setBounds(64, 360, 296, 38);
		this.add(btnSalvarVacina);
		
		JLabel lblCadastroDeVacinas = new JLabel("Cadastro de vacinas");
		lblCadastroDeVacinas.setHorizontalAlignment(SwingConstants.LEFT);
		lblCadastroDeVacinas.setForeground(Color.WHITE);
		lblCadastroDeVacinas.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblCadastroDeVacinas.setBounds(10, 0, 177, 42);
		add(lblCadastroDeVacinas);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 39, 203, 12);
		add(separator);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxPais.setSelectedIndex(-1);
				comboBoxEstagio.setSelectedIndex(-1);
				comboBoxPesq.setSelectedIndex(-1);
				formattedTextFieldData.setText("");
			}
		});
		btnLimpar.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnLimpar.setBounds(335, 56, 89, 23);
		add(btnLimpar);
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
