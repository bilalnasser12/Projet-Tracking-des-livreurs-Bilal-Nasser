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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ma.fstt.model.Commande;
import ma.fstt.model.ConnexionMysql;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class CommandeController implements Initializable {
    Connection cnx;
    public PreparedStatement st;
    public ResultSet result;
    @FXML
    private Button bntaddF;

    @FXML
    private AnchorPane AnchorPaneFFF;

    @FXML
    private Button bntdeleteF;

    @FXML
    private Button bntupdateF;

    @FXML
    private Button btnbackF;

    @FXML
    private TextField chercherF;

    @FXML
    private TableColumn<Commande, String> clndatedebutF;

    @FXML
    private TableColumn<Commande, String> clndatefinF;

    @FXML
    private TableColumn<Commande, String> clnidcommandeF;

    @FXML
    private TableColumn<Commande, String> clnkmF;

    @FXML
    private TableView<Commande> tableF;

    @FXML
    private TextField textdatedebutF;

    @FXML
    private TextField textdatefinF;

    @FXML
    private TextField textkmF;

    public ObservableList<Commande> data = FXCollections.observableArrayList();

    @FXML
    void addcommande() {
        String id = chercherF.getText();
        String Dtdebut = textdatedebutF.getText();
        String Dtfin = textdatefinF.getText();
        String km = textkmF.getText();
        String sql="insert into commande(Id_Commande,Date_Debut,Date_Fin,Km) values(?,?,?,?)";
        if(!id.equals("")&&!Dtdebut.equals("")&&!Dtfin.equals("")&&!km.equals("")){
        try {
            st=cnx.prepareStatement(sql);
            st.setString(1,id);
            st.setString(2,Dtdebut);
            st.setString(3,Dtfin);
            st.setString(4,km);
            st.execute();
            chercherF.setText("");
            textdatedebutF.setText("");
            textdatefinF.setText("");
            textkmF.setText("");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Commande ajouté avec succès!", ButtonType.OK);
            alert.showAndWait();
            showCommande();
        } catch (SQLException e) {
            e.printStackTrace();
        }}
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Remplir les champs", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    void cherchercommande() {
        String sql="select Id_Commande,Date_Debut,Date_Fin,Km from commande where Id_Commande='"+chercherF.getText()+"'";
        int m=0;
        try {
            st= cnx.prepareStatement(sql);
            result= st.executeQuery();
            if(result.next()){
                textdatedebutF.setText(result.getString("Date_Debut"));
                textdatefinF.setText(result.getString("Date_Fin"));
                textkmF.setText(result.getString("Km"));
                m=1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(m==0){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Aucun Id commande avec Id_Commande = "+chercherF.getText()+"", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    void deletecommande() {
        String sql = "delete from commande where Id_Commande = '"+chercherF.getText()+"'";
        try {
            st=cnx.prepareStatement(sql);
            st.executeUpdate();
            chercherF.setText("");
            textdatedebutF.setText("");
            textdatefinF.setText("");
            textkmF.setText("");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Commande supprime avec succès!", ButtonType.OK);
            alert.showAndWait();
            showCommande();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updatecommande() {
        String id = chercherF.getText();
        String Dtdebut = textdatedebutF.getText();
        String Dtfin = textdatefinF.getText();
        String km = textkmF.getText();
        String sql1 = "update commande set Date_Debut=?,Date_Fin=?,Km=? where Id_Commande = '"+chercherF.getText()+"'";
        if(!id.equals("")&&!Dtdebut.equals("")&&!Dtfin.equals("")&&!km.equals("")){
        try {
            st= cnx.prepareStatement(sql1);
            st.setString(1,Dtdebut);
            st.setString(2,Dtfin);
            st.setString(3,km);
            st.executeUpdate();
            chercherF.setText("");
            textdatedebutF.setText("");
            textdatefinF.setText("");
            textkmF.setText("");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Commande modifié avec succès!", ButtonType.OK);
            alert.showAndWait();
            showCommande();
        } catch (SQLException e) {
            e.printStackTrace();
        }}
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Remplir les champs", ButtonType.OK);
            alert.showAndWait();
        }
    }
    public void showCommande(){
        tableF.getItems().clear();
        String sql = "select * from commande";
        try {
            st=cnx.prepareStatement(sql);
            result=st.executeQuery();
            while(result.next()){
                data.add(new Commande(result.getString("Id_Commande"),result.getString("Date_Debut"),result.getString("Date_Fin"),result.getString("Km")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        clnidcommandeF.setCellValueFactory(new PropertyValueFactory<Commande,String>("id"));
        clndatedebutF.setCellValueFactory(new PropertyValueFactory<Commande,String>("Dtdebut"));
        clndatefinF.setCellValueFactory(new PropertyValueFactory<Commande,String>("Dtfin"));
        clnkmF.setCellValueFactory(new PropertyValueFactory<Commande,String>("km"));
        tableF.setItems(data);
    }

    @FXML
    void tablecommandeEvent() {
        Commande commande = tableF.getSelectionModel().getSelectedItem();
        String sql="select * from commande where Id_Commande=?";
        try {
            st=cnx.prepareStatement(sql);
            st.setString(1, commande.getId());
            result=st.executeQuery();
            if(result.next()){
                textdatedebutF.setText(result.getString("Date_Debut"));
                textdatefinF.setText(result.getString("Date_Fin"));
                textkmF.setText(result.getString("Km"));
                chercherF.setText(result.getString("Id_Commande"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private Parent fxml;
    @FXML
    void btnback(){
        AnchorPaneFFF.getScene().getWindow().hide();
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

        showCommande();
    }
}
