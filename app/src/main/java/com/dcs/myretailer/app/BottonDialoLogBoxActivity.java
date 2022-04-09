//package com.dcs.myretailer.app;
//
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//import com.dcs.myretailer.app.Cashier.MainActivity;
//import com.google.android.material.bottomsheet.BottomSheetBehavior;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
//public class BottonDialoLogBoxActivity extends AppCompatActivity {
//
//    private static final String TAG = MainActivity.class.getSimpleName();
//
//    @BindView(R.id.btn_bottom_sheet)
//    Button btnBottomSheet;
//
//    @BindView(R.id.btn_bottom_sheet_dialog)
//    LinearLayout layoutBottomSheet;
//
//    BottomSheetBehavior sheetBehavior;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        //onCreate(savedInstanceState);
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
//
//        /**
//         * bottom sheet state change listener
//         * we are changing button text when sheet changed state
//         * */
//        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                switch (newState) {
//                    case BottomSheetBehavior.STATE_HIDDEN:
//                        break;
//                    case BottomSheetBehavior.STATE_EXPANDED: {
//                        btnBottomSheet.setText("Close Sheet");
//                    }
//                    break;
//                    case BottomSheetBehavior.STATE_COLLAPSED: {
//                        btnBottomSheet.setText("Expand Sheet");
//                    }
//                    break;
//                    case BottomSheetBehavior.STATE_DRAGGING:
//                        break;
//                    case BottomSheetBehavior.STATE_SETTLING:
//                        break;
//                }
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//
//            }
//        });
//    }
//
//    /**
//     * manually opening / closing bottom sheet on button click
//     */
//    @OnClick(R.id.btn_bottom_sheet)
//    public void toggleBottomSheet() {
//        if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
//            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//            btnBottomSheet.setText("Close sheet");
//        } else {
//            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//            btnBottomSheet.setText("Expand sheet");
//        }
//    }
//}