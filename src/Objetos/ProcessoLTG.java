package Objetos;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ProcessoLTG extends Thread {

	private int idProcesso;
	private int tempoExecucao;
	private int deadLine;
	private int prioridade;

	private boolean isVivo;
	private boolean isStart = false;
	private boolean isProcessando;

	private ICallbackFinalizarProcesso callbackFinalizarProcesso;

	private JLabel idLabel;
	private JLabel tempoProcessoLabel;
	private JPanel painel;

	@Override
	public void run() {
		super.run();

		isStart = true;

		while (isVivo) {
			try {

				if (this.painel != null) {

					if (isProcessando)
						processsarNaCpu();
					else
						processarFilaEspera();
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void processarFilaEspera() throws InterruptedException {

		deadLine--;
		this.tempoProcessoLabel.setText("DL: " + this.deadLine + "s");
		this.tempoProcessoLabel.setForeground(Color.RED);
		Thread.sleep(1000);

		if (deadLine == 0) {

			isVivo = false;

			callbackFinalizarProcesso.abortarProcesso(this, painel);

		}
	}

	private void processsarNaCpu() throws InterruptedException {

		this.tempoExecucao--;
		this.tempoProcessoLabel.setText("TR: " + this.tempoExecucao);
		this.tempoProcessoLabel.setForeground(Color.BLACK);

		painel.setBackground(Color.GREEN);

		Thread.sleep(1000);

		if (tempoExecucao == 0) {

			this.isVivo = false;

			JLabel label = new JLabel("Livre");
			label.setForeground(Color.WHITE);
			label.setHorizontalAlignment(SwingConstants.CENTER);

			this.painel.removeAll();
			this.painel.setBackground(Color.BLACK);
			this.painel.setLayout(new GridLayout(1, 0, 0, 0));
			this.painel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
			this.painel.add(label);
			this.painel.revalidate();
			this.painel.repaint();

			callbackFinalizarProcesso.finalizarProcesso(idProcesso);

		}
	}

	public void addPanel(JPanel panel) {

		this.idLabel = new JLabel("PRO: " + this.idProcesso);
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);

		this.tempoProcessoLabel = new JLabel("DL: " + this.tempoExecucao + "s");
		tempoProcessoLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tempoProcessoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tempoProcessoLabel.setForeground(Color.RED);

		this.painel = panel;
		this.painel.removeAll();
		this.painel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		this.painel.setLayout(new GridLayout(2, 0, 0, 0));
		this.painel.add(idLabel);
		this.painel.add(tempoProcessoLabel);
		this.painel.setBackground(Color.YELLOW);
		this.painel.repaint();

	}

	public ProcessoLTG(int iD, ICallbackFinalizarProcesso callback) {

		this.callbackFinalizarProcesso = callback;

		this.isProcessando = false;

		Random gerador = new Random();

		this.idProcesso = iD;
		this.tempoExecucao = 4 + gerador.nextInt(16);
		this.deadLine = 4 + gerador.nextInt(16);

		this.isVivo = true;
		this.isProcessando = false;

	}

	// GETTERS E SETTERS
	// =====================================================================

	public int getIdProcesso() {
		return idProcesso;
	}

	public boolean isProcessando() {
		return isProcessando;
	}

	public void setProcessando(boolean isProcessando) {
		this.isProcessando = isProcessando;
	}

	public void setIdProcesso(int idProcesso) {
		this.idProcesso = idProcesso;
	}

	public int getTempoExecucao() {
		return tempoExecucao;
	}

	public void setTempoExecucao(int tempoExecucao) {
		this.tempoExecucao = tempoExecucao;
	}

	public int getTimeLine() {
		return deadLine;
	}

	public void setTimeLine(int timeLine) {
		this.deadLine = timeLine;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public boolean isVivo() {
		return isVivo;
	}

	public void setVivo(boolean isVivo) {
		this.isVivo = isVivo;
	}

	public boolean isStart() {
		return isStart;
	}

}
