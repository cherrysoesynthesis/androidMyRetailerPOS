package com.dcs.myretailer.app;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dcs.myretailer.app.Activity.CashLayoutActivity;
import com.dcs.myretailer.app.Activity.TransactionDetailsActivity;
import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;

import org.w3c.dom.Document;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ReprintBill {
    public ReprintBill(TransactionDetailsActivity context,
                      final Integer salesid, String BillNo, String STATUS
            , String dateFormat3, String checkBillnodate) {

        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                System.currentTimeMillis(), DBFunc.PurifyString("TransactionDetailsActivity-OnClick " +"Reprint Bill-"+BillNo));

        //String accessable = SyncActivity.volleyCheckPermission(this, Constraints.REPRINTBILL,Constraints.Accessable);
//                String accessable = Query.SearchUserAccess(Constraints.M0022, RemarkMainActivity.userid,RemarkMainActivity.userpassword);
//                if (accessable.equals("1")){
        //if (Constraints.Accessable.equals("1")) {
        final SweetAlertDialog pDialog =  new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(Constraints.ReprintQuestion)
                .setConfirmText(Constraints.YES)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();

                        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                                System.currentTimeMillis(), DBFunc.PurifyString("TransactionDetailsActivity-OnClick-YES" +"Reprint Bill-"+BillNo));



                        //CashLayoutActivity.PrintingReceipt(sales_id,getApplicationContext(),"SALES");
                        //Bitmap[] bitmap_qr_shoptima = Query.GetPrintQRCodeOnReceipt(getApplicationContext());
                        String chk_qr_code_on_receipt = Query.GetOptions(18);

                        if (chk_qr_code_on_receipt.equals("1")) {
                            // bitmap_qr_shoptima = Query.GetPrintQRCodeOnReceipt(getApplicationContext());
                            //Query.GetPrintQRCodeOnReceipt(getApplicationContext());
                            //QRCode Reprint
                            try {
                                GetPrintQRCodeOnReceipt(context, BillNo, salesid);
                            } catch (Exception e){

                            }
                        }else {
                            //Reprint Normal
                            Integer salesid = TransactionDetailsActivity.getSalesIDByBillNo(TransactionDetailsActivity.BillNo);

                            if (salesid != null && salesid > 0) {
                                Query.CheckEmulatorOrNot(context, salesid, BillNo, "Reprint", "Reprint", null, null);
                            }

                        }

                    }
                })
                .setCancelButton(Constraints.No, new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();

                        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                                System.currentTimeMillis(), DBFunc.PurifyString("TransactionDetailsActivity-OnClick-NO" +"Reprint Bill-"+BillNo));


                    }
                });
        pDialog.show();
        pDialog.setCancelable(false);
//                } else {
//                    Query.DonothaveUserAccess(this);
//                }

    }
    public static void GetPrintQRCodeOnReceipt(Context context, String BillNo, Integer sales_id) {
        //Bitmap[] bitmapQRCode = new Bitmap[1];
        //Bitmap bitmapQRCode = null;
        String  status_str_info = Query.GetStatus(BillNo);

        if (status_str_info.toUpperCase().equals(Constraints.SALES.toUpperCase())) {
            String str_info = Query.GetQRCodeFromBillPayment(BillNo);

            //bitmapQRCode = GenerateQRCodeString(context, str_info);
            GenerateQRCodeString(context, str_info,BillNo,sales_id);
        }
        //Log.i("TAGGG___","status_str_info__d_"+bitmapQRCode);
        //return bitmapQRCode;
    }
    private static void GenerateQRCodeString(final Context context, final String strInfo,
                                             final String BillNo, final Integer sales_id) {

        final SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        //final SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading ...");
        //pDialog.setCancelable(true);
//        pDialog.show();
        //final Bitmap[] bitmapQRCode = new Bitmap[1];

        RequestQueue queue_qrcode = Volley.newRequestQueue(context);
        Log.i("First_TESTING","First_TESTING");
//        RequestQueue queue = Volley.newRequestQueue(this);
        //String url = "http://" + qr_code_shoptima_url;
        String url = MainActivity.qr_code_shoptima_url;
        Log.i("qr_code_shopturl_dd",url);
        //String url = "http://" + "llposmgr.ddns.net:8085/Service.asmx";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //pDialog.dismiss();
                        // response code
                        String xmlString = response;
                        Log.i("GenerateQRCodeString_", response);
//                        <?xml version="1.0" encoding="utf-8"?><soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema"><soap:Body><GenerateQRCodeStringResponse xmlns="http://tempuri.org/"><GenerateQRCodeStringResult><xs:schema id="NewDataSet" xmlns="" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msdata="urn:schemas-microsoft-com:xml-msdata"><xs:element name="NewDataSet" msdata:IsDataSet="true" msdata:MainDataTable="ReturnTable" msdata:UseCurrentLocale="true"><xs:complexType><xs:choice minOccurs="0" maxOccurs="unbounded"><xs:element name="ReturnTable"><xs:complexType><xs:sequence><xs:element name="STATUS" type="xs:string" minOccurs="0" /><xs:element name="ERRORCODE" type="xs:string" minOccurs="0" /><xs:element name="QRCODE" type="xs:string" minOccurs="0" /></xs:sequence></xs:complexType></xs:element></xs:choice></xs:complexType></xs:element></xs:schema><diffgr:diffgram xmlns:msdata="urn:schemas-microsoft-com:xml-msdata" xmlns:diffgr="urn:schemas-microsoft-com:xml-diffgram-v1"><DocumentElement xmlns=""><ReturnTable diffgr:id="ReturnTable1" msdata:rowOrder="0" diffgr:hasChanges="inserted"><STATUS>SUCCESS</STATUS><ERRORCODE>0</ERRORCODE><QRCODE>dcs:ZMiWiadjOZm90Rszvn5+SDCYtZ060vKEq+XRWhfgR3Jd6Lujro5gl1iKxsSKQuoOHUUjpInfZ6HmrecQ5LLuOgRvmPm750E4F1td66GcmRS7I16DCd4SRCEMWexHpdxNP5T89iNNIVFXrq8RP1sbdQ==</QRCODE></ReturnTable></DocumentElement></diffgr:diffgram></GenerateQRCodeStringResult></GenerateQRCodeStringResponse></soap:Body></soap:Envelope>
//
                        Document xmlparse = null;
                        Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);
                        String status = "";
                        //String qrcodestring = "";
                        String dcsQrCodeString = "";
                        for (int ii = 0; ii < parse.getElementsByTagName("STATUS").getLength(); ii++) {
                            status = (parse.getElementsByTagName("STATUS").getLength() > 0)
                                    ? parse.getElementsByTagName("STATUS").item(ii).getTextContent() : " ";
                        }
                        for (int iii = 0; iii < parse.getElementsByTagName("QRCODE").getLength(); iii++) {
                            dcsQrCodeString = (parse.getElementsByTagName("QRCODE").getLength() > 0)
                                    ? parse.getElementsByTagName("QRCODE").item(iii).getTextContent() : " ";
                        }

                        //qrcodestring = dcsQrCodeString.split(":")[1];
                        String qrcodestring = dcsQrCodeString;
                        //qrcodestring = "dcs:ZMiWiadjOZm90Rszvn5+SG7Ep2MsGAfnfx8xoCE1e3rA4mtgaoy0BfjbFEHlqY5dRAZzgiBdN8zO+uJulLTQ3flsQ0wozp2FOyNmX2pCABM1QJIlTooayanA5NhSv3nQboljX7a7KnjoRCF/zOXccA==";
                        Log.i("qrcodestring_","qrcodestring_"+qrcodestring);
                        //bitmap_qr_shoptima = convertQRCodeImage(context,qrcodestring);
                        //convertQRCodeImage(dcsQrCodeString);
                        //bitmapQRCode[0] = convertQRCodeImage(context, qrcodestring);
                        //bitmapQRCode = convertQRCodeImage(context, qrcodestring);
                        TransactionDetailsActivity.convertQRCodeImage(context, qrcodestring, pDialog,BillNo,sales_id);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("First_TESTING","First_error");
//                        // As of f605da3 the following should work
                NetworkResponse response = error.networkResponse;
                Log.i("errorres:1",error.getMessage());
                Log.i("errorres:", String.valueOf(error));
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));

                        Log.i("res:",res);
                        // Now you can use any deserializer to make sense of data
//                                JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }) {
            @Override
            public Map<String, String> getParams() {
                return null;
            }
            @Override
            public byte[] getBody() {
                String encodedURL = null;
                String temp = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                        "  <soap12:Body>\n" +
                        "    <GenerateQRCodeString xmlns=\"http://tempuri.org/\">\n" +
                        "      <UserID>"+MainActivity.qr_code_shoptima_user_id+"</UserID>\n" +
                        "      <Password>"+MainActivity.qr_code_shoptima_password+"</Password>\n" +
                        "      <strInfo>"+strInfo+"</strInfo>\n" +
                        "    </GenerateQRCodeString>\n" +
                        "  </soap12:Body>\n" +
                        "</soap12:Envelope>";
                Log.i("APPshp", "request__String: " + temp);
                byte[] b = temp.getBytes(Charset.forName("UTF-8"));

                return b;
            }

            @Override
            public String getBodyContentType() {
                return "text/xml;charset=utf-8";
            }
        };
        queue_qrcode.add(stringRequest);
        //pDialog.show();
        //return bitmapQRCode;
        //progressDialog = Query.showProgressDialog(context, ENUM.Downaloding);
    }

}
