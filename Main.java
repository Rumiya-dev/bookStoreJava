package model;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import model.salesman;
import model.bookGenre;
import model.customer;
import model.person;
import model.book;
import model.order;
import model.Profit;
import model.BookAdditional;

public class Main {
	
//создание массивов для книг, клиентов, заказов и продавцов	
static ArrayList<book> books = new ArrayList<>();
static ArrayList<customer> customers = new ArrayList<>();
static ArrayList<salesman> salesmans = new ArrayList<>();
static ArrayList<order> orders = new ArrayList<>();


//программа начинает свою работу с этой главной функции
public static void main(String[]args) {
	/*
	 * заполнение данными метода из массивов orders, books, customers, salesmans
	 */
	initData();//в первую очередь нужно перейти к части кода, где заполняются массивы данными
	
	String booksInfo = 
			String.format("total number of books sold %d for the amount %f", getCountOfSoldBooks(), getAllPriceOfSoldBooks());
	System.out.println(booksInfo);
	
	for(salesman salesman : salesmans) {
		System.out.println(salesman.getName() + " sold " + getProfitBySalesman(salesman.getId()).toString());//toString - преобразует число в строку
	}
	
	ArrayList<BookAdditional>soldBooksCount = getCountOfSoldBooksByGenre();
	HashMap<bookGenre,Double>soldBookPrices = getPriceOfSoldBooksByGenre();
	
	String soldBookStr = "by genre: %s books sold %d total price %f";//%s - имя из name(), %d - количество из getCount(), %f - цена price
	
	for(BookAdditional bookAdditional : soldBooksCount) {
		double price = soldBookPrices.get(bookAdditional.getGenre());
		System.out.println(
		String.format(
		soldBookStr, 
		bookAdditional.getGenre().name(),bookAdditional.getCount(),price));
	}
	
	int age = 30;
	String analyzeGenreStr = "Customer(s) before %d age choose genre %s";
	System.out.println(String.format(analyzeGenreStr, 30, getMostPopularGenreLessThenAge(age)));
	
	String analyzeGenreStr2 = "Customer(s) after %d age choose genre %s";
	System.out.println(String.format(analyzeGenreStr2, 30, getMostPopularGenreMoreThenAge( age )));
}

/*получить наиболее популярный жанр для заказчиков до
 * возраста age
 * age - требуемый возраст
 * return жанр
 */
public static bookGenre getMostPopularGenreLessThenAge(int age) {
	ArrayList<Long> customersIds = new ArrayList<>();
	for(customer customer : customers) {
		if(customer.getAge() < age) {
			customersIds.add(customer.getId());
		}
	}
	
	return extracted(customersIds);
}


/*получить наиболее популярный жанр для заказчиков старше age
 * возраста age
 * age - требуемый возраст
 * return жанр
 */
public static bookGenre getMostPopularGenreMoreThenAge(int age) {
	ArrayList<Long> customersIds = new ArrayList<>();
	for(customer customer : customers) {
		if(customer.getAge() > age) {
			customersIds.add(customer.getId());
		}
	}
	
	return extracted(customersIds);
}


/*расчет наиболее популярных жанров книг для клиетов для методов, которые определяют популярные жанры для разных возрастов
 * 
 */
private static bookGenre extracted(ArrayList<Long> getMostPopularBookGenre) {
	int countArt = 0;
	int countProgr = 0;
	int countPsych = 0;
	
	for(order order : orders) {
		if(getMostPopularBookGenre.contains(order.getCustomerId())) {
			 countArt += getCountOfSoldBooksByGenre(order, bookGenre.Art);
			 countProgr += getCountOfSoldBooksByGenre(order, bookGenre.Programming);
			 countPsych += getCountOfSoldBooksByGenre(order, bookGenre.Psychology);
		}
	}
	ArrayList<BookAdditional> result = new ArrayList<>();
	result.add(new BookAdditional(bookGenre.Art, countArt));
	result.add(new BookAdditional(bookGenre.Programming, countProgr));
	result.add(new BookAdditional(bookGenre.Art, countPsych));
	
	 result.sort(new Comparator<BookAdditional>() {

		@Override
		public int compare(BookAdditional left, BookAdditional right) {
			
			return right.getCount() - left.getCount();//сортировка массива по убыванию
		}
		 
	 });
	return result.get(0).getGenre();//get(0) - это первый элемент отсортированого по убыванию массива
}


//получить количество проданных книг по жанрам
public static ArrayList<BookAdditional> getCountOfSoldBooksByGenre(){
	ArrayList<BookAdditional> result = new ArrayList<>();
	int countArt = 0;
	int countProgr = 0;
	int countPsych = 0;
	
	for(order order : orders) {
		countArt += getPriceOfSoldBooksByGenre(order, bookGenre.Art);
		countProgr += getPriceOfSoldBooksByGenre(order, bookGenre.Programming);
		countPsych += getPriceOfSoldBooksByGenre(order, bookGenre.Psychology);
	}
	
	result.add(new BookAdditional(bookGenre.Art, countArt));
	result.add(new BookAdditional(bookGenre.Programming, countProgr));
	result.add(new BookAdditional(bookGenre.Art, countPsych));
	
	return result;
	
}

//получить общее количество книг в одном заказе по определенному жанру
public static int getCountOfSoldBooksByGenre(order order, bookGenre genre) {
	int count = 0;
	for(long bookId : order.getBooks()) {//берем массив заказов order в нем находим id книги и по этому id переходим
		//в массив книг
		book book = getBookById(bookId);
		if(book != null && book.getGenre() == genre)//если книга не имеет пустое значение цены и жанр совпадает,
		count ++;//увелииваем это значение
	}
	return count;
}

//получить стоимость проданных книг по жанрам
public static HashMap<bookGenre,Double>getPriceOfSoldBooksByGenre(){
	HashMap<bookGenre,Double> result = new HashMap<>();
	double priceArt = 0;
	double priceProgr = 0;
	double pricePsych = 0;
	for(order order : orders) {
		priceArt += getPriceOfSoldBooksByGenre(order, bookGenre.Art);
		priceProgr += getPriceOfSoldBooksByGenre(order, bookGenre.Programming);
		pricePsych += getPriceOfSoldBooksByGenre(order, bookGenre.Psychology);
	}
	result.put(bookGenre.Art, priceArt);
	result.put(bookGenre.Programming, priceProgr);
	result.put(bookGenre.Psychology, pricePsych);
	return result;
}

//получить общую сумму книг в одном заказе по определенному жанру
public static double getPriceOfSoldBooksByGenre(order order, bookGenre genre) {
	double price = 0;
	for(long bookId : order.getBooks()) {//берем массив заказов order в нем находим id книги и по этому id переходим
		//в массив книг, где указана стоимость книги
		book book = getBookById(bookId);
		if(book != null && book.getGenre() == genre)//если книга не имеет пустое значение цены
		price += book.getPrice();//прибавляем к перем price цену из массива books
	}
	return price;
}


//получить общее количество и общую стоимость товаров для каждого продавца
//long salesmanId - уникальный id каждого продавца
public static Profit getProfitBySalesman(long salesmanId) {
	int count = 0;
	double price = 0;
	for(order order  : orders) {
		if(order.getSalesmanId() == salesmanId) {
			price += getPriceOfSoldBooksInOrder(order);
			count += order.getBooks().length;
		}
		
	}
	return new Profit(count, price); //new profit - анонимная переменная, которая является одноразовой
	//здесь возвращаем количество проданных книг и сумму(цену)
}


//общая сумма всех заказов 
public static double getAllPriceOfSoldBooks() {
	double price = 0;
	for(order order : orders) {
		price += getPriceOfSoldBooksInOrder(order);
	}
	return price;
}

//получить общую стоимость заказа
public static double getPriceOfSoldBooksInOrder(order order) {
	double price = 0;
	for(long bookId : order.getBooks()) {//берем массив заказов order в нем находим id книги и по этому id переходим
		//в массив книг, где указана стоимость книги
		book book = getBookById(bookId);
		if(book != null)//если книга не имеет пустое значение цены
		price += book.getPrice();//прибавляем к перем price цену из массива books
	}
	return price;
	
}


public static book getBookById(long id) {// цикл foreach => for (тип и название: название массива/коллекции)
	//функция для получения  книги по ее id из массива arrayList
	book current = null;//инициализация переменной current с пустым значением
	for(book book : books) {//прохождение массива books с помощью массива foreach
		if(book.getId() == id) {//если id, который мы получили в начале функции long id
			// совпадает с id книги
			current = book;//то мы присваеваем перем current эту книгу
			break;//выходим из цикла
		}
	}
	return current;//возвращаем полученное значение
}




//получить общее количество проданных книг
public static int getCountOfSoldBooks() {
	int count = 0;
	for(order order : orders) {
		count += order.getBooks().length;//прошлись по каждому заказу и узнали количество книг
	}
	return count;
}

//заполнение массивов данными
public static void initData(){
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

}
