package logicControllers;

import entities.User;
import org.apache.log4j.Logger;

public class UserController {
    private static Logger logger = Logger.getLogger(AuthController.class);

    private DAOUser dao;

    public UserController(){
        dao = new DAOUser();
    }

    public User getUser(String auth) {
        return dao.getUser(auth);
    }

}
