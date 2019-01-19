package Controller;

import java.util.ArrayList;
import java.util.List;

public class Help {
    private String page;
    private String text;
    private List<String> index = new ArrayList<String>();;

    public Help(){
        index.add("addstudent");
        index.add("addstudenttosubject");
        index.add("addsubject");
        index.add("addteacher");
        index.add("addteachertosubject");
        index.add("auth");
        index.add("indexstudent");
        index.add("indexteacher");
        index.add("messages");
        index.add("message");
        index.add("messageview");
        index.add("removestudentfromsubject");
        index.add("subjectstudent");
        index.add("subjectteacher");
        index.add("removeteacherfromsubject");
        index.add("studentinfo");
        index.add("changepassword");

    }

    public List<String> getIndex() {
        return index;
    }

    public String getText(String page){
        if(page.equals("indexadmin")){
            return "Ventana principal del administrador \n" +
                    "En ésta página se tiene control total al sistema, se pueden añadir y borrar alumnos, " +
                    "profesores y asignaturas, tambien podemos ver todos los alumnos y asignaturas existentes en el sistema" +
                    "y añadir alumnos y profesores a las asignaturas. ";

        }else if(page.equals("addstudent")){
            return "Ventana para añadir un estudiante al sistema. \n" +
                    "Únicamente tiene acceso a ella el administrador." +
                    "Recuerde que debe introducirse un DNI válido.";

        }else if(page.equals("addstudenttosubject")){
            return "Ventana para añdir un estudiante a una asignatura. \n" +
                    "Haga clic en la asignatura y estudiantes deseados y presione el botón añadir para añadirlos.";

        }else if(page.equals("addsubject")){
            return"Ventana para añadir una asignatura al sistema. \n" +
                    "Únicamente tiene acceso a ella el administrador." +
                    "Recuerde que no pueden existir dos asignaturas con nombres idénticos.";

        }else if(page.equals("addteacher")){
            return "Ventana para añadir un profesor al sistema \n" +
                    "Únicamente tiene acceso a ella el adminisitrador.";

        }else if(page.equals("addteachertosubject")){
            return "Ventana para añadir un profesor a una asignatura. \n" +
                    "Haga clic en el profesor y en la asignatura y presione el botón de añadir.";

        }else if(page.equals("auth")){
            return "Ventana de login. \n" +
                    "Ingrese su usuario y contraseña para acceder al sistema.";

        }else if(page.equals("indexstudent")){
            return "Ventana principal del alumno. \n" +
                    "En ésta ventana se tiene acceso a las notas del alumno y a los mensajes que puede recibir del profesor." +
                    "Los alumnos pueden ver sus asignaturas y sus faltas de asistencia.";

        }else if(page.equals("indexteacher")){
            return "Ventana principal del profesor. \n" +
                    "En esta ventana se tiene acceso a las asignaturas que cursa el profesor, así como al servicio de mensajería.";

        }else if(page.equals("messages")){
            return "Ventana de mensajería. \n" +
                    "En ésta ventana los alumnos pueden ver la bandeja de entrada de los mensajes que han recibido y los profesores pueden elegir enviar un mensaje a un alumno.";

        }else if(page.equals("messageview")){
            return "Ventana de mensaje. \n " +
                    "El profesor puede redactar un mensaje y enviarselo a un alumno.";

        }else if(page.equals("subjectstudent")) {
            return "Ventana de asignaturas. \n" +
                    "El alumno puede ver sus asignaturas y sus notas.";

        }else if(page.equals("subjectsteacher")) {
            return "Ventana de asignatura.\n" +
                    "El profesor puede ver los alumnos de sus asignaturas y añadirles notas y ausencias.";

        }else if(page.equals("removestudentfromsubject")){
             return "Ventana de borrado de alumno de asignatura.\n" +
                "El administrador puede expulsar a un alumno de la asignatura elegida";

        }else if(page.equals("removeteacherfromsubject")){
            return "Ventana de borrado del profesor de una asignatura.\n" +
                    "El administrador puede expulsar a un profesor de su asignatura.";

        }else if(page.equals("studentinfo")){
            return "Ventana de información del alumno\n" +
                    "En esta ventana se muestra toda la información referida al alumno, donde puede consultar sus notas finales";
        }else if(page.equals("message")){
            return "Ventana de mensaje\n" +
                    "En esta ventana el alumno puede leer el mensaje recibido";
        }else if(page.equals("changepassword")){
            return "Ventana de cambio de contraseña.\n" +
                    "En esta ventana el alumno o profesor puede cambiar su contraseña de acceso.";

        }else
            return "error de página";
        }
}
