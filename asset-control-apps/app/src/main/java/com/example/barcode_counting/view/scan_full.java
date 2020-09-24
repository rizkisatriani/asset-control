package com.example.barcode_counting.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.barcode_counting.R;
import com.example.barcode_counting.helper.helper;
import com.example.barcode_counting.helper.storage;
import com.example.barcode_counting.model.asset;
import com.google.zxing.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class scan_full extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView mScannerView;
    FrameLayout frame;
    storage storage;
   helper helper;
    ArrayList<asset> data_asset = new ArrayList<>();
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_full);
        frame = findViewById(R.id.frame_layout_camera);
        storage=new storage(this);
        helper=new helper(this);
        get_data();
        initScanView();

    }
    void initScanView(){
        mScannerView = new ZXingScannerView(scan_full.this);
        mScannerView.setAutoFocus(true);
        mScannerView.setResultHandler(this);
        frame.removeAllViews();
        frame.addView(mScannerView);
    }

    @Override
    public void handleResult(Result result) {
        if(search(result.getText())){
            mScannerView.stopCamera();
            Intent i=new Intent(scan_full.this,Tampil_data.class);
            i.putExtra("index",index);
            startActivity(i);
        }else{
                helper.AlertNotif("Scan Gagal","Barcode "+result.getText()+" Tidak Terdaftar");
         /*   helper.AlertYesListener("Barcode", "Kode Barcode " + result.getText() +
                    "tidak diketahui Click Yes Untuk Simulasi .", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mScannerView.stopCamera();
                    Intent x=new Intent(scan_full.this,Tampil_data.class);
                    x.putExtra("index",5);
                    startActivity(x);
                }
            });*/
        }
    }

    void get_data(){
        try {
            Log.e("Json",storage.getJson_asset());
            JSONObject obj = new JSONObject(storage.getJson_asset());
            JSONArray json=obj.getJSONArray("data");
            for (int i = 0; i < json.length(); i++) {
                JSONObject innerJsonArray = json.getJSONObject(i);
                data_asset.add(new asset(innerJsonArray.getString("id_asset"),
                        innerJsonArray.getString("id_stock_take"),innerJsonArray.getString("fixed_asset_goup"),
                        innerJsonArray.getString("fixed_asset_number"), innerJsonArray.getString("reference_asset_number"),
                        innerJsonArray.getString("NAME"), innerJsonArray.getString("name2"),
                        innerJsonArray.getString("description"), innerJsonArray.getString("STATUS"),
                        innerJsonArray.getString("TYPE"), innerJsonArray.getString("location"), storage.getId_user()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onStart() {
        mScannerView.startCamera();
        super.onStart();
    }

    @Override
    protected void onResume() {
      //  initScanView();
        mScannerView.setResultHandler(scan_full.this::handleResult);
        mScannerView.startCamera();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mScannerView.stopCamera();
        super.onPause();
    }
    boolean search(String barcode){

        for (int i=0;i<data_asset.size();i++){
            if(data_asset.get(i).getFixed_asset_number().equals(barcode)){
                index=i;
                return true;
            }
        }
        return false;
    }
}