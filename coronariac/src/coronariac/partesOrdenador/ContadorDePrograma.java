package coronariac.partesOrdenador;

public class ContadorDePrograma {
	
	private int posicion;
	
	public  ContadorDePrograma() {
		this.posicion=000;
		
	}
	
	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	
	public void annadirStep() {
		if(this.posicion<99) {
			this.posicion++;
		}else {
			this.posicion=0;
		}
	}

}
