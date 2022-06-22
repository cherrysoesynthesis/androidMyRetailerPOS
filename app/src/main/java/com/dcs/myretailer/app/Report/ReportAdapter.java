package com.dcs.myretailer.app.Report;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ReportAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    Context context;
    public ReportAdapter(FragmentManager fm, int NumOfTabs, ReportActivity applicationContext) {
        super(fm);
        mNumOfTabs = NumOfTabs;
        context = applicationContext;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ReportOverallFragment tab1 = new ReportOverallFragment();
                return tab1;
            case 1:
//                ReportActivity.previous_report_shift_name = "Now";
                ReportProductFragment tab2 = new ReportProductFragment();
                return tab2;
            case 2:
//                ReportActivity.previous_report_shift_name = "Now";
                ReportCategoryFragment tab3 = new ReportCategoryFragment();
                return tab3;
            case 3:
//                ReportActivity.previous_report_shift_name = "Now";
                ReportXFragment tabX = new ReportXFragment();
                return tabX;
                //ReportActivity.LaySearchPrint.setVisibility(View.VISIBLE);
                //ReportYFragment tabY = new ReportYFragment();

//                SyncActivity.volleyCheckPermission(ReportActivity.appContext, Constraints.X_Reports,Constraints.Accessable,syncRetailID,
//                        syncCompanyCode,syncUrl,download_retail_ID,download_company_code,download_url,download_userlogin,download_userpassword);

//                String accessable = SyncActivity.volleyCheckPermission(ReportActivity.appContext, Constraints.X_Reports,Constraints.Accessable);
//                if (RemarkMainActivity.userid.equals("1111")){
//                    ReportXFragment tabY = new ReportXFragment();
//                    return tabY;
//                }else {
//                    String accessable = Query.SearchUserAccess(Constraints.F0030, RemarkMainActivity.userid, RemarkMainActivity.userpassword);
//                    if (accessable.equals("1")) {
//                        ReportXFragment tabX = new ReportXFragment();
//                        return tabX;
//                    } else {
////                    Query.DonothaveUserAccess(ReportActivity.appContext);
//                        try {
//                            Query.DonothaveUserAccess(context);
//                        } catch (Exception e) {
//                            Log.i("errr__", "erer__" + e.getMessage());
//                            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
//                                    System.currentTimeMillis(), DBFunc.PurifyString("ReportAdapter -> DonothaveUserAccess: " + e.getMessage()));
//
//                        }
//                        // return null;
//                    }
//                }
            case 4:
                Log.i("position___","position_4____"+position);

                ReportXFragment tabY = new ReportXFragment();
                return tabY;
//                ReportActivity.LaySearchPrint.setVisibility(View.VISIBLE);
//                String syncRetailID = "";
//                String syncCompanyCode = "";
//                String syncUrl = "";
//                String download_retail_ID = "";
//                String download_company_code = "";
//                String download_url = "";
//                String download_userlogin = "";
//                String download_userpassword = "";
//                Cursor Cursor_Possys = Query.GetURLAndCodeFromPossys();
//                if (Cursor_Possys != null) {
//                    while (Cursor_Possys.moveToNext()) {
//                        syncRetailID = Cursor_Possys.getString(0);
//                        syncCompanyCode = Cursor_Possys.getString(1);
//                        syncUrl = Cursor_Possys.getString(2);
//                        download_retail_ID = Cursor_Possys.getString(3);
//                        download_company_code = Cursor_Possys.getString(4);
//                        download_url = Cursor_Possys.getString(5);
//                        download_userlogin = Cursor_Possys.getString(13);
//                        download_userpassword = Cursor_Possys.getString(14);
//                    }
//                    Cursor_Possys.close();
//                }
//                SyncActivity.volleyCheckPermission(ReportActivity.appContext, Constraints.Z_Reports,Constraints.Accessable,syncRetailID,
//                        syncCompanyCode,syncUrl,download_retail_ID,download_company_code,download_url,download_userlogin,download_userpassword);

//                //accessable = SyncActivity.volleyCheckPermission(ReportActivity.appContext, Constraints.Z_Reports,Constraints.Accessable);
//                if (RemarkMainActivity.userid.equals("1111")){
//                    ReportXFragment tabY = new ReportXFragment();
//                    return tabY;
//                }else {
//                    String accessablez = Query.SearchUserAccess(Constraints.M0022, RemarkMainActivity.userid, RemarkMainActivity.userpassword);
//                    if (accessablez.equals("1")) {
//                        ReportXFragment tabX = new ReportXFragment();
//                        return tabX;
//                    } else {
//                        //Query.DonothaveUserAccess(ReportActivity.appContext);
//                        Query.DonothaveUserAccess(context);
//                        //return null;
//                    }
//                }
//            case 5:
//                ReportOtherFragment tab4 = new ReportOtherFragment();
//                return tab4;
            default:
//                ReportActivity.LaySearchPrint.setVisibility(View.GONE);
//                ReportActivity.LaySearchPrint.setVisibility(View.VISIBLE);
//                ReportOtherFragment tab4 = new ReportOtherFragment();
//                return tab4;
                return null;
        }
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}