package gestionficherosapp;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import gestionficheros.FormatoVistas;
import gestionficheros.GestionFicheros;
import gestionficheros.GestionFicherosException;
import gestionficheros.TipoOrden;

public class GestionFicherosImpl implements GestionFicheros {
	private File carpetaDeTrabajo = null;
	private Object[][] contenido;
	private int filas = 0;
	private int columnas = 3;
	private FormatoVistas formatoVistas = FormatoVistas.NOMBRES;
	private TipoOrden ordenado = TipoOrden.DESORDENADO;

	public GestionFicherosImpl() {
		carpetaDeTrabajo = File.listRoots()[0];
		actualiza();
	}

	private void actualiza() {

		String[] ficheros = carpetaDeTrabajo.list(); // obtener los nombres
		// calcular el n�mero de filas necesario
		filas = ficheros.length / columnas;
		if (filas * columnas < ficheros.length) {
			filas++; // si hay resto necesitamos una fila m�s
		}

		// dimensionar la matriz contenido seg�n los resultados

		contenido = new String[filas][columnas];
		// Rellenar contenido con los nombres obtenidos
		for (int i = 0; i < columnas; i++) {
			for (int j = 0; j < filas; j++) {
				int ind = j * columnas + i;
				if (ind < ficheros.length) {
					contenido[j][i] = ficheros[ind];
				} else {
					contenido[j][i] = "";
				}
			}
		}
	}

	@Override
	public void arriba() {

		if (carpetaDeTrabajo.getParentFile() != null) {
			carpetaDeTrabajo = carpetaDeTrabajo.getParentFile();
			actualiza();
		}

	}

	@Override
	public void creaCarpeta(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo,arg0);
		//que no exista -> lanzar� una excepci�n
		if (file.exists()) {
			throw new GestionFicherosException("Directorio Existente.");
		}
		//que se pueda escribir -> lanzar� una excepci�n
		if (file.canWrite()==false) {
			throw new GestionFicherosException("No tienes permisos de Escritura.");
		}
		//crear la carpeta -> lanzar� una excepci�n
		file.mkdir();
		actualiza();
	}

	@Override
	public void creaFichero(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo,arg0);
		//que no exista -> lanzar� una excepci�n
		if (file.exists()) {
			throw new GestionFicherosException("Fichero Existente.");
		}
		//crear el fichero -> lanzar� una excepci�n
		try {
			file.createNewFile();
			actualiza();
		} catch (IOException e1) {
			throw new GestionFicherosException("No tienes permisos de Escritura.");
		}
	}

	@Override
	public void elimina(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo,arg0);
		//que no exista -> lanzar� una excepci�n
		if (!file.exists()) {
			throw new GestionFicherosException("Este Fichero no existe.");
		}
		//eliminar la carpeta\el fichero -> lanzar� una excepci�n
		file.delete();
		actualiza();
        //que se pueda escribir -> lanzar� una excepci�n
		if (!file.delete()){
			throw new GestionFicherosException("Este fichero/directorio no tiene permisos de escritura.");
		}
	}

	@Override
	public void entraA(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo, arg0);
		// se controla que el nombre corresponda a una carpeta existente
		if (!file.isDirectory()) {
			throw new GestionFicherosException("Error. Se ha encontrado "
					+ file.getAbsolutePath()
					+ " pero se esperaba un directorio");
		}
		// se controla que se tengan permisos para leer carpeta
		if (!file.canRead()) {
			throw new GestionFicherosException("Alerta. No se puede acceder a "
					+ file.getAbsolutePath() + ". No hay permiso");
		}
		// nueva asignaci�n de la carpeta de trabajo
		carpetaDeTrabajo = file;
		// se requiere actualizar contenido
		actualiza();

	}

	@Override
	public int getColumnas() {
		return columnas;
	}

	@Override
	public Object[][] getContenido() {
		return contenido;
	}

	@Override
	public String getDireccionCarpeta() {
		return carpetaDeTrabajo.getAbsolutePath();
	}

	@Override
	public String getEspacioDisponibleCarpetaTrabajo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEspacioTotalCarpetaTrabajo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getFilas() {
		return filas;
	}

	@Override
	public FormatoVistas getFormatoContenido() {
		return formatoVistas;
	}

	@Override
	public String getInformacion(String arg0) throws GestionFicherosException{
			
			StringBuilder strBuilder=new StringBuilder();
			
			File file = new File(carpetaDeTrabajo,arg0);
			//Controlar que existe. Si no, se lanzar� una excepci�n
			//
			if (!file.exists()) {
				throw new GestionFicherosException("Este Fichero/Directorio no existe.");
			}
	
			//Controlar que haya permisos de lectura. Si no, se lanzar� una excepci�n
			//
			if (file.canRead()==false) {
				throw new GestionFicherosException("No tienes permisos de Lectura.");
			}
			
			//T�tulo
			strBuilder.append("INFORMACI�N DEL SISTEMA");
			strBuilder.append("\n\n");
		
			//Nombre
			strBuilder.append("Nombre: ");
			strBuilder.append(arg0);
			strBuilder.append("\n");
		
			//Tipo: fichero o directorio	
			strBuilder.append("Tipo: ");
			if (!file.isDirectory()) {
				strBuilder.append("Fichero");
			}else {
				strBuilder.append("Directorio");
			}
			strBuilder.append("\n");
			//Ubicaci�n
			strBuilder.append("Ubicaci�n: ");
			strBuilder.append(file.getAbsolutePath());
			strBuilder.append("\n");
			//Fecha de �ltima modificaci�n
			strBuilder.append("Fecha ultima modificaci�n: ");
			SimpleDateFormat formato = new SimpleDateFormat("EEEE MMMM d HH:mm:ss z yyyy");
			strBuilder.append(formato.format(file.lastModified()));
			strBuilder.append("\n");
			//Si es un fichero oculto o no
			strBuilder.append("Oculto: ");
			if (!file.isHidden()) {
				strBuilder.append("No");
			}else {
				strBuilder.append("Si");
			}
			strBuilder.append("\n");
			//Si es un fichero: Tama�o en bytes
			if (!file.isDirectory()) {
				strBuilder.append("Tama�o: ");
				strBuilder.append(file.length()+" bytes");
				
			//Si es directorio: N�mero de elementos que contiene, 
			}else {
				try {
					strBuilder.append("Contenido: ");
					strBuilder.append(file.list().length+" entradas");
					strBuilder.append("\n");
			//Si es directorio: Espacio libre, espacio disponible, espacio total (bytes)
					DecimalFormat formatoNumero = new DecimalFormat( "#,###,###,##0.00" );
					strBuilder.append("Espacio libre: "+formatoNumero.format(file.getFreeSpace())+" bytes");
					strBuilder.append("\n");
					strBuilder.append("Espacio disponible: "+formatoNumero.format(file.getUsableSpace())+" bytes");
					strBuilder.append("\n");
					strBuilder.append("Espacio total: "+formatoNumero.format(file.getTotalSpace())+" bytes");
				}catch(NullPointerException e) {
					strBuilder.append("0 entradas"
							+ "\nEspacio libre: 0 bytes"
							+ "\nEspacio disponible: 0 bytes"
							+ "\nEspacio total: 0 bytes");
				}
			}
			
		return strBuilder.toString();
			}			
	

	@Override
	public boolean getMostrarOcultos() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNombreCarpeta() {
		return carpetaDeTrabajo.getName();
	}

	@Override
	public TipoOrden getOrdenado() {
		return ordenado;
	}

	@Override
	public String[] getTituloColumnas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getUltimaModificacion(String arg0)
			throws GestionFicherosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String nomRaiz(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numRaices() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void renombra(String arg0, String arg1)
			throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo,arg0);
		File newFile = new File(carpetaDeTrabajo,arg1);
		//que no exista -> lanzar� una excepci�n
		if (!file.exists()) {
			throw new GestionFicherosException("Este Fichero o Directorio no existe.");
		}
		file.renameTo(newFile);
		actualiza();
		//que se pueda escribir -> lanzar� una excepci�n
		if (!file.renameTo(newFile)) {
			throw new GestionFicherosException("No tienes permisos de Escritura.");
		}
	}

	@Override
	public boolean sePuedeEjecutar(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sePuedeEscribir(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sePuedeLeer(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setColumnas(int arg0) {
		columnas = arg0;

	}

	@Override
	public void setDirCarpeta(String arg0) throws GestionFicherosException {
		File file = new File(arg0);

		// se controla que la direcci�n exista y sea directorio
		if (!file.isDirectory()) {
			throw new GestionFicherosException("Error. Se esperaba "
					+ "un directorio, pero " + file.getAbsolutePath()
					+ " no es un directorio.");
		}

		// se controla que haya permisos para leer carpeta
		if (!file.canRead()) {
			throw new GestionFicherosException(
					"Alerta. No se puede acceder a  " + file.getAbsolutePath()
							+ ". No hay permisos");
		}

		// actualizar la carpeta de trabajo
		carpetaDeTrabajo = file;

		// actualizar el contenido
		actualiza();

	}

	@Override
	public void setFormatoContenido(FormatoVistas arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMostrarOcultos(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOrdenado(TipoOrden arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSePuedeEjecutar(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSePuedeEscribir(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSePuedeLeer(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUltimaModificacion(String arg0, long arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

}
