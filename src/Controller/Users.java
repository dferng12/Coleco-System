package Controller;

import Model.User;
import Controller.DAOS.DAOAuth;
import Controller.DAOS.DAOUser;
import org.apache.log4j.Logger;

public class Users {
    private static Logger logger = Logger.getLogger(Users.class);

    protected DAOUser daoUser;
    protected DAOAuth daoAuth;

    public Users(){
        daoUser = new DAOUser();
        daoAuth = new DAOAuth();
    }

    public User getUser(String username){
        return daoUser.getUser(username);
    }

    public void deleteUser(User user){
        return;
    }

}
