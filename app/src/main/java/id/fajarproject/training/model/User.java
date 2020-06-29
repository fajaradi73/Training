package id.fajarproject.training.model;

/**
 * Create by Fajar Adi Prasetyo on 24/06/2020.
 */
public class User {
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validUser(String username,String password){
        boolean isValid = true;
        if (!username.equals("admin") && !password.equals("123456")){
            isValid = false;
        }
        return isValid;
    }
}
