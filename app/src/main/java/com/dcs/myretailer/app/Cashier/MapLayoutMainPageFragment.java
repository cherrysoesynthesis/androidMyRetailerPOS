package com.dcs.myretailer.app.Cashier;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ProductData;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.MapButton;

import java.util.ArrayList;
import java.util.List;

public class MapLayoutMainPageFragment extends Fragment implements View.OnClickListener {
    //    List<Book> lstBook ;
    public static List<ProductData> lstProductData = new ArrayList<ProductData>();
    ArrayList<String> maphorizontalname = new ArrayList<String>();
    ArrayList<String> billnamehorizontal = new ArrayList<String>();
    ArrayList<String> mapname = new ArrayList<String>();
    ArrayList<String> billname = new ArrayList<String>();
    private int focusedItem = 0;
    TextView product_category_name;
    ImageView back;
    public static RecyclerView myrv;
    public static RecyclerView myrv2;
    private String BalNo = "0";

    public MapLayoutMainPageFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;

        view = inflater.inflate( R.layout.recyclerview_1, container, false);
        myrv = (RecyclerView) view.findViewById(R.id.recyclerview_id);
        myrv2 = (RecyclerView) view.findViewById(R.id.recyclerview_id_2);
        //ShowMapLayout(MainActivity.defaultMapID);
        //final MapViewer mapviewer = new MapViewer(getContext());
        //getMapLayoutHorizontalAll(MainActivity.defaultMapID);


        BalNo = Query.GetBalNo(MainActivity.strbillNo);

//        if (!(BalNo.equals("0") || BalNo.isEmpty())) {
            Query.ShowMapLayout(getContext(), MainActivity.defaultMapID, MainActivity.strbillNo);

//        }else {
//            Log.i("BalNo__",BalNo);
//        }
//        if (maphorizontalname.size() > 0 ) {
//            RecyclerViewBillHorizonalAdapter myAdapter = new RecyclerViewBillHorizonalAdapter(getContext(), maphorizontalname, billnamehorizontal);
//            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
//            myrv.setLayoutManager(gridLayoutManager);
//            myrv.setAdapter(myAdapter);
//        }
//        final MapViewer mapviewer = new MapViewer(getContext());
//        String str_chk_map_layout = Query.GetOptions(19);
//        if (str_chk_map_layout.equals("1")) {
//            //RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getContext(),lstBook,"product");
//            getMapLayoutHorizontalAll(MainActivity.defaultMapID);
//            RecyclerViewBillHorizonalAdapter myAdapter = new RecyclerViewBillHorizonalAdapter(getContext(), maphorizontalname, billnamehorizontal);
//            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
//            myrv.setLayoutManager(gridLayoutManager);
//            myrv.setAdapter(myAdapter);
//
//            getMapLayoutAll(MainActivity.defaultMapID);
//            RecyclerViewBillAdapter myAdapter2 = new RecyclerViewBillAdapter(getContext(), mapname, billname);
//            GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getActivity(), 1);
//            myrv2.setLayoutManager(gridLayoutManager2);
//            myrv2.setAdapter(myAdapter2);
//        }

        //        back = (ImageView) view.findViewById(R.id.back);
//        product_category_name = (TextView) view.findViewById(R.id.product_category_name);
//        product_category_name.setText(CategoryMainPageFragment.category_name);
//        back.setOnClickListener(this);

//         getProductAll();
////
////        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getContext(),lstBook,"fragment4");
////        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
////        myrv.setLayoutManager(gridLayoutManager);
////        myrv.setAdapter(myAdapter);
////
////        Bundle data = getArguments();
        return view;
    }

    private void getMapLayoutHorizontalAll(Integer mapID) {
        //Cursor c = DBFunc.Query("SELECT name,image,bgcolor FROM MapLayout WHERE id = " + mapID, true);
        //Cursor c = DBFunc.Query("SELECT name,image,bgcolor FROM MapLayout ", true);
        //lstBook.add(new Book("ESSENTIALS 1 - STRIPES DRESS","$210.45","Categorie Book","Description book", R.mipmap.image));
        //Cursor c = DBFunc.Query("SELECT Name,Price,Code,Image,ID FROM PLU Where ProductCategory IS NULL", true);
        String sql = "SELECT func,name,x,y,width,height,style,bgcolor,fgcolor,fontsize,img,id FROM MapButtons " +
                "WHERE map_id = " + mapID + "  " +
//                "AND id % 2 != 0 " +
                "ORDER BY id ASC";
        Cursor c = DBFunc.Query(sql, true);
        ///while (c.moveToNext()) {
        if (c != null) {
            billnamehorizontal.clear();
            maphorizontalname.clear();
            while (c.moveToNext()) {
                //if (!c.isNull(0)) {
                MapButton mapbtn = new MapButton();
                String[] func = c.getString(0).split(":", 3);
                try {
                    if (func.length >= 2) {
                        int Type = Integer.parseInt(func[0].trim());
                        int ObjID = Integer.parseInt(func[1].trim());
                        mapbtn.setFuncType(Type);
                        mapbtn.setObjID(ObjID);
                        if (func.length == 3) {
                            if (Type == 0 && ObjID == 19) {
                                if (!func[2].trim().isEmpty()) {
                                    mapbtn.setObjExt(func[2].trim());
                                }
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                }
//                Cursor c1 = DBFunc.Query("SELECT OpenDateTime FROM Bill " +
//                        "WHERE Cancel = 0 AND CloseDateTime IS NULL AND BalNo = '" + DBFunc.PurifyString(btn.getObjExt()) + "' ORDER BY BillNo ASC LIMIT 1", false);

                Cursor c1 = DBFunc.Query("SELECT OpenDateTime FROM Bill " +
                        "WHERE Cancel = 0 AND CloseDateTime IS NULL  ORDER BY BillNo ASC LIMIT 1", false);

                if (c1!=null) {
                    if (c1.moveToNext()) {
                        // Calendar openTime = Calendar.getInstance().;
                        // openTime.setTimeInMillis(c.getLong(1));
                        long curTime = System.currentTimeMillis();
                        long openTime = c1.getLong(0);
                        long interval = curTime - openTime;
                        maphorizontalname.add(c.getString(1));//map name, for dialog title, currently not in use!
                        billnamehorizontal.add(String.format("%02d:%02d:%02d", (interval / 1000 / 60 / 60), (interval / 1000 / 60) % 60, (interval / 1000) % 60));
                    }
                    c1.close();
                }

                //}
            }
            c.close();
        }
    }
    private void getMapLayoutAll(Integer mapID) {
        //Cursor c = DBFunc.Query("SELECT name,image,bgcolor FROM MapLayout WHERE id = " + mapID, true);
        //Cursor c = DBFunc.Query("SELECT name,image,bgcolor FROM MapLayout ", true);
        //lstBook.add(new Book("ESSENTIALS 1 - STRIPES DRESS","$210.45","Categorie Book","Description book", R.mipmap.image));
        //Cursor c = DBFunc.Query("SELECT Name,Price,Code,Image,ID FROM PLU Where ProductCategory IS NULL", true);
        String sql = "SELECT func,name,x,y,width,height,style,bgcolor,fgcolor,fontsize,img FROM MapButtons " +
                "WHERE map_id = " + mapID +  " " +
//                "AND id % 2 = 0 " +
                "ORDER BY id ASC";
        Cursor c = DBFunc.Query(sql, true);
        ///while (c.moveToNext()) {
        if (c != null) {
            billname.clear();
            mapname.clear();
            while (c.moveToNext()) {
                //if (!c.isNull(0)) {
                    mapname.add(c.getString(1));//map name, for dialog title, currently not in use!
                //}
                MapButton mapbtn = new MapButton();
                String[] func = c.getString(0).split(":", 3);
                try {
                    if (func.length >= 2) {
                        int Type = Integer.parseInt(func[0].trim());
                        int ObjID = Integer.parseInt(func[1].trim());
                        mapbtn.setFuncType(Type);
                        mapbtn.setObjID(ObjID);
                        if (func.length == 3) {
                            if (Type == 0 && ObjID == 19) {
                                if (!func[2].trim().isEmpty()) {
                                    mapbtn.setObjExt(func[2].trim());
                                }
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                }
//                Cursor c1 = DBFunc.Query("SELECT OpenDateTime FROM Bill " +
//                        "WHERE Cancel = 0 AND CloseDateTime IS NULL AND BalNo = '" + DBFunc.PurifyString(btn.getObjExt()) + "' " +
//                        "ORDER BY BillNo ASC LIMIT 1", false);
                Cursor c1 = DBFunc.Query("SELECT OpenDateTime FROM Bill " +
                        "WHERE Cancel = 0 AND CloseDateTime IS NULL  " +
                        "ORDER BY BillNo ASC LIMIT 1", false);

                if (c1!=null) {
                    if (c1.moveToNext()) {
                        // Calendar openTime = Calendar.getInstance().;
                        // openTime.setTimeInMillis(c.getLong(1));
                        long curTime = System.currentTimeMillis();
                        long openTime = c1.getLong(0);
                        long interval = curTime - openTime;
                        billname.add(String.format("%02d:%02d:%02d", (interval / 1000 / 60 / 60), (interval / 1000 / 60) % 60, (interval / 1000) % 60));
                    }
                    c1.close();
                }
            }
            c.close();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.back:
//                Intent intent = new Intent(getActivity(),MainActivity.class);
//                intent.putExtra("name","Product____"+String.valueOf("0"));
//                getActivity().startActivity(intent);
//                break;
        }
    }
}