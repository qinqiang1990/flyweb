package qq.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qq.spring.dao.BookDao;
import qq.spring.model.Book;

@Service
public class BookService {

	@Autowired
	private BookDao bookdao;

	// 模拟数据库操作
	public void add(Book book) {
		bookdao.add(book);
	}

	public void update(Book book) {
		bookdao.update(book);
	}

	public List<Book> list(Book book) {
		// TODO Auto-generated method stub
		return bookdao.list(book);
	}
}