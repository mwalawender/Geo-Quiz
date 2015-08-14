package geoquiz.walawender.michal.pl.geoquiz;

public class User {

    private String userLogin;
    private String userPassword;
    private boolean isLogged;

    User(String userLogin, String userPassword){
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
