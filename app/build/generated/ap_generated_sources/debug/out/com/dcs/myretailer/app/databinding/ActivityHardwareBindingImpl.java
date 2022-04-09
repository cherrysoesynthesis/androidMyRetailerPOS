package com.dcs.myretailer.app.databinding;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityHardwareBindingImpl extends ActivityHardwareBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.LayName, 2);
        sViewsWithIds.put(R.id.et_name, 3);
        sViewsWithIds.put(R.id.LayPrintReceipt, 4);
        sViewsWithIds.put(R.id.edit_receipt_printer, 5);
        sViewsWithIds.put(R.id.btn_receipt_printer, 6);
        sViewsWithIds.put(R.id.lbl_2, 7);
        sViewsWithIds.put(R.id.spn_printer_type, 8);
        sViewsWithIds.put(R.id.LayLengthPerLine, 9);
        sViewsWithIds.put(R.id.et_length, 10);
        sViewsWithIds.put(R.id.LayLineFeed, 11);
        sViewsWithIds.put(R.id.et_print_feedline, 12);
        sViewsWithIds.put(R.id.LayCharacterEncoding, 13);
        sViewsWithIds.put(R.id.edit_character, 14);
        sViewsWithIds.put(R.id.btn_character, 15);
        sViewsWithIds.put(R.id.lbl_10, 16);
        sViewsWithIds.put(R.id.spn_print_charset, 17);
        sViewsWithIds.put(R.id.LaySingleSizeFont, 18);
        sViewsWithIds.put(R.id.chk_print_singlesize, 19);
        sViewsWithIds.put(R.id.chk_print_default, 20);
        sViewsWithIds.put(R.id.lay_print_net, 21);
        sViewsWithIds.put(R.id.et_net_ip, 22);
        sViewsWithIds.put(R.id.et_net_port, 23);
        sViewsWithIds.put(R.id.laymac, 24);
        sViewsWithIds.put(R.id.tv_bt_mac, 25);
        sViewsWithIds.put(R.id.lay_print_bt, 26);
        sViewsWithIds.put(R.id.edit_bt_select, 27);
        sViewsWithIds.put(R.id.btn_bt_select, 28);
        sViewsWithIds.put(R.id.lay_print_usb, 29);
        sViewsWithIds.put(R.id.lbl_8, 30);
        sViewsWithIds.put(R.id.spn_usb_list, 31);
        sViewsWithIds.put(R.id.btn_usb_refresh, 32);
        sViewsWithIds.put(R.id.lbl_9, 33);
        sViewsWithIds.put(R.id.btn_usb_check, 34);
        sViewsWithIds.put(R.id.btn_print_save, 35);
        sViewsWithIds.put(R.id.btn_print_del, 36);
        sViewsWithIds.put(R.id.btn_print_close, 37);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityHardwareBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 38, sIncludes, sViewsWithIds));
    }
    private ActivityHardwareBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[13]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.LinearLayout) bindings[2]
            , (android.widget.LinearLayout) bindings[4]
            , (android.widget.LinearLayout) bindings[18]
            , (android.widget.ScrollView) bindings[0]
            , (android.widget.ImageView) bindings[28]
            , (android.widget.ImageView) bindings[15]
            , (android.widget.Button) bindings[37]
            , (android.widget.Button) bindings[36]
            , (android.widget.Button) bindings[35]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.Button) bindings[34]
            , (android.widget.Button) bindings[32]
            , (android.widget.CheckBox) bindings[20]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[19]
            , (android.widget.EditText) bindings[27]
            , (android.widget.EditText) bindings[14]
            , (android.widget.EditText) bindings[5]
            , (android.widget.EditText) bindings[10]
            , (android.widget.EditText) bindings[3]
            , (android.widget.EditText) bindings[22]
            , (android.widget.EditText) bindings[23]
            , (android.widget.EditText) bindings[12]
            , (android.widget.LinearLayout) bindings[26]
            , (android.widget.LinearLayout) bindings[21]
            , (android.widget.LinearLayout) bindings[29]
            , (android.widget.LinearLayout) bindings[24]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[30]
            , (android.widget.TextView) bindings[33]
            , (android.widget.Spinner) bindings[17]
            , (android.widget.Spinner) bindings[8]
            , (android.widget.Spinner) bindings[31]
            , (android.widget.EditText) bindings[25]
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