package com.example.barcode_counting.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class storage {
    public static SharedPreferences data;
    public static SharedPreferences.Editor editor;
    String Json_asset,id_stocktake,username,id_user,Json_asset_update,item_download,item_scan,tgldownload,tglupload;

    public storage(Context context) {
        data=context.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        editor=data.edit();
    }

    public String getJson_asset() {
        Json_asset=data.getString("Json_asset","");
        return Json_asset;
    }

    public void setJson_asset(String json_asset) {
        editor.putString("Json_asset",json_asset);
        editor.apply();
        this.Json_asset = json_asset;
    }

    public String getId_stocktake() {
        id_stocktake=data.getString("id_stocktake","");
        return id_stocktake;
    }

    public void setId_stocktake(String id_stocktake) {
        editor.putString("id_stocktake",id_stocktake);
        editor.apply();
        this.id_stocktake = id_stocktake;
    }

    public String getUsername() {
        username=data.getString("username","");
        return username;
    }

    public void setUsername(String username) {
        editor.putString("username",username);
        editor.apply();
        this.username = username;
    }

    public String getId_user() {
        id_user=data.getString("id_user","");
        return id_user;
    }

    public void setId_user(String id_user) {
        editor.putString("id_user",id_user);
        editor.apply();
        this.id_user = id_user;
    }

    public String getJson_asset_update() {
        Json_asset_update=data.getString("Json_asset_update","");
        return Json_asset_update;
    }

    public void setJson_asset_update(String json_asset_update) {
        editor.putString("Json_asset_update",json_asset_update);
        editor.apply();
        Json_asset_update = json_asset_update;
    }

    public String getItem_download() {
        item_download=data.getString("item_download","0");
        return item_download;
    }

    public void setItem_download(String item_download) {
        editor.putString("item_download",item_download);
        editor.apply();
        this.item_download = item_download;
    }

    public String getItem_scan() {
        item_scan=data.getString("item_scan","0");
        return item_scan;
    }

    public void setItem_scan(String item_scan) {
        editor.putString("item_scan",item_scan);
        editor.apply();
        this.item_scan = item_scan;
    }

    public String getTgldownload() {
        tgldownload=data.getString("tgldownload","-");
        return tgldownload;
    }

    public void setTgldownload(String tgldownload) {
        editor.putString("tgldownload",tgldownload);
        editor.apply();
        this.tgldownload = tgldownload;
    }

    public String getTglupload() {
        tglupload=data.getString("tglupload","-");
        return tglupload;
    }

    public void setTglupload(String tglupload) {
        editor.putString("tglupload",tglupload);
        editor.apply();
        this.tglupload = tglupload;
    }
}
