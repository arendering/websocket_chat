import dbService.DBService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import servlets.WebSocketChatServlet;
import settings.Settings;

public class Main {
    public static void main(String[] args) throws Exception {
        DBService dbService = new DBService();

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("src/main/resources");
        resource_handler.setWelcomeFiles(new String[] {"html/index.html"});

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new SignUpServlet(dbService)), "/signup");
        contextHandler.addServlet(new ServletHolder(new SignInServlet(dbService)), "/signin");
        contextHandler.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[] {resource_handler, contextHandler});

        Server server = new Server(Settings.instance().APP_PORT);
        server.setHandler(handlerList);
        server.start();
        server.join();
    }
}
