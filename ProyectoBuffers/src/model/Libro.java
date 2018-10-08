package model;

import java.io.Serializable;

public class Libro implements Serializable{
	
	private String id,title,author,editor;
	private int year,pages;
	
	//Constructores
	public Libro() {
		
	}
	//Recuperamos los elementos que definen un libro
	public Libro(String id, String title,String author,int year, String editor, int pages) {
		this.id=id;
		this.title=title;
		this.author=author;
		this.year=year;
		this.editor=editor;
		this.pages=pages;		
	}
	//Getters y Setters de los elementos de un libro
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}
}
