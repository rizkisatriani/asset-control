package com.example.barcode_counting.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.barcode_counting.R;
import com.example.barcode_counting.helper.Connection;
import com.example.barcode_counting.helper.helper;
import com.example.barcode_counting.helper.storage;
import com.example.barcode_counting.helper.var_global;
import com.example.barcode_counting.model.asset;
import com.google.gson.Gson;
import com.google.zxing.Result;
import com.opencsv.CSVWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Barcode extends AppCompatActivity implements ZXingScannerView.ResultHandler  {
    ZXingScannerView mScannerView;
    FrameLayout frame;
    ArrayList<asset> data_asset = new ArrayList<>();
    storage storage;
    TableLayout tbl;
    TableLayout tbl_header;
    TableRow tb_row;
    private LayoutInflater inflater;
    HorizontalScrollView hrz_scr;
    LinearLayout child;
    String Jsonasset="";
    helper helper;
    boolean isCaptured = false;
    Button sexport,mulai;
    boolean chek = false;
    View background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storage = new storage(Barcode.this);
        helper = new helper(Barcode.this);
        hrz_scr = findViewById(R.id.hrz_scr);
        child = findViewById(R.id.child);
        frame = findViewById(R.id.frame_layout_camera);
        inflater = LayoutInflater.from(Barcode.this);
        background=inflater.inflate(R.layout.background_frame, null, false);
        initScanView();
        frame.addView(background);
        tbl = findViewById(R.id.tbl);
        tbl_header = findViewById(R.id.tbl_header);
        sexport = findViewById(R.id.btn_export);
        mulai=findViewById(R.id.btn_mulai);
        sexport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    saveCsv(storage.getJson_asset());
                    upload_asset();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!isCaptured) {
                        initScanView();
                        mulai.setBackgroundColor(getResources().getColor(R.color.bg_alert));
                        mulai.setText("Batal");
                        isCaptured = true;
                        mScannerView.setResultHandler(Barcode.this::handleResult);
                        mScannerView.startCamera();
                    } else {
                        mScannerView.stopCamera();
                        frame.removeAllViews();
                        frame.addView(background);
                        mulai.setBackgroundColor(getResources().getColor(R.color.bg_primary));
                        mulai.setText("Mulai");
                        isCaptured = false;
                    }
                } catch (RuntimeException e) {
                    Log.e("RuntimeException",e.getMessage());
                }
            }
        });
        set_header();
        tampil();
        permision();

    }

    void initScanView(){
        mScannerView = new ZXingScannerView(Barcode.this);
        mScannerView.setAutoFocus(true);
        mScannerView.setResultHandler(this);
        frame.removeAllViews();
        frame.addView(mScannerView);
    }

    public void saveCsv(String Json) throws IOException, JSONException {
        if(Json.equals("")){
            helper.AlertNotif("Export Excel Gagal",
                    "Belum ada barcode yang di scan.");
            String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath() +  File.separator + "excel_asset"+  File.separator ;
            File dir = new File(rootPath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            Uri uri = Uri.parse(rootPath);
            intent.setDataAndType(uri, "text/csv");
            startActivity(Intent.createChooser(intent, "Open folder"));
        }else {
            JSONArray docs = new JSONArray(Json);
            String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/excel_asset/";
            File dir = new File(rootPath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = null;
            file = new File(rootPath, "/asset.csv");
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.exists()) {
                CSVWriter writer = new CSVWriter(new FileWriter(file));
                String[] stringArray1 = new String[2];
                stringArray1[0] = "Barcode";
                stringArray1[1] = "Jumlah";
                writer.writeNext(stringArray1);
                for (int i = 0; i < docs.length(); i++) {

                    stringArray1 = new String[2];
                    JSONObject innerJsonArray = docs.getJSONObject(i);
                    stringArray1[0] = innerJsonArray.getString("barcode");
                    stringArray1[1] = innerJsonArray.getString("count");

                    writer.writeNext(stringArray1);

                }
                writer.close();
                helper.AlertNotif("Export Excel", "Data berhasil di exort.");
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.parse("/excel_asset/"); // a directory
                intent.setDataAndType(uri, "*/*");
                startActivity(Intent.createChooser(intent, "Open folder"));
            }
        }
    }

    @Override
    public void handleResult(Result result) {
        for (asset item : data_asset) {
          //  String barcode = item.getBarcode().toLowerCase();
//            if (barcode.equals(result.getText().toLowerCase())) {
//                chek = true;
//            }
        }
        if (chek) {
            chek = false;
            helper.AlertNotif("Simpan Gagal", "Barcode sudah pernah di scan !");
        }
        else{
           // data_asset.add(new asset(result.getText(), 1));
            tambah(result.getText(), "1");
            Jsonasset = new Gson().toJson(data_asset);
            storage.setJson_asset(Jsonasset);
            /*helper.AlertYesListener("Scan Berhasil", "Apakah anda ingin scan barcode selanjutnya ?", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mScannerView.resumeCameraPreview(MainActivity.this::handleResult);
                }
            });*/
        }
        frame.removeAllViews();
        frame.addView(background);
        mulai.setBackgroundColor(getResources().getColor(R.color.bg_primary));
        mulai.setText("Mulai");
        isCaptured=false;
    }

    @Override
    protected void onStart() {
        //mScannerView.startCamera();
        super.onStart();
    }

    @Override
    protected void onPause() {
        mScannerView.stopCamera();
        super.onPause();
    }

    void tambah(String barcode, String Jumlah) {
        View view;
        TextView text1, text2;
        view = inflater.inflate(R.layout.row_table_item, null, false);
        text1 = view.findViewById(R.id.text1);
        text1.setText(barcode);
        text2 = view.findViewById(R.id.text2);
        text2.setText("" + Jumlah);
        tb_row = findViewById(R.id.tb_item);
        tbl.addView(view);
        autofullscreen(view);
    }

    void set_header() {
        View view;
        view = inflater.inflate(R.layout.row_table_header, null, false);
        TextView text1, text2;
        text1 = view.findViewById(R.id.text1);
        text1.setText("Barcode");
        text2 = view.findViewById(R.id.text2);
        text2.setText("Jumlah");
        tbl_header.addView(view);
        autofullscreen(view);
    }

    void autofullscreen(View view) {
        if (helper.HorizontalScrollViewIsScrolble(hrz_scr, child)) {
        } else {
            view.setMinimumWidth(helper.getsize_screen());
        }
    }

    void tampil() {
        try {
            JSONArray docs = new JSONArray(storage.getJson_asset());
            for (int i = 0; i < docs.length(); i++) {
                JSONObject innerJsonArray = docs.getJSONObject(i);
                View view;
                TextView text1, text2;
                view = inflater.inflate(R.layout.row_table_item, null, false);
                text1 = view.findViewById(R.id.text1);
                text1.setText(innerJsonArray.getString("barcode"));
                text2 = view.findViewById(R.id.text2);
                text2.setText("" + innerJsonArray.getString("count"));
                tbl.addView(view);
                autofullscreen(view);
//                data_asset.add(new asset(innerJsonArray.getString("barcode"),
//                        Integer.valueOf(innerJsonArray.getString("count"))));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    void upload_asset() {
        class GetJSON extends AsyncTask<String, Integer, String> {
            String Url;
            @Override
            protected void onPreExecute() {

                    Url=var_global.SET_API_UPLOAD;
                 super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
            }
            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("data",storage.getJson_asset());
                Connection rh = new Connection();
                String s = rh.sendPostRequest(Url, param);
                return null;
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