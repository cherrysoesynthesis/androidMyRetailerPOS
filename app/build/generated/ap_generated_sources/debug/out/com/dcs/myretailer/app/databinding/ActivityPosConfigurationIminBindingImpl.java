package com.dcs.myretailer.app.databinding;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityPosConfigurationIminBindingImpl extends ActivityPosConfigurationIminBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.Lay1, 1);
        sViewsWithIds.put(R.id.et_name, 2);
        sViewsWithIds.put(R.id.lbl_2, 3);
        sViewsWithIds.put(R.id.spn_printer, 4);
        sViewsWithIds.put(R.id.Lay2, 5);
        sViewsWithIds.put(R.id.edit_receipt_printer, 6);
        sViewsWithIds.put(R.id.btn_receipt_printer, 7);
        sViewsWithIds.put(R.id.Lay3, 8);
        sViewsWithIds.put(R.id.et_receipt_no_reference, 9);
        sViewsWithIds.put(R.id.lay4, 10);
        sViewsWithIds.put(R.id.et_printcount, 11);
        sViewsWithIds.put(R.id.linearlay_sync, 12);
        sViewsWithIds.put(R.id.edit_company_code, 13);
        sViewsWithIds.put(R.id.edit_company_url, 14);
        sViewsWithIds.put(R.id.edit_retail_id, 15);
        sViewsWithIds.put(R.id.edit_terminal_id, 16);
        sViewsWithIds.put(R.id.edit_server_image_upload_url, 17);
        sViewsWithIds.put(R.id.edit_storeno_id, 18);
        sViewsWithIds.put(R.id.linearlay_download, 19);
        sViewsWithIds.put(R.id.download_edit_company_code, 20);
        sViewsWithIds.put(R.id.download_edit_company_url, 21);
        sViewsWithIds.put(R.id.download_edit_retail_id, 22);
        sViewsWithIds.put(R.id.download_user_login, 23);
        sViewsWithIds.put(R.id.download_user_password, 24);
        sViewsWithIds.put(R.id.linearlay_jeripay_eunoia, 25);
        sViewsWithIds.put(R.id.download_edit_eunoia_url, 26);
        sViewsWithIds.put(R.id.download_edit_eunoia_storeId, 27);
        sViewsWithIds.put(R.id.download_edit_eunoia_partnerId, 28);
        sViewsWithIds.put(R.id.download_edit_eunoia_partnerEmail, 29);
        sViewsWithIds.put(R.id.Lay5, 30);
        sViewsWithIds.put(R.id.edit_round_method, 31);
        sViewsWithIds.put(R.id.btn_round_method, 32);
        sViewsWithIds.put(R.id.txt_balance_type, 33);
        sViewsWithIds.put(R.id.btn_balance_type, 34);
        sViewsWithIds.put(R.id.edit_balance_title, 35);
        sViewsWithIds.put(R.id.lbl_4, 36);
        sViewsWithIds.put(R.id.spn_col, 37);
        sViewsWithIds.put(R.id.TextView02, 38);
        sViewsWithIds.put(R.id.spn_row, 39);
        sViewsWithIds.put(R.id.Lay6, 40);
        sViewsWithIds.put(R.id.lbl_5, 41);
        sViewsWithIds.put(R.id.spn_baltype, 42);
        sViewsWithIds.put(R.id.Lay7, 43);
        sViewsWithIds.put(R.id.lbl_6, 44);
        sViewsWithIds.put(R.id.et_bal_title, 45);
        sViewsWithIds.put(R.id.lbl_7, 46);
        sViewsWithIds.put(R.id.spn_map_lay, 47);
        sViewsWithIds.put(R.id.Lay8, 48);
        sViewsWithIds.put(R.id.edit_bill_list_font_size, 49);
        sViewsWithIds.put(R.id.btn_bill_list_font_size, 50);
        sViewsWithIds.put(R.id.lbl_8, 51);
        sViewsWithIds.put(R.id.spn_fontsize, 52);
        sViewsWithIds.put(R.id.edit_bluetooth, 53);
        sViewsWithIds.put(R.id.btn_bluetooth, 54);
        sViewsWithIds.put(R.id.Lay9, 55);
        sViewsWithIds.put(R.id.lay10, 56);
        sViewsWithIds.put(R.id.chk_pos_selectlast, 57);
        sViewsWithIds.put(R.id.lay11, 58);
        sViewsWithIds.put(R.id.chk_pos_noprintcondiqty, 59);
        sViewsWithIds.put(R.id.lay12, 60);
        sViewsWithIds.put(R.id.chk_pos_paymentonlykp, 61);
        sViewsWithIds.put(R.id.chk_pos_hideNaviBar, 62);
        sViewsWithIds.put(R.id.chk_pos_usermode, 63);
        sViewsWithIds.put(R.id.chk_pos_checkupdate, 64);
        sViewsWithIds.put(R.id.chk_pos_referfunction, 65);
        sViewsWithIds.put(R.id.chk_pos_refercompulsory, 66);
        sViewsWithIds.put(R.id.chk_pos_referinfo1print, 67);
        sViewsWithIds.put(R.id.chk_pos_referinfo2print, 68);
        sViewsWithIds.put(R.id.chk_pos_referinfo3print, 69);
        sViewsWithIds.put(R.id.chk_pos_userovertake, 70);
        sViewsWithIds.put(R.id.chk_pos_noprintunpaid, 71);
        sViewsWithIds.put(R.id.chk_pos_printer_receipt, 72);
        sViewsWithIds.put(R.id.chk_pos_cash_drawer, 73);
        sViewsWithIds.put(R.id.chk_pos_customer_display, 74);
        sViewsWithIds.put(R.id.chk_pos_barcode_scanner, 75);
        sViewsWithIds.put(R.id.chk_pos_integrate_shoptima, 76);
        sViewsWithIds.put(R.id.chk_pos_qr_code_shoptima, 77);
        sViewsWithIds.put(R.id.chk_pos_online_order, 78);
        sViewsWithIds.put(R.id.chk_pos_image_url, 79);
        sViewsWithIds.put(R.id.chk_pos_service_charges, 80);
        sViewsWithIds.put(R.id.chk_pos_retail, 81);
        sViewsWithIds.put(R.id.chk_pos_receipt_print_paper, 82);
        sViewsWithIds.put(R.id.chk_pos_jerifood, 83);
        sViewsWithIds.put(R.id.chk_pos_barcode_on_receipt, 84);
        sViewsWithIds.put(R.id.chk_pos_4DigitICNO, 85);
        sViewsWithIds.put(R.id.chk_pos_zclose, 86);
        sViewsWithIds.put(R.id.chk_pos_kitchen_printer, 87);
        sViewsWithIds.put(R.id.linearlayshoptimaurl, 88);
        sViewsWithIds.put(R.id.edit_shoptima_url, 89);
        sViewsWithIds.put(R.id.linearlayshoptima, 90);
        sViewsWithIds.put(R.id.edit_shoptima_user_id, 91);
        sViewsWithIds.put(R.id.linearlayshoptimapassword, 92);
        sViewsWithIds.put(R.id.edit_shoptima_user_password, 93);
        sViewsWithIds.put(R.id.linearlayshoptimachineid, 94);
        sViewsWithIds.put(R.id.edit_shoptima_machine_id, 95);
        sViewsWithIds.put(R.id.linearlayshoptimallcode, 96);
        sViewsWithIds.put(R.id.edit_shoptima_mall_code, 97);
        sViewsWithIds.put(R.id.linearlayshoptimaurl_qrcode, 98);
        sViewsWithIds.put(R.id.edit_shoptima_url_qrcode, 99);
        sViewsWithIds.put(R.id.linearlayshoptima_qrcode, 100);
        sViewsWithIds.put(R.id.edit_shoptima_user_id_qrcode, 101);
        sViewsWithIds.put(R.id.linearlayshoptimapassword_qrcode, 102);
        sViewsWithIds.put(R.id.edit_shoptima_user_password_qrcode, 103);
        sViewsWithIds.put(R.id.linearlayshoptimallcode_qrcode, 104);
        sViewsWithIds.put(R.id.edit_shoptima_mallcode_qrcode, 105);
        sViewsWithIds.put(R.id.linearlayshoptimachineid_qrcode, 106);
        sViewsWithIds.put(R.id.edit_shoptima_mahcineid_qrcode, 107);
        sViewsWithIds.put(R.id.linearlayshoptimabrand_qrcode, 108);
        sViewsWithIds.put(R.id.edit_shoptima_brand_qrcode, 109);
        sViewsWithIds.put(R.id.linearlayshoptimaoutlet_qrcode, 110);
        sViewsWithIds.put(R.id.edit_shoptima_outlet_qrcode, 111);
        sViewsWithIds.put(R.id.et_info1, 112);
        sViewsWithIds.put(R.id.et_info2, 113);
        sViewsWithIds.put(R.id.et_info3, 114);
        sViewsWithIds.put(R.id.lbl_14, 115);
        sViewsWithIds.put(R.id.spn_lang, 116);
        sViewsWithIds.put(R.id.btn_lang_new, 117);
        sViewsWithIds.put(R.id.btn_lang_del, 118);
        sViewsWithIds.put(R.id.btn_lang_import, 119);
        sViewsWithIds.put(R.id.btn_lang_export, 120);
        sViewsWithIds.put(R.id.btn_save, 121);
        sViewsWithIds.put(R.id.btn_back, 122);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityPosConfigurationIminBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 123, sIncludes, sViewsWithIds));
    }
    private ActivityPosConfigurationIminBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[1]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.LinearLayout) bindings[30]
            , (android.widget.LinearLayout) bindings[40]
            , (android.widget.LinearLayout) bindings[43]
            , (android.widget.LinearLayout) bindings[48]
            , (android.widget.LinearLayout) bindings[55]
            , (android.widget.TextView) bindings[38]
            , (android.widget.Button) bindings[122]
            , (android.widget.ImageView) bindings[34]
            , (android.widget.ImageView) bindings[50]
            , (android.widget.ImageView) bindings[54]
            , (android.widget.Button) bindings[118]
            , (android.widget.Button) bindings[120]
            , (android.widget.Button) bindings[119]
            , (android.widget.Button) bindings[117]
            , (android.widget.ImageView) bindings[7]
            , (android.widget.ImageView) bindings[32]
            , (android.widget.Button) bindings[121]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[85]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[84]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[75]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[73]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[64]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[74]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[62]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[79]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[76]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[83]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[87]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[59]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[71]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[78]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[61]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[72]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[77]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[82]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[66]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[65]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[67]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[68]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[69]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[81]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[57]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[80]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[63]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[70]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[86]
            , (android.widget.EditText) bindings[20]
            , (android.widget.EditText) bindings[21]
            , (android.widget.EditText) bindings[29]
            , (android.widget.EditText) bindings[28]
            , (android.widget.EditText) bindings[27]
            , (android.widget.EditText) bindings[26]
            , (android.widget.EditText) bindings[22]
            , (android.widget.EditText) bindings[23]
            , (android.widget.EditText) bindings[24]
            , (android.widget.EditText) bindings[35]
            , (android.widget.EditText) bindings[49]
            , (android.widget.EditText) bindings[53]
            , (android.widget.EditText) bindings[13]
            , (android.widget.EditText) bindings[14]
            , (android.widget.EditText) bindings[6]
            , (android.widget.EditText) bindings[15]
            , (android.widget.EditText) bindings[31]
            , (android.widget.EditText) bindings[17]
            , (android.widget.EditText) bindings[109]
            , (android.widget.EditText) bindings[95]
            , (android.widget.EditText) bindings[107]
            , (android.widget.EditText) bindings[97]
            , (android.widget.EditText) bindings[105]
            , (android.widget.EditText) bindings[111]
            , (android.widget.EditText) bindings[89]
            , (android.widget.EditText) bindings[99]
            , (android.widget.EditText) bindings[91]
            , (android.widget.EditText) bindings[101]
            , (android.widget.EditText) bindings[93]
            , (android.widget.EditText) bindings[103]
            , (android.widget.EditText) bindings[18]
            , (android.widget.EditText) bindings[16]
            , (android.widget.EditText) bindings[45]
            , (android.widget.EditText) bindings[112]
            , (android.widget.EditText) bindings[113]
            , (android.widget.EditText) bindings[114]
            , (android.widget.EditText) bindings[2]
            , (android.widget.EditText) bindings[11]
            , (android.widget.EditText) bindings[9]
            , (android.widget.LinearLayout) bindings[56]
            , (android.widget.LinearLayout) bindings[58]
            , (android.widget.LinearLayout) bindings[60]
            , (android.widget.LinearLayout) bindings[10]
            , (android.widget.LinearLayout) bindings[0]
            , (android.widget.TextView) bindings[115]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[36]
            , (android.widget.TextView) bindings[41]
            , (android.widget.TextView) bindings[44]
            , (android.widget.TextView) bindings[46]
            , (android.widget.TextView) bindings[51]
            , (android.widget.LinearLayout) bindings[19]
            , (android.widget.LinearLayout) bindings[25]
            , (android.widget.LinearLayout) bindings[12]
            , (android.widget.LinearLayout) bindings[90]
            , (android.widget.LinearLayout) bindings[100]
            , (android.widget.LinearLayout) bindings[108]
            , (android.widget.LinearLayout) bindings[94]
            , (android.widget.LinearLayout) bindings[106]
            , (android.widget.LinearLayout) bindings[96]
            , (android.widget.LinearLayout) bindings[104]
            , (android.widget.LinearLayout) bindings[110]
            , (android.widget.LinearLayout) bindings[92]
            , (android.widget.LinearLayout) bindings[102]
            , (android.widget.LinearLayout) bindings[88]
            , (android.widget.LinearLayout) bindings[98]
            , (android.widget.Spinner) bindings[42]
            , (android.widget.Spinner) bindings[37]
            , (android.widget.Spinner) bindings[52]
            , (android.widget.Spinner) bindings[116]
            , (android.widget.Spinner) bindings[47]
            , (android.widget.Spinner) bindings[4]
            , (android.widget.Spinner) bindings[39]
            , (android.widget.TextView) bindings[33]
            );
        this.layOverAll.setTag(null);
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