package com.example.barcode_counting.model;

public class list_download_model {
    String tanggal,lokasi,id_stock_take,tangal_input,id_user,username,nama_lengkap;

    public list_download_model(String tanggal, String lokasi, String id_stock_take, String tangal_input, String id_user, String username, String nama_lengkap) {
        this.tanggal = tanggal;
        this.lokasi = lokasi;
        this.id_stock_take = id_stock_take;
        this.tangal_input = tangal_input;
        this.id_user = id_user;
        this.username = username;
        this.nama_lengkap = nama_lengkap;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getId_stock_take() {
        return id_stock_take;
    }

    public void setId_stock_take(String id_stock_take) {
        this.id_stock_take = id_stock_take;
    }

    public String getTangal_input() {
        return tangal_input;
    }

    public void setTangal_input(String tangal_input) {
        this.tangal_input = tangal_input;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }
}
