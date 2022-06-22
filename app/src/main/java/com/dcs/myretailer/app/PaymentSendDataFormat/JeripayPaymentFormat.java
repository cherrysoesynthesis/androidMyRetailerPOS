package com.dcs.myretailer.app.PaymentSendDataFormat;

import android.content.Intent;

import com.dcs.myretailer.app.Activity.CheckOutActivity;

public class JeripayPaymentFormat {

    public static Intent launchIntent(Intent intent, String BillNo, Double cardamount) {
        intent.putExtra("amount", Double.parseDouble(
                CheckOutActivity.getConvertedAmount(String.format("%.2f", (cardamount * 100)))));
        intent.putExtra("command", "sale");
        intent.putExtra("terminalTransactionId", BillNo);
//                launchIntent.putExtra("type", "payment");
        intent.putExtra("type", "Payment");

         return intent;


//                {
//                    'command': 'sale',
//                        'amount':1.00,
//                        'terminalTransactionId':'Trsansaction_number',
//                        'type':'Payment'
//                }
    }

    public static Intent mercatusMember(Intent intent, Double cardamount) {

        intent.putExtra("command", "member");
        intent.putExtra("amount", Double.parseDouble(
                CheckOutActivity.getConvertedAmount(String.format("%.2f", (cardamount * 100)))));
        return intent;
    }
    public static Intent mercatusNotMember(Intent intent, Double cardamount) {
        intent.putExtra("amount", Double.parseDouble(
                CheckOutActivity.getConvertedAmount(String.format("%.2f", (cardamount * 100)))));
        intent.putExtra("command", "nonMember");


//                    Parameter  For Nonmember Transaction
//                    {
//                        'command': nonMember,
//                            'amount':100.00
//                    }

        return intent;
    }
}
