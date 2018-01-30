package servlets;

import dbService.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {

    private DBService dbService;

    public SignUpServlet(DBService dbService) {
        this.dbService = dbService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String firstName = request.getParameter("first_name");
        String secondName = request.getParameter("second_name");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if(login.isEmpty() || password.isEmpty()) {
            response.getWriter().println("Login and password could not be empty.");
        } else {
            if (dbService.getUser(login) != null) {
                response.getWriter().println("User with the same login already registered. Try another.");
            } else {
                dbService.addUser(firstName, secondName, email, login, password);
                response.getWriter().println("You are successfully registered!");
            }
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
