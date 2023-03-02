package model;

public class order {
	
	private long id;//уникальный номер книги(артикул)
	private long customerId;//уникальный номер покупателя, которому продали книгу
	private long salesmanId;//кто продавец
	private long[] books;//список книг, которые продали
	public order(long id, long salesmanId, long customerId, long[] books) {
		
		this.id = id;
		this.customerId = customerId;
		this.salesmanId = salesmanId;
		this.books = books;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public long getSalesmanId() {
		return salesmanId;
	}
	public void setSalesmanId(long salesmanId) {
		this.salesmanId = salesmanId;
	}
	public long[] getBooks() {
		return books;
	}
	public void setBooks(long[] books) {
		this.books = books;
	}
	
	

}
