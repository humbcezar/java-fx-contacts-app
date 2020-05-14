package contacts;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private BorderPane borderPane;
    @FXML
    private TableView<Contact> contacts;
    @FXML
    private TableColumn<Contact, String> firstName;
    @FXML
    private TableColumn<Contact, String> lastName;
    @FXML
    private TableColumn<Contact, String> phoneNumber;
    @FXML
    private TableColumn<Contact, String> notes;

    private ContactData contactData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactData = new ContactData();
        contactData.loadContacts();
        contacts.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        contacts.setItems(contactData.getContacts());
    }

    public void showAddDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(borderPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DialogController dialogController = fxmlLoader.getController();
            dialogController.addContact(this.contactData);
        }
    }

    public ContactData getContactData() {
        return contactData;
    }
}
