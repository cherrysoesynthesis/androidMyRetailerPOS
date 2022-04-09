package com.dcs.myretailer.app.databinding;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityMapSetupBindingImpl extends ActivityMapSetupBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.spn_list, 1);
        sViewsWithIds.put(R.id.layOverAll, 2);
        sViewsWithIds.put(R.id.et_name, 3);
        sViewsWithIds.put(R.id.choose_color, 4);
        sViewsWithIds.put(R.id.btn_color, 5);
        sViewsWithIds.put(R.id.edit_text_product_name1, 6);
        sViewsWithIds.put(R.id.lbl_1, 7);
        sViewsWithIds.put(R.id.et_name1, 8);
        sViewsWithIds.put(R.id.lbl_2, 9);
        sViewsWithIds.put(R.id.btn_color1, 10);
        sViewsWithIds.put(R.id.lbl_3, 11);
        sViewsWithIds.put(R.id.btn_img, 12);
        sViewsWithIds.put(R.id.btn_edit, 13);
        sViewsWithIds.put(R.id.btn_edit1, 14);
        sViewsWithIds.put(R.id.btn_save, 15);
        sViewsWithIds.put(R.id.btn_del, 16);
        sViewsWithIds.put(R.id.btn_back, 17);
        sViewsWithIds.put(R.id.btn_save1, 18);
        sViewsWithIds.put(R.id.btn_del1, 19);
        sViewsWithIds.put(R.id.btn_back1, 20);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityMapSetupBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 21, sIncludes, sViewsWithIds));
    }
    private ActivityMapSetupBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[17]
            , (android.widget.Button) bindings[20]
            , (com.dcs.myretailer.app.FlatButton) bindings[5]
            , (com.dcs.myretailer.app.FlatButton) bindings[10]
            , (android.widget.Button) bindings[16]
            , (android.widget.Button) bindings[19]
            , (android.widget.Button) bindings[13]
            , (android.widget.Button) bindings[14]
            , (com.dcs.myretailer.app.FlatButton) bindings[12]
            , (android.widget.Button) bindings[15]
            , (android.widget.Button) bindings[18]
            , (android.widget.LinearLayout) bindings[4]
            , (android.widget.EditText) bindings[6]
            , (android.widget.EditText) bindings[3]
            , (android.widget.EditText) bindings[8]
            , (android.widget.LinearLayout) bindings[2]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[11]
            , (android.widget.Spinner) bindings[1]
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