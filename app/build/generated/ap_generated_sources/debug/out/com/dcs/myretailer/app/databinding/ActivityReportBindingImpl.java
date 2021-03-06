package com.dcs.myretailer.app.databinding;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityReportBindingImpl extends ActivityReportBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tab_layout_report, 1);
        sViewsWithIds.put(R.id.tab_layout_report_search, 2);
        sViewsWithIds.put(R.id.LaySearchPrint, 3);
        sViewsWithIds.put(R.id.daterange, 4);
        sViewsWithIds.put(R.id.m_icon, 5);
        sViewsWithIds.put(R.id.m_text, 6);
        sViewsWithIds.put(R.id.m_text1, 7);
        sViewsWithIds.put(R.id.report_search, 8);
        sViewsWithIds.put(R.id.layRprint, 9);
        sViewsWithIds.put(R.id.r_print, 10);
        sViewsWithIds.put(R.id.Lay_report_export, 11);
        sViewsWithIds.put(R.id.report_export, 12);
        sViewsWithIds.put(R.id.Lay_report_refresh, 13);
        sViewsWithIds.put(R.id.report_refresh, 14);
        sViewsWithIds.put(R.id.txtbuttn, 15);
        sViewsWithIds.put(R.id.chart1, 16);
        sViewsWithIds.put(R.id.pager_report, 17);
        sViewsWithIds.put(R.id.nav_view, 18);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityReportBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds));
    }
    private ActivityReportBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.LinearLayout) bindings[13]
            , (android.widget.LinearLayout) bindings[3]
            , (com.github.mikephil.charting.charts.BarChart) bindings[16]
            , (android.widget.LinearLayout) bindings[4]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[7]
            , (com.google.android.material.bottomnavigation.BottomNavigationView) bindings[18]
            , (androidx.viewpager.widget.ViewPager) bindings[17]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.ImageView) bindings[12]
            , (android.widget.ImageView) bindings[14]
            , (android.widget.ImageView) bindings[8]
            , (com.google.android.material.tabs.TabLayout) bindings[1]
            , (com.google.android.material.tabs.TabLayout) bindings[2]
            , (android.widget.Button) bindings[15]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
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