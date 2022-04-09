package com.dcs.myretailer.app.databinding;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityReportSettingBindingImpl extends ActivityReportSettingBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.LaySales, 2);
        sViewsWithIds.put(R.id.chk_sales, 3);
        sViewsWithIds.put(R.id.txt_sales, 4);
        sViewsWithIds.put(R.id.layCategory, 5);
        sViewsWithIds.put(R.id.chk_category, 6);
        sViewsWithIds.put(R.id.txt_category, 7);
        sViewsWithIds.put(R.id.LayProductSales, 8);
        sViewsWithIds.put(R.id.chk_product_sales, 9);
        sViewsWithIds.put(R.id.txt_product_sales, 10);
        sViewsWithIds.put(R.id.LayPayment, 11);
        sViewsWithIds.put(R.id.chk_payment, 12);
        sViewsWithIds.put(R.id.txt_payment, 13);
        sViewsWithIds.put(R.id.LayDiscount, 14);
        sViewsWithIds.put(R.id.chk_discount, 15);
        sViewsWithIds.put(R.id.txt_discount, 16);
        sViewsWithIds.put(R.id.chk_tax, 17);
        sViewsWithIds.put(R.id.txt_tax, 18);
        sViewsWithIds.put(R.id.LayCancellation, 19);
        sViewsWithIds.put(R.id.chk_cancellation, 20);
        sViewsWithIds.put(R.id.txt_cancellation, 21);
        sViewsWithIds.put(R.id.LayReferInfoSales, 22);
        sViewsWithIds.put(R.id.chk_refer_info, 23);
        sViewsWithIds.put(R.id.txt_refer_info, 24);
        sViewsWithIds.put(R.id.LayRefund, 25);
        sViewsWithIds.put(R.id.chk_refund, 26);
        sViewsWithIds.put(R.id.txt_refund, 27);
        sViewsWithIds.put(R.id.LayBtnSave, 28);
        sViewsWithIds.put(R.id.btn_save_report_settings, 29);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityReportSettingBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 30, sIncludes, sViewsWithIds));
    }
    private ActivityReportSettingBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[28]
            , (android.widget.LinearLayout) bindings[19]
            , (android.widget.LinearLayout) bindings[14]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.LinearLayout) bindings[22]
            , (android.widget.LinearLayout) bindings[25]
            , (android.widget.LinearLayout) bindings[2]
            , (android.widget.ScrollView) bindings[0]
            , (android.widget.Button) bindings[29]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[20]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[6]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[15]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[12]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[9]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[23]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[26]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[3]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[17]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.TextView) bindings[21]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[24]
            , (android.widget.TextView) bindings[27]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[18]
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