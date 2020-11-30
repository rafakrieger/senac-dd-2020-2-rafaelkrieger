package br.com.senac.vacinas.view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import br.com.senac.vacinas.controller.VacinaController;
import br.com.senac.vacinas.controller.VacinacaoController;

import javax.swing.JSeparator;
import javax.swing.JButton;

public class Principal extends JFrame {

	private JPanel contentPane;
	private static final String DEFAULT_COUNT = "0";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setResizable(false);
		setTitle("Controle de vacinas   ||   Covid-19");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 600);
		setBackground(new Color(32, 178, 170));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(contentPane);

			}
		});
		btnNewButton.setIcon(new ImageIcon(Principal.class.getResource("/br/com/senac/vacinas/utils/icons/icons8-home-32.png")));
		menuBar.add(btnNewButton);
		
		JMenu mnPessoas = new JMenu("Pessoas");
		mnPessoas.setIcon(new ImageIcon(Principal.class.getResource("/br/com/senac/vacinas/utils/icons/icons8-fila.png")));
		mnPessoas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnPessoas);
		
		JMenuItem mntmCadastrarPessoa = new JMenuItem("Cadastrar");
		mntmCadastrarPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transicao transicao = new Transicao();
				setContentPane(transicao);
				AddPessoa addPessoa = new AddPessoa();				
				setContentPane(addPessoa);
			}
		});
		mntmCadastrarPessoa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmCadastrarPessoa.setIcon(new ImageIcon(Principal.class.getResource("/br/com/senac/vacinas/utils/icons/icons8-add-folder-48.png")));
		mntmCadastrarPessoa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnPessoas.add(mntmCadastrarPessoa);
		
		JMenuItem mntmConsultarPessoa = new JMenuItem("Consultar");
		mntmConsultarPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transicao transicao = new Transicao();
				setContentPane(transicao);
				BuscaPessoa buscaPessoa = new BuscaPessoa();
				setContentPane(buscaPessoa);
			}
		});
		mntmConsultarPessoa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mntmConsultarPessoa.setIcon(new ImageIcon(Principal.class.getResource("/br/com/senac/vacinas/utils/icons/icons8-search-64.png")));
		mntmConsultarPessoa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnPessoas.add(mntmConsultarPessoa);
		
		JMenu mnVacinas = new JMenu("Vacinas");
		mnVacinas.setIcon(new ImageIcon(Principal.class.getResource("/br/com/senac/vacinas/utils/icons/icons8-dose-64.png")));
		mnVacinas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnVacinas);
		
		JMenuItem mntmCadastrarVacina = new JMenuItem("Cadastrar");
		mntmCadastrarVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transicao transicao = new Transicao();
				setContentPane(transicao);
				AddVacina addVacina = new AddVacina();
				setContentPane(addVacina);
			}
		});
		mntmCadastrarVacina.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		mntmCadastrarVacina.setIcon(new ImageIcon(Principal.class.getResource("/br/com/senac/vacinas/utils/icons/icons8-add-folder-48.png")));
		mntmCadastrarVacina.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnVacinas.add(mntmCadastrarVacina);
		
		JMenuItem mntmConsultarVacina = new JMenuItem("Consultar");
		mntmConsultarVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transicao transicao = new Transicao();
				setContentPane(transicao);
				BuscaVacina buscaVacina = new BuscaVacina();
				setContentPane(buscaVacina);
			}
		});
		mntmConsultarVacina.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		mntmConsultarVacina.setIcon(new ImageIcon(Principal.class.getResource("/br/com/senac/vacinas/utils/icons/icons8-search-64.png")));
		mntmConsultarVacina.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnVacinas.add(mntmConsultarVacina);
		
		JMenu mnAplicacoes = new JMenu("Aplicações");
		mnAplicacoes.setIcon(new ImageIcon(Principal.class.getResource("/br/com/senac/vacinas/utils/icons/icons8-insulin-pen-60.png")));
		mnAplicacoes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnAplicacoes);
		
		JMenuItem mntmCadastrarAplicacao = new JMenuItem("Cadastrar");
		mntmCadastrarAplicacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transicao transicao = new Transicao();
				setContentPane(transicao);
				AddVacinacao addVacinacao = new AddVacinacao();
				setContentPane(addVacinacao);
			}
		});
		mntmCadastrarAplicacao.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mntmCadastrarAplicacao.setIcon(new ImageIcon(Principal.class.getResource("/br/com/senac/vacinas/utils/icons/icons8-add-folder-48.png")));
		mntmCadastrarAplicacao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnAplicacoes.add(mntmCadastrarAplicacao);
		
		JMenuItem mntmConsultarAplicacao = new JMenuItem("Consultar");
		mntmConsultarAplicacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transicao transicao = new Transicao();
				setContentPane(transicao);
				BuscaVacinacao buscaVacinacao = new BuscaVacinacao();
				setContentPane(buscaVacinacao);
			}
		});
		mntmConsultarAplicacao.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
		mntmConsultarAplicacao.setIcon(new ImageIcon(Principal.class.getResource("/br/com/senac/vacinas/utils/icons/icons8-search-64.png")));
		mntmConsultarAplicacao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnAplicacoes.add(mntmConsultarAplicacao);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(32, 178, 170));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTotalVacinas = new JLabel("VACINAS CADASTRADAS");
		lblTotalVacinas.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalVacinas.setForeground(Color.DARK_GRAY);
		lblTotalVacinas.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblTotalVacinas.setBounds(22, 79, 178, 25);
		contentPane.add(lblTotalVacinas);
		
		JLabel lblTotalAplicaes = new JLabel("TOTAL DE APLICAÇÕES");
		lblTotalAplicaes.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalAplicaes.setForeground(Color.DARK_GRAY);
		lblTotalAplicaes.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblTotalAplicaes.setBounds(22, 194, 162, 25);
		contentPane.add(lblTotalAplicaes);
		
		JLabel lblMediaAvaliacao = new JLabel("MÉDIA DE AVALIAÇÃO");
		lblMediaAvaliacao.setHorizontalAlignment(SwingConstants.LEFT);
		lblMediaAvaliacao.setForeground(Color.DARK_GRAY);
		lblMediaAvaliacao.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblMediaAvaliacao.setBounds(22, 300, 162, 25);
		contentPane.add(lblMediaAvaliacao);
		
		JLabel lblRelatrio = new JLabel("Vacinação Covid-19");
		lblRelatrio.setHorizontalAlignment(SwingConstants.LEFT);
		lblRelatrio.setForeground(Color.WHITE);
		lblRelatrio.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblRelatrio.setBounds(12, 0, 261, 68);
		contentPane.add(lblRelatrio);
		
		VacinaController vacinaController = new VacinaController();
		JLabel lblRelVacinas = new JLabel(""+vacinaController.contarVacinas());
		lblRelVacinas.setForeground(Color.WHITE);
		lblRelVacinas.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelVacinas.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblRelVacinas.setBounds(36, 104, 114, 64);
		contentPane.add(lblRelVacinas);
		
		VacinacaoController vacinacaoController = new VacinacaoController();
		JLabel lblRelAplica = new JLabel(""+vacinacaoController.contarAplicacoes());
		lblRelAplica.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelAplica.setForeground(Color.WHITE);
		lblRelAplica.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblRelAplica.setBounds(36, 215, 114, 64);
		contentPane.add(lblRelAplica);
		
		JLabel lblRelAvaliacao = new JLabel(""+vacinacaoController.mediaAvaliacao());
		lblRelAvaliacao.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelAvaliacao.setForeground(Color.WHITE);
		lblRelAvaliacao.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblRelAvaliacao.setBounds(36, 322, 114, 64);
		contentPane.add(lblRelAvaliacao);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 56, 203, 12);
		contentPane.add(separator);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/br/com/senac/vacinas/utils/icons/hermes3.png")));
		lblNewLabel.setBounds(276, 70, 114, 297);
		contentPane.add(lblNewLabel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 166, 203, 2);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(12, 278, 203, 2);
		contentPane.add(separator_2);
	}
}
