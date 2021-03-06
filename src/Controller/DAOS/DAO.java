package Controller.DAOS;

import Model.User;

import java.io.IOException;
import java.sql.*;

public class DAO {
    protected Connection connection = null;
    protected boolean connectionStatus;

    public DAO(){
        connectionStatus = connect();
    }

    public void createDB(){
        String query = "DROP DATABASE IF EXISTS ColecoSystem";
        executeUpdate(query);

        query = "CREATE DATABASE ColecoSystem";
        executeUpdate(query);

        DAOGrades daoGrades = new DAOGrades();
        DAOUser daoUser = new DAOUser();
        DAOSubject daoSubject = new DAOSubject();
        DAOAbsences daoAbsences = new DAOAbsences();
        DAOAuth daoAuth = new DAOAuth();
        DAOMessages daoMessages = new DAOMessages();
        daoGrades.init();
        daoSubject.init();
        daoUser.init();
        daoAuth.init();
        daoAbsences.init();
        daoMessages.init();

        daoGrades.fk();
        daoSubject.fk();
        daoUser.fk();
        daoAbsences.fk();
        daoMessages.fk();
    }

    public void restoreBackup(String restoreFile){
        String dbName = "ColecoSystem";
        String dbUser = "colecouser";
        String dbPass = "colecopass";
        String os = System.getProperty("os.name");

        String execProgram = "cmd";
        String execParam = "/c";
        if(os.equals("Linux")){
            execProgram = "/bin/bash";
            execParam = "-c";
        }
        String executeCmd = "mysql -u " + dbUser + " -p" + dbPass + " " + dbName + " < " + restoreFile;
        String[] cmd = {execProgram, execParam, executeCmd};
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ignored) {

        }
    }

    public void createBackup(String saveFile){
        String execProgram = "cmd";
        String execParam = "/c";
        String dbName = "ColecoSystem";
        String dbUser = "colecouser";
        String dbPass = "colecopass";
        String os = System.getProperty("os.name");

        if(os.equals("Linux")){
            execProgram = "/bin/bash";
            execParam = "-c";
        }
        String executeCmd = "mysqldump -u " + dbUser + " -p" + dbPass + " " + dbName+ " > " + saveFile;
        String[] cmd = {execProgram, execParam, executeCmd};
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ignored) {

        }

    }
    public ResultSet execQuery(String query){
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);

        } catch (SQLException e) {
            return null;
        }
    }

    public int executeUpdate(String query){
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query);

        } catch (SQLException e) {
            return -1;
        }

    }

    public int executeAdd(String query){
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        } catch (SQLException e) {
            return -1;
        }

    }

    public User getUser(String DNI){
        return null;
    }


    private boolean connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ColecoSystem?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "colecouser", "colecopass");
            return connection.isValid(50000);
        }catch (ClassNotFoundException | SQLException e){

            return false;
        }
    }

    public boolean getConnectionStatus(){
        return connectionStatus;
    }
}
