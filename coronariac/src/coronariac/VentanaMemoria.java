package coronariac;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import control.Flags;
import coronariac.partesOrdenador.Memoria;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Toolkit;

public class VentanaMemoria extends JFrame {

	final String version ="Versión 0.6";
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List<JLabel> labelsPos; // lista para almacenar las etiquetas de posiciones
	private List<JTextPane> labelsCeldas; //lista para almacenar las celdas

	/**
	 * Create the frame.
	 */
	public VentanaMemoria(Memoria memoria, Flags flag) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaMemoria.class.getResource("/img/iconoPequennoMemo.png")));
		setResizable(false);
		setTitle("Celdas de Memoria Coronariac "+version);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// iniciamos las listas
		labelsPos = new ArrayList<>();
		labelsCeldas = new ArrayList<>();
		
		JLabel lblNewLabel = new JLabel("Salida");
		lblNewLabel.setBounds(10, 388, 44, 14);
		contentPane.add(lblNewLabel);
		
		JLabel labelSalida = new JLabel("0");
		labelSalida.setBounds(64, 388, 29, 14);
		contentPane.add(labelSalida);
		
		JButton botonProgramar = new JButton("Programar");
		botonProgramar.setBounds(538, 368, 118, 23);
		contentPane.add(botonProgramar);
				
		int x = 83;
		int y = 21;
		
		for (int i = 0; i <= 99; i++) {
			JTextPane celda = new JTextPane();
			celda.setBounds(x + 21, y, 49, 20);
			celda.setText(memoria.getRam(i));
			celda.setBackground(new Color(153, 169, 255));
			contentPane.add(celda);
			
			labelsCeldas.add(celda);
			
			JLabel labelPos = new JLabel(Integer.toString(i));
			labelPos.setBounds(x, y, 49, 14);
			contentPane.add(labelPos);

			labelsPos.add(labelPos); // Agregamos cada etiqueta a la lista
			
			y += 22;
			if (i == 16 || i == 33 || i == 50 || i == 67 || i == 84) {
				x += 90;
				y = 21;
			}
		}
		
		//======Acciones=======================
		
		botonProgramar.addActionListener(e -> {
		    // Recorremos ambas listas en paralelo para obtener posición y valor
		    for (int i = 0; i < labelsPos.size(); i++) {
		        try {
		            // Obtenemos la posición desde labelsPos
		            JLabel label = labelsPos.get(i);
		            int posicion = Integer.parseInt(label.getText());
		            
		            // Obtenemos el valor desde labelsCeldas
		            JTextPane celda = labelsCeldas.get(i);
		            String valor = celda.getText().trim(); // Eliminamos espacios en blanco

		            // Validamos que el valor tiene exactamente 3 dígitos
		            if (!valor.matches("-?\\d{3}")) {
		                throw new IllegalArgumentException(
		                    "El valor en la celda debe ser un número de exactamente 3 dígitos. Valor inválido: " + valor);
		            }

		            // Asignamos el valor a la memoria
		            System.out.println("Posición = " + posicion + " Valor = " + valor);
		            memoria.setRam(posicion, valor);
		        } catch (NumberFormatException ex) {
		            System.err.println("Error al convertir la posición a número: " + ex.getMessage());
		        } catch (IllegalArgumentException ex) {
		            System.err.println("Error en el valor: " + ex.getMessage());
		        } catch (IndexOutOfBoundsException ex) {
		            System.err.println("Posición fuera de los límites de la memoria: " + ex.getMessage());
		        }
		    }
		});

		
	}
	
	public void actualizarVista(Memoria memoria) {
	    for (int i = 0; i < labelsCeldas.size(); i++) {
	        String valor = memoria.getRam(i);
	        labelsCeldas.get(i).setText(valor);  // Actualiza las celdas de la interfaz
	    }
	}

}
