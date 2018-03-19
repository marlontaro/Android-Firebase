package pe.carrion.firebasechat;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.androidnetworking.AndroidNetworking;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

import android.content.pm.Signature;

import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import pe.carrion.firebasechat.model.Usuario;
import pe.carrion.firebasechat.network.DemoApi;


/**
 * Created by Marlon Cordova on 18/03/2018.
 */

public class StartApp extends com.orm.SugarApp{
    private static StartApp instance;

    private DemoApi demoApi = new DemoApi();


    public StartApp() {
        super();
        instance = this;
    }

    public static StartApp getInstance() {
        return instance;
    }

    public void setCurrentUsuario(Usuario source) {
        demoApi.setCurrentUsuario(source);
    }

    public Usuario getCurrentUsuario() {
        return demoApi.getCurrentUsuario();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        PrintCodeHash();


        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        //Firebase.setAndroidContext(getApplicationContext());
        FirebaseApp.initializeApp(getApplicationContext());
        AndroidNetworking.initialize(getApplicationContext());


    }


    public void PrintCodeHash() {

        try {

            PackageInfo info = getPackageManager().getPackageInfo(
                    "pe.carrion.firebasechat",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {


        }
    }

}
