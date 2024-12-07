package control;

import coronariac.partesOrdenador.ContadorDePrograma;
import coronariac.partesOrdenador.Memoria;

public class Control {
	
	private int MAR;//memory adress register
	private int primerOperandoAcc;
	private int segundoOperandoAcc;
	private int resultadoAcc;
	private String instruccion;//el contenido del MAR
	private Flags flag; 
	private Memoria memo;
	private ContadorDePrograma contador;
	
	public Control(Flags flag, Memoria memo,ContadorDePrograma contador) {
		this.primerOperandoAcc=0;
		this.segundoOperandoAcc=0;
		this.resultadoAcc=0;
		this.MAR=0;
		this.instruccion="0";
		this.flag = flag;
		this.memo = memo;
		this.contador = contador;
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
				
				break;
				
			case 4:
				System.out.println("		Instrucción cuatro: SFT y el dato es: "+dato);
				break;
				
			case 5:
				System.out.println("		Instrucción cinco: OUT y el dato es: "+dato);
				break;
				
			case 6:
				System.out.println("		Instrucción seis: STO y el dato es: "+dato);
				System.out.print("			Extrayendo el resultado del acumulador y escribiéndolo en la posición "+dato);
				if(this.resultadoAcc<10) {
					memo.setRam(dato, "00"+Integer.toString(this.resultadoAcc));
				}else if(this.resultadoAcc<100) {
					memo.setRam(dato, "0"+Integer.toString(this.resultadoAcc));
				}if(this.resultadoAcc>=100) {
					memo.setRam(dato, Integer.toString(this.resultadoAcc));
				}
				//memo.setRam(dato, Integer.toString(this.resultadoAcc));
				
				break;
				
			case 7:
				System.out.println("		Instrucción siete: SUB y el dato es: "+dato);
				break;
				
			case 8:
				System.out.println("		Instrucción ocho: JMP y el dato es: "+dato);
				break;
				
			case 9:
				System.out.println("		Instrucción nueve: HRS y el dato es: "+dato);
				
				break;
		}
	}

}
