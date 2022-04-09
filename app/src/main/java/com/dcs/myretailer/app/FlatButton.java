package com.dcs.myretailer.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.dcs.myretailer.app.Setting.ButtonStyle;
import com.dcs.myretailer.app.Setting.ButtonTextAlign;

public class FlatButton extends androidx.appcompat.widget.AppCompatButton {

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
    float x_txt,y_txt;
    Rect margin = new Rect(8,8,8,8);
    Rect bound_txt = new Rect();
    int draw_h_pos = 0;
    int draw_h_init = 0;
    int draw_totalheight = 0;
    ButtonTextAlign txtAlign = ButtonTextAlign.MiddleCenter;

    boolean Pressed = false;

    private static final float[] NEGATIVE = {
            -1.0f,     0,     0,    0, 255, // red
            0, -1.0f,     0,    0, 255, // green
            0,     0, -1.0f,    0, 255, // blue
            0,     0,     0, 1.0f,   0  // alpha
    };

    public FlatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.com_andrognito_pinlockviewapp_FlatButton);
        init();
        setBackgroundColor(typedArray.getColor(R.styleable.com_andrognito_pinlockviewapp_FlatButton_bgcolor, Color.WHITE));
        setTextColor(typedArray.getColor(R.styleable.com_andrognito_pinlockviewapp_FlatButton_textcolor, Color.BLACK));
    }

    public FlatButton(Context context){
        super(context);
        init();
    }




    @SuppressWarnings("deprecation")
    void init(){

        setBackgroundDrawable(null);


        darkpaint = new Paint();
        darkpaint.setStyle(Paint.Style.FILL);
        darkpaint.setColor(Color.argb(0x80,0xFF,0xFF,0xFF));
        selectColor = new Paint();
        selectColor.setColor(Color.argb(0xFF, 0x7F, 0x7F, 0x7F));
        selectColor.setStyle(Paint.Style.STROKE);
        selectColor.setStrokeWidth(4f);
        selectColor.setPathEffect(new DashPathEffect(new float[] {10,10}, 0));

        selectColor.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));

        //selectColor.setStyle(Style.FILL);
        bgcolor = new Paint();
        bgcolor.setColor(Color.WHITE);
        bgcolor.setStyle(Paint.Style.FILL);
        bglight = new Paint();
        bglight.setColor(Color.argb(0xFF, (int)(((bgcolor.getColor()>>16)&0xFF)/1.5f), (int)(((bgcolor.getColor()>>8)&0xFF)/1.5f), (int)(((bgcolor.getColor())&0xFF)/1.5f)));
        bglight.setStrokeWidth(4);
        bglight.setStyle(Paint.Style.STROKE);
        bgdark = new Paint();
        bgdark.setColor(Color.argb(0xFF, (int)(((bgcolor.getColor()>>16)&0xFF)/2f), (int)(((bgcolor.getColor()>>8)&0xFF)/2f), (int)(((bgcolor.getColor())&0xFF)/2f)));
        bgdark.setStrokeWidth(4);
        bgdark.setStyle(Paint.Style.STROKE);

        txtcolor = new Paint();
        txtcolor.setColor(Color.BLACK);
        txtcolor.setAntiAlias(true);
        txtcolor.setShadowLayer(5f, 0, 0, Color.WHITE);
        txtcolor.setStyle(Paint.Style.FILL);
        txtcolor.setTextSize(getTextSize());

        txtborder = new Paint();

        txtborder.setColor(Color.WHITE);
        txtborder.setAntiAlias(true);
        txtborder.setStyle(Paint.Style.STROKE);
        txtborder.setStrokeWidth(4f);
        txtborder.setTextSize(getTextSize());



        RefreshLook();
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    ((FlatButton)v).Pressed = true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP){
                    ((FlatButton)v).Pressed = false;
                }
                if (event.getAction() == MotionEvent.ACTION_CANCEL){
                    ((FlatButton)v).Pressed = false;
                }
                ((FlatButton)v).RefreshLook();
                return false;
            }

        });
    }


    /**
     * Set FlatButton background color and if applicable, set border color as well
     * @param	color color value to set
     */
    public void setBackgroundColor(int color){
        bgcolor.setColor(Color.argb(0xFF, (color>>16&0xFF), (color>>8&0xFF), (color&0xFF)));
        bglight.setColor(Color.argb(0xFF, (int)(((color>>16)&0xFF)/1.5f), (int)(((color>>8)&0xFF)/1.5f), (int)(((color)&0xFF)/1.5f)));
        bgdark.setColor(Color.argb(0xFF, (int)(((color>>16)&0xFF)/2f), (int)(((color>>8)&0xFF)/2f), (int)(((color)&0xFF)/2f)));
    }

    /**
     * Return FlatButton background color value
     * @return	get background color
     */
    public int getBackgroundColor(){
        return bgcolor.getColor();
    }

    /**
     * Set FlatButton Text color
     * @param	color color value to set
     */
    public void setTextColor(int color){
        txtcolor.setColor(Color.argb(0xFF, (color>>16&0xFF), (color>>8&0xFF), (color&0xFF)));
        txtcolor.setShadowLayer(5f, 0, 0, Color.argb(0xFF, 255-(color>>16&0xFF), 255-(color>>8&0xFF), 255-(color&0xFF)));
        txtborder.setColor(Color.argb(0xFF, 255-(color>>16&0xFF), 255-(color>>8&0xFF), 255-(color&0xFF)));
    }

    /**
     * Return FlatButton Text color value
     * @return	get text color
     */
    public int getTextColor(){
        return txtcolor.getColor();
    }

    /**
     * Set FlatButton Image
     * @param	b Bitmap image to be set
     */
    public void setImage(Bitmap b){
        img = b;
    }

    /**
     * Return assigned image
     * @return	get image
     */
    public Bitmap getImage(){
        return img;
    }

    public void setSelect(boolean select){
        this.select = select;
        //selectColor.setColor(Color.argb(0x80, 0xC0, 0xFF, 0x90));
    }

    /**
     * Set FlatButton button style
     * @param	style button face plate style
     */
    public void setButtonStyle(ButtonStyle style){
        btn_style = style;
    }

    /**
     * Set Text Alignment
     * @param	align Text alignment location
     */
    public void setTextAlign(ButtonTextAlign align){
        txtAlign = align;
    }

    /**
     * Set FlatButton text margin to be draw on
     * @param	margin margin value in pixel
     */
    public void setMargin(int margin){

        this.margin.top = Math.max(0, margin);
        this.margin.bottom = Math.max(0, margin);
        this.margin.left = Math.max(0, margin);
        this.margin.right = Math.max(0, margin);
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
            c.scale(0.95f, 0.95f, getWidth()/2f, getHeight()/2f);
            //c.scale(0.95f, 0.95f, c.getWidth()/2f, c.getHeight()/2f);
        }

        if(btn_style == ButtonStyle.NORMAL || btn_style == ButtonStyle.IMAGE_TEXT || btn_style == ButtonStyle.IMAGE_TEXT_BORDERLESS){

            if(btn_style == ButtonStyle.NORMAL || btn_style == ButtonStyle.IMAGE_TEXT){
                c.drawRect(4,4, getWidth()-4, getHeight()-4, bgcolor);
                if(Pressed){
                    c.drawRect(2,2, getWidth()-2, getHeight()-2, darkpaint);
                }
            }

            if(btn_style == ButtonStyle.IMAGE_TEXT || btn_style == ButtonStyle.IMAGE_TEXT_BORDERLESS){
                if(img!=null){
                    c.drawBitmap(img, new Rect(0,0,img.getWidth(),img.getHeight()), new Rect(4,4, getWidth()-4, getHeight()-4), null);
                }
            }

            if(btn_style == ButtonStyle.NORMAL || btn_style == ButtonStyle.IMAGE_TEXT){
                if(Pressed){
                    c.drawLine(getWidth()-4, 2, getWidth()-4, getHeight()-4, bglight);
                    c.drawLine(2, getHeight()-4, getWidth()-2, getHeight()-4, bglight);

                    c.drawLine(2, 4, getWidth()-2, 4, bgdark);
                    c.drawLine(4, 4, 4, getHeight()-4, bgdark);

                }else{
                    c.drawLine(2, 4, getWidth()-2, 4, bglight);
                    c.drawLine(4, 4, 4, getHeight()-4, bglight);

                    c.drawLine(getWidth()-4, 2, getWidth()-4, getHeight()-4, bgdark);
                    c.drawLine(2, getHeight()-4, getWidth()-2, getHeight()-4, bgdark);
                }
            }



            if(getText().toString().length()>0){
                String[] lines = getText().toString().split("\n");


                if(lines.length>=2){
                    draw_h_pos = 0;
                    draw_totalheight = 0;
                    draw_h_init = 0;
                    for(int l=0;l<lines.length-(txtAlign==ButtonTextAlign.MiddleCenter?2:1);l++){
                        //Rect lr = new Rect();
                        txtcolor.getTextBounds(lines[l], 0, lines[l].length(), bound_txt);
                        if(l==0){
                            draw_h_init = -bound_txt.top + margin.top;
                        }
                        draw_totalheight += bound_txt.height();
                    }
                    for(String s:lines){
                        //Rect lr = new Rect();
                        txtcolor.getTextBounds(s, 0, s.length(), bound_txt);

                        switch(txtAlign){
                            case TopLeft:
                                x_txt = margin.left;
                                y_txt = draw_h_init + draw_h_pos + margin.top;
                                break;
                            case TopCenter:
                                x_txt = (getWidth()/2) - bound_txt.centerX();
                                y_txt = draw_h_init + draw_h_pos + margin.top;
                                break;
                            case TopRight:
                                x_txt = getWidth() - bound_txt.width() - margin.right;
                                y_txt = draw_h_init + draw_h_pos + margin.top;
                                break;

                            case MiddleLeft:
                                x_txt = margin.left;
                                y_txt = (getHeight()/2) - (draw_totalheight/2) + draw_h_pos;
                                break;
                            case MiddleRight:
                                x_txt = getWidth() - bound_txt.width() - margin.right;
                                y_txt = (getHeight()/2) - (draw_totalheight/2) + draw_h_pos;
                                break;
                            case BottomLeft:
                                x_txt = margin.left;
                                y_txt = getHeight() - draw_totalheight + draw_h_pos - margin.bottom;
                                break;
                            case BottomCenter:
                                x_txt = (getWidth()/2) - bound_txt.centerX();
                                y_txt = getHeight() - draw_totalheight + draw_h_pos - margin.bottom;
                                break;
                            case BottomRight:
                                x_txt = getWidth() - bound_txt.width() - margin.right;
                                y_txt = getHeight() - draw_totalheight + draw_h_pos - margin.bottom;
                                break;
                            case MiddleCenter:
                            default:
                                x_txt = (getWidth()/2) - bound_txt.centerX();
                                y_txt = (getHeight()/2) - (draw_totalheight/2) + draw_h_pos;
                                break;
                        }
                        c.drawText(s, x_txt, y_txt, txtborder);
                        c.drawText(s, x_txt, y_txt, txtcolor);

                        draw_h_pos+=bound_txt.height();
                    }
                }else{
                    //Rect lr = new Rect();
                    txtcolor.getTextBounds(getText().toString(), 0, getText().toString().length(), bound_txt);


                    switch(txtAlign){
                        case TopLeft:
                            x_txt = margin.left;
                            y_txt = -bound_txt.top + margin.top;
                            break;
                        case TopCenter:
                            x_txt = (getWidth()/2) - bound_txt.centerX();
                            y_txt = -bound_txt.top + margin.top;
                            break;
                        case TopRight:
                            x_txt = getWidth() - bound_txt.width() - margin.right;
                            y_txt = -bound_txt.top + margin.top;
                            break;

                        case MiddleLeft:
                            x_txt = margin.left;
                            y_txt = (getHeight()/2) - (bound_txt.centerY());
                            break;
                        case MiddleRight:
                            x_txt = getWidth() - bound_txt.width() - margin.right;
                            y_txt = (getHeight()/2) - (bound_txt.centerY());
                            break;
                        case BottomLeft:
                            x_txt = margin.left;
                            y_txt = getHeight() - bound_txt.bottom - margin.bottom;
                            break;
                        case BottomCenter:
                            x_txt = (getWidth()/2) - bound_txt.centerX();
                            y_txt = getHeight() - bound_txt.bottom - margin.bottom;
                            break;
                        case BottomRight:
                            x_txt = getWidth() - bound_txt.width() - margin.right;
                            y_txt = getHeight() - bound_txt.bottom - margin.bottom;
                            break;
                        case MiddleCenter:
                        default:
                            x_txt = (getWidth()/2) - bound_txt.centerX();
                            y_txt = (getHeight()/2) - (bound_txt.centerY());
                            break;
                    }

                    c.drawText(getText().toString(), x_txt, y_txt, txtborder);
                    c.drawText(getText().toString(), x_txt, y_txt, txtcolor);


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


