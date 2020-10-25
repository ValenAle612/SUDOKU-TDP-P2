package Logica;

public class Celda {
	private Integer valor;
	private EntidadGrafica entidadG;
	private int fila, columna;
	private boolean modificable ;// para saber si puedo modificarla en el juego, es decir si es una celda con la que se inicializo el juego 
	private boolean error;
	
	/**
	 * Crea una celda
	 */
	public Celda() {
		this.error = false;// estado de la selda
		this.modificable = true;
		this.valor = null;
		this.entidadG = new EntidadGrafica();
		this.columna = this.fila = 0;
	}
	
	/**
	 * Actualiza la imagen de la celda
	 */
	public void actualizar() {
		if(this.valor != null && this.valor + 1 < this.getCantElem()) {
			this.valor++;
		}else {
			this.valor = 0;
		}
		entidadG.actualizar(this.valor);
	}
	
	/**
	 * Retorna el estado de la celda
	 * @return true si esta en estado de error, false en caso contrario
	 */
	public boolean getError() {
		return this.error;
	}
	
	/**
	 * Settea el estado de la celda
	 * @param error estado de la celda 
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * Retorna la cantidad de imagenes que puede tener 
	 * una celda
	 * @return cantidad de imagenes
	 */
	public int getCantElem() {
		return this.entidadG.getNumeros().length;
	}
	
	/**
	 * @return Retorna el valor de la celda
	 */
	public Integer getValor() {
		return this.valor;
	}
	
	/**
	 * Settea el valor de la celda
	 * @param valor 
	 */
	public void setValor(Integer valor) {
		if(valor != null && valor < this.getCantElem() ) {
			this.valor = valor;
			this.entidadG.actualizar(this.valor);
		}else {
			this.valor = null;
		}
	} 
	
	/**
	 * Settea la cualidad de la ceda
	 * @param m true si la celda puede cambiar su magen, false si es una celda inicial
	 */
	public void setModificable(boolean m) {
		this.modificable = m;
	}
	
	/**
	 * @return fila a la que pertenece la celda
	 */
	public int getFila() {
		return this.fila;
	}
	
	/**
	 * @return columna a la que pertenece la celda
	 */
	public int getCol() {
		return this.columna;
	}
	
	/**
	 * Settea la fila con el valr f
	 * @param f
	 */
	public void setFila(int f) {
		this.fila = f;
	}
	
	/**
	 * Settea la columna con el valor c
	 * @param c
	 */
	public void setCol(int c) {
		this.columna = c;
	}
	
	/**
	 * @return la entidad gráfica de la celda
	 */
	public EntidadGrafica getEntidadG() {
		return this.entidadG;
	}
	
	/**
	 * Retorna la cualidad de la celda
	 * @return true si la celda puede ser accionada, false si es una celda inicial del juego
	 */
	public boolean modificable() {
		return this.modificable;
	}
	
	/**
	 * Compara la celda c con la actual
	 * @param c
	 * @return true si son iguales, false en caso contrario
	 */
	public boolean equals(Celda c) {
		return this.entidadG.equals(c.getEntidadG());
	}
	
}
