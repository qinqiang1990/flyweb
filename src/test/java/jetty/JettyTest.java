package jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.Test;

/*import java.lang.reflect.Field;

 import org.eclipse.jetty.server.Connector;
 import org.eclipse.jetty.server.Server;
 import org.eclipse.jetty.server.nio.SelectChannelConnector;
 import org.eclipse.jetty.webapp.WebAppContext;
 import org.junit.Test;*/

public class JettyTest {

	private final int PORT = 9090;

	private final String DEFAULT_WEBAPP_PATH = "src/main/webapp";

	private final String DEFAULT_CONTEXT_PATH = "/flyweb";

	private final String DEFAULT_WEBDEFAULTXML_PATH = "src/main/webapp/WEB-INF/webdefault.xml";

	private final String DEFAULT_WEBXML_PATH = "src/main/webapp/WEB-INF/web.xml";

	@Test
	public void start() throws Exception {

		Server server = new Server(PORT);
//war
		WebAppContext context = new WebAppContext();
		context.setContextPath(DEFAULT_CONTEXT_PATH);
		context.setResourceBase(DEFAULT_WEBAPP_PATH);
		context.setDefaultsDescriptor(DEFAULT_WEBDEFAULTXML_PATH);
		context.setDescriptor(DEFAULT_WEBXML_PATH);
		
		WebAppClassLoader classLoader = new WebAppClassLoader(context);
		classLoader.addClassPath("target/classes");
		context.setClassLoader(classLoader);

		server.setHandler(context);

		server.start();
		server.join();
	}
}
