package com.dcs.myretailer.app.databinding;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityAddNewProductBindingImpl extends ActivityAddNewProductBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(47);
        sIncludes.setIncludes(2, 
            new String[] {"activity_stock_in", "activity_stock_adjustment"},
            new int[] {3, 4},
            new int[] {com.dcs.myretailer.app.R.layout.activity_stock_in,
                com.dcs.myretailer.app.R.layout.activity_stock_adjustment});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.linearlay_image_hide, 5);
        sViewsWithIds.put(R.id.img_choose_folder, 6);
        sViewsWithIds.put(R.id.choose_photo_id, 7);
        sViewsWithIds.put(R.id.take_camera_id, 8);
        sViewsWithIds.put(R.id.lay_product_name, 9);
        sViewsWithIds.put(R.id.edit_text_product_name1, 10);
        sViewsWithIds.put(R.id.edit_text_product_name2, 11);
        sViewsWithIds.put(R.id.layPrice, 12);
        sViewsWithIds.put(R.id.edit_text_price, 13);
        sViewsWithIds.put(R.id.layProductCode, 14);
        sViewsWithIds.put(R.id.edit_text_product_code, 15);
        sViewsWithIds.put(R.id.scan_prodcut_code, 16);
        sViewsWithIds.put(R.id.edit_text_product_variant_name, 17);
        sViewsWithIds.put(R.id.edit_text_product_variant_price, 18);
        sViewsWithIds.put(R.id.btn_add_variant, 19);
        sViewsWithIds.put(R.id.edit_text_product_modifier_name, 20);
        sViewsWithIds.put(R.id.edit_text_product_modifier_price, 21);
        sViewsWithIds.put(R.id.btn_add_modifier, 22);
        sViewsWithIds.put(R.id.layProductCategory, 23);
        sViewsWithIds.put(R.id.edit_product_category, 24);
        sViewsWithIds.put(R.id.img_product_category, 25);
        sViewsWithIds.put(R.id.layTax, 26);
        sViewsWithIds.put(R.id.edit_product_tax, 27);
        sViewsWithIds.put(R.id.img_product_tax, 28);
        sViewsWithIds.put(R.id.layOnHandQty, 29);
        sViewsWithIds.put(R.id.edit_text_product_onhandqty, 30);
        sViewsWithIds.put(R.id.edit_codiment, 31);
        sViewsWithIds.put(R.id.img_codiment, 32);
        sViewsWithIds.put(R.id.layopenprice, 33);
        sViewsWithIds.put(R.id.layAllowOpenPrice, 34);
        sViewsWithIds.put(R.id.chk_allow_open_price, 35);
        sViewsWithIds.put(R.id.layremarks, 36);
        sViewsWithIds.put(R.id.layAllowRemarks, 37);
        sViewsWithIds.put(R.id.chk_allow_remarks, 38);
        sViewsWithIds.put(R.id.chk_allow_name_quick_edit, 39);
        sViewsWithIds.put(R.id.chk_allow_price_quick_edit, 40);
        sViewsWithIds.put(R.id.layKitchenPrinter, 41);
        sViewsWithIds.put(R.id.edit_receipt_printer, 42);
        sViewsWithIds.put(R.id.img_receipt_printer, 43);
        sViewsWithIds.put(R.id.Laybtn, 44);
        sViewsWithIds.put(R.id.btn_delete_product, 45);
        sViewsWithIds.put(R.id.btn_add_product, 46);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityAddNewProductBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 47, sIncludes, sViewsWithIds));
    }
    private ActivityAddNewProductBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (android.widget.LinearLayout) bindings[44]
            , (android.widget.ScrollView) bindings[0]
            , (android.widget.Button) bindings[22]
            , (android.widget.Button) bindings[46]
            , (android.widget.Button) bindings[19]
            , (android.widget.Button) bindings[45]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[39]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[35]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[40]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[38]
            , (android.widget.LinearLayout) bindings[7]
            , (android.widget.EditText) bindings[31]
            , (android.widget.EditText) bindings[24]
            , (android.widget.EditText) bindings[27]
            , (android.widget.EditText) bindings[42]
            , (android.widget.EditText) bindings[13]
            , (android.widget.EditText) bindings[15]
            , (android.widget.EditText) bindings[20]
            , (android.widget.EditText) bindings[21]
            , (android.widget.EditText) bindings[10]
            , (android.widget.EditText) bindings[11]
            , (android.widget.EditText) bindings[30]
            , (android.widget.EditText) bindings[17]
            , (android.widget.EditText) bindings[18]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.ImageView) bindings[32]
            , (android.widget.ImageView) bindings[25]
            , (android.widget.ImageView) bindings[28]
            , (android.widget.ImageView) bindings[43]
            , (android.widget.LinearLayout) bindings[34]
            , (android.widget.LinearLayout) bindings[37]
            , (android.widget.LinearLayout) bindings[41]
            , (android.widget.LinearLayout) bindings[29]
            , (android.widget.LinearLayout) bindings[2]
            , (android.widget.LinearLayout) bindings[12]
            , (android.widget.LinearLayout) bindings[23]
            , (android.widget.LinearLayout) bindings[14]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.LinearLayout) bindings[26]
            , (android.widget.LinearLayout) bindings[33]
            , (android.widget.LinearLayout) bindings[36]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.ImageView) bindings[16]
            , (com.dcs.myretailer.app.databinding.ActivityStockAdjustmentBinding) bindings[4]
            , (com.dcs.myretailer.app.databinding.ActivityStockInBinding) bindings[3]
            , (android.widget.ImageView) bindings[8]
            );
        this.ScrollView01.setTag(null);
        this.layOverAll.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        setContainedBinding(this.stockAdj);
        setContainedBinding(this.stockIn);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        stockIn.invalidateAll();
        stockAdj.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (stockIn.hasPendingBindings()) {
            return true;
        }
        if (stockAdj.hasPendingBindings()) {
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
        stockIn.setLifecycleOwner(lifecycleOwner);
        stockAdj.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeStockIn((com.dcs.myretailer.app.databinding.ActivityStockInBinding) object, fieldId);
            case 1 :
                return onChangeStockAdj((com.dcs.myretailer.app.databinding.ActivityStockAdjustmentBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeStockIn(com.dcs.myretailer.app.databinding.ActivityStockInBinding StockIn, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeStockAdj(com.dcs.myretailer.app.databinding.ActivityStockAdjustmentBinding StockAdj, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
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
        executeBindingsOn(stockIn);
        executeBindingsOn(stockAdj);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): stockIn
        flag 1 (0x2L): stockAdj
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}