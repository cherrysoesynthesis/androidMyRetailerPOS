package com.dcs.myretailer.app.Database;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
//import android.database.sqlite.SQLiteDatabase;
import net.sqlcipher.Cursor;
import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;

import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.Logger;
import com.dcs.myretailer.app.Query.Query;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBFunc {
	public static void OpenDBFromDisk(String path, String DBName, Context context) throws IOException{
		InputStream is = new FileInputStream(path);
		OpenDBFromDisk(is,DBName, context);
	}
	
	public static void OpenDBFromDisk(InputStream inputStream, String DBName, Context context) throws IOException{

		InputStream is = inputStream;
		context.getDatabasePath(DBName).getParentFile().mkdir();
		if(context.getDatabasePath(DBName).exists()){
			context.getDatabasePath(DBName).delete();
		}
		OutputStream os = new FileOutputStream(context.getDatabasePath(DBName));
		
		byte[] b = new byte[1024];
		int len = 0;
		while((len = is.read(b))>0){
			os.write(b,0,len);
		}
		os.flush();
		os.close();
		is.close();

	}
	
	public static void SaveDBToDisk(String outpath, String DBpath) throws IOException{
		InputStream is = new FileInputStream(DBpath);
		
		//File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/posdata");
		//f.mkdir();
		OutputStream os = new FileOutputStream(outpath);
		byte[] b = new byte[1024];
		int len = 0;
		while((len = is.read(b))>0){
			os.write(b,0,len);
		}
		os.flush();
		os.close();
		is.close();
	}
	
	public static long GetMasterDBSize(Context context){
		if(!context.getDatabasePath("master.db").exists()){
			return -1;//not available
		}
		return context.getDatabasePath("master.db").length();
	}
	
	public static long GetTransactDBSize(Context context){
		if(!context.getDatabasePath("transact.db").exists()){
			return -1;//not available
		}
		return context.getDatabasePath("transact.db").length();
	}
	
	public static void CloseDBFromInternal(){
		if(Allocator.DB!=null){
			Allocator.DB.close();
		}
	}
	
	public static void CloseTransactDBFromInternal(){
		if(Allocator.TransactDB!=null){
			Allocator.TransactDB.close();
		}
	}
	
	public static boolean LoadDBFromInternal(Context context){
		try {
			Log.i("LoadDBFromInternal", "master" + context.getDatabasePath("master.db").exists());
			if (context.getDatabasePath("master.db").exists()) {
				SQLiteDatabase.loadLibs(context);
				//Allocator.DB = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath("master.db").getAbsolutePath(), null);
				Log.i("LoadDBFromInternal_", "LoadDBFgetAbsolutePath_" + context.getDatabasePath("master.db").getAbsolutePath());

				Allocator.DB = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath("master.db").getAbsolutePath(), "test123", null);
				Cursor c = Allocator.DB.rawQuery("PRAGMA journal_mode=ON", null);
				c.close();
				c = Allocator.DB.rawQuery("PRAGMA foreign_keys=ON", null);
				c.close();
				return true;
			}
			return false;
		} catch (Exception e){
			return false;
		}
	}
	
	public static boolean LoadTransactDBFromInternal(Context context){
		if(context.getDatabasePath("transact.db").exists()){
			SQLiteDatabase.loadLibs(context);
			Allocator.TransactDB = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath("transact.db").getAbsolutePath(),"test123", null);
			Cursor c = Allocator.TransactDB.rawQuery("PRAGMA journal_mode=ON", null);
			c.close();
			c = Allocator.TransactDB.rawQuery("PRAGMA foreign_keys=ON", null);
			c.close();
			return true;
		}
		return false;
		
	}
	
	public static boolean CreateResetDB(Context context, boolean isMaster){
		Log.i("LoadDBFromInternal_","CreateResetDBl_____"+
				isMaster);
		try{
			if(isMaster){
				DBFunc.CloseDBFromInternal();
				context.deleteDatabase("master.db");
			}else{
				DBFunc.CloseTransactDBFromInternal();
				context.deleteDatabase("transact.db");
			}

			Map<String,String> tblmap = DBFunc.LoadTableHeader(context,isMaster);
			Log.i("gds__tblmap__","tblmap__"+tblmap);

			SQLiteDatabase db = null;
			SQLiteDatabase.loadLibs(context);
			if(isMaster){
				File dirdb = context.getDatabasePath("master.db").getAbsoluteFile().getParentFile();
				dirdb.mkdir();
				db = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath("master.db").getAbsolutePath(),"test123", null);

//				File dirdb = context.getDatabasePath("master.db").getAbsoluteFile().getParentFile();
//				dirdb.mkdir();
//				db = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath("master.db").getAbsolutePath(),"test123", null);

//				File databaseFile = context.getDatabasePath("master.db");
//				databaseFile.mkdirs();
//				databaseFile.delete();
//				db = SQLiteDatabase.openOrCreateDatabase(databaseFile, "test123", null);
//				database.execSQL("create table t1(a, b)");
//				database.execSQL("insert into t1(a, b) values(?, ?)", new Object[]{"one for the money",
//						"two for the show"});

			}else{
				File dirdb = context.getDatabasePath("master.db").getAbsoluteFile().getParentFile();
				dirdb.mkdir();
				db = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath("transact.db").getAbsolutePath(),"test123", null);
			}

			Cursor c = db.rawQuery("PRAGMA journal_mode=OFF", null);
			c.close();
			c = db.rawQuery("PRAGMA locking_mode=EXCLUSIVE", null);
			c.close();
			c = db.rawQuery("PRAGMA ignore_check_constraints=ON", null);
			c.close();
			c = db.rawQuery("PRAGMA foreign_keys=OFF", null);
			c.close();

			for(String tbl : tblmap.keySet()){
				Log.i("SDf___","df___"+tblmap.get(tbl));
				db.execSQL(tblmap.get(tbl));
			}

			db.close();

			if(isMaster){
				DBFunc.LoadDBFromInternal(context);
			}else{
				DBFunc.LoadTransactDBFromInternal(context);
			}
			return true;
		}catch(IOException e){
			Logger.WriteLog("DBFunc",e.toString());
			Log.i("DBFuIOExceptionnc",e.toString());
			return false;
		}
		
	}
	
	public static Map<String,String> LoadTableHeader(Context context, boolean master) throws IOException{
		SQLiteDatabase.loadLibs(context);
		DBFunc.OpenDBFromDisk(context.getAssets().open("header_repair.db"), "header_repair.db", context);
//		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath("header_repair.db").getAbsolutePath(),"test123", null);
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath("header_repair.db").getAbsolutePath(),"", null);
		Cursor c = null;
		if(master){
			c = db.rawQuery("SELECT tblname, tblsql FROM master", null);
		}else{
			c = db.rawQuery("SELECT tblname, tblsql FROM transact", null);
		}
		
		if(c == null){//error
			return null;
		}else{
			Map<String,String> tblmap = new HashMap<String,String>();
			while(c.moveToNext()){

				tblmap.put(c.getString(0), c.getString(1));
			}
			c.close();
			db.close();

			context.getDatabasePath("header_repair.db").delete();

			return tblmap;
		}
	}
	
	public static List<String> LoadScriptUpdate(Context context, boolean master) throws IOException{
		SQLiteDatabase.loadLibs(context);
		DBFunc.OpenDBFromDisk(context.getAssets().open("header_repair.db"), "header_repair.db", context);
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath("header_repair.db").getAbsolutePath(),"", null);
		Cursor c = null;
		if(master){
			c = db.rawQuery("SELECT script FROM master_script ORDER BY seq ASC", null);
		}else{
			c = db.rawQuery("SELECT script FROM transact_script ORDER BY seq ASC", null);
		}
		
		if(c == null){//error
			return null;
		}else{
			List<String> scripts = new ArrayList<String>();
			while(c.moveToNext()){
				scripts.add(c.getString(0));
			}
			c.close();
			db.close();
			context.getDatabasePath("header_repair.db").delete();
			return scripts;
		}
	}
	
	public static String PurifyString(String str){
		return str.replaceAll("'", "''");
	}

	public static Cursor Query(String sql, boolean masterDB){
		if(masterDB){
			
			if(Allocator.DB == null){
				return null;
			}
			
			return Allocator.DB.rawQuery(sql, null);
		}else{
			if(Allocator.TransactDB == null){
				return null;
			}		
			return Allocator.TransactDB.rawQuery(sql, null);
		}
	}
	
	public static void DBUserLog(String username, long userid, String auth, long timetick, String logmsg){
		if(Allocator.TransactDB != null){
			try{
				Allocator.TransactDB.execSQL("INSERT INTO UserLog(name,user_id,auth,time,info) VALUES ('"+DBFunc.PurifyString(username)+"', "+userid+", '"+DBFunc.PurifyString(auth)+"', "+timetick+", '"+DBFunc.PurifyString(logmsg)+"')");				
			}catch(SQLException e){
				Logger.WriteLog("DBFunc",e.toString());
				//Log.w("DBUserLog", e.toString());
			}
			
		}	
	}

	public static void ExecQuery(String sql, boolean masterDB){
		if(masterDB){
			if(Allocator.DB != null){
				Allocator.DB.execSQL(sql);
			}		
		}else{
			if(Allocator.TransactDB != null){
				Allocator.TransactDB.execSQL(sql);
			}		
		}
	}
	public static Cursor GetBillNoFromBill() {
		String sql = Query.GetSQLForBillPendingQty();

		Log.i("sql__SaveBill_","sql__c.getCountsql()__"+sql);
		return DBFunc.Query(sql, false);
	}
//	public static Cursor GetBillNoFromBill() {
////		String sql = "SELECT ReferenceBillNo FROM Sales " +
////				" WHERE ReferenceBillNo = '"+BillNo+"'  " ;
////
////		Cursor c = DBFunc.Query(sql, false);
////		if ( c != null){
////			if (c.getCount() == 0){
////
////
////			}
//
////			String sql = "SELECT BillNo FROM Bill where CloseDateTime IS NULL order by BillNo DESC ";
//			//String sql = "SELECT BillNo FROM BillList where IsClosed IS NULL AND STATUS = 'PENDING' order by BillNo DESC ";
//			//String sql = "SELECT BillNo FROM BillList where STATUS = 'PENDING' order by BillNo DESC ";
//			//String sql = "SELECT BillNo FROM BillList where STATUS = 'PENDING' order by BillNo DESC LIMIT 1";
//
//			//Cursor c = DBFunc.Query(sql, false);
////			if ( c != null) {
////				while (c.moveToNext()) {
////
////
////				}
////			}
//
//		//String sql = "SELECT BillList.BillNo,BillList.STATUS FROM Bill " +
//		String sql = "SELECT Bill.BillNo,BillList.STATUS FROM Bill " +
//				"inner join BillList on BillList.BillID = Bill.BillNo " +
//				"where Bill.CloseDateTime IS NULL AND BillList.STATUS = 'PENDING'  order by BillList.BillNo DESC LIMIT 1";

//		return DBFunc.Query(sql, false);
//	}
}
