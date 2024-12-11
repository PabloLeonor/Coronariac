package entradaSalida;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import coronariac.partesOrdenador.Memoria;

public class EntradaSalida {
	private ArrayList<String> entrada;//se almacenan los datos de entrada
    private ArrayList<String> salida;//se almacenan los datos de salida

    // Constructor
    public EntradaSalida() {
        entrada = new ArrayList<>();  // Inicializa el ArrayList de entrada
        salida = new ArrayList<>();   // Inicializa el ArrayList de salida
    }
	
	//funciones relacionadas con la entrada y salida 
    
    
    public void setEntrada(String dato) {
        entrada.add(dato);
    }

    public void setSalida(String dato) {
        salida.add(dato); 
    }

    public ArrayList<String> getEntrada() {
        return entrada;    
    }

    public ArrayList<String> getSalida() {
        return salida;      
    }
	
    /**
     * 
     * Recupera el primer valor del array list
     * 
     * **/
    public String getPrimerValor() {
        return entrada.isEmpty() ? null : entrada.get(0); //esto evita que pete si el array está vacío!
    }
    
    
    /**
     * 
     * Elimina el primer valor del array list
     * 
     * **/
    public void eliminarPrimerValor() { //esto elimina el primer valor de la entrada, así no hace falta ir contando nada, hacemos del array una pila1
        if (!entrada.isEmpty()) {
            entrada.remove(0);
        }
    }

	
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
			File fichero = fileChooser.getSelectedFile();
			 try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichero))) {
				 	String memoDump=Arrays.toString(memoria.getRamToda());
		            writer.write(memoDump); // Escribimos el texto en el archivo
		            System.out.println("Se ha guardado correctamente");
		        } catch (IOException e) {
		            System.err.println("Error al guardar el archivo: " + e.getMessage());
		        }
			
		}
	}
	
	public void cargarArchivo(JMenuItem mntmGuardar,Memoria memoria) {
		System.out.println("Se va a abrir el archivo y se va a guardar en la memoria");	
		JMenuItem advertencia = new JMenuItem("Advertencia");
		JOptionPane.showMessageDialog(advertencia, "Función no disponible por el momento");
		
		
	}

	

}
