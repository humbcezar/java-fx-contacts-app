package contacts;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Controller mainController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("contacts.fxml"));
        Parent root = fxmlLoader.load();
        this.mainController = fxmlLoader.getController();
        primaryStage.setTitle("Contacts");
        primaryStage.setScene(new Scene(root, 975, 575));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        this.mainController.getContactData().saveContacts();
    }
}
