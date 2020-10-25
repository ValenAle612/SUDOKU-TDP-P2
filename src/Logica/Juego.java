package Logica;
import java.io.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import Logica.Celda;
public class Juego {
	private Celda [][] tablero;
	private boolean archivoValido;
	
	/**
	 * Crea un tablero de sudoku
	 * @param sudoku
	 */
	public Juego(String sudoku) {
		tablero=new Celda[9][9];
		this.archivoValido = true;
		this.inicializar(sudoku);//sudoku
	}
	
	/**
	 * Inicializa el tablero con la matriz en el archivo si y solo si el formato del archivo es valido  
	 * @param sudoku ruta del archivo
	 */
	public void inicializar(String sudoku) {
		
			int Matriz[][] = crearMatriz(sudoku);
			
			
			this.archivoValido = this.archivoValido && this.validarMatriz(Matriz);
			
			for(int i=0; i < 9 ; i++) {//INICIALIZO LA MATRIZ
				for(int j=0; j < 9 ; j++) {
					tablero[i][j] = new Celda();
					tablero[i][j].setFila(i);
					tablero[i][j].setCol(j);
				}
			}
			
			if(archivoValido) {
				
				boolean MatrizValida = archivoValido;
			
				for (int i =0; i<9  && MatrizValida; i++) {
					for (int j =0; j<9; j++) {
					
						Random rand = new Random();
						int value = rand.nextInt(2);

						if (value == 0){

							int valor = rand.nextInt(9);
						
							tablero[i][valor].setValor(Matriz[i][valor]-1);
							tablero[i][valor].setModificable(false);
						}//end if
					}//end for2
				}//end for1
			}//end if archivoValido
			
	}
	
	/**
	 * Se crea una matriz numerica de 9x9 con una solucion valida de Sudoku
	 * @param path ruta del archivo que contiene la matriz
	 * @return Matriz numerica de 9x9 si la matriz de archivo es valida; Matriz nula en caso contrario
	 */
	private int[][] crearMatriz(String path){
		
		int iMatriz=0, jMatriz=0, pos=0;
		
		int [][] Matriz = new int[9][9];
		
		boolean esValido = true;
		int contadorFilas = 0;//cuento la cantidad de lineas que hay, si hay mas (o menos) de las estipuladas ( 9 ) , entonces el archivo no es valido
		boolean charValido = true;
		
		
		InputStream in = getClass().getResourceAsStream(path); 
		if( in != null ){
			
					Scanner s = new Scanner(in);
					String linea;
		
					while( s.hasNextLine() && iMatriz<9 && esValido) {
						jMatriz=0;
						pos=0;
						linea = s.nextLine();
						esValido = esValido && (linea.length() == 17);//chequeo que la linea cumpla el largo adecuado, si tiene espacios deberia de terner un largo de 17
																	  //incluyendo los numeros para inicializar la matriz
						while( pos < linea.length() && esValido && charValido) {
						
							if(linea.charAt(pos) != ' ') {//controlo que los numeros esten separados por espacios y controlo que los caracteres que tengo en la matriz
								if( esNumero1a9(linea.charAt(pos)) && pos+1<linea.length()) {//del archivo sean numeros del 1 al 9 y no otros símbolos
									
									charValido = charValido && ( linea.charAt(pos+1) == ' ' );
									
								}else { 
									if(!esNumero1a9(linea.charAt(pos)))
										 charValido = false;
								}
							}
							
							if( linea.charAt(pos) != ' ' && jMatriz<9 && charValido) {
								Matriz[iMatriz][jMatriz] = Integer.parseInt(""+linea.charAt(pos));
								jMatriz++;
							}
							System.out.print(linea.charAt(pos));
							pos++;
				
						}
						
						esValido = esValido && charValido;

						System.out.println("");

						iMatriz++;
						contadorFilas++;
			
					}	

					
					if(	(contadorFilas < 9 || contadorFilas > 9 ) || !esValido ) {
						archivoValido = false;
						Matriz = null;
					}
		}else {		
			this.archivoValido = false;
			System.out.println("ERROR AL LEER ARCHIVO, RUTA INVÁLIDA");
		}
		return Matriz;
	}	
	
	/**
	 * Se valida que: |¬ la matriz no tenga numeros repetidos en cada cuadrante de 3x3
	 * 				  |¬ la matriz no tenga numeros repetidos en cadaa fila
	 * 				  |¬ la matriz no tenga numeros repetidos en cada columna	
	 * @param Matriz
	 * @return verdadero si la matriz cumple el formato, falso en caso contrario
	 */
	private boolean validarMatriz(int [][]Matriz) {
		boolean vF=true, vC=true, validaCuadrante=true;
		
		validaCuadrante = validarCuadrante(Matriz);
		
		if(validaCuadrante)//si los 9 cuadrantes de 3x3 no tienen numeros repetidos entonces paso a validar las filas
			for(int i=0; i < Matriz.length && vF; i++) { //validarFilas
				vF = vF && validarFilas(Matriz[i]);
			}

		if(validaCuadrante && vF)//si los cuadrantes no tienen numeros repetidos y las filas tampoco tienen numeros repetidos entonces
			vC = validarCol(Matriz); //valido las columnas
		
		return vF && vC && validaCuadrante;
				
	}
	
	/**
	 * Valida que cada cuadrante de 3x3 perteneciente a la matriz m no tenga numeros repetidos
	 * @param m 
	 * @return true si cada cuadrante de 3x3 no tiene numeros repetidos, false en caso contrario
	 */
	private boolean validarCuadrante(int [][] m) {
		
		boolean verif = true;

		int f = 0;
		
		int i=0 , j, filaSig = 0, cAct = 0;
		
		while( i < 9 && verif) {
			f = ( i / 3 ) * 3;
			filaSig = f;
			cAct = f;
			j = f = 0;
			
			while(j < 9 && verif ) {
				
				for ( int k = j+1; ( k < (f + 3) || i < (cAct + 3) ) && verif; k++) {
					if(k == (f + 3) && (i + 1) < (cAct + 3) ) {
						k = f;
						i++;
					}else 
						if(k == (f + 3) && ( i + 1 ) == (cAct + 3))
							i++;
					
					if( i < (cAct + 3) && ( ( filaSig != i ) || ( j != k ) ) ) {			
						
							verif = ( m[filaSig][j] != m[i][k] );

					}
					

				}//end for k
				
				j++;
				
				if(filaSig == (cAct + 2 ) && j == (f + 3)) {// si ya verifique el cuadrante
					f = ( j / 3 ) * 3;
					filaSig = cAct;
				}
				
				if( j == (f + 3) ) {
					j = f; filaSig++;
				}
				i = filaSig ;
			}//end while j
			i = ( cAct + 3 );
		}//end while externo
		
		return verif;
	
	}
	
	
	/**
	 * valida que la matriz aux no tenga numeros repetidos
	 * @param aux
	 * @return true si no tiene numeros repetidos, false en caso contrario
	 */
	private boolean validarAux(int aux[]) {
		boolean valida = true;
		
		for(int i=0; i<aux.length && valida;i++) {
			for(int j=i+1; j<aux.length && valida;j++) {
				
				valida = ( aux[i] != aux[j]);
			
			}
		}

		return valida;
	}
	
	/**
	 * Valida que las columnas de la matriz Matriz no tengan numeros repetidos 
	 * @param Matriz
	 * @return true si no tiene numeros repetidos, false en caso contrario
	 */
	private boolean validarCol(int Matriz[][] ) {
		boolean vC=true;

		for(int j=0; j < Matriz.length && vC; j++) {
			for(int i=0; i < Matriz.length && vC; i++) {
				
				for(int k=i+1; k < Matriz.length && vC; k++) {

						vC = ( Matriz[i][j] != Matriz[k][j] );
						
				}// end for k 
			}//end for i
		}//end for j
		
		return vC;
	}
	
	/**
	 * Valida que las filas de la matriz Matriz no contengan numeros repetidos
	 * @param Matriz
	 * @return true si las filas no tienen numeros repetidos, false en caso contrario
	 */
	private boolean validarFilas(int Matriz[]) {
		boolean vF=true;

		for(int i=0; i<Matriz.length && vF; i++) {
			for(int j=i+1; j < Matriz.length && vF; j++) {

				vF = vF && ( Matriz[i] != Matriz[j] );
			}
		}
		
		return vF;
		
	}
	
	/**
	 * Verifica si las celdas del tablero son validas, es decir si
	 * cada celda de la matriz cumple las siguientes condiciones : |¬ no se repite su valor en el cuadrante de 3x3 al que pertenece
	 * 															   |¬ no se repite su valor en la columna a la que pertenece
	 * 															   |¬ no se repite su valor en la fila a la que pertenece
	 */
	public void verificar() {
		this.verificarCuadrantesTablero();
		this.verificarColumnasTablero();
		this.verificarFilasTablero();
	}
	
	
	/**
	 * Verifica que cada celda perteneciente a cada cuadrante del tablero
	 * no se repita en su mismo cuadrante, si hay celdas repetidas las pone en estado de error
	 */
	private void verificarCuadrantesTablero() {
		boolean verif = true;

		int f = 0;
		
		int i=0 , j, filaSig = 0, cAct = 0;
		while( i < 9 ) {
			f = ( i / 3 ) * 3;//calculo cuadrante act ej. si i es 2 f entonces es 0, si i es 5 entonces f es 3
			filaSig = f;// fila de la celda que estoy comparando
			cAct = f;// cuadrante actual
			j = f = 0;
			
			while(j < 9) {// mientras no haya recorrido todas las columnas de la seccion 
				
				for ( int k = j+1; k < (f + 3) || i < (cAct + 3); k++) {//comparo la celda tablero[filaSig][j] con el resto de celdas del tablero
					if(k == (f + 3) && (i + 1) < (cAct + 3) ) {
						k = f;
						i++;
					}else 
						if(k == (f + 3) && ( i + 1 ) == (cAct + 3))
							i++;
					
					if( i < (cAct + 3) && ( ( filaSig != i ) || ( j != k ) ) )
						if(tablero[filaSig][j].getValor() != null && tablero[i][k].getValor()!=null ) {
							verif = !( tablero[filaSig][j].equals(tablero[i][k])  );
							if(!verif) {
								tablero[filaSig][j].setError(true);
								tablero[i][k].setError(true);
							}
						}
				}//end for k
				
				j++;
				
				if(filaSig == (cAct + 2 ) && j == (f + 3)) {// si ya verifique el cuadrante
					f = ( j / 3 ) * 3;
					filaSig = cAct;
				}
				
				if( j == (f + 3) ) {
					j = f; filaSig++;
				}
				i = filaSig ;
			}//end while j
			i = ( cAct + 3 );
		}//end while externo
		
		
	}
	
	/**
	 * Verifica si las filas en el tablero contienen celdas no repetidas en la misma fila a la que pertenecen,
	 * si hay celdas repetidas se settean en estado de error
	 */
	private void verificarFilasTablero() {
		boolean verif = true;;

		for(int i = 0; i < tablero.length; i++) {
			for(int j = 0; j < tablero.length; j++) {
				if( tablero[i][j].getValor() != null ) {
					
					for(int k = j+1; k < tablero.length; k++) {
						
						if(tablero[i][k].getValor() != null)
							verif = (  !tablero[i][j].equals( tablero[i][k] )  );
						
						if(tablero[i][k].getValor() != null && !verif) {
							tablero[i][j].setError(true);
							tablero[i][k].setError(true);//la celda esta en estado de error pq esta repetida
						}
						
					}//end for k
				
				}//endf
			}//end for j
		}//end for i
		
	}
	
	/**
	 * Verifica si las columnas contienen celdas no repetidas en la misma columna a la que pertenecen,
	 * si se repiten se settean en estado de error
	 */
	private void verificarColumnasTablero() {
		boolean verif = true;

		for(int j = 0; j < tablero.length; j++) {
			for(int i = 0; i < tablero.length; i++) {
				if( tablero[i][j].getValor() != null ) {
					for(int k = i+1; k<tablero.length; k++) {
						if( tablero[k][j].getValor() != null )
							verif = (  !tablero[i][j].equals( tablero[k][j] )  );
						if( tablero[k][j].getValor() != null &&  !verif) {
							tablero[i][j].setError(true);
							tablero[k][j].setError(true);//la celda esta en estado de error pq esta repetida
						}
						
					}//end for k
					if(!verif) {
						tablero[i][j].setError(true);//la celda esta en estado de error pq esta repetida
					}
				}
			}//end for i
		}//end for j
		
	}
	
	/**
	 * Archivo valido es true si el archivo que inicializa el juego es valido, false en caso contrario
	 * @return archivoValido
	 */
	public boolean archivoValido() {
		return this.archivoValido;
	}
	
	/**
	 * Valida que el caracter c sea un numero del 1 al 9
	 * @param c caracter
	 * @return true si c es un numero del 1 al 9, false en caso contrario 
	 */
	public boolean esNumero1a9(char c) {
		boolean esN = true;

		switch(c){
			case '1' : case '2' : case '3' : case '4' : case '5' : case '6' : case '7' : case '8' : case '9' :{
				esN = true;
				break;
			}default : {
				esN = false;
				break;
			}
		}

		return esN;
	}
	
	
	/**
	 * actualiza la imagen de la celda c
	 * @param c Celda
	 */
	public void accionar(Celda c) {
		c.actualizar();
	}
	
	/**
	 * retorna la cantidad de filas de la matriz del juego
	 * @return cantidad de filas
	 */
	public int getCantFilas() {
		return 9;
	}
	
	/**
	 * retorna la cantidad de columnas de la matriz del juego
	 * @return cantidad de columnas
	 */
	public int getCantCol() {
		return 9;
	}
	
	/**
	 * retorna la celda en la fila i, columna j en el tablero
	 * @param i fila
	 * @param j columna
	 * @return Celda 
	 */
	public Celda getCelda(int i, int j) {
		return this.tablero[i][j];
	}
}
