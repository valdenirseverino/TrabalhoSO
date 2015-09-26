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
import Objetos.Processo;

public class EscalonadorLTG extends Thread implements ICallbackFinalizarProcesso {

	private int numProcessosCriados;
	private boolean isVivo = true;
	private int numProcessosIniciais;

	private JPanel painelProcessosFinalizados;
	private JPanel painelProcessosAptos;

	private ArrayList<Processo> listaProcessos;
	private ArrayList<JPanel> listaPanelAptos;
	private ArrayList<JPanel> listaCores;

	@Override
	public void finalizarProcesso(int idProcesso) {

		System.out.println("Processo " + idProcesso + " foi finalizado...");

		JLabel idLabel = new JLabel("PRO: " + idProcesso);
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel painel = new JPanel();
		painel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		painel.setLayout(new GridLayout(1, 0, 0, 0));
		painel.setBackground(Color.BLUE);
		painel.add(idLabel);

		painelProcessosFinalizados.add(painel);
		painelProcessosFinalizados.revalidate();
		painelProcessosFinalizados.repaint();
	}

	@Override
	public void abortarProcesso(int idProcesso) {

		System.out.println("Processo " + idProcesso + " foi abortado...");

		JLabel idLabel = new JLabel("PRO: " + idProcesso);
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel painel = new JPanel();
		painel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		painel.setLayout(new GridLayout(1, 0, 0, 0));
		painel.setBackground(Color.RED);
		painel.add(idLabel);

		painelProcessosFinalizados.add(painel);
		painelProcessosFinalizados.revalidate();
		painelProcessosFinalizados.repaint();
	}

	public void addNovoProcesso() {

		numProcessosCriados++;

		JPanel panel = new JPanel();
		Processo p = new Processo(numProcessosCriados, this);
		p.addPanel(panel);

		listaProcessos.add(p);
		listaPanelAptos.add(panel);

		painelProcessosAptos.add(panel);
		painelProcessosAptos.revalidate();
		painelProcessosAptos.repaint();

	}

	@Override
	public void run() {
		super.run();

		while (isVivo) {

			for (int i = 0; i < listaCores.size(); i++) {

				JPanel panel = listaCores.get(i);

				if (listaProcessos.size() > 0) {
					
					Processo p;
					
					if (panel.getBackground().equals(Color.RED)) {

						p = listaProcessos.remove(0);
						p.addPanel(panel);
						p.setProcessando(true);
						

						JPanel jp = listaPanelAptos.remove(0);
						painelProcessosAptos.remove(jp);
						painelProcessosAptos.revalidate();
						painelProcessosAptos.repaint();
					
					} else {						
						p = listaProcessos.get(i);
						
					}
					
					p.start();
					
					

				}

			}

		}

	}

	// ========================================================================================================
	// CONSTRUTOR
	// ========================================================================================================

	public EscalonadorLTG(int numProcessos, ArrayList<JPanel> cores, JPanel finalizados, JPanel aptos) {

		this.numProcessosCriados = 0;
		this.listaProcessos = new ArrayList<>();
		this.listaPanelAptos = new ArrayList<>();
		this.numProcessosIniciais = numProcessos;
		this.painelProcessosFinalizados = finalizados;
		this.listaCores = cores;

		initConteinerAptos(aptos);
	}

	private void initConteinerAptos(JPanel conteinerProcessosAptos) {

		numProcessosCriados = numProcessosIniciais;

		painelProcessosAptos = new JPanel();
		painelProcessosAptos.setLayout(new GridLayout(1, 0, 0, 0));

		for (int i = 1; i <= numProcessosIniciais; i++) {

			JPanel panel = new JPanel();
			Processo p = new Processo(i, this);

			p.addPanel(panel);

			listaProcessos.add(p);
			listaPanelAptos.add(panel);

			painelProcessosAptos.add(panel);

		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 25, 819, 80);
		scrollPane.setViewportView(painelProcessosAptos);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		conteinerProcessosAptos.add(scrollPane);

	}

}
