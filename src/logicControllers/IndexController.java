package logicControllers;

import entities.Subject;
import entities.User;

import java.util.List;

public class IndexController {
    private User user;
    private DAO dao;

    public IndexController(String dni){
        this.dao = new DAOAuth();
        this.user = dao.getUser(dni);
    }

    public List<Subject> getUserSubjects(){
        return user.getSubjects();
    }
}
