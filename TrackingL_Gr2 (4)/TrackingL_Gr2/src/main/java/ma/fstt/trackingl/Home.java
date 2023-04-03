package ma.fstt.trackingl;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Home {
    @FXML
    private Button buttondeliveryF;

    @FXML
    private Button buttonordersF;

    @FXML
    private Button buttonproductF;

    @FXML
    private Button buttonreturnF;
    private Parent fxml;
    @FXML
    private AnchorPane borderpaneFF;

    @FXML
    void returnlogin(){
        borderpaneFF.getScene().getWindow().hide();
        Stage homee = new Stage();
        try {
            fxml = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(fxml);
            homee.setScene(scene);
            homee.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   @FXML
    void deliveryF(){
        borderpaneFF.getScene().getWindow().hide();
        Stage homeee = new Stage();
        try {
            fxml = FXMLLoader.load(getClass().getResource("Livreur.fxml"));
            Scene scene = new Scene(fxml);
            homeee.setScene(scene);
            homeee.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void ordersF(){
        borderpaneFF.getScene().getWindow().hide();
        Stage homeee = new Stage();
        try {
            fxml = FXMLLoader.load(getClass().getResource("commande.fxml"));
            Scene scene = new Scene(fxml);
            homeee.setScene(scene);
            homeee.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void produitF(){
        borderpaneFF.getScene().getWindow().hide();
        Stage homeee = new Stage();
        try {
            fxml = FXMLLoader.load(getClass().getResource("produit.fxml"));
            Scene scene = new Scene(fxml);
            homeee.setScene(scene);
            homeee.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
