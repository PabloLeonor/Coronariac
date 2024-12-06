package coronariac;

import java.awt.EventQueue;

import control.Flags;
import coronariac.partesOrdenador.Memoria;

public class Main {

	public static void main(String[] args) {
		Memoria memoria = new Memoria();
		Flags flag = new Flags();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal(memoria,flag);
					VentanaMemoria frameMemo = new VentanaMemoria(memoria,flag);
					
					frame.setVisible(true);
					frameMemo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
