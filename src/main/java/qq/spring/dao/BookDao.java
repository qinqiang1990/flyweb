package qq.spring.dao;

import org.springframework.stereotype.Repository;

import qq.spring.model.Book;

@Repository
public class BookDao {

	public void add(Book book) {
		System.out.print("Add");
	}

	public void update(Book book) {
		System.out.print("Update");
	}

	public String list(Book book) {
		System.out.print("list");
		return "list";
	}
}