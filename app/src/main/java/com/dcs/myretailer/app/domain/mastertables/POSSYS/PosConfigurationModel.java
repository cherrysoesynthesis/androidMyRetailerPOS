package com.dcs.myretailer.app.domain.mastertables.mastertables.POSSYS;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "possys")
public class PosConfigurationModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    Integer ID;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "receipt_printer")
    Integer receipt_printer;
    @ColumnInfo(name = "retails_id")
    String retails_id;
    @ColumnInfo(name = "terminal_id")
    String terminal_id;
    @ColumnInfo(name = "storeno")
    String storeno;
    @ColumnInfo(name = "server_image_upload_url")
    String server_image_upload_url;
    @ColumnInfo(name = "company_code")
    String company_code;
    @ColumnInfo(name = "company_url")
    String company_url;
    @ColumnInfo(name = "download_retails_id")
    String download_retails_id;
    @ColumnInfo(name = "download_company_code")
    String download_company_code;
    @ColumnInfo(name = "download_company_url")
    String download_company_url;
    @ColumnInfo(name = "shoptima_url")
    String shoptima_url;
    @ColumnInfo(name = "shoptima_user_id")
    String shoptima_user_id;
    @ColumnInfo(name = "shoptima_password")
    String shoptima_password;
    @ColumnInfo(name = "shoptima_machine_id")
    String shoptima_machine_id;
    @ColumnInfo(name = "shoptima_mall_code")
    String shoptima_mall_code;
    @ColumnInfo(name = "qrcode_shoptima_url")
    String qrcode_shoptima_url;
    @ColumnInfo(name = "qrcode_shoptima_user_id")
    String qrcode_shoptima_user_id;
    @ColumnInfo(name = "qrcode_shoptima_password")
    String qrcode_shoptima_password;
    @ColumnInfo(name = "qrcode_shoptima_machine_id")
    String qrcode_shoptima_machine_id;
    @ColumnInfo(name = "qrcode_shoptima_mall_code")
    String qrcode_shoptima_mall_code;
    @ColumnInfo(name = "qrcode_shoptima_brand_code")
    String qrcode_shoptima_brand_code;
    @ColumnInfo(name = "qrcode_shoptima_outlet_code")
    String qrcode_shoptima_outlet_code;
    @ColumnInfo(name = "receipt_count")
    Integer receipt_count;
    @ColumnInfo(name = "hotkey_row")
    Integer hotkey_row;
    @ColumnInfo(name = "hotkey_col")
    Integer hotkey_col;
    @ColumnInfo(name = "bal_mode")
    Integer bal_mode;
    @ColumnInfo(name = "bal_title")
    String bal_title;
    @ColumnInfo(name = "bill_fontsize")
    Integer bill_fontsize;
    @ColumnInfo(name = "option")
    String option;
    @ColumnInfo(name = "maplayout")
    Integer maplayout;
    @ColumnInfo(name = "refer_info1_name")
    String refer_info1_name;
    @ColumnInfo(name = "refer_info2_name")
    String refer_info2_name;
    @ColumnInfo(name = "refer_info3_name")
    String refer_info3_name;
    @ColumnInfo(name = "roundtype")
    Integer roundtype;
    @ColumnInfo(name = "language")
    Integer language;

    public PosConfigurationModel(Integer ID, String name, Integer receipt_printer, String retails_id, String terminal_id, String storeno, String server_image_upload_url, String company_code, String company_url, String download_retails_id, String download_company_code, String download_company_url, String shoptima_url, String shoptima_user_id, String shoptima_password, String shoptima_machine_id, String shoptima_mall_code, String qrcode_shoptima_url, String qrcode_shoptima_user_id, String qrcode_shoptima_password, String qrcode_shoptima_machine_id, String qrcode_shoptima_mall_code, String qrcode_shoptima_brand_code, String qrcode_shoptima_outlet_code, Integer receipt_count, Integer hotkey_row, Integer hotkey_col, Integer bal_mode, String bal_title, Integer bill_fontsize, String option, Integer maplayout, String refer_info1_name, String refer_info2_name, String refer_info3_name, Integer roundtype, Integer language) {
        this.ID = ID;
        this.name = name;
        this.receipt_printer = receipt_printer;
        this.retails_id = retails_id;
        this.terminal_id = terminal_id;
        this.storeno = storeno;
        this.server_image_upload_url = server_image_upload_url;
        this.company_code = company_code;
        this.company_url = company_url;
        this.download_retails_id = download_retails_id;
        this.download_company_code = download_company_code;
        this.download_company_url = download_company_url;
        this.shoptima_url = shoptima_url;
        this.shoptima_user_id = shoptima_user_id;
        this.shoptima_password = shoptima_password;
        this.shoptima_machine_id = shoptima_machine_id;
        this.shoptima_mall_code = shoptima_mall_code;
        this.qrcode_shoptima_url = qrcode_shoptima_url;
        this.qrcode_shoptima_user_id = qrcode_shoptima_user_id;
        this.qrcode_shoptima_password = qrcode_shoptima_password;
        this.qrcode_shoptima_machine_id = qrcode_shoptima_machine_id;
        this.qrcode_shoptima_mall_code = qrcode_shoptima_mall_code;
        this.qrcode_shoptima_brand_code = qrcode_shoptima_brand_code;
        this.qrcode_shoptima_outlet_code = qrcode_shoptima_outlet_code;
        this.receipt_count = receipt_count;
        this.hotkey_row = hotkey_row;
        this.hotkey_col = hotkey_col;
        this.bal_mode = bal_mode;
        this.bal_title = bal_title;
        this.bill_fontsize = bill_fontsize;
        this.option = option;
        this.maplayout = maplayout;
        this.refer_info1_name = refer_info1_name;
        this.refer_info2_name = refer_info2_name;
        this.refer_info3_name = refer_info3_name;
        this.roundtype = roundtype;
        this.language = language;
    }
}
