package dbService.dao;

import dbService.dataSets.UserDataSet;
import org.hibernate.Session;

public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public void insertUser(String firstName, String secondName, String email, String login, String password) {
        session.save(new UserDataSet(firstName, secondName, email, login, password));
    }
}
