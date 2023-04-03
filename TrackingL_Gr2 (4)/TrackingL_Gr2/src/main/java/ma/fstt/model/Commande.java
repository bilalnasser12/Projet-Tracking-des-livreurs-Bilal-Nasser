package ma.fstt.model;

import java.util.Date;

public class Commande {
    String id;
    String Dtdebut;
    String Dtfin;
    String km;
    public Commande(String id,String Dtdebut,String Dtfin,String km){
        this.id=id;
        this.Dtdebut=Dtdebut;
        this.Dtfin=Dtfin;
        this.km=km;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDtdebut() {
        return Dtdebut;
    }

    public void setDtdebut(String dtdebut) {
        Dtdebut = dtdebut;
    }

    public String getDtfin() {
        return Dtfin;
    }

    public void setDtfin(String dtfin) {
        Dtfin = dtfin;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }
}
