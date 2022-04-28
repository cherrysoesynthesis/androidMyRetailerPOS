package com.dcs.myretailer.app.Setting;

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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Activity.SettingActivity;
import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.DialogBox;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.FlatButton;
import com.dcs.myretailer.app.Logger;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.ActivityMapSetupBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActivityMapSetup extends AppCompatActivity {
    static Activity CurrentActivity;
    static Bitmap bgimg = null;
    static int bgcolor = Color.WHITE;
    final BitmapFactory.Options options = new BitmapFactory.Options();
    Context context;
    ActivityMapSetupBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_map_setup);
        CurrentActivity = this;

        context = ActivityMapSetup.this;

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Map Layout");

        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        if (terminalTypeVal.equals(Constraints.PAX_E600M)){
            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            linearOverAllParams.leftMargin = 30;
            binding.layOverAll.setLayoutParams(linearOverAllParams);
        }

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        final ArrayAdapter<String> adapt_list = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        final List<Integer> ID = new ArrayList<Integer>();
        adapt_list.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spnList.setAdapter(adapt_list);

        binding.btnColor.setBackgroundColor(bgcolor);

        binding.btnColor.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final Dialog dlg = new Dialog(CurrentActivity);
                dlg.setContentView(R.layout.dlg_colorpicker);
                dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 1));

                final FlatButton vcolor = (FlatButton)dlg.findViewById(R.id.btn_color);
                final SeekBar sb_red = (SeekBar)dlg.findViewById(R.id.sb_color_red);
                final SeekBar sb_green = (SeekBar)dlg.findViewById(R.id.sb_color_green);
                final SeekBar sb_blue = (SeekBar)dlg.findViewById(R.id.sb_color_blue);
                sb_red.setProgress((bgcolor>>16)&0xFF);
                sb_green.setProgress((bgcolor>>8)&0xFF);
                sb_blue.setProgress(bgcolor&0xFF);
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
                        bgcolor = Color.argb(255, sb_red.getProgress(), sb_green.getProgress(), sb_blue.getProgress());
                        binding.btnColor.setBackgroundColor(Color.argb(255, (bgcolor>>16)&0xFF,(bgcolor>>8)&0xFF,(bgcolor)&0xFF));
                        binding.btnColor.RefreshLook();
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

        binding.btnImg.setButtonStyle(ButtonStyle.IMAGE_TEXT);
        binding.btnImg.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ShowImageSelector(CurrentActivity,bgimg);

            }

        });

        binding.btnEdit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final int pos = binding.spnList.getSelectedItemPosition();
                if(pos==Spinner.INVALID_POSITION){
                    DialogBox dlg1 = new DialogBox(v.getContext());
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 0));
                    dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 25));
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg1.show();
                    return;
                }

                Intent intent = new Intent(CurrentActivity.getBaseContext(),ActivityMapButtonSetup.class);
                Bundle b = new Bundle();
                b.putInt("MapID", ID.get(binding.spnList.getSelectedItemPosition()));
                intent.putExtras(b);
                CurrentActivity.startActivity(intent);
                finish();
            }

        });

        Cursor data = DBFunc.Query("SELECT ID,Name FROM MapLayout ORDER BY ID ASC", true);
        if(data!=null){
            while(data.moveToNext()){
                ID.add(data.getInt(0));
                adapt_list.add(data.getString(1));
            }
        }
        data.close();



        binding.btnSave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final String name = binding.etName.getText().toString();

                if(name.isEmpty()){
                    DialogBox dlg1 = new DialogBox(v.getContext());
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 0));
                    dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 20));
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg1.show();
                    return;
                }

                Cursor data = DBFunc.Query("SELECT ID FROM MapLayout ORDER BY ID ASC", true);
                int count = data.getCount();
                data.close();



                if(count==0){
                    if(bgimg==null){
                        DBFunc.ExecQuery("INSERT INTO MapLayout (name, image, bgcolor) VALUES ('"+DBFunc.PurifyString(name)+"',NULL,"+bgcolor+")", true);
                    }else{
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bgimg.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] b = stream.toByteArray();
                        String imgbase64 = Base64.encodeToString(b, Base64.DEFAULT);
                        DBFunc.ExecQuery("INSERT INTO MapLayout (name, image, bgcolor) VALUES ('"+DBFunc.PurifyString(name)+"','"+DBFunc.PurifyString(imgbase64)+"',"+bgcolor+")", true);
                    }

                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "FloorMap -> Add -> Name: "+name);

                    adapt_list.clear();
                    ID.clear();

                    data = DBFunc.Query("SELECT id, name FROM MapLayout ORDER BY ID ASC", true);
                    if(data!=null){
                        while(data.moveToNext()){
                            ID.add(data.getInt(0));
                            adapt_list.add(data.getString(1));
                        }
                        data.close();
                        binding.spnList.setSelection(ID.size()-1);
                    }

                    DialogBox dlg1 = new DialogBox(v.getContext());
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 0));
                    dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 22));
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg1.show();
                    return;
                }

                final DialogBox dlg = new DialogBox(v.getContext());
                dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 0));
                dlg.setDialogIconType(DialogBox.IconType.QUESTION);
                dlg.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 21));

                dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 4), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg.dismiss();

                        if(bgimg==null){
                            DBFunc.ExecQuery("INSERT INTO MapLayout (name, image, bgcolor) VALUES ('"+DBFunc.PurifyString(name)+"',NULL,"+bgcolor+")", true);
                        }else{
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bgimg.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byte[] b = stream.toByteArray();
                            String imgbase64 = Base64.encodeToString(b, Base64.DEFAULT);
                            DBFunc.ExecQuery("INSERT INTO MapLayout (name, image, bgcolor) VALUES ('"+DBFunc.PurifyString(name)+"','"+DBFunc.PurifyString(imgbase64)+"',"+bgcolor+")", true);
                        }

                        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "FloorMap -> Add -> Name: "+name);

                        adapt_list.clear();
                        ID.clear();

                        Cursor data = DBFunc.Query("SELECT id, name FROM MapLayout ORDER BY ID ASC", true);
                        if(data!=null){
                            while(data.moveToNext()){
                                ID.add(data.getInt(0));
                                adapt_list.add(data.getString(1));
                            }
                            data.close();
                            binding.spnList.setSelection(ID.size()-1);
                        }

                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 0));
                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 22));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;

                    }
                });
                dlg.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 5), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg.dismiss();
                        int pos = binding.spnList.getSelectedItemPosition();
                        int id = ID.get(pos);

                        if(bgimg==null){
                            DBFunc.ExecQuery("UPDATE MapLayout SET name = '"+DBFunc.PurifyString(name)+"', image = NULL, bgcolor = "+bgcolor+" WHERE ID = "+id, true);
                        }else{
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bgimg.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byte[] b = stream.toByteArray();
                            String imgbase64 = Base64.encodeToString(b, Base64.DEFAULT);
                            DBFunc.ExecQuery("UPDATE MapLayout SET name = '"+DBFunc.PurifyString(name)+"', image = '"+DBFunc.PurifyString(imgbase64)+"', bgcolor = "+bgcolor+" WHERE ID = "+id, true);
                        }

                        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "FloorMap -> Add -> Name: "+name);

                        adapt_list.clear();
                        ID.clear();

                        Cursor data = DBFunc.Query("SELECT id, name FROM MapLayout ORDER BY ID ASC", true);
                        if(data!=null){
                            while(data.moveToNext()){
                                ID.add(data.getInt(0));
                                adapt_list.add(data.getString(1));
                            }
                            data.close();
                           binding.spnList.setSelection(ID.size()-1);
                        }

                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 0));
                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 23));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }
                });
                dlg.setButton3OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 1), null);

                dlg.show();
            }

        });

        binding.spnList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position<ID.size()){
                    int _id = ID.get(position);
                    Cursor data = DBFunc.Query("SELECT name,image,bgcolor FROM MapLayout WHERE ID = "+_id+" LIMIT 1", true);

                    if(data!=null){
                        if(data.moveToNext()){
                            binding.etName.setText(data.getString(0));
                            String imgbase64 = data.getString(1);
                            if(imgbase64!=null){
                                byte[] b = Base64.decode(imgbase64, Base64.DEFAULT);
                                bgimg = BitmapFactory.decodeByteArray(b, 0, b.length, options);
                            }else{
                                bgimg = null;
                            }
                            bgcolor = data.getInt(2);
                            binding.btnColor.setBackgroundColor(bgcolor);


                        }
                        data.close();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        binding.btnDel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final int pos = binding.spnList.getSelectedItemPosition();
                if(pos==Spinner.INVALID_POSITION){
                    DialogBox dlg1 = new DialogBox(v.getContext());
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 0));
                    dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 25));
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg1.show();
                    return;
                }



                final DialogBox dlg = new DialogBox(v.getContext());
                dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 0));
                dlg.setDialogIconType(DialogBox.IconType.QUESTION);
                dlg.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 11, adapt_list.getItem(pos)));
                dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 6), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg.dismiss();
                        int id = ID.get(pos);
                        DBFunc.ExecQuery("DELETE FROM MapButtons WHERE map_id = "+id, true);
                        DBFunc.ExecQuery("DELETE FROM MapLayout WHERE id = "+id, true);

                        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "FloorMap -> Delete -> Name: "+adapt_list.getItem(pos));

                        adapt_list.clear();
                        ID.clear();
                        Cursor data = DBFunc.Query("SELECT id, name FROM MapLayout ORDER BY ID ASC", true);
                        if(data!=null){
                            while(data.moveToNext()){
                                ID.add(data.getInt(0));
                                adapt_list.add(data.getString(1));
                            }
                            data.close();
                            if(pos<ID.size()){
                                binding.spnList.setSelection(pos);
                            }else{
                                if(ID.size()>0){
                                    binding.spnList.setSelection(ID.size()-1);
                                }
                            }
                        }

                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 0));
                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 24));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                    }
                });

                dlg.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 7), null);
                dlg.show();

            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CurrentActivity.finish();
            }
        });


        this.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 0));
        //this.getActionBar().setIcon(R.drawable.img_cfg_map);
        binding.lbl1.setText(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 100));
        binding.lbl2.setText(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 101));
        binding.lbl3.setText(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 102));

        binding.btnColor.setText(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 103));
        binding.btnImg.setText(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 104));
        binding.btnEdit.setText(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 105));

        binding.btnSave.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 2));
        binding.btnBack.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 100));
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.activity_add_new_product_actions, menu);
//        inflater.inflate(R.menu.activity_add_new_product_actions, menu);
////        Log.i("dsdfdfdd___", String.valueOf(myAdapter.getSelectedItemCount()));
////        if (myAdapter.getSelectedItemCount() == 0) {
////            menu.findItem(R.id.action_delete_product).setVisible(false);
////        } else {
////            menu.findItem(R.id.action_delete_product).setVisible(true);
////        }
//
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        //return true;
//
//
//        return super.onCreateOptionsMenu(menu);
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_add_new_product) {
//            //int latestID = findLatestID("PLU");
//            finish();
//            Intent addNewProduct = new Intent(getApplicationContext(),AddNewProductActivity.class);
//            addNewProduct.putExtra("ID","null");
//            startActivity(addNewProduct);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


    void ShowImageSelector(Context context, final Bitmap imgtmp){
        final DialogBox dlg = new DialogBox(context);
        dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 4));
        ImageView imgview = new ImageView(context);
        if(imgtmp!=null){
            imgview.setScaleType(ImageView.ScaleType.FIT_XY);
            imgview.setImageBitmap(imgtmp);
        }
        dlg.addView(imgview);
        dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 40), new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dlg.dismiss();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });

        if(bgimg!=null){
            dlg.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 41), new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    dlg.dismiss();
                    bgimg = null;
                }
            });
        }

        dlg.setButton3OnClickListener(StrTextConst.GetText(StrTextConst.TextType.CFGMAPL, 42), null);
        dlg.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                bgimg = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                //img = Bitmap.createScaledBitmap(_img, 128, 128, true);
                ShowImageSelector(CurrentActivity, bgimg);
            } catch (IOException e) {
                Logger.WriteLog("ActvityMapSetup",e.toString());
                //e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(ActivityMapSetup.this);
        Intent i = new Intent(ActivityMapSetup.this, SettingActivity.class);
        startActivity(i);
        finish();
    }
}
