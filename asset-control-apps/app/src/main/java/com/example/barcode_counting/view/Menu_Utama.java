package com.example.barcode_counting.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.barcode_counting.R;
import com.example.barcode_counting.helper.Connection;
import com.example.barcode_counting.helper.helper;
import com.example.barcode_counting.helper.storage;
import com.example.barcode_counting.helper.var_global;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Menu_Utama extends AppCompatActivity {
    CardView btn_scan,btn_download,btn_daftar,btn_upload;
    LinearLayout btn_item_update;
    TextView txt_download,txt_scan,txt_tanggal,txt_tgl_upload,txt_user;
    storage db;
    helper Helper;
    Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat dateFormat;
    private String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__utama);
        db=new storage(this);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateFormat.format(calendar.getTime());
        Helper=new helper(this);
        txt_user=findViewById(R.id.txt_user);
        txt_user.setText(db.getUsername());
        txt_tgl_upload=findViewById(R.id.txt_tgl_upload);
        txt_tgl_upload.setText(db.getTglupload());
        txt_download=findViewById(R.id.txt_download);
        txt_scan=findViewById(R.id.txt_scan);
        txt_scan.setText(db.getItem_scan());
        txt_tanggal=findViewById(R.id.txt_tanggal);
        txt_tanggal.setText(db.getTgldownload());
        Log.e("Json Size","00:"+db.getItem_download());
        txt_download.setText(db.getItem_download());
        btn_item_update=findViewById(R.id.btn_item_update);
        btn_item_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu_Utama.this,barang_asset_update.class));
            }
        });
        btn_upload=findViewById(R.id.btn_upload);
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload_asset();
            }
        });
        btn_download=findViewById(R.id.btn_download);
        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (db.getJson_asset_update().equals("")) {
                    startActivity(new Intent(Menu_Utama.this, list_download.class));
                }else{
                    Helper.AlertYesListener("Peringatan", "Jika anda mendownload data baru data stock take anda sebelumnya akan terhapus",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(Menu_Utama.this, list_download.class));
                                }
                            });
                }
            }
        });
        btn_daftar=findViewById(R.id.btn_daftar);
        btn_scan=findViewById(R.id.btn_scan);
        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu_Utama.this,barang_asset.class));
            }
        });
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Menu_Utama.this,scan_full.class);
                startActivity(intent);
            }
        });
        permision();
       // download_asset();
    }

    void upload_asset() {
        class GetJSON extends AsyncTask<String, Integer, String> {
            String Url;

            @Override
            protected void onPreExecute() {
                    Url= var_global.SET_API_UPLOAD;
                 super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                try {
                    JSONObject json=new JSONObject(result);
                    String kode=json.getString("kode");
                    if (kode.equals("200")){
                       Helper.AlertNotif("Notifikasi",json.getString("message"));
                       db.setTglupload(date);
                        txt_tgl_upload.setText(date);
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
                param.put("data",db.getJson_asset_update());
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



    void permision(){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED&&ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE
                            ,Manifest.permission.CAMERA},
                    1);

        } else {

        }
    }
}