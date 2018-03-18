package pe.carrion.firebasechat;

/**
 * Created by CARRION on 18/03/2018.
 */

public class Mensaje {

    private String Mensaje;
    private String UrlFoto;
    private String Nombre;
    private String FotoPerfil;
    private String TypeMensaje;
    private String Hora;

    public Mensaje() {
    }

    public Mensaje(String mensaje, String nombre, String fotoPerfil, String typeMensaje, String hora) {
        Mensaje = mensaje;
        Nombre = nombre;
        FotoPerfil = fotoPerfil;
        TypeMensaje = typeMensaje;
        Hora = hora;
    }

    public Mensaje(String mensaje, String urlFoto, String nombre, String fotoPerfil, String typeMensaje, String hora) {
        Mensaje = mensaje;
        UrlFoto = urlFoto;
        Nombre = nombre;
        FotoPerfil = fotoPerfil;
        TypeMensaje = typeMensaje;
        Hora = hora;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getUrlFoto() {
        return UrlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        UrlFoto = urlFoto;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getFotoPerfil() {
        return FotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        FotoPerfil = fotoPerfil;
    }

    public String getTypeMensaje() {
        return TypeMensaje;
    }

    public void setTypeMensaje(String typeMensaje) {
        TypeMensaje = typeMensaje;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }
}
