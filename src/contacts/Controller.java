package contacts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
        contacts.getSelectionModel().select(0);
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

    public void showEditDialog() {
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
        Contact selected = contacts.getSelectionModel().getSelectedItem();
        DialogController dialogController = fxmlLoader.getController();

        dialogController.getFirstName().setText(selected.getFirstName());
        dialogController.getLastName().setText(selected.getLastName());
        dialogController.getPhoneNumber().setText(selected.getPhoneNumber());
        dialogController.getNotes().setText(selected.getNotes());

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            dialogController.editContact(selected);
            contacts.refresh();
        }
    }

    public void showDeleteDialog() {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.initOwner(borderPane.getScene().getWindow());
        confirmationDialog.setTitle("Delete contact");
        confirmationDialog.setContentText("Are you sure you want to delete the selected contact?");
        confirmationDialog.getDialogPane().setMinWidth(borderPane.getScene().getWindow().getWidth() / 2);
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        Contact selected = contacts.getSelectionModel().getSelectedItem();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            contactData.deleteContact(selected);
        }
    }

    public ContactData getContactData() {
        return contactData;
    }

}
