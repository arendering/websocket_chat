package dbService;

import dbService.dao.UsersDAO;
import dbService.dataSets.UserDataSet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import settings.Settings;

public class DBService {

    private final SessionFactory sessionFactory;

    public DBService() {
            Configuration configuration = getMysqlConfiguration();
            sessionFactory = createSessionFactory(configuration);
    }


    public UserDataSet getUser(String login) {
        try {
            Session session = sessionFactory.openSession();
            UsersDAO dao = new UsersDAO(session);
            UserDataSet userDataSet = dao.get(login);
            session.close();
            return userDataSet;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addUser(String firstName, String secondName, String email, String login, String password) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UsersDAO dao = new UsersDAO(session);
            dao.insertUser(firstName, secondName, email, login, password);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    private Configuration getMysqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver.class", "com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://" + Settings.instance().DB_HOST +
                ":" + Settings.instance().DB_PORT + "/" +
                Settings.instance().DB_NAME + "?serverTimezone=UTC";
        configuration.setProperty("hibernate.connection.url", url);

        configuration.setProperty("hibernate.connection.username", Settings.instance().DB_USER);
        configuration.setProperty("hibernate.connection.password", Settings.instance().DB_PASSWORD);
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");

        return configuration;
    }

    private SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
