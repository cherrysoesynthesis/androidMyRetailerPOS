package com.dcs.myretailer.app.Setting;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.DialogBox;
import com.dcs.myretailer.app.FlatButton;
import com.dcs.myretailer.app.Logger;
import com.dcs.myretailer.app.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActivityMapButtonSetup extends AppCompatActivity {
    static int MapID = -1;
    static Activity CurrentActivity;

    static MapViewer map;

    static Bitmap _tmpbmp = null;
    static boolean changed = false;
    //ActivityMapButtonSetup binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapbutton_setup);
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_mapbutton_setup);

        Button btn_add = (Button)findViewById(R.id.btn_add);
        Button btn_edit = (Button)findViewById(R.id.btn_edit);
        Button btn_del = (Button)findViewById(R.id.btn_del);

        ToggleButton tg_move = (ToggleButton)findViewById(R.id.tg_move);
        ToggleButton tg_resize = (ToggleButton)findViewById(R.id.tg_resize);

        Button btn_save = (Button)findViewById(R.id.btn_save);
        Button btn_back = (Button)findViewById(R.id.btn_back);

        this.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 0));
        //this.getActionBar().setIcon(R.drawable.img_cfg_map);
        btn_add.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 8));
        btn_edit.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 9));
        btn_del.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 10));

        tg_move.setText(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 101));
        tg_resize.setText(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 102));


        Bundle bundle = getIntent().getExtras();
        MapID = -1;

        if(bundle!=null){
            MapID = bundle.getInt("MapID");
        }

        if(MapID==-1){
            final DialogBox dlg1 = new DialogBox(this);
            dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 0));
            dlg1.setDialogIconType(DialogBox.IconType.ERROR);
            dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 26));
            dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    dlg1.dismiss();
                    finish();
                }

            });
            dlg1.show();
            return;
        }

        CurrentActivity = this;
        map = new MapViewer(this);
        LinearLayout lay = (LinearLayout)findViewById(R.id.lay_map);
        map.setLayoutParams(new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
        lay.addView(map);
        map.setEditMode(true);
        map.setOnSelectedButtonListener(new MapViewer.OnSelectedButton(){

            @Override
            public void SelectedButton(int buttonPos, MapButton button) {
                changed = true;
            }

        });


        Cursor data = DBFunc.Query("SELECT image,bgcolor FROM MapLayout WHERE id = "+MapID, true);
        if(data==null){
            final DialogBox dlg1 = new DialogBox(this);
            dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
            dlg1.setDialogIconType(DialogBox.IconType.ERROR);
            dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 19));
            dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    dlg1.dismiss();
                    finish();
                }

            });
            dlg1.show();
            return;
        }

        if(!data.moveToNext()){
            data.close();
            final DialogBox dlg1 = new DialogBox(this);
            dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
            dlg1.setDialogIconType(DialogBox.IconType.ERROR);
            dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 19));
            dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    dlg1.dismiss();
                    finish();
                }

            });
            dlg1.show();
            return;
        }

        try{
            String imgbase64 = data.getString(0);
            if(imgbase64!=null){
                byte[] b = Base64.decode(imgbase64,Base64.DEFAULT);
                map.setBGImage(BitmapFactory.decodeByteArray(b, 0, b.length));
            }else{
                map.setBGImage(null);
            }
        }catch(IllegalArgumentException e){
            map.setBGImage(null);
        }

        map.setBGColor(data.getInt(1));
        data.close();

        data = DBFunc.Query("SELECT func,name,x,y,width,height,style,bgcolor,fgcolor,fontsize,img FROM MapButtons WHERE map_id = "+MapID+" ORDER BY id ASC", true);
        if(data==null){
            final DialogBox dlg1 = new DialogBox(this);
            dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
            dlg1.setDialogIconType(DialogBox.IconType.ERROR);
            dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 19));
            dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    dlg1.dismiss();
                    finish();
                }

            });
            dlg1.show();
            return;
        }

        while(data.moveToNext()){
            MapButton btn = new MapButton();
            String[] func = data.getString(0).trim().split(":",3);
            try{
                if(func.length>=2){
                    int Type = Integer.parseInt(func[0].trim());
                    int ObjID = Integer.parseInt(func[1].trim());
                    btn.setFuncType(Type);
                    btn.setObjID(ObjID);
                    btn.setObjExt("");

                    if(func.length==3){
                        if(Type == 0){
                            if(ObjID == 19){
                                btn.setObjExt(func[2].trim());
                            }
                        }
                    }
                }
            }catch(NumberFormatException e){
            }

            btn.setText(data.getString(1));
            float val = data.getFloat(2);
            if(val<0)val = 0;
            btn.setX(val);

            val = data.getFloat(3);
            if(val<0)val = 0;
            btn.setY(val);

            val = data.getFloat(4);
            if(val+btn.getX()>100){
                val = 100 - btn.getX();
            }
            btn.setWidth(val);

            val = data.getFloat(5);
            if(val+btn.getY()>100){
                val = 100 - btn.getY();
            }
            btn.setHeight(val);

            switch(data.getInt(6)){
                case 0:
                    btn.setButtonStyle(ButtonStyle.NORMAL);
                    break;
                case 1:
                    btn.setButtonStyle(ButtonStyle.IMAGE_TEXT);
                    break;
                case 2:
                    btn.setButtonStyle(ButtonStyle.IMAGE);
                    break;
                case 3:
                    btn.setButtonStyle(ButtonStyle.IMAGE_TEXT_BORDERLESS);
                    break;
            }
            btn.setBGColor(data.getInt(7));
            btn.setFGColor(data.getInt(8));

            val = data.getFloat(9);
            if(val<10)val = 10;
            if(val>40)val = 40;
            btn.setTextSize(val);

            String imgbase64 = data.getString(10);
            try{
                if(imgbase64!=null){
                    byte[] b = Base64.decode(imgbase64,Base64.DEFAULT);
                    btn.setImage(BitmapFactory.decodeByteArray(b, 0, b.length));
                }else{
                    btn.setImage(null);
                }
            }catch(IllegalArgumentException e){
                btn.setImage(null);
            }
            map.ButtonList().add(btn);

        }
        //map.invalidate();


        btn_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ShowButtonEditorDlg(true);
            }

        });

        btn_edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(map.getSelectedButtonPosition()==-1){
                    final DialogBox dlg1 = new DialogBox(v.getContext());
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 0));
                    dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 25));
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg1.show();
                    return;
                }

                ShowButtonEditorDlg(false);
            }
        });


        btn_del.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(map.getSelectedButtonPosition() == -1){
                    DialogBox dlg1 = new DialogBox(v.getContext());
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 0));
                    dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 25));
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg1.show();
                    return;
                }

                final DialogBox dlg1 = new DialogBox(v.getContext());
                dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 0));
                dlg1.setDialogIconType(DialogBox.IconType.QUESTION);
                dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 11));
                dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 6), new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        dlg1.dismiss();
                        map.ButtonList().remove(map.getSelectedButtonPosition());
                        map.setSelectedButtonPosition(-1);
                        map.invalidate();
                    }
                });
                dlg1.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 7), null);
                dlg1.show();
            }
        });




        tg_move.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                map.setMoveMode(isChecked);
            }

        });

        tg_resize.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                map.setResizeMode(isChecked);
            }

        });


        btn_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final DialogBox dlg = new DialogBox(v.getContext());
                dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 0));
                dlg.setDialogIconType(DialogBox.IconType.QUESTION);
                dlg.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 12));
                dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 6), new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        dlg.dismiss();
                        //delete old buttons
                        DBFunc.ExecQuery("DELETE FROM MapButtons WHERE map_id = "+MapID, true);

                        //insert new button
                        for(MapButton btn : map.ButtonList()){
                            String sql = "INSERT INTO MapButtons(map_id,func,name,x,y,width,height,style,bgcolor,fgcolor,fontsize,img) VALUES(";
                            sql += MapID +", ";
                            String funcStr = btn.getFuncType()+":"+btn.getObjID();
                            if(btn.getFuncType()==0 && btn.getObjID()==19){

                                String tblname = btn.getObjExt().trim();
                                if(!tblname.isEmpty()){
                                    funcStr += ":"+tblname;
                                }
                            }
                            sql += "'"+DBFunc.PurifyString(funcStr)+"'" +", ";
                            sql += "'"+DBFunc.PurifyString(btn.getText())+"'" +", ";
                            sql += btn.getX() +", ";
                            sql += btn.getY() +", ";
                            sql += btn.getWidth() +", ";
                            sql += btn.getHeight() +", ";
                            switch(btn.getButtonStyle()){
                                case NORMAL:
                                    sql += 0 +", ";
                                    break;
                                case IMAGE_TEXT:
                                    sql += 1 +", ";
                                    break;
                                case IMAGE:
                                    sql += 2 +", ";
                                    break;
                                case IMAGE_TEXT_BORDERLESS:
                                    sql += 3 +", ";
                                    break;
                            }
                            sql += btn.getBGColor() +", ";
                            sql += btn.getFGColor() +", ";
                            sql += btn.getTextSize() +", ";
                            if(btn.getImage()==null){
                                sql += "NULL)";
                            }else{
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                btn.getImage().compress(Bitmap.CompressFormat.PNG, 100, stream);
                                byte[] b = stream.toByteArray();
                                String imgbase64 = Base64.encodeToString(b, Base64.DEFAULT);
                                sql += "'"+imgbase64+"'" +")";
                            }

                            DBFunc.ExecQuery(sql, true);
                        }

                        changed = false;

                        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "FloorMap -> Edit -> ID: "+MapID);

                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 0));
                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 22));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                    }

                });
                dlg.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 7), null);
                dlg.show();
            }
        });


        btn_back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Close();
            }

        });



    }

    protected void onDestroy(){
        super.onDestroy();

        MapID = -1;
        map = null;
        changed=false;
        //deltaX = 0;
        //deltaY = 0;
        //selectedButton = -1;
        //selectedButtonEdit = -1;
        //selectionResize = false;
        //resizeType = 0;
        _tmpbmp = null;
    }

    @Override
    public void onBackPressed() {
//        Close();
        Intent i = new Intent(ActivityMapButtonSetup.this,ActivityMapSetup.class);
        startActivity(i);
        finish();
    }

    void Close(){
        if(changed){
            final DialogBox dlg = new DialogBox(CurrentActivity);
            dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 15));
            dlg.setDialogIconType(DialogBox.IconType.QUESTION);
            dlg.setMessage(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 16));
            dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 6), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dlg.dismiss();
                    CurrentActivity.finish();
                }
            });
            dlg.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 7), null);
            dlg.show();
        }else{
            CurrentActivity.finish();
        }
    }

    void ShowButtonEditorDlg(final boolean addNew){

        //if(selectedButton>=map.ButtonList().size()){
        //	return;
        //}

        final DialogBox dlg = new DialogBox(this);
        dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 1));

        //.getLayoutInflater().inflate(R.layout.dlg_mapbtncfg, root);
        View v = getLayoutInflater().inflate(R.layout.dlg_mapbtncfg, null, false);

        dlg.addView(v);

        TextView lbl_1 = (TextView)v.findViewById(R.id.lbl_1);
        TextView lbl_2 = (TextView)v.findViewById(R.id.lbl_2);
        TextView lbl_3 = (TextView)v.findViewById(R.id.lbl_3);
        TextView lbl_4 = (TextView)v.findViewById(R.id.lbl_4);
        TextView lbl_5 = (TextView)v.findViewById(R.id.lbl_5);

        final EditText et_name = (EditText)v.findViewById(R.id.et_name);
        final Spinner spn_type = (Spinner)v.findViewById(R.id.spn_tbltype);
        final ArrayAdapter<String> adapt_type = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapt_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_type.setAdapter(adapt_type);
        adapt_type.add(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 30)); //0:19
        adapt_type.add(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 31)); //0
        adapt_type.add(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 32)); //6


        final List<Integer> objID = new ArrayList<Integer>();

        final LinearLayout lay_obj = (LinearLayout)v.findViewById(R.id.lay_obj);
        final Spinner spn_obj = (Spinner)v.findViewById(R.id.spn_obj);
        final ArrayAdapter<String> adapt_obj = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapt_obj.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_obj.setAdapter(adapt_obj);

        final LinearLayout lay_tblname = (LinearLayout)v.findViewById(R.id.lay_tblname);
        final EditText et_tbl_name = (EditText)v.findViewById(R.id.et_tblname);


        spn_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                switch(position){
                    default:
                        lay_obj.setVisibility(View.GONE);
                        lay_tblname.setVisibility(View.VISIBLE);
                        adapt_obj.clear();
                        et_tbl_name.setText("");

                        break;
                    case 1://FUNCTION
                        lay_obj.setVisibility(View.VISIBLE);
                        lay_tblname.setVisibility(View.GONE);
                        objID.clear();
                        adapt_obj.clear();
                        objID.add(0);
                        adapt_obj.add(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 33));
                        objID.add(2);
                        adapt_obj.add(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 34));
                        objID.add(20);
                        adapt_obj.add(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 35));
                        et_tbl_name.setText("");
                        break;
                    case 2://Map Layout
                        lay_obj.setVisibility(View.VISIBLE);
                        lay_tblname.setVisibility(View.GONE);
                        objID.clear();
                        adapt_obj.clear();
                        et_tbl_name.setText("");
                        Cursor c = DBFunc.Query("SELECT id,name FROM MapLayout ORDER BY id ASC", true);
                        while(c.moveToNext()){
                            objID.add(c.getInt(0));
                            adapt_obj.add(c.getString(1));
                        }
                        c.close();
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}

        });

        final FlatButton btn_txtcolor = (FlatButton)v.findViewById(R.id.btn_txtcolor);
        final FlatButton btn_bgcolor = (FlatButton)v.findViewById(R.id.btn_bgcolor);

        btn_bgcolor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final Dialog dlg = new Dialog(CurrentActivity);
                dlg.setContentView(R.layout.dlg_colorpicker);
                dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 2));
                final FlatButton vcolor = (FlatButton)dlg.findViewById(R.id.btn_color);
                final SeekBar sb_red = (SeekBar)dlg.findViewById(R.id.sb_color_red);
                final SeekBar sb_green = (SeekBar)dlg.findViewById(R.id.sb_color_green);
                final SeekBar sb_blue = (SeekBar)dlg.findViewById(R.id.sb_color_blue);
                sb_red.setProgress((btn_bgcolor.getBackgroundColor()>>16)&0xFF);
                sb_green.setProgress((btn_bgcolor.getBackgroundColor()>>8)&0xFF);
                sb_blue.setProgress(btn_bgcolor.getBackgroundColor()&0xFF);
                vcolor.setBackgroundColor(Color.argb(255, sb_red.getProgress(),sb_green.getProgress(),sb_blue.getProgress()));
                vcolor.RefreshLook();
                sb_red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        vcolor.setBackgroundColor(Color.argb(255, sb_red.getProgress(),sb_green.getProgress(),sb_blue.getProgress()));
                        vcolor.RefreshLook();
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {}
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {}
                });

                sb_green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        vcolor.setBackgroundColor(Color.argb(255, sb_red.getProgress(),sb_green.getProgress(),sb_blue.getProgress()));
                        vcolor.RefreshLook();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {}

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {}
                });

                sb_blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        vcolor.setBackgroundColor(Color.argb(255, sb_red.getProgress(),sb_green.getProgress(),sb_blue.getProgress()));
                        vcolor.RefreshLook();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {}

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {}
                });

                Button btn_ok = (Button)dlg.findViewById(R.id.btn_color_ok);
                Button btn_cancel = (Button)dlg.findViewById(R.id.btn_color_cancel);

                btn_ok.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        //int bgcolor = Color.argb(255, sb_red.getProgress(), sb_green.getProgress(), sb_blue.getProgress());
                        btn_bgcolor.setBackgroundColor(Color.argb(255, sb_red.getProgress(),sb_green.getProgress(),sb_blue.getProgress()));
                        btn_bgcolor.RefreshLook();
                        dlg.dismiss();
                    }

                });

                btn_cancel.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        dlg.dismiss();
                    }
                });

                TextView lbl_1 = (TextView)dlg.findViewById(R.id.lbl_1);
                TextView lbl_2 = (TextView)dlg.findViewById(R.id.lbl_2);
                TextView lbl_3 = (TextView)dlg.findViewById(R.id.lbl_3);

                lbl_1.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 102));
                lbl_2.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 103));
                lbl_3.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 104));

                vcolor.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 105));

                btn_ok.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0));
                btn_cancel.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 1));

                dlg.show();
            }
        });

        btn_txtcolor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final Dialog dlg = new Dialog(CurrentActivity);
                dlg.setContentView(R.layout.dlg_colorpicker);
                dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 3));
                final FlatButton vcolor = (FlatButton)dlg.findViewById(R.id.btn_color);
                final SeekBar sb_red = (SeekBar)dlg.findViewById(R.id.sb_color_red);
                final SeekBar sb_green = (SeekBar)dlg.findViewById(R.id.sb_color_green);
                final SeekBar sb_blue = (SeekBar)dlg.findViewById(R.id.sb_color_blue);
                sb_red.setProgress((btn_txtcolor.getTextColor()>>16)&0xFF);
                sb_green.setProgress((btn_txtcolor.getTextColor()>>8)&0xFF);
                sb_blue.setProgress(btn_txtcolor.getTextColor()&0xFF);
                vcolor.setBackgroundColor(Color.argb(255, sb_red.getProgress(),sb_green.getProgress(),sb_blue.getProgress()));
                vcolor.RefreshLook();
                sb_red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        vcolor.setBackgroundColor(Color.argb(255, sb_red.getProgress(),sb_green.getProgress(),sb_blue.getProgress()));
                        vcolor.RefreshLook();
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {}
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {}
                });

                sb_green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        vcolor.setBackgroundColor(Color.argb(255, sb_red.getProgress(),sb_green.getProgress(),sb_blue.getProgress()));
                        vcolor.RefreshLook();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {}

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {}
                });

                sb_blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        vcolor.setBackgroundColor(Color.argb(255, sb_red.getProgress(),sb_green.getProgress(),sb_blue.getProgress()));
                        vcolor.RefreshLook();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {}

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {}
                });

                Button btn_ok = (Button)dlg.findViewById(R.id.btn_color_ok);
                Button btn_cancel = (Button)dlg.findViewById(R.id.btn_color_cancel);

                btn_ok.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        //int bgcolor = Color.argb(255, sb_red.getProgress(), sb_green.getProgress(), sb_blue.getProgress());
                        btn_txtcolor.setTextColor(Color.argb(255, sb_red.getProgress(),sb_green.getProgress(),sb_blue.getProgress()));
                        btn_txtcolor.RefreshLook();
                        dlg.dismiss();
                    }

                });

                btn_cancel.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        dlg.dismiss();
                    }
                });

                TextView lbl_1 = (TextView)dlg.findViewById(R.id.lbl_1);
                TextView lbl_2 = (TextView)dlg.findViewById(R.id.lbl_2);
                TextView lbl_3 = (TextView)dlg.findViewById(R.id.lbl_3);

                lbl_1.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 102));
                lbl_2.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 103));
                lbl_3.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 104));

                vcolor.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 105));

                btn_ok.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0));
                btn_cancel.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 1));

                dlg.show();
            }
        });

        final Button btn_img = (Button)v.findViewById(R.id.btn_img);
        btn_img.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ShowImageSelector(v.getContext(), _tmpbmp);
            }

        });

        final Spinner spn_fontsize = (Spinner)v.findViewById(R.id.spn_fontsize);
        final ArrayAdapter<String> adapt_fontsize = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapt_fontsize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_fontsize.setAdapter(adapt_fontsize);
        for (int i = 0; i < 31; i++) {
            adapt_fontsize.add("" + (10 + i));
        }
        spn_fontsize.setSelection(8);

        final Spinner spn_style = (Spinner)v.findViewById(R.id.spn_style);
        final ArrayAdapter<String> adapt_style = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapt_style.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_style.setAdapter(adapt_style);
        adapt_style.add(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 36));
        adapt_style.add(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 37));
        adapt_style.add(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 38));
        adapt_style.add(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 39));


        float btn_size_w = 10;
        float btn_size_h = 10;
        if(map.getSelectedButtonPosition()>-1){
            MapButton btn = map.getSelectedButton();
            et_name.setText(btn.getText());
            int size = (int)btn.getTextSize()-10;
            if(size<0)size = 0;
            if(size>30)size = 30;
            btn_size_w = btn.getWidth();
            btn_size_h = btn.getHeight();

            spn_fontsize.setSelection(size);
            btn_bgcolor.setBackgroundColor(Color.argb(255, (btn.bgColor>>16) & 0xFF, (btn.bgColor>>8) & 0xFF, btn.bgColor & 0xFF));
            btn_txtcolor.setTextColor(Color.argb(255, (btn.fgColor>>16) & 0xFF, (btn.fgColor>>8) & 0xFF, btn.fgColor & 0xFF));
            _tmpbmp = btn.getImage();

            switch(btn.getButtonStyle()){
                case NORMAL:
                    spn_style.setSelection(0);
                    break;
                case IMAGE_TEXT:
                    spn_style.setSelection(1);
                    break;
                case IMAGE:
                    spn_style.setSelection(2);
                    break;
                case IMAGE_TEXT_BORDERLESS:
                    spn_style.setSelection(3);
                    break;
            }

            if(btn.getFuncType()==0 && btn.getObjID()==19){
                et_tbl_name.setText(btn.getObjExt().trim());
            }else{
                et_tbl_name.setText("");
            }
        }

        final float fbtn_size_w = btn_size_w;
        final float fbtn_size_h = btn_size_h;


        dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(addNew){
                    MapButton btn = new MapButton();
                    btn.setBGColor(btn_bgcolor.getBackgroundColor());
                    btn.setFGColor(btn_txtcolor.getTextColor());
                    btn.setText(et_name.getText().toString());
                    btn.setTextSize(spn_fontsize.getSelectedItemPosition()+10);
                    btn.setImage(_tmpbmp);
                    switch(spn_style.getSelectedItemPosition()){
                        case 0:
                            btn.setButtonStyle(ButtonStyle.NORMAL);
                            break;
                        case 1:
                            btn.setButtonStyle(ButtonStyle.IMAGE_TEXT);
                            break;
                        case 2:
                            btn.setButtonStyle(ButtonStyle.IMAGE);
                            break;
                        case 3:
                            btn.setButtonStyle(ButtonStyle.IMAGE_TEXT_BORDERLESS);
                            break;
                    }
                    btn.setX(0);
                    btn.setY(0);
                    btn.setWidth(fbtn_size_w);
                    btn.setHeight(fbtn_size_h);
                    String tblname = et_tbl_name.getText().toString().trim();
                    switch(spn_type.getSelectedItemPosition()){
                        default://balance number
                            btn.setFuncType(0);
                            btn.setObjID(19);
                            btn.setObjExt(tblname);
                            break;
                        case 1://Function
                            btn.setFuncType(0);
                            btn.setObjID(objID.get(spn_obj.getSelectedItemPosition()));
                            btn.setObjExt("");
                            break;
                        case 2://Map Layout
                            btn.setFuncType(6);
                            btn.setObjID(objID.get(spn_obj.getSelectedItemPosition()));
                            btn.setObjExt("");
                            break;
                    }



                    map.ButtonList().add(btn);
                    //selectedButtonEdit = map.ButtonList().size()-1;

                    map.setSelectedButtonPosition(map.ButtonList().size()-1);
                    map.invalidate(btn.getBound());
                    //map.invalidate();


                }else{
                    MapButton btn = map.getSelectedButton();
                    btn.setBGColor(btn_bgcolor.getBackgroundColor());
                    btn.setFGColor(btn_txtcolor.getTextColor());
                    btn.setText(et_name.getText().toString());
                    btn.setTextSize(spn_fontsize.getSelectedItemPosition()+10);
                    btn.setImage(_tmpbmp);
                    switch(spn_style.getSelectedItemPosition()){
                        case 0:
                            btn.setButtonStyle(ButtonStyle.NORMAL);
                            break;
                        case 1:
                            btn.setButtonStyle(ButtonStyle.IMAGE_TEXT);
                            break;
                        case 2:
                            btn.setButtonStyle(ButtonStyle.IMAGE);
                            break;
                        case 3:
                            btn.setButtonStyle(ButtonStyle.IMAGE_TEXT_BORDERLESS);
                            break;
                    }
                    String tblname = et_tbl_name.getText().toString().trim();

                    switch(spn_type.getSelectedItemPosition()){
                        default://balance number
                            btn.setFuncType(0);
                            btn.setObjID(19);
                            btn.setObjExt(tblname);
                            break;
                        case 1://Function
                            btn.setFuncType(0);
                            btn.setObjID(objID.get(spn_obj.getSelectedItemPosition()));
                            btn.setObjExt("");
                            break;
                        case 2://Map Layout
                            btn.setFuncType(6);
                            btn.setObjID(objID.get(spn_obj.getSelectedItemPosition()));
                            btn.setObjExt("");
                            break;
                    }

                    map.invalidate(btn.getBound());
                }
                dlg.dismiss();
                changed = true;
            }
        });

        dlg.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 1), new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dlg.dismiss();
            }
        });

        lbl_1.setText(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 100));
        lbl_2.setText(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 103));
        lbl_3.setText(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 104));
        lbl_4.setText(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 105));
        lbl_5.setText(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 106));

        btn_txtcolor.setText(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 107));
        btn_bgcolor.setText(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 108));
        btn_img.setText(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 109));

        dlg.show();
    }

    void ShowImageSelector(Context context, final Bitmap imgtmp){
        final DialogBox dlg = new DialogBox(context);
        dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 4));
        ImageView imgview = new ImageView(context);
        if(imgtmp!=null){
            imgview.setScaleType(ImageView.ScaleType.FIT_XY);
            imgview.setImageBitmap(imgtmp);
        }
        dlg.addView(imgview);
        dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 40), new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dlg.dismiss();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });

        if(_tmpbmp!=null){
            dlg.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 41), new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    dlg.dismiss();
                    _tmpbmp = null;
                }
            });
        }

        dlg.setButton3OnClickListener(StrTextConst.GetText(StrTextConst.TextType.CFGMAPB, 42), null);
        dlg.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                _tmpbmp = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                //img = Bitmap.createScaledBitmap(_img, 128, 128, true);
                ShowImageSelector(CurrentActivity, _tmpbmp);
            } catch (IOException e) {
                Logger.WriteLog("ActivityMapButtonSetup",e.toString());
                //e.printStackTrace();
            }
        }
    }
}
