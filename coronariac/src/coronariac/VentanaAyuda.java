package coronariac;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.awt.TextArea;
import java.awt.Toolkit;

public class VentanaAyuda extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public VentanaAyuda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaAyuda.class.getResource("/img/iconoPequenno.png")));
		setTitle("Manual de usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TextArea textArea = new TextArea();
		textArea.setText("-------CORONARIAC------\r\n\r\nPurede descargar una versión ampliada de este manual en\r\nel README de github: https://github.com/PabloLeonor/Coronariac\r\n\r\n====Índice====\r\n¿Qué es Coronariac?\r\nVentanas de Coronariac\r\nCómo usar Coronariac\r\n\r\n====¿Qué es Coronariac?====\r\nCoronariac es un emulador del ordenador Cardiac\r\nde Bell Labs, el cual fue desarrollado en los años\r\n60 para enseñar a los estudiantes de aquella \r\népoca qué es un ordenador y como se usa.\r\n\r\n====Venantas de Coronariac====\r\nAl iniciar su copia de Coronariac, puede ver 2 \r\nventanas, una es la ventana principal, en ella\r\npuede encontrar el menú y una descripción del\r\nfuncionamiento del emulador.\r\n\r\nEn la otra ventana, se encuentra un desglose de\r\nla memoria del emulador, así como un texto\r\nque muestra la salida del ordenador\r\n\r\n====Cómo usar Coronariac====\r\nPara usar coronariac, lo primero que debe hacer es \r\nbuscar un archivo tipo tarjeta de datos y establecer\r\nun luagar de su ordenador .Esto puede hacerlo \r\nmediante :\r\nArchivo/Establecer archivo de entrada\r\ny \r\nConfiguración/Establecer ubicación de salida\r\n\r\nDespués escriba en la ventana de memoria\r\nsu programa usando los 10 opcodes de\r\nCardiac que Coronariac puede leer que son:\r\n0= INP Entrada\r\n1= CLA limpiar acumulador y cargar en él\r\n2= ADD Sumar\r\n3= TAC Comprobar el signo del acumulador y \r\nsalto condicional si es negativo\r\n4= SFT Desplazamiento lateral\r\n5= OUT salida\r\n6= STO guardar el resultado del acumulador\r\n7= SUB restar\r\n8= JMP salto incondicional\r\n9= HRS parada de la máquina y reset\r\n\r\npuede cargar el siguiente programa, que se\r\nencuentra dentro del manual de CARDIAC \r\nque simplemente suma 2 números en la\r\nentrada:\r\n[0] 024\r\n[1] 025\r\n[2] 124\r\n[3] 225\r\n[4] 626\r\n[5] 526\r\n[6] 900\r\n\r\nEste programa sumará dos datos\r\nde la entrada y los sacará como la\r\nsalida, la cual puede ver en\r\nVer/Ver contenido de la salida");
		textArea.setBounds(9, 10, 416, 243);
		contentPane.add(textArea);
	}
}
