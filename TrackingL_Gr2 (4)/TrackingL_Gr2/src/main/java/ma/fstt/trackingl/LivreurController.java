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
import ma.fstt.model.Livreur;
import ma.fstt.model.Produit;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LivreurController implements Initializable {
    Connection cnx;
    public PreparedStatement st;
    public ResultSet result;
    @FXML
    private Button btnbackFFF;

    @FXML
    private TextField chercherFFF;

    @FXML
    private TableColumn<Livreur, String> clnidlivreurFF;

    @FXML
    private TableColumn<Livreur, String> clnnomFF;

    @FXML
    private TableColumn<Livreur, String> clntelephoneFF;

    @FXML
    private TableView<Livreur> tableFFF;

    @FXML
    private TextField textnomFF;

    @FXML
    private TextField texttelephoneFF;
    @FXML
    private AnchorPane AnchorPaneFFFF;

    @FXML
    void addlivreur() {
        String idl = chercherFFF.getText();
        String noml = textnomFF.getText();
        String telephone = texttelephoneFF.getText();
        String sql="insert into livreur(Id_Livreur,Nom,Telephone) values(?,?,?)";
        if(!idl.equals("")&&!noml.equals("")&&!telephone.equals("")){
            try {
                st=cnx.prepareStatement(sql);
                st.setString(1,idl);
                st.setString(2,noml);
                st.setString(3,telephone);
                st.execute();
                chercherFFF.setText("");
                textnomFF.setText("");
                texttelephoneFF.setText("");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Livreur ajouté avec succès!", ButtonType.OK);
                alert.showAndWait();
                showLivreur();
            } catch (SQLException e) {
                e.printStackTrace();
            }}
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Remplir les champs", ButtonType.OK);
            alert.showAndWait();
        }

    }
private Parent fxml;
    @FXML
    void btnbackkk() {
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

    @FXML
    void chercherlivreur() {
        String sql="select Id_Livreur,Nom,Telephone from livreur where Id_Livreur='"+chercherFFF.getText()+"'";
        int m=0;
        try {
            st= cnx.prepareStatement(sql);
            result= st.executeQuery();
            if(result.next()){
                textnomFF.setText(result.getString("Nom"));
                texttelephoneFF.setText(result.getString("Telephone"));
                m=1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(m==0){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Aucun Id livreur avec Id_Livreur = "+chercherFFF.getText()+"", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    void deletelivreur() {
        String sql = "delete from livreur where Id_Livreur = '"+chercherFFF.getText()+"'";
        try {
            st=cnx.prepareStatement(sql);
            st.executeUpdate();
            chercherFFF.setText("");
            textnomFF.setText("");
            texttelephoneFF.setText("");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Livreur supprime avec succès!", ButtonType.OK);
            alert.showAndWait();
            showLivreur();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void tablelivreurEvent() {
        Livreur livreur= tableFFF.getSelectionModel().getSelectedItem();
        String sql="select * from livreur where Id_Livreur=?";
        try {
            st=cnx.prepareStatement(sql);
            st.setString(1, livreur.getIdl());
            result=st.executeQuery();
            if(result.next()){
                textnomFF.setText(result.getString("Nom"));
                texttelephoneFF.setText(result.getString("Telephone"));
                chercherFFF.setText(result.getString("Id_Livreur"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updatelivreur() {
        String idl = chercherFFF.getText();
        String noml = textnomFF.getText();
        String telephone = texttelephoneFF.getText();
        String sql1 = "update livreur set Id_Livreur=?,Nom=?,Telephone=? where Id_Livreur = '"+chercherFFF.getText()+"'";
        if(!idl.equals("")&&!noml.equals("")&&!telephone.equals("")){
            try {
                st= cnx.prepareStatement(sql1);
                st.setString(1,idl);
                st.setString(2,noml);
                st.setString(3,telephone);
                st.executeUpdate();
                chercherFFF.setText("");
                textnomFF.setText("");
                texttelephoneFF.setText("");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Livreur modifié avec succès!", ButtonType.OK);
                alert.showAndWait();
                showLivreur();
            } catch (SQLException e) {
                e.printStackTrace();
            }}
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Remplir les champs", ButtonType.OK);
            alert.showAndWait();
        }
    }
    public ObservableList<Livreur> data = FXCollections.observableArrayList();
    public void showLivreur(){
        tableFFF.getItems().clear();
        String sql = "select * from livreur";
        try {
            st=cnx.prepareStatement(sql);
            result=st.executeQuery();
            while(result.next()){
                data.add(new Livreur(result.getString("Id_Livreur"),result.getString("Nom"),result.getString("Telephone")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        clnidlivreurFF.setCellValueFactory(new PropertyValueFactory<Livreur,String>("idl"));
        clnnomFF.setCellValueFactory(new PropertyValueFactory<Livreur,String>("noml"));
        clntelephoneFF.setCellValueFactory(new PropertyValueFactory<Livreur,String>("telephone"));
        tableFFF.setItems(data);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cnx = ConnexionMysql.connectionDB();

        showLivreur();
    }
}
