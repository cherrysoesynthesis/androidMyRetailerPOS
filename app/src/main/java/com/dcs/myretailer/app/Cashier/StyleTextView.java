package com.dcs.myretailer.app.Cashier;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.Paint.Style;
import android.util.AttributeSet;

import com.dcs.myretailer.app.R;

public class StyleTextView extends androidx.appcompat.widget.AppCompatTextView{

    //int bordercolor = Color.DKGRAY;
    int bgcolor = Color.WHITE;
    int txtcolor = Color.BLACK;

    int drkborder = Color.argb(0, 0, 0, 0);
    int color1 = drkborder;
    int color2 = drkborder;
    int color3 = drkborder;
    String txtinfo = "";
    float txtinfosize = 1f;
    Paint txtpaint = new Paint();
    Paint txtborder = new Paint();
    int align = TEXT_ALIGNMENT_VIEW_END;

    public StyleTextView(Context context, AttributeSet attrs) {
        super(context);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.com_andrognito_pinlockviewapp_StyleTextView);
        setTextColor(typedArray.getColor(R.styleable.com_andrognito_pinlockviewapp_Cashier_MetallicButton_textcolor, Color.BLACK));
        this.setSingleLine(true);
        init();
        invalidate();
        requestLayout();
    }

    public StyleTextView(Context context) {
        super(context);
        this.setSingleLine(true);
        init();
        invalidate();
        requestLayout();
    }

    public void setBackgroundColor(int color){
        bgcolor = color;
        drkborder = Color.argb(255,(int)(((bgcolor>>16)&0xFF)/2f), (int)(((bgcolor>>8)&0xFF)/2f), (int)(((bgcolor)&0xFF)/2f));
        color1 = Color.argb(180,255, 255, 255);
        color2 = Color.argb(0,(int)(((drkborder>>16)&0xFF)), (int)(((drkborder>>8)&0xFF)), (int)(((drkborder)&0xFF)));
        color3 = Color.argb(255,(int)(((drkborder>>16)&0xFF)), (int)(((drkborder>>8)&0xFF)), (int)(((drkborder)&0xFF)));

        invalidate();
        requestLayout();
    }

    public void setAlignment(int align){
        this.align = align;
        invalidate();
        requestLayout();
    }

    @SuppressWarnings("deprecation")
    void init(){
        setBackgroundDrawable(null);
        txtpaint.setStyle(Style.FILL);
        txtpaint.setAntiAlias(true);
        txtborder.setStyle(Style.STROKE);
        txtborder.setStrokeWidth(4f);
        txtborder.setAntiAlias(true);
    }

    public void setTextColor(int color){
        txtpaint.setColor(color);
        txtpaint.setShadowLayer(5f, 0, 0, Color.argb(0xFF, 255-(txtcolor>>16&0xFF), 255-(txtcolor>>8&0xFF), 255-(txtcolor&0xFF)));
        txtborder.setColor(Color.argb(0xFF, 255-(txtcolor>>16&0xFF), 255-(txtcolor>>8&0xFF), 255-(txtcolor&0xFF)));

        invalidate();
        requestLayout();
    }

    public void setTextInfo(String text){
        txtinfo = text;
        invalidate();
        requestLayout();
    }

    public void setTextInfoSize(float size){
        txtinfosize = size;
        invalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas c){
        RectF bound = new RectF(1,1,getWidth()-1, getHeight()-1);
        Paint p = new Paint();
        p.setColor(drkborder);
        c.drawRoundRect(bound, 4, 4, p);
        bound = new RectF(3,3,getWidth()-3, getHeight()-3);

        p.setColor(bgcolor);
        c.drawRoundRect(bound, 4, 4, p);

        Shader shader1 = new LinearGradient(bound.centerX(),bound.top,bound.centerX(),bound.bottom,color2, color3,Shader.TileMode.CLAMP);
        p.setShader(shader1);

        c.drawRoundRect(bound, 4, 4, p);


        bound = new RectF(bound.left,bound.centerY() + bound.centerY()/2,bound.right, bound.bottom);
        shader1 = new LinearGradient(bound.centerX(),bound.top,bound.centerX(),bound.bottom,color2, color1,Shader.TileMode.CLAMP);
        p.setShader(shader1);

        c.drawRoundRect(bound, 4, 4, p);

        bound = new RectF(5,5,getWidth()-5, getHeight()-5);
        if(getText()!=null){
            if(getText().toString().length()>0){

                txtpaint.setTypeface(getTypeface());
                txtpaint.setTextSize(getTextSize());
                txtborder.setTypeface(getTypeface());

                txtborder.setTextSize(getTextSize());

                Rect lr = new Rect();
                txtpaint.getTextBounds(getText().toString(), 0, getText().toString().length(), lr);
                if(align==TEXT_ALIGNMENT_CENTER){
                    c.drawText(getText().toString(), (bound.width()/2-(lr.width()-txtborder.getStrokeWidth())/2), bound.centerY() - lr.centerY(), txtpaint);
                }else if(align==TEXT_ALIGNMENT_VIEW_START){
                    c.drawText(getText().toString(), txtborder.getStrokeWidth(), bound.centerY() - lr.centerY(), txtpaint);
                }else{
                    c.drawText(getText().toString(), (bound.width()-txtborder.getStrokeWidth()-lr.width()), bound.centerY() - lr.centerY(), txtpaint);
                }

            }
        }


        if(txtinfo!=null){
            if(txtinfo.length()>0){
                txtpaint.setTypeface(Typeface.DEFAULT);
                txtpaint.setTextSize(txtinfosize);
                Rect lr = new Rect();
                txtpaint.getTextBounds(txtinfo, 0, txtinfo.length(), lr);
                c.drawText(txtinfo, 5+bound.left, bound.top+5+lr.height(), txtpaint);
            }
        }


    }

}
