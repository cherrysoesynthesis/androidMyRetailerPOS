package com.dcs.myretailer.app.databinding;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityMapbuttonSetupBindingImpl extends ActivityMapbuttonSetupBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.btn_add, 1);
        sViewsWithIds.put(R.id.btn_edit, 2);
        sViewsWithIds.put(R.id.btn_del, 3);
        sViewsWithIds.put(R.id.btn_add1, 4);
        sViewsWithIds.put(R.id.btn_edit1, 5);
        sViewsWithIds.put(R.id.btn_del1, 6);
        sViewsWithIds.put(R.id.tg_move, 7);
        sViewsWithIds.put(R.id.tg_resize, 8);
        sViewsWithIds.put(R.id.lay_map, 9);
        sViewsWithIds.put(R.id.btn_save1, 10);
        sViewsWithIds.put(R.id.btn_back1, 11);
        sViewsWithIds.put(R.id.btn_save, 12);
        sViewsWithIds.put(R.id.btn_back, 13);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityMapbuttonSetupBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }
    private ActivityMapbuttonSetupBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[1]
            , (android.widget.Button) bindings[4]
            , (android.widget.Button) bindings[13]
            , (android.widget.Button) bindings[11]
            , (android.widget.Button) bindings[3]
            , (android.widget.Button) bindings[6]
            , (android.widget.Button) bindings[2]
            , (android.widget.Button) bindings[5]
            , (android.widget.Button) bindings[12]
            , (android.widget.Button) bindings[10]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.ToggleButton) bindings[7]
            , (android.widget.ToggleButton) bindings[8]
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