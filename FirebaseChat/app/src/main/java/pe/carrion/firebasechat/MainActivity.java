package pe.carrion.firebasechat;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private CircleImageView FotoPerfil;
    private TextView Nombre;
    private RecyclerView rvMensajes;
    private EditText Mensajes;
    private Button Enviar;

    private AdapterMensajes adapter;

    private ImageButton EnviarFoto;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    private static final int PHOTO_SEND_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FotoPerfil=(CircleImageView)findViewById(R.id.fotoPerfil);
        Nombre=(TextView)findViewById(R.id.txtNombre);
        rvMensajes=(RecyclerView)findViewById(R.id.rvMensaje);
        Mensajes=(EditText)findViewById(R.id.txtMensaje);
        Enviar=(Button)findViewById(R.id.btnEnviar);

        adapter= new AdapterMensajes(this);
        EnviarFoto=(ImageButton)findViewById(R.id.btnEnviarFoto);

        database = FirebaseDatabase.getInstance();
        databaseReference=database.getReference("chat"); // Sala de Chat (nombre)
        storage = FirebaseStorage.getInstance();


        LinearLayoutManager l = new LinearLayoutManager(this);

        rvMensajes.setLayoutManager(l);
        rvMensajes.setAdapter(adapter);

        Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // adapter.addMensaje(new Mensaje(Mensajes.getText().toString(),Nombre.getText().toString(),"","1","00:00"));
                databaseReference.push().setValue(new Mensaje(Mensajes.getText().toString(),Nombre.getText().toString(),"","1","00:00"));
                Mensajes.setText("");
            }
        });

        EnviarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(i,"Selecciona una foto"),PHOTO_SEND_CODE);
            }
        });

    adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            setScrollBar();
        }
    });

    databaseReference.addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Mensaje m= dataSnapshot.getValue(Mensaje.class);
            adapter.addMensaje(m);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
    }

    private void setScrollBar()
    {
        rvMensajes.scrollToPosition(adapter.getItemCount()-1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PHOTO_SEND_CODE && resultCode == RESULT_OK)
        {
            Uri u =data.getData();
            storageReference=storage.getReference("imagenesChat");//Imagenes del chat
            final StorageReference fotoReferencia= storageReference.child(u.getLastPathSegment());
            fotoReferencia.putFile(u).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri u=taskSnapshot.getDownloadUrl();
                    Mensaje m = new Mensaje("Steven te ha enviado una imagen",u.toString(),Nombre.getText().toString(),"","2","00:00");
                    databaseReference.push().setValue(m);
                }
            });
        }
    }
}
