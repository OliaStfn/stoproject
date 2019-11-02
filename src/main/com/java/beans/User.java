package beans;

import java.time.LocalDateTime;

public interface User {
    String getLogin();

    void setLogin(String login);

    String getPassword();

    void setPassword(String password);

    String getEmail();

    void setEmail(String email);

    LocalDateTime getCreatedDate();

    void setCreatedDate(LocalDateTime createdDate);
}
