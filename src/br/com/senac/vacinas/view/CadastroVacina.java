package br.com.senac.vacinas.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.awt.event.ActionEvent;

public class CadastroVacina extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBoxPais;
	private JComboBox comboBoxEstagio;
	private JComboBox comboBoxPesq;
	private JFormattedTextField formattedTextFieldData;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroVacina frame = new CadastroVacina();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroVacina() {
		setTitle("Cadastro de vacinas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 350);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPais = new JLabel("PAÍS DE ORIGEM");
		lblPais.setForeground(Color.DARK_GRAY);
		lblPais.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPais.setBounds(10, 0, 134, 25);
		contentPane.add(lblPais);
		
		String[] paises = {"China", "Rússia"};
		comboBoxPais = new JComboBox(paises);
		comboBoxPais.setSelectedIndex(-1);
		comboBoxPais.setFont(new Font("Dialog", Font.PLAIN, 14));
		comboBoxPais.setBounds(10, 25, 294, 30);
		contentPane.add(comboBoxPais);
		
		JLabel lblEstagio = new JLabel("ESTÁGIO DE PESQUISA");
		lblEstagio.setForeground(Color.DARK_GRAY);
		lblEstagio.setFont(new Font("Dialog", Font.BOLD, 14));
		lblEstagio.setBounds(10, 62, 179, 25);
		contentPane.add(lblEstagio);
		
		String[] estagio = {"1 - Inicial", "2 - Testes", "3 - Aplicação em massa"};
		comboBoxEstagio = new JComboBox(estagio);
		comboBoxEstagio.setSelectedIndex(-1);
		comboBoxEstagio.setFont(new Font("Dialog", Font.PLAIN, 14));
		comboBoxEstagio.setBounds(10, 85, 294, 30);
		contentPane.add(comboBoxEstagio);
		
		PesquisadorDAO dao = new PesquisadorDAO();
		List<PesquisadorVO> pesquisadores = dao.pesquisarTodos();
		comboBoxPesq = new JComboBox(pesquisadores.toArray());
		comboBoxPesq.setSelectedIndex(-1);
		comboBoxPesq.setFont(new Font("Dialog", Font.PLAIN, 14));
		comboBoxPesq.setBounds(10, 151, 294, 30);
		contentPane.add(comboBoxPesq);
		
		JLabel lblPesqResp = new JLabel("PESQUISADOR RESPONSÁVEL");
		lblPesqResp.setForeground(Color.DARK_GRAY);
		lblPesqResp.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPesqResp.setBounds(10, 126, 230, 25);
		contentPane.add(lblPesqResp);
		
		JLabel lblDataInicio = new JLabel("DATA DE INÍCIO DA PESQUISA");
		lblDataInicio.setForeground(Color.DARK_GRAY);
		lblDataInicio.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDataInicio.setBounds(10, 192, 230, 25);
		contentPane.add(lblDataInicio);
		
		try {
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");		
			formattedTextFieldData = new JFormattedTextField(mascaraData);
			formattedTextFieldData.setFont(new Font("Dialog", Font.PLAIN, 14));
			formattedTextFieldData.setBounds(10, 216, 294, 30);
			contentPane.add(formattedTextFieldData);
		
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
				JOptionPane.showMessageDialog(contentPane, mensagem);				
			}
		});
		btnSalvarVacina.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSalvarVacina.setBounds(66, 262, 185, 38);
		contentPane.add(btnSalvarVacina);
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
