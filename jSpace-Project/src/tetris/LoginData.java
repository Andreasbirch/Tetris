package tetris;

import javafx.beans.property.SimpleStringProperty;

public class LoginData {

    private final SimpleStringProperty username;
    private final SimpleStringProperty password;

    public LoginData (String username, String password) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }

    public String getUsername() {
        return username.get();
    }

    public String getPassword() {
        return password.get();
    }
}
