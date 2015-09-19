package algoritmos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JPanel;

public class Generico extends Thread {

	private ArrayList<JPanel> listaCpuVazia;
	private HashMap<Integer, Processo> listaProcesos;
	private HashMap<Integer, JPanel> listaCPU;
	private ArrayList<JPanel> cores;
	private int contadorID;

	private JPanel painelPrincipal;

	public Generico(HashMap<Integer, JPanel> listaCPU, ArrayList<JPanel> cores, JPanel painelPrincipal) {

		this.listaProcesos = new HashMap<>();
		this.listaCPU = listaCPU;
		this.cores = cores;
		this.painelPrincipal = painelPrincipal;
		this.listaProcesos = listaProcesos;
		this.listaCPU = listaCPU;
		this.listaCpuVazia = new ArrayList<>();
		this.contadorID = listaProcesos.size();
	}

	// @Override
	// public void run() {
	// super.run();
	//
	// while (listaProcesos.size() > 0) {
	//
	// Set<Integer> chaves = listaProcesos.keySet();
	//
	// ArrayList<Integer> listaID = new ArrayList<>();
	//
	// for (Integer chave : chaves) {
	//
	// Processo processo = listaProcesos.get(chave);
	//
	// if (!processo.isVivo()) {
	//
	// int id = processo.getIdProcesso();
	//
	// listaID.add(id);
	//
	// System.out.println("Processo " + id + " morreu");
	// }
	//
	// }
	//
	// for (Integer id : listaID) {
	//
	// JPanel panel = listaCPU.get(id);
	//
	// listaCpuVazia.add(panel);
	//
	// panel.removeAll();
	//
	// painelPrincipal.repaint();
	//
	// listaCPU.remove(id);
	// listaProcesos.remove(id);
	//
	// System.out.println("processos restantes: " + listaProcesos.size());
	// }
	//
	// }
	// }

	@Override
	public void run() {
		super.run();

		for (int i = 0; i < cores.size(); i++) {
			Processo pro = new Processo(i + 1, cores.get(i));
			pro.start();
		}

	}

	public void adicionarProcesso() {

		for (JPanel panel : cores) {
			if (panel.getBackground().equals(Color.RED)) {
				new Processo(12345, panel).start();
				return;
			}
		}

	}

}
