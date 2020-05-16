package contacts;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DialogController {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField notes;

    public TextField getFirstName() {
        return firstName;
    }

    public TextField getLastName() {
        return lastName;
    }

    public TextField getPhoneNumber() {
        return phoneNumber;
    }

    public TextField getNotes() {
        return notes;
    }


    public void addContact(ContactData contactData) {
        contactData.addContact(
            firstName.getText().trim(),
            lastName.getText().trim(),
            phoneNumber.getText().trim(),
            notes.getText().trim()
        );
    }

    public void editContact(Contact contact) {
        contact.setFirstName(firstName.getText().trim());
        contact.setLastName(lastName.getText().trim());
        contact.setPhoneNumber(phoneNumber.getText().trim());
        contact.setNotes(notes.getText().trim());
    }
}
