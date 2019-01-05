package logicControllers.DAOS;

import entities.Absence;
import entities.Grade;
import entities.Student;
import entities.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOAbsences extends DAO{

    public void init(){
        String query  = "CREATE TABLE IF NOT EXISTS absences (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "ab_date DATE," +
                "reason varchar(200)," +
                "penalty int);";

        executeUpdate(query);

        query = "CREATE TABLE IF NOT EXISTS absences_subject_students(" +
                "id INT," +
                "name varchar(60)," +
                "dni varchar(9));";
        executeUpdate(query);
    }

    public void fk(){
        String query = "ALTER TABLE absences_subject_students ADD CONSTRAINT fk_absences_subject_students FOREIGN KEY (id) REFERENCES grades(id)";
        executeUpdate(query);

        query = "ALTER TABLE absences_subject_students ADD CONSTRAINT fk_absences_subject_students2 FOREIGN KEY (name) REFERENCES subjects(name)";
        executeUpdate(query);

        query = "ALTER TABLE absences_subject_students ADD CONSTRAINT fk_absences_subject_students3 FOREIGN KEY (dni) REFERENCES students(dni)";
        executeUpdate(query);
    }

    public void getAbsences(Student student, Subject subject) throws SQLException {
        String query = "SELECT absences.id, absences.ab_date, absences.reason, absences.penalty FROM absences_subject_students" +
                " INNER JOIN absences ON absences.id = absences_subject_students.id " +
                "INNER JOIN subjects ON subjects.name = absences_subject_students.name" +
                " INNER JOIN students ON students.dni = absences_subject_students.dni " +
                "WHERE students.dni = \"" + student.getDni().toString() + "\" " +
                "AND subjects.name = \"" + subject.getName() + "\"";

        ResultSet resultSet = execQuery(query);

        if(resultSet == null) return;

        while(resultSet.next()){
            Absence absence = new Absence(resultSet.getDate("absences.ab_date"), resultSet.getString("absences.reason"), resultSet.getInt("absences.penalty"));
            absence.setId(resultSet.getInt("absences.id"));
            subject.addAbsence(student, absence);
        }
    }

    public void addAbsence(Absence absence){
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String query = "INSERT INTO absences(ab_date, reason, penalty) VALUES(\"" + sdf.format(absence.getDate())  +"\", \"" + absence.getReason()+ "\", \"" + String.valueOf(absence.getPenalty()) + "\");";
        int id = executeAdd(query);
        absence.setId(id);
    }

    public void addGradeToSubject(Subject subject, Absence absence, Student student){
        String query = "INSERT INTO absences_subject_students(id, name, dni) VALUES('" + absence.getId() + "','" + subject.getName() + "','" + student.getDni().toString() + "');";
        executeUpdate(query);
    }

    public void getAbsencesFromSubject(Subject subject){
        DAOUser daoUser = new DAOUser();
        try {
            for(Student student: daoUser.getStudentsFromSubject(subject)){
                getAbsences(student, subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAbsencesFromStudent(Subject subject, Student student, Absence absence){
        String query = "DELETE FROM grades_absences_students WHERE id = " + absence.getId() + " AND name = '" + subject.getName() + "' AND dni = '" + student.getDni().toString();
        executeUpdate(query);
    }

    public void deleteAbsence(Absence absence){
        String query = "DELETE FROM absences WHERE id = " + absence.getId() + ";";
        executeUpdate(query);
    }

}
