package controller;

import dao.DAO;
import dao.DaoImpl;
import entity.AccessLevel;
import entity.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.net.URL;
import java.util.ResourceBundle;

public class SecondController implements Initializable {
    private DAO dao = new DaoImpl();
    // Для валидации полей ValidationSupport
    private ValidationSupport validationSupport = new ValidationSupport();

    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField idField;
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;;
    @FXML
    private ComboBox<AccessLevel> accessLvlField;
    @FXML
    private TextField dateOfCreation;
    @FXML
    private TextField dateOfModification;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxAccessLvl();
        setupValidation();
    }

    public void setupValidation() {
        // validationSupport.setErrorDecorationEnabled(true);
        validationSupport.registerValidator(loginField, Validator.createEmptyValidator("Поле логина не может быть пустым"));
        validationSupport.registerValidator(passwordField, Validator.createEmptyValidator("Поле пароля не может быть пустым"));
        validationSupport.registerValidator(accessLvlField, Validator.createEmptyValidator("Поле роли не может быть пустым"));
    }
    public void comboBoxAccessLvl() {
        ObservableList<AccessLevel> list = dao.getAccessLevelList();
        accessLvlField.setItems(list);
    }

    @FXML
    private void cancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void insertOrUpdateUser() {
        Stage stage = (Stage) okButton.getScene().getWindow();

        if (loginField.getText().isEmpty() || passwordField.getText().isEmpty() || accessLvlField.getValue() == null) {
            showAlertWithDefaultHeaderText();
        } else {
            if (idField.getText().isEmpty()) {
                dao.insertUser(loginField.getText(), passwordField.getText(), accessLvlField.getValue());
            } else {
                dao.updateUser(Long.parseLong(idField.getText()), loginField.getText(), passwordField.getText(), accessLvlField.getValue());
            }
            stage.close();
        }
    }

    public void preloadData(User user) {
        idField.setText(String.valueOf(user.getId()));
        loginField.setText(user.getLogin());
        passwordField.setText(user.getPassword());
        accessLvlField.setValue(user.getAccessLvl());
        dateOfCreation.setText(String.valueOf(user.getDateOfCreation()));
        dateOfModification.setText(String.valueOf(user.getDateOfModification()));
    }

    private void showAlertWithDefaultHeaderText() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Статус");
        alert.setContentText("Данные заполнены некорректно");

        alert.showAndWait();
    }
}
