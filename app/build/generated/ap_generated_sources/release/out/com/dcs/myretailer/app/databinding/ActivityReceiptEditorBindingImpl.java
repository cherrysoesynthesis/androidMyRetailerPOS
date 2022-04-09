package com.dcs.myretailer.app.databinding;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityReceiptEditorBindingImpl extends ActivityReceiptEditorBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.layOverAll, 2);
        sViewsWithIds.put(R.id.linearLayout_add_header, 3);
        sViewsWithIds.put(R.id.edit_text_receipt_editor_header, 4);
        sViewsWithIds.put(R.id.lay1, 5);
        sViewsWithIds.put(R.id.btn_add, 6);
        sViewsWithIds.put(R.id.btn_delete, 7);
        sViewsWithIds.put(R.id.linearFooter, 8);
        sViewsWithIds.put(R.id.edit_text_receipt_editor_footer, 9);
        sViewsWithIds.put(R.id.lay2, 10);
        sViewsWithIds.put(R.id.btn_add_footer, 11);
        sViewsWithIds.put(R.id.btn_delete_footer, 12);
        sViewsWithIds.put(R.id.general_setting_layout, 13);
        sViewsWithIds.put(R.id.img_choose_folder, 14);
        sViewsWithIds.put(R.id.choose_photo_id, 15);
        sViewsWithIds.put(R.id.take_camera_id, 16);
        sViewsWithIds.put(R.id.Layprintlogo, 17);
        sViewsWithIds.put(R.id.chk_print_logo, 18);
        sViewsWithIds.put(R.id.layFontAssets, 19);
        sViewsWithIds.put(R.id.edit_select_font_assets, 20);
        sViewsWithIds.put(R.id.img_zclose, 21);
        sViewsWithIds.put(R.id.Laybtn, 22);
        sViewsWithIds.put(R.id.btn_add_receipt_editor, 23);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityReceiptEditorBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 24, sIncludes, sViewsWithIds));
    }
    private ActivityReceiptEditorBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[22]
            , (android.widget.LinearLayout) bindings[17]
            , (android.widget.ScrollView) bindings[0]
            , (android.widget.Button) bindings[6]
            , (android.widget.Button) bindings[11]
            , (android.widget.Button) bindings[23]
            , (android.widget.Button) bindings[7]
            , (android.widget.Button) bindings[12]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[18]
            , (android.widget.LinearLayout) bindings[15]
            , (android.widget.EditText) bindings[20]
            , (android.widget.EditText) bindings[9]
            , (android.widget.EditText) bindings[4]
            , (android.widget.LinearLayout) bindings[13]
            , (android.widget.ImageView) bindings[14]
            , (android.widget.LinearLayout) bindings[21]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.LinearLayout) bindings[10]
            , (android.widget.LinearLayout) bindings[19]
            , (android.widget.LinearLayout) bindings[2]
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.LinearLayout) bindings[3]
            , (android.widget.ImageView) bindings[16]
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