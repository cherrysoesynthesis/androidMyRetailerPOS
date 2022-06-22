package com.dcs.myretailer.app.Setting;


import android.util.Base64;
import android.util.Log;

//import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

//import java.util.Base64;

//import org.apache.commons.codec.binary.Base64;

public class JeripaySignature {
    private static final String charset = "UTF8";



//    public static byte[] hmac256(String data, String secret) throws Exception {
//        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
//        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
//        Log.i("secret_key_","secret_key__"+secret_key);
//        sha256_HMAC.init(secret_key);
//        Log.i("sha256_HMAC_","sha256_HMAC__"+sha256_HMAC);
//        String base_string_base64 = Base64.encodeToString(data.getBytes(), Base64.NO_WRAP);
//        Log.i("base_string_base64_","base_string_base64__"+base_string_base64);
//        return sha256_HMAC.doFinal(base_string_base64.getBytes());
//        //return sha256_HMAC.doFinal(data.getBytes());
//    }

    public static String hmac256(String base_string, String key) throws Exception {
        String retVal = "";
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            //SecretKeySpec secret = new SecretKeySpec(key.getBytes("UTF-8"), mac.getAlgorithm());
            //JW3kbGtlfG6XfE3p1eNB0e1o9oXWIHgVVQDT08frzGY%3D%0A
            SecretKeySpec secret = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            mac.init(secret);

            //String base_string_base64 = Base64.encodeToString(base_string.getBytes(), Base64.NO_WRAP);
            //Fu7PtY5Hfkr/NJHE8Mp9egXMP1CdmoPHrd/oTZp6+gM=
            //Fu7PtY5Hfkr/NJHE8Mp9egXMP1CdmoPHrd/oTZp6+gM=
            //Log.i("dfdfd____","Fu7PtY5Hfkr/NJHE8Mp9egXMP1CdmoPHrd/oTZp6+gM="+"__"+base_string+"__");
            //byte[] digest = mac.doFinal(base_string.getBytes());
            //Cg1b2Gukt1tdoPQlY/CApzB/SQyCtVpMYYSdib1c2aY=

            //byte[] digest = mac.doFinal("Fu7PtY5Hfkr/NJHE8Mp9egXMP1CdmoPHrd/oTZp6+gM=".getBytes());

            //String check = "Fu7PtY5Hfkr/NJHE8Mp9egXMP1CdmoPHrd/oTZp6+gM=";
            Log.i("hmac256", "Stringgg___" + base_string+"___");
            String check = base_string.trim();
            Log.i("hmac256", "check___" + check+"___");
            byte[] digest = mac.doFinal(check.getBytes());
            //byte[] digest = mac.doFinal(base_string.getBytes());
            //byte[] digest = mac.doFinal(base_string.getBytes("UTF8"));
            //String str = new String(base_string);
            //Log.i("hmac256str", "str" + str);
            //byte[] digest = mac.doFinal(str.getBytes("UTF8"));

            //byte[] digest = mac.doFinal("aeCHvpkJI2DhsdkgywS4Otg3JxMKEFedlI/1ZgiCBAs=".getBytes());
            //HChDKmmWDPyIOpQq6pD3fhCAeT2BmS5uNDoGP8amtQE=

            //android.util.Base64.encodeT
            //retVal = Base64.encodeToString(digest,Base64.DEFAULT);
            retVal = Base64.encodeToString(digest,Base64.NO_WRAP);


            Log.i("hmac256", "String_in_Base644_" + base_string);
            Log.i("hmac256", "keyy_" + key);
            Log.i("hmac256", "resultt_" + retVal);
        } catch (Exception e) {
            Log.i("error_",e.getMessage());
        }
        Log.i("hmac256", "result_" + retVal);
        return retVal;
    }

    public static String getSign(String data, String secret) throws Exception {

        //step 1 get content digest
//        byte[] bytesEncoded = Base64.encodeBase64(str.getBytes());
//        System.out.println("encoded value is " + new String(bytesEncoded));
//
//// Decode data on other side, by processing encoded data
//        byte[] valueDecoded = Base64.decodeBase64(bytesEncoded);

        //byte[] encodedBytes = Base64.encodeBase64(DigestUtils.sha256(data));
        //Log.i("___digest","digest__"+encodedBytes);
        //String digest = data.equals("") ? "" : Base64.getEncoder().encodeToString(DigestUtils.sha256(data));
        //String digest = data.equals("") ? "" : String.valueOf(Base64.encodeBase64(DigestUtils.sha256(data)));
        //String digest = data.equals("") ? "" : android.util.Base64.encodeToString(DigestUtils.sha256(data), 0);

        //String base64 = Base64.encodeToString(data, Base64.DEFAULT);

        JSONObject jsonParam2 = new JSONObject();
//                    jsonParam.put("storeId", storeId);
//                    jsonParam.put("partnerId",partnerId);
        jsonParam2.put("storeId", "633");
        jsonParam2.put("partnerId","WEEBO");

        //String digest = data.equals("") ? "" :  Base64.encodeToString(DigestUtils.sha256(jsonParam2.toString()), Base64.DEFAULT);
//        String digest = data.equals("") ? "" :  Base64.encodeToString(DigestUtils.sha256(data), Base64.DEFAULT);
        String digest = "";


        //Fu7PtY5Hfkr/NJHE8Mp9egXMP1CdmoPHrd/oTZp6+gM=
        //Fu7PtY5Hfkr/NJHE8Mp9egXMP1CdmoPHrd/oTZp6+gM=

        //encode = android.util.Base64.encodeToString(stream.toByteArray(), android.util.Base64.DEFAULT)
        //String signature = generator.sign(keyFile, android.util.Base64.decode(data, android.util.Base64.NO_WRAP));
        Log.i("___digest","digest__"+digest);

//        byte[] data = someData;
//        MessageDigest digester = MessageDigest.getInstance("SHA-256");
//        digester.update(data);
//        String base64Encoded = Base64.getEncoder().encodeToString(digester.digest());



        //step 2 get text to sign
        String text = digest;

////        String hmac = Base64.getEncoder().encodeToString(hmac256(text, secret));
//        //String hmac = String.valueOf(Base64.encodeBase64(hmac256(text, secret)));
//        //String hmac = android.util.Base64.encodeToString(hmac256(text, secret),0);
//        //String hmac = Base64.encodeToString(hmac256(text, secret),android.util.Base64.DEFAULT);
//        //String enc = new String(digest);
//        //String retVal = Base64.encodeToString(enc,0);
//        //String retVal = Base64.encodeToString(digest.getBytes(),0);

        //Log.i("Hmac256","hmac256_"+hmac256(digest, secret));
        //String s = new String(hmac256(digest, secret), StandardCharsets.UTF_8);
        //Log.i("Hmac256","hmac256str_"+s);
        //String hmac = Base64.encodeToString(hmac256(digest, secret),Base64.DEFAULT);
        String hmac = hmac256(digest, secret);
//        //byte[] aa = hmac256(text, "s34b78u6nw23ds1357b56e34");
//        //Log.i("___aa","aa__"+aa);
//        //String hmac = Base64.encodeToString(aa,Base64.DEFAULT);
//        //String hmac = "Dy5wHW7eu266cQLpOSGMfvdmGnr2IxmaEXaGx7WRD/Y=";
//        //Dy5wHW7eu266cQLpOSGMfvdmGnr2IxmaEXaGx7WRD/Y=
//        //Cg1b2Gukt1tdoPQlY/CApzB/SQyCtVpMYYSdib1c2aY=
//        Log.i("___hmac","hmaccc__"+hmac);
//        String hmac = "";
//
//        try {
//            byte[] hmacSha256 = calcHmacSha256(secret.getBytes("UTF-8"),digest.getBytes("UTF-8"));
//            //Log.i("Hex: %032x", new BigInteger(1, hmacSha256));
//            hmac = Base64.encodeToString(hmacSha256,Base64.DEFAULT);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        Log.i("___hmac","hmaccc__"+hmac);
        return hmac;
    }

    static public byte[] calcHmacSha256(byte[] secretKey, byte[] message) {
        byte[] hmacSha256 = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "HmacSHA256");
            mac.init(secretKeySpec);
            hmacSha256 = mac.doFinal(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate hmac-sha256", e);
        }
        return hmacSha256;

    }

    public static boolean verify(String sign, String data, String secret) throws Exception {
        String signLocal = getSign(data, secret);
        if (signLocal != null && sign.equals(signLocal))
            return true;

        return false;
    }


}
