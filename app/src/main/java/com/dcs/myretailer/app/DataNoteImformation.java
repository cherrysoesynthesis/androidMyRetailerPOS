package com.dcs.myretailer.app;

import android.graphics.Bitmap;

public class DataNoteImformation
{
//    public static String[] textArray = {"PRODUCT CATEGORY 1","PRODUCT CATEGORY 2","PRODUCT CATEGORY 3"};
//    public static String[] dateArray = {"2017-04-25","2017-04-25","2017-04-25"};
//    public static String[] id = {"1","2","3"};
//    public static int[] img = {R.drawable.image8,R.drawable.image,R.drawable.image7};
//    public static int[] img_1 = {R.drawable.ic_arrow_right_grey_500,R.drawable.ic_arrow_right_grey_500,R.drawable.ic_arrow_right_grey_500};
    Integer ID;
    String Name;
//    Bitmap Image;
    String Image;

    public DataNoteImformation(Integer ID, String name, String image) {
        this.ID = ID;
        this.Name = name;
        this.Image = image;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}