package com.dcs.myretailer.app.databinding;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityAddModifierBindingImpl extends ActivityAddModifierBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.layOverAll, 4);
        sViewsWithIds.put(R.id.LayModiName, 5);
        sViewsWithIds.put(R.id.LayModiPrice, 6);
        sViewsWithIds.put(R.id.LayBtn, 7);
        sViewsWithIds.put(R.id.btn_delete_modifier, 8);
        sViewsWithIds.put(R.id.btn_add_modifier, 9);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityAddModifierBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }
    private ActivityAddModifierBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.LinearLayout) bindings[7]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.LinearLayout) bindings[6]
            , (android.widget.ScrollView) bindings[0]
            , (android.widget.Button) bindings[9]
            , (android.widget.Button) bindings[8]
            , (android.widget.EditText) bindings[2]
            , (android.widget.EditText) bindings[3]
            , (android.widget.LinearLayout) bindings[4]
            );
        this.ScrollView01.setTag(null);
        this.editTextPermissionModifierName.setTag(null);
        this.editTextPermissionModifierPrice.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
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
        if (BR.ModifierData == variableId) {
            setModifierData((com.dcs.myretailer.app.ModifierData) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setModifierData(@Nullable com.dcs.myretailer.app.ModifierData ModifierData) {
        updateRegistration(0, ModifierData);
        this.mModifierData = ModifierData;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.ModifierData);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeModifierData((com.dcs.myretailer.app.ModifierData) object, fieldId);
        }
        return false;
    }
    private boolean onChangeModifierData(com.dcs.myretailer.app.ModifierData ModifierData, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.name) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.price) {
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
        com.dcs.myretailer.app.ModifierData modifierData = mModifierData;
        java.lang.String modifierDataName = null;
        java.lang.String modifierDataPrice = null;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xbL) != 0) {

                    if (modifierData != null) {
                        // read ModifierData.name
                        modifierDataName = modifierData.getName();
                    }
            }
            if ((dirtyFlags & 0xdL) != 0) {

                    if (modifierData != null) {
                        // read ModifierData.price
                        modifierDataPrice = modifierData.getPrice();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0xbL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.editTextPermissionModifierName, modifierDataName);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.editTextPermissionModifierPrice, modifierDataPrice);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): ModifierData
        flag 1 (0x2L): ModifierData.name
        flag 2 (0x3L): ModifierData.price
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}