package beans;

import DAO.Identificator;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Admin implements User, Identificator<Integer>, Serializable {
    private int id;
    private String login;
    private String password;
    private String email;
    private LocalDateTime lastAccess;
    private LocalDateTime createdDate;

    public Admin() {
        this.login = "none";
        this.password = "none";
        this.email = "none";
        this.lastAccess = LocalDateTime.now();
        this.createdDate = LocalDateTime.now();
    }

    public Admin(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.lastAccess = LocalDateTime.now();
        this.createdDate = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(LocalDateTime lastAccess) {
        this.lastAccess = lastAccess;
    }

    @Override
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
