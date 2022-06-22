package com.dcs.myretailer.app;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class Logger {
	private static FileWriter writer;
	private static BufferedWriter bw;
	
	
	public static void WriteLog(String app, String msg){
		Log.e(app, msg);
		
		
		if(bw==null){
			try{
				File dir = new File(Allocator.DataPath+"/log");

				if(!dir.exists()){
					if(!dir.isDirectory()){
						dir.mkdirs();
					}
				}
				Calendar cal = Calendar.getInstance();
				writer = new FileWriter("sdcard/posdata/log/"+String.format("%04d%02d%02d_%02d%02d%02d%03d.log", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND), cal.get(Calendar.MILLISECOND)));

				bw = new BufferedWriter(writer);
			}catch(Exception e){}
		}
		if(bw!=null){
			try {
				Calendar cal = Calendar.getInstance();
				String str = "DateTime: "+String.format("%04d%02d%02d_%02d%02d%02d%03d.log", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND), cal.get(Calendar.MILLISECOND))+"\r\n";
				str += "Status: "+app+"\r\n";
				str += "Error: \r\n";
				str += msg+"\r\n";
				str += "=============================\r\n";
				bw.write(str);
				bw.flush();
			} catch (IOException e) {}
		}
	}
}
