package logicControllers;

import entities.Admin;
import entities.Subject;
import entities.User;

import java.util.List;

public class IndexAdminController {
    private Admin user;
    private DAO dao;

    public IndexAdminController(String authUsername){
        this.dao = new DAOAuth();
        this.user = (Admin) dao.getUser(authUsername);
    }

    public List<Subject> getUserSubjects(){
        return user.getSubjects();
    }
}
