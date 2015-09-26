package Objetos;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Processo extends Thread {

	private int idProcesso;
	private int tempoExecucao;
	private int deadLine;
	private int prioridade;

	private boolean isVivo;
	private boolean isProcessando;

	private ICallbackFinalizarProcesso callbackFinalizarProcesso;

	private JLabel idLabel;
	private JLabel deadlineLabel;
	private JLabel tempoProcessoLabel;

	private JPanel painel;

	@Override
	public void run() {
		super.run();

		while (isVivo) {
			try {

				if (this.painel != null) {

					if (isProcessando) {

						this.tempoExecucao--;
						this.tempoProcessoLabel.setText("TR: " + this.tempoExecucao);

						if (tempoExecucao == 0) {

							this.isVivo = false;

							JLabel label = new JLabel("Livre");
							label.setForeground(Color.YELLOW);
							label.setHorizontalAlignment(SwingConstants.CENTER);

							callbackFinalizarProcesso.finalizarProcesso(this.idProcesso);

							this.painel.removeAll();
							this.painel.setBackground(Color.RED);
							this.painel.add(label);
						}

						Thread.sleep(1000);

					} else {

						deadLine--;
						this.deadlineLabel.setText("DL: " + this.deadLine + "s");

						Thread.sleep(1000);

						if (deadLine == 0) {
							callbackFinalizarProcesso.abortarProcesso(idProcesso);
							isVivo = false;
						}

					}
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void addPanel(JPanel panel) {

		this.idLabel = new JLabel("PRO: " + this.idProcesso);
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);

		this.tempoProcessoLabel = new JLabel("TE: " + this.tempoExecucao + "s");
		tempoProcessoLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tempoProcessoLabel.setHorizontalAlignment(SwingConstants.CENTER);

		this.deadlineLabel = new JLabel("DL: " + this.deadLine + "s");
		deadlineLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		deadlineLabel.setHorizontalAlignment(SwingConstants.CENTER);
		deadlineLabel.setForeground(Color.RED);

		this.painel = panel;
		this.painel.removeAll();
		this.painel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		this.painel.setLayout(new GridLayout(3, 0, 0, 0));
		this.painel.add(idLabel);
		this.painel.add(tempoProcessoLabel);
		this.painel.add(deadlineLabel);
		this.painel.setBackground(Color.green);
		this.painel.repaint();
	}

	public Processo(int iD, ICallbackFinalizarProcesso callback) {

		this.callbackFinalizarProcesso = callback;

		this.isProcessando = false;

		Random gerador = new Random();

		this.idProcesso = iD;
		this.tempoExecucao = 4 + gerador.nextInt(16);
		this.deadLine = 4 + gerador.nextInt(16);
		this.isVivo = true;

		System.out.println("=============================================================");
		System.out.println("PROCESSO " + this.idProcesso + " CRIADO....");
		System.out.println("TEMPO DE EXECUCAO: " + tempoExecucao);
		System.out.println("TIME LINE: " + deadLine);
		System.out.println("PRIORIDADE: " + prioridade);

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

}
