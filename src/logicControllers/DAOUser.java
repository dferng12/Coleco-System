package logicControllers;

import entities.DNI;
import entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DAOUser extends DAO {
    public String identify(String username) {

        String queryAdmin = "IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'admin')";
        ResultSet resultAdmin = execQuery(queryAdmin);
        String queryTeacher = "IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'teacher')";
        ResultSet resultTeacher = execQuery(queryTeacher);
        String queryStudent = "IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'student')";
        ResultSet resultStudent = execQuery(queryStudent);

        try {

            if (resultAdmin.next() && resultAdmin.getInt(1) != 1) {
                createTable("admin");
            } else if (resultTeacher.next() && resultTeacher.getInt(1) != 1) {
                createTable("teacher");
            } else if (resultStudent.next() && resultStudent.getInt(1) != 1) {
                createTable("student");
            }

            queryAdmin = "SELECT COUNT(*) FROM admin WHERE username=\"" +username+"";
            queryTeacher = "SELECT COUNT(*) FROM teacher WHERE username=\"" +username+"";
            queryStudent = "SELECT COUNT(*) FROM teacher WHERE username=\"" +username+"";
            if(resultAdmin.next() && resultAdmin.getInt(1) == 1 ){
                return "admin";
            }else if(resultTeacher.next() && resultTeacher.getInt(1) == 1 ){
                return "teacher";
            }else if(resultStudent.next() && resultStudent.getInt(1) == 1 ) {
                return "student";
            }

        } catch (SQLException e) {
            logger.error("ERROR IN SQL QUERY");
            logger.debug(e.getMessage());
        }

        return "error";
    }

    private void createTable(String type) {
        String query = null;

        try {
            if (type.equals("admin")) {
                logger.error("TABLE ADMIN NOT EXIST");
                query = "CREATE TABLE admin (" +
                        "username VARCHAR(20) PRIMARY KEY);";
                Statement stat = connection.createStatement();
                int log = stat.executeUpdate(query);
                logger.debug("TABLE ADMIN CREATED WITH" + log + " RECORDS AFFECTED");

            } else if (type.equals("teacher")) {
                logger.error("TABLE TEACHER NOT EXIST");
                query = "CREATE TABLE teacher (" +
                        "username VARCHAR(20) NOT NULL," +
                        "name VARCHAR(20) NOT NULL," +
                        "subname VARCHAR(45) NOT NULL," +
                        "dni VARCHAR(9) NOT NULL," +
                        "age INT NOT NULL," +
                        "email VARCHAR(50) NOT NULL," +
                        "phoneNumber CHAR(10) NOT NULL," +
                        "address VARCHAR(100) NOT NULL," +
                        "PRIMARY KEY(dni));";
                Statement stat = connection.createStatement();
                int log = stat.executeUpdate(query);
                logger.debug("TABLE TEACHER CREATED WITH" + log + " RECORDS AFFECTED");


            } else if (type.equals("student")) {
                logger.error("TABLE STUDENT NOT EXIST");
                query = "CREATE TABLE student (" +
                        "username VARCHAR(20) NOT NULL," +
                        "name VARCHAR(20) NOT NULL," +
                        "subname VARCHAR(45) NOT NULL," +
                        "dni VARCHAR(9) NOT NULL," +
                        "age INT NOT NULL," +
                        "email VARCHAR(50) NOT NULL," +
                        "phoneNumber CHAR(10) NOT NULL," +
                        "address VARCHAR(100) NOT NULL," +
                        "PRIMARY KEY(dni));";
                Statement stat = connection.createStatement();
                int log = stat.executeUpdate(query);
                logger.debug("TABLE STUDENT CREATED WITH" + log + " RECORDS AFFECTED");

            }


        } catch (SQLException e) {
            logger.error("ERROR IN SQL QUERY");
            logger.debug(e.getMessage());


        }
    }
}