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

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTable tableTotalVacinas;
	private JTable tableMasculino;
	private JTable tableFeminino;
	private JTable tableVoluntario;
	private JTable tableMediaAvaliacao;

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
		setBounds(100, 100, 600, 600);
		setBackground(new Color(32, 178, 170));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnPessoas = new JMenu("Pessoas");
		mnPessoas.setIcon(new ImageIcon(Principal.class.getResource("/br/com/senac/vacinas/icons/icons8-fila.png")));
		mnPessoas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnPessoas);
		
		JMenuItem mntmCadastrarPessoa = new JMenuItem("Cadastrar");
		mntmCadastrarPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPessoa addPessoa = new AddPessoa();				
				setContentPane(addPessoa);
			}
		});
		mntmCadastrarPessoa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmCadastrarPessoa.setIcon(new ImageIcon(Principal.class.getResource("/br/com/senac/vacinas/icons/icons8-adicionar-usuário-masculino.png")));
		mntmCadastrarPessoa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnPessoas.add(mntmCadastrarPessoa);
		
		JMenuItem mntmConsultarPessoa = new JMenuItem("Consultar");
		mntmConsultarPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscaPessoa buscaPessoa = new BuscaPessoa();
				setContentPane(buscaPessoa);
			}
		});
		mntmConsultarPessoa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mntmConsultarPessoa.setIcon(new ImageIcon(Principal.class.getResource("/br/com/senac/vacinas/icons/icons8-cardápio.png")));
		mntmConsultarPessoa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnPessoas.add(mntmConsultarPessoa);
		
		JMenu mnVacinas = new JMenu("Vacinas");
		mnVacinas.setIcon(new ImageIcon(Principal.class.getResource("/br/com/senac/vacinas/icons/icons8-dose-64.png")));
		mnVacinas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnVacinas);
		
		JMenuItem mntmCadastrarVacina = new JMenuItem("Cadastrar");
		mntmCadastrarVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddVacina addVacina = new AddVacina();
				setContentPane(addVacina);
			}
		});
		mntmCadastrarVacina.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		mntmCadastrarVacina.setIcon(new ImageIcon(Principal.class.getResource("/br/com/senac/vacinas/icons/icons8-add-folder-48.png")));
		mntmCadastrarVacina.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnVacinas.add(mntmCadastrarVacina);
		
		JMenuItem mntmConsultarVacina = new JMenuItem("Consultar");
		mntmConsultarVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscaVacina buscaVacina = new BuscaVacina();
				setContentPane(buscaVacina);
			}
		});
		mntmConsultarVacina.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		mntmConsultarVacina.setIcon(new ImageIcon(Principal.class.getResource("/br/com/senac/vacinas/icons/icons8-cardápio.png")));
		mntmConsultarVacina.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnVacinas.add(mntmConsultarVacina);
		
		JMenu mnAplicacoes = new JMenu("Aplicações");
		mnAplicacoes.setIcon(new ImageIcon(Principal.class.getResource("/br/com/senac/vacinas/icons/icons8-insulin-pen-60.png")));
		mnAplicacoes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnAplicacoes);
		
		JMenuItem mntmCadastrarAplicacao = new JMenuItem("Cadastrar");
		mntmCadastrarAplicacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddVacinacao addVacinacao = new AddVacinacao();
				setContentPane(addVacinacao);
			}
		});
		mntmCadastrarAplicacao.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mntmCadastrarAplicacao.setIcon(new ImageIcon(Principal.class.getResource("/br/com/senac/vacinas/icons/icons8-add-folder-48 (1).png")));
		mntmCadastrarAplicacao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnAplicacoes.add(mntmCadastrarAplicacao);
		
		JMenuItem mntmConsultarAplicacao = new JMenuItem("Consultar");
		mntmConsultarAplicacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscaVacinacao buscaVacinacao = new BuscaVacinacao();
				setContentPane(buscaVacinacao);
			}
		});
		mntmConsultarAplicacao.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
		mntmConsultarAplicacao.setIcon(new ImageIcon(Principal.class.getResource("/br/com/senac/vacinas/icons/icons8-cardápio.png")));
		mntmConsultarAplicacao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnAplicacoes.add(mntmConsultarAplicacao);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(32, 178, 170));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTotalVacinas = new JLabel("Total de Vacinas Cadastradas");
		lblTotalVacinas.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalVacinas.setForeground(Color.DARK_GRAY);
		lblTotalVacinas.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTotalVacinas.setBounds(12, 81, 576, 25);
		contentPane.add(lblTotalVacinas);
		
		tableTotalVacinas = new JTable();
		tableTotalVacinas.setBounds(227, 107, 147, 40);
		contentPane.add(tableTotalVacinas);
		
		JLabel lblTotalAplicaes = new JLabel("Total de Aplicações");
		lblTotalAplicaes.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalAplicaes.setForeground(Color.DARK_GRAY);
		lblTotalAplicaes.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTotalAplicaes.setBounds(12, 192, 576, 25);
		contentPane.add(lblTotalAplicaes);
		
		JLabel lblMasculino = new JLabel("Sexo Masculino");
		lblMasculino.setHorizontalAlignment(SwingConstants.CENTER);
		lblMasculino.setForeground(Color.DARK_GRAY);
		lblMasculino.setFont(new Font("Dialog", Font.BOLD, 13));
		lblMasculino.setBounds(-15, 229, 242, 25);
		contentPane.add(lblMasculino);
		
		JLabel lblFeminino = new JLabel("Sexo Feminino");
		lblFeminino.setHorizontalAlignment(SwingConstants.CENTER);
		lblFeminino.setForeground(Color.DARK_GRAY);
		lblFeminino.setFont(new Font("Dialog", Font.BOLD, 13));
		lblFeminino.setBounds(175, 229, 242, 25);
		contentPane.add(lblFeminino);
		
		tableMasculino = new JTable();
		tableMasculino.setBounds(36, 257, 147, 40);
		contentPane.add(tableMasculino);
		
		tableFeminino = new JTable();
		tableFeminino.setBounds(227, 257, 147, 40);
		contentPane.add(tableFeminino);
		
		JLabel lblVoluntarios = new JLabel("Voluntários\n");
		lblVoluntarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblVoluntarios.setForeground(Color.DARK_GRAY);
		lblVoluntarios.setFont(new Font("Dialog", Font.BOLD, 13));
		lblVoluntarios.setBounds(358, 229, 242, 25);
		contentPane.add(lblVoluntarios);
		
		tableVoluntario = new JTable();
		tableVoluntario.setBounds(409, 257, 147, 40);
		contentPane.add(tableVoluntario);
		
		JLabel lblMediaAvaliacao = new JLabel("Média da Avaliação da Vacinação");
		lblMediaAvaliacao.setHorizontalAlignment(SwingConstants.CENTER);
		lblMediaAvaliacao.setForeground(Color.DARK_GRAY);
		lblMediaAvaliacao.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMediaAvaliacao.setBounds(12, 358, 576, 25);
		contentPane.add(lblMediaAvaliacao);
		
		tableMediaAvaliacao = new JTable();
		tableMediaAvaliacao.setBounds(227, 387, 147, 40);
		contentPane.add(tableMediaAvaliacao);
		
		JLabel lblRelatrio = new JLabel("Relatório");
		lblRelatrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelatrio.setForeground(Color.DARK_GRAY);
		lblRelatrio.setFont(new Font("Dialog", Font.BOLD, 20));
		lblRelatrio.setBounds(12, 0, 576, 68);
		contentPane.add(lblRelatrio);
	}
}
