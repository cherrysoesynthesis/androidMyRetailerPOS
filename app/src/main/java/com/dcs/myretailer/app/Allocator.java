package com.dcs.myretailer.app;


import android.graphics.Bitmap;
import android.graphics.Color;

import net.sqlcipher.database.SQLiteDatabase;


public class Allocator {
	public static boolean EndProgram = false;


	public static SQLiteDatabase DB;
	public static SQLiteDatabase TransactDB;

	//public static long NextBillNo = 1;
	//public static List<TransactBill> billList = new ArrayList<TransactBill>();
	//public static TransactBill CurrentTransact;

	public static String DataPath = "";


	public static String clip_btn_text = "";
	public static int clip_btn_type = 0;
	public static int clip_btn_obj = 0;
	public static int clip_btn_style = 0;
	public static int clip_btn_bgcolor = Color.WHITE;
	public static int clip_btn_txtcolor = Color.BLACK;

	public static String PrinterUSBPort = "";


	public static Bitmap clip_btn_img = null;

	public static boolean usermode = false;

	public static long cashierID = -1;
	public static String cashierName = "PSAdmin";
	public static String cashierAuth = "";


	public static boolean Auth_Allow_Cashier = false;
	public static boolean Auth_Allow_Report = true;
	public static boolean Auth_Allow_Setup = true;
	public static boolean Auth_Allow_ClosePrg = true;



	public static boolean Auth_POS_EditQty = false;
	public static boolean Auth_POS_CancelItem = false;
	public static boolean Auth_POS_CancelBill = false;
	public static boolean Auth_POS_ShowAllBill = false;
	public static boolean Auth_POS_ReprintBill = false;
	public static boolean Auth_POS_FeedBill = false;
	public static boolean Auth_POS_Discount = false;
	public static boolean Auth_POS_Payment = false;

	public static boolean Auth_POS_BalanceNewClose = false;
	public static boolean Auth_POS_BalanceMove = false;
	public static boolean Auth_POS_BalanceSplit = false;
	public static boolean Auth_POS_BalanceMerge = false;
	public static boolean Auth_POS_BalanceShowAll = false;

	public static boolean Auth_POS_ReportX = false;
	public static boolean Auth_POS_ReportZ = false;
	public static boolean Auth_POS_ReportPrev = false;

	public static boolean Auth_POS_Refer = false;
	public static boolean Auth_POS_ReferList = false;
	public static boolean Auth_POS_ReferFind = false;

	public static boolean Auth_Setup_Dept = true;
	public static boolean Auth_Setup_PLU = true;
	public static boolean Auth_Setup_CondiSeq = true;
	public static boolean Auth_Setup_Discount = true;
	public static boolean Auth_Setup_Tax = true;
	public static boolean Auth_Setup_Payment = true;
	public static boolean Auth_Setup_Report = true;
	public static boolean Auth_Setup_Refer = true;
	public static boolean Auth_Setup_HotKey = true;
	public static boolean Auth_Setup_Window = true;
	public static boolean Auth_Setup_Map = true;
	public static boolean Auth_Setup_User = true;
	public static boolean Auth_Setup_Printer = true;
	public static boolean Auth_Setup_KPrinter = true;
	public static boolean Auth_Setup_BillInfo = true;
	public static boolean Auth_Setup_POSConfig = true;
	public static boolean Auth_Setup_DB = true;
}
