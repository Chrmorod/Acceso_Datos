package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GestionDatos {

	public GestionDatos() {

	}

	//TODO: Implementa una función para abrir ficheros
	private BufferedReader abrirFichero(String fichero) throws FileNotFoundException {
			FileReader fw = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fw);
			return br;
	}

	//TODO: Implementa una función para cerrar ficheros
	private void cerrarFichero(BufferedReader fichero) throws IOException{
			fichero.close();
	}
	public boolean compararContenido (String fichero1, String fichero2) throws IOException{
		//Abrimos Ficheros
		BufferedReader br1 = abrirFichero(fichero1);
		BufferedReader br2 = abrirFichero(fichero2);
		String line1="",line2 = "";
			do {
				//Devolverá true siempre y cuando la linea que lea en el fichero1 sea igual que en el fichero2.
				line1=br1.readLine();
				line2=br2.readLine();
				//Condiciones
				/*
				 * while((line1=br1.readLine())!=null){
						if(!line1.equals(line2=br2.readLine())){
							return false;
						} 
					}
					
				cerrarFichero(br1);
				cerrarFichero(br2);
				return true;
				
				 */
				if (line1!=null && line2==null) //fichero1 tiene contenido vs fichero 2 sin contenido será false
					return false;

				if (line2!=null && line1==null) //fichero2 tiene contenido vs fichero 1 sin contenido será false
					return false;

				if(!line1.equals(line2)) //fichero1 si no es igual a fichero2 devuelve false
					return false;

				if (line1==null && line2==null || line1.equals(line2)) //fichero1 tiene contenido o nada de contenido vs fichero 2 tiene contenido o nada de contenido será true
					return true;


			}
			while(true);

	}

	public int buscarPalabra (String fichero1, String palabra, boolean primera_aparicion) throws IOException{
			BufferedReader br1 = abrirFichero(fichero1);//Abre fichero
			//Iniciamos variables
			int numLinea=-1;//-1 no encuentra cualquier otro numero ha encontrado en esa linea.
			int contador = 0;//cuenta de la lineas del fichero
			String linea = "";//contenido de la linea
			do {

				if(linea.equals(palabra)) { //si la palabra es igual al contenido de la linea definido en el while asignar el recuento de linea a numLinea

					numLinea=contador;

					if(primera_aparicion) {
						numLinea=contador;
						break;//rompe el bucle para recoger con la primera linea que te encuentres(primer if) y poder recuperarlo.
					}

				}

				contador++;	//aumentamos contador

			}while((linea = br1.readLine())!=null);//hasta fin de fichero

			return numLinea;	//devuelve numLinea
	}
}
