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
import javax.swing.filechooser.FileNameExtensionFilter;

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
    public void guardarMemo(JMenuItem mntmGuardar, Memoria memoria) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Exportar estado de la memoria");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo tipo .ROM (ROM de Coronariac)", "ROM");
        fileChooser.setFileFilter(filter);
        
        int seleccion = fileChooser.showSaveDialog(mntmGuardar);
        if (seleccion == JFileChooser.APPROVE_OPTION) { //aquí vemos que sea.rom
            File fichero = fileChooser.getSelectedFile();
            if (!fichero.getName().toLowerCase().endsWith(".rom")) {
                fichero = new File(fichero.getAbsolutePath() + ".ROM");
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichero))) {
                String memoDump = Arrays.toString(memoria.getRamToda());
                writer.write(memoDump);
                System.out.println("Se ha guardado correctamente: " + fichero.getAbsolutePath());
                JOptionPane.showMessageDialog(null, "El estado de la memoria se ha exportado con éxito.");
            } catch (IOException e) {
            	JOptionPane.showMessageDialog(null, "Fallo al exportar.");
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
    public void cargarArchivo(JMenuItem mntmGuardar, Memoria memoria) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Cargar archivo ROM");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo tipo .ROM (ROM de Coronariac)", "ROM");
        fileChooser.setFileFilter(filter);
        int seleccion = fileChooser.showOpenDialog(mntmGuardar);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();

            StringBuilder contenido = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    contenido.append(linea);
                }
                // se guarda todo en el string
                String memo = contenido.toString();
                System.out.println("Archivo cargado con éxito:");
                System.out.println(memo); 
                
                //ahora procesamos memo y lo cargamos a la memoria
                memo = memo.replace("[", "");
                memo = memo.replace("]", "");
                memo = memo.replace(" ", "");
	            String[] memoProcesado = new String[100] ;
	            memoProcesado= memo.split(",");
	            int contador=0;
	            for (String datos : memoProcesado) {
	            	System.out.println("["+contador+"]" + datos);
	            	memoria.setRam(contador, datos);
	            	contador++;
	            }
	            JOptionPane.showMessageDialog(null, "Estado de la memoria importada con éxito");
                
            } catch (IOException e) {
            	JOptionPane.showMessageDialog(null, "Fallo al importar el archivo");
                System.err.println("Error al cargar el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("Carga de archivo cancelada.");
        }
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
					JOptionPane.showMessageDialog(null, "Tarjeta de entrada correctamente.");
	            } catch (IOException e) {
	                System.err.println("Error al leer el archivo: " + e.getMessage());
	                JOptionPane.showMessageDialog(null, "Archivo no válido o corrupto.");
	            }
	        } else {//si se cancela la apertura
	            System.out.println("Selección de archivo cancelada.");
	        }
	    }
	 
	 /**
	  * 
	  * Devuelve un String que es la dirección de la memoria del usuario donde se va aguardar el archivo de salida
	  * 
	  * **/
	 public String ubicacionSalida() {
		    JFileChooser fileChooser = new JFileChooser();
		    fileChooser.setDialogTitle("Seleccione una carpeta donde guardar su tarjeta de datos");//con esto se establece el titulo de la pantalla
		    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    int result = fileChooser.showDialog(null, "Aceptar");
		    if (result == JFileChooser.APPROVE_OPTION) {
		        return fileChooser.getSelectedFile().getAbsolutePath();
		    } else {
		    	JOptionPane.showMessageDialog(null, "Fallo al establecer ubicación de salida");
		        return null;
		    }
		}
	 



	

}