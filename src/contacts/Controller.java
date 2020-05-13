package contacts;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ContactData contactData = new ContactData();
        contactData.loadContacts();
        contacts.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        contacts.getItems().setAll(contactData.getContacts());
    }
}
