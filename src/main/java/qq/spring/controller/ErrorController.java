package qq.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/error")
public class ErrorController {
 
	@RequestMapping(value = "/deny")
	public String deny() { 
		return "error/deny";
	}
}