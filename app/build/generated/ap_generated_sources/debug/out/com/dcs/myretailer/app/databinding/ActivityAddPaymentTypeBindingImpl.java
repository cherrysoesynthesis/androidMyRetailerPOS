package com.dcs.myretailer.app.databinding;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityAddPaymentTypeBindingImpl extends ActivityAddPaymentTypeBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.layOverAll, 2);
        sViewsWithIds.put(R.id.Lay1, 3);
        sViewsWithIds.put(R.id.edit_text_payment_name, 4);
        sViewsWithIds.put(R.id.paymenttype_onoff, 5);
        sViewsWithIds.put(R.id.Lay2, 6);
        sViewsWithIds.put(R.id.edit_text_payment_amount, 7);
        sViewsWithIds.put(R.id.chk_allow_empty_cash, 8);
        sViewsWithIds.put(R.id.Lay3, 9);
        sViewsWithIds.put(R.id.chk_allow_link_to_payment_app, 10);
        sViewsWithIds.put(R.id.Lay4, 11);
        sViewsWithIds.put(R.id.chk_allow_integrate_to_shoptima, 12);
        sViewsWithIds.put(R.id.Lay5, 13);
        sViewsWithIds.put(R.id.chk_allow_full_amount, 14);
        sViewsWithIds.put(R.id.Lay6, 15);
        sViewsWithIds.put(R.id.chk_allow_rounding_activate, 16);
        sViewsWithIds.put(R.id.Lay7, 17);
        sViewsWithIds.put(R.id.chk_allow_remarks, 18);
        sViewsWithIds.put(R.id.Lay8, 19);
        sViewsWithIds.put(R.id.chk_allow_ezlink, 20);
        sViewsWithIds.put(R.id.LayBtn, 21);
        sViewsWithIds.put(R.id.btn_delete_payment, 22);
        sViewsWithIds.put(R.id.btn_add_payment, 23);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityAddPaymentTypeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 24, sIncludes, sViewsWithIds));
    }
    private ActivityAddPaymentTypeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[3]
            , (android.widget.LinearLayout) bindings[6]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.LinearLayout) bindings[13]
            , (android.widget.LinearLayout) bindings[15]
            , (android.widget.LinearLayout) bindings[17]
            , (android.widget.LinearLayout) bindings[19]
            , (android.widget.LinearLayout) bindings[21]
            , (android.widget.ScrollView) bindings[0]
            , (android.widget.Button) bindings[23]
            , (android.widget.Button) bindings[22]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[8]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[20]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[14]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[12]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[10]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[18]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[16]
            , (android.widget.EditText) bindings[7]
            , (android.widget.EditText) bindings[4]
            , (android.widget.LinearLayout) bindings[2]
            , (androidx.appcompat.widget.SwitchCompat) bindings[5]
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