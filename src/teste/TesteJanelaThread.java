package teste;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import algoritmos.Processo;

public class TesteJanelaThread {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TesteJanelaThread window = new TesteJanelaThread();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TesteJanelaThread() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		for (int i = 0; i < 10; i++) {
			JPanel panel = new JPanel();
			panel.setSize(50, 50);
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			frame.getContentPane().add(panel);

			Processo processo = new Processo(1, panel);
			processo.start();
		}

	}
}
