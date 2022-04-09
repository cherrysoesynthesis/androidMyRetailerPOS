package com.dcs.myretailer.app.domain.mastertables.mastertables.taxtype;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TaxTypeModel {
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "datetime")
    Long dt;

    public TaxTypeModel(Integer id, String name, Long dt) {
        this.id = id;
        this.name = name;
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

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }
}
