package com.dcs.myretailer.app.databinding;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityCashLayoutBindingImpl extends ActivityCashLayoutBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.layOverAll, 1);
        sViewsWithIds.put(R.id.Layshowamt, 2);
        sViewsWithIds.put(R.id.total, 3);
        sViewsWithIds.put(R.id.layLine, 4);
        sViewsWithIds.put(R.id.layshowcollected, 5);
        sViewsWithIds.put(R.id.laycollected, 6);
        sViewsWithIds.put(R.id.cash_collected, 7);
        sViewsWithIds.put(R.id.change_due, 8);
        sViewsWithIds.put(R.id.layshowall, 9);
        sViewsWithIds.put(R.id.btn_10, 10);
        sViewsWithIds.put(R.id.btn_15, 11);
        sViewsWithIds.put(R.id.btn_20, 12);
        sViewsWithIds.put(R.id.btn_25, 13);
        sViewsWithIds.put(R.id.btn_30, 14);
        sViewsWithIds.put(R.id.btn_50, 15);
        sViewsWithIds.put(R.id.btn_100, 16);
        sViewsWithIds.put(R.id.btn_500, 17);
        sViewsWithIds.put(R.id.btn_fixed, 18);
        sViewsWithIds.put(R.id.Laybtnaccept, 19);
        sViewsWithIds.put(R.id.btn_accept, 20);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityCashLayoutBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 21, sIncludes, sViewsWithIds));
    }
    private ActivityCashLayoutBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[19]
            , (android.widget.LinearLayout) bindings[2]
            , (android.widget.Button) bindings[10]
            , (android.widget.Button) bindings[16]
            , (android.widget.Button) bindings[11]
            , (android.widget.Button) bindings[12]
            , (android.widget.Button) bindings[13]
            , (android.widget.Button) bindings[14]
            , (android.widget.Button) bindings[15]
            , (android.widget.Button) bindings[17]
            , (android.widget.Button) bindings[20]
            , (android.widget.Button) bindings[18]
            , (android.widget.EditText) bindings[7]
            , (android.widget.TextView) bindings[8]
            , (android.widget.LinearLayout) bindings[4]
            , (android.widget.LinearLayout) bindings[1]
            , (android.widget.LinearLayout) bindings[6]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.TextView) bindings[3]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
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