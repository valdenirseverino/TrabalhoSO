package teste;

import java.awt.EventQueue;

import interfece_grafica.JanelaInicial;

public class Teste {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					new JanelaInicial();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}