package logicControllers;

import entities.AuthInfo;
import entities.Student;
import entities.User;
import logicControllers.DAOS.DAOAuth;
import logicControllers.DAOS.DAOUser;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
