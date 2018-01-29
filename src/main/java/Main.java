import dbService.DBService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignUpServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        DBService dbService = new DBService();

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("src/main/resources/html");
        resource_handler.setWelcomeFiles(new String[] {"index.html"});

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new SignUpServlet(dbService)), "/signup");

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[] {resource_handler, contextHandler});

        Server server = new Server(8080);
        server.setHandler(handlerList);
        server.start();
        server.join();
    }
}
