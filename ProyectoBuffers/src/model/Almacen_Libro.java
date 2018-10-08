package model;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Almacen_Libro {
	private int respuesta_libro=-1;
		
	//Metodo para guardar un libro.
	public int guardar_Libro(Libro li) {
		//Nombre del fichero.
		String f=li.getId()+".epub";
		//Inicializamos Objeto ObjectOutputStream.
		ObjectOutputStream out=null;
		
		try {
			//Almacenamos en el Stream.
			out = new ObjectOutputStream(new FileOutputStream("C:\\Libros\\"+f));
			//Escribimos nuestro Objeto en el fichero indicando la ruta.
			out.writeObject(li);
			respuesta_libro=1;
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.err);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}finally {
			//Cerramos el Stream.
			iCerrar(out);
		}
		return respuesta_libro;
	}
	
	//Metodo para recuperar un libro.
	public Libro recuperar_libro(String identificador) {
		//Inicilizamos el Objeto Libro y ObjectInputStream.
		Libro li = null;
		ObjectInputStream in=null;
		try {
			//Recuperamos el fichero con el identificador indicado.
			File fichero = new File ("C:\\Libros\\"+identificador+".epub");
			//Almacenamos en el Stream.
			in = new ObjectInputStream(new FileInputStream(fichero));
			//Leemos el Stream.
			li = (Libro) in.readObject();
		}catch (ClassNotFoundException e) {
			e.printStackTrace(System.err);
		}catch (IOException e) {
			e.printStackTrace(System.err);
		}finally {
			//Cerramos el Stream.
			iCerrar(in);
		}
		return li;
	}
	
	//Metodo para recuperar todos los libros
	public ArrayList<Libro> recuperar_todos(){
		//Inicializamos los Objetos Libro y ObjectInputStream.
		Libro libro=null;
		ObjectInputStream in=null;
		//Creamos un ArrayList.
		ArrayList<Libro> libros = new ArrayList<Libro>();
		//Recuperamos el directorio como Objeto Fichero.
		File directory = new File("C:\\Libros\\");
		//Metodo para filtrar por ficheros .epub en el directorio Libros.
		File[] ficheros=directory.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".epub");
			}
		});
		/*Recorremos el contenido del directorio de donde almacenamos 
		  cada uno de los nombres de fichero en el Stream.*/
		for (File fichero : ficheros) {
			try {
				in = new ObjectInputStream(new FileInputStream(fichero));
				
			} catch (FileNotFoundException e) {
				e.printStackTrace(System.err);
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
			try {
				//Leemos el contenido y lo guardamos como Objeto Libro.
				libro = (Libro) in.readObject();
				//Lo añadimos al array de Objeto Libro.
				libros.add(libro);
			} catch (ClassNotFoundException e) {
				e.printStackTrace(System.err);
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
		}
		return libros;
	}
	
	//Metodo para intenta cerrar el fichero.
	public void iCerrar(Closeable cerrar) {
		try {
			if(cerrar != null) {
				cerrar.close();
			}
		}catch(IOException e) {
			e.printStackTrace(System.err);
		}
	}
}
