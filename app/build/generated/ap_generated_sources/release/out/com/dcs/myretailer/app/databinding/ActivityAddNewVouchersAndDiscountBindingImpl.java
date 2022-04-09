package com.dcs.myretailer.app.databinding;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityAddNewVouchersAndDiscountBindingImpl extends ActivityAddNewVouchersAndDiscountBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.layOverAll, 2);
        sViewsWithIds.put(R.id.linear_voucher_name, 3);
        sViewsWithIds.put(R.id.edit_text_voucher_name, 4);
        sViewsWithIds.put(R.id.linear_discount_name, 5);
        sViewsWithIds.put(R.id.edit_text_discount_name, 6);
        sViewsWithIds.put(R.id.linear_discount_rate, 7);
        sViewsWithIds.put(R.id.edit_text_discount_rate, 8);
        sViewsWithIds.put(R.id.lineartype, 9);
        sViewsWithIds.put(R.id.type_name, 10);
        sViewsWithIds.put(R.id.btn_type, 11);
        sViewsWithIds.put(R.id.linear_discount_type, 12);
        sViewsWithIds.put(R.id.btn_discount_type, 13);
        sViewsWithIds.put(R.id.img_discount_type, 14);
        sViewsWithIds.put(R.id.linear_discount_value_expiry_date, 15);
        sViewsWithIds.put(R.id.edit_text_expiry_date, 16);
        sViewsWithIds.put(R.id.voucher_expiry_date, 17);
        sViewsWithIds.put(R.id.linear_discount_value_amount, 18);
        sViewsWithIds.put(R.id.edit_text_value_amount, 19);
        sViewsWithIds.put(R.id.linear_voucher_code, 20);
        sViewsWithIds.put(R.id.edit_text_voucher_code, 21);
        sViewsWithIds.put(R.id.scan_vooucher_code, 22);
        sViewsWithIds.put(R.id.chk_allow_bill_discount, 23);
        sViewsWithIds.put(R.id.chk_allow_amt_discount, 24);
        sViewsWithIds.put(R.id.linearopendiscount, 25);
        sViewsWithIds.put(R.id.chk_open_discount, 26);
        sViewsWithIds.put(R.id.chk_allow_service_charge_discount, 27);
        sViewsWithIds.put(R.id.LayBtn, 28);
        sViewsWithIds.put(R.id.btn_delete_discount, 29);
        sViewsWithIds.put(R.id.btn_add_discount, 30);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityAddNewVouchersAndDiscountBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 31, sIncludes, sViewsWithIds));
    }
    private ActivityAddNewVouchersAndDiscountBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[28]
            , (android.widget.ScrollView) bindings[0]
            , (android.widget.Button) bindings[30]
            , (android.widget.Button) bindings[29]
            , (android.widget.EditText) bindings[13]
            , (android.widget.ImageView) bindings[11]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[24]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[23]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[27]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[26]
            , (android.widget.EditText) bindings[6]
            , (android.widget.EditText) bindings[8]
            , (android.widget.EditText) bindings[16]
            , (android.widget.EditText) bindings[19]
            , (android.widget.EditText) bindings[21]
            , (android.widget.EditText) bindings[4]
            , (android.widget.ImageView) bindings[14]
            , (android.widget.LinearLayout) bindings[2]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.LinearLayout) bindings[7]
            , (android.widget.LinearLayout) bindings[12]
            , (android.widget.LinearLayout) bindings[18]
            , (android.widget.LinearLayout) bindings[15]
            , (android.widget.LinearLayout) bindings[20]
            , (android.widget.LinearLayout) bindings[3]
            , (android.widget.LinearLayout) bindings[25]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.ImageView) bindings[22]
            , (android.widget.EditText) bindings[10]
            , (android.widget.ImageView) bindings[17]
            );
        this.ScrollView01.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
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