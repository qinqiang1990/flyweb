package jetty;
 
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;

import com.google.common.collect.Lists;
 

public class JettyTest {

	private static final int PORT = 8080;

	private static final String DEFAULT_WEBAPP_PATH = "src/main/webapp";

	private static final String DEFAULT_CONTEXT_PATH = "/flyweb";

	private static final String DEFAULT_WEBDEFAULTXML_PATH = "target/flyweb/WEB-INF/webdefault.xml";

	private static final String DEFAULT_WEBXML_PATH = "target/flyweb/WEB-INF/web.xml";

	public static void main(String[] args) throws Exception {

		Server server = new Server(PORT);
		// war
		WebAppContext context = new WebAppContext(DEFAULT_WEBAPP_PATH,
				DEFAULT_CONTEXT_PATH);

		context.setDefaultsDescriptor(DEFAULT_WEBDEFAULTXML_PATH);
		context.setDescriptor(DEFAULT_WEBXML_PATH);
		
	 
		List<String> jarNameExprssions = Lists.newArrayList("/flyweb/WEB-INF/lib/*[^/]*\\.jar$");
		  
		context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
				StringUtils.join(jarNameExprssions, '|'));

		
		/*
		WebAppClassLoader classLoader = new WebAppClassLoader(context);
		classLoader.addClassPath("target/classes");
		context.setClassLoader(classLoader);*/
  
		//context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern","target/flyweb/WEB-INF/lib/.*\\.jar$");
 
		server.setHandler(context);

		server.start(); 
		
		System.out.println("[INFO] Server running at http://localhost:" + PORT + DEFAULT_CONTEXT_PATH);
		System.out.println("[HINT] Hit Enter to reload the application quickly");

		// 等待用户输入回车重载应用.
		while (true) {
			char c = (char) System.in.read();
			if (c == '\n') {

				server.stop();
				server.start();
			}
		}
	}
}
