package coronariac;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Control;
import control.Flags;
import coronariac.partesOrdenador.ContadorDePrograma;
import coronariac.partesOrdenador.Memoria;
import entradaSalida.EntradaSalida;

import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel contentPane_1;

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal(Memoria memoria, Flags flag) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/iconoPequenno.png")));
		
		//objetos
		ContadorDePrograma contador= new ContadorDePrograma();
		EntradaSalida io = new EntradaSalida();
		Control cu = new Control(flag);
		
		//botones
		setTitle("Coronariac V0.1");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 450);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir...");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				io.cargarArchivo(mntmAbrir,memoria);
				System.out.println("Se va a cargar el archivo");
			}
		});
		mnNewMenu.add(mntmAbrir);
		contentPane = new JPanel();
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mnNewMenu.add(mntmGuardar);
		
		mntmGuardar.addActionListener(e -> {
			io.guardarMemo(mntmGuardar,memoria);
			System.out.println("Se va a guardar el archivo");
		});
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnNewMenu.add(mntmSalir);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de..");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mntmAcercaDe, "Gracias por usar Coronariac :)\nversión 0.1\nCoronariac © 2024 by Pablo Leonor is licensed under CC BY-NC-SA 4.0.\nTo view a copy of this license, visit https://creativecommons.org/licenses/by-nc-sa/4.0/");
			}
		});
		mnAyuda.add(mntmAcercaDe);
		
		JMenuItem mntmAyuda = new JMenuItem("Ayuda");
		mntmAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mntmAyuda, "Soy una ayuda");

			}
		});
		mnAyuda.add(mntmAyuda);
		contentPane_1 = new JPanel();
		
		
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/NuevoLogo.png")));
		lblNewLabel.setBounds(157, 17, 593, 90);
		contentPane_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/parada.png")));
		lblNewLabel_1.setBounds(538, 202, 64, 64);
		contentPane_1.add(lblNewLabel_1);
		
		JLabel lblInstruccin = new JLabel("Instrucción");
		lblInstruccin.setBounds(434, 141, 109, 36);
		contentPane_1.add(lblInstruccin);
		
		JLabel lblNewLabel_2 = new JLabel("Copiar contenidos de la entrada a la celda 00");
		lblNewLabel_2.setBounds(341, 177, 343, 14);
		contentPane_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Registro de\r instrucciones");
		lblNewLabel_3.setBounds(434, 310, 208, 14);
		contentPane_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("000");
		lblNewLabel_4.setBounds(511, 335, 49, 14);
		contentPane_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Acumulador");
		lblNewLabel_5.setBounds(236, 279, 84, 14);
		contentPane_1.add(lblNewLabel_5);
		
		JLabel acumuladorPrimerOperando = new JLabel("000");
		acumuladorPrimerOperando.setBounds(246, 304, 49, 14);
		contentPane_1.add(acumuladorPrimerOperando);
		
		JLabel acumuladorSegundoOperando = new JLabel("000");
		acumuladorSegundoOperando.setBounds(246, 323, 49, 14);
		contentPane_1.add(acumuladorSegundoOperando);
		
		JLabel acumuladorResultado = new JLabel("000");
		acumuladorResultado.setBounds(246, 345, 49, 14);
		contentPane_1.add(acumuladorResultado);
		
		JLabel lblNewLabel_6 = new JLabel("Entrada");
		lblNewLabel_6.setBounds(33, 304, 49, 14);
		contentPane_1.add(lblNewLabel_6);
		
		JTextPane textoEntrada = new JTextPane();
		textoEntrada.setBounds(33, 329, 49, 20);
		contentPane_1.add(textoEntrada);
		
		JLabel lblNewLabel_7 = new JLabel("0=INP");
		lblNewLabel_7.setBounds(33, 55, 49, 14);
		contentPane_1.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("1=CLA");
		lblNewLabel_8.setBounds(33, 70, 49, 14);
		contentPane_1.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("2=ADD");
		lblNewLabel_9.setBounds(33, 85, 49, 14);
		contentPane_1.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("3=TAC");
		lblNewLabel_10.setBounds(33, 100, 49, 14);
		contentPane_1.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("4=SFT");
		lblNewLabel_11.setBounds(33, 115, 49, 14);
		contentPane_1.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("5=OUT");
		lblNewLabel_12.setBounds(95, 55, 49, 14);
		contentPane_1.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("6=STO");
		lblNewLabel_13.setBounds(95, 70, 49, 14);
		contentPane_1.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("7=SUB");
		lblNewLabel_14.setBounds(95, 85, 49, 14);
		contentPane_1.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("8=JMP");
		lblNewLabel_15.setBounds(95, 100, 49, 14);
		contentPane_1.add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("9=HRS");
		lblNewLabel_16.setBounds(95, 115, 49, 14);
		contentPane_1.add(lblNewLabel_16);
		
		JLabel posicionContador = new JLabel(Integer.toString(contador.getPosicion()));
		posicionContador.setBounds(110, 252, 49, 14);
		contentPane_1.add(posicionContador);
		
		JButton botonStep = new JButton("STEP");
		botonStep.setBounds(33, 218, 90, 23);
		contentPane_1.add(botonStep);
		
		botonStep.addActionListener(e -> {
		    try {
		        // Primero se interpreta la instrucción
		        System.out.println("Se va a leer la celda número " + contador.getPosicion());
		        cu.setMAR(contador.getPosicion());

		        // Convertir el valor de RAM (String) a int antes de pasarlo
		        String instruccion = memoria.getRam(contador.getPosicion());
		        cu.setInstruccion(instruccion);

		        cu.decodificar();

		        // Ahora se actualiza el contador de programa
		        contador.annadirStep(); // Incrementa el contador de programa +1
		        posicionContador.setText(Integer.toString(contador.getPosicion())); // Actualiza el label
		    } catch (NumberFormatException ex) {
		        System.err.println("Error al convertir la instrucción a un número: " + ex.getMessage());
		    }
		});

		
		JButton btnAuto = new JButton("AUTO");
		btnAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnAuto, "Función no disponible por el momento","", JOptionPane.WARNING_MESSAGE);
				
			}
		});
		btnAuto.setBounds(133, 218, 90, 23);
		contentPane_1.add(btnAuto);
		
		JButton botonReset = new JButton("RESET");
		botonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contador.setPosicion(0);// se establece el contador de programa a cero
				posicionContador.setText(Integer.toString(contador.getPosicion()));//se actualiza el label
			}
		});
		botonReset.setBounds(33, 197, 90, 23);
		contentPane_1.add(botonReset);
		
	}
}
