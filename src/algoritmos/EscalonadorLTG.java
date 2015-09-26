package algoritmos;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import Objetos.ICallbackFinalizarProcesso;
import Objetos.ProcessoLTG;

public class EscalonadorLTG extends Thread implements ICallbackFinalizarProcesso {

	// ATRIBUTOS
	// ================================================================================================

	private boolean isAtivo = true;

	private int numProcessosCriados;

	private JPanel painelProcessosFinalizados;
	private JPanel listaProcessosAptos;

	private ArrayList<ProcessoLTG> listaProcessos;
	private ArrayList<JPanel> listaAptos;
	private ArrayList<JPanel> listaCores;

	// CONSTRUTOR
	// ================================================================================================

	public EscalonadorLTG(int processos, ArrayList<JPanel> cores, JPanel finalizados, JPanel aptos) {

		this.listaProcessos = new ArrayList<>();
		this.listaAptos = new ArrayList<>();

		this.painelProcessosFinalizados = finalizados;
		this.listaCores = cores;
		this.numProcessosCriados = processos;

		this.listaProcessosAptos = new JPanel();
		this.listaProcessosAptos.setLayout(new GridLayout(1, 0, 0, 0));

		// PREENCHE A LISTA DE APTOS COM PROCESSOS INICIAIS
		// =============================================================================================

		for (int i = 1; i <= processos; i++) {

			JPanel panel = new JPanel();

			ProcessoLTG processo = new ProcessoLTG(i, this);
			processo.addPanel(panel);

			listaProcessos.add(processo);
			listaAptos.add(panel);

			listaProcessosAptos.add(panel);

		}

		JScrollPane scrollPane = new JScrollPane(listaProcessosAptos);
		scrollPane.setBounds(28, 25, 819, 80);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		aptos.add(scrollPane);
	}

	// CALLBACKS
	// ================================================================================================

	@Override
	public void finalizarProcesso(int idProcesso) {

		System.out.println("Processo " + idProcesso + " foi finalizado...");

		JLabel idLabel = new JLabel("PRO: " + idProcesso);
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		idLabel.setForeground(Color.WHITE);
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel painel = new JPanel();
		painel.removeAll();
		painel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		painel.setLayout(new GridLayout(1, 0, 0, 0));
		painel.setBackground(Color.BLUE);
		painel.add(idLabel);

		painelProcessosFinalizados.add(painel);
		painelProcessosFinalizados.revalidate();
		painelProcessosFinalizados.repaint();
	}

	@Override
	public void abortarProcesso(ProcessoLTG processo, JPanel painel) {

		int id = processo.getIdProcesso();

		listaProcessos.remove(processo);

		System.out.println("Processo " + id + " foi abortado...  ");

		JLabel idLabel = new JLabel("PRO: " + id);
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		idLabel.setForeground(Color.WHITE);
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);

		listaAptos.remove(painel);
		listaProcessosAptos.repaint();

		// JPanel painel = new JPanel();
		painel.removeAll();
		painel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		painel.setLayout(new GridLayout(1, 0, 0, 0));
		painel.setBackground(Color.RED);
		painel.add(idLabel);

		painelProcessosFinalizados.add(painel);
		painelProcessosFinalizados.revalidate();
		painelProcessosFinalizados.repaint();
	}

	// CRIA NOVOS PROCESSOS
	// ================================================================================================

	public void addNovoProcesso() {

		this.numProcessosCriados++;

		JPanel panel = new JPanel();

		ProcessoLTG processo = new ProcessoLTG(numProcessosCriados, this);
		processo.addPanel(panel);
		processo.start();

		listaProcessos.add(processo);
		listaAptos.add(panel);

		listaProcessosAptos.add(panel);
		listaProcessosAptos.revalidate();
		listaProcessosAptos.repaint();

	}

	// EXECUCAO DA THREAD
	// ================================================================================================

	public void desativa() {
		this.isAtivo = false;
		for (ProcessoLTG p : listaProcessos)
			p.setVivo(false);
	}

	@Override
	public void run() {
		super.run();

		for (ProcessoLTG processo : listaProcessos)
			if (!processo.isAlive())
				processo.start();

		while (isAtivo) {

			for (int i = 0; i < listaCores.size(); i++) {

				JPanel panel = listaCores.get(i);

				if (listaProcessos.size() > 0 && listaAptos.size() > 0) {

					if (panel.getBackground().equals(Color.BLACK)) {

						listaProcessosAptos.remove(listaAptos.remove(0));
						listaProcessosAptos.revalidate();
						listaProcessosAptos.repaint();

						ProcessoLTG processo = listaProcessos.remove(0);
						processo.addPanel(panel);
						processo.setProcessando(true);

					}
				}

			}

		}
	}
}