package qq.security.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/auth")
public class LoginController {
	protected static Logger logger = Logger.getLogger("LoginController");

	/**
	 * 指向登录页面
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(
			@RequestParam(value = "error", required = false) boolean error,
			Model model) {

		logger.debug("Received request to show login page");

		if (error == true) {
			// Assign an error message
			model.addAttribute("error",
					"You have entered an invalid username or password!");
		} else {
			model.addAttribute("error", "");
		}
		return "security/loginpage";

	}

	/**
	 * 指定无访问额权限页面
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String getDeniedPage() {

		logger.debug("Received request to show denied page");

		return "security/deniedpage";

	}
}