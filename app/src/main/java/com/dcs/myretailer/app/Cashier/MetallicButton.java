package com.dcs.myretailer.app.Cashier;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.dcs.myretailer.app.R;

public class MetallicButton extends Button {

    boolean select = false;
    Paint bgcolor;
    Paint bglight;
    Paint bgdark;
    Paint txtcolor;
    Paint txtborder;
    Paint selectColor;
    Paint darkpaint;
    Bitmap img = null;
    ButtonStyle btn_style = ButtonStyle.NORMAL;
    boolean Pressed = false;

    private static final float[] NEGATIVE = {
            -1.0f,     0,     0,    0, 255, // red
            0, -1.0f,     0,    0, 255, // green
            0,     0, -1.0f,    0, 255, // blue
            0,     0,     0, 1.0f,   0  // alpha
    };


    public MetallicButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.com_andrognito_pinlockviewapp_Cashier_MetallicButton);
        init();
        setBackgroundColor(typedArray.getColor(R.styleable.com_andrognito_pinlockviewapp_Cashier_MetallicButton_bgcolor, Color.WHITE));
        setTextColor(typedArray.getColor(R.styleable.com_andrognito_pinlockviewapp_Cashier_MetallicButton_textcolor, Color.BLACK));
    }

    public MetallicButton(Context context){
        super(context);
        init();
    }




    @SuppressWarnings("deprecation")
    void init(){

        setBackgroundDrawable(null);
        darkpaint = new Paint();
        darkpaint.setStyle(Style.FILL);

        //darkpaint.setColor(Color.argb(0x80,0xFF,0xFF,0xFF));
        darkpaint.setColor(Color.argb(0x80,50,205,50));
        selectColor = new Paint();
        selectColor.setColor(Color.argb(0xFF, 0x7F, 0x7F, 0x7F));
        selectColor.setStyle(Style.STROKE);
        selectColor.setStrokeWidth(4f);
        selectColor.setPathEffect(new DashPathEffect(new float[] {10,10}, 0));

        selectColor.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));

        //button color
        bgcolor = new Paint();
        bgcolor.setColor(Color.WHITE);
        bgcolor.setStyle(Style.FILL);

        //border color
        bglight = new Paint();
        bglight.setColor(Color.argb(0xFF, (int)(((bgcolor.getColor()>>16)&0xFF)/1.5f), (int)(((bgcolor.getColor()>>8)&0xFF)/1.5f), (int)(((bgcolor.getColor())&0xFF)/1.5f)));
        bglight.setStrokeWidth(8);
        bglight.setStyle(Style.STROKE);
        bgdark = new Paint();
        bgdark.setColor(Color.argb(0xFF, (int)(((bgcolor.getColor()>>16)&0xFF)/2f), (int)(((bgcolor.getColor()>>8)&0xFF)/2f), (int)(((bgcolor.getColor())&0xFF)/2f)));
        bgdark.setStrokeWidth(8);
        bgdark.setStyle(Style.STROKE);

        txtcolor = new Paint();
        txtcolor.setColor(Color.BLACK);
        txtcolor.setAntiAlias(true);
        txtcolor.setShadowLayer(5f, 0, 0, Color.WHITE);
        txtcolor.setStyle(Style.FILL);
        txtcolor.setTextSize(getTextSize());

        txtborder = new Paint();

        txtborder.setColor(Color.WHITE);
        txtborder.setAntiAlias(true);
        txtborder.setStyle(Style.STROKE);
        txtborder.setStrokeWidth(4f);
        txtborder.setTextSize(getTextSize());

        RefreshLook();
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    ((MetallicButton)v).Pressed = true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP){
                    ((MetallicButton)v).Pressed = false;
                }
                if (event.getAction() == MotionEvent.ACTION_CANCEL){
                    ((MetallicButton)v).Pressed = false;
                }
                ((MetallicButton)v).RefreshLook();
                return false;
            }

        });
    }

//(50,205,50)
    public void setBackgroundColor(int color){
        bgcolor.setColor(Color.argb(0xFF, 255,255,255));
        bglight.setColor(Color.argb(0xFF, 255,255,255));
        bgdark.setColor(Color.argb(0xFF, 240,255,240));
    }
//    public void setBackgroundColor(int color){
//        bgcolor.setColor(Color.argb(0xFF, (color>>16&0xFF), (color>>8&0xFF), (color&0xFF)));
//        bglight.setColor(Color.argb(0xFF, (int)(((color>>16)&0xFF)/1.5f), (int)(((color>>8)&0xFF)/1.5f), (int)(((color)&0xFF)/1.5f)));
//        bgdark.setColor(Color.argb(0xFF, (int)(((color>>16)&0xFF)/2f), (int)(((color>>8)&0xFF)/2f), (int)(((color)&0xFF)/2f)));
//    }

    public void setTextColor(int color){
        txtcolor.setColor(Color.argb(0xFF, (color>>16&0xFF), (color>>8&0xFF), (color&0xFF)));
        txtcolor.setShadowLayer(5f, 0, 0, Color.argb(0xFF, 255-(color>>16&0xFF), 255-(color>>8&0xFF), 255-(color&0xFF)));
        txtborder.setColor(Color.argb(0xFF, 255-(color>>16&0xFF), 255-(color>>8&0xFF), 255-(color&0xFF)));
    }

    public void setImage(Bitmap b){
        img = b;
    }

    public void setSelect(boolean select){
        this.select = select;
        //selectColor.setColor(Color.argb(0x80, 0xC0, 0xFF, 0x90));
    }

    public void setButtonStyle(ButtonStyle style){
        btn_style = style;
    }

    public enum ButtonStyle{
        NORMAL(0),
        IMAGE_TEXT(1),
        IMAGE(2);
        private ButtonStyle(int value) {
        }

    }

    public void RefreshLook(){
        invalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas c){
        //super.onDraw(c);

        if(Pressed){
            c.save();
            //if(btn_style == ButtonStyle.IMAGE){
            c.scale(0.95f, 0.95f, getWidth()/2f, getHeight()/2f);
            //}
        }
        if(btn_style == ButtonStyle.NORMAL || btn_style == ButtonStyle.IMAGE_TEXT){


            if(btn_style == ButtonStyle.IMAGE_TEXT){
                if(img!=null){
                    c.drawBitmap(img, new Rect(0,0,img.getWidth(),img.getHeight()), new Rect(4,4, getWidth()-4, getHeight()-4), null);
                }
            }


            //button face
            RectF bound = new RectF(2,4, getWidth()-4, getHeight()-4);

            Shader shader1 = new SweepGradient(bound.centerX(),bound.centerY(),new int[]{bgdark.getColor(), bgcolor.getColor(),bgdark.getColor(), bgcolor.getColor(),bgdark.getColor()},null);
            Matrix matrix1 = new Matrix();
            matrix1.setRotate(45, bound.centerX(), bound.centerY());
            shader1.setLocalMatrix(matrix1);
            Paint p1 = new Paint();
            p1.setShader(shader1);
            p1.setAntiAlias(true);
            c.drawRoundRect(bound, 8, 8, p1);


            Shader shader2 = new SweepGradient(bound.centerX(),bound.centerY(),new int[]{bgdark.getColor(), bgcolor.getColor(),bgdark.getColor()},null);
            Matrix matrix2 = new Matrix();
            matrix2.setRotate(45, bound.centerX(), bound.centerY());
            shader2.setLocalMatrix(matrix2);
            Paint p2 = new Paint();
            p2.setStrokeWidth(4);
            p2.setStyle(Style.STROKE);
            p2.setShader(shader2);
            p2.setAntiAlias(true);
            c.drawRoundRect(bound, 8, 8, p2);





            if(getText().toString().length()>0){
                String[] lines = getText().toString().split("\n");


                if(lines.length>=2){
                    int h = 0;
                    int totalheight = 0;
                    for(int l=0;l<lines.length-2;l++){
                        Rect lr = new Rect();
                        txtcolor.getTextBounds(lines[l], 0, lines[l].length(), lr);
                        totalheight += lr.height();
                    }
                    for(String s:lines){
                        Rect lr = new Rect();
                        txtcolor.getTextBounds(s, 0, s.length(), lr);
                        c.drawText(s, (getWidth()/2) - lr.centerX(), (getHeight()/2) - (totalheight/2) + h, txtborder);
                        c.drawText(s, (getWidth()/2) - lr.centerX(), (getHeight()/2) - (totalheight/2) + h, txtcolor);

                        h+=lr.height();
                    }
                }else{
                    Rect lr = new Rect();
                    txtcolor.getTextBounds(getText().toString(), 0, getText().toString().length(), lr);
                    c.drawText(getText().toString(), (getWidth()/2) - lr.centerX(), (getHeight()/2) - lr.centerY(), txtborder);
                    c.drawText(getText().toString(), (getWidth()/2) - lr.centerX(), (getHeight()/2) - lr.centerY(), txtcolor);

                }
            }


        } else if(btn_style == ButtonStyle.IMAGE){
            if(img!=null){
                c.drawBitmap(img, new Rect(0,0,img.getWidth(),img.getHeight()), new Rect(0,0, getWidth(), getHeight()), null);
            }
        }

        if(select){
            c.drawRect(2, 2, getWidth()-2, getHeight()-2, selectColor);
        }
        if(Pressed){
            c.restore();
        }
    }

}
