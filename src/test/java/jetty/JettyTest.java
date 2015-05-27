package jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

/*import java.lang.reflect.Field;

 import org.eclipse.jetty.server.Connector;
 import org.eclipse.jetty.server.Server;
 import org.eclipse.jetty.server.nio.SelectChannelConnector;
 import org.eclipse.jetty.webapp.WebAppContext;
 import org.junit.Test;*/

public class JettyTest {

	private static final int PORT = 19090;
	private static final String DEFAULT_WEBAPP_PATH = "target/flyweb.war";

	private static final String DEFAULT_CONTEXT_PATH = "/";

	public static void main(String[] args) throws Exception {

		Server server = new Server(PORT);
		WebAppContext context = new WebAppContext();
		context.setContextPath(DEFAULT_CONTEXT_PATH);
		context.setWar(DEFAULT_WEBAPP_PATH);

		
	    Connector connector = new SelectChannelConnector();  
        connector.setPort(8080);  
        server.setConnectors(new Connector[] { connector });  
        WebAppContext context = new WebAppContext("E:\\workspace\\demo", "/demo");  
        server.addHandler(context);  
        server.setStopAtShutdown(true);  
        server.setSendServerVersion(true); 
        
        
		server.setHandler(context);

		server.start();
		server.join();
	}
}

