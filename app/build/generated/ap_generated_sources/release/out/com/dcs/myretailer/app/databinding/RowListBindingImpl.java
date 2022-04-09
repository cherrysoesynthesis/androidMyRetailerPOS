package com.dcs.myretailer.app.databinding;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class RowListBindingImpl extends RowListBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.delete_layout, 1);
        sViewsWithIds.put(R.id.img, 2);
        sViewsWithIds.put(R.id.front_layout, 3);
        sViewsWithIds.put(R.id.Lay1, 4);
        sViewsWithIds.put(R.id.cardview_id, 5);
        sViewsWithIds.put(R.id.linear_layout, 6);
        sViewsWithIds.put(R.id.checkb, 7);
        sViewsWithIds.put(R.id.bill_no, 8);
        sViewsWithIds.put(R.id.total_items, 9);
        sViewsWithIds.put(R.id.datetime, 10);
        sViewsWithIds.put(R.id.total_amount, 11);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public RowListBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }
    private RowListBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[4]
            , (android.widget.TextView) bindings[8]
            , (androidx.cardview.widget.CardView) bindings[5]
            , (android.widget.CheckBox) bindings[7]
            , (android.widget.TextView) bindings[10]
            , (android.widget.FrameLayout) bindings[1]
            , (android.widget.FrameLayout) bindings[3]
            , (android.widget.ImageView) bindings[2]
            , (android.widget.LinearLayout) bindings[6]
            , (com.chauthai.swipereveallayout.SwipeRevealLayout) bindings[0]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[9]
            );
        this.swipeLayout.setTag(null);
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