package logicControllers;

import org.apache.log4j.Logger;

public class UserController {
    private static Logger logger = Logger.getLogger(AuthController.class);

    private DAOUser dao;

    public UserController(){
        dao = new DAOUser();
    }

    public String identify(String user) {
        System.out.print(user);
        return dao.identify(user);
    }

}
