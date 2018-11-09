package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Iterator;

import model.*;
import view.*;

public class GestionEventos implements Serializable {

	private GestionDatos model;
	private Almacen_Libro model2;
	private LaunchView view;
	private ActionListener actionListener_comparar, actionListener_buscar, actionListener_guardar,actionListener_recuperar,actionListener_recuperar_todos;

	public GestionEventos(GestionDatos model, LaunchView view, Almacen_Libro model2) {
		this.model = model;
		this.view = view;
		this.model2 = model2;
	}

	public void contol() {
		actionListener_comparar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_compararContenido();
			}
		};
		view.getComparar().addActionListener(actionListener_comparar);

		actionListener_buscar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_buscarPalabra();
			}
		};
		view.getBuscar().addActionListener(actionListener_buscar);
		
		actionListener_guardar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_guardarLibro();
			}
		};
		view.getSaveBook().addActionListener(actionListener_guardar);
		
		actionListener_recuperar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_recuperarLibro();
			}
		};
		view.getWithdrawBook().addActionListener(actionListener_recuperar);
		
		actionListener_recuperar_todos = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					call_recuperar_todos();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NumberFormatException e) {
					view.showError("Campo año no introducido");
				}
			}
		};
		view.getAllBooks().addActionListener(actionListener_recuperar_todos);
	}

	private int call_compararContenido(){
		//Recuperamos de los TextField
		String fichero1 = view.getFichero1().getText(),fichero2 = view.getFichero2().getText();
		
		try {
			// TODO: Llamar a la función compararContenido de GestionDatos
			//Llamamos a la función para saber si de contenido son iguales
		boolean iguales = model.compararContenido(fichero1, fichero2);
		System.out.println(iguales);
		if (iguales) 
			view.getTextArea().setText("Los ficheros introducidos son iguales.");
		else 
			view.getTextArea().setText("Los ficheros introducidos no son iguales.");
		//Fichero sin introducir
		}catch(FileNotFoundException e) { 
			view.showError("Error en la función --call_compararContenido-- campo ficheros vacios o mal introducidos MAS INFO: "+e);
		}catch(IOException e) {
			// TODO: Gestionar excepciones
			view.showError("Error en la función --call_compararContenido-- en la clase GestionEventos MAS INFO: "+e);
		}
		
		return 0;
	}
	// Buscar palabra
	private int call_buscarPalabra() {
		//Inicializamos variables
		int buscaLine1 = -1,buscaLine2 = -1;;
		String mensaje1="",mensaje2="";
		//Recuperamos de los TextField y CheckBox
		String fichero1 = view.getFichero1().getText(),fichero2 = view.getFichero2().getText(),palabra  = view.getPalabra().getText(); 
		//recuperamos true CheckBox marcado; false CheckBox no marcado
		boolean palabraPrimera = view.getPrimera().isSelected();
					
			// TODO: Llamar a la función buscarPalabra de GestionDatos
			if (palabra.length()==0) {
				//Aviso palabra no introducida
				view.showError("AVISO! Palabra no  introducida.");
				
			}else {
						try {
							//LLamamos al metodo buscarPalabra desde el TextField fichero1
							buscaLine1 = model.buscarPalabra(fichero1, palabra, palabraPrimera);
							
							mensaje1="La palabra introducida: "+palabra+" ha sido encontrada en el Fichero1 en la linea "+buscaLine1;
							
						} catch (IOException e) {
							
							//Aviso Fichero1 no introducido
							view.showError("AVISO! Fichero 1 no ha sido introducido.");
						}
					
						try {
							//LLamamos al metodo buscarPalabra desde el TextField fichero2
							buscaLine2 =model.buscarPalabra(fichero2, palabra, palabraPrimera);
							
							mensaje2="La palabra introducida: "+palabra+" ha sido encontrada en el Fichero2 en la linea "+buscaLine2;
							
						} catch (IOException e) {
							//Aviso Fichero2 no introducido
							view.showError("AVISO! Fichero 2 no ha sido introducido.");
						}
						//Muestra las posibilidades de mensajes segun la busqueda que aparecen en el TextArea.
						
						if (buscaLine1!=-1&&buscaLine2!=-1) { 
							
							view.getTextArea().setText(mensaje1+"\n"+mensaje2);
							
						}else if(buscaLine1!=-1&&buscaLine2==-1){
							
							view.getTextArea().setText(mensaje1+" pero no se encuentra en el fichero2.");
							
						}else if(buscaLine1==-1&&buscaLine2!=-1) {
							
							view.getTextArea().setText(mensaje2+" pero no se encuentra en el fichero1.");
							
						}else {
							
							view.getTextArea().setText("La palabra introducida: "+palabra+" no ha sido encontrada en ninguno de los dos ficheros.");
						}
		  }
					
		return 0;
		
	}
	
	private int call_guardarLibro() {
		//Datos de entrada
		UUID uniqueKey = UUID.randomUUID();
		String titulo = view.getTitleTextField().getText(),autor = view.getAuthorTextField().getText(),editor = view.getEditorTextField().getText();;
		int anyo = Integer.parseInt(view.getYearTextField().getText()),pages = Integer.parseInt(view.getPagesTextField().getText());
		
		//Objeto LIBRO
		Libro libro = new Libro(""+uniqueKey,titulo,autor,anyo,editor,pages);
		
		//Guardamos el libro y mostramos la info
		try{
			
			model2.guardar_Libro(libro);
			view.getTextArea().setText("Guardado Libro:\n Titulo: "+libro.getTitle()+"\nAutor: "+libro.getAuthor()+"\nAño: "+libro.getYear()+"\nEditor: "+libro.getEditor()+"\nNº Paginas: "+libro.getAuthor()+"\nIdentificador: "+libro.getId()+"\n Por favor, si desea consultar este libro guardese el identificador");
		
		}catch(Exception e) {
			
			view.getTextArea().setText("Alguno de los datos del libro ha sido mal introducido" + e);
		}
		
		return 0;
		
	}
	private int call_recuperarLibro() {
		//Recuperamos referencia.
		String id = view.getIdTextField().getText();
		
		//LLamamos a la funcion Libro y recuperamos el OBJETO Libro.
		Libro libro = model2.recuperar_libro(id);
		
		//Recuperamos los datos en variables del tipo que correspondan.
		String titulo = libro.getTitle(), autor = libro.getAuthor(), editor = libro.getEditor();
		int anyo = libro.getYear(), paginas = libro.getPages();
		
		//Mostramos en pantalla el resultado recuperado.
		view.getTextArea().setText("Libro consultado con Identificador: "+id+" \nTitulo: "+titulo+"\nAutor: "+autor+"\nAño: "+anyo+"\nEditor: "+editor+"\nNº Paginas: "+paginas);

		return 0;
	}
	
	private int call_recuperar_todos() throws IOException {
		
		//Creamos el arrayList de todos los libros OBJETO.
		ArrayList<Libro> libros = new ArrayList<Libro>();
		
		//Creamos variable auxiliar para mostrar todos los libros.
		String Libros="";
		int cont = 0;
		//LLamamos a la funcion recuperar todos y los almacenamos en el Array.
		libros =model2.recuperar_todos();
		
		//Creamos un Iterator. Referencia Video Iterator.
		Iterator it = libros.iterator();
		
		//Mientras haya contenido almacenalo en la variable auxiliar.
		while(it.hasNext()) {
			String anyo = view.getYearTextField().getText();
			
			int anyonumerico = Integer.parseInt(anyo);
			
			Libro libro = (Libro) it.next();
			
			if(libro.getYear()==anyonumerico) {
				Libros = Libros +"_____Libro Encontrado____\nIdentificador: "+libro.getId()+"\nTitulo: "+libro.getTitle()+"\nAutor: "+libro.getAuthor()+"\nAño: "+libro.getYear()+"\nEditor: "+libro.getEditor()+"\nNº Paginas: "+libro.getPages()+"\n";
				cont = cont+1;
			}else {
				view.showError("Libros por años no encontrados");
				break;
			}
			
		}
		
		//Muestrame todo el contenido.
		view.getTextArea().setText(Libros+"\n cantidad de libros encontrados: "+cont);

		return 0;
	}

}
