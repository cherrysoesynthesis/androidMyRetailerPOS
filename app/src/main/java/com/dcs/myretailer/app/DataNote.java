package com.dcs.myretailer.app;

public class DataNote
{
    String text;
    String comment;
    String date;
    public int masarImg;
    public int arrowImg;

    public DataNote(String text, String comment, String date , Integer img, Integer arrowImg)
    {
        this.text = text;
        this.comment = comment;
        this.date = date;
        this.masarImg = img;
        this.arrowImg = arrowImg;
    }

    public String getText()
    {
        return text;
    }

    public String getComment()
    {
        return comment;
    }

    public String getDate()
    {
        return date;
    }

    public int getMasarImg() {
        return masarImg;
    }

    public void setMasarImg(int masarImg) {
        this.masarImg = masarImg;
    }

    public int getArrowImg() {
        return arrowImg;
    }

    public void setArrowImg(int arrowImg) {
        this.arrowImg = arrowImg;
    }
}