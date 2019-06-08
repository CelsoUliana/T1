package com.example.t1.View;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.t1.Model.Root;
import com.example.t1.Model.User;
import com.example.t1.R;

import java.io.InputStream;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class LoginActivity extends AppCompatActivity {

    Realm realm;
    private EditText password;
    private EditText login;
    private Button btnBuscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("t1.realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfig);

        CreateDatabaseFromJson();
        CreateDefaultUser();

        setContentView(R.layout.activity_login);
    }


    public void CreateDatabaseFromJson() {

        realm = Realm.getDefaultInstance();

        InputStream inputStream = getResources().openRawResource(R.raw.cattle);
        InputStream is = getResources().openRawResource(R.raw.cattle2);

        try {
            realm.beginTransaction();
            realm.createObjectFromJson(Root.class, inputStream);
            realm.createObjectFromJson(Root.class, is);
            realm.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            realm.close();
        }
    }

    protected void CreateDefaultUser() {
        realm = Realm.getDefaultInstance(); // opens "t1.realm"
        try {
            // Persist your data in a transaction
            realm.beginTransaction();
            User usu = realm.createObject(User.class); // Create managed objects directly
            usu.setLogin("admin");
            usu.setPassword("123");
            realm.commitTransaction();

        } finally {
            realm.close();
        }
    }

    public void onButtonClickTest(View v){
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }

    public void onButtonClick(View v){
        realm = Realm.getDefaultInstance();

        try{

            password = findViewById(R.id.password);
            login = findViewById(R.id.login);

            final RealmResults<User> usu = realm.where(User.class)
                    .equalTo("login", login.getText().toString())
                    .equalTo("password", password.getText().toString())
                    .findAll();

            if(usu.size() > 0){
                Toast.makeText(this, "achou usuarios", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
            }

            else{
                Toast.makeText(this, "Nao achou usuarios", Toast.LENGTH_LONG).show();
            }

        }
        finally {
            //exportDatabase();
            realm.close();
        }
    }

    @Override
    protected void onDestroy() {
        realm = Realm.getDefaultInstance();
        realm.close();
        super.onDestroy();
    }
}
