package interfece_grafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import algoritmos.EscalonadorLTG;

public class JanelaPrincipal {

	// ==========================================================================================================================================
	// ATRIBUTOS DA JANELA
	// ==========================================================================================================================================

	private JFrame frame;
	private int LARGURA_TELA = 900;
	private int numCpu;

	// ==========================================================================================================================================
	// ATRIBUTOS DO ALGORITMO
	// ==========================================================================================================================================

	private JPanel listaProcessosFinalizadosPanel;
	private JPanel filaAptosConteiner;
	private JPanel coresProcesssamentoPanel;

	private String nomeAlgoritmo;
	private int quantum;

	private ArrayList<JPanel> celulasProcessamento;

	private EscalonadorLTG escalonador;

	// ==========================================================================================================================================
	// CONSTRUTOR DA CLASSE
	// ==========================================================================================================================================

	public JanelaPrincipal(String algoritmo, int numCpu, int quantum, int numProcessos) {

		this.nomeAlgoritmo = algoritmo;
		this.numCpu = numCpu;
		this.quantum = quantum;

		initPainelCabecalho();
		initPainelControle();
		initPainelProcessos();
		initPainelCPU();

		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(LARGURA_TELA, 700);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		frame.getContentPane().add(cabecalhoPanel);
		frame.getContentPane().add(processosPanel);
		frame.getContentPane().add(controlePanel);
		frame.getContentPane().add(cpuPanel);

		escalonador = new EscalonadorLTG(numProcessos, celulasProcessamento, listaProcessosFinalizadosPanel,
				filaAptosConteiner);

	}

	// ==========================================================================================================================================
	// CABECHALHO
	// ==========================================================================================================================================

	private JPanel cabecalhoPanel;

	private void initPainelCabecalho() {

		JLabel tituloCabecalhoLabel = new JLabel("Algoritmo: " + nomeAlgoritmo);
		tituloCabecalhoLabel.setBounds(10, 11, 852, 22);
		tituloCabecalhoLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tituloCabecalhoLabel.setHorizontalAlignment(SwingConstants.LEFT);

		cabecalhoPanel = new JPanel();
		cabecalhoPanel.setBackground(Color.ORANGE);
		cabecalhoPanel.setBounds(0, 0, 900, 42);
		cabecalhoPanel.setLayout(null);
		cabecalhoPanel.add(tituloCabecalhoLabel);
	}

	// ==========================================================================================================================================
	// AREA DAS CPUS
	// ==========================================================================================================================================

	private JPanel cpuPanel;

	private void initPainelCPU() {

		JLabel numCpuLabel = new JLabel("Cores de Processamento: " + numCpu);
		numCpuLabel.setForeground(Color.YELLOW);
		numCpuLabel.setBounds(10, 11, 266, 22);
		numCpuLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		numCpuLabel.setHorizontalAlignment(SwingConstants.LEFT);

		initCoresProcessadmento();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(20, 44, 858, 146);
		scrollPane.setViewportView(coresProcesssamentoPanel);

		cpuPanel = new JPanel();
		cpuPanel.setBackground(Color.DARK_GRAY);
		cpuPanel.setBounds(0, 41, 900, 213);
		cpuPanel.setLayout(null);
		cpuPanel.add(numCpuLabel);
		cpuPanel.add(scrollPane);

		if (quantum > 0) {
			JLabel valorQuantumLabel = new JLabel("Valor do Quantum: " + quantum);
			valorQuantumLabel.setHorizontalAlignment(SwingConstants.LEFT);
			valorQuantumLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			valorQuantumLabel.setForeground(Color.YELLOW);
			valorQuantumLabel.setBounds(286, 11, 266, 22);
			cpuPanel.add(valorQuantumLabel);
		}
	}

	private void initCoresProcessadmento() {

		coresProcesssamentoPanel = new JPanel();
		coresProcesssamentoPanel.setLayout(new GridLayout(4, 2, 0, 0));

		celulasProcessamento = new ArrayList<>();

		for (int i = 0; i < this.numCpu; i++) {

			JLabel label = new JLabel("Livre");
			label.setForeground(Color.YELLOW);
			label.setHorizontalAlignment(SwingConstants.CENTER);

			JPanel panel = new JPanel();
			panel.setSize(50, 50);
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
			panel.setBackground(Color.RED);
			panel.add(label);

			celulasProcessamento.add(panel);
		}

		for (JPanel panel : celulasProcessamento)
			coresProcesssamentoPanel.add(panel);

	}

	// ==========================================================================================================================================
	// AREA DOS PROCESSOS APTOS E BLOQUEADOS
	// ==========================================================================================================================================

	private JPanel processosPanel;
	private JPanel conteinerFinalizados;

	private void initPainelProcessos() {

		JLabel telaFilaFinalizados = new JLabel("Fila dos Finalizados");
		telaFilaFinalizados.setHorizontalAlignment(SwingConstants.LEFT);
		telaFilaFinalizados.setForeground(Color.YELLOW);
		telaFilaFinalizados.setFont(new Font("Tahoma", Font.PLAIN, 18));
		telaFilaFinalizados.setBounds(10, 291, 163, 22);

		JLabel filaAptosLabel = new JLabel("Fila dos Aptos:");
		filaAptosLabel.setHorizontalAlignment(SwingConstants.LEFT);
		filaAptosLabel.setForeground(Color.YELLOW);
		filaAptosLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		filaAptosLabel.setBounds(10, 11, 117, 22);

		initConteinerBloqueados();
		initFilaAptos();

		processosPanel = new JPanel();
		processosPanel.setBounds(0, 251, 900, 389);
		processosPanel.setBackground(Color.GRAY);
		processosPanel.setLayout(null);
		processosPanel.add(telaFilaFinalizados);
		processosPanel.add(conteinerFinalizados);
		processosPanel.add(filaAptosLabel);
		processosPanel.add(filaAptosConteiner);
		filaAptosConteiner.setLayout(null);
	}

	private void initFilaAptos() {
		filaAptosConteiner = new JPanel();
		filaAptosConteiner.setBounds(10, 42, 869, 238);
	}

	private void initConteinerBloqueados() {

		conteinerFinalizados = new JPanel();
		conteinerFinalizados.setBounds(10, 324, 869, 54);
		conteinerFinalizados.setLayout(new GridLayout(1, 0, 0, 0));

		listaProcessosFinalizadosPanel = new JPanel();
		listaProcessosFinalizadosPanel.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane = new JScrollPane(listaProcessosFinalizadosPanel);
		conteinerFinalizados.add(scrollPane);

	}

	// ==========================================================================================================================================
	// BOTOES DA JANELA
	// ==========================================================================================================================================

	private JPanel controlePanel;

	private JButton botaoStart;
	private JButton botaoStop;
	private JButton botaoCriarProcesso;

	private void initPainelControle() {

		controlePanel = new JPanel();
		controlePanel.setBounds(0, 640, LARGURA_TELA, 31);
		controlePanel.setBackground(Color.LIGHT_GRAY);
		controlePanel.setLayout(new GridLayout(1, 0, 0, 0));

		initBotaoCriarProcesso();
		initBotaoStart();
		initBotaoStop();

		controlePanel.add(botaoStart);
		controlePanel.add(botaoCriarProcesso);
		controlePanel.add(botaoStop);

	}

	private void initBotaoStop() {
		botaoStop = new JButton("Stop");
		botaoStop.setFont(new Font("Tahoma", Font.PLAIN, 18));
		botaoStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false);
				frame.dispose();
				new JanelaInicial();
			}
		});

	}

	private void initBotaoStart() {
		botaoStart = new JButton("Start");
		botaoStart.setFont(new Font("Tahoma", Font.PLAIN, 18));
		botaoStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				try {

					if (botaoStart.isEnabled()) {
						escalonador.start();
						botaoStart.setEnabled(false);
					}

				} catch (Exception e) {
					System.out.println("botao de start desativado");
				}
			}
		});
	}

	private void initBotaoCriarProcesso() {
		botaoCriarProcesso = new JButton("Novo Processo");
		botaoCriarProcesso.setFont(new Font("Tahoma", Font.PLAIN, 18));
		botaoCriarProcesso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				escalonador.addNovoProcesso();
			}
		});

	}
}