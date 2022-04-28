package com.dcs.myretailer.app.Setting;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MapViewer extends View {

    private OnSelectedButton selectedListener;

    List<MapButton> btnlist = new ArrayList<MapButton>();
    Bitmap img = null;
    int BGColor = Color.WHITE;

    Paint selectColor = new Paint();

    Paint p = new Paint();

    //int selected = -1;
    boolean EditMode = false;
    boolean MoveMode = false;
    boolean ResizeMode = false;
    int mouseSelected = -1;
    int selectedButton = -1;
    boolean selectionResize = false;
    int resizeType = 0;
    float deltaX = 0;
    float deltaY = 0;

    private static final float[] NEGATIVE = {
            -1.0f,     0,     0,    0, 255, // red
            0, -1.0f,     0,    0, 255, // green
            0,     0, -1.0f,    0, 255, // blue
            0,     0,     0, 1.0f,   0  // alpha
    };

    public interface OnSelectedButton{
        public void SelectedButton(int buttonPos, MapButton button);
    }

    public void setOnSelectedButtonListener(OnSelectedButton listener){
        this.selectedListener = listener;
    }

    public MapViewer(Context context) {
        super(context);
        p.setStrokeWidth(4);
        selectColor.setColor(Color.argb(0xFF, 0x7F, 0x7F, 0x7F));
        selectColor.setStyle(Paint.Style.STROKE);
        selectColor.setStrokeWidth(4f);
        selectColor.setPathEffect(new DashPathEffect(new float[] {10,10}, 0));

        selectColor.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));


        this.setOnTouchListener(new OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(EditMode){
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            if(mouseSelected==-1){
                                float vX = event.getX() / v.getWidth() * 100f;
                                float vY = event.getY() / v.getHeight() * 100f;

                                for(int i=btnlist.size()-1;i>=0;i--){
                                    MapButton btn = btnlist.get(i);
                                    if(vX>=btn.getX() && vX <= (btn.getX()+btn.getWidth()) && vY>=btn.getY() && vY <= (btn.getY()+btn.getHeight())){

                                        mouseSelected = i;
                                        selectedButton = i;

                                        deltaX = (vX - btnlist.get(mouseSelected).getX());
                                        deltaY = (vY - btnlist.get(mouseSelected).getY());


                                        if(ResizeMode){
                                            resizeType = 0;
                                            if(btnlist.get(mouseSelected).getWidth()-deltaX<5){
                                                resizeType |= 1<<1;
                                            }

                                            if(btnlist.get(mouseSelected).getHeight()-deltaY<5){
                                                resizeType |= 1<<2;
                                            }

                                            if(resizeType>0){
                                                selectionResize = true;
                                            }
                                        }
                                        break;
                                    }
                                }

                                if(mouseSelected==-1){
                                    return false;
                                }else{
                                    invalidate(btnlist.get(mouseSelected).getBound());
                                }
                                //Log.e("Button", "Selected: "+selectedButton);
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                            //changed = true;

                            if(mouseSelected!=-1){
                                if(selectedListener!=null){
                                    selectedListener.SelectedButton(mouseSelected, btnlist.get(mouseSelected));
                                }
                            }
                            mouseSelected = -1;
                            if(selectionResize){
                                resizeType = 0;
                                selectionResize = false;
                            }
                            invalidate();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            //changed = true;
                            if(mouseSelected!=-1){
                                float vX = (event.getX() / v.getWidth() * 100f);
                                float vY = event.getY() / v.getHeight() * 100f;

                                if(selectionResize){

                                    if(((resizeType>>1)&0x1) == 1){//right
                                        btnlist.get(mouseSelected).setWidth(vX-btnlist.get(mouseSelected).getX());
                                        if(btnlist.get(mouseSelected).getWidth()<4){
                                            btnlist.get(mouseSelected).setWidth(4);
                                        }
                                        if((btnlist.get(mouseSelected).getX() + btnlist.get(mouseSelected).getWidth())>100){
                                            btnlist.get(mouseSelected).setWidth(100-btnlist.get(mouseSelected).getX());
                                        }
                                    }

                                    if(((resizeType>>2)&0x1) == 1){//bottom
                                        btnlist.get(mouseSelected).setHeight(vY-btnlist.get(mouseSelected).getY());
                                        if(btnlist.get(mouseSelected).getHeight()<4){
                                            btnlist.get(mouseSelected).setHeight(4);
                                        }

                                        if((btnlist.get(mouseSelected).getY() + btnlist.get(mouseSelected).getHeight())>100){
                                            btnlist.get(mouseSelected).setHeight(100-btnlist.get(mouseSelected).getY());
                                        }
                                    }

                                }else{

                                    if(MoveMode){
                                        vX = vX-deltaX;
                                        vY = vY-deltaY;

                                        if(vX+btnlist.get(mouseSelected).getWidth()>100) vX = 100-btnlist.get(mouseSelected).getWidth();
                                        if(vY+btnlist.get(mouseSelected).getHeight()>100) vY = 100-btnlist.get(mouseSelected).getHeight();
                                        if(vX<0)vX = 0;
                                        if(vY<0)vY = 0;
                                        btnlist.get(mouseSelected).setX(vX);
                                        btnlist.get(mouseSelected).setY(vY);
                                    }
                                }
                                invalidate();
                            }
                            break;
                    }
                    return true;
                }else{
                    if(selectedListener!=null){
                        float vX = event.getX() / v.getWidth() * 100f;
                        float vY = event.getY() / v.getHeight() * 100f;

                        for(int i=btnlist.size()-1;i>=0;i--){
                            MapButton btn = btnlist.get(i);
                            if(vX>=btn.getX() && vX <= (btn.getX()+btn.getWidth()) && vY>=btn.getY() && vY <= (btn.getY()+btn.getHeight())){


                                selectedListener.SelectedButton(i,btn);

                                break;
                            }
                        }



                        //selectedListener.SelectedButton(mouseSelected, btnlist.get(mouseSelected));
                    }
                    return true;
                }
                //return false;
            }
        });
    }

    public List<MapButton> ButtonList(){
        return btnlist;
    }

    public void setEditMode(boolean val){
        EditMode = val;
        invalidate();
    }

    public void setMoveMode(boolean val){
        if(EditMode){
            MoveMode = val;
        }
    }

    public void setResizeMode(boolean val){
        if(EditMode){
            ResizeMode= val;
        }
    }

    public int getSelectedButtonPosition(){
        return selectedButton;
    }

    public void setSelectedButtonPosition(int select){
        selectedButton = select;
        if(selectedButton<-1)selectedButton = -1;
        if(selectedButton>=btnlist.size())selectedButton = btnlist.size()-1;
    }

    public MapButton getSelectedButton(){
        if(selectedButton>=0 && selectedButton<btnlist.size()){
            return btnlist.get(selectedButton);
        }else{
            return null;
        }
    }




    public void setBGImage(Bitmap img){
        this.img = img;
    }

    public void setBGColor(int value){
        BGColor = value;
    }


    @Override
    protected void onDraw(Canvas c){
        super.onDraw(c);

        float scaleX = getWidth()/100f;
        float scaleY = getHeight()/100f;

        //drawing start here
        p.setStyle(Paint.Style.FILL);
        if(img==null){
            p.setColor(BGColor);
            c.drawPaint(p);
        }else{
            c.drawBitmap(img, new Rect(0,0,img.getWidth(), img.getHeight()), new Rect(0,0,getWidth(),getHeight()), null);
        }

        //drawing button
        for(int i = 0;i<btnlist.size();i++){
            MapButton b = btnlist.get(i);
            p.setStyle(Paint.Style.FILL);
            p.setColor(b.getBGColor());
            float btnWidth = scaleX*b.getWidth();
            float btnHeight = scaleY*b.getHeight();


            RectF bound = new RectF(scaleX*b.getX(), scaleY*b.getY(), (scaleX*b.getX())+btnWidth, (scaleY*b.getY())+btnHeight);

            //c.clipRect(bound, Region.Op.REPLACE);
            try {
                c.clipRect(bound, Region.Op.UNION);
            }catch (IllegalArgumentException e) {

            }
            if(b.getButtonStyle()==ButtonStyle.NORMAL || b.getButtonStyle() == ButtonStyle.IMAGE_TEXT){
                c.drawRect(bound, p);
            }

            if(b.getButtonStyle() == ButtonStyle.IMAGE_TEXT || b.getButtonStyle()==ButtonStyle.IMAGE || b.getButtonStyle()==ButtonStyle.IMAGE_TEXT_BORDERLESS){
                if(b.getImage()!=null){
                    c.drawBitmap(b.getImage(), new Rect(0,0,b.getImage().getWidth(),b.getImage().getHeight()), bound, null);
                }
            }

            if(b.getButtonStyle()==ButtonStyle.NORMAL || b.getButtonStyle() == ButtonStyle.IMAGE_TEXT || b.getButtonStyle() == ButtonStyle.IMAGE_TEXT_BORDERLESS){

                //if(b.getButtonStyle() != ButtonStyle.IMAGE_TEXT_BORDERLESS){
                try {

                    c.clipRect(bound, Region.Op.INTERSECT);
                } catch (IllegalArgumentException e) {

                }
                //}
                p.setColor(b.getFGColor());
                p.setTextSize(b.getTextSize());
                if(!b.getText().isEmpty()){
                    String[] lines = b.getText().split("\n");
                    if(lines.length>1){
                        int h = 0;
                        int totalheight = 0;

                        for(int l=0;l<lines.length-2;l++){
                            Rect lr = new Rect();
                            p.getTextBounds(lines[l], 0, lines[l].length(), lr);
                            totalheight += lr.height();
                        }
                        for(String s:lines){
                            Rect lr = new Rect();
                            p.getTextBounds(s, 0, s.length(), lr);
                            c.drawText(s, bound.left + ((btnWidth/2) - lr.centerX()), bound.top + ((btnHeight/2) - (totalheight/2) + h), p);
                            h+=lr.height();
                        }
                    }else{
                        Rect lr = new Rect();
                        p.getTextBounds(b.getText(), 0, b.getText().length(), lr);
                        c.drawText(b.getText(), bound.left + ((btnWidth/2) - lr.centerX()), bound.top + ((btnHeight/2) - lr.centerY()), p);
                    }
                }

                if(b.getButtonStyle() != ButtonStyle.IMAGE_TEXT_BORDERLESS){
                    p.setStyle(Paint.Style.STROKE);
                    p.setColor((Color.argb(0xFF, (int)(((b.bgColor>>16)&0xFF)/2f), (int)(((b.bgColor>>8)&0xFF)/2f), (int)(((b.bgColor)&0xFF)/2f))));
                    c.drawRect(new RectF(bound.left+2, bound.top+2, bound.right-2, bound.bottom-2), p);
                }
                try {

                    c.clipRect(bound, Region.Op.REPLACE);
                }catch (IllegalArgumentException e) {

                }
            }
            if(EditMode){
                if(selectedButton == i){
                    c.drawRect(bound.left+5, bound.top+5, bound.right-5, bound.bottom-5, selectColor);
                }
            }

        }
        //end of drawing
    }




}

