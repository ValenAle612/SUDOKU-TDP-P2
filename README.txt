-Convenciones-
El archivo es válido cuando:
			    > El archivo contiene una matriz de 9x9 con la que se inicialiará el juego.
			    > La matriz solo puede contener números del 1 al 9, si contiene otro caracter se considera como archivo inválido.
	         	    > Cada número de la matriz del archivo está separado por un espacio uno del otro, en caso de no estarlo el archivo se considera como archivo inválido.
	     		    > Entonces cada línea tendrá 17 caracteres entre los espacios que separan cada número (serán 8 en total) y los 9 números de la linea que posteriormente pasaran a formar cada fila.
			    > Si existe un espacio al final y/o principio de la linea, es decir una linea con mas de 17 caracteres, el archivo se considea inválido.
			    > Si la matriz tiene un tamaño menor al estipulado ( 9x9 ) se considera como inválida.

		|¬ En caso de que el archivo resulte inválido se abrirá una ventana indicando que el archivo es inválido.
		|¬ En caso que el programa no detecte el archivo de texto, es decir que tenga un nombre con una ruta incorrecta, que no se encuentre como recurso dentro de la IDE, que no se encuentre en la carpeta "archivos" perteneciente al estado interno del ejecutable .jar ( abriéndolo como archivo .rar se podrá visualizar ) o en la carpeta "archivos" contenida en la carpeta "src" adjunta, ( en esta se encuentran carpetas con el código fuente, los sonidos, imagenes y archivo/s ) se abrirá una ventana indicando que el archivo es inválido y se generará un mensaje por consola en la IDE.

Recursos audiovisuales:
		|¬ Los recursos audiovisuales, es decir :
							 > Imagenes tales como -> números del reloj, figuras de las celdas del tablero y decoración de los frames,
							 > Sonidos tales como -> inicio del juego (sonidoInicio.wav), archivo inválido y solución inválida (pm.wav), y reiniciar tablero (waka waka.wav),
		   se encuentran dentro de la carpeta src en las carpetas "reloj", "img" y "sonidos" respectivamente.

NOTA |¬ El archivo utilizado en la inicialización del juego del archivo ejecutable "SUDOKU.jar" se encuentra dentro de la carpeta "archivos" contenida en la carpeta src.


