import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logicControllers.DAOS.DAO;
import logicControllers.DAOS.DAOAuth;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
//count: student pass: pass
public class Main extends Application {

    private static Logger logger = Logger.getLogger(Main.class);
    @Override
    public void start(Stage primaryStage) throws Exception{
        PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/resources/log4j.properties");
        logger.warn("COLECO SYSTEM STARTED");

        DAO dao = new DAO();
        //dao.createDB();
        DAOAuth daoAuth = new DAOAuth();
        daoAuth.createAdmin();

        Parent root = FXMLLoader.load(getClass().getResource("views/auth.fxml"));
        primaryStage.setTitle("Coleco System");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
