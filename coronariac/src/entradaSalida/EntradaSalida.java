package entradaSalida;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
		fileChooser.setDialogTitle("Exportar estado de la memoria ");
		int seleccion = fileChooser.showSaveDialog(mntmGuardar);
		
		if(seleccion== JFileChooser.APPROVE_OPTION) {//con esto manejamos el guardado de archivos
			File fichero = fileChooser.getSelectedFile();
			 try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichero))) {
				 	String memoDump=Arrays.toString(memoria.getRamToda());
		            writer.write(memoDump); 
		            System.out.println("Se ha guardado correctamente");
		        } catch (IOException e) {
		            System.err.println("Error al guardar el archivo: " + e.getMessage());
		        }
			
		}
	}
	
	/**
	 * 
	 * Carga un archivo con el estado de la memoria, es como guardarMemo pero a la inversa
	 * 
	 * @param mntmGuardar : Esto es el botón en el que se accede al susodicho menú de guardado.
	 * @param memoria : es el estado actual de la memoria.
	 * **/
	public void cargarArchivo(JMenuItem mntmGuardar,Memoria memoria) {
		System.out.println("Se va a abrir el archivo y se va a guardar en la memoria");	
		JMenuItem advertencia = new JMenuItem("Advertencia");
		JOptionPane.showMessageDialog(advertencia, "Función no disponible por el momento");
		
		
	}
	/**
	 * 
	 * Función para buscar un archivo tipo tarjeta de datos y almacenarlos en el arrayList de entrada
	 * 
	 * **/
	 public void buscarTarjetaEntrada() {
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle("Seleccione un archivo .tjd ");
	        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() { //<==Con esto, me aseguro de que es sólo con los archivos.tjd
	            @Override
	            public boolean accept(File file) {
	                return file.isDirectory() || file.getName().toLowerCase().endsWith(".tjd");
	            }

	            @Override
	            public String getDescription() {
	                return "Archivo tipo tarjeta de datos .tjd"; //texto que se muestra en el explorador de java
	            }
	        });
	        int result = fileChooser.showOpenDialog(null);

	        if (result == JFileChooser.APPROVE_OPTION) { //si se eleije un archivo
	            File selectedFile = fileChooser.getSelectedFile();

	            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
	                entrada.clear(); // quitamos entradas previas no sea que haya conglivyo

	                String linea; //convertirmos la entrada  a algo que se pueda guardar en el programa
	                while ((linea = br.readLine()) != null) {
	                    if (linea.matches("\\[\\d+\\] .*")) {
	                        String valor = linea.substring(linea.indexOf("]") + 2);
	                        entrada.add(valor);
	                    } else {
	                        System.out.println("Línea no válida: " + linea);//puede ser que no sea de 25 sólo haya una cosa en el input
	                    }
	                }
	                //con esto me aseguro que no haya problemas al cargar cosas que no son .tjd
	                System.out.println("Archivo procesado correctamente. Datos cargados en 'entrada'.");
	            } catch (IOException e) {
	                System.err.println("Error al leer el archivo: " + e.getMessage());
	            }
	        } else {//si se cancela la apertura
	            System.out.println("Selección de archivo cancelada.");
	        }
	    }
	 
	 
	 public String ubicacionSalida() {
		    JFileChooser fileChooser = new JFileChooser();
		    fileChooser.setDialogTitle("Seleccione una carpeta donde guardar su tarjeta de datos");//con esto se establece el titulo de la pantalla
		    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    int result = fileChooser.showDialog(null, "Aceptar");
		    //int result = fileChooser.showSaveDialog(null);
		    if (result == JFileChooser.APPROVE_OPTION) {
		        return fileChooser.getSelectedFile().getAbsolutePath();
		    } else {
		        
		        return null;
		    }
		}
	 



	

}