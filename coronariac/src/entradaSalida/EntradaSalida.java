package entradaSalida;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import coronariac.partesOrdenador.Memoria;

public class EntradaSalida {
	//funciones relacionadas con la entrada y salida y 
	
	/**
	 * 
	 * Función que se usa para guardar el estado actual de la memoria.
	 * 
	 * @param mntmGuardar : Esto es el botón en el que se accede al susodicho menú de guardado.
	 * @param memoria : es el estado actual de la memoria.
	 * **/
	public void guardarMemo(JMenuItem mntmGuardar,Memoria memoria) {
		JFileChooser fileChooser = new JFileChooser();
		int seleccion = fileChooser.showSaveDialog(mntmGuardar);
		
		if(seleccion== JFileChooser.APPROVE_OPTION) {//con esto manejamos el guardado de archivos
			System.out.println("se ha guardado correctamente manim");
			File fichero = fileChooser.getSelectedFile();
			 try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichero))) {
				 	String memoDump=Arrays.toString(memoria.getRamToda());
		            writer.write(memoDump); // Escribimos el texto en el archivo
		            System.out.println("Se ha guardado correctamente, manim");
		        } catch (IOException e) {
		            System.err.println("Error al guardar el archivo: " + e.getMessage());
		        }
			
		}
	}
	
	public void cargarArchivo(JMenuItem mntmGuardar,Memoria memoria) {
		System.out.println("Se va a abrir el archivo y se va a guardar en la memoria");	
		
	}
	

}
