package model;

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

public interface BookDataSource {
	  List<book>  getBooks();
	  List<customer> getCustomers();
	  List<salesman> getSalesmans();
	  List<order> getOrders();
	  


}
