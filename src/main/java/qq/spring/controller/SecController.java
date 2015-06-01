package qq.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import qq.spring.model.Book;
import qq.spring.service.BookService;

@Controller
@RequestMapping(value = "/sec")
public class SecController {
	@Autowired
	private BookService bookservice;

	@Secured("{ROLE_USER,ROLE_ADMIN}")
	@RequestMapping(value = "/add")
	public String add(Book book, Model model) {
		bookservice.add(book);
		model.addAttribute("message", "add");
		return "book/add";
	}

	@Secured("{ROLE_USER}")
	@RequestMapping(value = "/update")
	public String update(Book book, Model model) {
		bookservice.update(book);
		model.addAttribute("message", "update");
		return "book/update";
	}

	@RequestMapping(value = "/list")
	@ResponseBody
	public List<Book> list(Book book) {
		return bookservice.list(book);
	}
}