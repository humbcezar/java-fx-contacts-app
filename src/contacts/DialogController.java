package contacts;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DialogController {
    @FXML
    public TextField firstName;
    @FXML
    public TextField lastName;
    @FXML
    public TextField phoneNumber;
    @FXML
    public TextField notes;

    public void addContact(ContactData contactData) {
        contactData.addContact(
            firstName.getText().trim(),
            lastName.getText().trim(),
            phoneNumber.getText().trim(),
            notes.getText().trim()
        );
    }
}
