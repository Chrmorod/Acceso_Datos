package view;

import java.awt.Dimension;

import javax.swing.*;

public class LaunchView extends JFrame {

	private JButton comparar,buscar;
	private JTextArea textArea;
	private JTextField fichero1,fichero2,palabra;
	private JLabel label_f1,label_f2,label_pal;
	private JCheckBox primera;
	
	private JPanel panel;
	private JButton saveBook;
	private JButton withdrawBook;
	private JButton allBooks;
	private JTextField idTextField;
	private JTextField titleTextField;
	private JTextField authorTextField;
	private JTextField yearTextField;
	private JTextField editorTextField;
	private JTextField pagesTextField;
	
	public LaunchView() {
		
		setBounds(200,200,1000,450);
		setTitle("Proyecto Buffers");	
		panel = new JPanel();
		
		comparar = new JButton("Comparar contenido");
		comparar.setBounds(20, 5, 150, 26);
		comparar.setPreferredSize(new Dimension(150, 26));
		buscar = new JButton("Buscar palabra");
		buscar.setBounds(175, 5, 150, 26);
		buscar.setPreferredSize(new Dimension(150, 26));
					
		fichero1 = new JTextField("",10);
		fichero1.setBounds(395, 8, 74, 20);
		fichero2 = new JTextField("",10);
		fichero2.setBounds(539, 8, 74, 20);
		palabra = new JTextField("",10);
		palabra.setBounds(675, 8, 74, 20);
		
		label_f1 = new JLabel("Fichero 1:");
		label_f1.setBounds(330, 11, 66, 14);
		label_f2 = new JLabel("Fichero 2:");
		label_f2.setBounds(474, 11, 66, 14);
		label_pal = new JLabel("Palabra:");
		label_pal.setBounds(618, 11, 55, 14);
		
		primera = new JCheckBox("Primera aparición");
		primera.setBounds(754, 6, 132, 23);

		textArea = new JTextArea(20, 80);
		textArea.setBounds(53,36,644,364);
		textArea.setEditable(false);		
		panel.setLayout(null);
		//Nuevo Botón para guardar libros.
		saveBook = new JButton("Guardar Libro");
		saveBook.setBounds(782, 297, 132, 23);
		panel.add(saveBook);
		panel.add(textArea);
		//Nuevo Botón para recuperar todos los libros.
        allBooks = new JButton("Recuperar Todos");
        allBooks.setBounds(782, 365, 132, 23);
        panel.add(allBooks);
        //Nuevo Botón para recuperar un libro.
        withdrawBook = new JButton("Recuperar Libro");
        withdrawBook.setBounds(782, 331, 132, 23);
        panel.add(withdrawBook);
        
        //Nuevo TextField identificador
        idTextField = new JTextField();
        idTextField.setBounds(808, 53, 156, 20);
        panel.add(idTextField);
        idTextField.setColumns(10);
        titleTextField = new JTextField();
        titleTextField.setColumns(10);
        titleTextField.setBounds(808, 90, 156, 20);
        panel.add(titleTextField);
        //Nuevo TextField autor
        authorTextField = new JTextField();
        authorTextField.setColumns(10);
        authorTextField.setBounds(808, 132, 156, 20);
        panel.add(authorTextField);
        //Nuevo TextField año de publicacion
        yearTextField = new JTextField();
        yearTextField.setColumns(10);
        yearTextField.setBounds(808, 173, 156, 20);
        panel.add(yearTextField);
        //Nuevo TextField editor
        editorTextField = new JTextField();
        editorTextField.setColumns(10);
        editorTextField.setBounds(808, 209, 156, 20);
        panel.add(editorTextField);
        //Nuevo TextField numero de paginas
        pagesTextField = new JTextField();
        pagesTextField.setColumns(10);
        pagesTextField.setBounds(808, 240, 48, 20);
        panel.add(pagesTextField);
        //Nuevo label identificador
        JLabel idLabel = new JLabel("Consulta por ID:");
        idLabel.setBounds(707, 56, 101, 14);
        panel.add(idLabel);
        //Nuevo label titulo
        JLabel titleLabel = new JLabel("Titulo:");
        titleLabel.setBounds(707, 93, 46, 14);
        panel.add(titleLabel);
        //Nuevo label autor
        JLabel authorLabel = new JLabel("Autor:");
        authorLabel.setBounds(707, 135, 46, 14);
        panel.add(authorLabel);
        //Nuevo label año de publicación
        JLabel yearLabel = new JLabel("A\u00F1o Publicaci\u00F3n:");
        yearLabel.setBounds(707, 176, 101, 14);
        panel.add(yearLabel);
        //Nuevo label de editor
        JLabel editorLabel = new JLabel("Editor:");
        editorLabel.setBounds(707, 212, 86, 14);
        panel.add(editorLabel);
        //Nuevo label de numero de paginas
        JLabel pagesLabel = new JLabel("N\u00BA Paginas:");
        pagesLabel.setBounds(707, 243, 86, 14);
        panel.add(pagesLabel);
		
		panel.add(comparar);
		panel.add(buscar);
		panel.add(label_f1);
		panel.add(fichero1);
		panel.add(label_f2);
		panel.add(fichero2);
		panel.add(label_pal);
		panel.add(palabra);
		panel.add(primera);
		

		
        // Añadimos el JPanel al JFrame
        this.getContentPane().add(panel);		
                       		
	}	
	
	public JButton getComparar() {
		return comparar;
	}

	public void setComparar(JButton comparar) {
		this.comparar = comparar;
	}

	public JButton getBuscar() {
		return buscar;
	}

	public void setBuscar(JButton buscar) {
		this.buscar = buscar;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
			
	public JTextField getFichero1() {
		return fichero1;
	}

	public void setFichero1(JTextField fichero1) {
		this.fichero1 = fichero1;
	}

	public JTextField getFichero2() {
		return fichero2;
	}

	public JTextField getPalabra() {
		return palabra;
	}

	public void setPalabra(JTextField palabra) {
		this.palabra = palabra;
	}

	public JCheckBox getPrimera() {
		return primera;
	}

	public void setPrimera(JCheckBox primera) {
		this.primera = primera;
	}

	public void setFichero2(JTextField fichero2) {
		this.fichero2 = fichero2;
	}
	
	public void showError(String m){
		JOptionPane.showMessageDialog(this.panel,
			    m,
			    "Error",
			    JOptionPane.ERROR_MESSAGE);
	}
/******Getters y Setters Nuevos de los TextField y Botones añadidos para la seccion Libros.***/
	public JTextField getIdTextField() {
		return idTextField;
	}

	public void setIdTextField(JTextField idTextField) {
		this.idTextField = idTextField;
	}

	public JTextField getTitleTextField() {
		return titleTextField;
	}

	public void setTitleTextField(JTextField titleTextField) {
		this.titleTextField = titleTextField;
	}

	public JTextField getAuthorTextField() {
		return authorTextField;
	}

	public void setAuthorTextField(JTextField authorTextField) {
		this.authorTextField = authorTextField;
	}

	public JTextField getYearTextField() {
		return yearTextField;
	}

	public void setYearTextField(JTextField yearTextField) {
		this.yearTextField = yearTextField;
	}

	public JTextField getEditorTextField() {
		return editorTextField;
	}

	public void setEditorTextField(JTextField editorTextField) {
		this.editorTextField = editorTextField;
	}

	public JTextField getPagesTextField() {
		return pagesTextField;
	}

	public void setPagesTextField(JTextField pagesTextField) {
		this.pagesTextField = pagesTextField;
	}
	

	public JButton getSaveBook() {
		return saveBook;
	}

	public void setSaveBook(JButton saveBook) {
		this.saveBook = saveBook;
	}

	public JButton getWithdrawBook() {
		return withdrawBook;
	}

	public void setWithdrawBook(JButton withdrawBook) {
		this.withdrawBook = withdrawBook;
	}

	public JButton getAllBooks() {
		return allBooks;
	}

	public void setAllBooks(JButton allBooks) {
		this.allBooks = allBooks;
	}
}
