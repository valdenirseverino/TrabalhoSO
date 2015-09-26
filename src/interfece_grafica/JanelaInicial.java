package interfece_grafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class JanelaInicial {

	private JFrame frame;
	private JComboBox<Object> processadoresComboBox;
	private JSpinner processsosIniciaisSpinner;
	private JSpinner quantumSpinner;

	private JRadioButton ltgRadioButton;
	private JRadioButton roundRobinRadioButton;
	private JRadioButton schedulingRadioButton;

	private final String LTG = "Least Yime to Go (LTG)";
	private final String ROUND_ROBIN = "Fila de Prioridade comRound Robin";
	private final String SCHEDULING = "Inteval-based Scheduling";

	public JanelaInicial() {
		initialize();
		initLayout();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setSize(350, 400);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
	}

	private void initLayout() {
		frame.getContentPane().add(getProcessadorLabel());
		frame.getContentPane().add(getTipoEscalonamentoLabel());
		frame.getContentPane().add(getQuantumLabel());
		frame.getContentPane().add(getPainelAlgoritmos());
		frame.getContentPane().add(getProcessadoresComboBox());
		frame.getContentPane().add(getQuantumSpinner());
		frame.getContentPane().add(getBotaoIniciar());
		frame.getContentPane().add(getNumeroProcessosIniciaisLabel());
		frame.getContentPane().add(getNumeroProcessosIniciais());
	}

	private JLabel getNumeroProcessosIniciaisLabel() {
		JLabel lblNmeroDeProcessos = new JLabel("N\u00FAmero de processos iniciais:");
		lblNmeroDeProcessos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNmeroDeProcessos.setBounds(10, 232, 233, 22);
		return lblNmeroDeProcessos;
	}

	private JSpinner getNumeroProcessosIniciais() {
		processsosIniciaisSpinner = new JSpinner();
		processsosIniciaisSpinner
				.setModel(new SpinnerNumberModel(new Integer(10), new Integer(0), null, new Integer(1)));
		processsosIniciaisSpinner.setFont(new Font("Tahoma", Font.PLAIN, 12));
		processsosIniciaisSpinner.setBounds(253, 230, 66, 20);
		return processsosIniciaisSpinner;
	}

	private JLabel getQuantumLabel() {
		JLabel valorQuantumLabel = new JLabel("Valor do Quantum:");
		valorQuantumLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		valorQuantumLabel.setBounds(10, 166, 151, 22);
		return valorQuantumLabel;
	}

	private JLabel getTipoEscalonamentoLabel() {
		JLabel tipoDeEscalonadorLabel = new JLabel("Tipo de Escalonador:");
		tipoDeEscalonadorLabel.setBounds(10, 11, 166, 22);
		tipoDeEscalonadorLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		return tipoDeEscalonadorLabel;
	}

	private JLabel getProcessadorLabel() {
		JLabel numeroProcessadoresLabel = new JLabel("N\u00FAmero de processadores:");
		numeroProcessadoresLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		numeroProcessadoresLabel.setBounds(10, 199, 210, 22);
		return numeroProcessadoresLabel;
	}

	private JComboBox<Object> getProcessadoresComboBox() {

		Integer[] lista = new Integer[64];

		for (int i = 0; i < 64; i++)
			lista[i] = i + 1;

		processadoresComboBox = new JComboBox<>(lista);
		processadoresComboBox.setSelectedIndex(7);
		processadoresComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		processadoresComboBox.setBounds(253, 202, 66, 20);

		return processadoresComboBox;
	}

	private JSpinner getQuantumSpinner() {

		quantumSpinner = new JSpinner();
		quantumSpinner.setFont(new Font("Tahoma", Font.PLAIN, 12));
		quantumSpinner.setModel(new SpinnerNumberModel(1, 1, 20, 1));
		quantumSpinner.setBounds(253, 169, 66, 20);
		return quantumSpinner;
	}

	private JButton getBotaoIniciar() {

		JButton botaoIniciar = new JButton("Iniciar");
		botaoIniciar.setFont(new Font("Tahoma", Font.PLAIN, 24));
		botaoIniciar.setBounds(116, 290, 127, 37);

		botaoIniciar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				validarCampos();
			}

		});
		return botaoIniciar;
	}

	private void validarCampos() {

		int valorQuantum = Integer.parseInt(quantumSpinner.getValue().toString());
		int numCpu = Integer.parseInt(processadoresComboBox.getSelectedItem().toString());
		int numProcessos = Integer.parseInt(processsosIniciaisSpinner.getValue().toString());
		String algoritmo = "";

		if (ltgRadioButton.isSelected())
			algoritmo = LTG;

		if (roundRobinRadioButton.isSelected())
			algoritmo = ROUND_ROBIN;

		if (schedulingRadioButton.isSelected())
			algoritmo = SCHEDULING;

		if (algoritmo.length() == 0) {
			JOptionPane.showMessageDialog(frame, "Escolha um algortmo");
			return;
		}

		int resposta = -1;

		while (resposta == -1)
			resposta = JOptionPane.showConfirmDialog(frame, "Vc está certo disso?", "Confirmação", JOptionPane.YES_NO_OPTION);

		
		
		if (!algoritmo.equals(ROUND_ROBIN))
			valorQuantum = -1;

		if (resposta == 0) {

			frame.setVisible(false);
			frame.dispose();

			new JanelaPrincipal(algoritmo, numCpu, valorQuantum, numProcessos);

		}
	}

	private JPanel getPainelAlgoritmos() {

		JPanel opcoesAlgoritmosPanel = new JPanel();
		opcoesAlgoritmosPanel.setForeground(Color.BLACK);
		opcoesAlgoritmosPanel.setBackground(Color.WHITE);
		opcoesAlgoritmosPanel.setBounds(10, 44, 309, 100);
		opcoesAlgoritmosPanel.setLayout(null);

		initButtonLTG();
		initButtonRoundRobin();
		initButtonScheduling();

		opcoesAlgoritmosPanel.add(ltgRadioButton);
		opcoesAlgoritmosPanel.add(roundRobinRadioButton);
		opcoesAlgoritmosPanel.add(schedulingRadioButton);

		ButtonGroup group = new ButtonGroup();
		group.add(ltgRadioButton);
		group.add(roundRobinRadioButton);
		group.add(schedulingRadioButton);

		return opcoesAlgoritmosPanel;
	}

	private void initButtonScheduling() {
		schedulingRadioButton = new JRadioButton("Interval-based Scheduling");
		schedulingRadioButton.setBackground(Color.WHITE);
		schedulingRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		schedulingRadioButton.setBounds(6, 71, 249, 29);
	}

	private void initButtonRoundRobin() {
		roundRobinRadioButton = new JRadioButton("Filas de prioridade com Round Robin");
		roundRobinRadioButton.setBackground(Color.WHITE);
		roundRobinRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		roundRobinRadioButton.setBounds(6, 39, 287, 29);
	}

	private void initButtonLTG() {
		ltgRadioButton = new JRadioButton("Least Time to Go (LTG)");
		ltgRadioButton.setSelected(true);
		ltgRadioButton.setBackground(Color.WHITE);
		ltgRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ltgRadioButton.setBounds(6, 7, 193, 29);
	}
}
