package pe.carrion.firebasechat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.facebook.*;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import pe.carrion.firebasechat.model.Usuario;
import pe.carrion.firebasechat.network.DemoApi;


public class LoginActivity extends AppCompatActivity {

    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton)findViewById(R.id.login_button);

        loginButton.setReadPermissions(Arrays.asList("public_profile","email"));

        String FirstName="";
        String LastName="";
        String SocialID="";
        String Mail="";
        String SocialToken;

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                // App code
               // String msg="This is my Toast message!";
                /*Toast.makeText(getApplicationContext(), msg,
                        Toast.LENGTH_LONG).show();*/



                GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                try {

                                    saveSugar(
                                            object.getString("first_name"),
                                            object.getString("last_name"),
                                            object.getString("id"),
                                            object.getString("email"),
                                            AccessToken.getCurrentAccessToken().getToken()
                                    );
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, name, email,first_name,last_name");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

        if( AccessToken.getCurrentAccessToken()!=null){

            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
        }
    }

    private void saveSugar(String FirstName,String LastName,String SocialID,String Mail,String SocialToken){
        Usuario usuario= new Usuario();
        usuario.setName(FirstName +" "+LastName);
        usuario.setMail(Mail);
        usuario.setTipo(1);
        usuario.setFirebase("[AUN FALTA]");
        usuario.setSocialid(SocialID);
        usuario.setSocialtoken(SocialToken);
        StartApp.getInstance().setCurrentUsuario(usuario);
        usuario.save();

        //llamar

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("nombre",  usuario.getName());
            jsonObject.put("correo",usuario.getMail());
            jsonObject.put("tipo", usuario.getTipo());
            jsonObject.put("socialId", usuario.getSocialid());
            jsonObject.put("firebase", usuario.getFirebase());


        } catch (JSONException e) {

        }

        AndroidNetworking.post(DemoApi.USUARIO_URL)
                .addJSONObjectBody(jsonObject)
                .setPriority(Priority.HIGH)
                .setTag("APi")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            Log.d("API",response.getString("status"));
                            Log.d("API",response.getString("message"));

                            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                            startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        // Log.d(TAG, );


                        return;
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

}
