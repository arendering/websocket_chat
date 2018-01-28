import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class Main {
    public static void main(String[] args) throws Exception {
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("src/main/resources/html");
        resource_handler.setWelcomeFiles(new String[] {"index.html"});
        Server server = new Server(8080);
        server.setHandler(resource_handler);
        server.start();
        server.join();
    }
}
