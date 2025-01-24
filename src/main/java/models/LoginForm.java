package models;

public class LoginForm {
    private String userCredential;
    private String password;

    public LoginForm(String userCredential, String password) {
        this.userCredential = userCredential;
        this.password = password;
    }

    public String getUserCredential() {
        return userCredential;
    }

    public void setUserCredential(String userCredential) {
        this.userCredential = userCredential;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
