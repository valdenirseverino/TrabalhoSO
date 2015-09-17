package interfece_grafica;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JProgressBar;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class JanelaInicial {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaInicial window = new JanelaInicial();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JanelaInicial() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setSize(350, 350);
		frame.setResizable(false);		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 44, 296, 100);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JRadioButton ltgRadioButton = new JRadioButton("Least Time to Go (LTG)");
		ltgRadioButton.setBackground(Color.WHITE);
		ltgRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ltgRadioButton.setBounds(6, 7, 193, 29);
		panel.add(ltgRadioButton);

		JRadioButton roundRobinRadioButton = new JRadioButton("Filas de prioridade com Round Robin");
		roundRobinRadioButton.setBackground(Color.WHITE);
		roundRobinRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		roundRobinRadioButton.setBounds(6, 39, 287, 29);
		panel.add(roundRobinRadioButton);

		JRadioButton schedulingRadioButton = new JRadioButton("Interval-based Scheduling");
		schedulingRadioButton.setBackground(Color.WHITE);
		schedulingRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		schedulingRadioButton.setBounds(6, 71, 249, 29);
		panel.add(schedulingRadioButton);

		ButtonGroup group = new ButtonGroup();
		group.add(ltgRadioButton);
		group.add(roundRobinRadioButton);
		group.add(schedulingRadioButton);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{ltgRadioButton, roundRobinRadioButton, schedulingRadioButton}));

		JLabel numeroProcessadoresLabel = new JLabel("N\u00FAmero de processadores:");
		numeroProcessadoresLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		numeroProcessadoresLabel.setBounds(10, 188, 210, 22);
		frame.getContentPane().add(numeroProcessadoresLabel);

		JLabel tipoDeEscalonadorLabel = new JLabel("Tipo de Escalonador:");
		tipoDeEscalonadorLabel.setBounds(10, 11, 166, 22);
		frame.getContentPane().add(tipoDeEscalonadorLabel);
		tipoDeEscalonadorLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));

		Integer[] lista = new Integer[64];
		for (int i = 0; i < 64; i++)
			lista[i] = i+1;

		JComboBox processadoresComboBox = new JComboBox(lista);
		processadoresComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		processadoresComboBox.setBounds(240, 188, 66, 20);
		frame.getContentPane().add(processadoresComboBox);
		
		JButton botaoIniciar = new JButton("Iniciar");
		botaoIniciar.setFont(new Font("Tahoma", Font.PLAIN, 24));
		botaoIniciar.setBounds(93, 236, 127, 37);
		frame.getContentPane().add(botaoIniciar);
		
		JLabel valorQuantumLabel = new JLabel("Valor do Quantum:");
		valorQuantumLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		valorQuantumLabel.setBounds(10, 155, 151, 22);
		frame.getContentPane().add(valorQuantumLabel);
		
		JSpinner quantumSpinner = new JSpinner();
		quantumSpinner.setFont(new Font("Tahoma", Font.PLAIN, 12));
		quantumSpinner.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		quantumSpinner.setBounds(240, 157, 66, 20);
		frame.getContentPane().add(quantumSpinner);

	}
}
