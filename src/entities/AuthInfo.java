package entities;

public class AuthInfo {

    private String user;
    //Password isn't stored as plain text. Instead, we store the hashed version of the password.
    private String passwd;

    public AuthInfo(String user, String passwd){
        this.user = user;
        this.passwd = passwd;
    }

}
