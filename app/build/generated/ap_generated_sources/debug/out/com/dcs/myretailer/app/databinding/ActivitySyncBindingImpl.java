package com.dcs.myretailer.app.databinding;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivitySyncBindingImpl extends ActivitySyncBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.layOverAll, 2);
        sViewsWithIds.put(R.id.sync_id, 3);
        sViewsWithIds.put(R.id.download_department, 4);
        sViewsWithIds.put(R.id.download_inventory, 5);
        sViewsWithIds.put(R.id.download_user_access, 6);
        sViewsWithIds.put(R.id.download_promotion, 7);
        sViewsWithIds.put(R.id.resend_sale, 8);
        sViewsWithIds.put(R.id.sync_payment_type, 9);
        sViewsWithIds.put(R.id.sync_discount, 10);
        sViewsWithIds.put(R.id.get_discountlist, 11);
        sViewsWithIds.put(R.id.sync_stockadjustment, 12);
        sViewsWithIds.put(R.id.get_eunoia_menu, 13);
        sViewsWithIds.put(R.id.get_eunoia_products, 14);
        sViewsWithIds.put(R.id.delete_log, 15);
        sViewsWithIds.put(R.id.product_linear_layout_id, 16);
        sViewsWithIds.put(R.id.cardview_id, 17);
        sViewsWithIds.put(R.id.sync_list, 18);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivitySyncBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds));
    }
    private ActivitySyncBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ScrollView) bindings[0]
            , (androidx.cardview.widget.CardView) bindings[17]
            , (android.widget.LinearLayout) bindings[1]
            , (android.widget.Button) bindings[15]
            , (android.widget.Button) bindings[4]
            , (android.widget.Button) bindings[5]
            , (android.widget.Button) bindings[7]
            , (android.widget.Button) bindings[6]
            , (android.widget.Button) bindings[11]
            , (android.widget.Button) bindings[13]
            , (android.widget.Button) bindings[14]
            , (android.widget.LinearLayout) bindings[2]
            , (android.widget.LinearLayout) bindings[16]
            , (android.widget.Button) bindings[8]
            , (android.widget.Button) bindings[10]
            , (android.widget.Button) bindings[3]
            , (android.widget.ListView) bindings[18]
            , (android.widget.Button) bindings[9]
            , (android.widget.Button) bindings[12]
            );
        this.ScrollView01.setTag(null);
        this.container.setTag(null);
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