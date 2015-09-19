package interfece_grafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import algoritmos.Generico;

public class JanelaPrincipal {

	private JFrame frame;

	private final int LARGURA_TELA = 900;
	private final int ALTURA_TELA = 500;

	private String algoritmo;
	private int numCpu;
	private int quantum;

	private Generico generico;

	// private HashMap<Integer, Processo> listaProcesos = new HashMap<>();
	private HashMap<Integer, JPanel> listaCPU = new HashMap<>();
	private ArrayList<JPanel> cores;

	private JPanel painelCpu;

	public JanelaPrincipal(String algoritmo, int numCpu, int quantum) {

		this.algoritmo = algoritmo;
		this.numCpu = numCpu;
		this.quantum = quantum;

		System.out.println("Janela Principal Criada");
		System.out.println("Algoritmo escolhido:" + algoritmo);
		System.out.println("Quantum escolhido:" + quantum);
		System.out.println("Numero de CPU:" + numCpu);

		initFrame();
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
		frame.getContentPane().add(getPainelCPU());
	}

	private JPanel getPainelCPU() {

		initPainelCPU();

		JPanel cpuPanel = new JPanel();
		cpuPanel.setBackground(Color.DARK_GRAY);
		cpuPanel.setBounds(0, 41, 900, 302);
		cpuPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cores de Processamento: " + numCpu);
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.setBounds(10, 11, 266, 22);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(20, 44, 858, 229);
		scrollPane.setViewportView(painelCpu);

		cpuPanel.add(lblNewLabel);
		cpuPanel.add(scrollPane);

		if (quantum > 0) {

			JLabel valorQuantumLabel = new JLabel("Valor do Quantum: " + quantum);
			valorQuantumLabel.setHorizontalAlignment(SwingConstants.LEFT);
			valorQuantumLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			valorQuantumLabel.setForeground(Color.YELLOW);
			valorQuantumLabel.setBounds(286, 11, 266, 22);

			cpuPanel.add(valorQuantumLabel);
		}

		return cpuPanel;
	}

	private void initPainelCPU() {

		this.painelCpu = new JPanel();
		this.painelCpu.setLayout(new GridLayout(4, 2, 0, 0));

		cores = new ArrayList<>();

		for (int i = 0; i < this.numCpu; i++) {

			JPanel panel = new JPanel();
			panel.setSize(50, 50);
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

			cores.add(panel);
		}

		for (JPanel panel : cores)
			this.painelCpu.add(panel);
	}

	private JPanel getPainelProcessos() {
		JPanel processosPanel = new JPanel();
		processosPanel.setBounds(0, 342, 900, 99);
		processosPanel.setBackground(Color.GRAY);
		return processosPanel;
	}

	private JPanel getPainelCabecalho() {

		JPanel cabecalhoPanel = new JPanel();
		cabecalhoPanel.setBackground(Color.ORANGE);
		cabecalhoPanel.setBounds(0, 0, 900, 42);
		cabecalhoPanel.setLayout(null);

		JLabel tituloCabecalhoLabel = new JLabel("Algoritmo: " + algoritmo);
		tituloCabecalhoLabel.setBounds(10, 11, 852, 22);
		tituloCabecalhoLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tituloCabecalhoLabel.setHorizontalAlignment(SwingConstants.LEFT);
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
				frame.setVisible(false);
				frame.dispose();
				new JanelaInicial();
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

				if (generico != null)
					generico.adicionarProcesso();
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

				try {

					generico = new Generico(listaCPU, cores, painelCpu);
					generico.start();

					startButton.setEnabled(false);

				} catch (Exception e) {
					System.out.println("botao de start desativado");
				}
			}
		});

		return startButton;
	}

}
