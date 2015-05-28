package qq.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import qq.spring.model.Book;
import qq.spring.service.BookService;

@Controller
@RequestMapping(value = "/book")
public class BookController {
	@Autowired
	private BookService bookservice;

	@RequestMapping(value = "/add")
	public String add(Book book, Model model) {
		bookservice.add(book);
		model.addAttribute("message", "add");
		return "book/add";
	}

	@RequestMapping(value = "/update")
	public String update(Book book, Model model) {
		bookservice.update(book);
		model.addAttribute("message", "update");
		return "book/update";
	}

	@RequestMapping(value = "/list")
	@ResponseBody
	public String list(Book book) {
		return bookservice.list(book);
	}
}