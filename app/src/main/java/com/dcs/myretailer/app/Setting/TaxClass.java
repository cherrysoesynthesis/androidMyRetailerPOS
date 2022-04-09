package com.dcs.myretailer.app.Setting;

class TaxClass {
    Long taxID;
    String name = "";
    String acronym = "";
    double rate = 0;
    int type = 0;
    long index = -1;

    public TaxClass(){}

    public TaxClass(long index){
        this.index = index;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setAcronym(String acronym){
        if(acronym.length()==0){
            this.acronym = "";
        }else{
            this.acronym = acronym.substring(0, 1);
        }
    }

    public String getAcronym(){
        return acronym;
    }

    public void setRate(double rate){
        this.rate = rate;
        if(this.rate<0)this.rate = 0;
        if(this.rate>100)this.rate = 100;
    }

    public double getRate(){
        return rate;
    }

    public void setType(int type){
        this.type = type;
    }

    public int getType(){
        return type;
    }

    public void setIndex(long index){
        this.index = index;
    }

    public long getIndex(){
        return index;
    }

    public Long getTaxID() {
        return taxID;
    }

    public void setTaxID(Long taxID) {
        this.taxID = taxID;
    }
}