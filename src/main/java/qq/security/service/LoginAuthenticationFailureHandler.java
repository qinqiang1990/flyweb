package qq.security.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * 登录验证出错处理
 * @author Luxh
 */
public class LoginAuthenticationFailureHandler implements
		AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException ae)
			throws IOException, ServletException {

		// 根据AuthenticationException异常的类型
		// 进行出错业务逻辑处理
		// ...

		response.sendRedirect(request.getContextPath() + "/login");
	}

}
