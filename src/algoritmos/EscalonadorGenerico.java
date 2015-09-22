package algoritmos;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import Objetos.Processo;

public class EscalonadorGenerico extends Thread {

	private int numProcessosCriados;
	private boolean isVivo = true;
	private int numProcessosIniciais;

	private JPanel painelProcessosBloqueados;
	private JPanel listaProcessosAptosPainel;

	private ArrayList<Processo> listaProcessosAptos;
	private ArrayList<JPanel> listaPanelAptos;
	private ArrayList<JPanel> listaCores;

	public void addNovoProcesso() {

		numProcessosCriados++;

		JPanel panel = new JPanel();
		Processo p = new Processo(numProcessosCriados);
		p.setPanel(panel);

		listaProcessosAptos.add(p);
		listaPanelAptos.add(panel);

		listaProcessosAptosPainel.add(panel);
		listaProcessosAptosPainel.revalidate();
		listaProcessosAptosPainel.repaint();

	}

	@Override
	public void run() {
		super.run();

		while (isVivo) {

			for (int i = 0; i < listaCores.size(); i++) {

				JPanel panel = listaCores.get(i);

				if (panel.getBackground().equals(Color.RED)) {

					if (listaProcessosAptos.size() > 0) {

						Processo p = listaProcessosAptos.remove(0);
						p.setPanel(panel);
						p.start();

						JPanel jp = listaPanelAptos.remove(0);
						listaProcessosAptosPainel.remove(jp);
						listaProcessosAptosPainel.revalidate();
						listaProcessosAptosPainel.repaint();
					}
				}

			}

		}

	}

	// ========================================================================================================
	// CONSTRUTOR
	// ========================================================================================================

	public EscalonadorGenerico(int numProcessos, ArrayList<JPanel> cores, JPanel bloqueados, JPanel aptos) {

		this.numProcessosCriados = 0;
		this.listaProcessosAptos = new ArrayList<>();
		this.listaPanelAptos = new ArrayList<>();
		this.numProcessosIniciais = numProcessos;
		this.painelProcessosBloqueados = bloqueados;
		this.listaCores = cores;

		initConteinerAptos(aptos);
	}

	private void initConteinerAptos(JPanel conteinerProcessosAptos) {

		numProcessosCriados = numProcessosIniciais;

		listaProcessosAptosPainel = new JPanel();
		listaProcessosAptosPainel.setLayout(new GridLayout(1, 0, 0, 0));

		for (int i = 1; i <= numProcessosIniciais; i++) {

			JPanel panel = new JPanel();
			Processo p = new Processo(i);
			p.setPanel(panel);

			listaProcessosAptos.add(p);
			listaPanelAptos.add(panel);

			listaProcessosAptosPainel.add(panel);

		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 25, 819, 80);
		scrollPane.setViewportView(listaProcessosAptosPainel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		conteinerProcessosAptos.add(scrollPane);

	}

}
