package com.dcs.myretailer.app.databinding;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityCheckOutBindingImpl extends ActivityCheckOutBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(6);
        sIncludes.setIncludes(0, 
            new String[] {"checkout_payment_list"},
            new int[] {4},
            new int[] {com.dcs.myretailer.app.R.layout.checkout_payment_list});
        sIncludes.setIncludes(1, 
            new String[] {"checkout_bill_information_show", "checkout_bill_information_ordersummary"},
            new int[] {2, 3},
            new int[] {com.dcs.myretailer.app.R.layout.checkout_bill_information_show,
                com.dcs.myretailer.app.R.layout.checkout_bill_information_ordersummary});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.checkOutScrollView, 5);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityCheckOutBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private ActivityCheckOutBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3
            , (android.widget.ScrollView) bindings[5]
            , (com.dcs.myretailer.app.databinding.CheckoutBillInformationShowBinding) bindings[2]
            , (com.dcs.myretailer.app.databinding.CheckoutBillInformationOrdersummaryBinding) bindings[3]
            , (com.dcs.myretailer.app.databinding.CheckoutPaymentListBinding) bindings[4]
            , (android.widget.LinearLayout) bindings[1]
            );
        setContainedBinding(this.checkoutInfo);
        setContainedBinding(this.checkoutOrderSummary);
        setContainedBinding(this.checkoutPaymentList);
        this.layCheckoutOverAll.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
        }
        checkoutInfo.invalidateAll();
        checkoutOrderSummary.invalidateAll();
        checkoutPaymentList.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (checkoutInfo.hasPendingBindings()) {
            return true;
        }
        if (checkoutOrderSummary.hasPendingBindings()) {
            return true;
        }
        if (checkoutPaymentList.hasPendingBindings()) {
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
        checkoutInfo.setLifecycleOwner(lifecycleOwner);
        checkoutOrderSummary.setLifecycleOwner(lifecycleOwner);
        checkoutPaymentList.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeCheckoutInfo((com.dcs.myretailer.app.databinding.CheckoutBillInformationShowBinding) object, fieldId);
            case 1 :
                return onChangeCheckoutPaymentList((com.dcs.myretailer.app.databinding.CheckoutPaymentListBinding) object, fieldId);
            case 2 :
                return onChangeCheckoutOrderSummary((com.dcs.myretailer.app.databinding.CheckoutBillInformationOrdersummaryBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeCheckoutInfo(com.dcs.myretailer.app.databinding.CheckoutBillInformationShowBinding CheckoutInfo, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeCheckoutPaymentList(com.dcs.myretailer.app.databinding.CheckoutPaymentListBinding CheckoutPaymentList, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeCheckoutOrderSummary(com.dcs.myretailer.app.databinding.CheckoutBillInformationOrdersummaryBinding CheckoutOrderSummary, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
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
        executeBindingsOn(checkoutInfo);
        executeBindingsOn(checkoutOrderSummary);
        executeBindingsOn(checkoutPaymentList);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): checkoutInfo
        flag 1 (0x2L): checkoutPaymentList
        flag 2 (0x3L): checkoutOrderSummary
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}