package teste;

import java.awt.EventQueue;

import interfece_grafica.JanelaPrincipal;

public class TesteInterfaceGrafica {
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {

					new JanelaPrincipal();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		
		
	}
}
