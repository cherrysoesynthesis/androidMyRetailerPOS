package com.dcs.myretailer.app.databinding;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentReportDateSheetDialogBindingImpl extends FragmentReportDateSheetDialogBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.linear_close, 1);
        sViewsWithIds.put(R.id.close_cashier_float_amt, 2);
        sViewsWithIds.put(R.id.Lay1, 3);
        sViewsWithIds.put(R.id.edit_text_datecount, 4);
        sViewsWithIds.put(R.id.LayStarting, 5);
        sViewsWithIds.put(R.id.edit_text_starting, 6);
        sViewsWithIds.put(R.id.img_text_starting, 7);
        sViewsWithIds.put(R.id.LayEnding, 8);
        sViewsWithIds.put(R.id.edit_text_ending, 9);
        sViewsWithIds.put(R.id.img_text_ending, 10);
        sViewsWithIds.put(R.id.LayPrevious, 11);
        sViewsWithIds.put(R.id.type_name, 12);
        sViewsWithIds.put(R.id.btn_type, 13);
        sViewsWithIds.put(R.id.btn_apply_date_range, 14);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentReportDateSheetDialogBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }
    private FragmentReportDateSheetDialogBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[3]
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.Button) bindings[14]
            , (android.widget.ImageView) bindings[13]
            , (android.widget.ImageView) bindings[2]
            , (android.widget.EditText) bindings[4]
            , (android.widget.EditText) bindings[9]
            , (android.widget.EditText) bindings[6]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.ImageView) bindings[7]
            , (android.widget.LinearLayout) bindings[1]
            , (android.widget.EditText) bindings[12]
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