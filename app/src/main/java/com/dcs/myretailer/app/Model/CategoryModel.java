package com.dcs.myretailer.app.Model;

public class CategoryModel {
    Integer ID;
    String UUID;
    String Name;
    String Code;
    String Image;
    Integer DateTime;

    public CategoryModel(Integer ID, String UUID, String name, String code, String image, Integer dateTime) {
        this.ID = ID;
        this.UUID = UUID;
        Name = name;
        Code = code;
        Image = image;
        DateTime = dateTime;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Integer getDateTime() {
        return DateTime;
    }

    public void setDateTime(Integer dateTime) {
        DateTime = dateTime;
    }
}
