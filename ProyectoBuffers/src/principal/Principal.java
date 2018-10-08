package principal;

import javax.swing.JFrame;

import model.*;
import view.*;
import controller.*;

public class Principal {

	public static void main(String[] args) {		
	
		GestionDatos model = new GestionDatos();
		Almacen_Libro model2 = new Almacen_Libro();
		LaunchView view = new LaunchView();
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setVisible(true);
		
		GestionEventos controller = new GestionEventos(model,view,model2);
		controller.contol();
		
	}

}
