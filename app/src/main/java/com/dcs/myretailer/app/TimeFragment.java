//package com.dcs.myretailer.app;
//
//
//import android.app.Fragment;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TimeFragment extends Fragment {
//    List<Book> lstBook ;
//
//    public TimeFragment() {
//    }
//
//    @Nullable
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        lstBook = new ArrayList<>();
//        lstBook.add(new Book("The Vegitarian","Categorie Book","Description book",R.drawable.login_btn_drawable));
//        lstBook.add(new Book("The Wild Robot","Categorie Book","Description book",R.drawable.login_btn_drawable));
//        lstBook.add(new Book("Maria Semples","Categorie Book","Description book",R.drawable.login_btn_drawable));
//        lstBook.add(new Book("The Martian","Categorie Book","Description book",R.drawable.login_btn_drawable));
//    }
//
//
//    @Nullable
//    @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view;
//
//        view = inflater.inflate( R.layout.recyclerview, container, false);
//        RecyclerView myrv = (RecyclerView) view.findViewById(R.id.recyclerview_id);
//        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lstBook);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
//        myrv.setLayoutManager(gridLayoutManager);
//        myrv.setAdapter(myAdapter);
//        return view;
//    }
//}