
package com.dcs.myretailer.app;


import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DialogBox {
	private Dialog dlg;
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private ImageView img_icon;
	private TextView txt_title;
	private TextView txt_msg;
	private LinearLayout lay_msg;
	private Context context;
	public DialogBox(Context context){
		this.context = context;
		dlg = new Dialog(this.context);
		dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dlg.setContentView(R.layout.dlg_msgbox_detail);
		dlg.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		dlg.setCancelable(true);
		lay_msg = (LinearLayout)dlg.findViewById(R.id.lay_msg);

		img_icon = (ImageView)dlg.findViewById(R.id.img_icon);
		img_icon.setVisibility(View.GONE);
		txt_title = (TextView)dlg.findViewById(R.id.txt_title);
		txt_msg = (TextView)dlg.findViewById(R.id.txt_msg);

		btn1 = (Button)dlg.findViewById(R.id.btn_1);
		btn2 = (Button)dlg.findViewById(R.id.btn_2);
		btn3 = (Button)dlg.findViewById(R.id.btn_3);

		btn1.setVisibility(View.GONE);
		btn2.setVisibility(View.GONE);
		btn3.setVisibility(View.GONE);

	}

	public Context getContext(){
		return context;
	}

	public void setWindowSize(float width, float height){
		//dlg.getWindow().setLayout(/ViewGroup., height)
		//LayoutParams params = new LayoutParams();
		if(width<0)width = 0;
		if(width>1)width = 1;
		if(height<0)height = 0;
		if(height>1)height = 1;

		int winWidth = (int)((float)context.getApplicationContext().getResources().getDisplayMetrics().widthPixels * width);
		int winHeight = (int)((float)context.getApplicationContext().getResources().getDisplayMetrics().heightPixels * height);

		dlg.getWindow().setLayout(winWidth,winHeight);
	}

	public Window getWindow(){
		return dlg.getWindow();
	}



	public void setDialogIconType(IconType icon){
		switch(icon){
			case INFORMATION:
				img_icon.setImageResource(R.drawable.setting);
				img_icon.setVisibility(View.VISIBLE);
				break;
			case QUESTION:
				img_icon.setImageResource(R.drawable.setting);
				img_icon.setVisibility(View.VISIBLE);
				break;
			case WARNING:
				img_icon.setImageResource(R.drawable.setting);
				img_icon.setVisibility(View.VISIBLE);
				break;
			case ERROR:
				img_icon.setImageResource(R.drawable.setting);
				img_icon.setVisibility(View.VISIBLE);
				break;
			case NONE:
			default:
				img_icon.setVisibility(View.GONE);
				break;
		}
	}

	public void setTitle(String text){
		txt_title.setText(text);
	}

	public void setMessage(String text){
 		if(text.isEmpty()){
			txt_msg.setVisibility(View.GONE);
		}else{
			txt_msg.setVisibility(View.VISIBLE);
		}
		txt_msg.setText(text);

	}

	public void show(){
		try {
			if (!dlg.isShowing()) dlg.show();
		} catch (Exception e){

		}
	}

	public void dismiss(){
		try {
			if(dlg.isShowing())dlg.dismiss();
		} catch (Exception e){

		}
	}

	public void setButton1OnClickListener(String text, OnClickListener listener){
		btn1.setText(text);
		if(listener==null){
			btn1.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					dlg.dismiss();
				}
			});
		}else{
			btn1.setOnClickListener(listener);
		}
		btn1.setVisibility(View.VISIBLE);
		dlg.setCancelable(false);

	}

	public void setButton2OnClickListener(String text, OnClickListener listener){
		btn2.setText(text);
		if(listener==null){
			btn2.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					dlg.dismiss();
				}
			});
		}else{
			btn2.setOnClickListener(listener);
		}
		btn2.setVisibility(View.VISIBLE);

		dlg.setCancelable(false);
	}

	public void setButton3OnClickListener(String text, OnClickListener listener){
		btn3.setText(text);
		if(listener==null){
			btn3.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					dlg.dismiss();
				}
			});
		}else{
			btn3.setOnClickListener(listener);
		}
		btn3.setVisibility(View.VISIBLE);
		dlg.setCancelable(false);
	}

	public boolean performButton1Click(){
		if(btn1.getVisibility()==View.VISIBLE){
			return btn1.performClick();
		}else{
			return false;
		}
	}

	public boolean performButton2Click(){
		if(btn2.getVisibility()==View.VISIBLE){
			return btn2.performClick();
		}else{
			return false;
		}
	}

	public boolean performButton3Click(){
		if(btn3.getVisibility()==View.VISIBLE){
			return btn3.performClick();
		}else{
			return false;
		}
	}

	public void AllowCancelable(boolean cancel){
		dlg.setCancelable(cancel);
		dlg.setCanceledOnTouchOutside(cancel);
	}

	public void FullscreenDialog(boolean flag){
		if(flag){
			dlg.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		}else{
			dlg.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		}
	}

	public void addView(View v){
		lay_msg.addView(v);
	}

	public enum IconType{
		NONE,
		INFORMATION,
		QUESTION,
		WARNING,
		ERROR;
	}


}
