package com.dcs.myretailer.app.Report;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Arrays;

public class SerialChecker {

    //private static String magicbase = "VJIY8APKZDXCEO4U1S5W27QNFG0L9TBH6R3M";

    public static boolean CheckSerial(Context context){
        File file = new File(context.getFilesDir(), "info.dat");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            if(line == null){
                return false;
            }
            int[] key = SerialChecker.DeGenSerial(line);
            int[] code = SerialChecker.Encode(context);
            if(Arrays.equals(code, key)){
                return true;
            }else{
                return false;
            }
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static String GetID(Context context){
        File file = new File(context.getFilesDir(), "info.dat");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            if(line == null){
                return null;
            }
            int[] key = SerialChecker.DeGenSerial(line);
            int[] code = SerialChecker.Encode(context);
            if(Arrays.equals(code, key)){
                //return true;
                int[]code2 = SerialChecker.DeGenSerial(SerialChecker.GenHWIDCode(code));
                DecimalFormat df = new DecimalFormat("00000");
                int value1 = key[0]+key[1]+key[2]+key[3]+key[4];
                int value2 = key[5]+key[6]+key[7]+key[8]+key[9];
                int value3 = key[10]+key[11]+key[12]+key[13]+key[14];
                int value4 = code2[0]+code2[1]+code2[2]+code2[3]+code2[4];
                int value5 = code2[5]+code2[6]+code2[7]+code2[8]+code2[9];
                int value6 = code2[10]+code2[11]+code2[12]+code2[13]+code2[14];
                return df.format(value4)+df.format(value1)+"-"+df.format(value5)+df.format(value2)+"-"+df.format(value6)+df.format(value3);
            }else{
                return null;
            }
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static String CreateMagic(){
        byte[] value = {0x56,0x4A,0x49,0x59,0x38,0x41,0x50,0x4B,0x5A,0x44,0x58,0x43,0x45,0x4F,0x34,0x55,0x31,0x53,0x35,0x57,0x32,0x37,0x51,0x4E,0x46,0x47,0x30,0x4C,0x39,0x54,0x42,0x48,0x36,0x52,0x33,0x4D};
        return new String(value, StandardCharsets.US_ASCII);
    }

//    private static String[] GetHWID(Context context){
//        String androidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
//        //Add UniqueID Because of Duplicate Encode Value for two terminals
//        //String uniqueID = UUID.randomUUID().toString();
//        String uniqueID = "a";
//        androidID = androidID + uniqueID;
//
//        String buildserial = Build.SERIAL;
//        String hwid = buildserial;
//		/*
//		try {
//		    Class<?> c = Class.forName("android.os.SystemProperties");
//		    Method get = c.getMethod("get", String.class);
//		    hwid = (String) get.invoke(c, "ro.serialno");
//		} catch (Exception ignored) {
//		}
//		*/
//
//        //TelephonyManager tManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
//        //@SuppressLint({"MissingPermission", "HardwareIds"}) String teleID = tManager.getDeviceId();
//        TelephonyManager tManager=(TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        String teleID = "";
//        try {
//            teleID = tManager.getDeviceId();
//            if (androidID == null) {
//                androidID = "";
//            }
//        } catch (Exception e){
//            androidID = "";
//        }
//        if(buildserial == null){
//            buildserial = "";
//        }
//        if(hwid == null){
//            hwid = "";
//        }
//        if(teleID == null){
//            teleID = "";
//        }
//        return new String[]{androidID, buildserial, hwid, teleID};
//    }

    private static String[] GetHWID(Context context){
//        String androidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        //Add UniqueID Because of Duplicate Encode Value for two terminals
        //String uniqueID = UUID.randomUUID().toString();
//        String uniqueID = "a";
        //androidID = androidID + uniqueID;
//        androidID = androidID;

        String buildserial = Build.SERIAL;
       // String hwid = buildserial;
		/*
		try {
		    Class<?> c = Class.forName("android.os.SystemProperties");
		    Method get = c.getMethod("get", String.class);
		    hwid = (String) get.invoke(c, "ro.serialno");
		} catch (Exception ignored) {
		}
		*/

        //TelephonyManager tManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        //@SuppressLint({"MissingPermission", "HardwareIds"}) String teleID = tManager.getDeviceId();
        TelephonyManager tManager=(TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String teleID = "";
        try {
            teleID = tManager.getDeviceId();
//            if (androidID == null) {
//                androidID = "";
//            }
        } catch (Exception e){
//            androidID = "";
        }
//        if(buildserial == null){
//            buildserial = "";
//        }
//        if(hwid == null){
//            hwid = "";
//        }
        if(teleID == null){
            teleID = "";
        }
        //return new String[]{androidID, buildserial, hwid, teleID};
        buildserial = "NM2J203B110500996";
        teleID = "357415080989329";
        return new String[]{buildserial ,teleID};
    }

//    public static int[] Encode(Context context){
//        int[] code = new int[15];
//        String[] hwid = GetHWID(context);
//        for(int i=0;i<code.length;i++){
//            int gen = 0;
//            for(String s1:hwid){
//                for(int x=0;x<s1.length();x++){
//
//                    for(int j=hwid.length-1;j>=0;j--){
//                        for(int y=hwid[j].length()-1;y>=0;y--){
//                            gen += (s1.codePointAt(x) * hwid[j].codePointAt(y)) * (s1.length()+hwid[j].length()*(i+1));
//                        }
//                    }
//                }
//            }
//            code[i] = (gen)&0xFF;
//        }
//        return code;
//    }

    public static int[] Encode(Context context){
        int[] code = new int[15];
        String[] hwid = GetHWID(context);
        for(int i=0;i<code.length;i++){
            int gen = 0;
            for(String s1:hwid){
                for(int x=0;x<s1.length();x++){

                    for(int j=hwid.length-1;j>=0;j--){
                        for(int y=hwid[j].length()-1;y>=0;y--){
                            gen += (s1.codePointAt(x) * hwid[j].codePointAt(y)) * (s1.length()+hwid[j].length()*(i+1));
                        }
                    }
                }
            }
            code[i] = (gen)&0xFF;
        }
        return code;
    }

    public static String GenHWIDCode(int[] code){

        String value = "";

        for(int c:code){
            value+=convertbase(c);
        }

        String dashed = "";
        for(int i=0;i<5;i++){
            if(i<4){
                dashed += value.substring(i*6,(i*6)+6)+"-";
            }else{
                dashed += value.substring(i*6);
            }
        }

        return dashed;
    }
	/*
	public static int[] ReverseHWIDCode(String key)throws Exception{
		String undash = key.replaceAll("-", "").toUpperCase();


		int[] code = new int[15];
		for(int i=0;i<undash.length();i+=2){
			code[i/2] = reversebase(undash.substring(i, i+2));
			//Log.e("123", undash.length()+"/"+i+"="+(i/2)+"="+cc+"/"+c[i/2]);
			if(code[i/2] <0 || code[i/2]>0xFF){
				throw new Exception("Software Error or unexpected problem occured!");
			}
		}
		return code;
	}
	*/


    public static String GenSerial(int[] code){
        int[] _code = new int[15];
        for(int i=0;i<code.length;i++){
            String tmp1 = String.format("%02X", code[i]);
            String tmp2 = String.format("%02X", code[(code.length-1)-i]);

            _code[i] = Integer.parseInt(tmp1.substring(1, 2)+tmp2.substring(0,1),16);
        }
        String value = "";
        for(int c:_code){
            value+=convertbase(c);
        }

        String dashed = "";
        for(int i=0;i<5;i++){
            if(i<4){
                dashed += value.substring(i*6,(i*6)+6)+"-";
            }else{
                dashed += value.substring(i*6);
            }
        }


        return dashed;
    }

    public static int[] DeGenSerial(String serial) throws Exception{
        if(serial.length()!=34){
            throw new Exception("Software Error or unexpected problem occured!");
        }
        String[] undashchk = serial.split("-");
        if(undashchk.length!=5){
            throw new Exception("Software Error or unexpected problem occured!");
        }
        for(String s:undashchk){
            if(s.length()!=6){
                throw new Exception("Software Error or unexpected problem occured!");
            }
        }
        String undash = serial.replaceAll("-", "").toUpperCase();


        int[] _code = new int[15];
        for(int i=0;i<undash.length();i+=2){
            _code[i/2] = reversebase(undash.substring(i, i+2));
            if(_code[i/2] <0 || _code[i/2]>0xFF){
                throw new Exception("Software Error or unexpected problem occured!");
            }
        }

        int[] code = new int[15];

        for(int i=0;i<_code.length;i++){
            String tmp1 = String.format("%02X", _code[(_code.length-1)-i]);
            String tmp2 = String.format("%02X", _code[i]);
            try{
                code[i] = Integer.parseInt((tmp1.substring(1, 2)+tmp2.substring(0,1)),16);
                if(code[i]<0 || code[i]>0xFF){
                    throw new Exception("Software Error or unexpected problem occured!");
                }
            }catch(NumberFormatException e){
                throw new Exception("Software Error or unexpected problem occured!");
            }


        }
        return code;
    }

    private static String convertbase(int i){
        String val = "";
        int quotient = i/36;
        val = (getbasevalue(i%36)+"");
        while(quotient>0){
            val = (getbasevalue(quotient%36)+"")+val;
            quotient = quotient/36;
        }
        if(val.length()==1){
            val = getbasevalue(0)+val;
        }
        return val;
    }

    private static int reversebase(String s){
        int val=0;
        for(int i=0;i<s.length();i++){
            int tmp = getreversebasevalue(s.substring(i,i+1));
            for(int j=0;j<(s.length()-i)-1;j++){
                tmp *=36;
            }
            val +=tmp;
        }
        return val;
    }

    private static int getreversebasevalue(String val){
        String tmp = CreateMagic();
        int pos = tmp.indexOf(val);
        if(pos==-1){
            throw new IndexOutOfBoundsException("Invalid value or negative value occured!");
        }
        return pos;
    }

    private static String getbasevalue(int val){
        String tmp = CreateMagic();
        if(val<0 || val>=tmp.length()){
            throw new IndexOutOfBoundsException("Invalid value or value is out of array bound!");
        }
        return tmp.substring(val, val+1);
		/*
		switch(i){
			case 0: return "V";
			case 1: return "J";
			case 2: return "I";
			case 3: return "Y";
			case 4: return "8";
			case 5: return "A";
			case 6: return "P";
			case 7: return "B";
			case 8: return "Z";
			case 9: return "D";
			case 10: return "X";
			case 11: return "C";
			case 12: return "E";
			case 13: return "O";
			case 14: return "L";
			case 15: return "U";
			case 16: return "1";
			case 17: return "S";
			case 18: return "5";
			case 19: return "W";
			case 20: return "2";
			case 21: return "7";
			case 22: return "Q";
			case 23: return "N";
			case 24: return "F";
			case 25: return "G";
			case 26: return "0";
			case 27: return "4";
			case 28: return "9";
			case 29: return "T";
			case 30: return "K";
			case 31: return "H";
			case 32: return "6";
			case 33: return "R";
			case 34: return "3";
			case 35: return "M";
			default: return "";
		}
		*/
    }

}
