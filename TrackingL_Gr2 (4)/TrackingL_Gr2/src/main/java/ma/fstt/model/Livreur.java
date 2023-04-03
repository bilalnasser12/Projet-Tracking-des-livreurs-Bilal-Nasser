package ma.fstt.model;

public class Livreur {
    String idl;
    String noml;
    String telephone;

    public Livreur(String idl, String noml, String telephone) {
        this.idl = idl;
        this.noml = noml;
        this.telephone = telephone;
    }

    public String getIdl() {
        return idl;
    }

    public void setIdl(String idl) {
        this.idl = idl;
    }

    public String getNoml() {
        return noml;
    }

    public void setNoml(String noml) {
        this.noml = noml;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
