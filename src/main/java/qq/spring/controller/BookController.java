package qq.spring.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import qq.security.dao.sdjpa.v1.ControllerDaov1;
import qq.security.service.ControllerService;
import qq.spring.model.Book;
import qq.spring.service.BookService;

@Controller
@RequestMapping(value = "/book")
public class BookController {

	protected static Logger logger = Logger.getLogger(BookController.class);

	@Autowired
	private BookService bookservice;
	@Autowired
	ControllerService controllerservice;

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

	@Autowired
	ControllerDaov1 controllerdao;

	@RequestMapping(value = "/list")
	@ResponseBody
	public List<Book> list(Book book) {

		// jpa v1 test
		List<qq.security.model.Controller> list = controllerdao.findAll();
		logger.info("controller count:" + list.size());

		controllerservice.find(1L);

		return bookservice.list(book);
	}

	@RequestMapping(value = "/index")
	public String index(Book book, Model model) {
		model.addAttribute("message", "index");
		return "book/index";
	}
}