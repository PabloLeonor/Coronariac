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
import entradaSalida.EntradaSalida;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import java.awt.Rectangle;
import java.awt.Label;

public class VentanaMemoria extends JFrame {

	final String version ="Versión 1 Es";
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List<JLabel> labelsPos; // lista para almacenar las etiquetas de posiciones
	private List<JTextPane> labelsCeldas; //lista para almacenar las celdas
	private JTextPane textoSalida= new JTextPane(); // declaramos textoSalida como atributo de la clase
	private Label labelUbicacion = new Label();

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
		lblNewLabel.setBounds(10, 343, 44, 14);
		contentPane.add(lblNewLabel);
		
		JButton botonProgramar = new JButton("Programar");
		botonProgramar.setBounds(538, 368, 118, 23);
		contentPane.add(botonProgramar);
		
		
		this.textoSalida.setText((String) null);
		this.textoSalida.setForeground(Color.BLACK);
		this.textoSalida.setEditable(false);
		this.textoSalida.setBounds(new Rectangle(1, 1, 1, 1));
		this.textoSalida.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.textoSalida.setBounds(10, 371, 49, 20);
		contentPane.add(this.textoSalida);
		
		//Label labelUbicacion = new Label("Procesando ubicación: X");
		labelUbicacion.setText("Procesando ubicación 0");
		labelUbicacion.setBounds(10, 0, 210, 21);
		contentPane.add(labelUbicacion);
				
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

			labelsPos.add(labelPos); // agregamos cada etiqueta a la lista
			
			y += 22;
			if (i == 16 || i == 33 || i == 50 || i == 67 || i == 84) {
				x += 90;
				y = 21;
			}
		}
		
		//======Acciones=======================
		
		botonProgramar.addActionListener(e -> {
		    for (int i = 0; i < labelsPos.size(); i++) {
		        try {
		            JLabel label = labelsPos.get(i);
		            int posicion = Integer.parseInt(label.getText());
		            
		            // Obtenemos el valor desde labelsCeldas
		            JTextPane celda = labelsCeldas.get(i);
		            String valor = celda.getText().trim(); // con esto quitamos los valores en blanco

		            //vemos si hay 3 digitos o es negativo
		            if (!valor.matches("-?\\d{3}")) {
		            	 JOptionPane.showMessageDialog(null, "El valor en la celda debe ser un número de exactamente 3 dígitos. Valor inválido: " + valor);
		                throw new IllegalArgumentException(
		                		
		                    "El valor en la celda debe ser un número de exactamente 3 dígitos. Valor inválido: " + valor);
		               
		            }

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
	
	public void escribirTextoUbicacionActual(int posicion) {
		this.labelUbicacion.setText("Procesando ubicación "+posicion);
	}
	
	public void actualizarTarjetaSalida(EntradaSalida io) {
		if(io.getSalida().isEmpty()) {
			this.textoSalida.setText("");
		}else {
			this.textoSalida.setText(io.getSalida().getLast());
		}
		

	}
	
	public void actualizarVista(Memoria memoria) {
	    for (int i = 0; i < labelsCeldas.size(); i++) {
	        String valor = memoria.getRam(i);
	        labelsCeldas.get(i).setText(valor);  // actualiza las celdas de la interfaz
	    }
	}
}
