package control;
//El sistema de flags, permite saber el estado interno sin tener que andar moviendo muchos datos de aquí para allá


public class Flags {
	private int flagHLT;
	
	public Flags() {
		this.flagHLT=0;
	}

	public int getFlagHLT() {
		return flagHLT;
	}

	public void setFlagHLT(int flagHLT) {
		this.flagHLT = flagHLT;
	}

}
