package ma.fstt.model;

public class Produit {
    String idp;
    String nom;
    String prix;
    String quantite;
    public Produit(String idp,String nom,String prix,String quantite){
        this.idp=idp;
        this.nom=nom;
        this.prix=prix;
        this.quantite=quantite;
    }

    public String getIdp() {
        return idp;
    }

    public void setIdp(String idp) {
        this.idp = idp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }
}
