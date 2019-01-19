package Controller;

import Model.User;
import Controller.DAOS.DAOAuth;
import Controller.DAOS.DAOUser;

public class Users {

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
