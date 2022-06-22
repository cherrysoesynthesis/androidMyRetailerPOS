package com.dcs.myretailer.app.Setting;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;

public class MapButton{
    int bgColor = Color.WHITE;
    int fgColor = Color.BLACK;

    float width = 0;
    float height = 0;
    float x = 0;
    float y = 0;

    String text = "";
    String name = "";
    Bitmap img = null;

    float textSize = 1;

    int functype = 0;
    int objid = 0;
    String objext = "";

    ButtonStyle btn_style = ButtonStyle.NORMAL;


    public void setFuncType(int val){
        functype = val;
    }

    public int getFuncType(){
        return functype;
    }

    public void setObjID(int val){
        objid = val;
    }

    public int getObjID(){
        return objid;
    }

    public void setObjExt(String val){
        objext = val;
    }

    public String getObjExt(){
        return objext;
    }

    public void setBGColor(int color){
        bgColor = color;
    }

    public int getBGColor(){
        return bgColor;
    }

    public void setFGColor(int color){
        fgColor = color;
    }

    public int getFGColor(){
        return fgColor;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public void setName(String text){
        name = text;
    }

    public String getName(){
        return name;
    }

    public void setX(float value){
        x = value;
    }

    public float getX(){
        return x;
    }

    public void setY(float value){
        y = value;
    }

    public float getY(){
        return y;
    }

    public void setWidth(float value){

        width = value;
        if(width<0)width = 0;
        if(width>100)width = 100;
    }

    public float getWidth(){
        return width;
    }

    public void setHeight(float value){
        height = value;
        if(height<0)height = 0;
        if(height>100)height = 100;
    }

    public float getHeight(){
        return height;
    }

    public void setTextSize(float size){
        textSize = size;
        if(textSize<1)textSize = 1;

    }

    public float getTextSize(){
        return textSize;
    }

    public void setImage(Bitmap img){
        this.img = img;
    }

    public void setButtonStyle(ButtonStyle style){
        btn_style = style;
    }

    public ButtonStyle getButtonStyle(){
        return btn_style;
    }

    public Rect getBound(){
        return new Rect((int)getX(), (int)getY(), (int)(getX()+getWidth()), (int)(getY()+getHeight()));

    }

    public Bitmap getImage(){
        return img;
    }
}
