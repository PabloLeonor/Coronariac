package control;

import javax.swing.JOptionPane;

import coronariac.partesOrdenador.ContadorDePrograma;
import coronariac.partesOrdenador.Memoria;
import entradaSalida.EntradaSalida;

public class Control {
	
	private int MAR;//memory adress register
	private int primerOperandoAcc;
	private int segundoOperandoAcc;
	private int resultadoAcc;
	private String instruccion;//el contenido del MAR
	private Flags flag; 
	private Memoria memo;
	private ContadorDePrograma contador;
	private EntradaSalida io;
	private String textoEntradaDescodificada;
	
	public Control(Flags flag, Memoria memo,ContadorDePrograma contador,EntradaSalida io) {
		this.primerOperandoAcc=0;
		this.segundoOperandoAcc=0;
		this.resultadoAcc=0;
		this.MAR=0;
		this.instruccion="0";
		this.flag = flag;
		this.memo = memo;
		this.contador = contador;
		this.io = io;
		this.textoEntradaDescodificada="";
	}
	
	public String getTextoEntradaDescodificada() {
		return textoEntradaDescodificada;
	}

	public void setTextoEntradaDescodificada(String textoEntradaDescodificada) {
		this.textoEntradaDescodificada = textoEntradaDescodificada;
	}

	public int getPrimerOperandoAcc() {
		return primerOperandoAcc;
	}

	public void setPrimerOperandoAcc(int primerOperandoAcc) {
		this.primerOperandoAcc = primerOperandoAcc;
	}

	public int getSegundoOperandoAcc() {
		return segundoOperandoAcc;
	}

	public void setSegundoOperandoAcc(int segundoOperandoAcc) {
		this.segundoOperandoAcc = segundoOperandoAcc;
	}

	public int getResultadoAcc() {
		return resultadoAcc;
	}

	public void setResultadoAcc(int resultadoAcc) {
		this.resultadoAcc = resultadoAcc;
	}

	public String getInstruccion() {
		return instruccion;
	}

	public void setInstruccion(String instruccion) {
		this.instruccion = instruccion;
	}

	public int getMAR() {
		return MAR;
	}

	public void setMAR(int MAR) {
		this.MAR = MAR;
	}
	
	
	public void decodificar() {
		//primero se desglosa la instrucción tal cual y el dato
		
		char signo;
		String rawInstruccion=this.instruccion;
		int instruccion = Integer.valueOf(rawInstruccion.substring(0,1));
		int dato = Integer.valueOf(rawInstruccion.substring(1));
		System.out.println("		["+dato+"]["+instruccion+"]");
		switch(instruccion) {
			case 0:
				System.out.println("		Instrucción cero: INP y el dato es: "+dato);
				if(io.getEntrada().isEmpty()) {
					JOptionPane.showInternalMessageDialog(null, "No hay una entrada establecida.\nPor favor, cargue una nueva tarjeta de entrada.");
					System.out.print("\n			No hay entrada");
				}else {
					System.out.print("\n			Contenido de la entrada: "+io.getEntrada());
					System.out.print("\n			Leyendo contenido de la entrada "+io.getPrimerValor());
					System.out.print("\n			Guardando en: "+dato);
					memo.setRam(dato, io.getPrimerValor());
					io.eliminarPrimerValor();
					System.out.print("\n			Contenido de la entrada: "+io.getEntrada());
					this.setTextoEntradaDescodificada("<html>"
				            + "Copiar el contenido de la entrada a "
				            + "la celda ["+dato+"] y avanzar una posición"
				            + "</html>");
				}
				break;
				
			case 1:
				System.out.println("		Instrucción una: CLA y el dato es: "+dato);
				System.out.print("			Introduciendo el contenido de la posición de memoria "+dato+" en el acumulador");
				
				this.primerOperandoAcc=0;
				this.segundoOperandoAcc=0;
				this.resultadoAcc=0;
				flag.setFlagSigno('+');
				
				this.primerOperandoAcc= Integer.valueOf(memo.getRam(dato));
				System.out.println("\n			Primer operando acc: "+this.primerOperandoAcc);
				this.setTextoEntradaDescodificada("<html>"
			            + "Llevar al acumulador el contenido de "
			            + "la celda ["+dato+"]"
			            + "</html>");
				
				break;
				
			case 2:
				System.out.println("		Instrucción dos: ADD y el dato es: "+dato);
				System.out.print("			Introduciendo el contenido de la posición de memoria "+dato+" en el acumulador");
				System.out.print("\n			Sumando acumulador");
				
				this.segundoOperandoAcc= Integer.valueOf(memo.getRam(dato));
				System.out.print("\n			Segundo operando acc" +this.segundoOperandoAcc);
				this.resultadoAcc = this.primerOperandoAcc+this.segundoOperandoAcc;
				System.out.print("\n			Resultado de la suma" +this.resultadoAcc);
				System.out.print("\n			Asignando flag del signo del resultado");
				
				if(this.resultadoAcc>=0) {
					this.flag.setFlagSigno('+');
				}else{
					this.flag.setFlagSigno('-');
				}
				System.out.print("Signo ["+ this.flag.getFlagSigno()+"]");
				this.setTextoEntradaDescodificada("<html>"
			            + "Llevar al acumulador el contenido de "
			            + "la celda ["+dato+"]"
			            + "</html>");
				break;
				
			case 3:
				System.out.println("		Instrucción tres: TAC y el dato es: "+dato);
				System.out.print("\n			Contenido del flag signo ["+this.flag.getFlagSigno()+"]");
				
				if(this.flag.getFlagSigno()=='+') {
					System.out.print("\n			El programa continuará");
				}else {
					System.out.print("\n			El programa saltará a la posición "+dato);
					contador.setPosicion(dato);
				}
				this.setTextoEntradaDescodificada("<html>"
			            + "Ir a la celda ["+dato+"]"
			            + "</html>");
				
				break;
				
			case 4:
				System.out.println("		Instrucción cuatro: SFT y el dato es: "+dato);
				int datoIzq;
				int datoDcha;
				//si el dato es menor que 10, (entre 0 y 9) se tomará en consideración sólo el desplazamiento a la derecha
				if(dato<9 && dato>=0) {
					datoDcha=dato;
					datoIzq=0;
				}else {
					
					datoDcha=dato%10;
					datoIzq = dato/10;
				}
				
				System.out.println("partiendo dato por la mitad: "+dato+" "+datoIzq+" "+datoDcha);
				//ahora se produce el desplazamiento a la izquierda en función del número de desplazamientos dado, primero a la izquierda y luego a la derecha:
				String accString= Integer.toString(this.resultadoAcc);
				for (int i = 1; i <= datoIzq; i++) {
				    accString+='0';
				}
			
				if (accString.length() > 4) {
				    accString = accString.substring(accString.length() - 4);
				}

				System.out.println("Se ha desplazado " + datoIzq + " posiciones a la izquierda: " + this.resultadoAcc + " es: " + accString);
				
				//ahora a la derecha
				for (int i = 1; i <= datoDcha; i++) {
				    accString='0'+accString;
				}
				
				// Asegúrate de que accString tiene al menos 4 caracteres
				if (accString.length() < 4) {
				    // Completar con ceros a la izquierda
				    while (accString.length() < 4) {
				        accString = '0' + accString;
				    }
				} else {
				    // Tomar los 4 caracteres más a la izquierda
				    accString = accString.substring(0, 4);
				}
				
				this.resultadoAcc=Integer.valueOf(accString);
				this.setTextoEntradaDescodificada("<html>"
			            + "Desplazar el contenido del acumulador ["+datoIzq+"] posiciones  "
			            + "a la izquierda y después desplazar ["+datoDcha+"] posiciones a la derecha"
			            + "</html>");

				System.out.println("Se ha desplazado " + datoDcha + " posiciones a la derecha: " + this.resultadoAcc + " es: " + accString);
				
				
				break;
				
			case 5:
				System.out.println("		Instrucción cinco: OUT y el dato es: "+dato);
				System.out.print("		Leyendo datos de la celda "+dato);
				String contenido = memo.getRam(dato);
				System.out.print("		Llevando "+contenido+" a la salida");
				io.setSalida(contenido);
				
				this.setTextoEntradaDescodificada("<html>"
			            + "Copiar el contenido de la celda ["+dato+"] en la"
			            + "tarjeta de salida y mostrarla"
			            + "</html>");
				
				break;
				
			case 6:
			    System.out.println("    Instrucción seis: STO y el dato es: " + dato);
			    System.out.print("        Extrayendo el resultado del acumulador y escribiéndolo en la posición " + dato);

			    // Si el resultado es negativo, manejamos el formato para los números negativos
			    if (this.resultadoAcc < 0) {
			        // Formatear el número negativo manteniendo el signo
			        int valorAbs = Math.abs(this.resultadoAcc);
			        if (valorAbs < 10) {
			            memo.setRam(dato, "-00" + valorAbs);
			        } else if (valorAbs < 100) {
			            memo.setRam(dato, "-0" + valorAbs);
			        } else {
			            memo.setRam(dato, String.format("-%03d", valorAbs));
			        }
			    } else {
			        // Para números positivos, formateamos normalmente
			        if (this.resultadoAcc < 10) {
			            memo.setRam(dato, "00" + this.resultadoAcc);
			        } else if (this.resultadoAcc < 100) {
			            memo.setRam(dato, "0" + this.resultadoAcc);
			        } else if (this.resultadoAcc > 999) {
			            int digitosASubir = this.resultadoAcc % 1000;
			            memo.setRam(dato, String.format("%03d", digitosASubir));
			        } else {
			            memo.setRam(dato, Integer.toString(this.resultadoAcc));
			        }
			    }
			    this.setTextoEntradaDescodificada("<html>"
			            + "Guardar en la celda ["+dato+"] el contenido del acumulador"
			            + "</html>");
				//memo.setRam(dato, Integer.toString(this.resultadoAcc));
				
				break;
				
			case 7:
				System.out.println("		Instrucción siete: SUB y el dato es: "+dato);
				System.out.print("			Introduciendo el contenido de la posición de memoria "+dato+" en el acumulador");
				System.out.print("\n			Sumando acumulador");
				
				this.segundoOperandoAcc= Integer.valueOf(memo.getRam(dato));
				System.out.print("\n			Segundo operando acc" +this.segundoOperandoAcc);
				this.resultadoAcc = this.primerOperandoAcc-this.segundoOperandoAcc;
				System.out.print("\n			Resultado de la suma" +this.resultadoAcc);
				System.out.print("\n			Asignando flag del signo del resultado");
				
				if(this.resultadoAcc>=0) {
					this.flag.setFlagSigno('+');
				}else{
					this.flag.setFlagSigno('-');
				}
				this.setTextoEntradaDescodificada("<html>"
			            + "Restar los contenidos de la celda ["+dato+"] "
			            + "al del acumulador"
			            + "</html>");
				System.out.print("Signo ["+ this.flag.getFlagSigno()+"]");
				break;

				
			case 8:
				System.out.println("		Instrucción ocho: JMP y el dato es: "+dato);
				
				System.out.print("\n			Guardando ubicación actual("+contador.getPosicion()+") en la posición 99");
				if (contador.getPosicion()<=9) {
					memo.setRam(99, "80"+contador.getPosicion());
				}else {
					memo.setRam(99, "8"+contador.getPosicion());
				}
				this.setTextoEntradaDescodificada("<html>"
			            + "Mover Contador a la celda ["+dato+"] y guardar "
			            + "la ubicación original en la celda [99]"
			            + "</html>");
				System.out.print("\n			El programa saltará a la posición "+dato);
				contador.setPosicion(dato);
				
				break;
				
			case 9:
				System.out.println("		Instrucción nueve: HRS y el dato es: "+dato);
				this.setTextoEntradaDescodificada(" ");
				System.out.print("\n			Guardando salida");
				System.out.print("\n			Estableciendo HLT");
				flag.setFlagHLT(1);
				contador.setPosicion(0);
				this.setTextoEntradaDescodificada("<html>"
			            + "Avanzando posicion de salida "
			            + "llevar contador a la celda [0]"
			            + " deteniendo máquina, pulse reset para empezar de nuevo"
			            + "</html>");
				
				break;
		}
	}

}