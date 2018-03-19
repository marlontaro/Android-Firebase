package pe.carrion.firebasechat.network;

import pe.carrion.firebasechat.model.Usuario;

/**
 * Created by Marlon Cordova on 18/03/2018.
 */

public class DemoApi {
    //http://nanamia-001-site1.etempurl.com
    public static String USUARIO_URL = "http://ryuktaro-001-site40.itempurl.com/api/usuario";
    public static String MENSAJE_URL = "http://ryuktaro-001-site40.itempurl.com/api/mensaje";


    private Usuario currentUser;


    public Usuario getCurrentUsuario() {
        return currentUser;
    }

    public void setCurrentUsuario(Usuario currentUser) {
        this.currentUser = currentUser;
    }
}
