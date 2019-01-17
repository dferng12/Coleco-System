package viewControllers;

import entities.Student;
import entities.Subject;
import entities.Teacher;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logicControllers.DAOS.DAOAuth;
import logicControllers.DAOS.DAOUser;
import logicControllers.Students;
import logicControllers.Subjects;
import logicControllers.Teachers;
import logicControllers.Users;
import sun.applet.Main;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ResourceBundle;

public class IndexAdmin implements Initializable{

    @FXML
    private ListView<User> listusers;

    @FXML
    private ListView<Subject> listsubjects;

    @FXML
    private Button addstudenttosubject;

    @FXML
    private Button addsubject;

    @FXML
    private Button addstudent;

    @FXML
    private Button addteacher;

    @FXML
    private Button addteachertosubject;

    @FXML
    private Button logout;

    @FXML
    private Button help;

    @FXML
    private Button removeuser;

    @FXML
    private Button backup;

    private User admin;

    public void setAdmin(User admin) {
        this.admin = admin;

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Students students = new Students();
        Teachers teachers = new Teachers();
        ObservableList<User> observableList = FXCollections.observableArrayList();
        observableList.addAll(students.getAllStudents());
        observableList.addAll(teachers.getAllTeachers());
        listusers.setItems(observableList);

        Subjects subjects = new Subjects();
        ObservableList<Subject> subjectObservableList = FXCollections.observableArrayList();
        subjectObservableList.addAll(subjects.getAllSubjects());
        listsubjects.setItems(subjectObservableList);

        addstudent.setOnAction(event -> {
            Stage st =  (Stage) addstudent.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/addstudent.fxml"));
            Region root;

            try {
                root =loader.load();

                Scene scene = new Scene(root);
                st.setScene(scene);

                st.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addsubject.setOnAction(event -> {
            Stage st =  (Stage) addstudent.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/addsubject.fxml"));
            Region root;

            try {
                root =loader.load();

                Scene scene = new Scene(root);
                st.setScene(scene);

                AddSubject indexController = loader.getController();

                st.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addstudenttosubject.setOnAction(actionEvent -> {
            Stage st =  (Stage) addstudenttosubject.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/addstudenttosubject.fxml"));
            Region root;

            try {
                root =loader.load();

                Scene scene = new Scene(root);
                st.setScene(scene);

                AddStudentToSubject controller = loader.getController();

                st.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        help.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/indexHelp.fxml"));
            IndexHelp help = new IndexHelp();
            loader.setController(help);
            try {

                Scene scene = new Scene(loader.load(), 800, 600);
                Stage stage = new Stage();
                stage.setTitle("Help");
                stage.setScene(scene);
                help.setPage("indexadmin");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        addteacher.setOnAction(actionEvent -> {
            Stage st =  (Stage) addstudent.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/addteacher.fxml"));
            Region root;

            try {
                root =loader.load();

                Scene scene = new Scene(root);
                st.setScene(scene);

                st.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addteachertosubject.setOnAction(actionEvent -> {
            Stage st =  (Stage) addstudent.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/addteachertosubject.fxml"));
            Region root;

            try {
                root =loader.load();

                Scene scene = new Scene(root);
                st.setScene(scene);

                st.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });



        logout.setOnAction(event -> {
            Stage st =  (Stage) logout.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/auth.fxml"));
            Region root;

            try {
                root =loader.load();

                Scene scene = new Scene(root);
                st.setScene(scene);

                Auth auth = loader.getController();

                st.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        removeuser.setOnAction(event -> {
            Users users = new Users();
            User user = listusers.getSelectionModel().getSelectedItem();
            DAOUser dao = new DAOUser();
            //DAOAuth daoAuth = new DAOAuth(); TODO eliminar authInfo
            if(user instanceof Student){
                //daoAuth.deleteAuthInfo(user.getAuthInfo());
                dao.removeStudent((Student) user);
                listusers.getItems().remove(user);
            }else if(user instanceof Teacher){
                //daoAuth.deleteAuthInfo(user.getAuthInfo());
                dao.removeTeacher((Teacher) user);
                listusers.getItems().remove(user);
            }
        });

        backup.setOnAction(event -> {
            try {

                String dbName = "ColecoSystem";
                String dbUser = "colecouser";
                String dbPass = "colecopass";

                String savePath = "";
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory = directoryChooser.showDialog(addstudent.getScene().getWindow());
                if(selectedDirectory!=null) {
                    savePath = selectedDirectory.getAbsolutePath() + "\backup.sql";

                    /*NOTE: Used to create a cmd command*/
                    String executeCmd = "mysqldump --user " + dbUser + " --password=" + dbPass + " " + dbName + " > " + savePath;

                    /*NOTE: Executing the command here*/
                    Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                    int processComplete = runtimeProcess.waitFor();

                    /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
                    if (processComplete == 0) {
                        System.out.println("Backup Complete");
                    } else {
                        System.out.println("Backup Failure");
                    }
                }

            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });

    }
}
