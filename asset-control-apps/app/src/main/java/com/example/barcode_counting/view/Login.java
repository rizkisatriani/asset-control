package com.example.barcode_counting.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.barcode_counting.R;
import com.example.barcode_counting.helper.Connection;
import com.example.barcode_counting.helper.helper;
import com.example.barcode_counting.helper.storage;
import com.example.barcode_counting.helper.var_global;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Login extends AppCompatActivity {
Button btn_login;
storage db;
    helper Helper;
    EditText nip,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nip=findViewById(R.id.nip);
        password=findViewById(R.id.password);
        Helper=new helper(this);
        db=new storage(this);
        db.setId_user("2");
        btn_login=findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    void login() {
        class GetJSON extends AsyncTask<String, Integer, String> {
            String Url;

            @Override
            protected void onPreExecute() {
                Url= var_global.SET_API_LOGIN;
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                try {
                    JSONObject json=new JSONObject(result);
                    String kode=json.getString("kode");
                    if (kode.equals("200")){
                        db.setId_user(json.getString("id_user"));
                        db.setUsername(json.getString("username"));
                        startActivity(new Intent(Login.this,Menu_Utama.class));
                        finish();
                    }else{
                        Helper.AlertNotif("Peringatan",json.getString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("nip",nip.getText().toString());
                param.put("password",password.getText().toString());
                Connection rh = new Connection();
                String s = rh.sendPostRequest(Url, param);
                return s;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
}