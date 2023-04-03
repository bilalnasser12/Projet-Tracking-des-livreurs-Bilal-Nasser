package ma.fstt.trackingl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ma.fstt.model.Commande;
import ma.fstt.model.ConnexionMysql;
import ma.fstt.model.Produit;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProduitController implements Initializable {
    Connection cnx;
    public PreparedStatement st;
    public ResultSet result;
    @FXML
    private Button btnbackFF;

    @FXML
    private TextField chercherFF;

    @FXML
    private TableColumn<Produit, String> clnidproduitF;

    @FXML
    private TableColumn<Produit, String> clnnomF;

    @FXML
    private TableColumn<Produit, String> clnprixF;

    @FXML
    private TableColumn<Produit, String> clnquantiteF;

    @FXML
    private TableView<Produit> tableFF;

    @FXML
    private TextField textnomFF;

    @FXML
    private TextField textprixFF;

    @FXML
    private TextField textquantiteFF;
    @FXML
    private Pane AnchorPaneFFFF;

    public ObservableList<Produit> data = FXCollections.observableArrayList();

    @FXML
    void addproduit() {

        String idp = chercherFF.getText();
        String nom = textnomFF.getText();
        String prix = textprixFF.getText();
        String quantite = textquantiteFF.getText();
        String sql="insert into produit(Id_Produit,Nom,Prix,Quantite) values(?,?,?,?)";
        if(!idp.equals("")&&!nom.equals("")&&!prix.equals("")&&!quantite.equals("")){
            try {
                st=cnx.prepareStatement(sql);
                st.setString(1,idp);
                st.setString(2,nom);
                st.setString(3,prix);
                st.setString(4,quantite);
                st.execute();
                chercherFF.setText("");
                textnomFF.setText("");
                textprixFF.setText("");
                textquantiteFF.setText("");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Commande ajouté avec succès!", ButtonType.OK);
                alert.showAndWait();
                showProduit();
            } catch (SQLException e) {
                e.printStackTrace();
            }}
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Remplir les champs", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    void chercherproduit() {
        String sql="select Id_Produit,Nom,Prix,Quantite from produit where Id_Produit='"+chercherFF.getText()+"'";
        int m=0;
        try {
            st= cnx.prepareStatement(sql);
            result= st.executeQuery();
            if(result.next()){
                textnomFF.setText(result.getString("Nom"));
                textprixFF.setText(result.getString("Prix"));
                textquantiteFF.setText(result.getString("Quantite"));
                m=1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(m==0){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Aucun Id produit avec Id_Produit = "+chercherFF.getText()+"", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    void deleteproduit() {
        String sql = "delete from produit where Id_Produit = '"+chercherFF.getText()+"'";
        try {
            st=cnx.prepareStatement(sql);
            st.executeUpdate();
            chercherFF.setText("");
            textnomFF.setText("");
            textprixFF.setText("");
            textquantiteFF.setText("");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Produit supprime avec succès!", ButtonType.OK);
            alert.showAndWait();
            showProduit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updatecommande() {
        String idp = chercherFF.getText();
        String nom = textnomFF.getText();
        String prix = textprixFF.getText();
        String quantite = textquantiteFF.getText();
        String sql1 = "update produit set Id_Produit=?,Nom=?,Prix=?,Quantite=? where Id_Produit = '"+chercherFF.getText()+"'";
        if(!idp.equals("")&&!nom.equals("")&&!prix.equals("")&&!quantite.equals("")){
            try {
                st= cnx.prepareStatement(sql1);
                st.setString(1,idp);
                st.setString(2,nom);
                st.setString(3,prix);
                st.setString(4,quantite);
                st.executeUpdate();
                chercherFF.setText("");
                textnomFF.setText("");
                textprixFF.setText("");
                textquantiteFF.setText("");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Produit modifié avec succès!", ButtonType.OK);
                alert.showAndWait();
                showProduit();
            } catch (SQLException e) {
                e.printStackTrace();
            }}
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Remplir les champs", ButtonType.OK);
            alert.showAndWait();
        }
    }
    public void showProduit(){
        tableFF.getItems().clear();
        String sql = "select * from produit";
        try {
            st=cnx.prepareStatement(sql);
            result=st.executeQuery();
            while(result.next()){
                data.add(new Produit(result.getString("Id_Produit"),result.getString("Nom"),result.getString("Prix"),result.getString("Quantite")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        clnidproduitF.setCellValueFactory(new PropertyValueFactory<Produit,String>("idp"));
        clnnomF.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
        clnprixF.setCellValueFactory(new PropertyValueFactory<Produit,String>("prix"));
        clnquantiteF.setCellValueFactory(new PropertyValueFactory<Produit,String>("quantite"));
        tableFF.setItems(data);
    }
    @FXML
    void tableproduitEvent() {
        Produit produit = tableFF.getSelectionModel().getSelectedItem();
        String sql="select * from produit where Id_Produit=?";
        try {
            st=cnx.prepareStatement(sql);
            st.setString(1, produit.getIdp());
            result=st.executeQuery();
            if(result.next()){
                textnomFF.setText(result.getString("Nom"));
                textprixFF.setText(result.getString("Prix"));
                textquantiteFF.setText(result.getString("Quantite"));
                chercherFF.setText(result.getString("Id_Produit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private Parent fxml;
    @FXML
    void btnbackk(){
        AnchorPaneFFFF.getScene().getWindow().hide();
        Stage homeee = new Stage();
        try {
            fxml = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(fxml);
            homeee.setScene(scene);
            homeee.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cnx = ConnexionMysql.connectionDB();

        showProduit();
    }
}
