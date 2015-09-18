package interfece_grafica;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class JanelaInicial {

	private JFrame frame;
	private JComboBox processadoresComboBox;
	private JSpinner quantumSpinner;

	private JRadioButton ltgRadioButton;
	private JRadioButton roundRobinRadioButton;
	private JRadioButton schedulingRadioButton;

	private Thread verQuantum = new Thread(new Runnable() {

		@Override
		public void run() {

			if (roundRobinRadioButton.isSelected()) {
				System.out.println("chibata");
				quantumSpinner.setEnabled(true);
				quantumSpinner.setFocusable(false);
			}

		}
	});

	public JanelaInicial() {
		initialize();
		initLayout();
		
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setSize(350, 320);
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
	}

	private JLabel getQuantumLabel() {
		JLabel valorQuantumLabel = new JLabel("Valor do Quantum:");
		valorQuantumLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		valorQuantumLabel.setBounds(10, 155, 151, 22);
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
		numeroProcessadoresLabel.setBounds(10, 188, 210, 22);
		return numeroProcessadoresLabel;
	}

	private JComboBox getProcessadoresComboBox() {

		Integer[] lista = new Integer[64];

		for (int i = 0; i < 64; i++)
			lista[i] = i + 1;

		processadoresComboBox = new JComboBox(lista);
		processadoresComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		processadoresComboBox.setBounds(240, 188, 66, 20);

		return processadoresComboBox;
	}

	private JSpinner getQuantumSpinner() {

		quantumSpinner = new JSpinner();
		quantumSpinner.setFont(new Font("Tahoma", Font.PLAIN, 12));
		quantumSpinner.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		quantumSpinner.setBounds(240, 157, 66, 20);
		return quantumSpinner;
	}

	private JButton getBotaoIniciar() {

		JButton botaoIniciar = new JButton("Iniciar");

		botaoIniciar.setFont(new Font("Tahoma", Font.PLAIN, 24));
		botaoIniciar.setBounds(106, 229, 127, 37);

		return botaoIniciar;
	}

	private JPanel getPainelAlgoritmos() {

		JPanel opcoesAlgoritmosPanel = new JPanel();
		opcoesAlgoritmosPanel.setForeground(Color.BLACK);
		opcoesAlgoritmosPanel.setBackground(Color.WHITE);
		opcoesAlgoritmosPanel.setBounds(10, 44, 296, 100);
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
		ltgRadioButton.setBackground(Color.WHITE);
		ltgRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ltgRadioButton.setBounds(6, 7, 193, 29);
	}

}
