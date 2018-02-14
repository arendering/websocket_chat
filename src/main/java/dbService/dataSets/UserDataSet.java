package dbService.dataSets;

import settings.Settings;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UserDataSet implements Serializable {

    @Column(name="firstName")
    private String firstName;

    @Column(name="secondName")
    private String secondName;

    @Column(name="email")
    private String email;

    @Id
    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    public UserDataSet() {
    }

    public UserDataSet(String firstName, String secondName, String email, String login, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
