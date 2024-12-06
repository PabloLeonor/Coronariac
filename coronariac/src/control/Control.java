package control;


public class Control {
	
	private int MAR;//memory adress register
	private String instruccion;//el contenido del MAR
	private Flags flag; 
	
	public Control(Flags flag) {
		this.MAR=0;
		this.instruccion="0";
		this.flag = flag;
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
		String rawInstruccion=this.instruccion;
		int instruccion = Integer.valueOf(rawInstruccion.substring(0));
		int dato = Integer.valueOf(rawInstruccion.substring(1));
		
		switch(instruccion) {
			case 0:
				System.out.println("Instrucción cero: INP y el dato es: "+dato);
				break;
				
			case 1:
				System.out.println("Instrucción una: CLA y el dato es: "+dato);
				break;
				
			case 2:
				System.out.println("Instrucción dos: ADD y el dato es: "+dato);
				break;
				
			case 3:
				System.out.println("Instrucción tres: TAC y el dato es: "+dato);
				break;
				
			case 4:
				System.out.println("Instrucción cuatro: SFT y el dato es: "+dato);
				break;
				
			case 5:
				System.out.println("Instrucción cinco: OUT y el dato es: "+dato);
				break;
				
			case 6:
				System.out.println("Instrucción seis: STO y el dato es: "+dato);
				break;
				
			case 7:
				System.out.println("Instrucción siete: SUB y el dato es: "+dato);
				break;
				
			case 8:
				System.out.println("Instrucción ocho: JMP y el dato es: "+dato);
				break;
				
			case 9:
				System.out.println("Instrucción nueve: HRS y el dato es: "+dato);
				//en primer lugar se resetea el contador
				
				//en segundo lugar se pone el flag HLT a uno
				flag.setFlagHLT(1);
				
				
				break;
		}
	}

}
