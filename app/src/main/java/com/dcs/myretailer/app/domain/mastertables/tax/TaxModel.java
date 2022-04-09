package com.dcs.myretailer.app.domain.mastertables.mastertables.tax;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TaxModel {
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "acronym")
    String acronym;
    @ColumnInfo(name = "rate")
    Double rate;
    @ColumnInfo(name = "type")
    Integer type;
    @ColumnInfo(name = "service_charges")
    Double service_charges;
    @ColumnInfo(name = "seq")
    Integer seq;
    @ColumnInfo(name = "datetime")
    Long dt;

    public TaxModel(Integer id, String name, String acronym, Double rate, Integer type, Double service_charges, Integer seq, Long dt) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.rate = rate;
        this.type = type;
        this.service_charges = service_charges;
        this.seq = seq;
        this.dt = dt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getService_charges() {
        return service_charges;
    }

    public void setService_charges(Double service_charges) {
        this.service_charges = service_charges;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }
}
