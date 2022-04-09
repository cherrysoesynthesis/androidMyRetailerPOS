package com.dcs.myretailer.app.databinding;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentEditProductSheetDialogBindingImpl extends FragmentEditProductSheetDialogBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(24);
        sIncludes.setIncludes(1, 
            new String[] {"onhandqty_layout"},
            new int[] {2},
            new int[] {com.dcs.myretailer.app.R.layout.onhandqty_layout});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.linear_close, 3);
        sViewsWithIds.put(R.id.close_cashier_float_amt, 4);
        sViewsWithIds.put(R.id.txt_header_, 5);
        sViewsWithIds.put(R.id.img_item_remarks, 6);
        sViewsWithIds.put(R.id.rel, 7);
        sViewsWithIds.put(R.id.txt_old_value, 8);
        sViewsWithIds.put(R.id.erase, 9);
        sViewsWithIds.put(R.id.LayShowPrice, 10);
        sViewsWithIds.put(R.id.product_edit_price, 11);
        sViewsWithIds.put(R.id.txtOnHandQty, 12);
        sViewsWithIds.put(R.id.LayQty, 13);
        sViewsWithIds.put(R.id.txtQuantity, 14);
        sViewsWithIds.put(R.id.subtraction, 15);
        sViewsWithIds.put(R.id.qun, 16);
        sViewsWithIds.put(R.id.addition, 17);
        sViewsWithIds.put(R.id.OpenDiscount, 18);
        sViewsWithIds.put(R.id.item_discount, 19);
        sViewsWithIds.put(R.id.btn_item_discount, 20);
        sViewsWithIds.put(R.id.chooseButton, 21);
        sViewsWithIds.put(R.id.btn_add_to_bill, 22);
        sViewsWithIds.put(R.id.btn_add_to_void, 23);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentEditProductSheetDialogBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 24, sIncludes, sViewsWithIds));
    }
    private FragmentEditProductSheetDialogBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.LinearLayout) bindings[13]
            , (android.widget.LinearLayout) bindings[10]
            , (android.widget.Button) bindings[18]
            , (android.widget.ImageView) bindings[17]
            , (android.widget.Button) bindings[22]
            , (android.widget.Button) bindings[23]
            , (android.widget.ImageView) bindings[20]
            , (androidx.recyclerview.widget.RecyclerView) bindings[21]
            , (android.widget.ImageView) bindings[4]
            , (android.widget.RelativeLayout) bindings[9]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.LinearLayout) bindings[0]
            , (android.widget.TextView) bindings[19]
            , (android.widget.LinearLayout) bindings[3]
            , (com.dcs.myretailer.app.databinding.OnhandqtyLayoutBinding) bindings[2]
            , (android.widget.EditText) bindings[11]
            , (android.widget.TextView) bindings[16]
            , (android.widget.RelativeLayout) bindings[7]
            , (android.widget.ImageView) bindings[15]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[8]
            , (android.widget.EditText) bindings[12]
            , (android.widget.LinearLayout) bindings[14]
            );
        this.inner.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        setContainedBinding(this.onHandQty);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        onHandQty.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (onHandQty.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        onHandQty.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeOnHandQty((com.dcs.myretailer.app.databinding.OnhandqtyLayoutBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeOnHandQty(com.dcs.myretailer.app.databinding.OnhandqtyLayoutBinding OnHandQty, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
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
        executeBindingsOn(onHandQty);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): onHandQty
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}