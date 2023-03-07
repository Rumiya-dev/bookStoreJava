package model;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import model.salesman;
import model.bookGenre;
import model.customer;
import model.person;
import model.book;
import model.order;
import model.Profit;
import model.BookAdditional;
import model.Main;

public class BookDataSourceImpl implements BookDataSource{
	
	private List<book> books = new ArrayList<>();
	private List<customer> customers = new ArrayList<>();
	private List<salesman> salesmans = new ArrayList<>();
	private List<order> orders = new ArrayList<>();
	
	public BookDataSourceImpl() { initData(); }
	
	public  void initData(){
		//продавцы
		salesmans.add(new salesman(1,  "Joe Dow",  37));//продавец с id 1 мария иванова, 37 лет
		salesmans.add(new salesman(2, "Chris Parker", 47));
		salesmans.add(new salesman(3, "Harry Potter", 57));
	    
		//покупатели
	    customers.add(new customer(1, "Jason Dow", 25));
	    customers.add(new customer(2, "Jason Statham", 32));
	    customers.add(new customer(3, "Thomas Anderson", 18));
	    
	    //книги 
	    books.add(new book(1, "Financist", "Teodor Draizer", 1600, bookGenre.Art));//id книги - 1, название книги - "Teodor Draizer", 
	    //цена - 1600
	    //, жанр - Art
	    books.add(new book(2, "War and Piece", "Tolstoy Lec", 1500, bookGenre.Art));
	    books.add(new book(3, "Dead soul", "Dostoevsky Fedor", 1300, bookGenre.Art));
	    books.add(new book(4, "Man and woman", "Fraid Zigmond", 1200, bookGenre.Psychology));
	    books.add(new book(5, "Manipulation and actualization", "Everett Shostorm", 1150, bookGenre.Psychology));
	    books.add(new book(6, "C++ start", "Zinich Roman", 1100, bookGenre.Programming));

	    //заказы
	    orders.add(new order(1, 2, 1, new long[]{6, 6, 6}));//продавец под №2 продал книги с номерами 
	    //4, 6, 4 покупателю под №3, номер заказа 1
	    orders.add(new order(2, 2, 2, new long[]{6, 6, 6}));
	    orders.add(new order(3, 3, 2, new long[]{6, 6, 6}));

	}
	
	
	@Override
	public List<book> getBooks() {
		// TODO Auto-generated method stub
		return books;
	}
	
	@Override
	public List<salesman> getSalesmans() {
		// TODO Auto-generated method stub
		return salesmans;
	}
	@Override
	public List<customer> getCustomers() {
		// TODO Auto-generated method stub
		return customers;
	}
	
	@Override
	public List<order> getOrders() {
		// TODO Auto-generated method stub
		return orders;
	}

	
	
}
