package algoritmos;

import java.util.Random;

public class Processo {

	private int iD;
	private int tempoExecucao;
	private int timeLine;
	private int prioridade;

	public Processo(int iD) {

		Random gerador = new Random();

		this.iD = iD;
		this.tempoExecucao = gerador.nextInt(100);
		this.timeLine = gerador.nextInt(10);
		this.prioridade = gerador.nextInt(3);

		System.out.println("=============================================================");
		System.out.println("PROCESSO " + iD + " CRIADO....");
		System.out.println("TEMPO DE EXECUCAO: " + tempoExecucao);
		System.out.println("TIME LINE: " + timeLine);
		System.out.println("PRIORIDADE: " + prioridade);

	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
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

}
