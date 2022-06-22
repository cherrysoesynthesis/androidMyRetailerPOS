//package com.dcs.myretailer.app;
//
//import android.content.Context;
//
//class RecyclerPaymentCheckoutAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
//
//    private Context mContext ;
//    private List<Book> mData ;
//    ArrayList<String> arr = new ArrayList<String>();
//    HashMap<String,String> hashValues = new HashMap<String, String>();
//    HashMap<String,String> hashValuesCount = new HashMap<String, String>();
//    ArrayList selectedItems = new ArrayList();
//    private String str ;
//    private ProductMainPageFragment tabFragment1;
//    int[] counter;
//    public static Integer totalItems  = 0;
//    public static Integer count_totalItems  = 0;
//    public static Integer count_item_selected  = 0;
//    public static Double totalAmount = 0.0;
//    Handler mHandler;
//    public RecyclerViewAdapter(Context mContext, List<Book> mData, String str) {
//        this.mContext = mContext;
//        this.mData = mData;
//        this.str = str;
//    }
//
//    public RecyclerViewAdapter(ProductMainPageFragment tabFragment1, List<Book> lstBook) {
//        this.tabFragment1 = tabFragment1;
//        this.mData = lstBook;
//    }
//
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        View view ;
//        LayoutInflater mInflater = LayoutInflater.from(mContext);
//        view = mInflater.inflate(R.layout.cardveiw_item_book,parent,false);
//        counter = new int[getItemCount()];
//        return new MyViewHolder(view);
//    }
//    public static int count = 0;
//    public static int ccount = 0;
//    public static int count_selected = 0;
//    public static Double sltPriceTotal = 0.0;
//    public static String str_sltPriceTotal = "0";
////    public static String selectedID = "";
////    public static String selectedPrice = "";
//    public static ArrayList<Integer> chksldQtyArr = new ArrayList<Integer>();
//    public static ArrayList<String> sldQtyArr = new ArrayList<String>();
////    public static ArrayList<String> countSelectedArr = new ArrayList<String>();
//    public static Integer countSelectedArr = 0;
//    public static ArrayList<String> sldIDArr = new ArrayList<String>();
//    public static ArrayList<String> sldNameArr = new ArrayList<String>();
//    public static ArrayList<String> sldCategoryIDArr = new ArrayList<String>();
//    public static ArrayList<String> sldCategoryNameArr = new ArrayList<String>();
//    public static ArrayList<String> sltPriceTotalArr = new ArrayList<String>();
//    public static ArrayList<String> sltBillDisArr = new ArrayList<String>();
//    public static ArrayList<String> TestQty = new ArrayList<String>();
//    public static String item_name = "0";
//    public static String item_countt = "0";
//    public static String item_diss = "0";
//    public static String item_pricee = "0";
//    public static String old_pricee = "0";
//    int totalqty = 0;
//    int exiting_id = 0;
//    @Override
//    public void onBindViewHolder(final MyViewHolder holder, final int position) {
//
//        holder.tv_book_title.setText(mData.get(position).getTitle());
//        //holder.book_price_id.setText("$"+mData.get(position).getPrice());
//        holder.book_price_id.setText("$"+String.format("%.2f", Double.valueOf(mData.get(position).getPrice())));
//        //holder.img_book_thumbnail.setImageResource(mData.get(position).getThumbnail());
//        holder.img_book_thumbnail.setImageBitmap(mData.get(position).getThumbnail());
//
//        if (str.equals("product")) {
//            if (hashValues.containsKey(mData.get(position).getTitle())){
//            //if (arr.contains(mData.get(position).getTitle())) {
//                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.dark_blue));
//                holder.tv_book_title.setTextColor(ContextCompat.getColor(mContext, R.color.white));
//                holder.book_price_id.setTextColor(ContextCompat.getColor(mContext, R.color.white));
//
//                holder.txt_item_ccount.setVisibility(View.VISIBLE);
//                holder.txt_item_ccount.setText(hashValues.get(mData.get(position).getTitle().toString()));
//            }
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    int position = holder.getAdapterPosition();
//                    count++;
//                    //countSelectedArr.add(String.valueOf(count));
//                    countSelectedArr = count;
//
////                    if (chksldQtyArr.contains(Integer.parseInt(mData.get(position).getProductID()))){
////                        //sldQtyArr.add(mData.get(position).getProductID());
////                        //totalqty += Integer.parseInt(mData.get(position).getProductID());
////                        Log.i("IF___if","iffff");
////                    }else{
//////                        Log.i("IF___else","elseeee");
////                        totalqty += Integer.parseInt(mData.get(position).getProductID());
//                        //sldQtyArr.add(mData.get(position).getProductID());
//                        //sldQtyArr.add(String.valueOf(count));
//                        //sldQtyArr.add(String.valueOf(count));
////                        sldQtyArr.add(String.valueOf(totalqty));
//                        sldNameArr.add(mData.get(position).getTitle());
//                        sldQtyArr.add(mData.get(position).getProductID());
//                        sldIDArr.add(mData.get(position).getProductID());
//                        sltPriceTotal += Double.parseDouble(mData.get(position).getPrice());
//                        str_sltPriceTotal = String.valueOf(sltPriceTotal);
//                        //sltPriceTotalArr.add(Integer.parseInt(selectedPrice))
//                        sltPriceTotalArr.add(mData.get(position).getPrice());
//                        chksldQtyArr.add(Integer.parseInt(mData.get(position).getProductID()));
//                        sldCategoryIDArr.add(mData.get(position).getProductCategoryID());
//                        sldCategoryNameArr.add(mData.get(position).getProductCategoryName());
//                        sltBillDisArr.add(mData.get(position).getPrice());
//
////                        if (arr.contains(mData.get(position).getTitle())){
////                            ccount = 0;
////                            ccount++;
////                            //arr.add(c.getString(2));
////                            //arr.clear();
////                        }else{
////                            ccount++;
////                            //arr.add(mData.get(position).getTitle());
////                        }
////                        hashValuesCount.put(mData.get(position).getTitle(), String.valueOf(ccount));
////                    }
//                    Log.i("__totalqty", String.valueOf(totalqty)+"__"+count);
//                    count_totalItems = count + totalItems;
//                   // count_item_selected = count + Integer.parseInt(hashValues.get(mData.get(position).getTitle().toString()));
//                    if (count_totalItems > 0) {
//                        //if (hashValuesCount.containsKey(mData.get(position).getTitle())) {
//                            count_item_selected = Integer.parseInt(holder.txt_item_ccount.getText().toString()) + 1;
//                            holder.txt_item_ccount.setVisibility(View.VISIBLE);
//                            holder.txt_item_ccount.setText(count_item_selected.toString());
//                       // }
//                        //MainActivity.btnCheckout.setText("CHECKOUT    "+ count + " Products "+ "      "+ "$210.45" );
//                        MainActivity.btnCheckout.setText(Html.fromHtml("<b>" + "CHECKOUT&nbsp;&nbsp;&nbsp;&nbsp;" + "</b>" +
//                                "<small>" + count_totalItems + " Products " + "</small>" +
//                                "<b>" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$" + String.format("%.2f", Double.valueOf(sltPriceTotal + totalAmount)) + "</b>"));
//                    }
////                Snackbar snackbar = Snackbar.make(v,"CHECKOUT    "+count+" Products",Snackbar.LENGTH_LONG);
////
////                // Display an action button in Snackbar
////                snackbar.setAction("$210.45", new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////                        // Do something
////                    }
////                });
////
////                // Set the Snackbar action button default text color
////                //snackbar.setActionTextColor(Color.parseColor("#FF378F44"));
////                snackbar.setActionTextColor(Color.parseColor("#FFFFFF"));
////
////                // Change the Snackbar default text color
////                View snackbarView = snackbar.getView();
////                TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
////                tv.setTextColor(Color.WHITE);
////
////                // Change the Snackbar default background color
////                //snackbarView.setBackgroundColor(Color.parseColor("#FFB0D9B9"));
////                snackbarView.setBackgroundColor(Color.parseColor("#7BC043"));
////
////                // Display the Snackbar
////                snackbar.show();
////                if (position!=RecyclerView.NO_POSITION){
////                    if (counter[position]==0){
//////                        holder.ivTr.setVisibility(View.VISIBLE);
//////                        holder.tvName.setTextColor(ContextCompat.getColor(context, R.color.green));
//////                        holder.tvDescr.setTextColor(ContextCompat.getColor(context, R.color.green));
////                        counter[position]=1;
////                    }else{
//////                        holder.ivTr.setVisibility(View.GONE);
//////                        holder.tvName.setTextColor(ContextCompat.getColor(context, R.color.white));
//////                        holder.tvDescr.setTextColor(ContextCompat.getColor(context, R.color.white));
////                        counter[position]=0;
////                    }
////                    Snackbar.make(v, "CHECKOUT " + position + "| counter: " + counter[position],
////                            Snackbar.LENGTH_LONG)
////                            .setAction("Action", null).show();
////
////                }
//
//                }
//            });
//
//
//            holder.itemView.setLongClickable(true);
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    item_name =  mData.get(position).getTitle().toString();
//                    item_countt =  holder.txt_item_ccount.getText().toString();
//                    Double amt = Double.valueOf(item_countt) * Double.valueOf(mData.get(position).getPrice().toString());
//                    item_pricee = String.valueOf(amt);
//                    item_diss = "";
//                    old_pricee = "";
//                    FragmentManager manager = ((AppCompatActivity) mContext).getSupportFragmentManager();
//                    EditProductSheetFragment editProductSheetFragment = new EditProductSheetFragment();
//                    //editProductSheetFragment.show(getSupportFragmentManager(), editProductSheetFragment.getTag());
//                    editProductSheetFragment.show(manager, editProductSheetFragment.getTag());
//                    return true;
//                }
//            });
//
//        }else if (str.equals("fragment4")) {
//            Intent intent = new Intent(mContext, MainActivity.class);
//            intent.putExtra("name", "Fragment____0");
//            mContext.startActivity(intent);
//        }else{
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = holder.getAdapterPosition();
//                    String selectedID = mData.get(position).getProductID();
//                    Log.i("selectedID",selectedID);
//                    Toast.makeText(v.getContext(), "selectedID " + selectedID, Toast.LENGTH_SHORT).show();
//                    //mContext.startActivity(new Intent(mContext, AddNewProductActivity.class));
//                    Intent intent = new Intent(mContext, AddNewProductActivity.class);
//                    intent.putExtra("ID", String.valueOf(selectedID));
//                    mContext.startActivity(intent);
//                }
//            });
//
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    int position = holder.getAdapterPosition();
//                    count_selected ++;
//                    selectedItems.add(count_selected);
//                    Log.i("dsfdsf_selectedItems", String.valueOf(selectedItems));
//                    Toast.makeText(v.getContext(), "Position is " + position, Toast.LENGTH_SHORT).show();
//                    holder.itemView.setBackgroundColor(Color.parseColor("#567845"));
//                    holder.product_linear_layout_id.setBackgroundColor(Color.parseColor("#337ab7"));
//                    return false;
//                }
//            });
//        }
//    }
//
//    private void getBillByProductID() {
//        Log.i("__sdsd", MainActivity.strbillNo);
//        //String sql = "Select TotalItems,totalNettAmount FROM BillDetails Where BillNo = '" + MainActivity.strbillNo + "' ";
//        String sql = " SELECT count(ProductQty),SUM(ProductPrice),ProductName FROM Details_BillProduct Where BillNo = '" + MainActivity.strbillNo + "' "+
//                "  group by ProductName";
//        Cursor c = DBFunc.Query(sql, false);
//        if (c != null) {
//            totalItems = 0;
//            totalAmount = 0.0;
//            while (c.moveToNext()) {
//                //if (c.moveToNext()) {
//                if (!c.isNull(0)) {
//                    Log.i("DSF__", String.valueOf(totalItems));
//                    Log.i("DSF_totalAmount_", String.valueOf(totalAmount));
//                    totalItems += c.getInt(0);
//                    totalAmount += Double.valueOf(c.getString(1));
//                    //arr.add(c.getString(2));
//                    hashValues.put(c.getString(2),c.getString(0));
//                }
//            }
//            c.close();
//        }
//        Log.i("xzsd_", String.valueOf(totalItems));
//        Log.i("xzsd_dd", String.valueOf(totalAmount));
//        String str_product = "Product";
//        if (totalItems > 0) {
//            str_product = "Products";
//        }
//    }
//    private void getBillByBillNo() {
//        Log.i("__sdsd", MainActivity.strbillNo);
//        //String sql = "Select TotalItems,totalNettAmount FROM BillDetails Where BillNo = '" + MainActivity.strbillNo + "' ";
//        String sql = " SELECT count(ProductQty),SUM(ProductPrice) FROM Details_BillProduct Where BillNo = '" + MainActivity.strbillNo + "' "+
//                "  group by BillNo";
//        Cursor c = DBFunc.Query(sql, false);
//        if (c != null) {
//            totalItems = 0;
//            totalAmount = 0.0;
//            while (c.moveToNext()) {
//                //if (c.moveToNext()) {
//                if (!c.isNull(0)) {
//                    Log.i("DSF__", String.valueOf(totalItems));
//                    Log.i("DSF_totalAmount_", String.valueOf(totalAmount));
//                    totalItems += c.getInt(0);
//                    totalAmount += Double.valueOf(c.getString(1));
//                }
//            }
//            c.close();
//        }
//        Log.i("xzsd_", String.valueOf(totalItems));
//        Log.i("xzsd_dd", String.valueOf(totalAmount));
//        String str_product = "Product";
//        if (totalItems > 0) {
//            str_product = "Products";
//        }
//    }
//        @Override
//    public int getItemCount() {
//        return mData.size();
//    }
//    public int getSelectedItemCount() {
//        if (selectedItems.size() > 0){
//
//            return selectedItems.size();
//        }else {
//
//            return 0;
//        }
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public LinearLayout product_linear_layout_id;
//        public TextView tv_book_title,book_price_id,txt_item_ccount;
//        public ImageView img_book_thumbnail;
//        CardView cardView ;
//        public MyViewHolder(View itemView) {
//            super(itemView);
//            product_linear_layout_id = (LinearLayout) itemView.findViewById(R.id.product_linear_layout_id) ;
//            tv_book_title = (TextView) itemView.findViewById(R.id.book_title_id) ;
//            book_price_id = (TextView) itemView.findViewById(R.id.book_price_id) ;
//            img_book_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
//            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
//            txt_item_ccount = (TextView) itemView.findViewById(R.id.txt_item_ccount);
//            getBillByBillNo();
//            getBillByProductID();
//
//            mHandler = new Handler();
//            m_Runnable.run();
//        }
//    }
//    private final Runnable m_Runnable = new Runnable()
//    {
//        public void run()
//
//        {
//            //oast.makeText(mContext,"in runnablaaae",Toast.LENGTH_SHORT).show();
////            Toast.makeText(AddTaxConfigurationActivity.this,"in runnable ddd"+TaxTypeSheetFragment.selected_tax_name ,Toast.LENGTH_SHORT).show();
//
//            mHandler.postDelayed(m_Runnable,500);
//            //AddTaxConfigurationActivity.this.mHandler.postDelayed(m_Runnable,20000);
//        }
//
//    };
//
//}
