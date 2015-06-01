package qq.spring;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class Security {
	
	public void getUserName(){
		SecurityContext secCtx=SecurityContextHolder.getContext();
		Authentication auth=secCtx.getAuthentication();
		Object principal=auth.getPrincipal();
		String userName="";
		if(principal instanceof UserDetails)
		{
			userName=((UserDetails)principal).getUsername();
		}
		else
		{
			userName=principal.toString();
		}
	}
	
}
