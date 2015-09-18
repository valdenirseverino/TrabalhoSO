package interfece_grafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class JanelaPrincipal {

	private JFrame frame;

	private final int LARGURA_TELA = 900;
	private final int ALTURA_TELA = 500;

	private String algoritmo;
	private int numCpu;
	private int quantum;

	public JanelaPrincipal(String algoritmo, int numCpu, int quantum) {
		initFrame();

		this.algoritmo = algoritmo;
		this.numCpu = numCpu;
		this.quantum = quantum;

	}

	private void initFrame() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(LARGURA_TELA, ALTURA_TELA);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getPainelCabecalho());
		frame.getContentPane().add(getPainelProcessos());
		frame.getContentPane().add(getPainelControle());
		frame.getContentPane().add(getCpuPainel());
	}

	private JPanel getCpuPainel() {

		JPanel cpuPanel = new JPanel();
		cpuPanel.setBackground(Color.RED);
		cpuPanel.setBounds(0, 41, LARGURA_TELA, 189);
		cpuPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cores de Processamento:");
		lblNewLabel.setBounds(10, 11, 199, 22);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		cpuPanel.add(lblNewLabel);

		JPanel panel = new JPanel();

		int qtd = 64;

		for (int i = 0; i < qtd; i++)
			panel.add(new JButton(i + ""));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(20, 44, 858, 118);

		cpuPanel.add(scrollPane);

		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(2, 0, 0, 0));

		return cpuPanel;

	}

	private JPanel getPainelProcessos() {
		JPanel processosPanel = new JPanel();
		processosPanel.setBounds(0, 229, 900, 212);
		processosPanel.setBackground(Color.GRAY);
		return processosPanel;
	}

	private JPanel getPainelCabecalho() {

		JPanel cabecalhoPanel = new JPanel();
		cabecalhoPanel.setBackground(Color.ORANGE);
		cabecalhoPanel.setBounds(0, 0, 900, 42);
		cabecalhoPanel.setLayout(null);

		JLabel tituloCabecalhoLabel = new JLabel("Algoritmo: xxxx");
		tituloCabecalhoLabel.setBounds(10, 11, 124, 22);
		tituloCabecalhoLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tituloCabecalhoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cabecalhoPanel.add(tituloCabecalhoLabel);

		return cabecalhoPanel;
	}

	private JPanel getPainelControle() {

		JPanel painelControle = new JPanel();
		painelControle.setBounds(0, 440, LARGURA_TELA, 31);
		painelControle.setBackground(Color.LIGHT_GRAY);
		painelControle.setLayout(new GridLayout(1, 0, 0, 0));

		painelControle.add(getButtonStart());
		painelControle.add(getButtonCriarProcesso());
		painelControle.add(getButtonStop());

		return painelControle;
	}

	private JButton getButtonStop() {
		JButton stopButton = new JButton("Stop");
		stopButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		stopButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		return stopButton;
	}

	private JButton getButtonCriarProcesso() {
		JButton criarNovoProcessoButton = new JButton("Novo Processo");
		criarNovoProcessoButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		criarNovoProcessoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(frame, "Criar Processo");
			}
		});
		return criarNovoProcessoButton;
	}

	private JButton getButtonStart() {
		JButton startButton = new JButton("Start");

		startButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(frame, "Start");
			}
		});
		return startButton;
	}

}
