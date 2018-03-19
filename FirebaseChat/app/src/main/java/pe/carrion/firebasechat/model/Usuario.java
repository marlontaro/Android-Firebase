package pe.carrion.firebasechat.model;

import com.orm.SugarRecord;

/**
 * Created by Marlon Cordova on 18/03/2018.
 */

public class Usuario extends SugarRecord {

    private String name;

    private String mail;
    private String socialid;
    private String socialtoken;
    private String firebase;
    private int tipo;

    public Usuario(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSocialid() {
        return socialid;
    }

    public void setSocialid(String socialid) {
        this.socialid = socialid;
    }

    public String getSocialtoken() {
        return socialtoken;
    }

    public void setSocialtoken(String socialtoken) {
        this.socialtoken = socialtoken;
    }

    public String getFirebase() {
        return firebase;
    }

    public void setFirebase(String firebase) {
        this.firebase = firebase;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
