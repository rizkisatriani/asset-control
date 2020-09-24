package com.example.barcode_counting.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.barcode_counting.R;
import com.example.barcode_counting.helper.helper;
import com.example.barcode_counting.helper.storage;
import com.example.barcode_counting.model.asset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class barang_asset extends AppCompatActivity implements Filterable {
    storage storage;
    helper Helper;
    ArrayList<asset> data_asset = new ArrayList<>();
    ArrayList<asset> data_filter = new ArrayList<>();
    TableLayout tbl;
    TableLayout tbl_header;
    private LayoutInflater inflater;
    TableRow tb_row;
    HorizontalScrollView hrz_scr;
    LinearLayout child;
    EditText ed_cari;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang_asset);
        storage = new storage(this);
        Helper = new helper(this);
        inflater = LayoutInflater.from(barang_asset.this);
        tbl = findViewById(R.id.tbl);
        tbl_header = findViewById(R.id.tbl_header);
        hrz_scr = findViewById(R.id.hrz_scr);
        child = findViewById(R.id.child);
        ed_cari=findViewById(R.id.ed_cari);
        ed_cari.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                getFilter().filter(editable.toString());
            }
        });
        set_header();
        get_data();
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
                        innerJsonArray.getString("TYPE"), innerJsonArray.getString("location"),storage.getId_user()));

                tambah( innerJsonArray.getString("fixed_asset_number"),
                        innerJsonArray.getString("NAME"), innerJsonArray.getString("name2"),
                        innerJsonArray.getString("description"), innerJsonArray.getString("STATUS"),
                        innerJsonArray.getString("TYPE"), innerJsonArray.getString("location"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void tampil_filter(){
            tbl.removeAllViews();
                for (int i = 0; i < data_filter.size(); i++) {

                    tambah( data_filter.get(i).getFixed_asset_number(),data_filter.get(i).getNAME()
                            ,data_filter.get(i).getname2(),data_filter.get(i).getDescription()
                            ,data_filter.get(i).getSTATUS(),data_filter.get(i).getTYPE(),
                            data_filter.get(i).getLocation());
                }

    }
    void set_header() {
        View view;
        view = inflater.inflate(R.layout.item_table_asset_header, null, false);
        TextView text1, text2, text3, text4, text5, text6, text7;
        text1 = view.findViewById(R.id.text1);
        text1.setText("Fixed Asset Number");
        text2 = view.findViewById(R.id.text2);
        text2.setText("Name");
        text3 = view.findViewById(R.id.text3);
        text3.setText("Name");
        text4 = view.findViewById(R.id.text4);
        text4.setText("Description");
        text5 = view.findViewById(R.id.text5);
        text5.setText("Status");
        text6 = view.findViewById(R.id.text6);
        text6.setText("Type");
        text7 = view.findViewById(R.id.text7);
        text7.setText("Lokasi");
        tbl_header.addView(view);
        autofullscreen(view);
    }

    void tambah(String a,String b,String c,String d,String e,String f,String g) {
        View view;
        view = inflater.inflate(R.layout.item_table_asset, null, false);
        TextView text1, text2, text3, text4, text5, text6, text7;
        text1 = view.findViewById(R.id.text1);
        text1.setText(a);
        text2 = view.findViewById(R.id.text2);
        text2.setText(b);
        text3 = view.findViewById(R.id.text3);
        text3.setText(c);
        text4 = view.findViewById(R.id.text4);
        text4.setText(d);
        text5 = view.findViewById(R.id.text5);
        text5.setText(e);
        text6 = view.findViewById(R.id.text6);
        text6.setText(f);
        text7 = view.findViewById(R.id.text7);
        text7.setText(g);
        tbl.addView(view);
        autofullscreen(view);
    }
    void autofullscreen(View view) {
        if (Helper.HorizontalScrollViewIsScrolble(hrz_scr, child)) {
        } else {
            view.setMinimumWidth(Helper.getsize_screen());
        }
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                Log.i("Panjang Sting", "" + constraint.length());
                if (constraint == null || constraint.length() == 0) { // if your editText field is empty, return full list of FriendItem
                    results.count = data_asset.size();
                    results.values = data_asset;
                } else {
                    ArrayList<asset> filteredList = new ArrayList<>();

                    constraint = constraint.toString().toLowerCase(); // if we ignore case
                    for (asset item : data_asset) {
                        String getName = item.getNAME().toLowerCase(); // if we ignore case
                        String getName2 = item.getname2().toLowerCase(); // if we ignore case
                        String getDescription = item.getDescription().toLowerCase(); // if we ignore case
                        String getType = item.getTYPE().toLowerCase(); // if we ignore case
                        String getStatus = item.getSTATUS().toLowerCase(); // if we ignore case
                        if (getName.contains(constraint.toString())
                                || getName2.contains(constraint.toString())
                                || getDescription.contains(constraint.toString())
                                || getType.contains(constraint.toString())
                                || getStatus.contains(constraint.toString())) {
                            filteredList.add(item); // added item witch contains our text in EditText
                        }
                    }

                    results.count = filteredList.size(); // set count of filtered list
                    results.values = filteredList; // set filtered list
                }
                return results; // return our filtered list
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                data_filter = (ArrayList<asset>) results.values; // replace list to filtered list
                if(data_filter!=null){
                  tampil_filter();
                }
            }
        };
        return filter;
    }
}