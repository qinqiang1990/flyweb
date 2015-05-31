package qq.spring.dao;

import java.util.ArrayList;
import java.util.List;

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

	public List<Book> list(Book book) {
		System.out.print("list");
		ArrayList<Book> list = new ArrayList<Book>();
		for (int i = 0; i < 10; i++) {
			Book temp = new Book();
			temp.setId(i);
			temp.setName(String.valueOf(i));
			temp.setAuthor(String.valueOf(i));
			list.add(temp);
		}
		return list;
	}
}