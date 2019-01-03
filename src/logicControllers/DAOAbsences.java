package logicControllers;

import entities.Absence;
import entities.Student;
import entities.Subject;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DAOAbsences extends DAO{

    public DAOAbsences(){
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
        String query = "SELECT absences.ab_date, absences.reason, absences.penalty FROM absences_subject_students" +
                " INNER JOIN absences ON absences.id = absences_subject_students.id " +
                "INNER JOIN subjects ON subjects.name = absences_subject_students.name" +
                " INNER JOIN students ON students.dni = absences_subject_students.dni " +
                "WHERE students.dni = \"" + student.getDni().toString() + "\" " +
                "AND subjects.name = \"" + subject.getName() + "\"";

        ResultSet resultSet = execQuery(query);

        if(resultSet == null) return;

        while(resultSet.next()){
            Absence absence = new Absence(resultSet.getDate("absences.ab_date"), resultSet.getString("absences.reason"), resultSet.getInt("absences.penalty"));
            subject.addAbsence(student, absence);
        }
    }

    public void addAbsence(Absence absence){;
        String query = "INSERT INTO absences(date, reason, penalty) VALUES(\"" + absence.getDate().toString()  +"\", \"" + absence.getReason()+ "\", \"" + String.valueOf(absence.getPenalty()) + "\");";
        executeUpdate(query);
    }
}