package com.example.barcode_counting.adapter;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.barcode_counting.R;
import com.example.barcode_counting.helper.Connection;
import com.example.barcode_counting.helper.storage;
import com.example.barcode_counting.helper.var_global;
import com.example.barcode_counting.model.list_download_model;
import com.example.barcode_counting.view.Menu_Utama;
import com.example.barcode_counting.view.barang_asset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class adapter_list extends BaseAdapter {

    ProgressDialog progressDialog;
    storage db;
    ArrayList<list_download_model> List;
    Context context;

    public adapter_list(ArrayList<list_download_model> list, Context context) {
        List = list;
        this.context = context;
        db=new storage(context);
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
    }

    @Override
    public int getCount() {
        return List.size();
    }

    @Override
    public Object getItem(int i) {
        return List.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).
                    inflate(R.layout.item_list, viewGroup, false);
        }
        list_download_model currentItem = List.get(i);
        TextView tanggal = (TextView)
                view.findViewById(R.id.txt_tanggal);
        TextView Lokasi = (TextView)
                view.findViewById(R.id.txt_lokasi);
        tanggal.setText(currentItem.getTanggal());
        Lokasi.setText(currentItem.getLokasi());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            download_asset(currentItem.getId_stock_take());
            }
        });
        return view;
    }

    void download_asset(String id) {
        progressDialog.show();
        class GetJSON extends AsyncTask<String, Integer, String> {
            String Url;

            @Override
            protected void onPreExecute() {
                    Url=var_global.SET_API_DOWNLOAD_ASSETS;
                    super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
               /* progressBar.setProgress(98);
                progres.setText(""+98+"%");
                handler.postDelayed(update,500);*/
               db.setJson_asset(result);

                JSONObject obj = null;
                try {
                    obj = new JSONObject(result);
                    JSONArray json=obj.getJSONArray("data");
                    String tanggal=obj.getString("tanggal");
                    db.setTgldownload(tanggal);
                    Log.e("Json Size","00:"+json.length());
                    db.setItem_download(""+json.length());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
               db.setJson_asset_update("");
               db.setItem_scan("0");
                progressDialog.dismiss();
                context.startActivity(new Intent(context, Menu_Utama.class));
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> param = new HashMap<String, String>();
                Connection rh = new Connection();
                param.put("id",id);
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
