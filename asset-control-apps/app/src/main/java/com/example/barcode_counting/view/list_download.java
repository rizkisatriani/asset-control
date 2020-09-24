package com.example.barcode_counting.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.barcode_counting.R;
import com.example.barcode_counting.adapter.adapter_list;
import com.example.barcode_counting.helper.Connection;
import com.example.barcode_counting.helper.storage;
import com.example.barcode_counting.helper.var_global;
import com.example.barcode_counting.model.list_download_model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class list_download extends AppCompatActivity {

    ProgressDialog progressDialog;
    storage db;
    ArrayList<list_download_model> List;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_download);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        db=new storage(this);
        List=new ArrayList<>();
        download_asset();
    }

    void render_arr(String Json){
        try {
            Log.e("Json Size"," :"+Json);
            JSONObject json = new JSONObject(Json);
            JSONArray obj=json.getJSONArray("data");
            for (int i=0;i<obj.length();i++){
                JSONObject list = obj.getJSONObject(i);
                List.add(new list_download_model(list.getString("tanggal"),
                        list.getString("lokasi"),list.getString("id_stock_take"),
                        list.getString("tangal_input"),list.getString("id_user"),
                        list.getString("username"),list.getString("nama_lengkap")));
            }

            ListView listView=findViewById(R.id.list_download);
            adapter_list  adapter=new adapter_list(List,this);
            listView.setAdapter( adapter);
            progressDialog.dismiss();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    void download_asset() {
        class GetJSON extends AsyncTask<String, Integer, String> {
            String Url;

            @Override
            protected void onPreExecute() {

                    Url=var_global.SET_API_DOWNLOAD_LIST;
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
               render_arr(result);
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> param = new HashMap<String, String>();
                Connection rh = new Connection();
                param.put("id","2");
                Log.e("URL :",Url );
                publishProgress((int) (30));
                String s = rh.sendPostRequest(Url, param);
                publishProgress((int) (50));
                Log.e("URL :",s );
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