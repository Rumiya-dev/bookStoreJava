package model;

public class book {
	private long id; //уникальный номер книги(артикул)
	private String title; //название книги
	private String author; //автор книги
	private double price; //цена книги
	private bookGenre genre;//жанр книги
	public book(long id, String title, String author, double price, bookGenre genre) {//заполение данных 
		//класса book через конструктор
		
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.genre = genre;
	}
	public long getId() {//геттеры и сеттеры - это
		//методы доступа к переменным объекта, через них
		//можно записать данные в перемнную и получить к ней доступ
		return id;
	}
	public void setId(long id) {
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public bookGenre getGenre() {
		return genre;
	}
	public void setGenre(bookGenre genre) {
		this.genre = genre;
	}
	
	
	

}
