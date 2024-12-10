package coronariac.partesOrdenador;

public class Memoria {
    private final int tamannoMemoria = 100; // Tamaño de la memoria
    private String[] ram = new String[tamannoMemoria]; // Memoria como array de Strings

    // Constructor que inicializa la RAM con valores por defecto ("000")
    public Memoria() {
        for (int i = 0; i < tamannoMemoria; i++) {
            ram[i] = "000";
        }
    }

    /**
     * Devuelve el valor almacenado en la posición indicada de la RAM
     *
     * @param posicion la posición de la RAM a consultar
     * @return el valor almacenado en la posición como String
     */
    public String getRam(int posicion) {
        if (posicion >= 0 && posicion < tamannoMemoria) {
            return ram[posicion];
        } else {
            throw new IndexOutOfBoundsException("Posición fuera de los límites de la RAM.");
        }
    }

    /**
     * Devuelve toda la RAM como un array de Strings
     *
     * @return el array completo de la RAM
     */
    public String[] getRamToda() {
        return ram;
    }

    /**
     * Asigna un valor a una posición específica de la RAM
     *
     * @param posicion la posición de la RAM a modificar
     * @param valor    el nuevo valor que se quiere almacenar
     */
    public void setRam(int posicion, String valor) {
        if (posicion >= 0 && posicion < tamannoMemoria) {
            if (valor != null && valor.matches("-?\\d{3}")) { // Valida que el valor sea un String con 3 dígitos ne
                ram[posicion] = valor;
            } else {
                throw new IllegalArgumentException("El valor debe ser un String de exactamente 3 dígitos.");
            }
        } else {
            throw new IndexOutOfBoundsException("Posición fuera de los límites de la RAM.");
        }
    }
}
