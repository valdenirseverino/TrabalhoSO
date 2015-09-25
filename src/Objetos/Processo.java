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
	private int timeLine;
	private int prioridade;

	private boolean isVivo;

	private ICallbackFinalizarProcesso callbackFinalizarProcesso;

	private JLabel idLabel;
	private JLabel tempoRestante;
	private JPanel painel;

	@Override
	public void run() {
		super.run();

		while (isVivo) {
			try {

				if (this.painel != null) {

					this.tempoExecucao = tempoExecucao - 1;
					this.tempoRestante.setText("TR: " + this.tempoExecucao);

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

		this.tempoRestante = new JLabel(this.tempoExecucao + "s");
		tempoRestante.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tempoRestante.setHorizontalAlignment(SwingConstants.CENTER);

		this.painel = panel;
		this.painel.removeAll();
		this.painel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		this.painel.setLayout(new GridLayout(2, 0, 0, 0));
		this.painel.add(idLabel);
		this.painel.add(tempoRestante);
		this.painel.setBackground(Color.green);
		this.painel.repaint();
	}

	public Processo(int iD, ICallbackFinalizarProcesso callback) {

		this.callbackFinalizarProcesso = callback;

		Random gerador = new Random();

		this.idProcesso = iD;
		this.tempoExecucao = 10 + gerador.nextInt(90);
		this.timeLine = 10 + gerador.nextInt(10);
		this.prioridade = gerador.nextInt(3);
		this.isVivo = true;

		System.out.println("=============================================================");
		System.out.println("PROCESSO " + this.idProcesso + " CRIADO....");
		System.out.println("TEMPO DE EXECUCAO: " + tempoExecucao);
		System.out.println("TIME LINE: " + timeLine);
		System.out.println("PRIORIDADE: " + prioridade);

	}

	// GETTERS E SETTERS
	// =====================================================================

	public int getIdProcesso() {
		return idProcesso;
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
		return timeLine;
	}

	public void setTimeLine(int timeLine) {
		this.timeLine = timeLine;
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
