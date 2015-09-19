package algoritmos;

import java.awt.Color;
import java.awt.Font;
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

	JLabel idLabel;
	JLabel tempoRestante;
	JPanel painel;

	@Override
	public void run() {
		super.run();

		while (isVivo) {
			try {

				this.tempoExecucao = tempoExecucao - 1;
				this.tempoRestante.setText("TR: " + this.tempoExecucao);

				if (tempoExecucao == 0) {
					this.isVivo = false;
					this.painel.removeAll();
					this.painel.setBackground(Color.RED);
				}

				Thread.sleep(1000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public Processo(int iD, JPanel panel) {

		Random gerador = new Random();

		this.idProcesso = iD;
		this.tempoExecucao = 10 + gerador.nextInt(90);
		this.timeLine = 10 + gerador.nextInt(10);
		this.prioridade = gerador.nextInt(3);
		this.isVivo = true;
		this.painel = panel;

		this.idLabel = new JLabel("ID: " + this.idProcesso);
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);

		this.tempoRestante = new JLabel("TR: " + this.tempoExecucao);
		tempoRestante.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tempoRestante.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(idLabel);
		panel.add(tempoRestante);
		panel.setBackground(Color.green);

		System.out.println("=============================================================");
		System.out.println("PROCESSO " + this.idProcesso + " CRIADO....");
		System.out.println("TEMPO DE EXECUCAO: " + tempoExecucao);
		System.out.println("TIME LINE: " + timeLine);
		System.out.println("PRIORIDADE: " + prioridade);

	}

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
