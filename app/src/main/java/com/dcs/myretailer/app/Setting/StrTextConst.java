package com.dcs.myretailer.app.Setting;

import android.database.Cursor;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class StrTextConst {
    public static Map<Integer,String> ObjTypeText = new HashMap<Integer,String>(){
        {put(0,"Function");}
        {put(1,"PLU");}
        {put(2,"Department");}
        {put(3,"Payment");}
        {put(4,"Discount");}
        {put(5,"PopUp Window");}
        {put(6,"Map Layout");}
        {put(7,"Refer Info");}
    };

    public static Map<Integer,String> FunctionText = new HashMap<Integer,String>(){
        {put(0,"None");}
        {put(1,"Edit Quantity");}
        {put(2,"Close Program");}
        {put(3,"Cancel Item");}
        {put(4,"Cancel Bill");}
        {put(5,"List All Bill");}
        {put(6,"Print/Reprint Bill");}
        {put(7,"Feed Paper");}
        {put(8,"Department List");}
        {put(9,"PLU List");}
        {put(10,"Payment List");}
        {put(11,"Discount List");}
        {put(12,"User Report List");}
        {put(13,"PLU Code Search");}
        {put(14,"Balance Open/Close");}
        {put(15,"Balance Change");}
        {put(16,"Balance Split");}
        {put(17,"Balance Merge");}
        {put(18,"Balance List");}
        //{put(19,"Balance #");}
        //{put(20,"Close Map Layout");}
        {put(21,"Refer Info Remove");}
        {put(22,"Refer Info List");}
        {put(23,"Refer Info Search");}
        //{put(24,"Log Out");}
        //{put(25,"Log In");}
        {put(26,"Image Capture");}
        {put(27,"QRScan");}
        {put(28,"PrintTest");}
        {put(29,"BarcodeScan");}
        {put(30,"Bluetooth");}
        {put(31,"Refund");}
    };

    public static Map<Integer,String> ReportTypeText = new HashMap<Integer,String>(){
        {put(0,"(Unknown)");}
        {put(1,"Total Sales");}
        {put(2,"Department");}
        {put(3,"PLU Sales");}
        {put(4,"Payment");}
        {put(5,"Discount");}
        {put(6,"Tax");}
        {put(7,"Cancellation");}
        {put(8,"Refer Info Sales");}

    };

    public static Map<Integer,String> ReportTotalOptionText = new HashMap<Integer,String>(){
        {put(0,"Skip Print Zero Value");}
        {put(8,"Logged In User Only(otherwise all users)");}
    };

    public static Map<Integer,String> ReportDeptOptionText = new HashMap<Integer,String>(){
        {put(0,"Skip Print Zero Value");}
        {put(1,"Don't Print Tax");}
        {put(2,"Don't Print Cancelled");}
        {put(3,"Don't Print Net Sales");}
        {put(4,"Don't Print Gross Sales");}
        {put(5,"Print Grand Total");}
        {put(6,"Grand Total Only");}
        {put(8,"Logged In User Only(otherwise all users)");}
    };

    public static Map<Integer,String> ReportPLUOptionText = new HashMap<Integer,String>(){
        {put(0,"Skip Print Zero Value");}
        {put(1,"Don't Print Tax");}
        {put(2,"Don't Print Cancelled");}
        {put(3,"Don't Print Net Sales");}
        {put(4,"Don't Print Gross Sales");}
        {put(5,"Print Grand Total");}
        {put(6,"Grand Total Only");}
        {put(8,"Logged In User Only(otherwise all users)");}
    };

    public static Map<Integer,String> ReportDiscOptionText = new HashMap<Integer,String>(){
        {put(0,"Skip Print Zero Value");}
        {put(5,"Print Grand Total");}
        {put(6,"Grand Total Only");}
        {put(8,"Logged In User Only(otherwise all users)");}
    };

    public static Map<Integer,String> ReportPaymentOptionText = new HashMap<Integer,String>(){
        {put(0,"Skip Print Zero Value");}
        {put(2,"Don't Print Cancelled");}
        {put(5,"Print Grand Total");}
        {put(6,"Grand Total Only");}
        {put(8,"Logged In User Only(otherwise all users)");}
    };

    public static Map<Integer,String> ReportTaxOptionText = new HashMap<Integer,String>(){
        {put(0,"Skip Print Zero Value");}
        {put(2,"Don't Print Cancelled");}
        {put(5,"Print Grand Total");}
        {put(6,"Grand Total Only");}
        {put(8,"Logged In User Only(otherwise all users)");}
    };

    public static Map<Integer,String> ReportCancelOptionText = new HashMap<Integer,String>(){
        {put(0,"Skip Print Zero Value");}
        {put(1,"Don't Print Pre-Cancel");}
        {put(2,"Don't Print Post-Cancel");}
        {put(8,"Logged In User Only(otherwise all users)");}
    };

    public static Map<Integer,String> ReportReferInfoOptionText = new HashMap<Integer,String>(){
        {put(0,"Skip Print Zero Value");}
        {put(1,"Don't Print Tax");}
        {put(2,"Don't Print Cancelled");}
        {put(3,"Don't Print Net Sales");}
        {put(4,"Don't Print Gross Sales");}
        {put(5,"Print Grand Total");}
        {put(8,"Logged In User Only(otherwise all users)");}
    };

    public static Map<Integer,String> GeneralText = new HashMap<Integer,String>(){
        {put(0,"OK");}
        {put(1,"Cancel");}
        {put(2,"Save");}
        {put(3,"Exit");}
        {put(4,"New");}
        {put(5,"Update");}
        {put(6,"Yes");}
        {put(7,"No");}
        {put(8,"Add");}
        {put(9,"Edit");}
        {put(10,"Delete");}
        {put(11,"Saving");}
        {put(12,"Saved");}
        {put(13,"Close");}
        {put(14,"Reset");}
        {put(15,"Discard Change?");}
        {put(16,"Are you sure you want to discard changes?");}
        {put(17,"Are you sure you want to delete?");}
        {put(18,"Error");}
        {put(19,"Failed to connect to DB or Database error!");}
        {put(20,"Something is wrong and cannot be update!");}
        {put(21,"POS Config Required");}
        {put(22,"Please go to POS Setting to configuration POS first! Press OK to exit");}
        {put(23,"Permission");}
        {put(24,"Not enough permission to use this function!");}
        {put(30,"Activation");}
        {put(31,"Activation Required!");}
        {put(32,"Code: {0}");}
        {put(33,"Activation Code Copied!");}
        {put(34,"Copy");}
        {put(35,"Enter");}
        {put(36,"Thank you for activating the software!");}
        {put(37,"Invalid Activation Key!\nExample:\n123456-7890AB-CDEFGH-IJKLMN-OPQRST\n\nCase can be insensitive");}
        {put(40,"Log In!");}
        {put(41,"Please key in User Code to continue!");}
        {put(42,"Please key in User Code!");}
        {put(43,"Logged in as PSAdmin!");}
        {put(44,"Wrong Password or No such user found!");}
        {put(50,"Creating Master Database for the first time...");}
        {put(51,"Creating Transact Database for the first time...");}
        {put(52,"Failed to create Master database! Please go Setup POS>POS Setting>Database Import/Export to do configuration!");}
        {put(53,"Failed to create Transact database! Please go Setup POS>POS Setting>Database Import/Export to do configuration!");}
        {put(60,"Failed to open camera for barcode reading or camera device is in busy!");}
        {put(61,"Barcode reading is not possible because no camera capable for this device found!");}
        {put(70,"Version: {0}");}
        {put(71,"Loading Patch Notice...");}
        {put(72,"New Version Available");}
        {put(73,"Update?");}
        {put(74,"During updating please do not turn off the device.\nIt is advise to use WIFI to do updating.\nAre you sure you want to continue?");}
        {put(75,"Continue");}
        {put(76,"Patching");}
        {put(77,"Failed to retrieve patch!");}
        {put(78,"Update cancelled!");}
        {put(79,"Patch server is offline, will check for update for next run.");}
        {put(80,"Failed to get latest version!");}
        {put(81,"{0}/{1} byte(s)");}
        {put(90,"License");}
        {put(92,"Please select Language from the list!");}
        {put(93,"Click to view third party License");}
        {put(100,"Back");}
        {put(101,"Density");}
        {put(102,"Red");}
        {put(103,"Green");}
        {put(104,"Blue");}
        {put(105,"Button");}
        {put(106,"View");}
        {put(107,"Print");}
        {put(108,"From");}
        {put(109,"To");}
        {put(110,"Select");}
        {put(111,"Now");}
        {put(112,"Re-Edit");}
        {put(200,"(Default English)");}
        {put(301,"START CASHIER");}
        {put(302,"REPORT VIEWER");}
        {put(303,"SETUP POS");}
        {put(304,"EXIT");}
        {put(305,"Change Language");}
        {put(191,"Failed to connect Payment Type error!");}
    };

    public static Map<Integer,String> CashierPOSText = new HashMap<Integer,String>(){
        {put(0,"MyRetailer");}
        //{put(0,"PIXIE POS");}
        {put(1,"Enter PLU Code or Scan");}
        {put(2,"Move {0}");}
        {put(3,"Not Enough");}
        {put(4,"Generating Report...");}
        {put(5,"Open Name");}
        {put(6,"Open Price");}
        {put(7,"Quantity");}
        {put(8,"Edit Quantity ({0})");}
        {put(9,"{0} (Total: {1})");}
        {put(10,"Change Due");}
        {put(11,"Refund");}
        {put(12,"Search Refer Info");}
        {put(100,"Please go to POS Setting to configuration POS first!\nPress OK to exit");}
        {put(101,"Please go to POS Setting to configuration POS receipt printer! Press OK to exit");}
        {put(102,"No default Map Layout has been assigned!\nPlease go to POS Setting to configuration POS first!\nPress OK to exit");}
        {put(103,"No default Receipt Printer selected! Please configure from POS Setting! Press OK to exit");}
        {put(104,"No PLU in this Department");}
        {put(105,"Can't find any bill!");}
        {put(106,"No {0} opened!");}
        {put(107,"There's no Department");}
        {put(108,"There's no Discount");}
        {put(109,"There's no PLU");}
        {put(110,"There's no Payment");}
        {put(111,"There's no Refer Info");}
        {put(112,"PLU Code is empty! Please enter PLU Code!");}
        {put(113,"Please select the bill to continue");}
        {put(114,"Please enter {0} Number!");}
        {put(115,"No user report");}
        {put(116,"No PLU selected!");}
        {put(117,"No item selected!");}
        {put(118,"Cannot discount non PLU item!");}
        {put(119,"Cannot edit non PLU!");}
        {put(120,"Cannot add Condiment beacuse item already been held!");}
        {put(121,"Cannot cancel Condiment because the main item already been held!");}
        {put(122,"Empty name is not allow!");}
        {put(123,"Price is invalid! Please enter New Price!");}
        {put(124,"Quantity is invalid! Minimum quantity is 1!");}
        {put(125,"Invalid amount entered!");}
        {put(126,"Cannot adjust Condiment Quantity!");}
        {put(127,"Cannot adjust item Quantity because it is already been held!");}
        {put(128,"Bill already discount!");}
        {put(129,"Item already discount!");}
        {put(130,"Discount cannot be zero!");}
        {put(131,"Zero Sales not allowed!");}
        {put(132,"Bill is empty!");}
        {put(133,"Bill is required to have Refer Info selected before payment!");}
        {put(134,"Cannot remove Refer Info from this Bill because there's no Refer Info assigned!");}
        {put(135,"Please select which report type you want, 'Print Report' or 'Previous Z Report'");}
        {put(136,"Please select the report you want");}
        {put(137,"Period report not yet select!\nPlease select the period report you want to print back");}
        {put(138,"Cannot view Z report! Only can be print out!");}
        {put(139,"Cannot do Z report because there's {0} bill(s) haven't settle yet!");}
        {put(140,"Cannot adjust Bill Cancelled!");}
        {put(141,"Cannot adjust Bill Paid!");}
        {put(142,"Cannot move to same {0} Number!");}
        {put(143,"{0} {1} already occupied!");}
        {put(144,"Not enough item to splitting!");}
        {put(146,"Cannot delete last bill split!");}
        {put(147,"Cannot split last item!");}
        //TODO: TO BE REMOVE   {put(148,"No item select!");}
        {put(149,"Failed to split! Something is wrong!");}
        {put(150,"Nothing to be split!");}
        {put(151,"Please select the {0} to merge from");}
        {put(152,"Bill {0} merged to Bill {1}!");}
        {put(153,"No free {0} available to merge!");}
        {put(154,"Short amount: {0}");}
        {put(155,"Failed to generate report!");}
        {put(160,"Balance Function Disabled");}
        {put(161,"Refer Info Function Disabled!");}
        {put(162,"Refer Info Code is empty!");}
        {put(200,"POS");}
        {put(201,"Total");}
        {put(202,"Tax");}
        {put(203,"Bill");}
        {put(204,"Balance");}
        {put(205,"Refer");}
        {put(206,"Info 1");}
        {put(207,"Info 2");}
        {put(208,"Info 3");}
        {put(209,"Department List");}
        {put(210,"Discount List");}
        {put(211,"PLU List");}
        {put(212,"Payment List");}
        {put(213,"Refer Info List");}
        {put(220,"PAID");}
        {put(221,"CANCELLED");}
        {put(222,"Current Bill");}
        {put(223,"+ Bill");}
        {put(224,"- Bill");}
        {put(225,"Print Report");}
        {put(226,"Previous Z Report");}
        {put(227,"Date");}
        {put(228,"Previous Report");}
        {put(229,"User Report:");}
        {put(230,"PLU Code");}
        {put(231,"Refer Code");}
        {put(300,"Invalid HotKey!\nType:{0}, ID:{1}");}
        {put(301,"Invalid Window!\nID:{0}, Position:{1}");}
        {put(302,"Invalid Button Configuration!");}
        {put(303,"No PLU Code was found or invalid PLU Code!");}
        {put(304,"No such Map Layout was found or invalid Map Layout!");}
        {put(305,"No such user report was found!");}
        {put(306,"Cannot find the bill to print!");}
        {put(307,"No such bill was found!");}
        {put(308,"MISSING TAX ID FROM DB!");}
        {put(309,"FAILED TO RETRIEVE ADDED PLU INDEX!");}
        {put(310,"Invalid Payment ID!!");}
        {put(311,"Something went wrong on database!\nInvalid or missing Bill!");}
        {put(312,"Something went wrong on database!");}
        {put(313,"Cannot find Refer Info with given Refer Info code!");}
        {put(400,"From: {0}\nTo: {1}");}
        {put(401,"From Date:");}
        {put(402,"To Date:");}
        {put(403,"Bill:{0}");}
        {put(404,"Bill:{0}(Cancelled)");}
        {put(405,"Bill:{0}(Paid)");}
        {put(406,"Bill:{0}(Paid Cancelled)");}
        {put(407,"{0}\n{1}\nAmount: {2}  Tax: {3}");}
        {put(408,"{0} Number");}
        {put(409,"Map Layout");}
        {put(410,"Enter {0} Number");}
        {put(411,"Move {0} {1} to...");}
        {put(412,"Are you sure you want to move {0} from {1} to {2}?");}
        {put(413,"{0} {1} moved to {0} {2}!");}
        {put(414,"Split: 1/1");}
        {put(415,"Split: {0}/{1}");}
        {put(416,"Split new bill?");}
        {put(417,"Delete split bill?");}
        {put(418,"Confirm splitting?");}
        {put(419,"Are you sure you want to cancel split?");}
        {put(420,"Are you sure you want to merge Bill {0} to Bill {1}?");}
        {put(421,"Are you sure you want to do Z closing report '{0}'?");}
        {put(422,"Enter a Name for this item...");}
        {put(423,"Enter Price for this item...");}
        {put(424,"Enter Quantity. Minimum is 1");}
        {put(425,"Set Menu {0} condiment OK?");}
        {put(426,"Which condiment need to be re-edit?");}
        {put(427,"Enter discount amount");}
        {put(428,"Enter Amount or Enter 0 for full amount");}
        {put(429,"Are you sure you want to cancel Bill {0}?");}
        {put(430,"This Bill {0} is already Z report!\nCancelling this bill make previous report changes!\nAre you sure you want to cancel it anyway?");}
        {put(431,"Are you sure you want to Close Program?");}
    };

    public static Map<Integer,String> PrintSplText = new HashMap<Integer,String>(){
        {put(0,"Print Spooler");}
        {put(1,"Printer Error");}
        {put(10,"Failed to Print document {0} (Job: {1}/{2})\nReason:\n{3}");}
        {put(11,"Print Spooler has shutdown and no further document printed");}
        {put(12,"Invalid Printer ID {0}!");}
        {put(13,"USB Port haven't set yet!");}
        {put(14,"USB device not connect or the connected USB device port changed!");}
        {put(15,"This USB Port {0} doesn't have permission yet! Please go to printer setting to allow permission!");}
        {put(16,"Failed to print to selected USB Printer!\nUSB Port error or device not responsive!");}
        {put(17,"No suitable bluetooth hardware in this device!");}
        {put(18,"Bluetooth hasn't ready or haven't turn on yet!");}
        {put(19,"Failed to retrieve paired Printer!");}
        {put(20,"Printer haven't paired yet! Please go Printer Setting to pairing it first!");}
        {put(21,"Failed to send to targeted Bluetooth printer!");}
        {put(22,"Bad print configuration for printer '{0}'!");}
        {put(23,"PrintSpooler caused unknown Error!");}
        {put(100,"Retry Print");}
        {put(101,"Cancel Print");}
        {put(102,"Cancel All");}
    };

    public static Map<Integer,String> RptGenText = new HashMap<Integer,String>(){
        {put(0,"Report Viewer");}
        {put(1,"Generating Report...");}
        {put(25,"Please select report from Report Type!");}
        {put(26,"Failed to generate report!");}
        {put(27,"Failed to save report!");}
        {put(28,"Report successfully saved to {0}!");}
        {put(10,"Page:");}
        {put(100,"Fit");}
        {put(101,"1:1");}
        {put(102,"Report Type");}
        {put(103,"Specific User");}
        {put(104,"Specific Refer");}
        {put(200,"Sales Summary");}
        {put(201,"PLU Summary");}
        {put(202,"PLU Group By Dept");}
        {put(203,"Department Summary");}
        {put(210,"User Log");}
        {put(300,"All Users");}
        {put(301,"Items Remarks");}
        {put(302,"Items Refund");}
        {put(303,"Receipt No Summary");}
        {put(304,"Payment Type Summary");}
//        {put(301,"Stock In");}
//        {put(302,"Stock Out");}
//        {put(303,"Stock Take");}
//        {put(304,"Stock Transfer");}
//        {put(305,"Stock Adjustment");}
//        {put(306,"Stock Agent");}
    };

    public static Map<Integer,String> ConfigMenuText = new HashMap<Integer,String>(){
        //{put(0,"PIXIE POS Setup");}
        {put(0,"MyRetailer Setup");}
        {put(1,"Department");}
        {put(2,"PLU");}
        {put(3,"Set Menu");}
        {put(4,"Refer Info");}
        {put(5,"Discount");}
        {put(6,"Tax Config");}
        {put(7,"Payment");}
        {put(8,"User Report");}
        {put(9,"Hot Key\nPlacement");}
        {put(10,"Window");}
        {put(11,"Floor Map");}
        {put(12,"User Setup");}
        {put(13,"Printer\nSetting");}
        {put(14,"Kitchen Printer");}
        {put(15,"Receipt\nHeader/Footer");}
        {put(16,"Database\nImport/Export");}
        {put(17,"POS Config");}
        {put(18,"Exit Setup");}
    };

    public static Map<Integer,String> ConfigDept = new HashMap<Integer,String>(){
        {put(0,"Department Setup");}
        {put(10,"Can't delete this Department because {0} PLU(s) assigned it!");}
        {put(11,"Are you sure you want to delete Department '{0}'?");}
        {put(20,"Please enter Department Name!");}
        {put(21,"Save as new Department or update it?");}
        {put(22,"Department Added!");}
        {put(23,"Department Updated!");}
        {put(24,"Department Deleted!");}
        {put(25,"Please select Department from the list to delete!");}
        {put(26,"Same PLU Code existed!");}
        {put(100,"Name");}
    };

    public static Map<Integer,String> ConfigPLU = new HashMap<Integer,String>(){
        {put(0,"PLU Setup");}
        {put(2,"Scan Barcode");}
        {put(11,"Are you sure you want to delete PLU '{0}'?");}
        {put(20,"Please enter PLU Name!");}
        {put(21,"Save as new PLU or update it?");}
        {put(22,"PLU Added!");}
        {put(23,"PLU Updated!");}
        {put(24,"PLU Deleted!");}
        {put(25,"Please select PLU from the list to delete!");}
        {put(26,"Invalid Price and Price cannot be negative!");}
        {put(27,"Same PLU Code existed!");}
        {put(30,"{0} (Include {1}%)");}
        {put(31,"{0} (Exclude {1}%)");}
        {put(32,"None");}
        {put(100,"Name");}
        {put(101,"Price");}
        {put(102,"Code");}
        {put(103,"Tax");}
        {put(104,"Kitchen Printer");}
        {put(105,"Option");}
        {put(106,"Dept");}
        {put(107,"Set Menu");}
        {put(108,"Scan");}
        {put(109,"Open Name");}
        {put(110,"Open Price");}
        {put(111,"Is Condiment");}
        {put(112,"Skip Quantity Input");}
    };

    public static Map<Integer,String> ConfigSetMenu = new HashMap<Integer,String>(){
        {put(0,"Set Menu Setup");}
        {put(10,"Can't delete this Set Menu because {0} PLU(s) assigned it!");}
        {put(11,"Are you sure you want to delete Set Menu '{0}'?");}
        {put(20,"Please enter Set Menu Name!");}
        {put(21,"Save as new Set Menu or update it?");}
        {put(22,"Set Menu Added!");}
        {put(23,"Set Menu Updated!");}
        {put(24,"Set Menu Deleted!");}
        {put(25,"Info cannot be empty!");}
        {put(26,"Invalid Price and Price cannot be negative!");}
        {put(101,"Info");}
        {put(102,"Option");}
        {put(103,"Multiple Selection");}
        {put(104,"Item");}
        {put(105,"Price");}
        {put(106,"Name");}
    };

    public static Map<Integer,String> ConfigRefer = new HashMap<Integer,String>(){
        {put(0,"Reference Info");}
        {put(11,"Are you sure you want to delete Refer Info '{0}'?");}
        {put(20,"Please enter Name!");}
        {put(21,"Save as new Refer Info or update it?");}
        {put(22,"Refer Info Added!");}
        {put(23,"Refer Info Updated!");}
        {put(24,"Refer Info Deleted!");}
        {put(25,"Please select Refer Info from the list to delete!");}
        {put(26,"Please enter Identify!");}
        {put(27,"Same Reference Identify existed!");}
        {put(100,"Name");}
        {put(101,"Identify");}
    };


    public static Map<Integer,String> ConfigDisc = new HashMap<Integer,String>(){
        {put(0,"Discount Setup");}
        {put(20,"Please enter Name!");}
        {put(23,"Update successfully!");}
        {put(25,"Discount value shouldn't lower than 0!");}
        {put(26,"Invalid Discount Value");}
        {put(100,"Name");}
        {put(101,"Rate");}
        {put(102,"Option");}
        {put(103,"Bill Discount");}
        {put(104,"Amount Discount");}
        {put(105,"Discount is Surcharge");}
        {put(106,"Before Tax?(Item Discount Only)");}
    };

    public static Map<Integer,String> ConfigPayment = new HashMap<Integer,String>(){
        {put(0,"Payment Setup");}
        {put(11,"Are you sure you want to delete Payment '{0}'?");}
        {put(20,"Please enter Payment Name!");}
        {put(21,"Save as new Payment or update it?");}
        {put(22,"Payment Added!");}
        {put(23,"Payment Updated!");}
        {put(24,"Payment Deleted!");}
        {put(25,"Please select Payment from the list!");}
        {put(26,"Invalid Amount Entered!");}
        {put(27,"Amount shouldn't be lower than 0!");}
        {put(100,"Name");}
        {put(101,"Amount");}
        {put(102,"Option");}
        {put(103,"On=Full Amt, Off=Fixed Amt");}
        {put(104,"Ask Amount Entry");}
        {put(105,"Disallow Empty Cash");}
        {put(106,"Is Zero Sales");}
        {put(107,"Link to Payment App");}
        {put(108,"Integrate to shoptima");}
        {put(109,"Rounding Activate");}
    };

    public static Map<Integer,String> ConfigTax = new HashMap<Integer,String>(){
        {put(0,"Tax Configuration");}
        {put(10,"Cannot delete this Tax because it is already assigned to PLU!");}
        {put(20,"Please enter name!");}
        {put(23,"Update successfully!");}
        {put(25,"Please select Tax Type!");}
        {put(26,"Invalid Tax Rate! Valid value is 0 to 100");}
        {put(100,"Name");}
        {put(101,"Rate");}
        {put(102,"Acronym");}
        {put(103,"Tax Type");}
        {put(104,"Add-On");}
        {put(105,"VAT");}
        {put(106,"Add-On(Individual)");}
    };

    public static Map<Integer,String> ConfigPrinter = new HashMap<Integer,String>(){
        {put(0,"Printer Setup");}
        {put(1,"List Paired BT");}
        {put(10,"Can't delete this Printer because {0} KP(s) assigned it!");}
        {put(11,"Are you sure you want to delete Printer '{0}'?");}
        {put(12,"Bad value on Length! Only accept 32 to 100!");}
        {put(13,"Bad value on Lines Feed! Only accept 1 to 100!");}
        {put(14,"IP Address cannot be empty!");}
        {put(15,"Bad value on Port! Only accept 1 to 65535!");}
        {put(16,"Please select USB Port from the list first!");}
        {put(17,"Can't get USB Device from usb port name!");}
        {put(18,"Already has permission to access USB port {0}");}
        {put(20,"Please enter Printer Name!");}
        {put(21,"Save as new Printer or update it?");}
        {put(22,"Printer Added!");}
        {put(23,"Printer Updated!");}
        {put(24,"Printer Deleted!");}
        {put(26,"Please select Printer from the list!");}
        {put(26,"No suitable Bluetooth device!");}
        {put(27,"Bluetooth is now in busy mode!");}
        {put(30,"Internal");}
        {put(31,"Network Printer");}
        {put(32,"BT Printer");}
        {put(33,"USB Printer");}
        {put(100,"Name");}
        {put(101,"Printer Type");}
        {put(102,"Length Per Line");}
        {put(103,"Lines Feed");}
        {put(104,"IP");}
        {put(105,"Port");}
        {put(106,"Device MAC");}
        {put(107,"USB Port");}
        {put(108,"USB Option");}
        {put(109,"Single Size Font?");}
        {put(110,"Select BT Printer");}
        {put(111,"Refresh");}
        {put(112,"Check USB Permission");}
        {put(113,"BT Setting");}
        {put(114,"Character\nEncoding");}
    };

    public static Map<Integer,String> ConfigHK = new HashMap<Integer,String>(){
        {put(0,"HotKey Placement");}
        {put(1,"Background Color");}
        {put(2,"Text Color");}
        {put(3,"Select Image");}
        {put(23,"HotKey Updated!");}
        {put(25,"Please click and select the Button first!");}
        {put(30,"Copy");}
        {put(31,"Paste");}
        {put(32,"Clear");}
        {put(33,"Open");}
        {put(34,"Remove");}
        {put(36,"Normal");}
        {put(37,"Image Text");}
        {put(38,"Image");}
        {put(100,"Name");}
        {put(101,"Type");}
        {put(102,"Object");}
        {put(103,"Style");}
        {put(104,"Design");}
        {put(105,"Text");}
        {put(106,"BG");}
        {put(107,"Image");}
    };

    public static Map<Integer,String> ConfigWindow = new HashMap<Integer,String>(){
        {put(0,"Window Config");}
        {put(11,"Are you sure you want to delete Window '{0}'?\nWarning: All the button(s) associate with it will be deleted as well!");}
        {put(20,"Please enter Name!");}
        {put(21,"Save as new Window or update it?");}
        {put(22,"Window Added!");}
        {put(23,"Window Updated!");}
        {put(24,"Window Deleted!");}
        {put(25,"Please select Window from the list first!");}
        {put(26,"Bad Row Size value (Minimum is 30)!");}
        {put(100,"Name");}
        {put(101,"Column");}
        {put(102,"Row");}
        {put(103,"Row Size");}
        {put(104,"Edit Button");}
    };

    public static Map<Integer,String> ConfigWinBtn = new HashMap<Integer,String>(){
        {put(0,"Window Button Edit");}
        {put(1,"Background Color");}
        {put(2,"Text Color");}
        {put(3,"Select Image");}
        {put(23,"Window Button Updated!");}
        {put(25,"Please click and select the Button first!");}
        {put(26,"Error! Invalid Window ID!");}
        {put(30,"Copy");}
        {put(31,"Paste");}
        {put(32,"Clear");}
        {put(33,"Open");}
        {put(34,"Remove");}
        {put(36,"Normal");}
        {put(37,"Image Text");}
        {put(38,"Image");}
        {put(100,"Name");}
        {put(101,"Type");}
        {put(102,"Object");}
        {put(103,"Style");}
        {put(104,"Design");}
        {put(105,"Text");}
        {put(106,"BG");}
        {put(107,"Image");}
    };

    public static Map<Integer,String> ConfigKP = new HashMap<Integer,String>(){
        {put(0,"Kitchen Printer Setup");}
        {put(10,"Can't delete this Kitchen Printer because {0} PLU(s) assigned it!");}
        {put(11,"Are you sure you want to delete Printer '{0}'?");}
        {put(20,"Please enter Kitchen Printer Name!");}
        {put(21,"Save as new Kitchen Printer or update it?");}
        {put(22,"Kitchen Printer Added!");}
        {put(23,"Kitchen Printer Updated!");}
        {put(24,"Kitchen Printer Deleted!");}
        {put(25,"Please select Kitchen Printer from the list to delete!");}
        {put(26,"Please select Printer from the Printer list!");}
        {put(100,"Name");}
        {put(101,"Printer");}
    };

    public static Map<Integer,String> ConfigMapL = new HashMap<Integer,String>(){
        {put(0,"Map Layout Setup");}
        {put(1,"Background Color");}
        {put(2,"Text Color");}
        {put(3,"Select Image");}
        {put(11,"Are you sure you want delete Map Layout '{0}'??\nWarning: The button(s) assigned with it will be deleted as well!");}
        {put(20,"Please enter name!");}
        {put(21,"Save as new Map Layout or update it?");}
        {put(22,"Map Layout Added!");}
        {put(23,"Map Layout Updated!");}
        {put(24,"Map Layout Deleted!");}
        {put(25,"Please select Map Layout from the list!");}
        {put(30,"Open");}
        {put(31,"Remove");}
        {put(32,"Close");}
        {put(100,"Name");}
        {put(101,"BG Color");}
        {put(102,"BG Image");}
        {put(103,"Set Color");}
        {put(104,"Set Image");}
        {put(105,"Edit Layout");}
    };

    public static Map<Integer,String> ConfigMapB = new HashMap<Integer,String>(){
        {put(0,"Map Layout Button Edit");}
        {put(1,"Button Config");}
        {put(2,"Background Color");}
        {put(3,"Text Color");}
        {put(4,"Select Image");}
        {put(11,"Are you sure you want to delete?");}
        {put(12,"Are you sure you want to save it?");}
        {put(22,"Map Layout Button Saved!");}
        {put(23,"Please click and select the Button first!");}
        {put(24,"Error! Invalid Map Layout ID!");}
        {put(25,"Please click and select the Button first!");}
        {put(26,"Error! Invalid Map Layout ID!");}
        {put(30,"Balance Number");}
        {put(31,"Function");}
        {put(32,"Map Layout");}
        {put(33,"None");}
        {put(34,"Close Program");}
        {put(35,"Close Map Layout");}
        {put(36,"Normal");}
        {put(37,"Image Text");}
        {put(38,"Image");}
        {put(39,"Image Text Borderless");}
        {put(40,"Open");}
        {put(41,"Remove");}
        {put(42,"Close");}
        {put(100,"Name");}
        {put(101,"Move");}
        {put(102,"Resize");}
        {put(103,"Type");}
        {put(104,"Font Size");}
        {put(105,"Style");}
        {put(106,"Design");}
        {put(107,"Text");}
        {put(108,"BG");}
        {put(109,"Image");}
    };

    public static Map<Integer,String> ConfigRcpt = new HashMap<Integer,String>(){
        {put(0,"Receipt Format");}
        {put(1,"Import");}
        {put(4,"Select Image");}
        {put(11,"Are you sure you want to delete?");}
        {put(23,"Receipt Format Updated!");}
        {put(24,"Line deleted!");}
        {put(25,"Please select the line you want to delete!");}
        {put(30,"Header");}
        {put(31,"Footer");}
        {put(32,"Line");}
        {put(33,"Double Width");}
        {put(34,"Double Height");}
        {put(35,"Line Type");}
        {put(36,"Text");}
        {put(37,"Bitmap");}
        {put(100,"Type");}
    };

    public static Map<Integer,String> ConfigUsers = new HashMap<Integer,String>(){
        {put(0,"User Setup");}
        {put(11,"Are you sure you want to delete User '{0}'?");}
        {put(12,"The User '{0}' that you're about to delete is the one logging now, are you sure?\nNote: Make sure there is at least one user with permission of 'Allow Setup'!");}
        {put(20,"Please enter Name!");}
        {put(21,"Save as new User or update it?");}
        {put(22,"User Added!");}
        {put(23,"User Updated!");}
        {put(24,"User Deleted!");}
        {put(25,"Please select User from the list to delete!");}
        {put(26,"Please enter Code!");}
        {put(27,"Existing user code! Please use another code!");}
        {put(100,"Name");}
        {put(101,"Code");}
        {put(102,"User Permission");}
        {put(201,"Allow Cashier");}
        {put(202,"Allow Report");}
        {put(203,"Allow Setup");}
        {put(300,"Cashier Can Edit Quantity");}
        {put(301,"Cashier Can Cancel Item");}
        {put(302,"Cashier Can Cancel Bill");}
        {put(303,"Cashier Can Show All Bill");}
        {put(304,"Cashier Can Reprint Bill");}
        {put(305,"Cashier Can Use Feed Printer Function");}
        {put(306,"Cashier Can Discount");}
        {put(307,"Cashier Can Do Payment");}
        {put(310,"Cashier Can Open New/Close Balance");}
        {put(311,"Cashier Can Move Balance");}
        {put(312,"Cashier Can Split Balance");}
        {put(313,"Cashier Can Merge Balance");}
        {put(314,"Cashier Can Show All Opened Balance");}
        {put(320,"Cashier Can Do X Report");}
        {put(321,"Cashier Can Do Z Report");}
        {put(322,"Cashier Can View/Print Previous Z Report");}
        {put(330,"Cashier Can Use Refer Info Function");}
        {put(331,"Cashier Can List Refer Info");}
        {put(332,"Cashier Can Find Refer Info");}
        {put(500,"Can Set Department");}
        {put(501,"Can Set PLU");}
        {put(502,"Can Set Menu");}
        {put(503,"Can Set Discount");}
        {put(504,"Can Set Tax");}
        {put(505,"Can Set Payment");}
        {put(506,"Can Set Report");}
        {put(507,"Can Set Hot Key");}
        {put(508,"Can Set Window");}
        {put(509,"Can Set Floor Map");}
        {put(510,"Can Set Users");}
        {put(511,"Can Set Printer");}
        {put(512,"Can Set Kitchen Printer");}
        {put(513,"Can Set Header/Footer");}
        {put(514,"Can Set POS Config");}
        {put(515,"Can Set Database");}
        {put(516,"Can Set Refer Info");}
    };


    public static Map<Integer,String> ConfigPOSSys = new HashMap<Integer,String>(){
        {put(0,"POS Configuration");}
        {put(1,"Round Method");}
        {put(2,"Language Setting");}
        {put(3,"Add New Language");}
        {put(4,"Import Language");}
        {put(5,"Export Language");}
        {put(11,"Are you sure you want to delete Language '{0}'?");}
        {put(12,"Language Deleted!");}
        {put(13,"Default language cannot be deleted!");}
        {put(14,"Default language cannot be imported!");}
        {put(15,"Import successfully!");}
        {put(16,"Export successfully!");}
        {put(20,"Please enter Name!");}
        {put(21,"If you have enabled User Function Mode, please make sure to create a user that able to do POS Setting!");}
        {put(22,"POS Configuration Saved!");}
        {put(23,"Please select Language from the list!");}
        {put(24,"Unknown Error occured!");}
        {put(25,"Please select Receipt Printer from the Printer list!");}
        {put(26,"Invalid Receipt Settlement Print Count value!\nValid range is 0 to above!");}
        {put(27,"Please enter Balance Title!");}
        {put(28,"Please select default Map Layout!");}
        {put(29,"Same Language Name exist!");}
        {put(30,"Disable");}
        {put(31,"Free Entry");}
        {put(32,"Map Layout");}
        {put(33,"Balance");}
        {put(34,"No Rounding");}
        {put(35,"Swedish Rounding \n (0-2=0, 0-7=5, 8-9=10)");}
        {put(36,"04/10 Rounding \n (0-4=0, 5-9=10)");}
        {put(37,"04/05 Rounding \n (0-4=0, 5-9=5)");}
        {put(38,"Round Down \n (1-9=0)");}
        {put(39,"Round Up \n (1-9=10)");}
        {put(40,"New");}
        {put(41,"Delete");}
        {put(42,"Import");}
        {put(43,"Export");}
        {put(100,"Name");}
        {put(101,"Receipt Printer");}
        {put(102,"Receipt Settlement Print Count");}
        {put(103,"HotKey\nSize");}
        {put(104,"Balance Type");}
        {put(105,"Balance\nTitle");}
        {put(106,"Default Layout");}
        {put(107,"Bill List Font Size");}
        {put(108,"Option");}
        {put(109,"Info1 Text");}
        {put(110,"Info2 Text");}
        {put(111,"Info3 Text");}
        {put(112,"Auto Select Last Item");}
        {put(113,"Don't Print Condiment Quantity");}
        {put(114,"Print KP when only Payment made");}
        {put(115,"Hide Bottom Navigation Bar");}
        {put(116,"User Function Mode");}
        {put(117,"Check Update on startup software");}
        {put(118,"Enable Refer Info function");}
        {put(119,"Refer Info compulsory before Payment");}
        {put(120,"Print Refer Info 1 on Receipt");}
        {put(121,"Print Refer Info 2 on Receipt");}
        {put(122,"Print Refer Info 3 on Receipt");}
        {put(123,"If the bill finalize/cancel by other than user open it, overtake it");}
        {put(124,"Don't print UNPAID Bill on Receipt");}
        {put(125,"Bluetooth");}
        {put(126,"Printer Receipt");}
        {put(127,"Cash Drawer");}
        {put(128,"Customer Display");}
        {put(129,"BarCode Scanner");}
        {put(130,"Integrate Shoptima");}
        {put(131,"QRCode on Receipt");}
        {put(132,"Online Ordering");}
        {put(133,"Hide Image");}
        {put(134,"Service Charges");}
        {put(135,"Myretailer Backend");}
        {put(136,"Receipt Print Paper");}
        {put(137,"JeriFood");}
        {put(138,"Barcode on Receipt");}
        {put(139,"Item Remarks for ICNO");}
//        {put(136,"Tax Inclusive");}
    };


    public static Map<Integer,String> ConfigRpt = new HashMap<Integer,String>(){
        {put(0,"User Report Setup");}
        {put(11,"Are you sure you want to delete Report '{0}'?");}
        {put(12,"Are you sure you want to delete?");}
        {put(20,"Please enter Name!");}
        {put(21,"Save as new Report Config or update it?");}
        {put(22,"Report Config Added!");}
        {put(23,"Report Config Updated!");}
        {put(24,"Report Config Deleted!");}
        {put(25,"Please select the report from the list first!");}
        {put(100,"Name");}
        {put(101,"This is Z closing Report");}
        {put(102,"Report List");}
        {put(103,"Type");}
        {put(104,"Option");}
        {put(110,"{0}. (Unknown){1}");}
        {put(111,"{0}. Total Sales{1}");}
        {put(112,"{0}. PLU Sales{1}");}
        {put(113,"{0}. Department{1}");}
        {put(114,"{0}. Payment{1}");}
        {put(115,"{0}. Discount{1}");}
        {put(116,"{0}. Tax{1}");}
        {put(117,"{0}. Cancellation{1}");}
        {put(118,"{0}. Refer Info Sales{1}");}
    };


    public static Map<Integer,String> ConfigDB = new HashMap<Integer,String>(){
        {put(0,"Database Config");}
        {put(1,"Force Repair/Update");}
        {put(2,"Open Master DB");}
        {put(3,"Open Transact DB");}
        {put(4,"Save Master DB");}
        {put(5,"Save Transact DB");}
        {put(6,"Import DB");}
        {put(7,"Exported!");}
        {put(8,"Reset Database");}
        {put(10,"Are you sure you want to Repair/Update database?\nImportant: Make sure backup before proceeding!");}
        {put(11,"Are you sure you want to Import DB? This will replace the current one on the device!");}
        {put(12,"Cannot find path or DB cannot be accessible!");}
        {put(13,"Exported! Filename:{0}");}
        {put(14,"Are you really sure you want to reset database?\nOnce reset everything will be empty!\nPlease key in the code to proceed!");}
        {put(15,"Database reset succesfully!");}
        {put(16,"Wrong code! Failed to reset!");}
        {put(17,"Failed to reset DB! Unknown error occured!");}
        {put(18,"Unknown Error occured!");}
        {put(20,"{0} byte(s)");}
        {put(21,"No DB Loaded!");}
        {put(22,"Checking Database...");}
        {put(23,"Preparing to Repair/Update...");}
        {put(24,"Preparing to Import...");}
        {put(25,"Reparing/Updating...");}
        {put(26,"Importing...");}
        {put(27,"Compacting...");}
        {put(28,"Database is OK and no repair/update is required.");}
        {put(29,"Database repair/update completed!");}
        {put(30,"Database import succesfully!");}
        {put(100,"Master Database");}
        {put(101,"Transaction Database");}
        {put(102,"Database Size:");}
        {put(103,"Import");}
        {put(104,"Export");}
        {put(105,"Repair");}
        {put(106,"Reset");}
        {put(107,"Status:");}
    };

    public static Map<Integer,String> ConfigPaymentHost = new HashMap<Integer,String>(){
        {put(0,"Config Payment Host");}
        {put(10,"Can't delete this Config Payment Host because {0} PLU(s) assigned it!");}
        {put(11,"Are you sure you want to delete Config Payment Host '{0}'?");}
        {put(20,"Please enter Config Payment Host Name!");}
        {put(21,"Save as new Config Payment Host or update it?");}
        {put(22,"Config Payment Host Added!");}
        {put(23,"Config Payment Host Updated!");}
        {put(24,"Config Payment Host Deleted!");}
        {put(25,"Please select Config Payment Host from the list to delete!");}
        {put(26,"Same PLU Code existed!");}
        {put(100,"Bank Name");}
        {put(101,"Activity");}
        {put(201,"Please enter Config Payment Host Activity!");}
        {put(202,"Please enter Config Payment Host Bank Name!");}
        {put(203,"Duplicate Bank Name, Create Different One!");}
    };

    public static Map<Integer,String> ConfigPaymentType = new HashMap<Integer,String>(){
        {put(0,"Config Payment Type");}
        {put(10,"Can't delete this Config Payment Type because {0} PLU(s) assigned it!");}
        {put(11,"Are you sure you want to delete Config Payment Type '{0}'?");}
        {put(20,"Please enter Config Payment Type Name!");}
        {put(21,"Save as new Config Payment Type or update it?");}
        {put(22,"Config Payment Type Added!");}
        {put(23,"Config Payment Type Updated!");}
        {put(24,"Config Payment Type Deleted!");}
        {put(100,"Name");}
        {put(25,"Please select Config Payment Type from the list to delete!");}
        {put(26,"Same PLU Code existed!");}
        {put(201,"Please enter Config Payment Type Activity!");}
        {put(202,"Please enter Config Payment Type Bank Name!");}
    };
    public static void LoadLanguageFile(String filepath){
        try{
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            String line;
            while((line=br.readLine())!=null){
                if(line.trim().startsWith(";")){
                    continue;
                }

                String[] langdata = line.split("=", 2);

                if(langdata.length==2){
                    String[] langtype = langdata[0].trim().split("_",2);

                    if(langtype.length==2){

                        int id = -1;
                        try{
                            id = Integer.parseInt(langtype[1]);
                            if(id<0)continue;

                            if(langtype[0].equalsIgnoreCase("gen")){//GENERAL
                                if(GeneralText.containsKey(id)){
                                    GeneralText.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("pos")){//POS Cashier
                                if(CashierPOSText.containsKey(id)){
                                    CashierPOSText.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("printspl")){//GENERAL
                                if(PrintSplText.containsKey(id)){
                                    PrintSplText.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("rptgen")){//GENERAL
                                if(RptGenText.containsKey(id)){
                                    RptGenText.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("hkobj")){//Hot Key Object Type Text
                                if(ObjTypeText.containsKey(id)){
                                    ObjTypeText.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("hkfunc")){//Hot Key Function Text
                                if(FunctionText.containsKey(id)){
                                    FunctionText.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("urtype")){//User Report Type Text
                                if(ReportTypeText.containsKey(id)){
                                    ReportTypeText.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("urtotal")){//User Report Total Text
                                if(ReportTotalOptionText.containsKey(id)){
                                    ReportTotalOptionText.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("urdept")){//User Report Department Text
                                if(ReportDeptOptionText.containsKey(id)){
                                    ReportDeptOptionText.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("urplu")){//User Report PLU Text
                                if(ReportPLUOptionText.containsKey(id)){
                                    ReportPLUOptionText.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("urdisc")){//User Report Discount Text
                                if(ReportDiscOptionText.containsKey(id)){
                                    ReportDiscOptionText.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("urpay")){//User Report Payment Text
                                if(ReportPaymentOptionText.containsKey(id)){
                                    ReportPaymentOptionText.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("urtax")){//User Report Tax Text
                                if(ReportTaxOptionText.containsKey(id)){
                                    ReportTaxOptionText.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("urvoid")){//User Report Cancel Text
                                if(ReportCancelOptionText.containsKey(id)){
                                    ReportCancelOptionText.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("cfgmenu")){//Config Menu Text
                                if(ConfigMenuText.containsKey(id)){
                                    ConfigMenuText.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("cfgdept")){//Config Dept
                                if(ConfigDept.containsKey(id)){
                                    ConfigDept.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("cfgcph")){//Config Configuration Payment Host
                                if(ConfigPaymentHost.containsKey(id)){
                                    ConfigPaymentHost.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("cfgcpt")){//Config Configuration Payment Host
                                if(ConfigPaymentType.containsKey(id)){
                                    ConfigPaymentType.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("cfgplu")){//Config PLU
                                if(ConfigPLU.containsKey(id)){
                                    ConfigPLU.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("cfgsetmenu")){//Config Set Menu Text
                                if(ConfigSetMenu.containsKey(id)){
                                    ConfigSetMenu.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("cfgdisc")){//Config DISC
                                if(ConfigDisc.containsKey(id)){
                                    ConfigDisc.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("cfgtax")){//Config TAX
                                if(ConfigTax.containsKey(id)){
                                    ConfigTax.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("cfgpay")){//Config Payment
                                if(ConfigPayment.containsKey(id)){
                                    ConfigPayment.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("cfgref")){//Config Refer Info
                                if(ConfigRefer.containsKey(id)){
                                    ConfigRefer.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("cfghotkey")){//Config HotKey
                                if(ConfigHK.containsKey(id)){
                                    ConfigHK.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("cfgwin")){//Config Window
                                if(ConfigWindow.containsKey(id)){
                                    ConfigWindow.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("cfgwinb")){//Config Window Button Editor
                                if(ConfigWinBtn.containsKey(id)){
                                    ConfigWinBtn.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("cfgmapl")){//Config Map Layout
                                if(ConfigMapL.containsKey(id)){
                                    ConfigMapL.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("cfgmapb")){//Config Map Layout Button Editor
                                if(ConfigMapB.containsKey(id)){
                                    ConfigMapB.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("cfgprinter")){//Config Printer
                                if(ConfigPrinter.containsKey(id)){
                                    ConfigPrinter.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("cfgkp")){//Config Kitchen Printer
                                if(ConfigKP.containsKey(id)){
                                    ConfigKP.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("cfgrcpt")){//Config Receipt Header/Footer
                                if(ConfigRcpt.containsKey(id)){
                                    ConfigRcpt.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("cfgrpt")){//Config User Report
                                if(ConfigRpt.containsKey(id)){
                                    ConfigRpt.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("cfgpos")){//Config POSSys
                                if(ConfigPOSSys.containsKey(id)){
                                    ConfigPOSSys.put(id, langdata[1]);
                                }
                            }else if(langtype[0].equalsIgnoreCase("cfguser")){//Config Users
                                if(ConfigUsers.containsKey(id)){
                                    ConfigUsers.put(id, langdata[1]);
                                }
                            }
                        }catch(NumberFormatException e){}
                    }
                }
            }
            br.close();
        }catch(IOException e){
            Logger.WriteLog("StrTextConst",e.toString());
            //Log.e("LoadLanguageFile", e.toString());
        }

    }

    public static void ExportLanguageFile(String filepath){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
            bw.write(";General Message");
            bw.newLine();
            List<Integer> l = new ArrayList<Integer>(StrTextConst.GeneralText.keySet());
            for(Integer i:l){
                bw.write("gen_"+i+"="+StrTextConst.GeneralText.get(i).replaceAll("\n", "\\\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";POS Cashier Text");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.CashierPOSText.keySet());
            for(Integer i:l){
                bw.write("pos_"+i+"="+StrTextConst.CashierPOSText.get(i).replaceAll("\n", "\\\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";PrinterSpooler Text");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.PrintSplText.keySet());
            for(Integer i:l){
                bw.write("printspl_"+i+"="+StrTextConst.PrintSplText.get(i).replaceAll("\n", "\\\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Report Viewer");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.RptGenText.keySet());
            for(Integer i:l){
                bw.write("rptgen_"+i+"="+StrTextConst.RptGenText.get(i).replaceAll("\n", "\\\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Hot Key Object Text");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ObjTypeText.keySet());
            for(Integer i:l){
                bw.write("hkobj_"+i+"="+StrTextConst.ObjTypeText.get(i).replaceAll("\n", "\\\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Hot Key Function Text");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.FunctionText.keySet());
            for(Integer i:l){
                bw.write("hkfunc_"+i+"="+StrTextConst.FunctionText.get(i).replaceAll("\n", "\\\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";User Report Type Text");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ReportTypeText.keySet());
            for(Integer i:l){
                bw.write("urtype_"+i+"="+StrTextConst.ReportTypeText.get(i).replaceAll("\n", "\\\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";User Report Total Sales Option Text");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ReportTotalOptionText.keySet());
            for(Integer i:l){
                bw.write("urtotal_"+i+"="+StrTextConst.ReportTotalOptionText.get(i).replaceAll("\n", "\\\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";User Report Department Option Text");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ReportDeptOptionText.keySet());
            for(Integer i:l){
                bw.write("urdept_"+i+"="+StrTextConst.ReportDeptOptionText.get(i).replaceAll("\n", "\\\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";User Report PLU Option Text");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ReportPLUOptionText.keySet());
            for(Integer i:l){
                bw.write("urplu_"+i+"="+StrTextConst.ReportPLUOptionText.get(i).replaceAll("\n", "\\\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";User Report Discount Option Text");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ReportDiscOptionText.keySet());
            for(Integer i:l){
                bw.write("urdisc_"+i+"="+StrTextConst.ReportDiscOptionText.get(i).replaceAll("\n", "\\\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";User Report Payment Option Text");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ReportPaymentOptionText.keySet());
            for(Integer i:l){
                bw.write("urpay_"+i+"="+StrTextConst.ReportPaymentOptionText.get(i).replaceAll("\n", "\\\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";User Report Tax Option Text");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ReportTaxOptionText.keySet());
            for(Integer i:l){
                bw.write("urtax_"+i+"="+StrTextConst.ReportTaxOptionText.get(i).replaceAll("\n", "\\\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";User Report Cancel Option Text");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ReportCancelOptionText.keySet());
            for(Integer i:l){
                bw.write("urvoid_"+i+"="+StrTextConst.ReportCancelOptionText.get(i).replaceAll("\n", "\\\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";User Report Refer Info Option Text");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ReportReferInfoOptionText.keySet());
            for(Integer i:l){
                bw.write("urref_"+i+"="+StrTextConst.ReportReferInfoOptionText.get(i).replaceAll("\n", "\\\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Setup Menu");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigMenuText.keySet());
            for(Integer i:l){
                bw.write("cfgmenu_"+i+"="+StrTextConst.ConfigMenuText.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Setup Database");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigDB.keySet());
            for(Integer i:l){
                bw.write("cfgdb_"+i+"="+StrTextConst.ConfigDB.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Setup Department");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigDept.keySet());
            for(Integer i:l){
                bw.write("cfgdept_"+i+"="+StrTextConst.ConfigDept.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Setup Config Payment Host");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigPaymentHost.keySet());
            for(Integer i:l){
                bw.write("cfgcph_"+i+"="+StrTextConst.ConfigPaymentHost.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }
            bw.newLine();


            bw.write(";Setup Config Payment Type");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigPaymentType.keySet());
            for(Integer i:l){
                bw.write("cfgcpt_"+i+"="+StrTextConst.ConfigPaymentType.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Setup PLU");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigPLU.keySet());
            for(Integer i:l){
                bw.write("cfgplu_"+i+"="+StrTextConst.ConfigPLU.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Setup Set Menu");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigSetMenu.keySet());
            for(Integer i:l){
                bw.write("cfgsetmenu_"+i+"="+StrTextConst.ConfigSetMenu.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Setup Discount");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigDisc.keySet());
            for(Integer i:l){
                bw.write("cfgdisc_"+i+"="+StrTextConst.ConfigDisc.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Setup KP");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigKP.keySet());
            for(Integer i:l){
                bw.write("cfgkp_"+i+"="+StrTextConst.ConfigKP.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Setup Payment");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigTax.keySet());
            for(Integer i:l){
                bw.write("cfgpay_"+i+"="+StrTextConst.ConfigTax.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Setup HotKey");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigHK.keySet());
            for(Integer i:l){
                bw.write("cfghotkey_"+i+"="+StrTextConst.ConfigHK.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Setup Window");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigWindow.keySet());
            for(Integer i:l){
                bw.write("cfgwin_"+i+"="+StrTextConst.ConfigWindow.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Setup Window Button");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigWinBtn.keySet());
            for(Integer i:l){
                bw.write("cfgwinb_"+i+"="+StrTextConst.ConfigWinBtn.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Setup Map Layout");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigMapL.keySet());
            for(Integer i:l){
                bw.write("cfgmapl_"+i+"="+StrTextConst.ConfigMapL.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Setup Map Layout Button Editor");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigMapB.keySet());
            for(Integer i:l){
                bw.write("cfgmapb_"+i+"="+StrTextConst.ConfigMapB.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Setup Tax Config");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigTax.keySet());
            for(Integer i:l){
                bw.write("cfgtax_"+i+"="+StrTextConst.ConfigTax.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Setup Report Config");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigRpt.keySet());
            for(Integer i:l){
                bw.write("cfgrpt_"+i+"="+StrTextConst.ConfigRpt.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Setup POSConfig");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigPOSSys.keySet());
            for(Integer i:l){
                bw.write("cfgpos_"+i+"="+StrTextConst.ConfigPOSSys.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Setup Printer");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigPrinter.keySet());
            for(Integer i:l){
                bw.write("cfgprinter_"+i+"="+StrTextConst.ConfigPrinter.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Receipt Header/Footer Setting");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigRcpt.keySet());
            for(Integer i:l){
                bw.write("cfgrcpt_"+i+"="+StrTextConst.ConfigRcpt.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Refer Setting");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigRefer.keySet());
            for(Integer i:l){
                bw.write("cfgref_"+i+"="+StrTextConst.ConfigRefer.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }
            bw.newLine();

            bw.write(";Users Setting");
            bw.newLine();
            l = new ArrayList<Integer>(StrTextConst.ConfigUsers.keySet());
            for(Integer i:l){
                bw.write("cfguser"+i+"="+StrTextConst.ConfigUsers.get(i).replaceAll("\n", "\\n"));
                bw.newLine();
            }

            bw.flush();
            bw.close();
        } catch (IOException e) {
            Logger.WriteLog("StrTextConst",e.toString());
            //Log.e("ExportLanguageFile", e.toString());
        }
    }

    public static boolean LoadLanguageSQL(int langlistID){
        try{
            Cursor c = DBFunc.Query("SELECT langtype,langid,value FROM LangDetails ORDER BY id ASC",true);

            if(c==null){
                return false;
            }

            while(c.moveToNext()){
                String langtype = c.getString(0).toLowerCase();
                int id = c.getInt(1);
                String langdata = c.getString(2);
                if(langtype.equalsIgnoreCase("gen")){//GENERAL
                    if(GeneralText.containsKey(id)){
                        GeneralText.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("pos")){//POS Cashier
                    if(CashierPOSText.containsKey(id)){
                        CashierPOSText.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("printspl")){//GENERAL
                    if(PrintSplText.containsKey(id)){
                        PrintSplText.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("rptgen")){//REPORT VIEWER
                    if(RptGenText.containsKey(id)){
                        RptGenText.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("hkobj")){//Hot Key Object Type Text
                    if(ObjTypeText.containsKey(id)){
                        ObjTypeText.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("hkfunc")){//Hot Key Function Text
                    if(FunctionText.containsKey(id)){
                        FunctionText.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("urtype")){//User Report Type Text
                    if(ReportTypeText.containsKey(id)){
                        ReportTypeText.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("urtotal")){//User Report Total Text
                    if(ReportTotalOptionText.containsKey(id)){
                        ReportTotalOptionText.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("urdept")){//User Report Department Text
                    if(ReportDeptOptionText.containsKey(id)){
                        ReportDeptOptionText.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("urplu")){//User Report PLU Text
                    if(ReportPLUOptionText.containsKey(id)){
                        ReportPLUOptionText.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("urdisc")){//User Report Discount Text
                    if(ReportDiscOptionText.containsKey(id)){
                        ReportDiscOptionText.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("urpay")){//User Report Payment Text
                    if(ReportPaymentOptionText.containsKey(id)){
                        ReportPaymentOptionText.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("urtax")){//User Report Tax Text
                    if(ReportTaxOptionText.containsKey(id)){
                        ReportTaxOptionText.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("urvoid")){//User Report Cancel Text
                    if(ReportCancelOptionText.containsKey(id)){
                        ReportCancelOptionText.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("cfgmenu")){//Config Menu Text
                    if(ConfigMenuText.containsKey(id)){
                        ConfigMenuText.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("cfgdept")){//Config Dept
                    if(ConfigDept.containsKey(id)){
                        ConfigDept.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("cfgplu")){//Config PLU
                    if(ConfigPLU.containsKey(id)){
                        ConfigPLU.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("cfgsetmenu")){//Config Set Menu Text
                    if(ConfigSetMenu.containsKey(id)){
                        ConfigSetMenu.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("cfgdisc")){//Config DISC
                    if(ConfigDisc.containsKey(id)){
                        ConfigDisc.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("cfgtax")){//Config TAX
                    if(ConfigTax.containsKey(id)){
                        ConfigTax.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("cfgpay")){//Config Payment
                    if(ConfigPayment.containsKey(id)){
                        ConfigPayment.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("cfgref")){//Config Refer Info
                    if(ConfigRefer.containsKey(id)){
                        ConfigRefer.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("cfghotkey")){//Config HotKey
                    if(ConfigHK.containsKey(id)){
                        ConfigHK.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("cfgwin")){//Config Window
                    if(ConfigWindow.containsKey(id)){
                        ConfigWindow.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("cfgwinb")){//Config Window Button Editor
                    if(ConfigWinBtn.containsKey(id)){
                        ConfigWinBtn.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("cfgmapl")){//Config Map Layout
                    if(ConfigMapL.containsKey(id)){
                        ConfigMapL.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("cfgmapb")){//Config Map Layout Button Editor
                    if(ConfigMapB.containsKey(id)){
                        ConfigMapB.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("cfgprinter")){//Config Printer
                    if(ConfigPrinter.containsKey(id)){
                        ConfigPrinter.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("cfgkp")){//Config Kitchen Printer
                    if(ConfigKP.containsKey(id)){
                        ConfigKP.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("cfgrcpt")){//Config Receipt Header/Footer
                    if(ConfigRcpt.containsKey(id)){
                        ConfigRcpt.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("cfgrpt")){//Config User Report
                    if(ConfigRpt.containsKey(id)){
                        ConfigRpt.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("cfgpos")){//Config POSSys
                    if(ConfigPOSSys.containsKey(id)){
                        ConfigPOSSys.put(id, langdata);
                    }
                }else if(langtype.equalsIgnoreCase("cfguser")){//Config Users
                    if(ConfigUsers.containsKey(id)){
                        ConfigUsers.put(id, langdata);
                    }
                }
            }
            c.close();
            return true;
        }catch(Exception e){
            Logger.WriteLog("StrTextConst",e.toString());
            //Log.e("LoadLanguageSQL", e.toString());
            return false;
        }
    }

    public static boolean LoadLanguageSQLFile(String filepath, int langlistID){
        try{
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            Cursor c = DBFunc.Query("SELECT COUNT(*) FROM LangList WHERE id = "+langlistID, true);
            if(c==null){
                return false;
            }
            if(c.moveToNext()){
                if(c.getInt(0)==0){
                    c.close();
                    return false;
                }
            }

            DBFunc.ExecQuery("DELETE FROM LangDetails WHERE langlist_id = "+langlistID, true);


            String line;
            while((line=br.readLine())!=null){
                if(line.trim().startsWith(";")){
                    continue;
                }

                String[] langdata = line.split("=", 2);

                if(langdata.length==2){
                    String[] langtype = langdata[0].trim().split("_",2);

                    if(langtype.length==2){

                        int id = -1;
                        try{
                            id = Integer.parseInt(langtype[1]);
                            if(id<0)continue;

                            if(langtype[0].equalsIgnoreCase("gen")){//GENERAL
                                if(!GeneralText.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("pos")){//POS Cashier
                                if(!CashierPOSText.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("printspl")){//GENERAL
                                if(PrintSplText.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("rptgen")){//REPORT VIEWER
                                if(RptGenText.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("hkobj")){//Hot Key Object Type Text
                                if(ObjTypeText.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("hkfunc")){//Hot Key Function Text
                                if(FunctionText.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("urtype")){//User Report Type Text
                                if(ReportTypeText.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("urtotal")){//User Report Total Text
                                if(ReportTotalOptionText.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("urdept")){//User Report Department Text
                                if(ReportDeptOptionText.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("urplu")){//User Report PLU Text
                                if(ReportPLUOptionText.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("urdisc")){//User Report Discount Text
                                if(ReportDiscOptionText.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("urpay")){//User Report Payment Text
                                if(ReportPaymentOptionText.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("urtax")){//User Report Tax Text
                                if(ReportTaxOptionText.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("urvoid")){//User Report Cancel Text
                                if(ReportCancelOptionText.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("cfgmenu")){//Config Menu Text
                                if(ConfigMenuText.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("cfgdept")){//Config Dept
                                if(ConfigDept.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("cfgcph")){//Config Payment Host
                                if(ConfigPaymentHost.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("cfgplu")){//Config PLU
                                if(ConfigPLU.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("cfgsetmenu")){//Config Set Menu Text
                                if(ConfigSetMenu.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("cfgdisc")){//Config DISC
                                if(ConfigDisc.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("cfgtax")){//Config TAX
                                if(ConfigTax.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("cfgpay")){//Config Payment
                                if(ConfigPayment.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("cfgref")){//Config Refer Info
                                if(ConfigRefer.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("cfghotkey")){//Config HotKey
                                if(ConfigHK.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("cfgwin")){//Config Window
                                if(ConfigWindow.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("cfgwinb")){//Config Window Button Editor
                                if(ConfigWinBtn.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("cfgmapl")){//Config Map Layout
                                if(ConfigMapL.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("cfgmapb")){//Config Map Layout Button Editor
                                if(ConfigMapB.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("cfgprinter")){//Config Printer
                                if(ConfigPrinter.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("cfgkp")){//Config Kitchen Printer
                                if(ConfigKP.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("cfgrcpt")){//Config Receipt Header/Footer
                                if(ConfigRcpt.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("cfgrpt")){//Config User Report
                                if(ConfigRpt.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("cfgpos")){//Config POSSys
                                if(ConfigPOSSys.containsKey(id)) continue;
                            }else if(langtype[0].equalsIgnoreCase("cfguser")){//Config Users
                                if(ConfigUsers.containsKey(id)) continue;
                            }

                            DBFunc.ExecQuery("INSERT INTO LangDetails(langlist_id, langtype, langid, value) VALUES("+langlistID+", '"+DBFunc.PurifyString(langtype[0])+"', "+id+", '"+DBFunc.PurifyString(langdata[1])+"')", true);

                        }catch(NumberFormatException e){}
                    }
                }
            }
            br.close();


            return true;
        }catch(Exception e){
            Logger.WriteLog("StrTextConst",e.toString());
            //Log.e("LoadLanguageSQLFile", e.toString());
            return false;
        }
    }

    public static boolean ExportLanguageSQLFile(String filepath, int langlistID){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));

            Cursor c = DBFunc.Query("SELECT langtype,langid,value FROM LangDetails ORDER BY langtype, langid ASC", true);
            if(c==null){
                return false;
            }
            String lastlangtype = "";
            while(c.moveToNext()){
                String langtype = c.getString(0);
                int id = c.getInt(1);
                String langdata = c.getString(2).replaceAll("\n", "\\\\n");
                if(!lastlangtype.equalsIgnoreCase(langtype)){
                    if(langtype.equalsIgnoreCase("gen")){
                        bw.write(";General Message");
                    }else if(langtype.equalsIgnoreCase("pos")){
                        bw.write(";POS Cashier Text");
                    }else if(langtype.equalsIgnoreCase("printspl")){
                        bw.write(";PrinterSpooler Text");
                    }else if(langtype.equalsIgnoreCase("printspl")){
                        bw.write(";PrinterSpooler Text");
                    }else if(langtype.equalsIgnoreCase("rptgen")){
                        bw.write(";Report Viewer");
                    }else if(langtype.equalsIgnoreCase("hkobj")){
                        bw.write(";Hot Key Object Text");
                    }else if(langtype.equalsIgnoreCase("hkfunc")){
                        bw.write(";Hot Key Function Text");
                    }else if(langtype.equalsIgnoreCase("urtype")){
                        bw.write(";User Report Type Text");
                    }else if(langtype.equalsIgnoreCase("urtotal")){
                        bw.write(";User Report Total Sales Option Text");
                    }else if(langtype.equalsIgnoreCase("urtotal")){
                        bw.write(";User Report Total Sales Option Text");
                    }else if(langtype.equalsIgnoreCase("urdept")){
                        bw.write(";User Report Department Option Text");
                    }else if(langtype.equalsIgnoreCase("urplu")){
                        bw.write(";User Report PLU Option Text");
                    }else if(langtype.equalsIgnoreCase("urdisc")){
                        bw.write(";User Report Discount Option Text");
                    }else if(langtype.equalsIgnoreCase("urpay")){
                        bw.write(";User Report Payment Option Text");
                    }else if(langtype.equalsIgnoreCase("urtax")){
                        bw.write(";User Report Tax Option Text");
                    }else if(langtype.equalsIgnoreCase("urvoid")){
                        bw.write(";User Report Cancel Option Text");
                    }else if(langtype.equalsIgnoreCase("urref")){
                        bw.write(";User Report Refer Info Option Text");
                    }else if(langtype.equalsIgnoreCase("cfgmenu")){
                        bw.write(";Setup Menu");
                    }else if(langtype.equalsIgnoreCase("cfgdb")){
                        bw.write(";Setup Database");
                    }else if(langtype.equalsIgnoreCase("cfgdept")){
                        bw.write(";Setup Department");
                    }else if(langtype.equalsIgnoreCase("cfgcph")){
                        bw.write(";Setup Config Payment Host");
                    }else if(langtype.equalsIgnoreCase("cfgplu")){
                        bw.write(";Setup PLU");
                    }else if(langtype.equalsIgnoreCase("cfgsetmenu")){
                        bw.write(";Setup Set Menu");
                    }else if(langtype.equalsIgnoreCase("cfgdisc")){
                        bw.write(";Setup Discount");
                    }else if(langtype.equalsIgnoreCase("cfgkp")){
                        bw.write(";Setup KP");
                    }else if(langtype.equalsIgnoreCase("cfgpay")){
                        bw.write(";Setup Payment");
                    }else if(langtype.equalsIgnoreCase("cfghotkey")){
                        bw.write(";Setup HotKey");
                    }else if(langtype.equalsIgnoreCase("cfghotkey")){
                        bw.write(";Setup HotKey");
                    }else if(langtype.equalsIgnoreCase("cfgwin")){
                        bw.write(";Setup Window");
                    }else if(langtype.equalsIgnoreCase("cfgwinb")){
                        bw.write(";Setup Window Button");
                    }else if(langtype.equalsIgnoreCase("cfgmapl")){
                        bw.write(";Setup Map Layout");
                    }else if(langtype.equalsIgnoreCase("cfgmapb")){
                        bw.write(";Setup Map Layout Button Editor");
                    }else if(langtype.equalsIgnoreCase("cfgtax")){
                        bw.write(";Setup Tax Config");
                    }else if(langtype.equalsIgnoreCase("cfgrpt")){
                        bw.write(";Setup Setup Report Config");
                    }else if(langtype.equalsIgnoreCase("cfgpos")){
                        bw.write(";Setup POSConfig");
                    }else if(langtype.equalsIgnoreCase("cfgprinter")){
                        bw.write(";Setup Printer");
                    }else if(langtype.equalsIgnoreCase("cfgrcpt")){
                        bw.write(";Receipt Header/Footer Setting");
                    }else if(langtype.equalsIgnoreCase("cfgref")){
                        bw.write(";Refer Setting");
                    }else if(langtype.equalsIgnoreCase("cfguser")){
                        bw.write(";Users Setting");
                    }
                    bw.newLine();

                    lastlangtype = langtype;
                }
                bw.write(langtype+"_"+id+"="+langdata);
                bw.newLine();
            }
            c.close();

            bw.flush();
            bw.close();
            return true;
        }catch(Exception e){
            Logger.WriteLog("StrTextConst",e.toString());
            //Log.e("ExportLanguageSQLFile", e.toString());
            return false;
        }

    }

    static Map<String,Map<Integer,String>> TMPLANG = new HashMap<String,Map<Integer,String>>();

    public static void CreateTempLang(){
        TMPLANG.clear();

        Map<Integer,String> map = new HashMap<Integer,String>();
        List<Integer> l = new ArrayList<Integer>(GeneralText.keySet());
        for(Integer i:l){
            map.put(i, GeneralText.get(i));
        }
        TMPLANG.put("gen", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(CashierPOSText.keySet());
        for(Integer i:l){
            map.put(i, CashierPOSText.get(i));
        }
        TMPLANG.put("pos", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(PrintSplText.keySet());
        for(Integer i:l){
            map.put(i, PrintSplText.get(i));
        }
        TMPLANG.put("printspl", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(RptGenText.keySet());
        for(Integer i:l){
            map.put(i, RptGenText.get(i));
        }
        TMPLANG.put("rptgen", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ObjTypeText.keySet());
        for(Integer i:l){
            map.put(i, ObjTypeText.get(i));
        }
        TMPLANG.put("hkobj", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(FunctionText.keySet());
        for(Integer i:l){
            map.put(i, FunctionText.get(i));
        }
        TMPLANG.put("hkfunc", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ReportTypeText.keySet());
        for(Integer i:l){
            map.put(i, ReportTypeText.get(i));
        }
        TMPLANG.put("urtype", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ReportTotalOptionText.keySet());
        for(Integer i:l){
            map.put(i, ReportTotalOptionText.get(i));
        }
        TMPLANG.put("urtotal", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ReportDeptOptionText.keySet());
        for(Integer i:l){
            map.put(i, ReportDeptOptionText.get(i));
        }
        TMPLANG.put("urdept", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ReportPLUOptionText.keySet());
        for(Integer i:l){
            map.put(i, ReportPLUOptionText.get(i));
        }
        TMPLANG.put("urplu", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ReportDiscOptionText.keySet());
        for(Integer i:l){
            map.put(i, ReportDiscOptionText.get(i));
        }
        TMPLANG.put("urdisc", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ReportPaymentOptionText.keySet());
        for(Integer i:l){
            map.put(i, ReportPaymentOptionText.get(i));
        }
        TMPLANG.put("urpay", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ReportTaxOptionText.keySet());
        for(Integer i:l){
            map.put(i, ReportTaxOptionText.get(i));
        }
        TMPLANG.put("urtax", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ReportCancelOptionText.keySet());
        for(Integer i:l){
            map.put(i, ReportCancelOptionText.get(i));
        }
        TMPLANG.put("urvoid", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ConfigMenuText.keySet());
        for(Integer i:l){
            map.put(i, ConfigMenuText.get(i));
        }
        TMPLANG.put("cfgmenu", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ConfigDept.keySet());
        for(Integer i:l){
            map.put(i, ConfigDept.get(i));
        }
        TMPLANG.put("cfgdept", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ConfigPaymentHost.keySet());
        for(Integer i:l){
            map.put(i, ConfigPaymentHost.get(i));
        }
        TMPLANG.put("cfgcph", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ConfigPaymentType.keySet());
        for(Integer i:l){
            map.put(i, ConfigPaymentType.get(i));
        }
        TMPLANG.put("cfgcpt", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ConfigPLU.keySet());
        for(Integer i:l){
            map.put(i, ConfigPLU.get(i));
        }
        TMPLANG.put("cfgplu", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ConfigSetMenu.keySet());
        for(Integer i:l){
            map.put(i, ConfigSetMenu.get(i));
        }
        TMPLANG.put("cfgsetmenu", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ConfigDisc.keySet());
        for(Integer i:l){
            map.put(i, ConfigDisc.get(i));
        }
        TMPLANG.put("cfgdisc", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ConfigTax.keySet());
        for(Integer i:l){
            map.put(i, ConfigTax.get(i));
        }
        TMPLANG.put("cfgtax", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ConfigPayment.keySet());
        for(Integer i:l){
            map.put(i, ConfigPayment.get(i));
        }
        TMPLANG.put("cfgpay", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ConfigRefer.keySet());
        for(Integer i:l){
            map.put(i, ConfigRefer.get(i));
        }
        TMPLANG.put("cfgref", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ConfigHK.keySet());
        for(Integer i:l){
            map.put(i, ConfigHK.get(i));
        }
        TMPLANG.put("cfghotkey", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ConfigWindow.keySet());
        for(Integer i:l){
            map.put(i, ConfigWindow.get(i));
        }
        TMPLANG.put("cfgwin", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ConfigWinBtn.keySet());
        for(Integer i:l){
            map.put(i, ConfigWinBtn.get(i));
        }
        TMPLANG.put("cfgwinb", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ConfigMapL.keySet());
        for(Integer i:l){
            map.put(i, ConfigMapL.get(i));
        }
        TMPLANG.put("cfgmapl", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ConfigMapB.keySet());
        for(Integer i:l){
            map.put(i, ConfigMapB.get(i));
        }
        TMPLANG.put("cfgmapb", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ConfigPrinter.keySet());
        for(Integer i:l){
            map.put(i, ConfigPrinter.get(i));
        }
        TMPLANG.put("cfgprinter", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ConfigKP.keySet());
        for(Integer i:l){
            map.put(i, ConfigKP.get(i));
        }
        TMPLANG.put("cfgkp", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ConfigRcpt.keySet());
        for(Integer i:l){
            map.put(i, ConfigRcpt.get(i));
        }
        TMPLANG.put("cfgrcpt", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ConfigRpt.keySet());
        for(Integer i:l){
            map.put(i, ConfigRpt.get(i));
        }
        TMPLANG.put("cfgrpt", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ConfigPOSSys.keySet());
        for(Integer i:l){
            map.put(i, ConfigPOSSys.get(i));
        }
        TMPLANG.put("cfgpos", map);

        map = new HashMap<Integer,String>();
        l = new ArrayList<Integer>(ConfigUsers.keySet());
        for(Integer i:l){
            map.put(i, ConfigUsers.get(i));
        }
        TMPLANG.put("cfguser", map);

    }

    public static void RestoreTempLang(){

        List<String> l = new ArrayList<String>(TMPLANG.keySet());

        for(String langtype:l){
            Map<Integer,String> langdata = TMPLANG.get(langtype);
            List<Integer> i = new ArrayList<Integer>(langdata.keySet());

            for(Integer id:i){
                if(langtype.equalsIgnoreCase("gen")){//GENERAL
                    if(GeneralText.containsKey(id)){
                        GeneralText.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("pos")){//POS Cashier
                    if(CashierPOSText.containsKey(id)){
                        CashierPOSText.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("printspl")){//GENERAL
                    if(PrintSplText.containsKey(id)){
                        PrintSplText.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("rptgen")){//GENERAL
                    if(RptGenText.containsKey(id)){
                        RptGenText.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("hkobj")){//Hot Key Object Type Text
                    if(ObjTypeText.containsKey(id)){
                        ObjTypeText.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("hkfunc")){//Hot Key Function Text
                    if(FunctionText.containsKey(id)){
                        FunctionText.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("urtype")){//User Report Type Text
                    if(ReportTypeText.containsKey(id)){
                        ReportTypeText.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("urtotal")){//User Report Total Text
                    if(ReportTotalOptionText.containsKey(id)){
                        ReportTotalOptionText.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("urdept")){//User Report Department Text
                    if(ReportDeptOptionText.containsKey(id)){
                        ReportDeptOptionText.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("urplu")){//User Report PLU Text
                    if(ReportPLUOptionText.containsKey(id)){
                        ReportPLUOptionText.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("urdisc")){//User Report Discount Text
                    if(ReportDiscOptionText.containsKey(id)){
                        ReportDiscOptionText.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("urpay")){//User Report Payment Text
                    if(ReportPaymentOptionText.containsKey(id)){
                        ReportPaymentOptionText.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("urtax")){//User Report Tax Text
                    if(ReportTaxOptionText.containsKey(id)){
                        ReportTaxOptionText.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("urvoid")){//User Report Cancel Text
                    if(ReportCancelOptionText.containsKey(id)){
                        ReportCancelOptionText.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("cfgmenu")){//Config Menu Text
                    if(ConfigMenuText.containsKey(id)){
                        ConfigMenuText.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("cfgdept")){//Config Dept
                    if(ConfigDept.containsKey(id)){
                        ConfigDept.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("cfgcph")){//Config Configuration Payment Host
                    if(ConfigPaymentHost.containsKey(id)){
                        ConfigPaymentHost.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("cfgcpt")){//Config Configuration Payment Type
                    if(ConfigPaymentType.containsKey(id)){
                        ConfigPaymentType.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("cfgplu")){//Config PLU
                    if(ConfigPLU.containsKey(id)){
                        ConfigPLU.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("cfgsetmenu")){//Config Set Menu Text
                    if(ConfigSetMenu.containsKey(id)){
                        ConfigSetMenu.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("cfgdisc")){//Config DISC
                    if(ConfigDisc.containsKey(id)){
                        ConfigDisc.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("cfgtax")){//Config TAX
                    if(ConfigTax.containsKey(id)){
                        ConfigTax.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("cfgpay")){//Config Payment
                    if(ConfigPayment.containsKey(id)){
                        ConfigPayment.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("cfgref")){//Config Refer Info
                    if(ConfigRefer.containsKey(id)){
                        ConfigRefer.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("cfghotkey")){//Config HotKey
                    if(ConfigHK.containsKey(id)){
                        ConfigHK.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("cfgwin")){//Config Window
                    if(ConfigWindow.containsKey(id)){
                        ConfigWindow.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("cfgwinb")){//Config Window Button Editor
                    if(ConfigWinBtn.containsKey(id)){
                        ConfigWinBtn.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("cfgmapl")){//Config Map Layout
                    if(ConfigMapL.containsKey(id)){
                        ConfigMapL.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("cfgmapb")){//Config Map Layout Button Editor
                    if(ConfigMapB.containsKey(id)){
                        ConfigMapB.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("cfgprinter")){//Config Printer
                    if(ConfigPrinter.containsKey(id)){
                        ConfigPrinter.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("cfgkp")){//Config Kitchen Printer
                    if(ConfigKP.containsKey(id)){
                        ConfigKP.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("cfgrcpt")){//Config Receipt Header/Footer
                    if(ConfigRcpt.containsKey(id)){
                        ConfigRcpt.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("cfgrpt")){//Config User Report
                    if(ConfigRpt.containsKey(id)){
                        ConfigRpt.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("cfgpos")){//Config POSSys
                    if(ConfigPOSSys.containsKey(id)){
                        ConfigPOSSys.put(id, langdata.get(id));
                    }
                }else if(langtype.equalsIgnoreCase("cfguser")){//Config Users
                    if(ConfigUsers.containsKey(id)){
                        ConfigUsers.put(id, langdata.get(id));
                    }
                }
            }
        }
    }

    public static boolean ExportTempLanguageFile(String filepath){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
            String lastlangtype = "";

            List<String> l = new ArrayList<String>(TMPLANG.keySet());
            Collections.sort(l);
            for(String langtype:l){
                Map<Integer,String> langdata = TMPLANG.get(langtype);
                List<Integer> i = new ArrayList<Integer>(langdata.keySet());
                Collections.sort(i);
                for(Integer id:i){
                    if(!lastlangtype.equalsIgnoreCase(langtype)){
                        if(langtype.equalsIgnoreCase("gen")){
                            bw.write(";General Message");
                        }else if(langtype.equalsIgnoreCase("pos")){
                            bw.write(";POS Cashier Text");
                        }else if(langtype.equalsIgnoreCase("printspl")){
                            bw.write(";PrinterSpooler Text");
                        }else if(langtype.equalsIgnoreCase("printspl")){
                            bw.write(";PrinterSpooler Text");
                        }else if(langtype.equalsIgnoreCase("rptgen")){
                            bw.write(";Report Viewer");
                        }else if(langtype.equalsIgnoreCase("hkobj")){
                            bw.write(";Hot Key Object Text");
                        }else if(langtype.equalsIgnoreCase("hkfunc")){
                            bw.write(";Hot Key Function Text");
                        }else if(langtype.equalsIgnoreCase("urtype")){
                            bw.write(";User Report Type Text");
                        }else if(langtype.equalsIgnoreCase("urtotal")){
                            bw.write(";User Report Total Sales Option Text");
                        }else if(langtype.equalsIgnoreCase("urtotal")){
                            bw.write(";User Report Total Sales Option Text");
                        }else if(langtype.equalsIgnoreCase("urdept")){
                            bw.write(";User Report Department Option Text");
                        }else if(langtype.equalsIgnoreCase("urplu")){
                            bw.write(";User Report PLU Option Text");
                        }else if(langtype.equalsIgnoreCase("urdisc")){
                            bw.write(";User Report Discount Option Text");
                        }else if(langtype.equalsIgnoreCase("urpay")){
                            bw.write(";User Report Payment Option Text");
                        }else if(langtype.equalsIgnoreCase("urtax")){
                            bw.write(";User Report Tax Option Text");
                        }else if(langtype.equalsIgnoreCase("urvoid")){
                            bw.write(";User Report Cancel Option Text");
                        }else if(langtype.equalsIgnoreCase("urref")){
                            bw.write(";User Report Refer Info Option Text");
                        }else if(langtype.equalsIgnoreCase("cfgmenu")){
                            bw.write(";Setup Menu");
                        }else if(langtype.equalsIgnoreCase("cfgdb")){
                            bw.write(";Setup Database");
                        }else if(langtype.equalsIgnoreCase("cfgdept")){
                            bw.write(";Setup Department");
                        }else if(langtype.equalsIgnoreCase("cfgcph")){
                            bw.write(";Setup Config Payment Host");
                        }else if(langtype.equalsIgnoreCase("cfgcpt")){
                            bw.write(";Setup Config Payment Type");
                        }else if(langtype.equalsIgnoreCase("cfgplu")){
                            bw.write(";Setup PLU");
                        }else if(langtype.equalsIgnoreCase("cfgsetmenu")){
                            bw.write(";Setup Set Menu");
                        }else if(langtype.equalsIgnoreCase("cfgdisc")){
                            bw.write(";Setup Discount");
                        }else if(langtype.equalsIgnoreCase("cfgkp")){
                            bw.write(";Setup KP");
                        }else if(langtype.equalsIgnoreCase("cfgpay")){
                            bw.write(";Setup Payment");
                        }else if(langtype.equalsIgnoreCase("cfghotkey")){
                            bw.write(";Setup HotKey");
                        }else if(langtype.equalsIgnoreCase("cfghotkey")){
                            bw.write(";Setup HotKey");
                        }else if(langtype.equalsIgnoreCase("cfgwin")){
                            bw.write(";Setup Window");
                        }else if(langtype.equalsIgnoreCase("cfgwinb")){
                            bw.write(";Setup Window Button");
                        }else if(langtype.equalsIgnoreCase("cfgmapl")){
                            bw.write(";Setup Map Layout");
                        }else if(langtype.equalsIgnoreCase("cfgmapb")){
                            bw.write(";Setup Map Layout Button Editor");
                        }else if(langtype.equalsIgnoreCase("cfgtax")){
                            bw.write(";Setup Tax Config");
                        }else if(langtype.equalsIgnoreCase("cfgrpt")){
                            bw.write(";Setup Setup Report Config");
                        }else if(langtype.equalsIgnoreCase("cfgpos")){
                            bw.write(";Setup POSConfig");
                        }else if(langtype.equalsIgnoreCase("cfgprinter")){
                            bw.write(";Setup Printer");
                        }else if(langtype.equalsIgnoreCase("cfgrcpt")){
                            bw.write(";Receipt Header/Footer Setting");
                        }else if(langtype.equalsIgnoreCase("cfgref")){
                            bw.write(";Refer Setting");
                        }else if(langtype.equalsIgnoreCase("cfguser")){
                            bw.write(";Users Setting");
                        }
                        bw.newLine();

                        lastlangtype = langtype;
                    }

                    bw.write(langtype+"_"+id+"="+langdata.get(id).replaceAll("\n", "\\\\n"));
                    bw.newLine();
                }
            }

            bw.flush();
            bw.close();
            return true;
        }catch(Exception e){
            Logger.WriteLog("StrTextConst",e.toString());
            return false;
        }
    }

    public static String GetText(TextType type, int id, Object ... args){
        String msg = null;
        switch(type){
            case GENERAL:
                msg = GeneralText.get(id);
                break;
            case POS:
                msg = CashierPOSText.get(id);
                break;
            case PRINTSPL:
                msg = PrintSplText.get(id);
                break;
            case RPTGEN:
                msg = RptGenText.get(id);
                break;
            case CFGMENU:
                msg = ConfigMenuText.get(id);
                break;
            case CFGDEPT:
                msg = ConfigDept.get(id);
                break;
            case CFGCPH:
                msg = ConfigPaymentHost.get(id);
                break;
            case CFGCPT:
                msg = ConfigPaymentType.get(id);
                break;
            case CFGPLU:
                msg = ConfigPLU.get(id);
                break;
            case CFGSETMENU:
                msg = ConfigSetMenu.get(id);
                break;
            case CFGDISC:
                msg = ConfigDisc.get(id);
                break;
            case CFGTAX:
                msg = ConfigTax.get(id);
                break;
            case CFGPAYMENT:
                msg = ConfigPayment.get(id);
                break;
            case CFGREFER:
                msg = ConfigRefer.get(id);
                break;
            case CFGHOTKEY:
                msg = ConfigHK.get(id);
                break;
            case CFGWINDOW:
                msg = ConfigWindow.get(id);
                break;
            case CFGWINBTN:
                msg = ConfigWinBtn.get(id);
                break;
            case CFGMAPL:
                msg = ConfigMapL.get(id);
                break;
            case CFGMAPB:
                msg = ConfigMapB.get(id);
                break;
            case CFGPRINTER:
                msg = ConfigPrinter.get(id);
                break;
            case CFGKP:
                msg = ConfigKP.get(id);
                break;
            case CFGRCPT:
                msg = ConfigRcpt.get(id);
                break;
            case CFGRPT:
                msg = ConfigRpt.get(id);
                break;
            case CFGUSER:
                msg = ConfigUsers.get(id);
                break;
            case CFGDB:
                msg = ConfigDB.get(id);
                break;
            case CFGPOS:
                msg = ConfigPOSSys.get(id);
                break;

        }
        if(msg==null){
            msg = "(msg:"+type.name() + "_"+id+")";
        }

        msg = msg.replaceAll("'", "''").replaceAll("\\\\n", "\n").replaceAll("\\\\t", "\t");

        return MessageFormat.format(msg, args);
    }


    public enum TextType{
        GENERAL,
        POS,
        PRINTSPL,
        RPTGEN,
        CFGMENU,
        CFGDEPT,
        CFGPLU,
        CFGSETMENU,
        CFGDISC,
        CFGTAX,
        CFGREFER,
        CFGPAYMENT,
        CFGPRINTER,
        CFGKP,
        CFGHOTKEY,
        CFGWINDOW,
        CFGWINBTN,
        CFGMAPL,
        CFGMAPB,
        CFGRCPT,
        CFGRPT,
        CFGUSER,
        CFGPOS,
        CFGDB,
        CFGCPH,
        CFGCPT,
    }
}
