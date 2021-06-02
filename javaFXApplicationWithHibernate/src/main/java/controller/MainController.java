package controller;

import dao.DAO;
import dao.DaoImpl;
import entity.AccessLevel;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    DAO dao = new DaoImpl();

    @FXML
    private Button exitButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, Long> idColumn;
    @FXML
    private TableColumn<User, String> loginColumn;
    @FXML
    private TableColumn<User, String> passwordColumn;
    @FXML
    private TableColumn<User, AccessLevel> accessLvlColumn;
    @FXML
    private TableColumn<User, LocalDateTime> dateOfCreationColumn;
    @FXML
    private TableColumn<User, LocalDateTime> dateOfModificationColumn;

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showUsers(getUsersList());
    }

    public ObservableList<User> getUsersList() {
        return dao.findAll();
    }

    public void showUsers(ObservableList<User> list) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        accessLvlColumn.setCellValueFactory(new PropertyValueFactory<>("accessLvl"));
        dateOfCreationColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfCreation"));
        dateOfModificationColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfModification"));

        tableView.setItems(list);
    }

    @FXML
    private void helpInfo() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/info.fxml"));

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Информация");
        stage.show();
    }

    @FXML
    private void exitButton() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
