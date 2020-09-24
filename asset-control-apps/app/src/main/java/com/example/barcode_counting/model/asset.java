package com.example.barcode_counting.model;

public class asset {
    String id_asset, id_stock_take,fixed_asset_goup,fixed_asset_number,reference_asset_number,NAME,name2,description,STATUS,TYPE,location,id_user;

    public asset(String id_asset, String id_stock_take, String fixed_asset_goup, String fixed_asset_number, String reference_asset_number, String NAME, String name2, String description, String STATUS, String TYPE, String location, String id_user) {
        this.id_asset = id_asset;
        this.id_stock_take = id_stock_take;
        this.fixed_asset_goup = fixed_asset_goup;
        this.fixed_asset_number = fixed_asset_number;
        this.reference_asset_number = reference_asset_number;
        this.NAME = NAME;
        this.name2 = name2;
        this.description = description;
        this.STATUS = STATUS;
        this.TYPE = TYPE;
        this.location = location;
        this.id_user = id_user;
    }

    public String getId_asset() {
        return id_asset;
    }

    public void setId_asset(String id_asset) {
        this.id_asset = id_asset;
    }

    public String getId_stock_take() {
        return id_stock_take;
    }

    public void setId_stock_take(String id_stock_take) {
        this.id_stock_take = id_stock_take;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getFixed_asset_goup() {
        return fixed_asset_goup;
    }

    public void setFixed_asset_goup(String fixed_asset_goup) {
        this.fixed_asset_goup = fixed_asset_goup;
    }

    public String getFixed_asset_number() {
        return fixed_asset_number;
    }

    public void setFixed_asset_number(String fixed_asset_number) {
        this.fixed_asset_number = fixed_asset_number;
    }

    public String getReference_asset_number() {
        return reference_asset_number;
    }

    public void setReference_asset_number(String reference_asset_number) {
        this.reference_asset_number = reference_asset_number;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getname2() {
        return name2;
    }

    public void setname2(String name2) {
        this.name2 = name2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
