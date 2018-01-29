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

        dbService.addUser(firstName, secondName, email, login, password);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
