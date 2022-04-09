package com.dcs.myretailer.app;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.dcs.myretailer.app.Database.DBFunc;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class APIActivity extends Activity {
    String strGetSalesPersonResult = "";
    String strGetCustomersTotalCountResult = "";
    String strGetCustomersResult = "";
    String strCompanyCode = "";
    String strFromRow = "";
    String strToRow = "";
    final String url = "http://49.128.60.174:44343/myRetailerAPI.asmx";
    String SalesPersonsID = "";
    String LoginID = "";
    String LoginPswd = "";
    String UserName = "";
    String Email = "";
    String HandphoneNo = "";
    String DOB = "";
    String UserLevel = "";
    String UserGroup = "";
    String Salutation = "";
    String UsersCommision = "";
    String CustomersID = "";
    String custcode = "";
    String FirstName = "";
    String LastName = "";
    String CustICNO = "";
    String MobileNo = "";
    String cardnumber = "";
    String Address1 = "";
    String Address2 = "";
    String Address3 = "";
    String PostalCode = "";
    String CustomerType = "";
    String Gender = "";
    String LoyaltyPoint = "";
    int TotalRequestCustomerCount = 0;
    int TotalCount = 0;
    int FromRow = 0;
    int ToRow = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
    }

    public void volleyGetSalesPerson() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // response code
                        String xmlString = response;
                        Log.i("ddddfsfdsf", xmlString);
                        //updatePayment(finalCompany_code1, finalOption_id,bill_payment_type.toUpperCase(), finalUrl);
                        Document xmlparse = null;
                        Document parse = XMLParseFunction(xmlString, xmlparse);
                        for (int i = 0; i < parse.getElementsByTagName("getSalesPersonResult").getLength(); i++) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
                                strGetSalesPersonResult = (parse.getElementsByTagName("getSalesPersonResult").getLength() > 0)
                                        ? parse.getElementsByTagName("getSalesPersonResult").item(i).getTextContent() : " ";
                            }
                        }
                        try {


                            JSONObject obj = new JSONObject(strGetSalesPersonResult);
                            String inv_obj = obj.getString("SalesPersons");
                            //Log.i("inv_obj",inv_obj);
                            JSONArray mJsonArray = new JSONArray(inv_obj);

                            JSONObject SalesPersonsObject = null;
                            //Log.i("mJsonArrayLength", String.valueOf(mJsonArray.length()));
                            DBFunc.ExecQuery("DELETE FROM SalesPersons", true);

                            for (int i =0; i < mJsonArray.length(); i++){
                                SalesPersonsObject = mJsonArray.getJSONObject(i);
                                SalesPersonsID = SalesPersonsObject.getString("ID");
                                LoginID = SalesPersonsObject.getString("LoginID");
                                LoginPswd = SalesPersonsObject.getString("LoginPswd");
                                UserName = SalesPersonsObject.getString("UserName");
                                Email = SalesPersonsObject.getString("Email");
                                HandphoneNo = SalesPersonsObject.getString("HandphoneNo");
                                DOB = SalesPersonsObject.getString("DOB");
                                UserLevel = SalesPersonsObject.getString("UserLevel");
                                UserGroup = SalesPersonsObject.getString("UserGroup");
                                Salutation = SalesPersonsObject.getString("Salutation");
                                UsersCommision = SalesPersonsObject.getString("UsersCommision");

                                DBFunc.ExecQuery("INSERT INTO Inventory (SalesPersonsID,LoginID,LoginPswd,UserName,Email,HandphoneNo,DOB," +
                                        "UserLevel,UserGroup,Salutation,UsersCommision) " +
                                        "VALUES ('"+DBFunc.PurifyString(SalesPersonsID)+"','"+DBFunc.PurifyString(LoginID)+"', "
                                        +"'"+DBFunc.PurifyString(LoginPswd).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(UserName).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(Email).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(HandphoneNo).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(DOB).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(UserLevel).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(UserGroup).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(Salutation).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(UsersCommision).toLowerCase().trim()+"')", true);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("ddddfsfdsf__", String.valueOf(error));
//                        // As of f605da3 the following should work
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));

                        // Now you can use any deserializer to make sense of data
//                                JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
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
                String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                        "  <soap12:Body>\n" +
                        "    <getSalesPerson xmlns=\"http://tempuri.org/\">\n" +
                        "      <companyCode>"+strCompanyCode+"</companyCode>\n" +
                        "    </getSalesPerson>\n" +
                        "  </soap12:Body>\n" +
                        "</soap12:Envelope>";
                //Log.i("APP", "request String: " + temp);
                byte[] b = temp.getBytes(Charset.forName("UTF-8"));

                return b;
            }

            @Override
            public String getBodyContentType() {
                return "text/xml;charset=utf-8";
            }
        };
        Log.i("_stringRsssssequest", String.valueOf(stringRequest));
        queue.add(stringRequest);
        // }
    }

    public void volleyGetCustomersTotalCountn() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // response code
                        String xmlString = response;
                        Log.i("ddddfsfdsf", xmlString);
                        //updatePayment(finalCompany_code1, finalOption_id,bill_payment_type.toUpperCase(), finalUrl);
                        Document xmlparse = null;
                        Document parse = XMLParseFunction(xmlString, xmlparse);
                        for (int i = 0; i < parse.getElementsByTagName("getCustomersTotalCountResult").getLength(); i++) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
                                strGetCustomersTotalCountResult = (parse.getElementsByTagName("getCustomersTotalCountResult").getLength() > 0)
                                        ? parse.getElementsByTagName("getCustomersTotalCountResult").item(i).getTextContent() : " ";
                            }
                        }
                        try {


                            JSONObject obj = new JSONObject(strGetCustomersTotalCountResult);
                            String inv_obj = obj.getString("Customers");
                            //Log.i("inv_obj",inv_obj);
                            JSONArray mJsonArray = new JSONArray(inv_obj);

                            JSONObject CustomersObject = null;
                            //Log.i("mJsonArrayLength", String.valueOf(mJsonArray.length()));
                            DBFunc.ExecQuery("DELETE FROM SalesPersons", true);


                            for (int i =0; i < mJsonArray.length(); i++){
                                CustomersObject = mJsonArray.getJSONObject(i);
                                TotalCount = CustomersObject.getInt("TotalCount");
                            }
                            TotalRequestCustomerCount = (TotalCount / 5000) + 1;
                            for(int i = 0; i <= TotalRequestCustomerCount; i++)
                            {
                                //companyCode = xxx;
                                FromRow = (5000*i)+1;
                                ToRow = (i+1)*5000;
                                //call getCustomer API
                                //save json to DB
                                volleyGetCustomers();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("ddddfsfdsf__", String.valueOf(error));
//                        // As of f605da3 the following should work
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));

                        // Now you can use any deserializer to make sense of data
//                                JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
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
                String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                        "  <soap12:Body>\n" +
                        "    <getCustomersTotalCount xmlns=\"http://tempuri.org/\">\n" +
                        "      <companyCode>"+strCompanyCode+"</companyCode>\n" +
                        "    </getCustomersTotalCount>\n" +
                        "  </soap12:Body>\n" +
                        "</soap12:Envelope>";
                //Log.i("APP", "request String: " + temp);
                byte[] b = temp.getBytes(Charset.forName("UTF-8"));

                return b;
            }

            @Override
            public String getBodyContentType() {
                return "text/xml;charset=utf-8";
            }
        };
        Log.i("_stringRsssssequest", String.valueOf(stringRequest));
        queue.add(stringRequest);
        // }
    }

    public void volleyGetCustomers() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // response code
                        String xmlString = response;
                        Log.i("ddddfsfdsf", xmlString);
                        //updatePayment(finalCompany_code1, finalOption_id,bill_payment_type.toUpperCase(), finalUrl);
                        Document xmlparse = null;
                        Document parse = XMLParseFunction(xmlString, xmlparse);
                        for (int i = 0; i < parse.getElementsByTagName("getCustomersResult").getLength(); i++) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
                                strGetCustomersResult = (parse.getElementsByTagName("getCustomersResult").getLength() > 0)
                                        ? parse.getElementsByTagName("getCustomersResult").item(i).getTextContent() : " ";
                            }
                        }
                        try {


                            JSONObject obj = new JSONObject(strGetCustomersResult);
                            String inv_obj = obj.getString("Customers");
                            //Log.i("inv_obj",inv_obj);
                            JSONArray mJsonArray = new JSONArray(inv_obj);

                            JSONObject CustomersObject = null;
                            //Log.i("mJsonArrayLength", String.valueOf(mJsonArray.length()));
                            DBFunc.ExecQuery("DELETE FROM Customers", true);
                            for (int i =0; i < mJsonArray.length(); i++){
                                CustomersObject = mJsonArray.getJSONObject(i);
                                CustomersID = CustomersObject.getString("ID");
                                custcode = CustomersObject.getString("custcode");
                                FirstName = CustomersObject.getString("FirstName");
                                LastName = CustomersObject.getString("LastName");
                                CustICNO = CustomersObject.getString("CustICNO");
                                Email = CustomersObject.getString("Email");
                                MobileNo = CustomersObject.getString("MobileNo");
                                cardnumber = CustomersObject.getString("cardnumber");
                                DOB = CustomersObject.getString("DOB");
                                Address1 = CustomersObject.getString("Address1");
                                Address2 = CustomersObject.getString("Address2");
                                Address3 = CustomersObject.getString("Address3");
                                PostalCode = CustomersObject.getString("PostalCode");
                                CustomerType = CustomersObject.getString("CustomerType");
                                Gender = CustomersObject.getString("Gender");
                                LoyaltyPoint = CustomersObject.getString("LoyaltyPoint");

                                DBFunc.ExecQuery("INSERT INTO Inventory (CustomersID,custcode,FirstName,LastName,CustICNO,Email,MobileNo,cardnumber,DOB," +
                                        "Address1,Address2,Address3,PostalCode,CustomerType,Gender,LoyaltyPoint) " +
                                        "VALUES ('"+DBFunc.PurifyString(CustomersID)+"','"+DBFunc.PurifyString(custcode)+"', "
                                        +"'"+DBFunc.PurifyString(FirstName).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(LastName).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(CustICNO).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(Email).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(DOB).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(MobileNo).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(cardnumber).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(Address1).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(Address2).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(Address3).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(PostalCode).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(CustomerType).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(Gender).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(LoyaltyPoint).toLowerCase().trim()+"')", true);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("ddddfsfdsf__", String.valueOf(error));
//                        // As of f605da3 the following should work
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));

                        // Now you can use any deserializer to make sense of data
//                                JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
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
                String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                        "  <soap12:Body>\n" +
                        "    <getCustomers xmlns=\"http://tempuri.org/\">\n" +
                        "      <companyCode>"+strCompanyCode+"</companyCode>\n" +
                        "      <FromRow>"+strFromRow+"</FromRow>\n" +
                        "      <ToRow>"+strToRow+"</ToRow>\n" +
                        "    </getCustomers>\n" +
                        "  </soap12:Body>\n" +
                        "</soap12:Envelope>";
                //Log.i("APP", "request String: " + temp);
                byte[] b = temp.getBytes(Charset.forName("UTF-8"));

                return b;
            }

            @Override
            public String getBodyContentType() {
                return "text/xml;charset=utf-8";
            }
        };
        Log.i("_stringRsssssequest", String.valueOf(stringRequest));
        queue.add(stringRequest);
        // }
    }

    public static Document XMLParseFunction(String xmlString, Document parse) {
        DocumentBuilder newDocumentBuilder = null;
        try {
            newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        try {
            parse = newDocumentBuilder.parse(new ByteArrayInputStream(xmlString.getBytes()));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parse;
    }
}
