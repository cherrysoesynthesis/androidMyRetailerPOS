package com.dcs.myretailer.app.databinding;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityZCloseResyncBindingImpl extends ActivityZCloseResyncBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.layZClose, 2);
        sViewsWithIds.put(R.id.txt_uuid_zclose_hidden, 3);
        sViewsWithIds.put(R.id.edit_select_zclose, 4);
        sViewsWithIds.put(R.id.img_zclose, 5);
        sViewsWithIds.put(R.id.Laybtn, 6);
        sViewsWithIds.put(R.id.btn_show_resync_zclose, 7);
        sViewsWithIds.put(R.id.btn_resync_zclose, 8);
        sViewsWithIds.put(R.id.LayAll, 9);
        sViewsWithIds.put(R.id.LayZReadNo, 10);
        sViewsWithIds.put(R.id.edit_text_zreadno, 11);
        sViewsWithIds.put(R.id.lay_trans_from_no, 12);
        sViewsWithIds.put(R.id.edit_text_trans_from, 13);
        sViewsWithIds.put(R.id.lay_trans_to_no, 14);
        sViewsWithIds.put(R.id.edit_text_trans_to, 15);
        sViewsWithIds.put(R.id.LayOpeningTime, 16);
        sViewsWithIds.put(R.id.edit_text_opening, 17);
        sViewsWithIds.put(R.id.LayClosingTime, 18);
        sViewsWithIds.put(R.id.edit_text_closing, 19);
        sViewsWithIds.put(R.id.LaySyncStatus, 20);
        sViewsWithIds.put(R.id.edit_text_sync_status, 21);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityZCloseResyncBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds));
    }
    private ActivityZCloseResyncBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.LinearLayout) bindings[18]
            , (android.widget.LinearLayout) bindings[16]
            , (android.widget.LinearLayout) bindings[20]
            , (android.widget.LinearLayout) bindings[10]
            , (android.widget.LinearLayout) bindings[6]
            , (android.widget.ScrollView) bindings[0]
            , (android.widget.Button) bindings[8]
            , (android.widget.Button) bindings[7]
            , (android.widget.EditText) bindings[4]
            , (android.widget.EditText) bindings[19]
            , (android.widget.EditText) bindings[17]
            , (android.widget.EditText) bindings[21]
            , (android.widget.EditText) bindings[13]
            , (android.widget.EditText) bindings[15]
            , (android.widget.EditText) bindings[11]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.LinearLayout) bindings[12]
            , (android.widget.LinearLayout) bindings[14]
            , (android.widget.LinearLayout) bindings[2]
            , (android.widget.TextView) bindings[3]
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