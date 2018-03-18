package pe.carrion.firebasechat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by CARRION on 18/03/2018.
 */

public class HolderMensaje extends RecyclerView.ViewHolder {

    private TextView Nombre;
    private TextView Mensaje;
    private TextView Hora;
    private CircleImageView FotoMensajePerfil;
    private ImageView FotoMensajeEnviado;

    public TextView getNombre() {
        return Nombre;
    }

    public void setNombre(TextView nombre) {
        Nombre = nombre;
    }

    public TextView getMensaje() {
        return Mensaje;
    }

    public void setMensaje(TextView mensaje) {
        Mensaje = mensaje;
    }

    public TextView getHora() {
        return Hora;
    }

    public void setHora(TextView hora) {
        Hora = hora;
    }

    public CircleImageView getFotoMensajePerfil() {
        return FotoMensajePerfil;
    }

    public void setFotoMensajePerfil(CircleImageView fotoMensajePerfil) {
        FotoMensajePerfil = fotoMensajePerfil;
    }

    public ImageView getFotoMensajeEnviado() {
        return FotoMensajeEnviado;
    }

    public void setFotoMensajeEnviado(ImageView fotoMensajeEnviado) {
        FotoMensajeEnviado = fotoMensajeEnviado;
    }

    public HolderMensaje(View itemView) {
        super(itemView);

        Nombre = (TextView)itemView.findViewById(R.id.NombreMensaje);
        Mensaje= (TextView) itemView.findViewById(R.id.TextoMensaje);
        Hora = (TextView)itemView.findViewById(R.id.HoraMensaje);
        FotoMensajePerfil =(CircleImageView)itemView.findViewById(R.id.FotoPerfilMensaje);
        FotoMensajeEnviado = (ImageView) itemView.findViewById(R.id.MensajeFoto);





    }
}
