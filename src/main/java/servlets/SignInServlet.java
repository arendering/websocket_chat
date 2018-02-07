package servlets;

import dbService.DBService;
import dbService.dataSets.UserDataSet;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SignInServlet extends HttpServlet {

    private DBService dbService;

    public SignInServlet(DBService dbService) { this.dbService = dbService; }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login.isEmpty() || password.isEmpty()) {
            response.getWriter().println("Login and password could not be empty.");
        } else {
            UserDataSet userDataSet = dbService.getUser(login);
            if(userDataSet != null && password.equals(userDataSet.getPassword())) {
                Map<String, String> pageVars = new HashMap<>();
                pageVars.put("login", login);
                pageVars.put("password", password);
                response.getWriter().println(PageGenerator.instance().getPage("chat.html", pageVars));
                response.setContentType("text/html;charset=utf-8");
            } else {
                response.getWriter().println("Wrong login or password.");
            }
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
