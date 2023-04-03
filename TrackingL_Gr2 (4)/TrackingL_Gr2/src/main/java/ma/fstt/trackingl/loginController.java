package ma.fstt.trackingl;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ma.fstt.model.ConnexionMysql;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    Connection cnx;
    public PreparedStatement st;
    public ResultSet result;
    @FXML
    private Button buttonloginF;

    @FXML
    private PasswordField passwordF;

    @FXML
    private TextField usernameF;
    @FXML
    private BorderPane BorderPaneF;
    @FXML
    private Label textF;
    private Parent fxml;

    @FXML
    void openlogin() {
        String nom = usernameF.getText();
        String pass = passwordF.getText();
        String sql="Select username,password from admins";
        try {
            st= cnx.prepareStatement(sql);
            result=st.executeQuery();
            if(result.next()){
                if(nom.equals(result.getString("username")) && pass.equals(result.getString("password"))) {
                    BorderPaneF.getScene().getWindow().hide();
                    Stage home = new Stage();
                    try {
                        fxml = FXMLLoader.load(getClass().getResource("Home.fxml"));
                        Scene scene = new Scene(fxml);
                        home.setScene(scene);
                        home.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    textF.setText("Username or/And Password are incorrect!!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cnx = ConnexionMysql.connectionDB();

    }
}
