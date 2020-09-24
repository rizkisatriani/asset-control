package com.example.barcode_counting.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.barcode_counting.R;
import com.example.barcode_counting.helper.helper;
import com.example.barcode_counting.helper.storage;
import com.example.barcode_counting.model.asset;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Tampil_data extends AppCompatActivity {
    storage storage;
    helper Helper;
    ArrayList<asset> data_asset_update = new ArrayList<>();
    ArrayList<asset> data_asset = new ArrayList<>();
    asset data;
    TextView fixed_asset_number,
            reference_asset_number,
            NAME,
            description,
            TYPE ;
            int index,index_update;
    EditText name2,location,STATUS;
    ImageButton btn_edit,btn_edit_status,btn_edit_location,btn_photo;
    Button btn_simpan;
    String currentPhotoPath="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data);
        index = getIntent().getExtras().getInt("index");
        storage = new storage(this);
        Helper = new helper(this);
        get_data();
        get_data_update();
        fixed_asset_number = findViewById(R.id.fixed_asset_number);
        NAME = findViewById(R.id.name);
        name2 = findViewById(R.id.name2);
        description = findViewById(R.id.description);
        STATUS = findViewById(R.id.status);
        TYPE = findViewById(R.id.type);
        location = findViewById(R.id.location);
        btn_edit=findViewById(R.id.btn_edit);
        btn_photo=findViewById(R.id.btn_photo);
        btn_edit_status=findViewById(R.id.btn_edit_status);
        btn_edit_location=findViewById(R.id.btn_edit_location);
        btn_simpan=findViewById(R.id.btn_simpan);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name2.isEnabled()){
                    name2.setEnabled(false);
                    btn_edit.setBackgroundColor(getResources().getColor(R.color.bg_primary));
                }else{
                    name2.setEnabled(true);
                    btn_edit.setBackgroundColor(getResources().getColor(R.color.bg_info));
                }
            }
        });
        btn_edit_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(STATUS.isEnabled()){
                    STATUS.setEnabled(false);
                    btn_edit_status.setBackgroundColor(getResources().getColor(R.color.bg_primary));
                }else{
                    STATUS.setEnabled(true);
                    btn_edit_status.setBackgroundColor(getResources().getColor(R.color.bg_info));
                }
            }
        });
        btn_edit_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(location.isEnabled()){
                    location.setEnabled(false);
                    btn_edit_location.setBackgroundColor(getResources().getColor(R.color.bg_primary));
                }else{
                    location.setEnabled(true);
                    btn_edit_location.setBackgroundColor(getResources().getColor(R.color.bg_info));
                }
            }
        });
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.AlertYesListener("Simpan Data", "Apakah Anda Yakin Ingin Menyimpan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        data.setNAME(NAME.getText().toString());
                        data.setname2(name2.getText().toString());
                        data.setSTATUS(STATUS.getText().toString());
                        data.setLocation(location.getText().toString());
                        data.setId_user(storage.getId_user());
                        data_asset.set(index,data);
                        if (search(data_asset.get(index).getFixed_asset_number())){
                            data_asset_update.set(index_update,data_asset.get(index));
                        }else{
                            data_asset_update.add(data_asset.get(index));
                        }

                        storage.setItem_scan(""+data_asset_update.size());
                        HashMap<String,ArrayList<asset>> Json=new HashMap<>();
                        Json.put("data",data_asset_update);
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(Json);
                        storage.setJson_asset_update(jsonString);
                        startActivity(new Intent(Tampil_data.this,Menu_Utama.class));
                        finish();
                    }
                });
            }
        });
        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buat_temp_file();
            }
        });
        set_display();
    }

    void get_data() {
        try {
            Log.e("Json", storage.getJson_asset());
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

    void get_data_update() {
        try {
            Log.e("Json Update"," Hasil :"+ storage.getJson_asset_update());
            if(!storage.getJson_asset_update().equals("")){
                JSONObject obj = new JSONObject(storage.getJson_asset_update());
                JSONArray json=obj.getJSONArray("data");
                for (int i = 0; i < json.length(); i++) {
                    JSONObject innerJsonArray = json.getJSONObject(i);
                    data_asset_update.add(new asset(innerJsonArray.getString("id_asset"),
                            innerJsonArray.getString("id_stock_take"),innerJsonArray.getString("fixed_asset_goup"),
                            innerJsonArray.getString("fixed_asset_number"), innerJsonArray.getString("reference_asset_number"),
                            innerJsonArray.getString("NAME"), innerJsonArray.getString("name2"),
                            innerJsonArray.getString("description"), innerJsonArray.getString("STATUS"),
                            innerJsonArray.getString("TYPE"), innerJsonArray.getString("location"), storage.getId_user()));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void set_display() {

        fixed_asset_number.setText(data_asset.get(index).getFixed_asset_number());
        NAME.setText(data_asset.get(index).getNAME());
        name2.setText(data_asset.get(index).getname2());
        description.setText(data_asset.get(index).getDescription());
        STATUS.setText(data_asset.get(index).getSTATUS());
        TYPE.setText(data_asset.get(index).getTYPE());
        location.setText(data_asset.get(index).getLocation());
        data=new asset(data_asset.get(index).getId_asset(),
                data_asset.get(index).getId_stock_take(),data_asset.get(index).getFixed_asset_number(),data_asset.get(index).getFixed_asset_number(),
                data_asset.get(index).getReference_asset_number(),data_asset.get(index).getNAME(),data_asset.get(index).getname2()
        ,data_asset.get(index).getDescription(),data_asset.get(index).getSTATUS(),data_asset.get(index).getTYPE(),data_asset.get(index).getLocation(),
                data_asset.get(index).getId_user());
    }

    private void buat_temp_file() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = getPictureFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            if (photoFile != null) {
                /*Uri photoURI = FileProvider.getUriForFile(this,
                        getPackageName()+".fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);*/
                startActivityForResult(takePictureIntent, 2);
            }
        }
    }
    boolean search(String barcode){

        for (int i=0;i<data_asset_update.size();i++){
            if(data_asset_update.get(i).getFixed_asset_number().equals(barcode)){
                index_update=i;
                return true;
            }
        }
        return false;
    }
    File getPictureFile() throws IOException {
        // Create an image file name
        String imageFileName = "testing";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File[] files = storageDir.listFiles();
        if (files == null) {
            // handle path not a directory, or other error
        } else {
            for (File file : files) {
                file.delete();
            }
        }

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
}