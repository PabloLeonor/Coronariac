package control;
//El sistema de flags, permite saber el estado interno sin tener que andar moviendo muchos datos de aquí para allá


public class Flags {
	private int flagHLT;
	private char flagSigno;
	
	public Flags() {
		this.flagHLT=0;
		this.flagSigno='+';
	}
	
	
	public int getFlagHLT() {
		return flagHLT;
	}

	public char getFlagSigno() {
		return flagSigno;
	}

	public void setFlagHLT(int flagHLT) {
		this.flagHLT = flagHLT;
	}
	
	public void setFlagSigno(char flagSigno) {
		this.flagSigno = flagSigno;
	}

}
