package controller;

import entity.User;
import javafx.scene.control.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JavafxView extends Application {

    private TextField login, password, dateOfCreation, dateOfModification;
    private ComboBox accessLvl;
    private Button savebtn;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX 8 Tutorial 60 - JavaFX Hibernate Integration");

        Group root = new Group();
        Scene scene = new Scene(root, 400, 300);

        login = new TextField();
        login.setTooltip(new Tooltip("Enter First Name"));
        login.setFont(Font.font("SanSerif", 15));
        login.setPromptText("First Name");
        login.setMaxWidth(200);

        password = new TextField();
        password.setTooltip(new Tooltip("Enter Last Name"));
        password.setFont(Font.font("SanSerif", 15));
        password.setPromptText("Last Name");
        password.setMaxWidth(200);

/*        accessLvl = new TextField();
        accessLvl.setTooltip(new Tooltip("Enter Email"));
        accessLvl.setFont(Font.font("SanSerif", 15));
        accessLvl.setPromptText("Email");
        accessLvl.setMaxWidth(200);*/

        //Hibernate Configuration
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();

        User user = new User();

        savebtn = new Button("Save");
        savebtn.setTooltip(new Tooltip("Save the User Details"));
        savebtn.setFont(Font.font("SanSerif", 15));
        savebtn.setOnAction(e ->{
            user.setLogin(login.getText());
            user.setPassword(password.getText());
/*            user.setAccessLvl(email.getText());
            user.setDate(date.getEditor().getText());
            user.setMobileNo(mobileNo.getText());*/

            Session session = sf.openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();

          //  clearFields();

        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(login, password, savebtn);
        vbox.setPadding(new Insets(10));
        root.getChildren().add(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

/*    private void clearFields() {
        // TODO Auto-generated method stub
        fName.clear();
        lName.clear();
        email.clear();
        mobileNo.clear();
        date.getEditor().setText(null);
        date.setValue(null);
    }*/

    public static void main(String[] args) {
        launch(args);
    }
}
