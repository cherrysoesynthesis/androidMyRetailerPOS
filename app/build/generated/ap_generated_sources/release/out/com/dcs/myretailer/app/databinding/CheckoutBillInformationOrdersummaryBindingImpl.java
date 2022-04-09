package com.dcs.myretailer.app.databinding;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class CheckoutBillInformationOrdersummaryBindingImpl extends CheckoutBillInformationOrdersummaryBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.card_view1, 1);
        sViewsWithIds.put(R.id.laySubCardView, 2);
        sViewsWithIds.put(R.id.laySubCardViewDetails, 3);
        sViewsWithIds.put(R.id.totitm_header_checkout, 4);
        sViewsWithIds.put(R.id.checkout_total_item, 5);
        sViewsWithIds.put(R.id.LaySubTotal, 6);
        sViewsWithIds.put(R.id.textViewNamehgjhgj, 7);
        sViewsWithIds.put(R.id.checkout_subtotal, 8);
        sViewsWithIds.put(R.id.bill_item_dis_layout, 9);
        sViewsWithIds.put(R.id.billitemdiscountheader, 10);
        sViewsWithIds.put(R.id.txt_item_discount_checkout, 11);
        sViewsWithIds.put(R.id.LayDis, 12);
        sViewsWithIds.put(R.id.bill_discount_name, 13);
        sViewsWithIds.put(R.id.btn_remove_discount_checkout, 14);
        sViewsWithIds.put(R.id.btn_add_discount_checkout, 15);
        sViewsWithIds.put(R.id.txt_discount_checkout, 16);
        sViewsWithIds.put(R.id.add_member, 17);
        sViewsWithIds.put(R.id.textViewName54, 18);
        sViewsWithIds.put(R.id.textViewName17676767, 19);
        sViewsWithIds.put(R.id.add_member_icon, 20);
        sViewsWithIds.put(R.id.service_charges_layout, 21);
        sViewsWithIds.put(R.id.service_chargesname, 22);
        sViewsWithIds.put(R.id.service_charges_value, 23);
        sViewsWithIds.put(R.id.inc_tax_layout, 24);
        sViewsWithIds.put(R.id.inc_taxname, 25);
        sViewsWithIds.put(R.id.tax_layout, 26);
        sViewsWithIds.put(R.id.taxname, 27);
        sViewsWithIds.put(R.id.tax_value, 28);
        sViewsWithIds.put(R.id.roundAdj_layout, 29);
        sViewsWithIds.put(R.id.roundAdjHeader, 30);
        sViewsWithIds.put(R.id.round_adj_value, 31);
        sViewsWithIds.put(R.id.multiple_payment, 32);
        sViewsWithIds.put(R.id.text_payment_cash, 33);
        sViewsWithIds.put(R.id.text_payment_cash_amount, 34);
        sViewsWithIds.put(R.id.laySubCardViewDetailsTotalAmt, 35);
        sViewsWithIds.put(R.id.totalNettTitle, 36);
        sViewsWithIds.put(R.id.checkout_total, 37);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public CheckoutBillInformationOrdersummaryBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 38, sIncludes, sViewsWithIds));
    }
    private CheckoutBillInformationOrdersummaryBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[12]
            , (android.widget.LinearLayout) bindings[6]
            , (android.widget.LinearLayout) bindings[17]
            , (android.widget.ImageView) bindings[20]
            , (android.widget.TextView) bindings[13]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.TextView) bindings[10]
            , (android.widget.ImageView) bindings[15]
            , (android.widget.ImageView) bindings[14]
            , (androidx.cardview.widget.CardView) bindings[1]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[37]
            , (android.widget.TextView) bindings[5]
            , (android.widget.LinearLayout) bindings[24]
            , (android.widget.TextView) bindings[25]
            , (android.widget.LinearLayout) bindings[0]
            , (android.widget.LinearLayout) bindings[2]
            , (android.widget.LinearLayout) bindings[3]
            , (android.widget.LinearLayout) bindings[35]
            , (android.widget.LinearLayout) bindings[32]
            , (android.widget.TextView) bindings[30]
            , (android.widget.LinearLayout) bindings[29]
            , (android.widget.TextView) bindings[31]
            , (android.widget.LinearLayout) bindings[21]
            , (android.widget.TextView) bindings[23]
            , (android.widget.TextView) bindings[22]
            , (android.widget.LinearLayout) bindings[26]
            , (android.widget.TextView) bindings[28]
            , (android.widget.TextView) bindings[27]
            , (android.widget.TextView) bindings[33]
            , (android.widget.TextView) bindings[34]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[36]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[11]
            );
        this.layCardView.setTag(null);
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