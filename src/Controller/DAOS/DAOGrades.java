package Controller.DAOS;

import Model.Grade;
import Model.Student;
import Model.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOGrades extends DAO{

    public void init(){
        String query  = "CREATE TABLE IF NOT EXISTS grades (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "grade INT," +
                        "percentage INT);";

        executeUpdate(query);

        query = "CREATE TABLE IF NOT EXISTS grades_subject_students(" +
                "id INT," +
                "name varchar(60)," +
                "dni varchar(9));";
        executeUpdate(query);
    }

    public void fk(){
        String query = "ALTER TABLE grades_subject_students ADD CONSTRAINT fk_grades_subject_students FOREIGN KEY (id) REFERENCES grades(id)";
        executeUpdate(query);

        query = "ALTER TABLE grades_subject_students ADD CONSTRAINT fk_grades_subject_students2 FOREIGN KEY (name) REFERENCES subjects(name)";
        executeUpdate(query);

        query = "ALTER TABLE grades_subject_students ADD CONSTRAINT fk_grades_subject_students3 FOREIGN KEY (dni) REFERENCES students(dni)";
        executeUpdate(query);
    }

    public void getGrades(Student student, Subject subject) throws SQLException {
        String query = "SELECT grades.id, grades.grade, grades.percentage FROM grades_subject_students" +
                " INNER JOIN grades ON grades.id = grades_subject_students.id " +
                "INNER JOIN subjects ON subjects.name = grades_subject_students.name" +
                " INNER JOIN students ON students.dni = grades_subject_students.dni " +
                "WHERE students.dni = \"" + student.getDni().toString() + "\" " +
                "AND subjects.name = \"" + subject.getName() + "\"";

        ResultSet resultSet = execQuery(query);

        if(resultSet == null) return;

        while(resultSet.next()){
            Grade grade = new Grade(resultSet.getInt("grade"), resultSet.getInt("percentage"));
            grade.setId(resultSet.getInt("grades.id"));
            subject.addGrade(student, grade);
        }
    }

    public void addGrade(Grade grade){
        String query = "INSERT INTO grades(grade, percentage) VALUES(" + grade.getValue()+", " + grade.getPercentage() + ");";
        int id = executeAdd(query);
        grade.setId(id);
    }

    public void addGradeToSubject(Subject subject, Grade grade, Student student){
        String query = "INSERT INTO grades_subject_students(id, name, dni) VALUES(" + grade.getId() + ",'" + subject.getName() + "','" + student.getDni().toString() + "');";
        executeUpdate(query);
    }

    public void getGradesFromSubject(Subject subject) {
        DAOUser daoUser = new DAOUser();
        try {
            for(Student student: daoUser.getStudentsFromSubject(subject)){
                getGrades(student, subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGradeFromStudent(Subject subject, Student student, Grade grade){
        String query = "DELETE FROM grades_subject_students WHERE id = " + grade.getId() + " AND name = '" + subject.getName() + "' AND dni = '" + student.getDni().toString();
        executeUpdate(query);
    }

    public void deleteGrade(Grade grade){
        String query = "DELETE FROM grades WHERE id =" + grade + ";";
        executeUpdate(query);
    }
}
