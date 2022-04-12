package com.dcs.myretailer.app.databinding;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityTransactionDetailsBindingImpl extends ActivityTransactionDetailsBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.layCheckoutOverAll, 2);
        sViewsWithIds.put(R.id.cardLay, 3);
        sViewsWithIds.put(R.id.headerlay, 4);
        sViewsWithIds.put(R.id.headerlay1, 5);
        sViewsWithIds.put(R.id.laytransactionbillno, 6);
        sViewsWithIds.put(R.id.transaction_bill_no, 7);
        sViewsWithIds.put(R.id.img_reprint, 8);
        sViewsWithIds.put(R.id.img_resync_billno, 9);
        sViewsWithIds.put(R.id.LinearDt, 10);
        sViewsWithIds.put(R.id.transaction_datetime, 11);
        sViewsWithIds.put(R.id.img_refresh_bill, 12);
        sViewsWithIds.put(R.id.img_reprint_kitchenprinter, 13);
        sViewsWithIds.put(R.id.transaction_cashier, 14);
        sViewsWithIds.put(R.id.transaction_payment_mode, 15);
        sViewsWithIds.put(R.id.jeripay_invoice_no, 16);
        sViewsWithIds.put(R.id.transaction_table_no, 17);
        sViewsWithIds.put(R.id.transaction_voucher_no, 18);
        sViewsWithIds.put(R.id.transaction_receipt_order_status, 19);
        sViewsWithIds.put(R.id.transaction_delivery_status, 20);
        sViewsWithIds.put(R.id.Linerar_refund_or_cancel, 21);
        sViewsWithIds.put(R.id.btn_cancel_bill, 22);
        sViewsWithIds.put(R.id.btn_refund_or_checkout, 23);
        sViewsWithIds.put(R.id.Linerar_refund_or_cancel_txt, 24);
        sViewsWithIds.put(R.id.btn_cancel_bill_txt, 25);
        sViewsWithIds.put(R.id.btn_refund_or_checkouttxt, 26);
        sViewsWithIds.put(R.id.Linerar_collected_or_delivery_txt, 27);
        sViewsWithIds.put(R.id.btn_collected_bill_txt, 28);
        sViewsWithIds.put(R.id.btn_delivery_txt, 29);
        sViewsWithIds.put(R.id.LayHeader, 30);
        sViewsWithIds.put(R.id.LayDescription, 31);
        sViewsWithIds.put(R.id.txtdescription, 32);
        sViewsWithIds.put(R.id.card_view111111, 33);
        sViewsWithIds.put(R.id.checkOutListView, 34);
        sViewsWithIds.put(R.id.lay_salessummary, 35);
        sViewsWithIds.put(R.id.transactionCard, 36);
        sViewsWithIds.put(R.id.lay_sale_summary_details, 37);
        sViewsWithIds.put(R.id.totalitm_header, 38);
        sViewsWithIds.put(R.id.transaction_total_items, 39);
        sViewsWithIds.put(R.id.subtotaltransactiondetails, 40);
        sViewsWithIds.put(R.id.transaction_grosssales, 41);
        sViewsWithIds.put(R.id.LayTotalItemDis, 42);
        sViewsWithIds.put(R.id.txttotalitemdis, 43);
        sViewsWithIds.put(R.id.transaction_totalitemdis, 44);
        sViewsWithIds.put(R.id.LayBillDiscount, 45);
        sViewsWithIds.put(R.id.total_bill_discount, 46);
        sViewsWithIds.put(R.id.transaction_totalbilldis, 47);
        sViewsWithIds.put(R.id.service_charges_layout, 48);
        sViewsWithIds.put(R.id.service_chargesname, 49);
        sViewsWithIds.put(R.id.service_charges_value, 50);
        sViewsWithIds.put(R.id.lay_inc_transation_tax, 51);
        sViewsWithIds.put(R.id.inc_Tax_namee, 52);
        sViewsWithIds.put(R.id.lay_exc_transation_tax, 53);
        sViewsWithIds.put(R.id.Tax_namee, 54);
        sViewsWithIds.put(R.id.transaction_taxes, 55);
        sViewsWithIds.put(R.id.LayRoundAdj, 56);
        sViewsWithIds.put(R.id.txtRoundAdj, 57);
        sViewsWithIds.put(R.id.roundAdj_values, 58);
        sViewsWithIds.put(R.id.lay_total_amt, 59);
        sViewsWithIds.put(R.id.txtTotalAmt, 60);
        sViewsWithIds.put(R.id.transaction_totalamount, 61);
        sViewsWithIds.put(R.id.card_view111, 62);
        sViewsWithIds.put(R.id.itemsListView, 63);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityTransactionDetailsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 64, sIncludes, sViewsWithIds));
    }
    private ActivityTransactionDetailsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[45]
            , (android.widget.LinearLayout) bindings[31]
            , (android.widget.LinearLayout) bindings[30]
            , (android.widget.LinearLayout) bindings[56]
            , (android.widget.LinearLayout) bindings[42]
            , (android.widget.LinearLayout) bindings[10]
            , (android.widget.LinearLayout) bindings[27]
            , (android.widget.LinearLayout) bindings[21]
            , (android.widget.LinearLayout) bindings[24]
            , (android.widget.ScrollView) bindings[0]
            , (android.widget.TextView) bindings[54]
            , (android.widget.Button) bindings[22]
            , (android.widget.TextView) bindings[25]
            , (android.widget.TextView) bindings[28]
            , (android.widget.TextView) bindings[29]
            , (android.widget.Button) bindings[23]
            , (android.widget.TextView) bindings[26]
            , (androidx.cardview.widget.CardView) bindings[3]
            , (androidx.cardview.widget.CardView) bindings[62]
            , (androidx.cardview.widget.CardView) bindings[33]
            , (com.dcs.myretailer.app.MyListView) bindings[34]
            , (android.widget.LinearLayout) bindings[4]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.ImageView) bindings[12]
            , (android.widget.ImageView) bindings[8]
            , (android.widget.ImageView) bindings[13]
            , (android.widget.ImageView) bindings[9]
            , (android.widget.TextView) bindings[52]
            , (com.dcs.myretailer.app.MyListView) bindings[63]
            , (android.widget.TextView) bindings[16]
            , (android.widget.LinearLayout) bindings[2]
            , (android.widget.LinearLayout) bindings[53]
            , (android.widget.LinearLayout) bindings[51]
            , (android.widget.LinearLayout) bindings[37]
            , (android.widget.LinearLayout) bindings[35]
            , (android.widget.LinearLayout) bindings[59]
            , (android.widget.LinearLayout) bindings[6]
            , (android.widget.TextView) bindings[58]
            , (android.widget.LinearLayout) bindings[48]
            , (android.widget.TextView) bindings[50]
            , (android.widget.TextView) bindings[49]
            , (android.widget.TextView) bindings[40]
            , (android.widget.TextView) bindings[46]
            , (android.widget.TextView) bindings[38]
            , (android.widget.TextView) bindings[7]
            , (androidx.cardview.widget.CardView) bindings[36]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[41]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[55]
            , (android.widget.TextView) bindings[39]
            , (android.widget.TextView) bindings[61]
            , (android.widget.TextView) bindings[47]
            , (android.widget.TextView) bindings[44]
            , (android.widget.TextView) bindings[18]
            , (android.widget.LinearLayout) bindings[1]
            , (android.widget.TextView) bindings[57]
            , (android.widget.TextView) bindings[60]
            , (android.widget.TextView) bindings[32]
            , (android.widget.TextView) bindings[43]
            );
        this.ScrollView01.setTag(null);
        this.transactiondetaillinerarlayout.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}